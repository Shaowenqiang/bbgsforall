/**
 * 查询物资类别
 */
queryType()
function queryType(){
    $.post("/basicInfo/queryBatchName", function (result) {
        if (result.state === "success") {
            if (result.category === 'Y' || result.category === 'E') {
                $("input[name=category]:first").click();
                /**
                 * 查询公式列表
                 */
                $.get("/page/bbgs/json/formula/priceFormula.json", function (result) {
                    console.log(result)
                    for (var i = 0; i < result.length; i++) {
                        var opt = '<option onclick="displayM()" value="' + result[i].formulafun + '">' + result[i].descript + '</option>'
                        if("【物资】最低评标价法"===result[i].descript){
                            otp = '<option onclick="hiddenM()" value="' + result[i].formulafun + '">' + result[i].descript + '</option>'
                        }
                        if (result[i].descript.indexOf("【物资】") > -1) {
                            $("#priceFormula").append(opt)

                        }
                    }

                });
            }
            if (result.category === 'N') {
                $("input[name=category]:last").click();
                /**
                 * 查询公式列表
                 */
                $.get("/page/bbgs/json/formula/priceFormula.json", function (result) {
                    console.log(result)
                    for (var i = 0; i < result.length; i++) {
                        var opt = '<option value="' + result[i].formulafun + '">' + result[i].descript + '</option>'
                        if (result[i].descript.indexOf("【服务】") > -1) {
                            $("#priceFormula").append(opt)
                        }
                    }

                });
            }
            if (result.category === 'J') {
                $("input[name=category]:last").click();
                /**
                 * 查询公式列表
                 */
                $.get("/page/bbgs/json/formula/priceFormula.json", function (result) {
                    console.log(result)
                    for (var i = 0; i < result.length; i++) {
                        var opt = '<option value="' + result[i].formulafun + '">' + result[i].descript + '</option>'
                        if (result[i].descript.indexOf("【服务竞谈】") > -1) {
                            $("#priceFormula").append(opt)
                        }
                    }

                });
            }
        }
    })
}


/**
 * 查询模板列表
 */
$.get("/templet/listAll", function (result) {
    for (var i = 0; i < result.length; i++) {
        var id = result[i].id
        var name = result[i].fileName
        var type = result[i].templateType
        var isDefault = result[i].isDefault
        var opt = '<option value="' + id + '">' + name + '</option>'
        if (type === "0") {
            $("#technologyTemplate").append(opt)
            if (isDefault === 'Y') {
                $("#technologyTemplate").val(id)
            }
        } else if (type === "1") {
            //商务组专家评分表
            $("#businessTemplate").append(opt)
            if (isDefault === 'Y') {
                $("#businessTemplate").val(id)
            }
        } else if (type === "2") {
            //报价计算得分表
            $("#priceScoreTemplate").append(opt)
            if (isDefault === 'Y') {
                $("#priceScoreTemplate").val(id)
            }
        } else if (type === "3") {
            //技术专家组阅标记录
            $("#technologyBidRecord").append(opt)
            if (isDefault === 'Y') {
                $("#technologyBidRecord").val(id)
            }
        } else if (type === "4") {
            //商务专家组阅标记录
            $("#businessBidRecord").append(opt)
            if (isDefault === 'Y') {
                $("#businessBidRecord").val(id)
            }
        } else if (type === "5") {
            //投标人清单
            $("#bidderList").append(opt)
            if (isDefault === 'Y') {
                $("#bidderList").val(id)
            }
        }
    }
})

/**
 * 查询专家组列表
 */
