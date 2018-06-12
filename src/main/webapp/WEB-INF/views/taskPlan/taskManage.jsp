<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <meta charset="utf-8">
    <title>计划任务</title>
    <link rel="stylesheet" href="<%=path %>/static/layui/css/layui.css" media="all"/>
</head>
<body>


<div class="layui-btn-group demoTable" >
    <button class="layui-btn" data-type="add">新增计划任务</button>
    <button class="layui-btn" data-type="update">修改计划任务</button>
    <button class="layui-btn" data-type="active">暂停或开始</button>
    <button class="layui-btn" data-type="delete">删除计划任务</button>
    <button class="layui-btn" data-type="refresh">刷新</button>
</div>

<table id="task_table" lay-filter="demo"></table>

<script type="text/javascript" src="<%=path %>/static/layui/layui.js"></script>
<script type="text/javascript" src="<%=path %>/static/js/home/public.js"></script>
<script>
    layui.use('table', function(){
        var table = layui.table;
        var $ = layui.$;

        table.render({
            elem: '#task_table'
            ,url: '<%=path %>/data/taskPlan/planList'
            ,cols: [[
                {checkbox: true, fixed: true}
                ,{field:'className', title:'类名', width:150}
                ,{field:'jobName', title:'任务名称', width:150}
                ,{field:'cronExpression', title:'时间表达式', width:150}
                ,{field:'isStart', title:'执行状态', width:100,templet:'<div>{{formatStart(d.isStart)}}</div>'}
                ,{field:'descript', title:'描述', width:150}
                ,{field:'status', title:'状态', width:100,templet:'<div>{{formatStatus(d.status)}}</div>'}
                ,{field:'updateTime', title:'更新时间', width:150,templet:'<div>{{ formatDateTime(d.updateTime)}}</div>'}
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
                    $.get('<%=path %>/data/taskPlan/remove/' + data[0].id,
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
            ,add: function(){
                layer.open({
                    type: 2,
                    title: '计划任务添加',
                    area: ['85%', '85%'],
                    maxmin:true,
                    content: '<%=path %>/page/taskPlan/add'
                });
            }
            ,update: function(){ //验证是否全选
                var checkStatus = table.checkStatus('idTest')
                    ,data = checkStatus.data;
                if(data.length === 1) {
                    layer.open({
                        type: 2,
                        area: ['85%', '85%'],
                        maxmin:true,
                        content:"<%=path %>/page/taskPlan/update?id="+data[0].id
                    })
                } else {
                    layer.msg("请选择一行！");
                }

            },active: function(){ //验证是否全选
                var checkStatus = table.checkStatus('idTest')
                    ,data = checkStatus.data;
                if(data.length === 1) {
                    var status = data[0].isStart === 'Y'? 'N':'Y';
                    $.get('<%=path %>/data/taskPlan/active?id=' + data[0].id + '&status='+ status,
                        function (data) {
                            if(data.code===0){
                                layer.msg(data.message);
                                location.reload(true);
                            }else {
                                layer.msg(data.message);
                            }
                        });
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