<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018-5-24
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>商品类型更改</title>
</head>
<link rel="stylesheet" href="<%=path%>/static/css/public.css">
<link rel="stylesheet" href="<%=path%>/static/layui/css/layui.css">
<link rel="stylesheet" href="<%=path%>/static/css/form-public.css">
<body>
<div class="container">
    <div class="account-right">
        <h1>商品类型更改</h1>
        <hr/>
        <form class="layui-form" id="proType">
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <input type="hidden" id="id" name="id" lay-verify="required" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">商品类型</label>
                <div class="layui-input-block">
                    <input type="text" id="name" name="name" lay-verify="required" autocomplete="off"
                           placeholder="请输入商品类型" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label"></label>
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit lay-filter="update">提交申请</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
<script type="text/javascript" src="<%=path %>/static/layui/layui.js"></script>
<script type="text/javascript" src="<%=path %>/static/js/home/public.js"></script>
<script>
    var proTypeId = GetQueryString("id");
    layui.use(['form'], function () {

        var form = layui.form;
        var $ = layui.jquery;
        var layer = layui.layer;

        $.get('<%=path %>/data/proType/one/' + proTypeId,
            function (data) {
                $('#id').val(data.id);
                $('#name').val(data.name);
            });

        form.on('submit(update)', function (data) {
            $.post('<%=path %>/data/proType/update',
                $('#proType').serialize(),
                function (res) {
                    if (res.code === 0) {
                        layer.msg('修改成功', {
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
