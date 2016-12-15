<!DOCTYPE html>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>上海开发中心年度之星--投票活动</title>
    <style>
        .reset{margin:0;padding:0}
        img.header{width: 100%;height: auto;}

        div.field{margin-left: 5%;padding-bottom: 3%;}
        div.field span{display: inline-block;text-align: right;width: 35%;}
        div.field input{width: 45%;}
        div.field input.submit{margin-left: 60%;width: 25%;font-size: 16px;}

        .error{color: red;}

        div.footer{width: 100%;text-align: center;background-color: #D3131E; height: 5%; vertical-align: middle; color: white; padding-top: 2%; font-size: 10px; position: fixed; top:95%;}
    </style>
</head>
<body class="reset">

<img src="/static/css/bg.gif" class="header"/>
<div class="container">
<form method="post" style="width: 100%;margin-top: 10%;" action="/vote/login/post">
    <#if code != -1>
        <div class="field error">
            用户名密码输入有误~
        </div>
    </#if>
    <div class="field">
        <span>手机号后4位：</span><input id="J_phoneNo" name="phoneNo" maxlength="4"/>
    </div>
    <div class="field">
        <span>身份证后6位：</span><input id="J_pass" type="password" name="pass" maxlength="6"/>
    </div>
    <div class="field">
        <input type="submit" class="submit" id="J_submit" value="提交"/>
    </div>
</form>
</div>
<div class="footer">上海开发中心党总支、工会、团委、后勤保障组联合举办</div>

<script type="text/javascript" src="/static/js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="/static/js/login.js"></script>
</body>
</html>

