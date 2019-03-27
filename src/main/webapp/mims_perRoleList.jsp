<%--
  Created by IntelliJ IDEA.
  User: charlesbean
  Date: 2019/3/20
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>学院办公信息管理系统-角色列表</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/media/layui/css/layui.css" media="all">
    <script src="${pageContext.request.contextPath}/media/js/jquery.min.js"></script>
    <script type="text/javascript">
        //上一页、下一页、尾页、首页的跳转
        function goPage(pageIndex) {
            var pageSize = $("#pageSize").val();
            $("#informationForm").attr("action", "${pageContext.request.contextPath}/role/mims_perRoleList?pageSize=" + pageSize + "&pageIndex=" + pageIndex);
            $("#informationForm").submit();
        }

        //跳转到特定的某一页的写法
        function goPageNum() {
            var pageIndex = $("#pageNum").val();
            goPage(pageIndex, pageSize);
        }

        //选择每页显示的大小
        function changePageSize() {
            var pageSize = $("#pageSize").val();
            $("#informationForm").attr("action", "${pageContext.request.contextPath}/role/mims_perRoleList?pageSize=" + pageSize);
            $("#informationForm").submit();
        }
        //1 删除记录
        function deleteRole(roleId, m) {
            layui.use('table', function () {
                layer.confirm('确认删除?', {
                    btn: ['是', '否'],
                    btn1: function () {
                        $.ajax({
                            url: "${pageContext.request.contextPath}/role/mims_perRoleDelete",
                            type: "POST",
                            data: {"roleId": roleId},
                            success: function (obj) {
                                if (obj == true) {
                                    layer.msg("删除成功", {icon: 6});
                                    $(m).parent().parent().remove();
                                } else {
                                    layer.msg("删除失败", {icon: 5}, function () {
                                        location.reload(true);
                                    });
                                }
                            }
                        })
                    },
                    btn2: function () {
                        layer.msg("取消删除", {icon: 4}), function () {
                            location.reload(true);
                        };
                    }
                });
            });
        }
    </script>
</head>
<body>
<form id="informationForm" method="post"></form>
<div class="layui-container">
    <div class="layui-btn-group">
        <button class="layui-btn layui-btn-norma" onclick="informationAdd()">
            <i class="layui-icon">&#xe654;</i>添加角色
        </button>
    </div>
</div>
<div class="layui-container">
    <%-- 第一部分：展示角色信息--%>
    <table class="layui-table" id="tbdata" lay-filter="tbop">
        <thead>
        <tr>
            <td>序号</td>
            <td>角色名称</td>
            <td>角色备注</td>
            <td>操作</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pageHelper.list}" var="r">
            <tr>
                <td>${r.id }</td>
                <td>${r.name }</td>
                <td>${r.description}</td>
                <td>
                    <a class="layui-btn layui-btn-mini" href="#" onclick="informationUpdate(${r.id});">编辑</a>
                    <a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="del"
                       onclick="deleteRole(${r.id},this);">删除</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <!-- 第二部分：分页信息  -->
    <div class="layui-box layui-laypage layui-laypage-default" id="layui-laypage-1">
        <!-- 1首页 -->
        <a href="#" onclick="goPage(1);">首页</a>
        <!-- 2上一页 -->
        <c:if test="${pageHelper.pageIndex!=1}">
            <a href="#" onclick="goPage(${pageHelper.pageIndex-1})">&lt;</a>
        </c:if>
        <!-- 3中间页 -->
        <c:forEach begin="${pageHelper.startNum}" end="${pageHelper.endNum}" step="1" var="i">
            <c:if test="${pageHelper.pageIndex==i}">
                <span style="color: #009688; font-weight: bold;">${i}</span>
            </c:if>
            <c:if test="${pageHelper.pageIndex!=i}">
                <a href="#" onclick="goPage(${i})">${i}</a>
            </c:if>
        </c:forEach>
        <!-- 4下一页 -->
        <c:if test="${pageHelper.pageIndex!=pageHelper.totalPage}">
            <a href="#" onclick="goPage(${pageHelper.pageIndex+1});">&gt;</a>
        </c:if>
        <!-- 5末页 -->
        <a href="#" onclick="goPage(${pageHelper.totalPage})">尾页</a>
        <!-- 6跳转特殊一页 -->
        <span class="layui-laypage-skip">到第
            <input type="text" id="pageNum" size="1" value="${pageHelper.pageIndex }" class="layui-input">页
            <input type="button" value="确定" class="layui-laypage-btn" onclick="goPageNum(${pageHelper.pageSize});">
        </span>
        <!-- 7共几条 -->
        <span class="layui-laypage-count">共${pageHelper.totalCount}条</span>
        <span class="layui-laypage-limits"> <!-- 8选择页面 -->
            <select lay-ignore="" id="pageSize" onchange="changePageSize();">
					<option value="3" ${pageHelper.pageSize==3?'selected':''}>3条/页</option>
					<option value="6" ${pageHelper.pageSize==6?'selected':''}>6条/页</option>
					<option value="9" ${pageHelper.pageSize==9?'selected':''}>9条/页</option>
			</select>
        </span>
    </div>
