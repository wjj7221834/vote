<!DOCTYPE html>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>CCB投票系统--结果页</title>
    <link href="/static/css/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css"/>
    <link href="/static/css/main.css" rel="stylesheet" type="text/css" />

    <style>
        .container{padding-left: 10px;}
        .container .header{padding-top: 20px;font-size: 18px;color: red;padding-bottom: 15px;}

        .reset{margin:0;padding:0}
    </style>
</head>
<body style="background-color: white;">
    <input type="hidden" id="J_prize_id" value="${id}"/>
    <input type="hidden" id="J_progress_sum" value="${sum}"/>

    <div class="container">
        <#if prize?if_exists>
            <div class="header reset">
            ${prize.name}
            </div>
        </#if>

        <#list prizeCandidateList as prizeCandidate>
            <div class="progress" id="progress_${prizeCandidate.userId}" style="margin-bottom: 20px;">
                <div class="elapsed" style="text-align: left;padding-bottom: 5px;font-size: 16px;font-weight: bold;">${prizeCandidate.name}</div>
                <input type="hidden" class="percent" data-value='0'/>
                <div class="pbar"></div>
            </div>
        </#list>
    </div>

<script type="text/javascript" src="/static/js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="/static/js/jquery-ui-1.8.16.custom.min.js"></script>
<script type="text/javascript" src="/static/js/result.js"></script>
</body>
</html>

