$(function(){

    // 切换分类
    $("#submitBtn").click(function(e){
        e.preventDefault();

        var voteNo = $("#voteNo").val();
        var noToBeVotedEls = $(".J_no-to-be-voted"),
            nosToBeVoted = [];
        for (var i = 0, len = noToBeVotedEls.length; i < len; i++) {
            var noToBeVotedEl = $(noToBeVotedEls[i]);
            if (noToBeVotedEl.is(':checked')) {
                nosToBeVoted.push(noToBeVotedEl.val());
            }
        }

        $.post("/vote/submit",{voteNo:voteNo, noToBeVoted: nosToBeVoted.join(",")},function(result){
            var json = $.parseJSON(result);
            if (json.code == 200) {
                alert("投票成功！");
            } else {
                alert("投票失败：[" + json.msg + "]");
            }
        });
    });
})