</div>
<script src="${pageContext.request.contextPath}/media/layui/layui.js"></script>
</body>
</html>
<%--编辑角色信息--%>
<div style="display: none; margin-top: 10px; width: 480px" id="mims_roleUpdate">
    <form id="roleUpdateForm" class="layui-form layui-form-pane">
        <div class="layui-form-item" pane>
            <label class="layui-form-label">角色名称：</label>
            <div class="layui-input-inline">
                <input id="updateId" name="id" type="hidden">
                <input id="updateName" name="name" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item" pane>
            <label class="layui-form-label">角色备注：</label>
            <div class="layui-input-inline">
                <input id="updateDescription" name="description" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item" pane>
            <label class="layui-form-label">菜单：</label>
            <div class="layui-input-inline" id="menu_update">
            </div>
        </div>
        <div class="layui-form-item" pane>
            <label class="layui-form-label">权限：</label>
            <div class="layui-input-inline" id="permission_update">
            </div>
        </div>
    </form>
</div>
<%--//异步更新角色信息--%>
<script !src="" type="text/javascript">

    var form;

    function informationUpdate(id) {
        layui.use('table', function () {
            form = layui.form;
            //首先异步获取该用户对应的所有信息--返回的obj是一个user对象。里面其实包含了他对应的角色细信息
            $.get("${pageContext.request.contextPath}/role/mims_perRoleGoUpdate", {'roleId': id}, function (obj) {
                //角色信息
                var role = obj.role;
                $("#updateId").val(role.id);
                $("#updateName").val(role.name);
                $("#updateDescription").val(role.description);
                //角色菜单
                var menu = obj.menu;
                for (var i = 0; i < menu.length; i++) {
                    if (menu[i].flag == true) {
                        $("#menu_update").append("<input type='checkbox' name='rids' value='" + menu[i].id + "' title='" + menu[i].name + " ' checked>");
                        form.render();
                    } else {
                        $("#menu_update").append("<input type='checkbox' name='rids' value='" + menu[i].id + "' title='" + menu[i].name + " '>");
                        form.render();
                    }
                }
                //角色权限
                var permission = obj.permission;
                for (var i = 0; i < permission.length; i++) {
                    if (permission[i].flag == true) {
                        $("#permission_update").append("<input type='checkbox' name='rids' value='" + permission[i].id + "' title='" + permission[i].name + " ' checked>");
                        form.render();
                    } else {
                        $("#permission_update").append("<input type='checkbox' name='rids' value='" + permission[i].id + "' title='" + permission[i].name + " '>");
                        form.render();
                    }
                }
            });
            //显示更新框
            layer.open({
                area: ['500px', '480px'],
                title: '角色编辑',
                type: 1,
                content: $('#mims_roleUpdate'), //这里content是一个普通的String
                btn: ['更新'],
                yes: function () {
                    $.ajax({
                        url: "${pageContext.request.contextPath}/role/mims_perRoleUpdate?roleId=" + id,
                        type: "post",
                        dataType: "json",
                        data: $("#roleUpdateForm").serialize(),
                        success: function (result) {
                            if (result == true) {
                                layer.msg("更新成功", {icon: 6}, function () {
                                    location.reload(true);
                                });
                            } else {
                                layer.msg("更新失败", {icon: 5}, function () {
                                    location.reload(true);
                                });
                            }
                        }
                    });
                },
                cancel: function () {
                    //这里为啥子需要更新？？
                    location.reload(true);
                }
            });
        });
    }
