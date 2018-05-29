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
    <title>修改密码</title>
</head>
<link rel="stylesheet" href="<%=path%>/static/css/public.css">
<link rel="stylesheet" href="<%=path%>/static/layui/css/layui.css">
<link rel="stylesheet" href="<%=path%>/static/css/form-public.css">
<body>
<div class="container">
    <div class="account-right">
        <h1>修改密码</h1>
        <hr/>
        <form class="layui-form" id="updatePwd">
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <input type="hidden" name="phone" id="phone" lay-verify="required" autocomplete="off" value="${store.phone}"
                           class="layui-input">
                    <input type="hidden" name="id" id="id" lay-verify="required" autocomplete="off" value="${store.id}"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">原密码</label>
                <div class="layui-input-block">
                    <input type="password" name="pwd" id="pwd" lay-verify="required|pass" autocomplete="off" placeholder="请输入原密码"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">新密码</label>
                <div class="layui-input-block">
                    <input type="password" name="newPwd" id="newPwd" lay-verify="required|pass" autocomplete="off" placeholder="请输入新密码"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">确认密码</label>
                <div class="layui-input-block">
                    <input type="password" name="repwd" id="repwd" lay-verify="required|repass" autocomplete="off" placeholder="请输入确认密码"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label"></label>
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit lay-filter="updatePwd">确认修改</button>
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
            pass: [/(.+){6,12}$/, '密码必须6到12位']
        });

        form.verify({
            repass: function(value){
                var newPwd = $('#newPwd').val();
                var pwd = $('#pwd').val();
                if(value !== newPwd){
                    return '两次输入的密码不一致!';
                }
                if(pwd === newPwd) {
                    return '不能与原密码输入一致';
                }
            }
        });
        form.on('submit(updatePwd)', function (data) {
            $.post('<%=path %>/data/store/updatePwd',
                $('#updatePwd').serialize(),
                function (res) {
                    if (res.code === 0) {
                        layer.msg('修改成功,请重新登录', {
                            time: 3000 //2秒关闭（如果不配置，默认是3秒）
                        }, function () {
                            window.location="/";
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
