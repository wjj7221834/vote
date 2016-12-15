$(function(){

    // checkbox的事件
    $(".J_no-to-be-voted").click(function(e){
        var maxCount = parseInt($('#J_prize-count').val()),
            maxGroupCount = parseInt($('#J_prize-group-count').val()),
            checkedElList = $('.J_no-to-be-voted:checked'),
            currentGroupId = parseInt($('#J_current-group-id').val()),
            currentCount = checkedElList.length;

        // 获取之前的投票情况
//        var groupId2count = {};
//        checkedElList.each(function(){
//            var el = $(this),
//                groupId = parseInt(el.attr('data-group-id'));
//            if (groupId2count[groupId]) {
//                var oldV = groupId2count[groupId];
//                groupId2count[groupId] = (oldV + 1);
//            } else {
//                groupId2count[groupId] = 1;
//            }
//        })
//
//        for (var groupId in groupId2count) {
//            var c = groupId2count[groupId];
//            if (c > maxGroupCount) {
//                e.preventDefault();
//                alert("该奖项每个项目组人最多投" + maxGroupCount + "票哦~");
//                return;
//            }
//        }
        var sum = 0;
        checkedElList.each(function(){
            var el = $(this),
                groupId = parseInt(el.attr('data-group-id'));
            if (groupId == currentGroupId) {
                sum++;
            }
        })
        if (sum > maxGroupCount) {
            e.preventDefault();
            alert("客官，你又调皮了，只能给本组的小伙伴投" + maxGroupCount + "票哦~");
            return;
        }

        if (currentCount > maxCount) {
            e.preventDefault();
            alert("客官，你又调皮了，该奖项一个人最多投" + maxCount + "票哦~");
            return;
        }
    });

    // 切换分类
    $("#submitBtn").click(function(e){
        e.preventDefault();

//        var voteNo = $("#voteNo").val();
        var noToBeVotedEls = $(".J_no-to-be-voted"),
            prizeId = $("#J_prize-id").val(),
            nosToBeVoted = [];
        for (var i = 0, len = noToBeVotedEls.length; i < len; i++) {
            var noToBeVotedEl = $(noToBeVotedEls[i]);
            if (noToBeVotedEl.is(':checked')) {
                nosToBeVoted.push(noToBeVotedEl.val());
            }
        }

        $.post("/vote/submit",{
//            voteNo:voteNo,
            noToBeVoted: nosToBeVoted.join(","),
            prizeId:prizeId
        },function(result){
            var json = $.parseJSON(result);
            if (json.code == 200) {
                alert("投票成功！");
                location.href="/vote/index";
            } else {
                alert("投票失败：[" + json.msg + "]");
            }
        });
    });
})