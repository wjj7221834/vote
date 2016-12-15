//$(function(){
//    // checkbox的事件
//    $("#J_submit").click(function(e){
//        var phoneNo = $('#J_phoneNo').val(),
//            pass = $('#J_pass').val();
//
//        // validate  phoneNo
//        if (phoneNo == '') {
//            alert('手机号码不能为空...');
//            return;
//        }
//
//        // validate phoneNo length
//        if (phoneNo.length != 4) {
//            alert('需要手机号码后4位哦...');
//            return;
//        }
//
//        // validate pass
//        if (pass == '') {
//            alert('密码不能为空...');
//            return;
//        }
//
//        // validate pass length
//        if (pass.length != 6) {
//            alert('密码是身份证后6位哦...');
//            return;
//        }
//
//        $.post("/vote/login/post",{phoneNo:phoneNo, pass:pass},function(result){
//            var json = $.parseJSON(result);
//            if (json.code == 200) {
//                location.href = '/vote/index';
//            } else {
//                alert("登陆失败");
//            }
//        });
//    });
//})