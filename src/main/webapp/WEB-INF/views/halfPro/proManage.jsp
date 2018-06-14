<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <meta charset="utf-8">
    <title>半成品管理</title>
    <link rel="stylesheet" href="<%=path %>/static/layui/css/layui.css" media="all"/>
</head>
<body>

<div class="account-right">
    <h1>半成品管理</h1>
    <hr/>
    <div class="layui-btn-group demoTable">
        <button class="layui-btn" data-type="add">添加半成品信息</button>
        <button class="layui-btn" data-type="update">修改半成品信息</button>
        <button class="layui-btn" data-type="delete">删除半成品</button>
        <button class="layui-btn" data-type="refresh">刷新</button>
    </div>

    <table id="halfPro_table" lay-filter="demo"></table>

<script type="text/javascript" src="<%=path %>/static/layui/layui.js"></script>
<script type="text/javascript" src="<%=path %>/static/js/home/public.js"></script>
<script>
    layui.use(['table', 'form'], function () {
        var table = layui.table;
        var $ = layui.$;
        var form = layui.form;
        var unitList = "";

        table.render({
            elem: '#halfPro_table'
            , url: '<%=path %>/data/halfPro/proList'
            , cols: [[
                {checkbox: true, fixed: true}
                , {field: 'name', title: '半成品名称', width: 150}
                , {field: 'count', title: '数量', width: 150}
                , {field: 'unit', title: '单位', width: 150}
                , {field: 'status', title: '状态', width: 100, templet: '<div>{{formatHalfProStatus(d.status)}}</div>'}
                , {field: 'createdTime', title: '添加时间', width: 150, templet: '<div>{{ formatDateTime(d.createdTime)}}</div>'}
            ]]
            , id: 'idTest'
            , page: true
            , height: 500
            , response: {
                statusName: 'status'
                , statusCode: 0
                , msgName: 'message'
                , countName: 'total'
                , dataName: 'rows'
            }
        });

        $('.demoTable .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        $('.proTable .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
        var active = {
            delete: function () {
                var checkStatus = table.checkStatus('idTest')
                    , data = checkStatus.data;
                if (data.length === 1) {
                    $.get('<%=path %>/data/halfPro/remove/' + data[0].id,
                        function (data) {
                            if (data.code === 0) {
                                layer.msg("删除成功！");
                                location.reload(true);
                            } else {
                                layer.msg("删除失败！");
                            }
                        });
                } else {
                    layer.msg('请选中一行！', {time: 1500});
                }
            },add: function(){
                layer.open({
                    type: 2,
                    title: '半成品添加',
                    area: ['80%', '80%'],
                    maxmin:true,
                    content: '<%=path %>/page/halfPro/add'
                });
            }, update: function () { //验证是否全选
                var checkStatus = table.checkStatus('idTest')
                    , data = checkStatus.data;
                if (data.length === 1) {
                    layer.open({
                        type: 2,
                        area: ['80%', '80%'],
                        maxmin: true,
                        content: "<%=path %>/page/halfPro/update?id=" + data[0].id
                    })
                } else {
                    layer.msg("请选择一行！");
                }

            }, refresh: function () {
                location.reload(true);
            }
        };
    });
</script>
</body>
</html>