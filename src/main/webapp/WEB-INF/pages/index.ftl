<!DOCTYPE html>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>上海开发中心年度之星--投票活动</title>
    <style>
        .reset{margin:0;padding:0}
        .logout{position: absolute;right: 10px;top: 10px;font-weight: bold;}

        div.current-user{padding-right: 5%;font-size: 14px;float: right;}
        div.current-user span{font-size: 18px;}

        li.prize{width: 95%;padding-left: 5%}
        li.prize a{display: block;height: 30px;padding-top: 20px;text-decoration: none;}
        li.prize a span{font-size: 18px;}

        img.header{width: 100%;height: auto;}
        div.footer{width: 100%;text-align: center;background-color: #D3131E; height: 5%; vertical-align: middle; color: white; padding-top: 2%; font-size: 10px; position: fixed; top:95%;}

        .ongoing{color:red;}
        .unstart{color:grey;}
        .has-voted{color: grey;}
    </style>
</head>
<body class="reset">
<a href="/vote/login" class="logout">重新登录</a>

<img src="/static/css/bg.gif" class="header"/>
<div class="current-user">
    hi,<span>${userName}</span>
</div>
<ul id="container" class="reset" style="padding-bottom: 5%;">
    <#list prizeList as prize>
        <li class="prize">
            <a class="<#if prize.statusCode == 2>ongoing<#else>unstart</#if> <#if prize.hasVoted>has-voted</#if>"  href="/vote/prize?id=${prize.id}">
                <span>【${prize.status}】${prize.name}<#if prize.hasVoted>--已投票</#if></span>
            </a>
        </li>
    </#list>
</ul>
<div class="footer">上海开发中心党总支、工会、团委、后勤保障组联合举办</div>
</body>
</html>

