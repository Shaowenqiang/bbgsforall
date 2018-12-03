/**
 * Created by HQSpring on 2017/10/30.
 */
function delExpertGroup(val){
    var arrs = val;

    $('#delBtn').html('<center><button id="delGroup" type="button" class="gw-l-button" data-dismiss="modal">确定</button>'
        + '<button id="cancelDel" type="button" style="margin-left: 10px;" class="gw-l-button" data-dismiss="modal">取消</button></center>');
    $('#optionDel').html("删除"+arrs[2]+"及所属信息吗？");
    $('#delNoticeModal').modal('show');

    $('#delGroup').click(
        function () {
            window.frames["② 专家信息维护"].freshGroupTable(arrs[1]);
        }
    )
}

function delExpert(val){
    var arrs = val;

    $('#delBtn').html('<center><button id="doDel" type="button" class="gw-l-button" data-dismiss="modal">确定</button>'
        + '<button id="cancelDel" type="button" style="margin-left: 10px;" class="gw-l-button" data-dismiss="modal">取消</button></center>');
    $('#optionDel').html('删除'+arrs[2]+'专家信息吗？');
    $('#delNoticeModal').modal('show');

    $('#doDel').click(
        function () {
            window.frames["② 专家信息维护"].freshExpertTable(arrs[1]);
        }
    )
}

function clearInput() {
    $('#optionSucc').html('保存成功！');
    $('#expertPromptSuccessModal').modal('show');
    $('#expertPromptSuccessModal').on("click", "#successKnown", function(){
        window.frames["② 专家信息维护"].getInFocus();
    } );
}

function expertRepeat(str) {
    $('#optionDel').html(str);
    $('#delNoticeModal').modal('show');
    $('#delNoticeModal').on("click", "button", function(){
        window.frames["② 专家信息维护"].getInFocus();
    } );
}
//邵文强添加开始=============================
function delAllExpertModal() {
    $("#delAllExpertModal").modal('show')
}
function delAllExpert() {
    window.frames["② 专家信息维护"].expertReload();
    $.post("/basicInfo/delAllExpert",function (result) {
        if(result.state === "success"){
            alertSuccess("删除成功")
            window.frames["② 专家信息维护"].reload();
        }else {
            alertModal(result.msg)
        }
    })
}
//邵文强添结束=============================