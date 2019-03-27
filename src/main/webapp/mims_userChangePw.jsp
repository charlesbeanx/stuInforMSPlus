<%--
  Created by IntelliJ IDEA.
  User: charlesbean
  Date: 2019/3/17
  Time: 17:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>用户信息>>密码修改</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"  content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/media/layui/css/layui.css" media="all">
    <script type="text/javascript" src="${pageContext.request.contextPath}/media/js/jquery.min.js"></script>
    <script type="text/javascript">
        function isOldPassword() {
            var oldPassword = $("#oldPassword").val();
            var userPassword = $("#userPassword").val();
            if(oldPassword!=userPassword){
                alert("输入原密码有误，请重新输入");
                location.reload();
            }
        }
        function theSamePassword() {
            var theFirstPassword = $("#firstPassword").val();
            var theNextPassword = $("#nextPassword").val();
            if (theFirstPassword != theNextPassword) {
                alert("输入的两次密码不一致，请重新输入");
            }
        }
    </script>
</head>
<body>
<div class="layui-container" style="margin-top: 5px">
    <form class="layui-form" action="${pageContext.request.contextPath}/user/mims_userChangePassword" method="post">
        <div class="layui-form-item">
            <label class="layui-form-label">原密码</label>
            <div class="layui-input-block">
                <input type="password" name="userPassword" id="userPassword" hidden value="${userLogin.password}">
                <input type="password" name="oldPassword" id="oldPassword" onblur="isOldPassword()"  placeholder="请输入原密码" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">新密码</label>
            <div class="layui-input-block">
                <input type="password" name="firstPassword" id="firstPassword" placeholder="请输入新密码" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">再确认</label>
            <div class="layui-input-block">
                <input type="password" name="nexPassword" id="nextPassword" onblur="theSamePassword();" placeholder="请再次输入新密码" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <input class="layui-btn" style="margin-left: 10%" type="submit" value="确定修改">
        </div>
    </form>
</div>
</body>
</html>