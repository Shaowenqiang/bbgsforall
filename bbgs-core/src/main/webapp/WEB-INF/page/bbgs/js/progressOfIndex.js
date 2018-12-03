/**
 * Created by hq4 on 2017-10-25.
 */
function deletePercentModal(id) {
    localStorage.setItem("percentid", id);
    $("#delWeightModal").modal()

}
function deletePercent() {
    $.post("/percent/delete",{id:localStorage.getItem("percentid")},function (result) {
        if(result.state === "success"){
           alertSuccess("删除成功")
            window.frames["③ 权重信息维护"].freshTable()
        }else{
            alertModal("删除失败")
        }
    })
}