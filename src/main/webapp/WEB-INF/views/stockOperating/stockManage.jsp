<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <meta charset="utf-8">
    <title>库存记录管理</title>
    <link rel="stylesheet" href="<%=path %>/static/layui/css/layui.css" media="all"/>
</head>
<body>


<div class="layui-btn-group demoTable" >
    <button class="layui-btn" data-type="refresh">刷新</button>
</div>

<table id="stock_table" lay-filter="demo"></table>

<script type="text/javascript" src="<%=path %>/static/layui/layui.js"></script>
<script type="text/javascript" src="<%=path %>/static/js/home/public.js"></script>
<script>
    layui.use('table', function(){
        var table = layui.table;
        var $ = layui.$;

        table.render({
            elem: '#stock_table'
            ,url: '<%=path %>/data/stockLog/stockList'
            ,cols: [[
                {checkbox: true, fixed: true}
                ,{field:'name', title:'库存名称', width:150}
                ,{field:'inStockCount', title:'入库数量', width:100}
                ,{field:'outStockCount', title:'出库数量', width:100}
                ,{field:'unit', title:'单位', width:100}
                ,{field:'empName', title:'操作人', width:150}
                ,{field:'descript', title:'备注', width:300}
                ,{field:'operatingTime', title:'操作时间', width:150,templet:'<div>{{ formatDateTime(d.operatingTime)}}</div>'}
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

        var active = {
            refresh:function () {
                location.reload(true);
            }
        };
    });
</script>
</body>
</html>