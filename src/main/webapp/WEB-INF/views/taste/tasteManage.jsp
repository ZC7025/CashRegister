<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <meta charset="utf-8">
    <title>口味管理</title>
    <link rel="stylesheet" href="<%=path %>/static/layui/css/layui.css" media="all"/>
</head>
<body>


<div class="layui-btn-group demoTable" >
    <button class="layui-btn" data-type="add">新增口味</button>
    <button class="layui-btn" data-type="update">修改口味</button>
    <button class="layui-btn" data-type="delete">删除口味</button>
    <button class="layui-btn" data-type="refresh">刷新</button>
</div>

<table id="taste_table" lay-filter="demo"></table>

<script type="text/javascript" src="<%=path %>/static/layui/layui.js"></script>
<script type="text/javascript" src="<%=path %>/static/js/home/public.js"></script>
<script>
    layui.use('table', function(){
        var table = layui.table;
        var $ = layui.$;

        table.render({
            elem: '#taste_table'
            ,url: '<%=path %>/data/taste/tasteList'
            ,cols: [[
                {checkbox: true, fixed: true}
                ,{field:'taste', title:'口味', width:150}
                ,{field:'descript', title:'口味简介', width:450}
                ,{field:'status', title:'状态', width:100,templet:'<div>{{formatStatus(d.status)}}</div>'}
                ,{field:'createdTime', title:'创建时间', width:150,templet:'<div>{{ formatDateTime(d.createdTime)}}</div>'}
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
                    $.get('<%=path %>/data/taste/remove/' + data[0].id,
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
            ,add: function(){ //商品类型添加

                layer.open({
                    type: 2,
                    title: '商品类型添加',
                    area: ['700px', '400px'],
                    maxmin:true,
                    content: '<%=path %>/page/taste/add'
                });
            }
            ,update: function(){ //验证是否全选
                var checkStatus = table.checkStatus('idTest')
                    ,data = checkStatus.data;
                if(data.length === 1) {
                    layer.open({
                        type: 2,
                        area: ['700px', '400px'],
                        maxmin:true,
                        content:"<%=path %>/page/taste/update?id="+data[0].id
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