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
    <title>报损记录添加</title>
</head>
<link rel="stylesheet" href="<%=path%>/static/css/public.css">
<link rel="stylesheet" href="<%=path%>/static/layui/css/layui.css">
<link rel="stylesheet" href="<%=path%>/static/css/form-public.css">
<body>
<div class="container">
    <div class="account-right">
        <h1>报损记录添加</h1>
        <hr/>
        <form class="layui-form" id="lose">
            <div class="layui-form-item">
                <label class="layui-form-label">报损类别</label>
                <div class="layui-input-block">
                    <select name="type" id="type" class="type" lay-verify="required" lay-filter="type">
                        <option value="raw">原料报损</option>
                        <option value="pro">商品报损</option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item" id="rawDiv">
                <label class="layui-form-label">原料</label>
                <div class="layui-input-block">
                    <select name="rawId" id="raw" class="raw" lay-verify="required"></select>
                </div>
            </div>
            <div class="layui-form-item" id="proDiv">
                <label class="layui-form-label">商品</label>
                <div class="layui-input-block">
                    <select name="proId" id="pro" class="product" lay-verify="required"></select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">数量</label>
                <div class="layui-input-block">
                    <input type="number" id="count2" name="count" lay-verify="required|isNumberPlus" autocomplete="off"
                           placeholder="请输入商品数量" maxlength="11" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">原因</label>
                <div class="layui-input-block">
                    <textarea name="reason" class="layui-textarea" lay-verify="required"
                              placeholder="请输入原因"></textarea>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label"></label>
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit lay-filter="add">确认提交</button>
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

        $(function () {
            typeChoose($('#type').val());
        });
        form.on('select(type)', function (data) {
            typeChoose($('#type').val());
        });
        function typeChoose(type) {
            if (type === 'raw') {
                $("#proDiv").hide();
                $("#rawDiv").show();
                loadRaw();
            } else {
                $("#rawDiv").hide();
                $("#proDiv").show();
                loadPro();
            }
        }

        function loadPro() {
            var supList = "<option value>请选择一项</option>";
            $.ajax({
                url: '<%=path %>/data/product/all',
                success: function (data) {
                    if(data.length === 0) {
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
                            ' background-color: #393D49; color: #fff; font-weight: 300;">没有商品哦，请前往添加</div>'
                            ,success: function(layero){
                                var btn = layero.find('.layui-layer-btn');
                                btn.find('.layui-layer-btn0').attr({
                                    href: '<%=path %>/page/product/add'
                                });
                            }
                        });
                    }
                    for (var i = 0; i < data.length; i++) {
                        supList += '<option value="' + data[i].id + '">'
                            + data[i].name + '(' + data[i].price + '元)</option>'
                    }
                    $("#pro").empty();
                    $("#pro").append(supList);
                    // 重新刷新表单，新option才会出现
                    form.render();
                }
            });
        }
        function loadRaw() {
            var rawList = "<option value>请选择一项</option>";
            $.ajax({
                url: '<%=path %>/data/rawMat/allLess',
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
                            ' background-color: #393D49; color: #fff; font-weight: 300;">没有商品原材料哦，请前往添加</div>'
                            ,success: function(layero){
                                var btn = layero.find('.layui-layer-btn');
                                btn.find('.layui-layer-btn0').attr({
                                    href: '<%=path %>/page/rawMat/add'
                                });
                            }
                        });
                    }
                    for (var i = len; i > 0 ; i--) {
                        rawList += '<option value="' + data[i-1].id + '">'
                            + data[i-1].name + '(' + data[i-1].unit + ')</option>'
                    }
                    $("#raw").empty();
                    $("#raw").append(rawList);
                    // 重新刷新表单，新option才会出现
                    form.render();
                }
            });
        }

        form.on('submit(add)', function (data) {
            $.post('<%=path %>/data/lose/save',
                $('#lose').serialize(),
                function (res) {
                    if (res.code === 0) {
                        layer.msg('添加成功', {
                            time: 1000
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