$.get("/expert/queryGroupList", function (result) {
    for (var i = 0; i < result.length; i++) {
        var opt = '<option value="' + result[i].id + '">' + result[i].expertGroupName + '</option>'
        if (result[i].type === "技术") {
            $("#technologyExpertGroup").append(opt)
            if(result[i].expertGroupName==="技术一组"){
                $("#technologyExpertGroup").val(result[i].id)
            }
        }
        if (result[i].type === "商务") {
            $("#businessExpertGroup").append(opt)
            if(result[i].expertGroupName==="商务一组"){
                $("#businessExpertGroup").val(result[i].id)
            }
        }
    }
})
/**
 *
 * 查询权重公式
 */
$.get("/percent/queryAll", function (result) {
    for (var i = 0; i < result.length; i++) {
        var opt = '<option value="' + result[i].weightName + '">' + result[i].weightName + '</option>'
        $("#priceWeight").append(opt)
        if (result[i].isDefault === 'Y') {
            $("#priceWeight").val(result[i].weightName)
        }
    }
})
//初始化Table
initTable_left()
function initTable_left() {
    $.post("/basicInfo/queryAllBid",function (result) {
        $("#table_left").bootstrapTable('load',result)
        // $("#table_left .bs-checkbox:eq(1) input").click()
        localStorage.setItem("tableLeftData", JSON.stringify(result));
        if(result.length>0){
            // loadRightData(result[0].bid)
        }else {
            // loadRightData("")
        }
    })
}

/**
 * filter 
 */
function filterForLeft() {
    var text = $("#searchText").val()
    var data = JSON.parse(localStorage.getItem("tableLeftData"))
    var newData = []
    for (var i=0;i<data.length;i++){
        if(data[i].bid.indexOf(text)>-1){
            newData.push(data[i])
        }
    }
    if( text=null ||  text===""){
        $('#table_left').bootstrapTable('refresh');
    }
    $('#table_left').bootstrapTable('load', newData);
}
$('#table_left').bootstrapTable({
    // url: "/basicInfo/queryAllBid",          //请求后台的URL（*）
    method: 'get',                        //请求方式（*）
    striped: true,                        //是否显示行间隔色
    cache: false,                         //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
    // toolbarAlign:'left',
    // clickToSelect: true,                //是否启用点击选中行
    // singleSelect: true,
    // search:true,
    checkboxHeader: true,
    checkbox: true,
    height: $(window).height() - 150,
    onClickRow: function (row, e) {
        // loadRightData(row.bid)
    },
    onLoadSuccess: function () {
    },
    onDblClickCell: function (field, value, row, td) {
    },
    columns: [{
        field: 'checkbox',
        checkbox: true,
        align: "center",
        width: 20
    }, {
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
        width: 30
    }, {
        field: 'id',
        titLe: 'ID',
        visible: false
    }, {
        field: 'bid',
        title: '标段',
        width: 120,
        align: "center",
        formatter: function (value, row, index) {
            return '<div style="text-align: left">' + value + '</div>'
        }
    }]
});
function loadRightData() {
    //获取值
    var selectValueStr = [];
    $("#example-post option:selected").each(function () {
        selectValueStr.push($(this).val());
    });
    $.post("/basicInfo/queryPackageByBid_new",{bids:selectValueStr.join(',')},function (result) {
        console.log("table_right_data")
        console.log(result)
        console.log("table_right_data")
        $('#table_right').bootstrapTable('load',result)
        localStorage.setItem("tableRightData", JSON.stringify(result));
    })
}

/**
 * filter
 */
