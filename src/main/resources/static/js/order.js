$(function () {
    $(".add_mi").eq(0).css('background', 'url("../images/mail_1.jpg") no-repeat').attr("id", "pay").siblings('.add_mi').css('background', 'url("../images/mail.jpg") no-repeat').attr("id", "");
    var aid = $("#pay").find("input").val();
    $('.Caddress .add_mi').click(function () {
        $(this).css('background', 'url("../images/mail_1.jpg") no-repeat').attr("id", "pay").siblings('.add_mi').css('background', 'url("../images/mail.jpg") no-repeat').attr("id", "");
        aid = $("#pay").find("input").val();
        console.log(aid);
    });
    $("#checkoutToPay").click(function () {
        $.ajax({
            url: "/car/pay/" + aid,
            type: "get",
            success: function (data) {
                if (data == true) {
                    success_prompt("正在下单，请稍后。。。", 2000);
                    window.setTimeout("window.location='/car/end'", 2000);
                }
            }

        });
    });
    totalMoney();
})

function onclick_open() {
    $(".shade_content").show();
    $(".shade").show();
    var input_out = $(".input_style");
    for (var i = 0; i <= input_out.length; i++) {
        if ($(input_out[i]).val() != "") {
            $(input_out[i]).val("");
        }
    }
}

function onclick_close() {
    var shade_content = $(".shade_content");
    var shade = $(".shade");
    if (confirm("确认关闭么！此操作不可恢复")) {
        shade_content.hide();
        shade.hide();
    }
}


function totalMoney() {
    var $sum = $(".sum");
    var total_money = 0;
    $sum.each(function () {
        console.log($(this).html());
        var goods = parseInt($(this).html().substring(1));
        console.log(goods)
        total_money += goods;
    });
    $('#totalPrice').html('$' + total_money);
}


