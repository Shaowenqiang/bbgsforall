var box = document.getElementById('box'), desc = document.getElementById('desc'),
    right = document.getElementById('right'), stepA = document.getElementById('stepA'),
    fixDiv = document.getElementById('fixDiv'), aWidth = right.clientWidth - 20,
    initTreeUrl = '../data/getBaseTree.json', step = 0, p3, cacheNode, timer,
    left = document.getElementById('left'),
    forEach = Array.prototype.forEach, _user = {};
// 获取登录信息
$.post("/login/queryLoginer", function (result) {
    if (result.state === "success") {
        if (result.msg !== null) {
            _user.roletype = result.msg.roletype
            $("#loginName").text(result.msg.realname)
        } else {
            window.location.href = "login.html"
        }
    }
    if (result.state === "error") {
        window.location.href = "login.html"
    }

})

// 登出功能
function loginOut() {
    $.post("/login/loginOut")
    window.location.href = "login.html"
}

var setting = {
    view: {
        dblClickExpand: false,
        showLine: false,
        showIcon: false
    },
    check: {
        enable: true
    },
    async: {
        enable: true,
        type: 'get',
        url: '../data/getBaseTree.json',
        autoParam: ['id'],
        dataFilter: null
    },
    data: {
        simpleData: {
            enable: true
        }
    },
    callback: {
        onClick: treeOnClick,
        onExpand: onExpand,
        onCollapse: onCollapse
    }
}

function onExpand(event, treeId, treeNode) {
    if ($('#' + treeNode.tId + '_a').hasClass('fixTree')) {
        $('#' + treeNode.tId + '_switch').addClass('fixTree')
    } else {
        fixDiv.classList.remove('fixDiv')
    }
}

function onCollapse(event, treeId, treeNode) {
    if ($('#' + treeNode.tId + '_a').hasClass('fixTree')) {
        $('#' + treeNode.tId + '_switch').addClass('fixTree')
    } else {
        fixDiv.classList.remove('fixDiv')
    }
}

function treeOnClick(event, treeId, treeNode) {
    if (cacheNode) {
        $('#' + cacheNode.tId + '_a').removeClass('fixTree')
        $('#' + cacheNode.tId + '_switch').removeClass('fixTree')
        $('#itemBtn-' + treeNode.tId).removeClass('fixTree')
    }
    var fixHeight = $('#' + treeNode.tId).offset().top
    fixDiv.classList.add('fixDiv')
    fixDiv.style.top = fixHeight + 'px'
    $('#' + treeNode.tId + '_a').addClass('fixTree')
    $('#' + treeNode.tId + '_switch').addClass('fixTree')
    $('#itemBtn-' + treeNode.tId).addClass('fixTree')
    cacheNode = treeNode
    if (treeNode.level === 0) {

    } else if (treeNode.level === 1) {
        // $("#infoTittle").css("visibility","hidden")
        // $("#rightInfoBtn").css("visibility","hidden")
        if (treeNode.name === "⑤ 招标基础数据输入、输出") {
            displayBatchName()
        } else {
            // $("#rightInfoBtn").css("visibility","hidden")
        }
        var options = {tabMainName: "myTab", tabContentMainName: "myTabContent", tabName: treeNode.id}
        options.tabUrl = treeNode.htmlUrl
        options.tabTitle = treeNode.name
        addTab(options)
    }
}

$.get(initTreeUrl, function (data) {
    $.fn.zTree.init($('#treeDemo'), setting, data)
})

function treeItemDropdown(e, el) {
    $('#treeItemMenu').dropdown()
}

// 显示当前批次名称
displayBatchName()

function displayBatchName() {
    $.post("/basicInfo/queryBatchName", function (result) {
        if (result.state === "success") {
            $("#infoTittle").text(result.batchName)
            // $("#infoTittle").css("visibility","visible")
            // $("#rightInfoBtn").css("visibility","visible")
        }
    })
}