function filterForRight() {
    var text = $("#searchText_p").val()
    var data = JSON.parse(localStorage.getItem("tableRightData"))
    var newData = []
    for (var i=0;i<data.length;i++){
        if(data[i].bidAbbreviaion.indexOf(text)>-1){
            newData.push(data[i])
        }
    }
    if( text=null ||  text===""){
        $('#table_right').bootstrapTable('refresh');
    }
    $('#table_right').bootstrapTable('load', newData);
}
//初始化右侧Table
loadRightData()
$('#table_right').bootstrapTable({
    method: 'get',                        //请求方式（*）
    striped: true,                        //是否显示行间隔色
    cache: false,                         //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
    clickToSelect: true,                //是否启用点击选中行
    // singleSelect: true,
    checkboxHeader: true,
    checkbox: true,
    height: $(window).height() - 150,
    onClickRow: function (row, e) {
    },
    onDblClickCell: function (field, value, row, td) {
    },
    columns: [{
        field: 'checkbox',
        checkbox: true,
        align: "center",
        width: 20
    }, {
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
        width: 30
    }, {
        field: 'bidAbbreviaion',
        title: '标段',
        width: 120,
        align: "center",
        formatter: function (value, row, index) {
            value = value.substring(0,value.lastIndexOf("包"))
            return '<div style="text-align: left">' + value + '</div>'
        }
    },{
        field: 'bidAbbreviaion',
        title: '标段简称',
        width: 120,
        align: "center",
        formatter: function (value, row, index) {
            return '<div style="text-align: left">' + value + '</div>'
        }
    },{
        field: '',
        title: '绑定状态',
        width: 120,
        align: "center",
        formatter: function (value, row, index) {
            return '<div style="color: red;font-size: 14px;">' + '未绑定模板' + '</div>'
        }
    }]
});
$(".form-mine").height($(window).height() - 154)
$('#table_left').bootstrapTable({height: $(window).height() - 150})
$('#table_right').bootstrapTable({height: $(window).height() - 150})
$(window).resize(function () {
    $('#table_left').bootstrapTable('resetView', {height: $(window).height() - 150});
    $('#table_right').bootstrapTable('resetView', {height: $(window).height() - 150});
    $(".form-mine").height($(window).height() - 154)
})
function viewTemplateDetail() {
    parent.parent.viewTemplateDetail();
}
/**
 *  保存标段详细信息
 */
