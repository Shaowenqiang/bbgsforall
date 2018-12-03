


$(".logoArea").css("margin-left", ($(window).width - 1000) / 2)
$("#loginBtn").click(function () {

    var url = '/mc_dic_server/login/in'
    var data = {
        loginName: $("#userName").val(),
        password: $("#password").val()
    }
    $.post("/login/intoIndex", data, function (result) {
        console.log(result)
        if (result.state === "success") {
            window.location.href = 'index.html'
        }
        if (result.state === "error") {
            $("#defeatDlg").dialog("open")
        }
    })
    // if ($("#userName").val() === "admin" && $("#password").val() === "admin") {
    //     window.location.href = 'index.html'
    // } else if ($("#userName").val() !== "admin" || $("#userName").val() !== "admin") {
    //     // $('#defeatDlg').dialog()
    // }
})

$("#revert").click(function () {
    $("#userName").val("")
    $("#password").val("")
})
$(".container_mine").css("margin-top", ($(window).height() - 500) / 2)
$(".container_mine").css("margin-left", ($(window).width() - 1000) / 2)
$(window).on('resize', function () {
    $(".container_mine").css("margin-top", ($(window).height() - 500) / 2)
    $(".container_mine").css("margin-left", ($(window).width() - 1000) / 2)
    $(".logoArea").css("margin-left", ($(window).width - 1000) / 2)
})
$(function () {
    document.onkeydown = function (e) {
        var ev = document.all ? window.event : e;
        if (ev.keyCode == 13) {
            $("#loginBtn").click()
        }
    }
});

var cb = "Unknown";
if(navigator.userAgent.toLowerCase().indexOf("chrome") === -1){
    alert('请使用谷歌浏览器，否则相关功能将不能正常使用！');
    window.open('/page/bbgs/google/ChromeStandalone_62.0.3202.75_Setup.exe','_blank');
}