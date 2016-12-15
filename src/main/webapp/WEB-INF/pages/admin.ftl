<!DOCTYPE html>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>CCB投票系统--管理员</title>
    <style>
        .reset{margin:0;padding:0}

        li.prize{padding-left: 10px;list-style: none;height: 50px;}
        li.prize a{display: block;height: 30px; padding-top: 20px;float: left;font-size: 18px;}
        li.prize span{display: block;height: 30px;padding-top: 18px;padding-left: 15px;float: left;}

        li.prize span input{font-size: 18px;margin-left: 10px;}

        .ongoing{color:red;}
        .unstart{color:grey;}
    </style>
</head>
<body class="reset">

<ul class="reset">
<#list prizeList as prize>
    <li class="prize">
        <a class="<#if prize.statusCode == 2>ongoing<#else>unstart</#if>" href="/vote/result?id=${prize.id}">【${prize.status}】${prize.name}</a>
        <span class="op" style="display: inline-block;">
            <input class="J_op_btn" type="button" data-id="${prize.id}" data-status="0" value="初始化"/>
            <input class="J_op_btn" type="button" data-id="${prize.id}" data-status="1" value="开始"/>
            <input class="J_op_btn" type="button" data-id="${prize.id}" data-status="2" value="结束"/>
        </span>
    </li>
</#list>
</ul>

<script type="text/javascript" src="/static/js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="/static/js/admin.js"></script>
</body>
</html>

