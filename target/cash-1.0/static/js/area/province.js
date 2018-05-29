var defaults = {
    s1: 'province',
    s2: 'city',
    s3: 'county',
    v1: "440000",
    v2: "441900",
    v3: "441901"
};
var $form;
var form;
var $;
layui.define(['jquery', 'form'], function () {
    $ = layui.jquery;
    form = layui.form;
    $form = $('form');
    treeSelect(defaults);
});
function treeSelect(config) {
    config.v1 = config.v1 ? config.v1 : "440000";
    config.v2 = config.v2 ? config.v2 : "441900";
    config.v3 = config.v3 ? config.v3 : "441901";
    $.each(threeSelectData, function (k, v) {
        appendOptionTo($form.find('select[name=' + config.s1 + ']'), k, v.val, config.v1);
    });
    form.render();
    cityEvent(config);
    if(config.v2.indexOf("-")>-1){
        config.v2 = config.v2.split("-")[1];
    }
    areaEvent(config);
    form.on('select(' + config.s1 + ')', function (data) {
        if(data.value.indexOf("-")>-1){
            data.value = data.value.split("-")[1];
        }
        cityEvent(data);
        // $('select[name="city"]').next().find('.layui-select-title input').click();
        // var select = 'dd[lay-value="' + tempVal + '"]';
        // $('#city').siblings("div.layui-form-select").find('dl').find(select).click();
        config.v2 = $('select[name=' + config.s2 + ']').val();
        if(config.v2.indexOf("-")>-1){
            config.v2 = config.v2.split("-")[1];
        }
        areaEvent(config);
        form.on('select(' + config.s2 + ')', function (data) {
            console.log(data);
            // if(data.value.indexOf("-")>-1){
            //     data.value = data.value.split("-")[1];
            // }
            // areaEvent(data);
            config.v2 = $('select[name=' + config.s2 + ']').val();
            if(config.v2.indexOf("-")>-1){
                config.v2 = config.v2.split("-")[1];
            }
            console.log(config.v2);
            areaEvent(config);
        });
    });

    function cityEvent(data) {
        $form.find('select[name=' + config.s2 + ']').html("");
        config.v1 = data.value ? data.value : config.v1;
        $.each(threeSelectData, function (k, v) {
            if (v.val === config.v1) {
                if (v.items) {
                    $.each(v.items, function (kt, vt) {
                        appendOptionTo($form.find('select[name=' + config.s2 + ']'), kt, vt.val, config.v2);
                    });
                }
            }
        });
        form.render();
        config.v2 = $('select[name=' + config.s2 + ']').val();
        if(config.v2.indexOf("-")>-1){
            config.v2 = config.v2.split("-")[1];
        }
        areaEvent(config);
    }
    function areaEvent(data) {
        $form.find('select[name=' + config.s3 + ']').html("");
        config.v2 = data.value ? data.value : config.v2;
        $.each(threeSelectData, function (k, v) {
            if (v.val === config.v1) {
                if (v.items) {
                    $.each(v.items, function (kt, vt) {
                        if (vt.val === config.v2) {
                            $.each(vt.items, function (ka, va) {
                                appendOptionTo($form.find('select[name=' + config.s3 + ']'), ka, va, config.v3);
                            });
                        }
                    });
                }
            }
        });
        form.render();
        form.on('select(' + config.s3 + ')', function (data) { });
    }
    function appendOptionTo($o, k, v, d) {
        var $opt = $("<option>").text(k).val(k+'-'+v);
        if (v === d) { $opt.attr("selected", "selected") }
        $opt.appendTo($o);
    }
}