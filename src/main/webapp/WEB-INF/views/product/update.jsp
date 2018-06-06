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
    <title>商品修改</title>
</head>
<link rel="stylesheet" href="<%=path%>/static/css/public.css">
<link rel="stylesheet" href="<%=path%>/static/layui/css/layui.css">
<link rel="stylesheet" href="<%=path%>/static/css/form-public.css">
<body>
<div class="container">
    <div class="account-right">
        <h1>商品修改</h1>
        <hr/>
        <form class="layui-form" id="product">
            <div class="layui-form-item" style="margin-top: 20px;">
                <div class="layui-input-block">
                    <input type="hidden" name="id" id="id" lay-verify="id" autocomplete="off"
                           class="layui-input" readonly/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">商品名称</label>
                <div class="layui-input-block">
                    <input type="text" name="name" id="name" lay-verify="required" autocomplete="off"
                           placeholder="请输入商品名称" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">价格（¥）</label>
                <div class="layui-input-block">
                    <input type="number" id="price" name="price" lay-verify="required|isNumber" autocomplete="off"
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
                    <input type="text" id="priority" name="priority" lay-verify="required|isNumberPlus" maxlength="2" autocomplete="off"
                           placeholder="请输入显示优先级，数字越大商品越靠前显示,默认最高优先" class="layui-input">
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
    function loadImg(id, path) {
        if(path !== '' && path !== null) {
            $('#'+id).attr('src', '<%=path %>/' + path);
        }
    }
    layui.use(['form', 'upload'], function () {
        var form = layui.form;
        var $ = layui.jquery;
        var upload = layui.upload;
        var layer = layui.layer;

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
            var unitId = null;
            var typeId = null;
            var tasteId = null;
            $.get('<%=path %>/data/product/one/' + proId,
                function (data) {
                    $('#id').val(data.id);
                    $('#priority').val(data.priority);
                    $('#name').val(data.name);
                    $('#price').val(data.price);
                    unitId = data.unitId;
                    tasteId = data.tasteId;
                    typeId = data.typeId;
                    loadImg("proImg1Demo", data.proImg1);
                    loadImg("proImg2Demo", data.proImg2);
                    loadImg("proImg3Demo", data.proImg3);
                    loadImg("proImg4Demo", data.proImg4);
                });
            var supList = "";
            var unitList = "";
            var typeList = "";
            if(${sessionScope.store != null}) {
                $.ajax({
                    url: '<%=path %>/data/proType/all',
                    success: function (data) {
                        for (var i = 0; i < data.length; i++) {
                            if(data[i].id === typeId) {
                                typeList += '<option value="' + data[i].id + '" selected="selected">'
                                    + data[i].name + '</option>'
                            } else {
                                typeList += '<option value="' + data[i].id + '">'
                                    + data[i].name + '</option>'
                            }
                        }
                        $("#type").append(typeList);
                        // 重新刷新表单，新option才会出现
                        form.render();
                    }
                });
                $.ajax({
                    url: '<%=path %>/data/taste/all',
                    success: function (data) {
                        for (var i = 0; i < data.length; i++) {
                            if(data[i].id === tasteId) {
                                supList += '<option value="' + data[i].id + '" selected="selected">'
                                    + data[i].taste + '</option>'
                            } else {
                                supList += '<option value="' + data[i].id + '">'
                                    + data[i].taste + '</option>'
                            }
                        }
                        $("#taste").append(supList);
                        form.render();
                    }
                });
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
            $.post('<%=path %>/data/product/update',
                $('#product').serialize(),
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
