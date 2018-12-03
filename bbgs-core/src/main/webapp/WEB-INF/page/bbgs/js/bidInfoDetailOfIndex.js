/**
 * Created by hq4 on 2017-10-31.
 */
function delBidDetail() {
    var id =localStorage.getItem("delBidDetailId");
    $.post("/basicInfo/updateBidInfoFlag", {id: id}, function (result) {
        if (result.state === 'success') {
            parent.alertSuccess("删除成功")
            $("iframe").each(function () {
                var name = $(this)[0].name
                if(name === "⑥ 唱标报价数据输入、输出"){
                    window.frames["⑥ 唱标报价数据输入、输出"].reloadBindInfoFrame()
                }
            })
            window.frames["标段模板信息明细"].selectTable()
        }else {
            parent.alertSuccess("删除失败")
        }
    })
}
function delBidDetailModal(id) {
    localStorage.setItem("delBidDetailId", id);
    $("#delBidDetail").modal()
}
