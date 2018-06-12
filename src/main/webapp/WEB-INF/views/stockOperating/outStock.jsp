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
    <title>物品领用</title>
</head>
<link rel="stylesheet" href="<%=path %>/static/css/public.css">
<link rel="stylesheet" href="<%=path %>/static/layui/css/layui.css">
<link rel="stylesheet" href="<%=path %>/static/css/form-public.css">
<body>
<div class="container">
    <div class="account-right">
        <h1>物品领用</h1>
        <hr/>
        <form class="layui-form" id="outStock">
            <div id="appenderDiv">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">原料</label>
                        <div class="layui-input-block">
                            <select name="rawId" id="rawId1" lay-verify="required"></select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">数量</label>
                        <div class="layui-input-block">
                            <input type="number" id="outStockCount1" name="count" lay-verify="required|isNumber" autocomplete="off"
                                   placeholder="请输入数量" maxlength="11" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">单位</label>
                        <div class="layui-input-block">
                            <select name="unitId" id="unitId1" lay-verify="required"></select>
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">领用人</label>
                <div class="layui-input-block">
                    <input type="text" id="empName" name="empName" lay-verify="required" autocomplete="off"
                           placeholder="请输入领用人" maxlength="11" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">领用说明</label>
                <div class="layui-input-block">
                    <textarea id="descript1" name="descript" class="layui-textarea"
                              placeholder="请输入领用说明">营业原料领用</textarea>
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-input-block">
                    <input type="button" class="layui-btn" id="appender" value="添加领用原料">
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
        var clickCount = 1;
        var rawCount = null;
        var rawList = "";
        var unitList = "";

        function appenderDiv(clickCount) {
            return "<div class=\"layui-form-item\">\n" +
                "                    <div class=\"layui-inline\">\n" +
                "                        <label class=\"layui-form-label\">原料</label>\n" +
                "                        <div class=\"layui-input-block\">\n" +
                "                            <select name=\"rawId\" class=\"raw\" id='rawId"+ clickCount +"' lay-verify=\"required\"></select>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <div class=\"layui-inline\">\n" +
                "                        <label class=\"layui-form-label\">数量</label>\n" +
                "                        <div class=\"layui-input-block\">\n" +
                "                            <input type=\"number\" id='outStockCount"+ clickCount +"' name=\"count\" lay-verify=\"required|isNumber\" autocomplete=\"off\"\n" +
                "                                   placeholder=\"请输入数量\" maxlength=\"11\" class=\"layui-input\">\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <div class=\"layui-inline\">\n" +
                "                        <label class=\"layui-form-label\">单位</label>\n" +
                "                        <div class=\"layui-input-block\">\n" +
                "                            <select name=\"unitId\" id='unitId"+ clickCount +"' lay-verify=\"required\"></select>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                </div>";
        }

        form.verify({
            isNumber: [/^\d+(?=\.{0,1}\d+$|$)/, '必须是正数']
        });
        form.verify({
            isNumberPlus: [/^\+?[1-9][0-9]*$/, '必须是正整数']
        });
        $(function () {
            if(${sessionScope.store != null}) {
                $.ajax({
                    url: '<%=path %>/data/rawMat/allLess',
                    success: function (data) {
                        rawCount = data.length;
                        if(rawCount === 0) {
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
                        for (var i = rawCount; i > 0 ; i--) {
                            rawList += '<option value="' + data[i-1].id + '">'
                                + data[i-1].name + '(' + data[i-1].unit + ')</option>'
                        }
                        $("#rawId1").append(rawList);
                        // 重新刷新表单，新option才会出现
                        form.render();
                    }
                });
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
                                ' background-color: #393D49; color: #fff; font-weight: 300;">没有单位哦，请前往添加</div>'
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
                        $("#unitId1").append(unitList);
                        // 重新刷新表单，新option才会出现
                        form.render();
                    }
                });
            }
        });

        $(document).on('click','#appender',function(){
            if(clickCount < rawCount) {
                $('#appenderDiv').append(appenderDiv(++clickCount));
                $("#rawId"+ clickCount).append(rawList);
                $("#unitId"+ clickCount).append(unitList);
                form.render();
            } else {
                layer.msg('最多领用'+ rawCount +'种原料');
            }
        });

        form.on('submit(add)', function (data) {
            var ids = "";
            for(var i =1; i <= clickCount; i++) {
                var a = $("#rawId" + i).val();
                var b = $("#outStockCount" + i).val();
                var c = $("#unitId" + i).val();
                ids += a + '-' + b + '-' + c + ',';
            }
            $.post('<%=path %>/data/stockLog/save?ids='+ids,
                $('#outStock').serialize(),
                function (res) {
                    if (res.code === 0) {
                        layer.msg('添加成功', {
                            time: 1000 //2秒关闭（如果不配置，默认是3秒）
                        }, function () {
                            location.reload(true);
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
