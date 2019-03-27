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
    <title>学院办公信息管理系统-用户列表</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/media/layui/css/layui.css" media="all">
    <script src="${pageContext.request.contextPath}/media/js/jquery.min.js"></script>
    <script type="text/javascript">
        //上一页、下一页、尾页、首页的跳转
        function goPage(pageIndex) {
            var pageSize = $("#pageSize").val();
            $("#mims_form").attr("action", "${pageContext.request.contextPath}/permission/mims_list?pageSize=" + pageSize + "&pageIndex=" + pageIndex);
            $("#mims_form").submit();
        }
        //跳转到特定的某一页的写法
        function goPageNum() {
            var pageIndex = $("#pageNum").val();
            goPage(pageIndex, pageSize);
        }
        //选择每页显示的大小
        function changePageSize() {
            var pageSize = $("#pageSize").val();
            $("#mims_form").attr("action", "${pageContext.request.contextPath}/permission/mims_list?pageSize=" + pageSize);
            $("#mims_form").submit();
        }
    </script>
</head>
<body>
<form action="" method="post" name="mims_form" id="mims_form"></form>
<div class="layui-container">
    <div class="layui-btn-group">
        <button class="layui-btn layui-btn-norma" onclick="addPermission()">
            <i class="layui-icon">&#xe654;</i>添加资源
        </button>
    </div>