</script>

<%--添加角色信息--%>
<div style="display: none; margin-top: 10px; width: 480px" id="mims_roleAdd">
    <form id="roleAddForm" class="layui-form layui-form-pane">
        <div class="layui-form-item" pane>
            <label class="layui-form-label">角色名称：</label>
            <div class="layui-input-inline">
                <input id="addName" name="name" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item" pane>
            <label class="layui-form-label">角色备注：</label>
            <div class="layui-input-inline">
                <input id="addDescription" name="description" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item" pane>
            <label class="layui-form-label">菜单：</label>
            <div class="layui-input-inline" id="menu_add">

            </div>
        </div>
        <div class="layui-form-item" pane>
            <label class="layui-form-label">权限：</label>
            <div class="layui-input-inline" id="permission_add">

            </div>
        </div>
    </form>
</div>
<%--//异步添加角色信息--%>
<script type="text/javascript">

    var form;

    function informationAdd() {
        layui.use('table', function () {
            form = layui.form;
            //首先异步获取该用户对应的所有信息--返回的obj是一个user对象。里面其实包含了他对应的角色细信息
            $.get("${pageContext.request.contextPath}/role/mims_perRoleGetMenuPermission", null, function (obj) {
                //角色菜单
                var menu = obj.menu;
                for (var i = 0; i < menu.length; i++) {
                    if (menu[i].parentid == 0) {
                        $("#menu_add").append("<input type='checkbox' id='menu" + menu[i].id + "' name='ids' value='" + menu[i].id + "' title='" + menu[i].name + " '>");
                    } else {
                        $("#menu" + menu[i].parentid).after("<input type='checkbox' id='menu" + menu[i].id + "' name='ids' value='" + menu[i].id + "' title='" + menu[i].name + " '>")
                    }
                    form.render();
                }
                //角色权限
                var permission = obj.permission;
                for (var i = 0; i < permission.length; i++) {
                    $("#permission_add").append("<input type='checkbox' name='ids' value='" + permission[i].id + "' title='" + permission[i].name + " '>");
                    form.render();
                }
            });
            //显示添加框
            layer.open({
                area: ['500px', '480px'],
                title: '角色添加',
                type: 1,
                content: $('#mims_roleAdd'), //这里content是一个普通的String
                btn: ['新增'],
                yes: function () {
                    $.ajax({
                        url: "${pageContext.request.contextPath}/role/mims_perRoleAdd",
                        type: "post",
                        dataType: "json",
                        data: $("#roleAddForm").serialize(),
                        success: function (result) {
                            if (result == true) {
                                layer.msg("新增成功", {icon: 6}, function () {
                                    location.reload(true);
                                });
                            } else {
                                layer.msg("新增失败", {icon: 5}, function () {
                                    location.reload(true);
                                });
                            }
                        }
                    });
                },
                cancel: function () {
                    //这里为啥子需要更新？？
                    location.reload(true);
                }
            });
        });
    }
</script>
<script type="text/javascript">
    window.onbeforeunload=function (){
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/user/mima_userLoginout",
            async: false
        });
    }
</script>