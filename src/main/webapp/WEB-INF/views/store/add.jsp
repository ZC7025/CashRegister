<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018-5-24
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>门店添加</title>
</head>
<link rel="stylesheet" href="<%=path%>/static/css/public.css">
<link rel="stylesheet" href="<%=path%>/static/layui/css/layui.css">
<link rel="stylesheet" href="<%=path%>/static/css/form-public.css">
<body>
<div class="container">
    <div class="account-right">
        <h1>门店添加</h1>
        <hr/>
        <form class="layui-form" id="store">
            <div class="layui-form-item">
                <label class="layui-form-label">门店编号</label>
                <div class="layui-input-block">
                    <input type="text" name="storeId" lay-verify="required" autocomplete="off" placeholder="请输入门店编号"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">门店名称</label>
                <div class="layui-input-block">
                    <input type="text" name="name" lay-verify="required" autocomplete="off"
                           placeholder="请输入门店名称" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">手机号码</label>
                <div class="layui-input-block">
                    <input type="text" name="phone" lay-verify="required" autocomplete="off"
                           placeholder="请输入手机号码" maxlength="11" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">登录密码</label>
                <div class="layui-input-block">
                    <input type="password" id="pwd" name="pwd" lay-verify="required|pass" autocomplete="off"
                           placeholder="请输入登录密码" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">确认密码</label>
                <div class="layui-input-block">
                    <input type="password" id="repwd" name="repwd" lay-verify="required|repass" autocomplete="off"
                           placeholder="请输入确认密码" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">关联邮箱</label>
                <div class="layui-input-block">
                    <input type="text" name="email" lay-verify="required|email" autocomplete="off"
                           placeholder="请输入关联邮箱" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">选择地区</label>
                <div class="layui-input-inline">
                    <select name="province" id="province" lay-filter="province" lay-verify="required">
                        <option value="">请选择省</option>
                    </select>
                </div>
                <div class="layui-input-inline">
                    <select name="city" id="city" lay-filter="city" lay-verify="required">
                        <option value="">请选择市</option>
                    </select>
                </div>
                <div class="layui-input-inline">
                    <select name="county" id="county" lay-filter="county" lay-verify="required">
                        <option value="">请选择县/区</option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">详细地址</label>
                <div class="layui-input-block">
                    <input type="text" name="address" lay-verify="required" autocomplete="off"
                           placeholder="请输入详细地址" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">行业类型</label>
                <div class="layui-input-block">
                    <select name="industryId" id="type-select" lay-filter="required">
                        <option value="">请选择行业类型</option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">所属总店</label>
                <div class="layui-input-block">
                    <select name="generalId" id="store-select">
                        <option value="">请选择所属总店</option>
                    </select>
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-row">
                    <label class="layui-form-label">相关文件</label>
                    <div class="layui-col-md2">
                        <div class="layui-upload">
                            <button type="button" class="layui-btn" id="license">营业执照</button>
                            <div class="layui-upload-list">
                                <img class="layui-upload-img" id="imgDemo" style="width:120px;height:120px">
                                <p id="imgText"></p>
                            </div>
                        </div>
                        <input type="hidden" name="licenseImg" id="licenseImg"/>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label"></label>
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit lay-filter="add">提交申请</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
<script type="text/javascript" src="<%=path %>/static/layui/layui.js"></script>
<script type="text/javascript" src="<%=path %>/static/js/area/data.js"></script>
<script type="text/javascript" src="<%=path %>/static/js/area/province.js"></script>
<script type="text/javascript" src="<%=path %>/static/js/home/public.js"></script>
<script>
    layui.use(['form', 'upload'], function () {

        var form = layui.form;
        var $ = layui.jquery;
        var layer = layui.layer;
        var upload = layui.upload;
        $(function () {
            var html = "";
            $.ajax({
                url: '<%=path %>/data/industry/all',
                success: function (data) {
                    //加载数据
                    for (var i = 0; i < data.length; i++) {
                        html += '<option value="' + data[i].id + '">' + data[i].name + '</option>'
                    }
                    $("#type-select").append(html);
                    // 重新刷新表单，新option才会出现
                    form.render();
                }
            });
            var storeList = "";
            $.ajax({
                url: '<%=path %>/data/store/all',
                success: function (data) {
                    //加载数据
                    for (var i = 0; i < data.length; i++) {
                        var city = data[i].city.split("-")[0];
                        var county = data[i].county.split("-")[0];
                        storeList += '<option value="' + data[i].id + '">' + data[i].name + '('+city+county+data[i].address+')</option>'
                    }
                    $("#store-select").append(storeList);
                    // 重新刷新表单，新option才会出现
                    form.render();
                }
            });
        });

        form.verify({
            pass: [/(.+){6,12}$/, '密码必须6到12位']
        });

        form.verify({
            repass: function(value){
                var repassValue = $('#pwd').val();
                if(value !== repassValue){
                    return '两次输入的密码不一致!';
                }
            }
        });

        uploadImg(upload, 'license', '<%=path %>/file/firist', 'imgDemo', 'licenseImg', 'imgText');

        form.on('submit(add)', function (data) {
            $.post('<%=path %>/data/store/save',
                $('#store').serialize(),
                function (res) {
                    if (res.code === 0) {
                        layer.msg('添加成功', {
                            time: 1000 //2秒关闭（如果不配置，默认是3秒）
                        }, function () {
                            parent.location.reload(true);
                        });
                        layer.closeAll(index);
                    } else {
                        layer.msg(res.message);
                    }
                }, 'json'
            );
            return false;
        });
    })
</script>
</html>
