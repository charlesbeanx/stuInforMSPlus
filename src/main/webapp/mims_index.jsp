<%--
  Created by IntelliJ IDEA.
  User: charlesbean
  Date: 2019/3/13
  Time: 23:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>学院办公信息管理系统-主页</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/media/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/media/css/app.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/media/css/font-awesome.min.css">
    <style type="text/css">
        iframe {
            width: 98%;
            height: 98%;
        }
        .layui-tab-item {
            height: 98%;
        }
    </style>
</head>
<body>
<div class="layui-layout layui-layout-admin kit-layout-admin">
    <%--头部--%>
    <div class="layui-header">
        <div class="layui-logo">
            <img src="${pageContext.request.contextPath}/media/images/mims_logo.png" style="margin-right: 10px" class="layui-nav-img"/>
            <span style="font-size: 14px">学院办公信息管理系统</span>
        </div>
        <!-- 头部点击能够直接刷新进主页-->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item" lay-unselect>
                <a href="mims_index.jsp" class="layui-icon layui-icon-refresh-3"></a>
            </li>
        </ul>

        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item" style="margin-right: 20px">
                <a href="javascript:showTab(1001,'mims_processlist.jsp','待办事项');">待办事项<span class="layui-badge">${miamInforCount.userMessageCount}</span>
                </a>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="${pageContext.request.contextPath}${userLogin.photo}" class="layui-nav-img">${userLogin.username}
                </a>
                <dl class="layui-nav-child">
                    <dd>
                        <a href="javascript:showTab(1001,'mims_userInfor.jsp','我的信息');">我的信息</a>
                    </dd>
                    <dd>
                        <a href="javascript:showTab(1001,'mims_userPhoto.jsp','更改头像');">更改头像</a>
                    </dd>
                    <dd>
                        <a href="javascript:showTab(1002,'mims_userChangePw.jsp','修改密码');">修改密码</a>
                    </dd>
                </dl>
            </li>
            <li class="layui-nav-item">
                <a href='${pageContext.request.contextPath}/user/mims_userLoginout'>注销</a>
            </li>
        </ul>
    </div>
    <%--左侧导航栏--%>
    <div class="layui-side layui-bg-black ">
        <div class="layui-side-scroll">
            <ul class="layui-nav layui-nav-tree" lay-shrink="all" id="lm">
                <c:forEach items="${userMenus}" var="m">
                    <li class="layui-nav-item">
                        <c:if test="${m.parentid==0}">
                            <a href="javascript:;" id="1"><i class="${m.icon}"></i>&nbsp;${m.name}</a>
                        </c:if>
                        <dl class="layui-nav-child">
                            <c:forEach items="${userMenus}" var="n">
                                <c:if test="${n.parentid==m.id}">
                                    <dd>
                                        <a href="javascript:;" id="2" onclick="showTab(1,'${pageContext.request.contextPath}${n.url}','${n.name}')">&nbsp;&nbsp;&nbsp;&nbsp;${n.name}</a>
                                    </dd>
                                </c:if>
                            </c:forEach>
                        </dl>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
    <%--右侧内容栏--%>
    <div class="layui-body" id="container">
        <div class="layui-tab" lay-filter="demo" style="width: 100%; height: 90%">
            <ul class="layui-tab-title"></ul>
            <div class="layui-tab-content" style="width: 99%; height: 98%"></div>
        </div>
    </div>
    <%--底部备注信息--%>
    <div class="layui-footer">
        <p>Copyright 2011-2018
            <a href="http://www.macheng.mobi/" rel="nofollow" target="_blank" title="Charles">© 2018-2019 数信院2015级12班 马成 版权所有 </a>
        </p>
    </div>
</div>
<script src="${pageContext.request.contextPath}/media/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/media/layui/layui.js"></script>
<script>
    //JavaScript代码区域
    var element;
    layui.config({
        base: 'media/layui/lay/modules/'
    }).use(['element','app'], function(){
        element = layui.element;
        showTab(20000,"${pageContext.request.contextPath}/loginlog/list","首页");
    });
    var tid=-1;
    function showTab(id,u,n) {
        if(tid>0){
            element.tabDelete('demo',tid);
        }
        element.tabAdd('demo', {
            title:n,
            content: '<iframe data-frameid="'+id+'" scrolling="auto" frameborder="0" src="'+u+'"></iframe>', //支持传入html
            id:id
        });
        element.tabChange('demo', id);
        element.render();
        tid=id;
    }
</script>
</body>
</html>
<script type="text/javascript">
    window.onbeforeunload=function (){
            $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/user/mima_userLoginout",
            async: false
        });
    }
</script>
