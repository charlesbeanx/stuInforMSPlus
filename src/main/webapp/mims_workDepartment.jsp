<%--
  Created by IntelliJ IDEA.
  User: charlesbean
  Date: 2019/3/22
  Time: 21:42
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
    <script src="${pageContext.request.contextPath}/media/layui/layui.js"></script>
    <%--分页信息的JS--%>
    <script type="text/javascript">
        //上一页、下一页、尾页、首页的跳转
        function goPage(pageIndex) {
            var pageSize = $("#pageSize").val();
            $("#mims_form").attr("action", "${pageContext.request.contextPath}/department/mims_list?pageSize=" + pageSize + "&pageIndex=" + pageIndex);
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
            $("#mims_form").attr("action", "${pageContext.request.contextPath}/department/mims_list?pageSize=" + pageSize);
            $("#mims_form").submit();
        }

        //删除
        function deleteInfor(id, location) {
            layui.use('table', function () {
                layer.confirm('确定删除本条记录吗？', {
                    btn: ['是', '否'],
                    btn1: function () {
                        $.ajax({
                            url: "${pageContext.request.contextPath}/department/mims_delete",
                            type: "post",
                            data: {"id": id},
                            dataType: "json",
                            success: function (result) {
                                if (result == 1) {
                                    layer.msg('删除成功', {icon: 6});
                                    $(location).parent().parent().remove();
                                }
                                if (result == 0) {
                                    layer.msg('删除失败', {icon: 5});
                                }
                                if (result == 2) {
                                    layer.msg('该部门还有员工在职，请先解散员工')
                                }
                            }
                        });
                    },
                    btn2: function () {
                        layer.msg('取消', {icon: 4}, function () {
                            location.reload(true);
                        })
                    }
                });
            });
        }
    </script>
</head>
<body>
<%--表单--%>
<form action="" method="post" name="mims_form" id="mims_form"></form>
<%--添加资源--%>
<div class="layui-container">
    <div class="layui-btn-group">
        <button class="layui-btn layui-btn-norma" onclick="add()">
            <i class="layui-icon">&#xe654;</i>添加部门
        </button>
    </div>
</div>
<%--信息展示--%>
<div class="layui-container">
    <%--第一部分：数据展示--%>
    <table class="layui-table" id="tbdata" lay-filter="tbop">
        <thead>
        <tr>
            <td>部门序号</td>
            <td>部门名称</td>
            <td>创建时间</td>
            <td>使用状态</td>
            <td>部门简介</td>
            <td>操作</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pageHelper.list}" var="p">
            <tr>
                <td>${p.id}</td>
                <td>${p.name}</td>
                <td>${p.createtime}</td>
                <td>
                    <span style="color: #1E9FFF">${p.locked==0?'启用':'禁用'}</span>
                </td>
                <td>${p.description}</td>
                <td>
                    <a class="layui-btn layui-btn-mini " lay-event="detail" onclick="updateInfor(${p.id});">编辑</a>
                    <a class="layui-btn layui-btn-danger" lay-event="del" onclick="deleteInfor(${p.id},this)">删除</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <%--第二部分：分页信息--%>
    <div class="layui-box layui-laypage layui-laypage-default" id="layui-laypage-1">
        <%--1 首页--%>
        <a href="#" onclick="goPage(1)">首页</a>
        <%--2 上一页--%>
        <c:if test="${pageHelper.pageIndex!=1}">
            <a href="#" onclick="goPage(${pageHelper.pageIndex-1})">&lt;</a>
        </c:if>
        <%--3 中间页--%>
        <c:forEach begin="${pageHelper.startNum}" end="${pageHelper.endNum}" step="1" var="i">
            <%--判断是否是当前页，不能就有点击事件--%>
            <c:if test="${pageHelper.pageIndex==i}">
                <span style="color: #009688; font-weight: bold;">${i}</span>
            </c:if>
            <c:if test="${pageHelper.pageIndex!=i}">
                <a href="#" onclick="goPage(${i})">${i}</a>
            </c:if>
        </c:forEach>
        <%--4 下一页--%>
        <c:if test="${pageHelper.pageIndex!=pageHelper.totalPage}">
            <a href="#" onclick="goPage(${pageHelper.pageIndex+1})">&gt;</a>
        </c:if>
        <%--5 末页--%>
        <a href="#" onclick="goPage(${pageHelper.totalCount})">尾页</a>
        <%--6 跳转某一页--%>
        <span class="layui-laypage-skip">到第
            <input type="text" id="pageNum" size="1" value="${pageHelper.pageIndex }" class="layui-input">页
            <input type="button" value="确定" class="layui-laypage-btn" onclick="goPageNum(${pageHelper.pageSize});">
        </span>
        <%--7 共几页--%>
        <span class="layui-laypage-count">共${pageHelper.totalCount}条</span>
        <%--8 每页展示数据数量--%>
        <span class="layui-laypage-limits">
            <select lay-ignore="" id="pageSize" onchange="changePageSize();">
                <option value="3" ${pageHelper.pageSize==3?'selected':''}>3条/页</option>
                <option value="6" ${pageHelper.pageSize==6?'selected':''}>6条/页</option>
                <option value="9" ${pageHelper.pageSize==9?'selected':''}>9条/页</option>
            </select>
        </span>
    </div>
