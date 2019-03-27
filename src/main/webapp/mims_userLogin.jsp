<%--
  Created by IntelliJ IDEA.
  User: charlesbean
  Date: 2019/3/2
  Time: 19:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>学院办公信息管理系统-登录</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <!-- load css -->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/media/layui/css/layui.css"
          media="all">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/media/css/login.css"
          media="all">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/media/css/verify.css">
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/media/js/jquery.min.js"></script>
</head>
<body class="layui-bg-black">
<div class="layui-canvs"></div>
<div class="layui-layout layui-layout-login">
    <%--登录页面用户信息写入框--%>
    <form action="${pageContext.request.contextPath}/user/mims_login" method="post">
        <h1>
            <strong>学院办公信息管理系统</strong> <em>Math-Information Office System</em>
        </h1>
        <%--用户的账号--%>
        <div class="layui-user-icon larry-login">
            <input type="hidden" name="loginAddress" id="loginAddress">
            <input type="text" placeholder="账号" name="username" value="admin" class="login_txtbx"/>
        </div>
        <%--用户的密码--%>
        <div class="layui-pwd-icon larry-login">
            <input type="password" placeholder="密码" name="password" value="admin" class="login_txtbx"/>
        </div>
        <%--前端验证密码--%>
        <div class="feri-code">
            <div id="securityCode"></div>
        </div>
        <%--提交登录按钮--%>
        <div class="layui-submit larry-login">
            <input type="submit" id="submitButton" disabled="disabled" value="立即登陆" class="submit_btn"/>
        </div>
    </form>
    <div class="layui-login-text">
        <p>© 2018-2019 数信院2015级12班 马成 版权所有</p>
    </div>
</div>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/media/js/login.js"></script>
<script type="application/javascript"
        src="${pageContext.request.contextPath}/media/js/verify.min.js"></script>
<script type="text/javascript">
    $(function () {
        //滑动验证码
        $('#securityCode').pointsVerify({
            defaultNum: 6, //默认的文字数量
            checkNum: 1, //校对的文字数量
            vSpace: 5, //间隔
            imgName: ['1.jpg', '2.jpg', '3.jpg', '4.jpg', '5.jpg'],
            imgSize: {
                width: '400px',
                height: '200px',
            },
            barSize: {
                width: '400px',
                height: '40px',
            },
            ready: function () {
            },
            success: function () {
                //......后续操作
                $("#submitButton").attr("disabled", false);
            },
            error: function () {
            }
        });
        //获取登录的城市ajax
        $.ajax({
            url: 'http://api.map.baidu.com/location/ip?ak=ia6HfFL660Bvh43exmH9LrI6',
            type: 'POST',
            dataType: 'jsonp',  //跨域请求
            success: function (data) {
                var a = JSON.stringify(data.content.address_detail.province + data.content.address_detail.city);
                var reg = new RegExp("\"", "g");
                //alert(a.replace(reg,"")); //对应的城市
                $("#loginAddress").val(a.replace(reg, ""));
            }
        });
    });
</script>
</body>
</html>