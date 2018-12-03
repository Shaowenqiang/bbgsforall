/**
 * Created by hq4 on 2017-10-13.
 */


//信息导入
function infoImport() {
    var options = {tabMainName: "myTab", tabContentMainName: "myTabContent", tabName: "test_2"}
    options.tabUrl = "subFile.html"
    options.tabTitle = "信息导入"
    addTab(options)
}
/**
 * importingModal
 * 导入中Modal
 * */
function importingModal() {
    $("#importingModal").modal()
}
/**
 * importingModal
 * 关闭导入中Modal
 * */
function closeImportingModal() {
    $("#importingModal").modal('hide')
}


/**
 * 刷新招标基础数据输入、输出的左侧表格
 */
function refreshBasicInfoTable() {
    window.frames["⑤ 招标基础数据输入、输出"].refreshFrame()
}