</div>
<div class="layui-container">
    <%--第一部分：数据展示--%>
    <table class="layui-table" id="tbdata" lay-filter="tbop">
        <thead>
        <tr>
            <td>序号</td>
            <td>名称</td>
            <td>类型</td>
            <td>页面路径</td>
            <td>图标</td>
            <td>级别</td>
            <td>操作</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pageHelper.list}" var="p">
            <tr>
                <td>${p.id}</td>
                <td>${p.name}</td>
                <td>${p.type=='menu'?'菜单':'权限'}</td>
                <td>${p.url}</td>
                <td>${p.icon}</td>
                <td>
                    <c:if test="${p.parentid==0 }">一级菜单</c:if>
                    <c:if test="${p.parentid>0 }">二级菜单</c:if>
                </td>
                <td>
                    <c:if test="${p.parentid==0}">
                        <a class="layui-btn layui-btn-mini" href="javascript:updateMenu1(${p.id });">编辑</a>
                    </c:if>
                    <c:if test="${p.parentid>0}">
                        <a class="layui-btn layui-btn-mini" href="javascript:updateMenu2(${p.id });">编辑</a>
                    </c:if>
                    <a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="del" onclick="deleteMenus(${p.id},this);">删除</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <%--第二部分：分页信息--%>
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
                <a href="#" onclick="goPage(${i})">${i }</a>
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
<!-- 内置jsp部分 -->
<script src="${pageContext.request.contextPath}/media/layui/layui.js"></script>
<script type="text/javascript">
    //删除记录
    function deleteMenus(id, m) {
        layui.use('table', function () {
            layer.confirm('确认删除?', {
                btn: ['是', '否'],
                btn1: function () {
                    $.ajax({
                        url: "${pageContext.request.contextPath}/permission/mims_delete",
                        type: "POST",
                        data: {"id": id},
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

    //增加资源
    function addPermission() {
        layui.use('table', function () {
            form = layui.form;
            //在这里点击，当点击的是二级菜单时候就弹出选项框获取父级菜单信息
            form.on('radio(level)', function (data) {
                changePid(data.value);
            });
            //开始新增
            layer.open({
                area: ['500px', '380px'],
                title: '添加资源',
                type: 1,
                content: $('#addResource'), //这里content是一个普通的String
                btn: ['新增'],
                yes: function () {
                    $.ajax({
                        url: "${pageContext.request.contextPath}/permission/mims_add",
                        type: "post",
                        dataType: "json",
                        data: $("#permission_add").serialize(),
                        success: function (result) {
                            if (result == true) {
                                layer.msg("添加成功", {icon: 6}, function () {
                                    location.reload(true);
                                });
                            } else {
                                layer.msg("添加失败", {icon: 5}, function () {
                                    location.reload(true);
                                });
                            }
                        }
                    });
                    layer.close();
                },
                cancel: function () {
                    // location.reload(true);
                }
            });
        });
    }

    //增加资源 -- 选择增加二级菜单时候需要提示一级菜单信息
    function changePid(i) {
        $("#addResource_select").css("display", "block");
        form.render();
        if (i == 2) {
            $.get("${pageContext.request.contextPath}/permission/mims_echo", null, function (arr) {
                var per = arr.permission;
                for (i = 0; i < per.length; i++) {
                    $("#add_resource").append("<option value=' " + per[i].id + " '>" + per[i].name + "</option>");
                }
                $("#addResource_select").css("display", "block");
                form.render();
            });
        } else {
            $("#addResource_select").css("display", "none");
            $("#add_resource").val(i);
        }
    }


    var form;

    //更新1级菜单
    function updateMenu1(id) {
        layui.use('table', function () {
            form = layui.form;
            $.post("${pageContext.request.contextPath}/permission/mims_echo", {'id': id}, function (obj) {
                var permission = obj.permission;
                $("#id").val(permission.id);
                $("#name").val(permission.name);
                $("#icon").val(permission.icon);
                $("#url").val(permission.url);
                $("#parentid").val(permission.parentid);
            });
            layer.open({
                area: ['500px', '380px'],
                title: '一级菜单更新',
                type: 1,
                content: $('#updateResource1'), //选择要展示的一级菜单
                btn: ['更新'],
                yes: function () {
                    $.ajax({
                        url: "${pageContext.request.contextPath}/permission/mims_update",
                        type: "post",
                        dataType: "json",
                        data: $("#update1").serialize(),
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
                    // location.reload(true);
                }
            });
        });
    }

    var form;

    //更新2级菜单
    function updateMenu2(id) {
        layui.use('table', function () {
            form = layui.form;
            $.get("${pageContext.request.contextPath}/permission/mims_echo", {'id': id}, function (obj) {
                var permission = obj.permission;
                $("#id2").val(permission.id);
                $("#name2").val(permission.name);
                $("#icon2").val(permission.icon);
                $("#url2").val(permission.url);
                var menu = obj.menu;
                for (var i = 0; i < menu.length; i++) {
                    if (menu[i].flag == true) {
                        $("#perId").append("<option value='" + menu[i].id + "' selected='selected'>" + menu[i].name + "</option>");
                        form.render();
                    } else {
                        $("#perId").append("<option value='" + menu[i].id + "' >" + menu[i].name + "</option>");
                        form.render();
                    }
                }
            });
            layer.open({
                area: ['500px', '380px'],
                title: '二级级菜单更新',
                type: 1,
                content: $('#updateResource2'), //选择要展示的一级菜单
                btn: ['更新'],
                yes: function () {
                    $.ajax({
                        url: "${pageContext.request.contextPath}/permission/mims_update",
                        type: "post",
                        dataType: "json",
                        data: $("#update2").serialize(),
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
                    // location.reload(true);
                }
            });
        });
    }
</script>
</body>
</html>
<!-- 增加资源弹出框 -->
<div style="display: none; margin-top: 10px; width: 480px" id="addResource">
    <form id="permission_add" class="layui-form ">
        <div class="layui-form-item">
            <label class="layui-form-label">名称：</label>
            <div class="layui-input-inline">
                <input name="name" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">类型：</label>
            <div class="layui-input-inline">
                <select name="type">
                    <option value="menu">菜单</option>
                    <option value="permission">权限</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">图标：</label>
            <div class="layui-input-inline">
                <input name="icon" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">路径：</label>
            <div class="layui-input-inline">
                <input name="url" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">级别：</label>
            <div class="layui-input-inline">
                <input type="radio" name="percode" value="1" lay-filter="level" title="一级" checked>
                <input type="radio" name="percode" value="2" lay-filter="level" title="二级">
            </div>
        </div>
        <!-- 添加二级菜单时候需要弹出来的弹出框 -->
        <div class="layui-form-item" id="addResource_select" style="display: none">
            <label class="layui-form-label">上级路径：</label>
            <div class="layui-input-inline">
                <select onselect="setPid(this)" name="parentid" id="add_resource">
                </select>
            </div>
        </div>
    </form>
</div>

<!-- 更新一级菜单弹出框 -->
<div style="display: none; margin-top: 10px; width: 480px" id="updateResource1">
    <form id="update1" class="layui-form ">
        <div class="layui-form-item">
            <label class="layui-form-label">名称：</label>
            <div class="layui-input-inline">
                <input name="id" type="hidden" id="id">
                <input name="parentid" type="hidden" id="parentid">
                <input name="name" class="layui-input" id="name">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">图标：</label>
            <div class="layui-input-inline">
                <input name="icon" class="layui-input" id="icon">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">路径：</label>
            <div class="layui-input-inline">
                <input name="url" class="layui-input" id="url">
            </div>
        </div>
    </form>
</div>

<!-- 更新二级菜单弹出框 -->
<div style="display: none; margin-top: 10px; width: 480px" id="updateResource2">
    <form id="update2" class="layui-form ">
        <div class="layui-form-item" id="dvl1">
            <label class="layui-form-label">上级路径：</label>
            <div class="layui-input-inline">
                <select name="parentid" id="perId">
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">名称：</label>
            <div class="layui-input-inline">
                <input name="id" type="hidden" id="id2">
                <input name="name" class="layui-input" id="name2">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">图标：</label>
            <div class="layui-input-inline">
                <input name="icon" class="layui-input" id="icon2">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">路径：</label>
            <div class="layui-input-inline">
                <input name="url" class="layui-input" id="url2">
            </div>
        </div>
    </form>
</div>
<script type="text/javascript">
    window.onbeforeunload=function (){
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/user/mima_userLoginout",
            async: false
        });
    }
</script>