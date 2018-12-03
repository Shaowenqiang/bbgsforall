initRadio("category")
/**
 * 查询物资类别
 */
queryType()
function queryType(){
    $.post("/basicInfo/queryBatchName", function (result) {
        if (result.state === "success") {
            if (result.category === 'Y') {
                $("input[name=category]:first").click();
            }
            if (result.category === 'N') {
                $("input[name=category]:last").click();
            }
        }
    })
}


//上传样式修改后的 把文件名显示的方法
$(".a-upload").on("change", "input[type='file']", function () {
    var filePath = $(this).val();
    if (filePath.indexOf(".xls") != -1 || filePath.indexOf(".xlsx") != -1) {
        var arr = filePath.split('\\');
        var fileName = arr[arr.length - 1];
        $("#showFileInfo").val(fileName);
        $("#showFileInfo1").val(fileName);
    } else if (filePath.length == 0) {
        $(".showFileInfo").val("您未上传文件");
        $(".showFileInfo1").val("您未上传文件");
        return false;
    } else {
        $("#showFileInfo").val("您上传文件类型有误！");
        $("#showFileInfo1").val("您上传文件类型有误！");
        return false;
    }
});
/**
 * 电商文件夹上传文件 显示信息
 */
$('input[id=file]').change(function() {
    var size = $('input[id=file]')[0].files.length
    $('#uploadurl').val(" 你一共选择了"+size+"个文件");
});

function saveReport() {
    console.log($('input[id=file]')[0].files)
    var fileList = $('input[id=file]')[0].files
    for (var i=0;i<fileList.length;i++){
        console.log(fileList[i].name)
        console.log(fileList[i].name.indexOf(".xls"))
        console.log(fileList[i].name.indexOf(".xlsx"))
        console.log(fileList[i].name)
        if(fileList[i].name.indexOf(".xls")<0 && fileList[i].name.indexOf(".xlsx")<0){
            parent.window.alertModal("文件夹中存在“"+fileList[i].name+"”，它并不是一个excel文件！")
            return false
        }
    }
    if ($("#exampleInputFile1").val() === "" && $('input[id=file]')[0].files.length ===0 ) {
        parent.window.alertModal("请选择上传文件！")
        return false
    }
    $("#filesForm").ajaxSubmit({
        beforeSubmit: function () {
            parent.loadingOn("导入中")
        },
        error:function(){
            parent.loadingOff()
        },
        success: function (result) {
            console.log(result)
            if (result.state === 'success') {
                refreshFrame()
                parent.loadingOff()
                parent.window.alertSuccess("导入成功！")
                parent.window.displayBatchName()
                $("#errorMsg").text("")
                $("#importExcelBtn").attr("disabled", "disabled")
            } else if (result.state === 'error') {
                parent.loadingOff()
                parent.window.alertModal("导入失败！")
                $("#errorMsg").text(result.msg)
                $("#errorMsg").attr("title",result.msg)
            }
        }
    });
    return false
}
/**
 * 判断是否存在批次数据
 */
isExistBatch()
function isExistBatch() {
    $.post("/basicInfo/existData", function (result) {
        if (result.state === "success") {
            $("#importExcelBtn").attr("disabled", "disabled")
        }
    })
}
/**
 * 导入按钮失效
 */
function grayImportExcelBtn() {
    $("#importExcelBtn").removeAttr("disabled")
}

/**
 * 重新加载frame信息
 */
function refreshFrame() {
    document.getElementById('chaa').contentWindow.location.reload(true);
}

/**
 * 导出唱标模板和投标人清单
 */
function exportFirstExcel() {
    parent.loadingOn("生成中...")
    var url = "";
    $.post("/basicInfo/queryBatchName", function (result) {
        if (result.state === "success") {
            if (result.category === 'J') {
                $.post("/basicInfo/exportRaceTalk", function (result) {
                    if (result.state === "success") {
                        window.location.target = '_blank'
                        window.location.href = '/page/bbgs/excel/投标人清单/投标人清单记录.xls'
                        parent.loadingOff()
                    }
                    if (result.state === "error") {
                        if(result.msg!=null){
                            parent.window.alertModal(result.msg)
                        }else{
                            parent.window.alertModal("导出失败")
                        }

                    }
                    parent.loadingOff()
                })
            }else{
                $.post("/basicInfo/exportFirstExcel", function (result) {
                    if (result.state === "success") {
                        window.location.target = '_blank'
                        window.location.href = '/page/bbgs/excel/投标人清单/投标人清单记录.xls'
                        parent.loadingOff()
                    }
                    if (result.state === "error") {
                        if(result.msg!=null){
                            parent.window.alertModal(result.msg)
                        }else{
                            parent.window.alertModal("导出失败")
                        }

                    }
                    parent.loadingOff()
                })
            }

        }
    })


}

/**
 * 电商类采购和物资正常采购上传方式改变
 */
function changeUploadType (uploadType){
    if(uploadType === 'Y'){
        $("#uploadType_1").css("display","block")
        $("#uploadType_2").css("display","none")
        $("#filesForm").attr("action","/basicInfo/saveData")
    }
    if(uploadType === 'E'){
        $("#uploadType_1").css("display","none")
        $("#uploadType_2").css("display","block")
        $("#filesForm").attr("action","/basicInfo/saveDataForE")
    }
}