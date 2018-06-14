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
    <title>半成品添加</title>
</head>
<link rel="stylesheet" href="<%=path%>/static/css/public.css">
<link rel="stylesheet" href="<%=path%>/static/layui/css/layui.css">
<link rel="stylesheet" href="<%=path%>/static/css/form-public.css">
<body>
<div class="container">
    <div class="account-right">
        <h1>半成品添加</h1>
        <hr/>
        <form class="layui-form" id="halfPro">
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <input type="hidden" name="storeId" lay-verify="required" autocomplete="off" value="${store.id}"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">半成品名称</label>
                <div class="layui-input-block">
                    <input type="text" name="name" lay-verify="required" autocomplete="off"
                           placeholder="请输入半成品名称" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">数量</label>
                <div class="layui-input-block">
                    <input type="text" name="count" lay-verify="required|isNumber" autocomplete="off"
                           placeholder="请输入数量" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">单位</label>
                <div class="layui-input-block">
                    <select name="unitId" id="unit" lay-verify="required"></select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">状态</label>
                <div class="layui-input-block">
                    <select name="status" id="status" lay-verify="required">
                        <option value="0">未使用</option>
                        <option value="1">已使用</option>
                        <option value="2">已报废</option>
                    </select>
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
<script type="text/javascript" src="<%=path %>/static/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/static/layui/layui.js"></script>
<script type="text/javascript" src="<%=path %>/static/js/home/public.js"></script>
<script>
    layui.use(['form', 'upload'], function () {
        var form = layui.form;
        var $ = layui.jquery;
        var layer = layui.layer;
        var unitList = "";

        form.verify({
            isNumber: [/^\d+(?=\.{0,1}\d+$|$)/, '必须是正数']
        });

        $(function () {
            if(${sessionScope.store != null}) {
                $.ajax({
                    url: '<%=path %>/data/unit/all',
                    success: function (data) {
                        var len = data.length;
                        if(len === 0) {
                            layer.open({
                                type: 1
                                ,title: false //不显示标题栏
                                ,closeBtn: false
                                ,area: '300px;'
                                ,shade: 0.8
                                ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
                                ,resize: false
                                ,btn: ['前往添加', '取消']
                                ,btnAlign: 'c'
                                ,moveType: 0 //拖拽模式，0或者1
                                ,content: '<div style="padding: 50px; line-height: 22px;' +
                                ' background-color: #393D49; color: #fff; font-weight: 300;">没有半成品单位哦，请前往添加</div>'
                                ,success: function(layero){
                                    var btn = layero.find('.layui-layer-btn');
                                    btn.find('.layui-layer-btn0').attr({
                                        href: '<%=path %>/page/unit/add'
                                    });
                                }
                            });
                        }
                        for (var i = len; i > 0 ; i--) {
                            unitList += '<option value="' + data[i-1].id + '">'
                                + data[i-1].unit + '(' + data[i-1].descript + ')</option>'
                        }
                        $("#unit").append(unitList);
                        // 重新刷新表单，新option才会出现
                        form.render();
                    }
                });
            }
        });

        form.on('submit(add)', function (data) {
            $.post('<%=path %>/data/halfPro/save',
                $('#halfPro').serialize(),
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
