<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <meta charset="utf-8">
    <title>商品管理</title>
    <link rel="stylesheet" href="<%=path %>/static/layui/css/layui.css" media="all"/>
</head>
<body>

<div class="account-right">
    <h1>商品管理</h1>
    <hr/>
    <div class="layui-row">
        <form id="proQuery" class="layui-form">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <input type="text" id="name" name="name" placeholder="请输入商品名称" class="layui-input layui">
                </div>
                <div class="layui-inline">
                    <select name="unitId" id="unit" lay-verify="required">
                        <option value="">请选择单位</option>
                    </select>
                </div>
                <div class="layui-inline">
                    <select name="typeId" id="type" lay-verify="required">
                        <option value="">请选择商品类型</option>
                    </select>
                </div>
                <div class="layui-inline">
                    <select name="tasteId" id="taste" lay-verify="required">
                        <option value="">请选择口味</option>
                    </select>
                </div>
                <a href="javascript:void(0);" class="layui-btn" id="searchBtn" data-type="reload">搜索</a>
            </div>
        </form>
    </div>
    <div class="layui-btn-group demoTable">
        <a href="javascript:void(0);" class="layui-btn" id="proShow" data-type="edit">查看商品配方</a>
        <button class="layui-btn" data-type="update">修改商品信息</button>
        >
        <button class="layui-btn" data-type="explort">导出商品信息</button>
        >
        <button class="layui-btn" data-type="delete">删除商品</button>
        <button class="layui-btn" data-type="refresh">刷新</button>
    </div>

    <table id="product_table" lay-filter="demo"></table>

    <%--商品配方列表弹窗--%>
    <div style="display: none;" id="proListShow">
        <div class="layui-btn-group proTable">
            <button class="layui-btn" data-type="deleteFormula">删除商品配方</button>
        </div>
        <table id="proList"></table>
    </div>
</div>

