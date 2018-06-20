<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <meta charset="utf-8">
    <title>报损记录</title>
    <link rel="stylesheet" href="<%=path %>/static/layui/css/layui.css" media="all"/>
</head>
<body>

<div class="container">
    <div class="layui-row">
        <form id="loseQuery" class="layui-form">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <select name="searchType" id="searchType" lay-verify="required" lay-filter="searchType">
                        <option value="raw">原料报损记录</option>
                        <option value="pro">商品报损记录</option>
                    </select>
                </div>
            </div>
        </form>
    </div>
    <div class="layui-btn-group demoTable">
        <button class="layui-btn" data-type="add">新增报损记录</button>
        <%--<button class="layui-btn" data-type="update">修改报损记录</button>--%>
        <button class="layui-btn" data-type="delete">删除报损记录</button>
        <button class="layui-btn" data-type="refresh">刷新</button>
    </div>

    <table id="type_table" lay-filter="demo"></table>
</div>
<script type="text/javascript" src="<%=path %>/static/layui/layui.js"></script>
<script type="text/javascript" src="<%=path %>/static/js/home/public.js"></script>
<script>
    layui.use(['table', 'form'], function () {
        var table = layui.table;
        var $ = layui.$;
        var form = layui.form;

        table.render({
            elem: '#type_table'
            , url: '<%=path %>/data/lose/loseList'
            , cols: [[
                {checkbox: true, fixed: true}
                , {field: 'proName', title: '商品名', width: 150}
                , {field: 'name', title: '原料名', width: 150}
                , {field: 'count', title: '数量', width: 150}
                , {field: 'reason', title: '原因', width: 150}
                , {field: 'status', title: '状态', width: 100}
                , {
                    field: 'createdTime',
                    title: '添加时间',
                    width: 150,
                    templet: '<div>{{ formatDateTime(d.createdTime)}}</div>'
                }
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

        form.on('select(searchType)', function (data) {
            table.reload('idTest', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                , where: {
                    searchType: $('#searchType').val()
                }
            });
        });

        var active = {
            delete: function () {
                var checkStatus = table.checkStatus('idTest')
                    , data = checkStatus.data;
                if (data.length === 1) {
                    $.get('<%=path %>/data/lose/remove/' + data[0].id,
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
            }
            , add: function () { //支付类型添加
                layer.open({
                    type: 2,
                    title: '报损记录添加',
                    area: ['80%', '80%'],
                    maxmin: true,
                    content: '<%=path %>/page/lose/add'
                });
            }
            , update: function () { //验证是否全选
                var checkStatus = table.checkStatus('idTest')
                    , data = checkStatus.data;
                if (data.length === 1) {
                    layer.open({
                        type: 2,
                        area: ['80%', '80%'],
                        maxmin: true,
                        content: "<%=path %>/page/lose/update?id=" + data[0].id
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