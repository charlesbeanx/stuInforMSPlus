<%--
  Created by IntelliJ IDEA.
  User: charlesbean
  Date: 2019/3/18
  Time: 11:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>用户信息>>修改头像</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"  content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/media/layui/css/layui.css" media="all">
    <script type="text/javascript" src="${pageContext.request.contextPath}/media/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/media/layui/layui.js"></script>
</head>
<body>
<div class="layui-container" style="margin-top: 5px">
    <form class="layui-form" action="${pageContext.request.contextPath}/user/mims_userPhoto" method="post">
        <div class="layui-form-item">
            <label class="layui-form-label">头像</label>
            <div class="layui-input-block">
                <div class="layui-form-mid layui-word-aux">
                    <img src="${pageContext.request.contextPath}${userLogin.photo}" />
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">选择文件</label>
            <div class="layui-input-block">
                <input type="file" name="myPhoto" id="myPhoto" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <input class="layui-btn" style="margin-left: 10%"  id="btn1" type="submit" value="确认导入">
        </div>
    </form>
</div>
</div>
</body>
</html>
