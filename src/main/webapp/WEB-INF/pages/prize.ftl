<!DOCTYPE html>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>CCB投票系统--投票页面</title>

    <style>
        .container{padding-left: 20px;padding-bottom: 5%;}
        .reset{margin:0;padding:0}
        .container .header{padding-top: 20px;font-size: 18px;color: red;padding-bottom: 15px;}

        li.candidate{text-decoration: none;list-style: none;padding: 5px 0px;}
        li.candidate span{margin-left: 5px;position: relative;top: 2px;}

        img.header{width: 100%;height: auto;}
        div.footer{width: 100%;text-align: center;background-color: #D3131E; height: 5%; vertical-align: middle; color: white; padding-top: 2%; font-size: 10px; position: fixed; top:95%;}
    </style>
</head>
<body class="reset">

<img src="/static/css/bg.gif" class="header"/>
<div class="container">
    <#if prize?if_exists>
        <div class="header reset">
            ${prize.name}<br/>
            <span>【该奖项每人至少投${prize.minCount}票，最多投${prize.maxCandidateCount}票<#if (prize.groupMaxCount > 0)>，本项目组最多投${prize.groupMaxCount}票</#if>】</span><br/>
        </div>
    </#if>

    <input type="hidden" id="J_prize-id" value="${prizeId}"/>
    <input type="hidden" id="J_prize-count" value="${prize.maxCandidateCount}"/>
    <input type="hidden" id="J_prize-group-count" value="${prize.groupMaxCount}"/>
    <input type="hidden" id="J_current-group-id" value="${voteUserGroupId}"/>

    <ul class="reset">
        <#list candidateList as candidate>
            <li class="candidate">
                <input class="J_no-to-be-voted" data-group-id=${candidate.groupId} type="checkbox" value="${candidate.id}"/>
                <span>${candidate.name}<#if candidate.groupId == voteUserGroupId >【本项目组】</#if></span>
            </li>
        </#list>
    </ul>

    <input style="display: block;width: 200px;margin-top:10px;margin-bottom:10px;font-size:18px;" id="submitBtn" type="button" value="投票"/>

</div>
<div class="footer">上海开发中心党总支、工会、团委、后勤保障组联合举办</div>

<script type="text/javascript" src="/static/js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="/static/js/prize.js"></script>
</body>
</html>

