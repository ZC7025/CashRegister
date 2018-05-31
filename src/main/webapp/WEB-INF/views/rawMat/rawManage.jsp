<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <meta charset="utf-8">
    <title>原材料管理</title>
    <link rel="stylesheet" href="<%=path %>/static/layui/css/layui.css" media="all"/>
</head>
<body>


<div class="layui-btn-group demoTable" >
    <button class="layui-btn" data-type="add">新增原材料</button>
    <button class="layui-btn" data-type="update">修改原材料信息</button>
    <button class="layui-btn" data-type="delete">删除原材料</button>
    <button class="layui-btn" data-type="refresh">刷新</button>
</div>

<table id="rawMat_table" lay-filter="demo"></table>

<script type="text/javascript" src="<%=path %>/static/layui/layui.js"></script>
<script type="text/javascript" src="<%=path %>/static/js/home/public.js"></script>
<script>
    layui.use('table', function(){
        var table = layui.table;
        var $ = layui.$;

        table.render({
            elem: '#rawMat_table'
            ,url: '<%=path %>/data/rawMat/rawList'
            ,cols: [[
                {checkbox: true, fixed: true}
                ,{field:'name', title:'原材料名称', width:150}
                ,{field:'price', title:'原材料价格(¥)', width:150}
                ,{field:'amount', title:'原材料数量', width:150}
                ,{field:'unit', title:'单位', width:150}
                ,{field:'supplierName', title:'供应商名称', width:150}
                ,{field:'phone', title:'供应商电话', width:150}
                ,{field:'birthTime', title:'生产日期', width:150,templet:'<div>{{ formatDateTime(d.birthTime)}}</div>'}
                ,{field:'shelfTime', title:'保质期', width:150}
                ,{field:'deadTime', title:'过期时间', width:150,templet:'<div>{{ formatDateTime(d.deadTime)}}</div>'}
                ,{field:'minStock', title:'最小库存', width:150}
                ,{field:'maxStock', title:'最大库存', width:150}
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

        var active = {
            delete:function () {
                var checkStatus = table.checkStatus('idTest')
                    ,data = checkStatus.data;
                if(data.length === 1) {
                    $.get('<%=path %>/data/rawMat/remove/' + data[0].id,
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
            ,add: function(){ //行业类型添加
                layer.open({
                    type: 2,
                    title: '添加原材料',
                    area: ['80%', '80%'],
                    maxmin:true,
                    content: '<%=path %>/page/rawMat/add'
                });
            }
            ,update: function(){ //验证是否全选
                var checkStatus = table.checkStatus('idTest')
                    ,data = checkStatus.data;
                if(data.length === 1) {
                    layer.open({
                        type: 2,
                        area: ['80%', '80%'],
                        maxmin:true,
                        content:"<%=path %>/page/rawMat/update?id="+data[0].id
                    })
                } else {
                    layer.msg("请选择一行！");
                }

            },
            refresh:function () {
                location.reload(true);
            }
        };
    });
</script>
</body>
</html>