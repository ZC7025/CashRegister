<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018-5-24
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>计划任务添加</title>
</head>
<link rel="stylesheet" href="<%=path%>/static/css/public.css">
<link rel="stylesheet" href="<%=path%>/static/layui/css/layui.css">
<link rel="stylesheet" href="<%=path%>/static/css/form-public.css">
<body>
<div class="container">
    <div class="account-right">
        <h1>计划任务添加</h1>
        <hr/>
        <form class="layui-form" id="taskPlan">
            <div class="layui-form-item">
                <label class="layui-form-label">类名</label>
                <div class="layui-input-block">
                    <input type="text" name="className" lay-verify="required" autocomplete="off"
                           placeholder="请输入类名" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">计划任务</label>
                <div class="layui-input-block">
                    <input type="text" name="jobName" lay-verify="required" autocomplete="off"
                           placeholder="请输入计划任务" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">时间表达式</label>
                <div class="layui-input-block">
                    <input type="text" name="cronExpression" lay-verify="required" autocomplete="off"
                           placeholder="请输入时间表达式" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">描述</label>
                <div class="layui-input-block">
                    <input type="text" name="descript" lay-verify="required" autocomplete="off"
                           placeholder="请输入描述" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label"></label>
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit lay-filter="add">提交申请</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
<script type="text/javascript" src="<%=path %>/static/layui/layui.js"></script>
<script type="text/javascript" src="<%=path %>/static/js/home/public.js"></script>
<script>
    layui.use(['form'], function () {

        var form = layui.form;
        var $ = layui.jquery;
        var layer = layui.layer;

        form.on('submit(add)', function (data) {
            $.post('<%=path %>/data/taskPlan/save',
                $('#taskPlan').serialize(),
                function (res) {
                    if (res.code === 0) {
                        layer.msg('添加成功', {
                            time: 1000 //2秒关闭（如果不配置，默认是3秒）
                        }, function () {
                            parent.location.reload(true);
                        });
                        layer.closeAll(index);
                    } else {
                        layer.msg(res.message);
                    }
                }, 'json'
            );
            return false;
        });
    })
</script>
</html>
