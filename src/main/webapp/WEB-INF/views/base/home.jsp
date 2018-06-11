<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>后台</title>
    <link rel="stylesheet" href="<%=path %>/static/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="<%=path %>/static/css/home/app.css" media="all">
</head>
<body>
<div class="layui-layout layui-layout-admin kit-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">餐饮收银系统</div>
        <ul class="layui-nav layui-layout-left kit-nav">
            <li class="layui-nav-item">
                <!-- 天气信息-->
                <div class="weather" pc>
                    <div id="tp-weather-widget"></div>
                    <script>(function (T, h, i, n, k, P, a, g, e) {
                        g = function () {
                            P = h.createElement(i);
                            a = h.getElementsByTagName(i)[0];
                            P.src = k;
                            P.charset = "utf-8";
                            P.async = 1;
                            a.parentNode.insertBefore(P, a)
                        };
                        T["ThinkPageWeatherWidgetObject"] = n;
                        T[n] || (T[n] = function () {
                            (T[n].q = T[n].q || []).push(arguments)
                        });
                        T[n].l = +new Date();
                        if (T.attachEvent) {
                            T.attachEvent("onload", g)
                        } else {
                            T.addEventListener("load", g, false)
                        }
                    }(window, document, "script", "tpwidget", "//widget.seniverse.com/widget/chameleon.js"))</script>
                    <script>tpwidget("init", {
                        "flavor": "slim",
                        "location": "WX4FBXXFKE4F",
                        "geolocation": "enabled",
                        "language": "zh-chs",
                        "unit": "c",
                        "theme": "chameleon",
                        "container": "tp-weather-widget",
                        "bubble": "disabled",
                        "alarmType": "badge",
                        "color": "#FFFFFF",
                        "uid": "U9EC08A15F",
                        "hash": "039da28f5581f4bcb5c799fb4cdfb673"
                    });
                    tpwidget("show");</script>
                </div>
            </li>
        </ul>
        <ul class="layui-nav layui-layout-right kit-nav">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="<%=path %>/static/images/face.jpg" class="layui-nav-img"> <sapn id="nickname">${admin.nickname }</sapn>
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:;" id="info">基本资料</a></dd>
                    <dd><a href="javascript:;" id="out">注销</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item">
                <a href="<%=path %>/"><i class="fa fa-sign-out" aria-hidden="true"></i> 首页</a>
            </li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black kit-side">
        <div class="layui-side-scroll">
            <div class="kit-side-fold"><i class="layui-icon" aria-hidden="true">&#xe603;</i></div>
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="kitNavbar" kit-navbar>
                <li class="layui-nav-item">
                    <a href="javascript:;"><span>门店管理</span></a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" kit-target
                               data-options="{url:'<%=path %>/page/store/add',icon:'&#xe658;',title:'门店添加',id:'1'}"><i class="layui-icon">&#xe658;</i><span>门店添加</span></a>
                        </dd>
                        <dd><a href="javascript:;" kit-target
                               data-options="{url:'<%=path %>/page/store/storeList',icon:'&#xe658;',title:'门店管理',id:'2'}"><i class="layui-icon">&#xe658;</i><span>门店管理</span></a>
                        </dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;"><span>行业类别管理</span></a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" kit-target
                               data-options="{url:'<%=path %>/page/industry/typeList',icon:'&#xe658;',title:'行业类别管理',id:'3'}"><i class="layui-icon">&#xe658;</i><span>行业类别管理</span></a>
                        </dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;"><span>计划任务管理</span></a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" kit-target
                               data-options="{url:'<%=path %>/page/taskPlan/taskList',icon:'&#xe658;',title:'计划任务管理',id:'4'}"><i class="layui-icon">&#xe658;</i><span>计划任务管理</span></a>
                        </dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>
    <div class="layui-body" id="container">、
        <!-- 内容主体区域 ,修改main路劲的话要去tab.js下改动，所以我们到时候规定好一个main页面-->
        <div style="padding: 15px;">主体内容加载中,请稍等...</div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        2018 &copy;
        <a href="">东莞市速成信息科技有限公司</a>

    </div>
</div>

<script type="text/javascript" src="<%=path %>/static/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/static/layui/layui.js"></script>
<script>
    <%--$("input[name='sex'][value=${admin.sex}]").attr("checked",true);--%>
    var message;
    layui.config({
        base: '<%=path %>/static/js/home/'
    }).use(['app', 'message', 'form'], function () {
        var app = layui.app,
            $ = layui.jquery,
            layer = layui.layer;
        //将message设置为全局以便子页面调用
        message = layui.message;
        var form = layui.form;
        //主入口
        app.set({
            type: 'iframe'
        }).init();

        $('#out').on('click', function () {
            $.get('<%=path %>/data/admin/out',
              function (data) {
                  if(data.code === 0) {
                      window.location = '/'
                  } else {
                      layer.msg('注销失败！');
                  }
              }
            );
        });
    });

</script>
</body>
</html>