/**
 * 模板信息维护 js
 * Created by hq4 on 2017-10-13.
 */
$(window).resize(function () {
    // $('#table_left').bootstrapTable('resetView',{height: $(window).height()-269} );
})
//初始化Table
$("#tb_order").bootstrapTable({
    url: "/page/bbgs/data/templateType.json",          //请求后台的URL（*）
    method: 'get',                        //请求方式（*）
    striped: true,                        //是否显示行间隔色
    cache: false,                         //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
    clickToSelect: true,                //是否启用点击选中行
//        height: 475,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
    detailView: true,                   //是否显示父子表
    onLoadSuccess: function () {
        $('#tb_order').bootstrapTable('expandRow', 0);
    },
    onExpandRow: function (index, row, $detail) {
        initSubTable(index, row, $detail);
    },
    onCollapseRow: function (index, row) {
        return false;
    },
    columns: [{
        field: 'id',
        title: 'ID',
        visible: false,
        align: "center"
    }, {
        formatter: function (value, row, index) {
            return ++index;
        },
        title: '序号',
        align: "center",
        width: "5%"
    }, {
        field: 'name',
        title: '模板分类',
        width: "77%",
        align: "center",
        formatter: function (value, row, index) {
            return '<div style="text-align: left">'+value+'</div>';
        }
    }, {
        formatter: function (value, row, index) {
            var id = row.id
            return "<span><a class='headCss'  data-toggle='modal' onclick='addDemoModal(\"" + id + "\")'>新增</a></span>";
        },
        title: '操作',
        align: "center",
        width: "15%"
    }]
});
// 表格选中样式添加方法
$("#tb_order").on('click-row.bs.table', function (e, row, element) {
    $('.selected').removeClass('selected');// 去除之前选中的行的，选中样式
    $(element).addClass('selected');// 添加当前选中的 success样式用于区别
});
//隐藏列
$('#tb_order').bootstrapTable('hideColumn', 'ism');
//显示列
$('#tb_order').bootstrapTable('showColumn', 'ism');
var parentid = "";
function initSubTable(index, row, $detail) {
    //把其他子表隐藏
    var sizeNum = $('#tb_order').bootstrapTable('getData').length;
    for (var i = 0; i < sizeNum; i++) {
        if (index != i) {
            $('#tb_order').bootstrapTable('collapseRow', i);
        } else {
            $('#tb_order').bootstrapTable('expandRow', i);
        }
    }
    parentid = row.id;
    var cur_table = $detail.html('<div class="bootstrapTableSonCssDesign" style="margin: 10px 10px 10px 3px"><table  class="SubTable"  id="child_' + parentid + '"></table></div>').find('table');
    $(cur_table).bootstrapTable({
        url: "/templet/listAll",
        method: 'get',
        queryParams: {templateType: parentid},
        ajaxOptions: {strParentID: parentid},
        // height:$(window).height()-269,
        clickToSelect: true,
        detailView: false,
        uniqueId: "MENU_ID",
        pageSize: 10,
        pageList: [10, 25],
        columns: [{
            field: 'id',
            title: 'ID',
            visible: false,
            valign: 'middle',
            align: "center"
        }, {
            formatter: function (value, row, index) {
                return ++index;
            },
            title: '序号',
            valign: 'middle',
            align: "center",
            width: "5%"
        }, {
            field: 'fileName',
            title: '名称',
            valign: 'middle',
            align: "center",
            width: "40%"
        }, {
            field: 'uptime',
            title: '时间',
            align: 'middle',
            align: "center",
            width: "15%",
            formatter: function (value, row, index) {
                if (value !== null && value !== undefined) {
                    var time1 = value.toString().slice(0, 4);
                    var time2 = value.toString().slice(4, 6);
                    var time3 = value.toString().slice(6, 8);
                    var time = time1 + "-" + time2 + "-" + time3
                    return time
                }
                return ""
            }
        },
            {
                field: 'isDefault',
                title: '状态',
                valign: 'middle',
                align: "center",
                width: "10%",
                formatter: function (value, row, index) {
                    if (value === 'Y'){
                        return '<div class="circle_contain"><span class="circle_b"></span><span>默认</span></div>'
                    }
                    var id = row.id
                    return '<div class="circle_contain"><span class="circle_o"></span><a href="#" onclick="setDefault(\''+id+'\')">设为默认</a></div>'

                }
            },
            {
                formatter: function (value, row, index) {
                    var id = row.id
                    var realName = row.realName
//                        return "<span><a class='headCss' onclick='detailView(\"" + realName + "\")'>详情</a>/<a class='headCss' onclick='editDemoModal()'>修改</a>/<a class='headCss' onclick='deleteConfirm(\"" + id + "\")'>删除</a></span>";
                    return "<span><a class='headCss' onclick='detailView(\"" + id + "\")'>详情</a>&nbsp;/&nbsp;<a class='headCss' onclick='deleteConfirm(\"" + id + "\")'>删除</a></span>";
                },
                title: '操作',
                valign: 'middle',
                align: "center",
                width: "15%"
            }
        ]
    });
//        $('#tb_order').bootstrapTable('expandRow', 0);
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
//    查看子表格详情
function detailView(realName) {
    parent.window.detailView(realName);
}
// 确认删除
function deleteConfirm(str) {
    parent.window.deleteConfirm(str);
}
// 新增模板弹窗
function addDemoModal(parentid) {
    parent.window.addDemoModal(parentid);
}
// 修改模板弹窗
function editDemoModal() {
    parent.window.editDemoModal();
}
/**
 * 刷新某个子表格
 */
function refreshSubTable(tableId) {
    console.log(tableId)
    $("#child_" + tableId).bootstrapTable('refresh')
}
/**
 * 刷新所有子表格
 */
function refreshAllSubTable() {
    $(".SubTable").each(function () {
        $(this).bootstrapTable('refresh')
    })
}
/**
 * 设置为默认
 */
function setDefault(id,type){
    parent.window.setDefault(id,type)
}