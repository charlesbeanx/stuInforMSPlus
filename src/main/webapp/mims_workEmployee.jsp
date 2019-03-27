<%--
  Created by IntelliJ IDEA.
  User: charlesbean
  Date: 2019/3/22
  Time: 21:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>学院办公信息管理系统-员工管理</title>
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
            $("#mims_form").attr("action", "${pageContext.request.contextPath}/employee/mims_list?pageSize=" + pageSize + "&pageIndex=" + pageIndex);
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
            $("#mims_form").attr("action", "${pageContext.request.contextPath}/employee/mims_list?pageSize=" + pageSize);
            $("#mims_form").submit();
        }

        //删除
        function deleteInfor(id, location) {
            layui.use('table', function () {
                layer.confirm('确定删除本条记录吗？', {
                    btn: ['是', '否'],
                    btn1: function () {
                        $.ajax({
                            url: "${pageContext.request.contextPath}/employee/mims_delete",
                            type: "post",
                            data: {"id": id},
                            dataType: "json",
                            success: function (result) {
                                if (result == true) {
                                    layer.msg('删除成功', {icon: 6});
                                    $(location).parent().parent().remove();
                                } else {
                                    layer.msg('删除失败', {icon: 5});
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

        //更新状态
        function changeState(id,locked) {
            var sta = locked==0?'禁用':'启用';
            layui.use('table',function () {
                layer.confirm('确定要'+sta+'吗？',{
                    btn:['是','否'],
                    btn1:function () {
                        $.ajax({
                            url:'${pageContext.request.contextPath}/employee/mims_changeState',
                            type:"post",
                            data:{'id':id,'locked':locked},
                            dataType:'json',
                            success:function (result) {
                                if (result==true){
                                    layer.msg('更新成功',{icon:6},function () {
                                        location.reload(true);
                                    });
                                }else {
                                    layer.msg('更新失败',{icon:5},function () {
                                        location.reload(true);
                                    })
                                }
                            }
                        });
                    },
                    btn2:function () {
                        location.reload(true);
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
            <i class="layui-icon">&#xe654;</i>添加员工
        </button>
    </div>
</div>
<%--信息展示--%>
<div class="layui-container">
    <%--第一部分：数据展示--%>
    <table class="layui-table" id="tbdata" lay-filter="tbop">
        <thead>
        <tr>
            <td>工号</td>
            <td>名字</td>
            <td>状态</td>
            <td>性别</td>
            <td>操作</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pageHelper.list}" var="p">
            <tr>
                <td>${p.number}</td>
                <td>${p.name}</td>
                <td>
                    <span style="color: #1E9FFF">${p.locked==0?'启用':'禁用'}</span>
                </td>
                <td>${p.sex}</td>
                <td>
                    <a class="layui-btn layui-btn-mini"
                       href="${pageContext.request.contextPath}/user/mims_echoid=${p.id}" lay-event="detail">查看详情</a>
                    <a class="layui-btn layui-btn-mini " lay-event="detail" href="#"
                       onclick="updateInfor(${p.id});">编辑</a>
                    <a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="del" href="#"
                       onclick="deleteInfor(${p.id},this)">删除</a>
                    <a class="layui-btn layui-btn-danger layui-btn-mini" id="state" href="#"
                       onclick="changeState(${p.id},${p.locked})" lay-event="detail">${p.locked==0?'禁用':'启用'}</a>
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
    <form action="#" id="addForm" class="layui-form layui-form-pane">
        <div class="layui-form-item" pane>
            <label class="layui-form-label">员工名字：</label>
            <div class="layui-input-inline">
                <input name="name" id="addName" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item" pane>
            <label class="layui-form-label">员工性别：</label>
            <div class="layui-input-block">
                <select class="layui-select layui-select-tips" name="sex"  id="addSex">
                    <option class="layui-select-tips" value="男">男</option>
                    <option class="layui-select-tips" value="女">女</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item" pane>
            <label class="layui-form-label">联系电话：</label>
            <div class="layui-input-inline">
                <input name="phone" id="addPhone" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item" pane>
            <label class="layui-form-label">电子邮箱：</label>
            <div class="layui-input-inline">
                <input name="email" id="addEmail" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item" pane>
            <label class="layui-form-label">QQ：</label>
            <div class="layui-input-inline">
                <input name="qq" id="addQQ" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item" pane>
            <label class="layui-form-label">个人简介：</label>
            <div class="layui-input-inline">
                <input name="description" id="addDescription" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item" pane>
            <label class="layui-form-label">所属部门：</label>
            <div class="layui-input-inline" id="addDepartments">

            </div>
        </div>
    </form>
</div>
<%--1 新增资源js--%>
<script type="text/javascript">
    var form;
    function add() {
        layui.use('table', function () {
            form = layui.form;
            $.post("${pageContext.request.contextPath}/department/mims_echo",function (result) {
                var dep = result.departments;
                for (var i=0;i<dep.length;i++){
                    $("#addDepartments").append("<input type='checkbox' id='dep"+dep[i].id +"' name='ids' value='"+dep[i].id+"' title='"+dep[i].name+"' >");
                    form.render();
                }
            });
            layer.open({
                area: ['500px', '380px'],
                title: '新增员工',
                type: 1,
                content: $('#addDiv'),
                btn: ['新增'],
                yes: function () {
                    $.ajax({
                        url: "${pageContext.request.contextPath}/employee/mims_add",
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
                    location.reload(true);
                }
            });
        });
    }
</script>
<%--2 更新资源弹出框--%>
<div style="display: none;margin-top: 10px;width: 480px;" id="updateDiv">
    <form action="#" id="updateForm" class="layui-form layui-form-pane">
        <div class="layui-form-item" pane>
            <label class="layui-form-label">员工名字：</label>
            <div class="layui-input-inline">
                <input name="number" id="updateNumber" class="layui-input" readonly="readonly">
            </div>
        </div>
        <div class="layui-form-item" pane>
            <label class="layui-form-label">员工名字：</label>
            <div class="layui-input-inline">
                <input name="name" id="updateName" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item" pane>
            <label class="layui-form layui-form-label">员工性别:</label>
            <div class="layui-input-block">
                <select name="sex"  id="updateSex">
                    <option value="男" id="updateSexMan">男</option>
                    <option value="女" id="updateSexWoman">女</option>
                </select>
            </div>
        </div>

        <div class="layui-form-item" pane>
            <label class="layui-form-label">联系电话：</label>
            <div class="layui-input-inline">
                <input name="phone" id="updatePhone" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item" pane>
            <label class="layui-form-label">电子邮箱：</label>
            <div class="layui-input-inline">
                <input name="email" id="updateEmail" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item" pane>
            <label class="layui-form-label">QQ：</label>
            <div class="layui-input-inline">
                <input name="qq" id="updateQQ" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item" pane>
            <label class="layui-form-label">个人简介：</label>
            <div class="layui-input-inline">
                <input name="description" id="updateDescription" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item" pane>
            <label class="layui-form-label">所属部门：</label>
            <div class="layui-input-inline" id="updateDepartments">

            </div>
        </div>
    </form>
</div>
<%--2 更新资源JS,日期弹出框--%>
<script type="text/javascript">
    var form;
    function updateInfor(id) {
        layui.use('table', function () {
            form = layui.form;
            $.post('${pageContext.request.contextPath}/employee/mims_echo', {'id': id}, function (result) {
                var emp = result.employee;
                $("#updateNumber").val(emp.number);
                $("#updateName").val(emp.name);
                $("#updatePhone").val(emp.phone);
                $("#updateEmail").val(emp.email);
                $("#updateQQ").val(emp.qq);
                $("#updateDescription").val(emp.description);
                if(emp.sex=='男'){
                    $("#updateSexMan").selected('selected');
                }else
                    $("#updateSexWoman").selected('selected');
                var dep = result.departments;
                for (var i=0;i<dep.length;i++){
                    if (dep[i].flag==1){
                        $("#updateDepartments").append("<input type='checkbox' name='ids' value='"+dep[i].id+"' title='"+dep[i].name+"' checked>");
                        form.render();
                    }else{
                        $("#updateDepartments").append("<input type='checkbox' name='ids' value='"+dep[i].id+"' title='"+dep[i].name+"'>");
                        form.render();
                    }
                }

            });
            layer.open({
                area: ['500px', '380px'],
                title: '更新员工',
                type: 1,
                content: $('#updateDiv'),
                btn: ['更新'],
                yes: function () {
                    $.ajax({
                        url: "${pageContext.request.contextPath}/employee/mims_update",
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
                    layer.msg('已取消', {icon: 4},function () {
                        location.reload(true);
                    });
                }
            });
        });
    }
</script>