<script type="text/javascript" src="<%=path %>/static/layui/layui.js"></script>
<script type="text/javascript" src="<%=path %>/static/js/home/public.js"></script>
<script type="text/html" id="imgUtil">
    {{#  if(d.proImg1 !== null && d.proImg1 != ''){ }}
    <img src="<%=path %>/{{ d.proImg1 }}" alt="d.proImg1"/>
    {{#  } else { }}
    <span>暂无图片</span>
    {{#  } }}
</script>
<script type="text/html" id="imgUtil2">
    {{#  if(d.proImg2 !== null && d.proImg2 != ''){ }}
    <img src="<%=path %>/{{ d.proImg2 }}" alt="d.proImg2"/>
    {{#  } else { }}
    <span>暂无图片</span>
    {{#  } }}
</script>
<script type="text/html" id="imgUtil3">
    {{#  if(d.proImg3 !== null && d.proImg3 != ''){ }}
    <img src="<%=path %>/{{ d.proImg3 }}" alt="d.proImg3"/>
    {{#  } else { }}
    <span>暂无图片</span>
    {{#  } }}
</script>
<script type="text/html" id="imgUtil4">
    {{#  if(d.proImg4 !== null && d.proImg4 != ''){ }}
    <img src="<%=path %>/{{ d.proImg4 }}" alt="d.proImg4"/>
    {{#  } else { }}
    <span>暂无图片</span>
    {{#  } }}
</script>
<script>
    layui.use(['table', 'form'], function () {
        var table = layui.table;
        var $ = layui.$;
        var form = layui.form;
        var supList = "";
        var unitList = "";
        var typeList = "";

        table.render({
            elem: '#product_table'
            , url: '<%=path %>/data/product/proList'
            , cols: [[
                {checkbox: true, fixed: true}
                , {field: 'name', title: '商品名称', width: 150}
                , {field: 'taste', title: '口味', width: 150}
                , {field: 'typeName', title: '商品类型', width: 150}
                , {field: 'unit', title: '单位', width: 150}
                , {field: 'price', title: '价格', width: 150}
                , {field: 'proImg1', title: '图片1', width: 150, templet: '#imgUtil'}
                , {field: 'proImg2', title: '图片2', width: 150, templet: '#imgUtil2'}
                , {field: 'proImg3', title: '图片3', width: 150, templet: '#imgUtil3'}
                , {field: 'proImg4', title: '图片4', width: 150, templet: '#imgUtil4'}
                , {field: 'priority', title: '优先级', width: 150}
                , {field: 'status', title: '状态', width: 100, templet: '<div>{{formatStatus(d.status)}}</div>'}
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

        $('.proTable .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        $('#searchBtn').on('click', function(){
            var type = $(this).data('type');
            active[type].call(this);
        });

        $('#proShow').on('click', function () {
            var checkStatus = table.checkStatus('idTest')
                , data = checkStatus.data;
            if (data.length === 1) {
                layer.open({
                    type: 1,                //弹窗类型
                    title: '商品配方',     //显示标题
                    closeBtn: 1,         //是否显示关闭按钮
                    shadeClose: true, //显示模态窗口
                    fixed: false,    //层是否固定在可视区域
                    offset: 't',//快捷设置顶部坐标
                    move: true,//禁止拖拽
                    area: ['890px', '560px'], //宽高
                    content: $("#proListShow")  //弹窗内容
                });
                table.render({
                    elem: '#proList'
                    , url: '<%=path %>/data/formula/formulaList?proId=' + data[0].id
                    , cols: [[
                        {checkbox: true, fixed: true}
                        , {field: 'name', title: '原料名', width: 90}
                        , {field: 'unit', title: '单位', width: 150}
                        , {field: 'count', title: '数量', width: 150}
                        , {field: 'descript', title: '介绍', width: 300}
                    ]]
                    , id: 'proId'
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
            } else {
                layer.msg('请选中一行！', {time: 1500});
            }
        });

        var active = {
            delete: function () {
                var checkStatus = table.checkStatus('idTest')
                    , data = checkStatus.data;
                if (data.length === 1) {
                    $.get('<%=path %>/data/product/remove/' + data[0].id,
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
            }, deleteFormula: function () {
                var checkStatus = table.checkStatus('proId')
                    , data = checkStatus.data;
                if (data.length === 1) {
                    $.get('<%=path %>/data/formula/remove/' + data[0].id,
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
            }, update: function () { //验证是否全选
                var checkStatus = table.checkStatus('idTest')
                    , data = checkStatus.data;
                if (data.length === 1) {
                    layer.open({
                        type: 2,
                        area: ['80%', '80%'],
                        maxmin: true,
                        content: "<%=path %>/page/product/update?id=" + data[0].id
                    })
                } else {
                    layer.msg("请选择一行！");
                }

            }, explort: function () {
                $.get('<%=path %>/data/product/proExplort',
                    function (data) {
                        if (data.code === 0) {
                            layer.msg("导出成功！请前往桌面查看文件");
                        } else {
                            layer.msg("导出失败！");
                        }
                    });
            }, refresh: function () {
                location.reload(true);
            }, reload: function(){
                table.reload('idTest', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    ,where: {
                        name : $('#name').val()
                        ,unitId : $('#unit').val()
                        ,typeId : $('#type').val()
                        ,tasteId : $('#taste').val()
                    }
                });
            }
        };
        $(function () {
            if(${sessionScope.store != null}) {
                $.ajax({
                    url: '<%=path %>/data/proType/all',
                    success: function (data) {
                        for (var i = 0; i < data.length; i++) {
                            typeList += '<option value="' + data[i].id + '">'
                                + data[i].name + '</option>'
                        }
                        $("#type").append(typeList);
                        // 重新刷新表单，新option才会出现
                        form.render();
                    }
                });
                $.ajax({
                    url: '<%=path %>/data/taste/all',
                    success: function (data) {
                        for (var i = 0; i < data.length; i++) {
                            supList += '<option value="' + data[i].id + '">'
                                + data[i].taste + '</option>'
                        }
                        $("#taste").append(supList);
                        // 重新刷新表单，新option才会出现
                        form.render();
                    }
                });
                $.ajax({
                    url: '<%=path %>/data/unit/all',
                    success: function (data) {
                        var len = data.length;
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
    });
</script>
</body>
</html>