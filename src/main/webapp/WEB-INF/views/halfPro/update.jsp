<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018-5-24
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>半成品修改</title>
</head>
<link rel="stylesheet" href="<%=path%>/static/css/public.css">
<link rel="stylesheet" href="<%=path%>/static/layui/css/layui.css">
<link rel="stylesheet" href="<%=path%>/static/css/form-public.css">
<body>
<div class="container">
    <div class="account-right">
        <h1>半成品修改</h1>
        <hr/>
        <form class="layui-form" id="halfPro">
            <div class="layui-form-item" style="margin-top: 20px;">
                <div class="layui-input-block">
                    <input type="hidden" name="id" id="id" lay-verify="id" autocomplete="off"
                           class="layui-input" readonly/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">半成品名称</label>
                <div class="layui-input-block">
                    <input type="text" name="name" id="name" lay-verify="required" autocomplete="off"
                           placeholder="请输入半成品名称" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">数量</label>
                <div class="layui-input-block">
                    <input type="number" id="count" name="count" lay-verify="required|isNumber" autocomplete="off"
                           placeholder="请输入数量" maxlength="11" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">单位</label>
                <div class="layui-input-block">
                    <select name="unitId" id="unit" lay-verify="required"></select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label"></label>
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit lay-filter="add">确认修改</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
<script type="text/javascript" src="<%=path %>/static/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/static/layui/layui.js"></script>
<script type="text/javascript" src="<%=path %>/static/js/home/public.js"></script>
<script>
    var proId = GetQueryString("id");
    layui.use(['form', 'upload'], function () {
        var form = layui.form;
        var $ = layui.jquery;
        var layer = layui.layer;

        form.verify({
            isNumber: [/^\d+(?=\.{0,1}\d+$|$)/, '必须是正数']
        });

        $(function () {
            var unitId = null;
            $.get('<%=path %>/data/halfPro/one/' + proId,
                function (data) {
                    $('#id').val(data.id);
                    $('#name').val(data.name);
                    $('#count').val(data.count);
                    unitId = data.unitId;
                });
            var unitList = "";
            if(${sessionScope.store != null}) {
                $.ajax({
                    url: '<%=path %>/data/unit/all',
                    success: function (data) {
                        var len = data.length;
                        for (var i = len; i > 0 ; i--) {
                            if(data[i-1].id === unitId) {
                                unitList += '<option value="' + data[i-1].id + '" selected="selected">'
                                    + data[i-1].unit + '(' + data[i-1].descript + ')</option>'
                            } else {
                                unitList += '<option value="' + data[i-1].id + '">'
                                    + data[i-1].unit + '(' + data[i-1].descript + ')</option>'
                            }
                        }
                        $("#unit").append(unitList);
                        form.render();
                    }
                });
            }
        });

        form.on('submit(add)', function (data) {
            $.post('<%=path %>/data/halfPro/update',
                $('#halfPro').serialize(),
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
