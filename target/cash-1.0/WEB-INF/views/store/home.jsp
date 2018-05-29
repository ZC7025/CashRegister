<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>门店后台</title>
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
                    <img src="<%=path %>/static/images/face.jpg" class="layui-nav-img"> <sapn id="nickname">${store.name }</sapn>
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
                <%--&lt;%&ndash;设置奖励和审核&ndash;%&gt;--%>
                <%--<shiro:hasPermission name="manage">--%>
                    <li class="layui-nav-item">
                        <a href="javascript:;"><span>门店管理</span></a>
                        <dl class="layui-nav-child">
                            <dd><a href="javascript:;" kit-target
                                   data-options="{url:'<%=path %>/page/store/update?id=${store.id}',icon:'&#xe658;',title:'修改信息',id:'1'}"><i class="layui-icon">&#xe658;</i><span>修改信息</span></a>
                            </dd>
                            <dd><a href="javascript:;" kit-target
                                   data-options="{url:'<%=path %>/page/store/updatePwd',icon:'&#xe658;',title:'修改密码',id:'2'}"><i class="layui-icon">&#xe658;</i><span>修改密码</span></a>
                            </dd>
                        </dl>
                    </li>
                    <li class="layui-nav-item">
                        <a href="javascript:;"><span>牌号管理</span></a>
                        <dl class="layui-nav-child">
                            <dd><a href="javascript:;" kit-target
                                   data-options="{url:'<%=path %>/page/grade/gradeList',icon:'&#xe658;',title:'牌号管理',id:'3'}"><i class="layui-icon">&#xe658;</i><span>牌号管理</span></a>
                            </dd>
                        </dl>
                    </li>
                    <%--<li class="layui-nav-item">--%>
                        <%--<a href="javascript:;"><span>认证管理</span></a>--%>
                        <%--<dl class="layui-nav-child">--%>
                            <%--<dd><a href="javascript:;" kit-target--%>
                                   <%--data-options="{url:'<%=path %>/page/vip/userVipPage',icon:'&#xe658;',title:'用户',id:'20'}"><i class="layui-icon">&#xe658;</i><span>用户</span></a>--%>
                            <%--</dd>--%>
                        <%--</dl>--%>
                    <%--</li>--%>
                <%--</shiro:hasPermission>--%>
                    <%--&lt;%&ndash;root用户可见&ndash;%&gt;--%>
                    <%--<shiro:hasPermission name="root">--%>
                        <%--<li class="layui-nav-item">--%>
                            <%--<a href="javascript:;"><span>角色权限管理</span></a>--%>
                            <%--<dl class="layui-nav-child">--%>
                                <%--<dd><a href="javascript:;" kit-target--%>
                                       <%--data-options="{url:'<%=path%>/page/Jur/List',icon:'&#xe658;',title:'权限管理',id:'47'}"><i class="layui-icon">&#xe658;</i><span>权限管理</span></a>--%>
                                <%--</dd>--%>
                                <%--<dd><a href="javascript:;" kit-target--%>
                                       <%--data-options="{url:'<%=path%>/page/Role/List',icon:'&#xe658;',title:'角色管理',id:'49'}"><i class="layui-icon">&#xe658;</i><span>角色管理</span></a>--%>
                                <%--</dd>--%>
                            <%--</dl>--%>
                        <%--</li>--%>
                    <%--</shiro:hasPermission>--%>
                <%--root用户可见--%>
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


        //信息
        $('#info').on('click', function () {
            layer.open({
                type: 1,                //弹窗类型
                title: '编辑个人信息',     //显示标题
                closeBtn: 1,         //是否显示关闭按钮
                shadeClose: true, //显示模态窗口
                fixed: false,    //层是否固定在可视区域
                move: false,//禁止拖拽
                area: ['890px', '560px'], //宽高
                content: $("#editIndexMsg")  //弹窗内容
            });
        });

        $('#out').on('click', function () {
            $.get('<%=path %>/data/store/out',
              function (data) {
                  if(data.code === 0) {
                      window.location = '/'
                  } else {
                      layer.msg('注销失败！');
                  }
              }
            );
        });

        //修改信息
        form.on('submit(edit)', function (data) {
            $.post('<%=path %>/data/admin/edit',
                $('#huserForm').serialize(),
                function (res) {
                    if (res.code === 0) {
                        layer.closeAll();
                        layer.msg('修改成功！');
                        $('#rname').empty();
                        $('#rname').text(res.data.rname);
                    } else {
                        layer.msg("修改失败，请重新再试")
                    }
                }, 'json'
            );
            return false;
        });

    });

</script>
</body>
</html>