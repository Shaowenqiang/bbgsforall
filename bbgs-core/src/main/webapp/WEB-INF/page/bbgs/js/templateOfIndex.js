/**
 * Created by swq on 2017-10-13.
 * 模板页面 在 index.html 操作 js
 */


//上传样式修改后的 把文件名显示的方法
$(".a-upload").on("change", "input[type='file']", function () {
    var filePath = $(this).val();
    if (filePath.indexOf("xlsx") !== -1 || filePath.indexOf("xls") !== -1) {
        var arr = filePath.split('\\');
        var fileName = arr[arr.length - 1];
        $("#showFileInfo1").val(fileName);
    } else if (filePath.length == 0) {
        $("#showFileInfo1").val("您未上传文件");
        return false;
    } else {
        $("#showFileInfo1").val("您上传文件类型有误！");
        return false;
    }
});
//新增模板modal addDemoModal
//新增模板类别ID
//    var templetParentid = ""
function addDemoModal(parentid) {
    console.log(parentid)
    $("#templetParentId").val(parentid)
    $("#addTemplateModal").modal()
}
//删除确认弹窗
function deleteConfirm(str) {
    $("#deleteId").text(str)
    $("#promptChoiseModal").modal()
}
/**
 * 确认删除
 * */
function confirmDel() {
    $.ajax({
        url: '/templet/delTemplet',// 跳转到 action
        data: {id: $("#deleteId").text()},
        type: 'post',
        cache: false,
        success: function (result) {
            if (result.state === "success") {
                alertSuccess("删除成功")
                window.frames["① 模板信息维护"].refreshAllSubTable()
                $("iframe").each(function () {
                    var name = $(this)[0].name
                    if(name === "⑥ 唱标报价数据输入、输出"){
                        window.frames["⑥ 唱标报价数据输入、输出"].reloadBindInfoFrame()
                    }
                })
            } else if (result.state === "error") {
                alertModal("删除失败")
            }
        },
        error: function () {
        }
    });

}
//详情查看
function detailView(id) {
    $.ajax({
        url: '/templet/viewExcel',// 跳转到 action
        data: {id: id},
        type: 'post',
        cache: false,
        success: function (result) {
            if (result.state === 'success') {
                console.log(result)
                console.log(result.msg)
                var fileName = result.msg.fileName
                var realName = result.msg.realName
                var options = {tabMainName: "myTab", tabContentMainName: "myTabContent"}
                options.tabName = result.msg.id
                options.tabUrl = '/page/bbgs/excelHtml/' + realName.replace(".xlsx", "").replace(".xls", "") + ".htm"
                options.tabTitle = fileName.replace(".xlsx", "").replace(".xls", "").trim() + "详情"
                addTab(options)
            } else if (result.state === 'error') {
                console.log(result.msg)
                alertModal(result.msg)
            }

        },
        error: function () {
            console.log("异常！")
//                // view("异常！");
        }
    });

}
//修改modal弹窗
function editDemoModal() {
    $("#editDemoModal").modal()
}
/**
 * 不能为空 弹窗
 * */
function alertModal(str) {
    $("#NoticeShow").text(str)
    $("#NoticeModal").modal()
}
/**
 * promptSuccessModal"successText
 * 操作成功提示
 * */
function alertSuccess(str) {
    $("#successText").text(str)
    $("#promptSuccessModal").modal()
}

var fileBtn = $("input[type=file]");
fileBtn.on("change", function () {
    var index = $(this).val().lastIndexOf("\\");
    var sFileName = $(this).val().substr((index + 1));
    $("#rightText").html(sFileName);
});
/**
 * 新增Templet模板
 */
function saveReport() {
    $("#addDataSourceForm").ajaxSubmit({
        url: "/templet/addTemplet",
        success: function (result) {
            if (result.state === 'success') {
                alertSuccess("新增成功！")
                $("#addTemplateModal").modal('hide')
                //成功后置空新增表单
                $("#rightText").text("")
                $("#inputFile").val("")
                $("#DemoName").val("")
                //调用子frame方法刷新所有子表格
                window.frames["① 模板信息维护"].refreshAllSubTable()
                $("iframe").each(function () {
                    var name = $(this)[0].name
                    if(name === "⑥ 唱标报价数据输入、输出"){
                        window.frames["⑥ 唱标报价数据输入、输出"].reloadBindInfoFrame()
                    }
                })
            } else if (result.state === 'error') {
                alertModal(result.msg)
            }
        }
    });
    return false
}
/**
 * 检查字符串是否以 suffix 结尾
 * @param str
 * @returns {boolean}
 */
function check(string, suffix) {
    var pos = string.lastIndexOf(suffix);
    if (pos === -1) {
        return false;
    } else {
        return pos + suffix.length === string.length;
    }
}
function saveDemo() {
    if ($("#DemoName").val() === "") {
        alertModal("模板名称不能为空！")
        return
    }
    if ($("#rightText").text() === "") {
        alertModal("请选择模板文件！")
        return
    } else if (!(check($("#rightText").text(), ".xls")||check($("#rightText").text(), ".xlsx"))) {
        alertModal("请选择正确类型的文件！")
        return
    }
    saveReport()
}
/*模板详细*/
function viewTemplateDetail() {
    var options = {tabMainName: "myTab", tabContentMainName: "myTabContent", tabName: "bidInfoDetail"}
    options.tabUrl = "bidInfoDetail.html"
    options.tabTitle = "标段模板信息明细"
    addTab(options)
}
/**
 * 设为默认 每个类型只能存在一个
 */
function setDefault(id) {
    $.post("/templet/setDefault", {id: id}, function (result) {
        if (result.state === "success") {
            window.frames["① 模板信息维护"].refreshAllSubTable()
            $("iframe").each(function () {
                var name = $(this)[0].name
                if(name === "⑥ 唱标报价数据输入、输出"){
                    window.frames["⑥ 唱标报价数据输入、输出"].reloadBindInfoFrame()
                }
            })
        }
    })
}
/**
 * 刷新模板信息明细的表格
 */
function refreshTempDetailTable() {
    window.frames["标段模板信息明细"].refreshTable()
}

/**
 * modal 点击空白不关闭
 */
// $('#addTemplateModal').modal({backdrop: 'static', keyboard: false});

// backdrop:static时,空白处不关闭.
//
//     keyboard:false时,esc键盘不关闭.

//============ sssJL start =================
/**
 * 是否确认流标
 * */
function alertBidModal(str) {
    $("#optionBid").text(str);
    $("#promptBidModal").modal();
}
function hideBidModal() {
    $("#promptBidModal").modal("hide");
}
/**
 * 至少选择一个
 * */
function alertLessOneModal() {
    $("#promptLessOneModal").modal();
}
//============ sssJL end =================
