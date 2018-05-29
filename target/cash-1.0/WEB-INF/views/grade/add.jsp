<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018-5-24
  Time: 16:58
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>牌号添加</title>
</head>
<link rel="stylesheet" href="<%=path%>/static/css/public.css">
<link rel="stylesheet" href="<%=path%>/static/layui/css/layui.css">
<link rel="stylesheet" href="<%=path%>/static/css/form-public.css">
<body>
<div class="container">
    <div class="account-right">
        <h1>牌号添加</h1>
        <hr/>
        <form class="layui-form" id="grade">
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <input type="hidden" name="storeId" id="storeId" lay-verify="required" autocomplete="off" value="${store.id}"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">牌号名称</label>
                <div class="layui-input-block">
                    <input type="text" name="gradeName" id="gradeName" lay-verify="required" autocomplete="off" placeholder="请输入牌号名称"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">座位数</label>
                <div class="layui-input-block">
                    <input type="number" name="seat" id="seat" lay-verify="required|isNumber" autocomplete="off" placeholder="请输入座位数"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label"></label>
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit lay-filter="add">确认添加</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
<script type="text/javascript" src="<%=path %>/static/layui/layui.js"></script>
<script>
    layui.use(['form', 'upload'], function () {
        var form = layui.form;
        var $ = layui.jquery;
        var layer = layui.layer;

        form.verify({
            isNumber: [/^\+?[1-9][0-9]*$/, '座位必须是正整数']
        });

        form.on('submit(add)', function (data) {
            $.post('<%=path %>/data/grade/save',
                $('#grade').serialize(),
                function (res) {
                    if (res.code === 0) {
                        layer.msg('添加成功', {
                            time: 3000 //2秒关闭（如果不配置，默认是3秒）
                        }, function () {
                            location.reload(true);
                        });
                        layer.closeAll("iframe");
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
