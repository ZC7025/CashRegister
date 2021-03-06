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
    <title>供应商更改</title>
</head>
<link rel="stylesheet" href="<%=path%>/static/css/public.css">
<link rel="stylesheet" href="<%=path%>/static/layui/css/layui.css">
<link rel="stylesheet" href="<%=path%>/static/css/form-public.css">
<body>
<div class="container">
    <div class="account-right">
        <h1>供应商更改</h1>
        <hr/>
        <form class="layui-form" id="supplier">
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <input type="hidden" id="id" name="id" lay-verify="required" autocomplete="off"
                           class="layui-input">
                    <input type="hidden" name="storeId" lay-verify="required" autocomplete="off" value="${store.id}"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">供应商姓名</label>
                <div class="layui-input-block">
                    <input type="text" id="name" name="name" lay-verify="required" autocomplete="off"
                           placeholder="请输入供应商姓名" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">手机号码</label>
                <div class="layui-input-block">
                    <input type="text" id="phone" name="phone" lay-verify="required" autocomplete="off"
                           placeholder="请输入手机号码" maxlength="11" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">地址</label>
                <div class="layui-input-block">
                    <input type="text" id="address" name="address" lay-verify="required" autocomplete="off"
                           placeholder="请输入地址" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">默认供应商</label>
                <div class="layui-input-block" id="redio"></div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label"></label>
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit lay-filter="update">确认更改</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
<script type="text/javascript" src="<%=path %>/static/layui/layui.js"></script>
<script type="text/javascript" src="<%=path %>/static/js/home/public.js"></script>
<script>
    var empId = GetQueryString("id");
    layui.use(['form'], function () {

        var form = layui.form;
        var $ = layui.jquery;
        var layer = layui.layer;

        $.get('<%=path %>/data/supplier/one/' + empId,
            function (data) {
                $('#id').val(data.id);
                $('#name').val(data.name);
                $('#phone').val(data.phone);
                $('#address').val(data.address);
                var html = "";
                if(data.defaults === 'N') {
                    html = "<input type='radio' name='defaults' value='Y' title='是'>" +
                        "<input type='radio' name='defaults' value='N' title='否' checked>";
                } else {
                    html = "<input type='radio' name='defaults' value='Y' title='是' checked>" +
                        "<input type='radio' name='defaults' value='N' title='否'>";
                }
                $('#redio').append(html);
                form.render();
            });

        form.on('submit(update)', function (data) {
            $.post('<%=path %>/data/supplier/update',
                $('#supplier').serialize(),
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
