/**
 * Created by hq4 on 2017-10-15.
 */
/**
 * 获取专家组下拉
 */
$.get("/expert/queryGroupList", function (result) {
    for (var i = 0; i < result.length; i++) {
        var opt = '<option value="' + result[i].id + '">' + result[i].expertGroupName + '</option>'
        if (result[i].type === "技术") {
            $("#expertGroup_t").append(opt)
        }
        if (result[i].type === "商务") {
            $("#expertGroup_b").append(opt)
        }
    }
})

/**
 * 获取模板下拉
 * */
function clickType() {
    var templateType = $("#templateType").val().trim()
    if (templateType !== "") {
        $.get("/templet/listAll", {templateType: templateType}, function (result) {
            $("#templateName").text("")
            var opt = '<option value="">=== 请选择 ===</option>'
            $("#templateName").append(opt)
            for (var i = 0; i < result.length; i++) {
                var id = result[i].id
                var name = result[i].fileName
                opt = '<option value="' + id + '">' + name + '</option>'
                $("#templateName").append(opt)
            }
        })
    }
}
/**
 * 初始化表格数据
 */
resetZero()
function resetZero() {
    $("input").val("")
    $("select").val("")
    selectTable()
}
/**
 * 获取表格数据
 * */
function selectTable() {
    var data = {}
    data.flag = 'Y'
    if ($("#templateType").val() === "0") {
        data.technologyTemplate = $("#templateName").val()
    } else if ($("#templateType").val() === "1") {
        data.businessTemplate = $("#templateName").val()
    } else if ($("#templateType").val() === "2") {
        data.priceScoreTemplate = $("#templateName").val()
    } else if ($("#templateType").val() === "3") {
        data.technologyBidRecord = $("#templateName").val()
    } else if ($("#templateType").val() === "4") {
        data.businessBidRecord = $("#templateName").val()
    } else if ($("#templateType").val() === "5") {
        data.bidderList = $("#templateName").val()
    }

    data.bidAbbreviaion = $("#bidName").val()
    var technologyExpertGroup = $("#expertGroup_t option:selected").val()
    var businessExpertGroup = $("#expertGroup_b option:selected").val()
    data.technologyExpertGroup = technologyExpertGroup
    data.businessExpertGroup = businessExpertGroup
    $.post("/basicInfo/queryAllBidRecord", data, function (result) {
        // console.log(result)
        // JSON.stringify(result);
        // localStorage.setItem("allData", JSON.stringify(result));
        // JSON.parse(localStorage.getItem("allData"))
        $("#table").bootstrapTable('load', result)
    })
}
$(window).resize(function () {
    $('#table').bootstrapTable('resetView', {height: $(window).height() - 97});
})
//初始化Table
$('#table').bootstrapTable({
    method: 'get',                        //请求方式（*）
    striped: true,                        //是否显示行间隔色
    cache: false,                         //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
    clickToSelect: true,                //是否启用点击选中行
    height: $(window).height() - 97,
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
        width: "20px"
    }, {
        field: 'bidAbbreviaion',
        title: '<div style="width: 120px;">标段简称</div>',
        align: "center",
        width: "110px"
    }, {
        field: 'technologyTemplateChn',
        title: '技术专家评分表',
        align: "center",
        width: "110px"
    }, {
        field: 'businessTemplateChn',
        title: '商务专家评分表',
        align: "center",
        width: "110px"
    }, {
        field: 'priceScoreTemplateChn',
        title: '报价评分表',
        align: "center",
        width: "110px"
    },
        {
            field: 'technologyBidRecordChn',
            title: '技术专家阅标记录',
            align: "center",
            width: "110px"
        }, {
            field: 'businessBidRecordChn',
            title: '商务专家阅标记录',
            align: "center",
            width: "110px"
        }, {
            field: 'bidderListChn',
            title: '投标人清单',
            align: "center",
            width: "110px"
        }, {
            field: 'technologyExpertGroupName',
            title: '技术专家组',
            align: "center",
            width: "110px"
        }, {
            field: 'businessExpertGroupName',
            title: '商务专家组',
            align: "center",
            width: "110px"
        }, {
            field: 'technologyWeight',
            title: '技术权重',
            align: "center",
            width: "110px"
        }, {
            field: 'businessWeight',
            title: '商务权重',
            align: "center",
            width: "110px"
        }, {
            field: 'priceWeight',
            title: '价格权重',
            align: "center",
            width: "110px"
        }, {
            field: 'describeName',
            title: '<div style="width: 200px;">公式</div>',
            align: "center"
        }, {
            field: '',
            title: '操作',
            align: "center",
            formatter: function (value, row) {
                var id = row.id;
                return '<a style="cursor: pointer" onclick="deleteRow(\'' + id + '\')">删除</a>'
            }
        }]
});
function deleteRow(id) {
    parent.delBidDetailModal(id)
    // if (id !== null) {
        // $.post("/basicInfo/updateBidInfoFlag", {id: id}, function (result) {
        //     if (result.state === 'success') {
        //         selectTable()
        //         // filter()
        //         //刷新基础信息表格
        //         parent.window.refreshBasicInfoTable()
        //     }
        // })
    // }
}
/**
 * 刷新左侧表格
 */
function refreshTable() {
    selectTable()
}
/**
 * 查询过滤
 */
function filter() {
    selectTable()
}
//    $("#bidName").bind("propertychange", select());
function select() {
    var data = JSON.parse(localStorage.getItem("allData"))
    var data_new = []
    var str = $("#bidName").val().trim()
    var str2 = $("#templateName").val().trim()
    for (var i = 0; i < data.length; i++) {
        var arr = []
        var bidAbbreviaion = data[i].bidAbbreviaion
        var technologyTemplate = data[i].technologyTemplate
        var businessTemplate = data[i].businessTemplate
        var priceScoreTemplate = data[i].priceScoreTemplate
        var technologyBidRecord = data[i].technologyBidRecord
        var businessBidRecord = data[i].businessBidRecord
        var bidderList = data[i].bidderList

        arr.push(technologyTemplate)
        arr.push(businessTemplate)
        arr.push(priceScoreTemplate)
        arr.push(technologyBidRecord)
        arr.push(businessBidRecord)
        arr.push(bidderList)
        if (str2 !== '') {
            if ((bidAbbreviaion.indexOf(str) !== -1) && (arr.indexOf(str2) !== -1)) {
                data_new.push(data[i])
            }
        } else {
            if ((bidAbbreviaion.indexOf(str) !== -1)) {
                data_new.push(data[i])
            }
        }

    }

    $("#table").bootstrapTable("load", data_new)
}