function saveBidInfo() {
    var ids = []
    // var arr1 = $("#table_left").bootstrapTable('getSelections');
    // for (var i=0;i<arr1.length;i++){
    //     ids.push(arr1[i].bid)
    // }
    var arr2 = $("#table_right").bootstrapTable('getSelections');
    for (var i=0;i<arr2.length;i++){
        ids.push(arr2[i].bidAbbreviaion)
    }
    if(ids.length===0){
        parent.parent.alertModal("请选择标段或包")
    }
    if ($("#technologyTemplate").val() === "") {
        parent.parent.alertModal("请选择技术组专家评分表！")
        return
    } else if ($("#businessTemplate").val() === "") {
        parent.parent.alertModal("请选择商务组专家评分表！")
        return
    } else if ($("#priceScoreTemplate").val() === "") {
        parent.parent.alertModal("请选择报价评分表！")
        return
    } else if ($("#technologyBidRecord").val() === "") {
        parent.parent.alertModal("请选择技术专家组阅标记录！")
        return
    } else if ($("#businessBidRecord").val() === "") {
        parent.parent.alertModal("请选择商务专家组阅标记录！")
        return
    } else if ($("#bidderList").val() === "") {
        parent.parent.alertModal("请选择投标人清单！")
        return
    } else if ($("#technologyExpertGroup").val() === "") {
        parent.parent.alertModal("请选择技术专家组！")
        return
    } else if ($("#businessExpertGroup").val() === "") {
        parent.parent.alertModal("请选择商务专家组！")
        return
    } else if ($("#priceWeight").val() === "") {
        parent.parent.alertModal("请选择权重！")
        return
    } else if ($("#priceFormula").val() === "") {
        parent.parent.alertModal("请选择价格公式！")
        return
    } else if ($("#m").val() === "") {
        parent.parent.alertModal("请选择公式参数（m）！")
        return
    }


    var weight = $("#priceWeight").val()
    var priceWeight = ""
    var businessWeight = ""
    var technologyWeight = ""
    var m = weight.split("，");
    for (var i = 0; i < m.length; i++) {
        if (m[i].indexOf("技术") === 0) {
            technologyWeight = m[i].replace("技术", "").replace("，", "").replace("：", "").replace("%", "") / 100
        }
        if (m[i].indexOf("商务") === 0) {
            businessWeight = m[i].replace("商务", "").replace("，", "").replace("：", "").replace("%", "") / 100
        }
        if (m[i].indexOf("价格") === 0) {
            priceWeight = m[i].replace("价格", "").replace("，", "").replace("：", "").replace("%", "") / 100
        }
    }
    technologyWeight = toDecimal2(technologyWeight)
    businessWeight = toDecimal2(businessWeight)
    priceWeight = toDecimal2(priceWeight)
    function toDecimal2(x) {
        var f = parseFloat(x);
        if (isNaN(f)) {
            return false;
        }
        var f = Math.round(x * 100) / 100;
        var s = f.toString();
        var rs = s.indexOf('.');
        if (rs < 0) {
            rs = s.length;
            s += '.';
        }
        while (s.length <= rs + 2) {
            s += '0';
        }
        return s;
    }

    var data = {
        technologyTemplate: $("#technologyTemplate").val(),
        businessTemplate: $("#businessTemplate").val(),
        priceScoreTemplate: $("#priceScoreTemplate").val(),
        technologyBidRecord: $("#technologyBidRecord").val(),
        businessBidRecord: $("#businessBidRecord").val(),
        bidderList: $("#bidderList").val(),
        businessExpertGroup: $("#businessExpertGroup").val(),
        technologyExpertGroup: $("#technologyExpertGroup").val(),
        businessWeight: businessWeight,
        technologyWeight: technologyWeight,
        priceWeight: priceWeight,
        priceFormula: $("#priceFormula").val(),
        m: $("#m").val(),
        ids: ids.toString()
    }
    if (ids.length > 0) {
        $.post("/basicInfo/saveBidInfos", data, function (result) {
            if (result.state === "success") {
                // initTable_left()
                loadRightData()
                //刷新模板信息表格
                viewState()
                parent.parent.alertSuccess("保存成功")
                parent.parent.refreshTempDetailTable()
            }
            if (result.state === "error") {
                if(result.msg!=null){
                    parent.parent.alertModal(result.msg)
                }else{
                    parent.parent.alertModal("保存失败")
                }
            }
        })
    }
}
/**
 * 刷新左侧表格
 */
function refreshLeftTable() {
    $("#table_left").bootstrapTable("refresh")
}

$("#bidderInfoDetailBtn").click(function () {
    parent.parent.bidderInfoDetailView();
})
/**
 * 导出专家组阅标记录
 */
function exportSencondExcle() {
    parent.parent.loadingOn("生成中...")
    $.post("/basicInfo/exportSencondExcle", function (result) {
        if (result.state === "success") {
            window.location.target = '_blank'
            window.location.href = '/page/bbgs/excel/专家组阅标记录.zip'
            parent.parent.loadingOff()
        }
        if (result.state === "error") {
            if(result.msg!=null){
                parent.parent.alertModal(result.msg)
            }else{
                parent.parent.alertModal("导出失败")
            }

        }
        parent.parent.loadingOff()
    })

}
/**
 * 导出按钮是否能使用
 */
viewState()
function viewState() {
    // $.post("/basicInfo/viewState", function (result) {
    //     if (result.state === "success") {
    //         $("#exportAll").removeAttr("disabled")
    //     }
    //     if (result.state === "error"){
    //         $("#exportAll").attr("disabled","disabled")
    //     }
    // })
}

/**
 * 公式参数隐藏
 */
$("#priceFormula").click(function () {
    if("formulaService@materials_priceFormula_2"===$("#priceFormula").val()){
        hiddenM()
    }else {
        displayM()
    }
})
function hiddenM() {
    $("#m_div").css("display","none")
}
function displayM() {
    $("#m_div").css("display","block")
}
