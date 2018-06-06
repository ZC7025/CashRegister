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
    <title>商品添加</title>
</head>
<link rel="stylesheet" href="<%=path%>/static/css/public.css">
<link rel="stylesheet" href="<%=path%>/static/layui/css/layui.css">
<link rel="stylesheet" href="<%=path%>/static/css/form-public.css">
<body>
<div class="container">
    <div class="account-right">
        <h1>商品添加</h1>
        <hr/>
        <form class="layui-form" id="product">
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <input type="hidden" name="storeId" lay-verify="required" autocomplete="off" value="${store.id}"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">商品名称</label>
                <div class="layui-input-block">
                    <input type="text" name="name" lay-verify="required" autocomplete="off"
                           placeholder="请输入商品名称" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">价格（¥）</label>
                <div class="layui-input-block">
                    <input type="number" name="price" lay-verify="required|isNumber" autocomplete="off"
                           placeholder="请输入商品价格" maxlength="11" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">单位</label>
                <div class="layui-input-block">
                    <select name="unitId" id="unit" lay-verify="required"></select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">商品类型</label>
                <div class="layui-input-block">
                    <select name="typeId" id="type" lay-verify="required"></select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">口味</label>
                <div class="layui-input-block">
                    <select name="tasteId" id="taste" lay-verify="required"></select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">显示优先级</label>
                <div class="layui-input-block">
                    <input type="text" id="priority" name="priority" lay-verify="isNumberPlus" maxlength="2" autocomplete="off"
                           placeholder="请输入显示优先级，数字越大商品越靠前显示,默认最高优先" value="99" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-row">
                    <label class="layui-form-label">商品图片</label>
                    <div class="layui-col-md2">
                        <div class="layui-upload">
                            <button type="button" class="layui-btn" id="proImg1">图片1</button>
                            <div class="layui-upload-list">
                                <img class="layui-upload-img" id="proImg1Demo" style="width:120px;height:120px">
                                <p id="proImg1Text"></p>
                            </div>
                        </div>
                        <input type="hidden" name="proImg1" id="proImg1Img"/>
                    </div>
                    <div class="layui-col-md2">
                        <div class="layui-upload">
                            <button type="button" class="layui-btn" id="proImg2">图片2</button>
                            <div class="layui-upload-list">
                                <img class="layui-upload-img" id="proImg2Demo" style="width:120px;height:120px">
                                <p id="proImg2Text"></p>
                            </div>
                        </div>
                        <input type="hidden" name="proImg2" id="proImg2Img"/>
                    </div>
                    <div class="layui-col-md2">
                        <div class="layui-upload">
                            <button type="button" class="layui-btn" id="proImg3">图片3</button>
                            <div class="layui-upload-list">
                                <img class="layui-upload-img" id="proImg3Demo" style="width:120px;height:120px">
                                <p id="proImg3Text"></p>
                            </div>
                        </div>
                        <input type="hidden" name="proImg3" id="proImg3Img"/>
                    </div>
                    <div class="layui-col-md2">
                        <div class="layui-upload">
                            <button type="button" class="layui-btn" id="proImg4">图片4</button>
                            <div class="layui-upload-list">
                                <img class="layui-upload-img" id="proImg4Demo" style="width:120px;height:120px">
                                <p id="proImg4Text"></p>
                            </div>
                        </div>
                        <input type="hidden" name="proImg4" id="proImg4Img"/>
                    </div>
                </div>
            </div>
            <div id="proAppender">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">原料</label>
                        <div class="layui-input-block">
                            <select name="rawId" class="raw" id="raw1" lay-verify="required"></select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">数量</label>
                        <div class="layui-input-block">
                            <input type="number" id="count1" name="count" lay-verify="required|isNumber" autocomplete="off"
                                   placeholder="请输入数量" maxlength="11" class="layui-input">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">配方简介</label>
                    <div class="layui-input-block">
                    <textarea id="descript1" name="descript" class="layui-textarea"
                              placeholder="请输入配方简介"></textarea>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">原料</label>
                        <div class="layui-input-block">
                            <select name="rawId" class="raw" id="raw2" lay-verify="required"></select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">数量</label>
                        <div class="layui-input-block">
                            <input type="number" id="count2" name="count" lay-verify="required|isNumber" autocomplete="off"
                                   placeholder="请输入数量" maxlength="11" class="layui-input">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">配方简介</label>
                    <div class="layui-input-block">
                    <textarea id="descript2" name="descript" class="layui-textarea"
                              placeholder="请输入配方简介"></textarea>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <input type="button" class="layui-btn" id="appender" value="添加商品原料">
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
    function appenderDiv(clickCount) {
        return "<div class=\"layui-form-item\">\n" +
            "                    <div class=\"layui-inline\">\n" +
            "                        <label class=\"layui-form-label\">原料</label>\n" +
            "                        <div class=\"layui-input-block\">\n" +
            "                            <select name=\"rawId\" class=\"raw\" id='raw"+ clickCount +"' lay-verify=\"required\"></select>\n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                    <div class=\"layui-inline\">\n" +
            "                        <label class=\"layui-form-label\">数量</label>\n" +
            "                        <div class=\"layui-input-block\">\n" +
            "                            <input type=\"number\" id='count"+ clickCount + "' name=\"count\" lay-verify=\"required|isNumber\" autocomplete=\"off\"\n" +
            "                                   placeholder=\"请输入数量\" maxlength=\"11\" class=\"layui-input\">\n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "                <div class=\"layui-form-item\">\n" +
            "                    <label class=\"layui-form-label\">配方简介</label>\n" +
            "                    <div class=\"layui-input-block\">\n" +
            "                    <textarea id='descript"+ clickCount + "' name=\"descript\" class=\"layui-textarea\"\n" +
            "                              placeholder=\"请输入配方简介\"></textarea>\n" +
            "                    </div>\n" +
            "                </div>";
    }
    layui.use(['form', 'upload'], function () {
        var form = layui.form;
        var $ = layui.jquery;
        var upload = layui.upload;
        var layer = layui.layer;
        var clickCount = 2;
        var supList = "";
        var unitList = "";
        var typeList = "";
        var rawList = "";

        uploadImg(upload, 'proImg1', '<%=path %>/file/firist', 'proImg1Demo', 'proImg1Img', 'proImg1Text');
        uploadImg(upload, 'proImg2', '<%=path %>/file/firist', 'proImg2Demo', 'proImg2Img', 'proImg2Text');
        uploadImg(upload, 'proImg3', '<%=path %>/file/firist', 'proImg3Demo', 'proImg3Img', 'proImg3Text');
        uploadImg(upload, 'proImg4', '<%=path %>/file/firist', 'proImg4Demo', 'proImg4Img', 'proImg4Text');

        form.verify({
            isNumber: [/^\d+(?=\.{0,1}\d+$|$)/, '必须是正数']
        });
        form.verify({
            isNumberPlus: [/^\+?[1-9][0-9]*$/, '必须是正整数']
        });

        $(function () {
            if(${sessionScope.store != null}) {
                $.ajax({
                    url: '<%=path %>/data/proType/all',
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
                                ' background-color: #393D49; color: #fff; font-weight: 300;">没有商品类型哦，请前往添加</div>'
                                ,success: function(layero){
                                    var btn = layero.find('.layui-layer-btn');
                                    btn.find('.layui-layer-btn0').attr({
                                        href: '<%=path %>/page/proType/add'
                                    });
                                }
                            });
                        }
                        for (var i = 0; i < data.length; i++) {
                            typeList += '<option value="' + data[i].id + '">'
                                + data[i].name + '</option>'
                        }
                        $("#type").append(typeList);
                        // 重新刷新表单，新option才会出现
                        form.render();
                    }
                });
                $.ajax({
                    url: '<%=path %>/data/taste/all',
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
                                ' background-color: #393D49; color: #fff; font-weight: 300;">没有商品口味哦，请前往添加</div>'
                                ,success: function(layero){
                                    var btn = layero.find('.layui-layer-btn');
                                    btn.find('.layui-layer-btn0').attr({
                                        href: '<%=path %>/page/taste/add'
                                    });
                                }
                            });
                        }
                        for (var i = 0; i < data.length; i++) {
                            supList += '<option value="' + data[i].id + '">'
                                + data[i].taste + '</option>'
                        }
                        $("#taste").append(supList);
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
                                ' background-color: #393D49; color: #fff; font-weight: 300;">没有商品单位哦，请前往添加</div>'
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
                        $("#raw1").append(rawList);
                        $("#raw2").append(rawList);
                        // 重新刷新表单，新option才会出现
                        form.render();
                    }
                });
            }
        });
        $(document).on('click','#appender',function(){
            if(clickCount < 20) {
                $('#proAppender').append(appenderDiv(++clickCount));
                $("#raw"+ clickCount).append(rawList);
                form.render();
            } else {
                layer.msg('商品最多添加20种配方原料');
            }
        });

        form.on('submit(add)', function (data) {
            var ids = "";
            for(var i =1; i <= clickCount; i++) {
                var a = $("#raw" + i).val();
                var b = $("#count" + i).val();
                var c = $("#descript" + i).val();
                ids += a + '-' + b + '-' + c + ',';
            }
            $.post('<%=path %>/data/product/save?formulaIds='+ids,
                $('#product').serialize(),
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