</div>

</body>
</html>
<%--1 新增资源弹出框--%>
<div style="display: none;margin-top: 10px;width: 480px;" id="addDiv">
    <form action="#" id="addForm" class="layui-form-pane">
        <div class="layui-form-item" pane>
            <label class="layui-form-label">部门名称：</label>
            <div class="layui-input-inline">
                <input name="name" id="addName" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item" pane>
            <label class="layui-form-label">部门简介：</label>
            <div class="layui-input-inline">
                <input name="description" id="addDescription" class="layui-input">
            </div>
        </div>
    </form>
</div>
<%--1 新增资源js--%>
<script type="text/javascript">
    function add() {
        layui.use('table', function () {
            form = layui.form;
            layer.open({
                area: ['500px', '380px'],
                title: '新增部门',
                type: 1,
                content: $('#addDiv'),
                btn: ['新增'],
                yes: function () {
                    $.ajax({
                        url: "${pageContext.request.contextPath}/department/mims_add",
                        type: "post",
                        data: $("#addForm").serialize(),
                        dataType: "json",
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
                    // layer.close(index);
                },
                cancel: function () {

                }
            });
        });
    }
</script>
<%--2 更新资源弹出框--%>
<div style="display: none;margin-top: 10px;width: 480px;" id="updateDiv">
    <form action="#" id="updateForm" class="layui-form-pane">
        <div class="layui-form-item" pane>
            <label class="layui-form-label">部门名称：</label>
            <div class="layui-input-inline">
                <input name="name" id="updateName" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item" pane>
            <label class="layui-form-label">部门简介：</label>
            <div class="layui-input-inline">
                <input name="description" id="updateDescription" class="layui-input">
            </div>
        </div>
    </form>
</div>
<%--2 更新资源JS,日期弹出框--%>
<script type="text/javascript">
    function updateInfor(id) {
        layui.use('table', function () {
            form = layer.form;
            $.post('${pageContext.request.contextPath}/department/mims_echo', {'id': id}, function (obj) {
                var result=obj.department;
                $("#updateName").val(result.name);
                $("#updateCreatetime").val(result.createtime);
                $("#updateDescription").val(result.description);
            });
            layer.open({
                area: ['500px', '380px'],
                title: '部门更新',
                type: 1,
                content: $('#updateDiv'),
                btn: ['更新'],
                yes: function () {
                    $.ajax({
                        url: "${pageContext.request.contextPath}/department/mims_update",
                        type: 'post',
                        data: $('#updateForm').serialize(),
                        dataType: 'json',
                        success: function (result) {
                            if (result == true) {
                                layer.msg('更新成功', {icon: 6}, function () {
                                })
                            } else {
                                layer.msg('更新失败', {icon: 5}, function () {
                                })
                            }
                        }
                    });
                    layer.close(index);
                },
                cancel: function () {
                    layer.msg('已取消', {icon: 4});
                }
            });
        });
    }
</script>