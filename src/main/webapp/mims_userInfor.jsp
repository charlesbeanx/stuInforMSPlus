<%--
  Created by IntelliJ IDEA.
  User: charlesbean
  Date: 2019/3/17
  Time: 17:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学院办公信息管理系统-我的信息</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/media/layui/css/layui.css"${pageContext.request.contextPath}/media="all">
    <script type="text/javascript" src="${pageContext.request.contextPath}/media/js/jquery.min.js"></script>
</head>
<body>
<div class="layui-container" style="margin-top: 5px">
    <form class="layui-form" action="#" method="post">
        <div class="layui-form-item">
            <label class="layui-form-label">名字</label>
            <div class="layui-input-block">
                <div class="layui-form-mid layui-word-aux">${userLogin.username }</div>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">头像</label>
            <div class="layui-input-block">
                <div class="layui-form-mid layui-word-aux">
                    <img src="${pageContext.request.contextPath}${userLogin.photo}" style="height: 20px; weight:20px "/>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 100px;">上次登录时间</label>
            <div class="layui-input-inline">
                <div class="layui-form-mid layui-word-aux">${userLogin.currentTime}</div>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 100px;">上次登录IP</label>
            <div class="layui-input-inline">
                <div class="layui-form-mid layui-word-aux">${userLogin.ip}</div>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">状态</label>
            <div class="layui-input-inline">
                <div class="layui-form-mid layui-word-aux">${userLogin.locked==0?'启用':'禁用' }</div>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">个人简介</label>
            <div class="layui-input-block">
                <div class="layui-form-mid layui-word-aux">${userLogin.description}</div>
            </div>
        </div>
    </form>
</div>
</body>
</html>