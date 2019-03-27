<%--
  Created by IntelliJ IDEA.
  User: charlesbean
  Date: 2019/3/18
  Time: 20:20
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
    <script src="${pageContext.request.contextPath}/media/js/jquery.min.js"/>
    <script type="text/javascript">
        //带着每页大小去搜寻
        function searchData() {
            var pageSize = $("#pageSize").val();
            $("#userForm").attr("action", "${pageContext.request.contextPath}/user/mims_perUserList/?pageSize=" + pageSize);
            $("#userForm").submit();
        }

        //跳转到特定的某一页的写法
        function goPageNum() {
            var pageIndex = $("#pageNum").val();
            goPage(pageIndex, pageSize);
        }

        //选择每页显示的大小
        function changePageSize() {
            var pageSize = $("#pageSize").val();
            $("#mims_form").attr("action", "${pageContext.request.contextPath}/employee/mims_list?pageSize=" + pageSize);
            $("#mims_form").submit();
        }
        //跳转到特定的某一页的写法
        function goPageNum() {
            var pageIndex = $("#pageNum").val();
            goPage(pageIndex, pageSize);
        }


    </script>
</head>
<body>
<form id="userForm" action="${pageContext.request.contextPath}/user/mims_perUserList">
    <div class="layui-container">
        <div class="layui-row" style="margin-top: 10px">
            <!-- 搜索条件1 --名字 		 -->
            <div class="layui-col-xs3" style="margin-right: 20px">
                <div class="layui-form-item layui-form-text">
                    <label class="layui-form-label">姓名：</label>
                    <div class="layui-input-block">
                        <input type="text" id="username" name="username" class="layui-input" placeholder="用户姓名"
                               value="${user.username }">
                    </div>
                </div>
            </div>
            <!-- 搜索条件2 --是否已经启用  -->
            <div class="layui-col-xs3" style="margin-right: 20px">
                <div class="layui-form-item layui-form-text">
                    <label class="layui-form-label">状态：</label>
                    <div class="layui-input-block">
                        <select class="layui-input" id="locked" name="locked">
                            <option value="-1" ${user.locked==-1?'selected':'' }>全部</option>
                            <option value="0"  ${user.locked==0? 'selected':'' }>启用</option>
                            <option value="1"  ${user.locked==1? 'selected':'' }>禁用</option>
                        </select>
                    </div>
                </div>
            </div>
            <!-- 提交表单 -->
            <div class="layui-col-xs2">
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn" onclick="searchData()">
                            <i class="layui-icon layui-icon-search">搜索</i>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
