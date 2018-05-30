<%--
  Created by IntelliJ IDEA.
  User: CMY
  Date: 2018/1/3
  Time: 9:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <meta charset="utf-8">
    <title>修改牌号</title>
    <link rel="stylesheet" href="<%=path %>/static/layui/css/layui.css" media="all"/>
</head>
<body>

<div class="layui-container">
    <div class="layui-row">
        <div class="layui-col-md12">
            <form id="update" class="layui-form">
                <div class="layui-form-item" style="margin-top: 20px;">
                    <label class="layui-form-label"></label>
                    <div class="layui-input-block">
                        <input type="hidden" name="id" id="id" lay-verify="required" autocomplete="off"
                               class="layui-input" readonly/>
                    </div>
                </div>

                <div class="layui-form-item" style="margin-top: 20px;">
                    <label class="layui-form-label">牌号</label>
                    <div class="layui-input-block">
                        <input type="text" name="gradeName" id="gradeName" lay-verify="required" autocomplete="off"
                               class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item" style="margin-top: 20px;">
                    <label class="layui-form-label">座位数</label>
                    <div class="layui-input-block">
                        <input type="number" name="seat" id="seat" lay-verify="required|isNumber" autocomplete="off"
                               class="layui-input">
                    </div>
                </div>

                <div class="layui-form-item" style="margin-top: 20px;">
                    <div class="layui-input-block">
                        <button class="layui-btn" lay-submit lay-filter="update">修改提交</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript" src="<%=path %>/static/layui/layui.js"></script>
<script src="<%=path %>/static/js/home/public.js"></script>
<script>
    var gradeId = GetQueryString("id");
    layui.use(['form'], function () {

        var form = layui.form;
        var $ = layui.jquery;
        var layer = layui.layer;

        $.get('<%=path %>/data/grade/one/' + gradeId,
            function (data) {
                $('#id').val(data.id);
                $('#gradeName').val(data.gradeName);
                $('#seat').val(data.seat);
            });

        form.verify({
            isNumber: [/^\+?[1-9][0-9]*$/, '座位必须是正整数']
        });

        form.on('submit(update)', function (data) {
            $.post('<%=path %>/data/grade/update',
                $('#update').serialize(),
                function (res) {
                    if (res.code === 0) {
                        layer.msg('修改成功', {
                            time: 1000 //2秒关闭（如果不配置，默认是3秒）
                        }, function () {
                            layer.closeAll();
                            location.reload(true);
                        });
                    } else {
                        layer.msg(res.message);
                    }
                }, 'json'
            );
            return false;
        });
    });
</script>
</body>
</html>
