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
    <title>原材料添加</title>
</head>
<link rel="stylesheet" href="<%=path%>/static/css/public.css">
<link rel="stylesheet" href="<%=path%>/static/layui/css/layui.css">
<link rel="stylesheet" href="<%=path%>/static/css/form-public.css">
<body>
<div class="container">
    <div class="account-right">
        <h1>原材料添加</h1>
        <hr/>
        <form class="layui-form" id="rawMat">
            <div class="layui-form-item">
                <label class="layui-form-label">原材料名称</label>
                <div class="layui-input-block">
                    <input type="text" name="name" lay-verify="required" autocomplete="off"
                           placeholder="请输入原材料名称" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">进价（¥）</label>
                <div class="layui-input-block">
                    <input type="number" name="price" lay-verify="required|isNumber" autocomplete="off"
                           placeholder="请输入原材料进价" maxlength="11" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">数量</label>
                <div class="layui-input-block">
                    <input type="number" id="amount" name="amount" lay-verify="required|isNumber|checked" autocomplete="off"
                           placeholder="请输入原材料数量" maxlength="11" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">单位</label>
                <div class="layui-input-block">
                    <select name="unitId" id="unit" lay-verify="required"></select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">生产日期</label>
                <div class="layui-input-inline">
                    <input type="text" name="birthTime" class="layui-input" id="birthTime" placeholder="请选择生产日期">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">保质期</label>
                <div class="layui-input-inline">
                    <input type="text" name="shelf" id="shelf" lay-verify="required|isNumberPlus" class="layui-input" placeholder="请输入保质期">
                    <input type="hidden" id="shelfTime" name="shelfTime" lay-verify="required" class="layui-input">
                </div>
                <div class="layui-input-inline">
                    <select name="timeUnit" id="timeUnit" lay-verify="required" lay-filter="level">
                        <option value="天">天</option>
                        <option value="月">月</option>
                        <option value="年">年</option>
                        <option value="时">小时</option>
                        <option value="分">分钟</option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">过期时间</label>
                <div class="layui-input-inline">
                    <input type="text" name="deadTime" class="layui-input" id="deadTime" readonly>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">最小库存</label>
                <div class="layui-input-block">
                    <input type="text" id="minStock" name="minStock" lay-verify="required|isNumber|checked" autocomplete="off"
                           placeholder="请输入最小库存" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">最大库存</label>
                <div class="layui-input-block">
                    <input type="text" id="maxStock" name="maxStock" lay-verify="required|isNumber|checked" autocomplete="off"
                           placeholder="请输入最大库存" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">供应商</label>
                <div class="layui-input-block">
                    <select name="supplierId" id="sup-select" lay-filter="required">
                        <option value="">请选择供应商</option>
                    </select>
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
    var nowDate = new Date();
    layui.use(['form', 'laydate'], function () {
        var form = layui.form;
        var $ = layui.jquery;
        var layer = layui.layer;
        var laydate = layui.laydate;
        var dateStr = nowDate.getFullYear() + '-' + (nowDate.getMonth()+1) + '-' + nowDate.getDate()
            + ' '+ nowDate.getHours() + ':'+nowDate.getMinutes()+':'+nowDate.getSeconds();
        laydate.render({
            elem: '#birthTime'
            ,value: dateStr
            ,type: 'datetime'
            , max: dateStr
            ,done: function(value){
                timeCalc(value);
            }
        });
        // 输入保质期后的失去焦点事件
        $(document).on('blur','#shelf',function(){
            timeCalc($('#birthTime').val());
        });
        form.on('select(level)', function(data){
            timeCalc($('#birthTime').val());
        });
        //计算过期时间
        function timeCalc(birth) {
            var shelf = $('#shelf').val();
            var timeUnit = $('#timeUnit').val();
            if(shelf !== null && shelf !== '') {
                $('#shelfTime').val(shelf + timeUnit);
                $('#deadTime').val(formatDate(DateAdd(timeUnit, parseInt(shelf), new Date(birth))));
            } else {
                layer.msg('请输入保质期');
            }
        }

        form.verify({
            isNumber: [/^\d+(?=\.{0,1}\d+$|$)/, '必须是正数']
        });
        form.verify({
            isNumberPlus: [/^\+?[1-9][0-9]*$/, '必须是正整数']
        });
        form.verify({
            checked: function(){
                var min = $('#minStock').val();
                var max = $('#maxStock').val();
                var amount = $('#amount').val();
                if(amount < min || max < min) {
                    return '不能小于最小库存';
                }
                if(min > max || amount > max){
                    return '不能超过最大库存';
                }
            }
        });
        $(function () {
            var supList = "";
            var unitList = "";
            if(${sessionScope.store != null}) {
                $.ajax({
                    url: '<%=path %>/data/supplier/all',
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
                                ' background-color: #393D49; color: #fff; font-weight: 300;">没有供应商哦，请前往添加</div>'
                                ,success: function(layero){
                                    var btn = layero.find('.layui-layer-btn');
                                    btn.find('.layui-layer-btn0').attr({
                                        href: '<%=path %>/page/supplier/add'
                                    });
                                }
                            });
                        }
                        for (var i = 0; i < data.length; i++) {
                            if(data[i].defaults === 'Y') {
                                supList += '<option value="' + data[i].id + '" selected="selected">'
                                    + data[i].name + '(' + data[i].phone + ')</option>'
                            } else {
                                supList += '<option value="' + data[i].id + '">'
                                    + data[i].name + '(' + data[i].phone + ')</option>'
                            }
                        }
                        $("#sup-select").append(supList);
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
                        $("#unit").append(unitList);
                        // 重新刷新表单，新option才会出现
                        form.render();
                    }
                });
            }
        });

        form.on('submit(add)', function (data) {
            $.post('<%=path %>/data/rawMat/save',
                $('#rawMat').serialize(),
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