<div class="layui-container">
    <%--第一部分：显示信息--%>
    <table class="layui-table" id="tbdata" lay-filter="tbop">
        <thead>
        <tr>
            <td>序号</td>
            <td>工号</td>
            <td>姓名</td>
            <td>职位</td>
            <td>状态</td>
            <td>操作</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pageHelper.list}" var="u">
            <tr>
                <td>${u.id }</td>
                <td>2015083412${u.id }</td>
                <td>${u.username }</td>
                <td>
                    <c:forEach items="${u.roles}" var="r">[${r.name}]
                    </c:forEach>
                </td>
                <td>
                    <span style="color: #1E9FFF">${u.locked==0?'启用':'禁用'}</span>
                </td>
                <td>
                    <a class="layui-btn layui-btn-mini layui-btn-mini"
                       href="${pageContext.request.contextPath}/user/mims_perUserInfor?userId=${u.id}"
                       lay-event="detail">查看详情
                    </a>
                    <a class="layui-btn layui-btn-mini"
                       href="#" onclick="userUpdate(${u.id});">编辑
                    </a>
                    <a class="layui-btn layui-btn-danger layui-btn-mini" id="state"
                       onclick="changeState(${u.id},${u.locked})"
                       lay-event="detail">${u.locked==0?'禁用':'启用'}
                    </a>
                    <a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="del"
                       onclick="deleteUser(${u.id},this);">删除
                    </a>
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
<script src="${pageContext.request.contextPath}/media/layui/layui.js"></script>
<script type="text/javascript">
    //删除记录
    function deleteUser(userId, m) {
        layui.use('table', function () {
            layer.confirm('确认删除本条记录吗?', {
                btn: ['是', '否'],
                btn1: function () {
                    $.ajax({
                        url: "${pageContext.request.contextPath}/user/mims_perUserDelete",
                        type: "POST",
                        data: {"userId": userId},
                        success: function (obj) {
                            if (obj == 'true') {
                                layer.msg("删除成功", {icon: 6});
                                $(m).parent().parent().remove();
                            } else {
                                layer.msg("删除失败", {icon: 5});
                                location.reload(true);
                            }
                        }
                    })
                },
                btn2: function () {
                    layer.msg("取消删除", {icon: 4}, function () {
                        location.reload(true);
                    });
                }
            });
        });
    }

    //启用禁用
    function changeState(userId, locked) {
        //设置一个值去衔接弹框的提示语
        var sta = locked == 0 ? '禁用' : '启用';
        layui.use('table', function () {
            layer.confirm('确认' + sta + '吗?', {
                btn: ['是', '否'],
                btn1: function () {
                    $.ajax({
                        url: "${pageContext.request.contextPath}/user/mims_perUserState",
                        type: "POST",
                        data: {"id": userId, "locked": locked},
                        success: function (obj) {
                            if (obj == 'true') {
                                layer.msg("更新状态成功", {icon: 6});
                                location.reload(true);

                            } else {
                                layer.msg("更新状态失败", {icon: 5});
                            }
                        }
                    })
                },
                btn2: function () {
                    layer.msg("取消更新用户状态", {icon: 4});
                    location.reload(true);
                }
            });
        });

    }

    var form;

    //异步更新数据
    function userUpdate(userId) {
        layui.use('table', function () {
            form = layui.form;
            //1 首先异步获取该用户对应的所有信息--返回的obj是一个user对象。里面其实包含了他对应的角色细信息
            $.post("${pageContext.request.contextPath}/user/mims_perUserGoUpdate", {'userId': userId}, function (obj) {
                //设置回显的角色信息
                var user = obj.user;
                $("#update_id").val(user.id);
                $("#update_username").val(user.username);
                //设置回显的资源信息
                var roles = obj.roles;
                for (var i = 0; i < roles.length; i++) {
                    if (roles[i].flag == 1) {
                        $("#user_update").append("<input type='checkbox' name='ids' value='" + roles[i].id + "' title='" + roles[i].name + " ' checked>");
                        form.render();
                    } else {
                        $("#user_update").append("<input type='checkbox' name='ids' value='" + roles[i].id + "' title='" + roles[i].name + " '>");
                        form.render();
                    }
                }
            });
            //2 显示 更新框
            layer.open({
                area: ['500px', '480px'],
                title: '用户编辑',
                type: 1,
                content: $('#mims_userUpdate'), //这里content是一个普通的String
                btn: ['更新'],
                yes: function () {
                    $.ajax({
                        url: "${pageContext.request.contextPath}/user/mims_perUserUpdate",
                        type: "POST",
                        dataType: "json",
                        data: $("#userUpdate").serialize(),
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
                    layer.msg("取消更新用户状态", {icon: 4}, function () {
                        location.reload(true);
                    });
                }
            });
        });
    }
</script>
</body>
</html>
<div style="display: none;margin-top: 10px;width: 480px" id="mims_userUpdate">
    <form id="userUpdate" class="layui-form layui-form-pane">
        <div class="layui-form-item" pane>
            <label class="layui-form-label">工号：</label>
            <div class="layui-input-inline">
                <input id="update_id" name="id" class="layui-input" readonly="readonly">
            </div>
        </div>
        <div class="layui-form-item" pane>
            <label class="layui-form-label">姓名：</label>
            <div class="layui-input-inline">
                <input id="update_username" name="username" class="layui-input" readonly="readonly">
            </div>
        </div>
        <div class="layui-form-item" pane>
            <label class="layui-form-label">职位：</label>
            <div class="layui-input-inline" id="user_update">

            </div>
        </div>
    </form>
</div>