// 删除当前批次所有数据
function delAllData() {
    $.post("/basicInfo/delAllData", function (result) {
        if (result.state === "success") {
            $("#infoTittle").text("")
            refreshBasicInfoTable()
            // window.frames["⑤ 招标基础数据输入、输出"].grayImportExcelBtn()
            location.reload()
        }
    })
}

/**
 * 显示清空数据按钮
 */
function displayDelDataBtn() {
    $("#rightInfoBtn").css("visibility", "visible")
}

/**
 * 投标人信息明细
 */
function bidderInfoDetailView() {
    var options = {tabMainName: "myTab", tabContentMainName: "myTabContent", tabName: "chaa"}
    options.tabUrl = "chaa.html"
    options.tabTitle = "供应商信息"
    addTab(options)
}

function loadingOn(info) {
    if (info && info.length > 0) {
        desc.innerHTML = info
    }
    box.style.display = 'flex'
}

function loadingOff() {
    box.style.display = 'none'
}

function guide(cards, wrapper) {
    localStorage.setItem('firstTime', 'no')
    const el = cards[0]
    const heardHeight = 103
    const cardHeight = parseInt(el.getBoundingClientRect().top)
    stepA.style.width = `${aWidth}px`
    stepA.style.top = `${cardHeight + heardHeight - 18}px`
    setTimeout(() => stepA.classList.add('draw'), 500)

    let enjoyhint_instance = new EnjoyHint({});

    const enjoyhint_script_steps = [
        {
            'next #stepA': '浏览系统说明，了解更多有价值的内容',
            'nextButton': {className: "myNext", text: "可以"},
            'skipButton': {className: "mySkip", text: "不了!"}
        },
        {
            'click #stepA': '你可以在这里看到系统的主要功能',
            'showSkip': false
        },
        {
            'next #left': '在『功能菜单』中点击你要进行的操作',
            'nextButton': {className: "myNext", text: "知道啦"},
            'showSkip': false
        }
    ]

    enjoyhint_instance.set(enjoyhint_script_steps);
    enjoyhint_instance.run();

    $('.enjoyhint_next_btn').on('click', function () {
        switch (step) {
            case 0:
                p3 = el.parentNode.parentNode.parentNode.clientWidth
                stepA.style.visibility = 'hidden'
                el.parentNode.classList.add('card-up')
                wrapper.style.transform = `translate3d(-${p3 * 2 + 60}px, 0px, 0px)`
                wrapper.style.transitionDuration = `6000ms`
                wrapper.style.transitionTimingFunction = `linear`
                let times = 0
                timer = setInterval(() => {
                    cards[times].parentNode.classList.remove('card-up')
                    if (times < 5)
                        cards[times + 1].parentNode.classList.add('card-up')
                    times++
                }, 1000)
                setTimeout(() => {
                    clearInterval(timer)
                    wrapper.style.transform = `translate3d(0px, 0px, 0px)`
                    wrapper.style.transitionDuration = `800ms`
                    forEach.call(cards, (current) => {
                        current.parentNode.classList.remove('card-up')
                    })
                    $('#stepA').trigger('click')
                }, 6000)
                break
        }
        step++
    })

    $('.enjoyhint_close_btn').on('click', function () {
        switch (step) {
            case 0:
                stepA.style.visibility = 'hidden'
                break
            case 1:
                wrapper.style.transform = `translate3d(0px, 0px, 0px)`
                wrapper.style.transitionDuration = `800ms`
                clearInterval(timer)
                forEach.call(cards, (current) => {
                    current.parentNode.classList.remove('card-up')
                })
                break
        }
    })
    $('.enjoyhint_skip_btn').on('click', function () {
        stepA.style.visibility = 'hidden'
    })
}
/**
 * 用户手册下载
 */
function userHelper() {
    window.location.target = '_blank'
    window.location.href = '/page/bbgs/word/招标智能评分系统用户使用手册.doc'
}