$(document).ready(function(){
    var sum = parseInt($('#J_progress_sum').val()),
        prizeId = $('#J_prize_id').val();

    $('.progress').children('.pbar').progressbar();

    function updateProgress() {
        $('.progress').each(function(i, el){
            var c = parseInt($(this).children('.percent').attr('data-value')),
                perCent = c / sum * 100;
            $(this).children('.pbar').children('.ui-progressbar-value').css('width', perCent + "%");
            $(this).children('.pbar').children('.ui-progressbar-value').html('<span>' + c + '</span>');
        })
    }

    // json format
    /**
     * {
     *     code : 200 | 500,
     *     msg : {
     *         status : {
     *             uid : 1
     *         },
     *         isStop : 1
     *     }
     * }
     */

    // check
    var interval = setInterval(function(){
        $.ajax({
            url: '/vote/status',
            data: {id : prizeId},
            success: function(response, status, xhr){
                var json = $.parseJSON(response);
                if (json.code != 200) {
                    return;
                }

                var userId2Count = json.msg.userId2Count;
                for (var uid in userId2Count) {
                    $('#progress_' + uid).children('.percent').attr('data-value', userId2Count[uid]);
                }

                if (json.msg.isStop == 1) {
                    clearInterval(interval);
                } else {
                    updateProgress();
                }
            }
        });
    }, 1000);
});