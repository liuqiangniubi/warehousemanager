// jQuery.extend({
//     alertWindow:function(e,n){var e=e,r;n===undefined?r="#00a8b7":r=n;
//         if($("body").find(".alertWindow1").length===0){
//             var i='<div class="alertWindow1" style="width: 100%;height: 100%; background:rgba(0,0,0,0.5);position: fixed; left:0px; top: 0px; z-index: 9999;"><div  style="width: 360px; height: 100px;background: #FFF;margin: 300px auto;border: 2px solid #CFCFCF;">'+'<div  style="width: inherit;height: 20px;">'+'<div class="alertWindowCloseButton1" style="float: right; width: 10px; height: 30px;margin-right:5px;font-family:\'microsoft yahei\';color:'+r+';cursor: pointer;"></div>'+"</div>"+'<div id="successImg" class="alertWindowTitle" style="margin-top:10px;text-align:center;font-family:\'Verdana, Geneva, Arial, Helvetica, sans-serif\';font-size: 18px;font-weight: normal;color: '+r+';">'+"</div>"+'<div class="alertWindowContent" style="width:360px;height: 40px;text-align:center;font-size: 18px;color: #7F7F7F;margin-top:10px;">'+e+"</div>"+"</div>"+"</div>";
//             $("body").append(i);
//             var s=$(".alertWindow1");
//             //2秒后自动关闭窗口
//             setTimeout(function(){s.hide()},1000);
//         }
//         else {$(".alertWindowContent").text(e),$(".alertWindow1").show(),setTimeout(function(){$(".alertWindow1").hide()},1000);}
//     }
// })
//
function car(pid, uid) {
    console.log($("#user").text());
    if ($("#user").text() == '登录') {
        window.location.href = "/user/login/" + "请登录";
    } else {
        $.ajax({
            url: "/car/" + pid + "/" + uid,
            type: "get",
            dataType: "json",
            success: function (data) {
                console.log(data);
                if (data.state == true) {
                    success_prompt("加入成功", 1500);
                    $("#dd").text(data.size + "  item(s) - $" + data.sum);
                } else {
                    fail_prompt("加入失败，库存不足", 1500);
                }
            }
        });
    }
}

function car2(pid, uid) {
    if ($("#user").text() == '登录') {
        window.location.href = "/user/login/" + "请登录";
    } else {
        var num = parseInt($("#num").val());
        $.ajax({
            url: "/car/" + pid + "/" + uid + "/" + num,
            type: "get",
            dataType: "json",
            success: function (data) {
                console.log(data);
                if (data.state == true) {
                    success_prompt("加入成功", 1500);
                    $("#dd").text(data.size + "  item(s) - $" + data.sum);
                } else {
                    fail_prompt("加入失败，库存不足", 1500);
                }
            }
        });
    }
}

function carNum(pid, number, uid) {
    if ($("#user").text() == '登录') {
        window.location.href = "/user/login/" + "请登录";
    } else {
        console.log(pid, uid, number);
        $.ajax({
            url: "/car/num/" + pid + "/" + uid + "/" + number,
            type: "get",
            dataType: "json",
            success: function (data) {
                console.log(data);
                if (data.state == true) {
                    $("#dd").text(data.size + "  item(s) - $" + data.sum);
                } else {
                    fail_prompt("加入失败，库存不足", 1500);
                }
            }
        });
    }
}

/**
 * 弹出式提示框，默认1.2秒自动消失
 * @param message 提示信息
 * @param style 提示样式，有alert-success、alert-danger、alert-warning、alert-info
 * @param time 消失时间
 */
var prompt = function (message, style, time) {
    style = (style === undefined) ? 'alert-success' : style;
    time = (time === undefined) ? 1200 : time;
    $('<div>')
        .appendTo('body')
        .addClass('alert ' + style)
        .html(message)
        .show()
        .delay(time)
        .fadeOut();
};

// 成功提示
var success_prompt = function (message, time) {
    prompt(message, 'alert-success', time);
};

// 失败提示
var fail_prompt = function (message, time) {
    prompt(message, 'alert-danger', time);
};

// 提醒
var warning_prompt = function (message, time) {
    prompt(message, 'alert-warning', time);
};

// 信息提示
var info_prompt = function (message, time) {
    prompt(message, 'alert-info', time);
};