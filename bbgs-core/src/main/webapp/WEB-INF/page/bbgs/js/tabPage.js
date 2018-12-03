/**
 * Created by hq4 on 2017-09-21.
 */
var height = window.height
$(function () {
    intoIndex()
})

/**
 * 增加标签页
 */
function addTab(options) {
    //option:
    //tabMainName:tab标签页所在的容器
    //tabName:当前tab的名称
    //tabTitle:当前tab的标题
    //tabUrl:当前tab所指向的URL地址
    // options.tabMainName = "myTab"
    // 下拉菜单区域的id
    options.tab_dropdown = "tabs_dropdown"
    // console.log("tabMaxHeight:"+ $(".rightInfo").width())
    // console.log(totalWidth()+options.tabTitle.length * 16 +42)
    // 控制显示条数 此js为根据宽度完成响应式变化 没用到
    // options.size = 9999999
    //是否存在TabTittle tabContent
    var exists = checkTabIsExists(options.tabMainName, options.tabName);
    $("#myTab .tab_li a").each(function () {
        $(this).removeClass("bgColor_tab")
    })
    // var li_size = $("#"+options.tabMainName+">.tab_li").length
    if (exists) {
        $("#tab_a_" + options.tabName).click();
        $("#tab_a_" + options.tabName).addClass("bgColor_tab")
    } else {
        //在tabTittle区加入Tab便签
        $("#" + options.tabMainName).append('<li onclick="tab_li_click()" class="tab_li" title="' + options.tabTitle + '"  id="tab_li_' + options.tabName + '"><a  href="#tab_content_' + options.tabName + '" data-toggle="tab" id="tab_a_' + options.tabName + '"><span class="tabTitleText">' + options.tabTitle + '</span><span class="close closeTab" type="button" onclick="closeTab(this);">&nbsp;×</span></a></li>');
        //固定TAB中substring_index(BID_ABBREVIAION,'包',-1)高度
        var mainHeight = $(document.body).height() - 101;
        var content = '';
        if (options.content) {
            content = options.content;
        } else {
            content = '<iframe  name="' + options.tabTitle + '"  class="iframeHeight" src="' + options.tabUrl + '" width="100%"  frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="yes" allowtransparency="yes"></iframe>';
        }
        $("#" + options.tabContentMainName).append('<div id="tab_content_' + options.tabName + '" role="tabpanel" class="tab-pane" id="' + options.tabName + '">' + content + '</div>');
        $("#tab_a_" + options.tabName).click();
        $("#tab_a_" + options.tabName).addClass("bgColor_tab")
    }
    //是否存在 dropdown历史记录中
    var exists2 = checkTabIsExists2(options.tab_dropdown, options.tabName)
    if (exists2) {
        // //关闭下拉区
        $("#tab_dropdown_" + options.tabName).remove();
        if (options.tabTitle !== "首页") {
            $("#" + options.tab_dropdown).append(' <li class="dorpdown_item" tabName="' + options.tabName + '" tabUrl="' + options.tabUrl + '"  tabTittle="' + options.tabTitle + '" onclick="clickTab_a(this)" id="tab_dropdown_' + options.tabName + '"><a tabindex="-1" data-toggle="tab" >' + options.tabTitle + '</a></li>');
        }
    } else {
        if (options.tabTitle !== "首页") {
            $("#" + options.tab_dropdown).append(' <li class="dorpdown_item" tabName="' + options.tabName + '" tabUrl="' + options.tabUrl + '"  tabTittle="' + options.tabTitle + '" onclick="clickTab_a(this)" id="tab_dropdown_' + options.tabName + '"><a tabindex="-1" data-toggle="tab" >' + options.tabTitle + '</a></li>');
        }
    }

    //加入此tab页后总宽度 如果小于总宽度 关闭第一个tab页 在进行比较
    //剩余宽度
    checkWidth()
    /**
     * 检查宽度是否足够
     */
    function checkWidth() {
        // var width = $(".rightInfo").width() - (totalWidth()+options.tabTitle.length * 16 +42 )
        // var width = $(".rightInfo").width() - totalWidth()
        var width = $(window).width() - 300 - totalWidth()
        // console.log(width)
        if (width < 30) {
            //触发关闭按钮
            $("#myTab .tab_li:first>a>.closeTab").click()
            checkWidth()
        }
    }

    $("#tab_a_" + options.tabName).click();
    $("#tab_a_" + options.tabName).addClass("bgColor_tab")
}
/**
 * 判断是否存在指定的标签页
 */
function checkTabIsExists(tabMainName, tabName) {
    var tab = $("#" + tabMainName + " > #tab_li_" + tabName);
    return tab.length > 0;
}
/**
 * 判断是否存在指定的dropdown记录
 */
function checkTabIsExists2(tab_dropdown, tabName) {
    var tab = $("#" + tab_dropdown + " > #tab_dropdown_" + tabName);
    return tab.length > 0;
}
/**
 * 关闭标签页
 */
function closeTab(button) {
    //通过该button找到对应li标签的id
    var li_id = $(button).parent().parent().attr('id');
    //取出标准id
    var id = li_id.replace("tab_li_", "");
    //如果关闭的是当前激活的TAB，激活他的前一个TAB
    if ($("li.active").attr('id') === li_id) {
        // 激活前一个tab
        console.log($("#myTab>.tab_li").length)
        if ($("#myTab>.tab_li").length > 1) {
            if(!$("#myTab>.tab_li:first").hasClass("active")){
                $("li.active").prev().find("a").click();
            }else {
                $("li.active").next().find("a").click();
            }
        }
        // $("li.active").prev().find("a").click();

        //激活第一个
        // $(".tab_li:last").find("a").click();
    }
    //关闭TAB tabTittle
    $("#" + li_id).remove();
    // //关闭下拉区
    // $("#tab_dropdown_"+id).remove();
    //关闭内容区
    $("#tab_content_" + id).remove();
    //关闭所有Tab页后 打开首页
    if ($("#myTab > .tab_li").length === 0) {
        intoIndex()
    }
}

/**
 * 主页
 *
 *
 **/
function intoIndex() {
    var options = {tabMainName: "myTab", tabContentMainName: "myTabContent", tabName: "mainHtml"}
    options.tabUrl = "guide.html"
    options.tabTitle = "首页"
    addTab(options)
}
/**
 * 获取当前tab_li 的 宽度总和
 *
 */
function totalWidth() {
    var width = 0
    $("#myTab>.tab_li").each(function () {
        width = width + $(this).width()
    })
    return width
}
/**
 * 关闭所有Tab页 保留主页
 **/
function closeAllTab() {
    //移除所有TabTitle 清空内容区
    $("#myTab>.tab_li>a>.closeTab").each(function () {
        $(this).click()
    })
    $(".dorpdown_item").each(function () {
        $(this).remove()
    })
}
/**
 * 点击 Tab_a
 */
function clickTab_a(obj) {
    var tabTittle = $(obj).attr("tabTittle")
    var tabUrl = $(obj).attr("tabUrl")
    var tabName = $(obj).attr("tabName")
    console.log(tabName)
    var options = {tabMainName: "myTab", tabContentMainName: "myTabContent"}
    options.tabName = tabName
    options.tabUrl = tabUrl
    options.tabTitle = tabTittle
    addTab(options)
}
/**
 * 点击Tab_li
 */
function tab_li_click() {
    $("#myTab .tab_li a").each(function () {
        $(this).removeClass("bgColor_tab")
    })
}
