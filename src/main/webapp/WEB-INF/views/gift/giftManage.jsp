<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <meta charset="utf-8">
    <title>套餐管理</title>
    <link rel="stylesheet" href="<%=path %>/static/layui/css/layui.css" media="all"/>
</head>
<body>


<div class="layui-btn-group demoTable" >
    <a href="javascript:void(0);" class="layui-btn" id="proShow" data-type="edit">查看套餐内商品</a>
    <button class="layui-btn" data-type="delete">删除套餐</button>
    <button class="layui-btn" data-type="refresh">刷新</button>
</div>

<table id="gift_table" lay-filter="demo"></table>

<%--套餐商品列表弹窗--%>
<div style="display: none;" id="proListShow">
    <div class="layui-btn-group proTable" >
        <button class="layui-btn" data-type="deletePro">删除套餐商品</button>
    </div>
    <table id="proList"></table>
</div>

<script type="text/javascript" src="<%=path %>/static/layui/layui.js"></script>
<script type="text/javascript" src="<%=path %>/static/js/home/public.js"></script>
<script>
    layui.use('table', function(){
        var table = layui.table;
        var $ = layui.$;

        table.render({
            elem: '#gift_table'
            ,url: '<%=path %>/data/gift/giftList'
            ,cols: [[
                {checkbox: true, fixed: true}
                ,{field:'name', title:'套餐名称', width:150}
                ,{field:'price', title:'价格', width:150}
                ,{field:'descript', title:'简介', width:300}
                ,{field:'status', title:'状态', width:100,templet:'<div>{{formatStatus(d.status)}}</div>'}
                ,{field:'createdTime', title:'添加时间', width:150,templet:'<div>{{ formatDateTime(d.createdTime)}}</div>'}
            ]]
            ,id: 'idTest'
            ,page: true
            ,height: 500
            ,response: {
                statusName: 'status'
                ,statusCode: 0
                ,msgName: 'message'
                ,countName: 'total'
                ,dataName: 'rows'
            }
        });

        $('.demoTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        $('.proTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        $('#proShow').on('click', function () {
            var checkStatus = table.checkStatus('idTest')
                ,data = checkStatus.data;
            if(data.length === 1) {
                layer.open({
                    type: 1,                //弹窗类型
                    title: '套餐商品',     //显示标题
                    closeBtn: 1,         //是否显示关闭按钮
                    shadeClose: true, //显示模态窗口
                    fixed:false,    //层是否固定在可视区域
                    offset: 't',//快捷设置顶部坐标
                    move: true,//禁止拖拽
                    area: ['890px', '560px'], //宽高
                    content: $("#proListShow")  //弹窗内容
                });
                console.log(data[0].id);
                table.render({
                    elem: '#proList'
                    ,url: '<%=path %>/data/proGift/proGiftList?giftId='+data[0].id
                    ,cols: [[
                        {checkbox: true, fixed: true}
                        ,{field:'name', title:'商品名', width:90}
                        ,{field:'price', title:'商品原价', width:150}
                        ,{field:'count', title:'商品数量', width:150}
                    ]]
                    ,id: 'proId'
                    ,page: true
                    ,height: 500
                    ,response: {
                        statusName: 'status'
                        ,statusCode: 0
                        ,msgName: 'message'
                        ,countName: 'total'
                        ,dataName: 'rows'
                    }
                });
            } else {
                layer.msg('请选中一行！', {time:1500});
            }
        });

        var active = {
            delete:function () {
                var checkStatus = table.checkStatus('idTest')
                    ,data = checkStatus.data;
                if(data.length === 1) {
                    $.get('<%=path %>/data/gift/remove/' + data[0].id,
                        function (data) {
                            if(data.code===0){
                                layer.msg("删除成功！");
                                location.reload(true);
                            }else {
                                layer.msg("删除失败！");
                            }
                        });
                } else {
                    layer.msg('请选中一行！', {time:1500});
                }
            }
            ,deletePro:function () {
                var checkStatus = table.checkStatus('proId')
                    ,data = checkStatus.data;
                if(data.length === 1) {
                    $.get('<%=path %>/data/proGift/remove/' + data[0].id,
                        function (data) {
                            if(data.code===0){
                                layer.msg("删除成功！");
                                location.reload(true);
                            }else {
                                layer.msg("删除失败！");
                            }
                        });
                } else {
                    layer.msg('请选中一行！', {time:1500});
                }
            }, refresh:function () {
                location.reload(true);
            }
        };
    });
</script>
</body>
</html>