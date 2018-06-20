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
    <title>订单添加</title>
</head>
<link rel="stylesheet" href="<%=path%>/static/css/public.css">
<link rel="stylesheet" href="<%=path%>/static/layui/css/layui.css">
<link rel="stylesheet" href="<%=path%>/static/css/form-public.css">
<body>
<div class="container">
    <div class="account-right">
        <h1>订单添加</h1>
        <hr/>
        <form class="layui-form" id="storeOrder">
            <div class="layui-form-item">
                <label class="layui-form-label">客人数</label>
                <div class="layui-input-block">
                    <input type="number" name="peopleCount" lay-verify="required|isNumberPlus" autocomplete="off" placeholder="请输入客人数"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">桌号</label>
                <div class="layui-input-block">
                    <select name="gradeId" id="gradeId" lay-verify="required"></select>
                </div>
            </div>
            <%-- 订单详情选择--%>
            <div id="proAppender">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">商品</label>
                        <div class="layui-input-block">
                            <select name="proId" class="product" id="product1" lay-verify="required" lay-filter="pro"></select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">数量</label>
                        <div class="layui-input-block">
                            <input type="number" id="count1" name="count" lay-verify="required|isNumberPlus" autocomplete="off"
                                   placeholder="请输入商品数量" maxlength="11" class="layui-input count" value="1">
                        </div>
                    </div>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">总金额</label>
                <div class="layui-input-block">
                    <input type="number" readonly name="totalMoney" id="totalMoney" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <input type="button" class="layui-btn" id="appender" value="添加订单商品">
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
    function appenderDiv(clickCount) {
        return "<div class=\"layui-form-item\">\n" +
            "                    <div class=\"layui-inline\">\n" +
            "                        <label class=\"layui-form-label\">商品</label>\n" +
            "                        <div class=\"layui-input-block\">\n" +
            "                            <select name='proId' class='product' id='product"+ clickCount +"' lay-verify=\"required\" lay-filter=\"pro\"></select>\n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                    <div class=\"layui-inline\">\n" +
            "                        <label class=\"layui-form-label\">数量</label>\n" +
            "                        <div class=\"layui-input-block\">\n" +
            "                            <input type=\"number\" id='count"+ clickCount +"' name=\"count\" lay-verify=\"required|isNumberPlus\" autocomplete=\"off\"\n" +
            "                                   placeholder=\"请输入商品数量\" maxlength=\"11\" class=\"layui-input count\" value=\"1\">\n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                </div>";
    }

    layui.use(['form'], function () {
        var form = layui.form;
        var $ = layui.jquery;
        var layer = layui.layer;
        var supList = "";
        var clickCount=1;
        var dataLen;
        $(function () {
            var html = "";
            $.ajax({
                url: '<%=path %>/data/grade/all',
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
                            ' background-color: #393D49; color: #fff; font-weight: 300;">没有桌牌号哦，请前往添加</div>'
                            ,success: function(layero){
                                var btn = layero.find('.layui-layer-btn');
                                btn.find('.layui-layer-btn0').attr({
                                    href: '<%=path %>/page/grade/add'
                                });
                            }
                        });
                    }
                    //加载数据
                    for (var i = 0; i < data.length; i++) {
                        html += '<option value="' + data[i].id + '">' + data[i].gradeName + '(' + data[i].seat + '人)</option>'
                    }
                    $("#gradeId").append(html);
                    // 重新刷新表单，新option才会出现
                    form.render();
                }
            });

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
                    dataLen = data.length;
                    for (var i = 0; i < dataLen; i++) {
                        supList += '<option value="' + data[i].id + '-' + data[i].price + '">'
                            + data[i].name + '(' + data[i].price + '元)</option>'
                    }
                    $("#product1").append(supList);
                    // 重新刷新表单，新option才会出现
                    form.render();
                }
            });
        });

        form.verify({
            isNumberPlus: [/^\+?[1-9][0-9]*$/, '必须是正整数']
        });

        $(document).on('click','#appender',function(){
            if(clickCount < dataLen) {
                $('#proAppender').append(appenderDiv(++clickCount));
                $("#product"+ clickCount).append(supList);
                form.render();
            } else {
                layer.msg('套餐最多添加' + dataLen + '种商品');
            }
        });

        // 输入商品数量后的焦点失去事件
        $(document).on('blur','.count',function(){
            calcMoney();
        });
        // 选择完商品后的事件
        form.on('select(pro)', function(data){
            calcMoney();
        });
        // 计算订单总金额
        function calcMoney() {
            var totalMoney=0;
            for(var i =1; i <= clickCount; i++) {
                // a=id-price
                var a = $("#product" + i).val();
                var prices = a.split("-");
                var count = $("#count" + i).val();
                totalMoney += parseInt(prices[1]) * count;
            }
            $("#totalMoney").val(totalMoney);
        }

        form.on('submit(add)', function (data) {
            var ids = "";
            for(var i =1; i <= clickCount; i++) {
                // a=id-price
                var a = $("#product" + i).val();
                var b = $("#count" + i).val();
                ids += a + '-' + b + ',';
            }
            $.post('<%=path %>/data/storeOrder/save?details='+ids,
                $('#storeOrder').serialize(),
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
