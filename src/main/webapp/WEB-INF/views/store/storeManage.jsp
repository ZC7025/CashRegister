<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <meta charset="utf-8">
    <title>门店管理</title>
    <link rel="stylesheet" href="<%=path %>/static/layui/css/layui.css" media="all"/>
</head>
<body>


<div class="layui-btn-group demoTable" >
    <button class="layui-btn" data-type="add">新增门店</button>
    <button class="layui-btn" data-type="update">修改门店信息</button>
    <button class="layui-btn" data-type="delete">删除门店</button>
    <button class="layui-btn" data-type="refresh">刷新</button>
</div>

<table id="store_table" lay-filter="demo"></table>

<script type="text/javascript" src="<%=path %>/static/layui/layui.js"></script>
<script type="text/javascript" src="<%=path %>/static/js/home/public.js"></script>
<script>
    layui.use('table', function(){
        var table = layui.table;
        var $ = layui.$;

        table.render({
            elem: '#store_table'
            ,url: '<%=path %>/data/store/storeList'
            ,cols: [[
                {checkbox: true, fixed: true}
                ,{field:'storeId', title:'门店编号', width:150}
                ,{field:'name', title:'门店名称', width:150}
                ,{field:'phone', title:'联系号码', width:150}
                ,{field:'email', title:'邮箱', width:150}
                ,{field:'province', title:'省', width:150,templet:'<div>{{formatArea(d.province)}}</div>'}
                ,{field:'city', title:'市', width:150,templet:'<div>{{formatArea(d.city)}}</div>'}
                ,{field:'county', title:'县/区', width:150,templet:'<div>{{formatArea(d.county)}}</div>'}
                ,{field:'address', title:'详细地址', width:150}
                ,{field:'industryName', title:'行业类型', width:150}
                ,{field:'generalName', title:'总店名称', width:150}
                ,{field:'status', title:'状态', width:100,templet:'<div>{{formatStatus(d.status)}}</div>'}
                ,{field:'createdTime', title:'入驻时间', width:150,templet:'<div>{{ formatDateTime(d.createdTime)}}</div>'}
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
                    $.get('<%=path %>/data/store/remove/' + data[0].id,
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
                    title: '添加门店',
                    area: ['80%', '80%'],
                    maxmin:true,
                    content: '<%=path %>/page/store/add'
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
                        content:"<%=path %>/page/store/update?id="+data[0].id
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