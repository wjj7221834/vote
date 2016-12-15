$(function(){
    $('.J_op_btn').click(function(e){
        e.preventDefault();

        var id = $(this).attr('data-id'),
            status = $(this).attr('data-status');

        $.ajax({
            url: '/vote/admin/setstatus',
            data: {id : id, status : status},
            success: function(response, status, xhr){
                var json = $.parseJSON(response);
                if (json.code != 200) {
                    alert(json.msg);
                    return;
                }
                location.reload();
            }
        });
    })

})