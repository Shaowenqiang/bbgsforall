/**
 * 表格字段 显示函数
 * 是否中标
 * @param value 字段值
 * @param row 字段所在行数据
 * @param index 字段所在行 行号
 * @returns {string} 显示值
 * @constructor
 */
function IsWinFormatter(value,row,index){
    return value == '1' ? getColorTextSpan('是', '#CC3366') : '';
}
//调用方法 显示 值和颜色
function getColorTextSpan(text, color) {
    return "<span style='color:" + color + "'>" + text + "</span>";
}
/**
 * 计算授标
 */
function supplierWinAction() {
    parent.loadingOn('正在计算,请稍后…');
    $.ajax({
        url: "/priceformula/calculateIfWin",
        type: 'POST',
        dataType: "json",
        //data: data,
        success: function (responseStr) {
            var result = responseStr;
            if (result.success) {
                // $("#tb_formulas").bootstrapTable("refresh");
                $("#packageSuppliercol").bootstrapTable("refresh");

            } else {
                if(result.msg === null || result.msg === undefined){
                    parent.window.alertModal("计算失败");
                }else {
                    parent.window.alertModal(result.msg);
                }
            }
            parent.loadingOff();
        },
        error: function (responseStr) {
            console.log("error");
            parent.loadingOff();
            alert(1111);
        }
    });
}

/**
 * 导出授标结果
 */
function exportWinAction() {
    parent.loadingOn('正在导出,请稍后…');
    $.ajax({
        url: "/priceformula/exportWin",
        type: 'POST',
        dataType: "json",
        //data: data,
        success: function (responseStr) {
            console.log(responseStr)
            if (responseStr.filename===undefined){
                parent.window.alertModal('无有效数据生成导出文件');
                return
            }
            if (responseStr) {
                console.log(responseStr.filename)
                //$('#downloadFileLink').attr('href', '/priceformula/download?fileName=' + responseStr);
//                    window.open('/priceformula/download?fileName=' + responseStr.filename)
                window.location.target = "_blank"
                window.location.href='/priceformula/download?fileName=' + responseStr.filename
            } else {
                parent.window.alertModal('无有效数据生成导出文件');
            }
            parent.loadingOff();
        },
        error: function (responseStr) {
            console.log("error");
            parent.loadingOff();
        }
    });
}
