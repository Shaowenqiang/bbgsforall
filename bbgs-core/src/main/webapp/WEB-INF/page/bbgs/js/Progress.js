let progressBar = document.querySelectorAll('.progress-btn-wrapper')
let progresses = document.querySelectorAll('.progress-color')
let percent = document.querySelectorAll('.percent')
let dragImage = document.getElementById('dragImage')
let jsProgress = document.getElementById('js-progress')
let swProgress = document.getElementById('sw-progress')
let jgProgress = document.getElementById('jg-progress')
let jsPercent = document.getElementById('js-percent')
let swPercent = document.getElementById('sw-percent')
let jgPercent = document.getElementById('jg-percent')
let jsBar = document.getElementById('js')
let swBar = document.getElementById('sw')
let jgBar = document.getElementById('jg')
let touch = {}
let forEach = Array.prototype.forEach;
let barMap = new Map();
let timeoutId =0
barMap.set('jsBar', {
	name: '技术',
	passiveName: '商务',
	activeBar: jsBar,
	activeProgress: jsProgress,
	activePercent: jsPercent,
	passiveBar: swBar,
	passiveProgress: swProgress,
	passivePercent: swPercent,
	referBar: jgBar,
	referProgress: jgProgress,
	referPercent: jgPercent
}).set('swBar', {
	name: '商务',
	passiveName: '价格',
	activeBar: swBar,
	activeProgress: swProgress,
	activePercent: swPercent,
	passiveBar: jgBar,
	passiveProgress: jgProgress,
	passivePercent: jgPercent,
	referBar: jsBar,
	referProgress: jsProgress,
	referPercent: jsPercent
}).set('jgBar', {
	name: '价格',
	passiveName: '商务',
	activeBar: swBar,
	activeProgress: jgProgress,
	activePercent: jgPercent,
	passiveBar: swBar,
	passiveProgress: swProgress,
	passivePercent: swPercent,
	referBar: jsBar,
	referProgress: jsProgress,
	referPercent: jsPercent
})
const mainLong = document.getElementById('main-progress').clientWidth
for (let i = 0; i < progressBar.length - 1; i++) {
	progressBar[i].addEventListener('dragstart', function (e) {
		this.classList.add('grabbing')
		e.dataTransfer.effectAllowed = "move";
		e.dataTransfer.setDragImage(dragImage, 0, 0);
		const currentBar = this
		touch[currentBar.id] = {}
		touch[currentBar.id].initiated = true
		touch[currentBar.id].startX = e.pageX
		touch[currentBar.id].left = document.getElementById(currentBar.id + '-progress').clientWidth
	}, true)
	progressBar[i].addEventListener('drag', function (e) {
		// e.preventDefault();
		const currentBar = this
		if (touch[currentBar.id] === undefined || touch[currentBar.id].initiated !== true) {
			return
		}
		const deltaX = e.pageX - touch[currentBar.id].startX
		let limit;
		if (currentBar.id === 'js') limit = percentToFloat(swBar.style.left)
		else limit = parseInt(mainLong - percentToFloat(jsBar.style.left))
		const offsetWidth = Math.min(limit, Math.max(0, touch[currentBar.id].left + deltaX))
		_offset(offsetWidth, currentBar)
	}, false)
	progressBar[i].addEventListener('dragend', function (e) {
		this.classList.remove('grabbing')
		e.preventDefault();
		const currentBar = this
		touch[currentBar.id].initiated = false
	}, false)
	progressBar[i].addEventListener("dragover", function (event) {
		event.preventDefault();
	}, false)
}

forEach.call(progresses, function (el) {
	el.addEventListener('click', function (e) {
		const currentProgress = this
		const rect = el.getBoundingClientRect()
		let offsetWidth
		if (el.id === 'jg-progress') offsetWidth = rect.right - e.pageX
		else offsetWidth = e.pageX - rect.left
		_offset(offsetWidth, currentProgress)
	})
})

forEach.call(percent, function (el) {
	el.addEventListener('click', function (e) {
		const currentInput = el.children[0]
		currentInput.addEventListener('keyup',function (e) {
			let self = this
			clearTimeout(timeoutId);
			timeoutId = setTimeout(function () {
				inputPercent(self.value,el)
			}, 400);
		},false)
	})
})

function _offset(offsetWidth, currentEl) {
	const percent = offsetWidth / mainLong * 100
	switchElement(currentEl, percent)
}

document.addEventListener("dragover", function (event) {
	event.preventDefault();
}, false)

function percentToFloat(percent) {
	return mainLong * parseFloat(percent) / 100
}

function switchElement(el, percent) {
	const currentID = el.id
	switch (currentID) {
		case 'js-progress':
			triggerPercent('jsBar', percent)
			break;
		case 'sw-progress':
			triggerPercent('swBar', percent)
			break;
		case 'jg-progress':
			triggerPercent('jgBar', percent)
			break;
		case 'js':
			triggerPercent('jsBar', percent)
			break;
		case 'sw':
			triggerPercent('swBar', percent)
			break;
	}
}
function inputPercent(percent, el) {
	const currentID = el.id
	let max, offsetWidth, jsProgressWith, level
	switch (currentID) {
		case 'js-percent':
			max = parseFloat(swBar.style.left)
			offsetWidth = Math.min(max, Math.max(0, percent * 1))
			jsProgress.style.width = `${offsetWidth}%`
			jsBar.style.left = `${offsetWidth}%`
			level = Math.round(parseFloat(swBar.style.left))
			if (offsetWidth >= level)
				jsPercent.children[0].value = `${offsetWidth}`
			swProgress.style.width = `${max - offsetWidth}%`
			swProgress.style.left = `${offsetWidth}%`
			swPercent.children[0].value = `${Math.round(max - offsetWidth)}`
			break;
		case 'sw-percent':
			max = 100 - parseFloat(jsProgress.style.width)
			offsetWidth = Math.min(max, Math.max(0, percent * 1))
			level = Math.round(max)
			if (offsetWidth >= level)
				swPercent.children[0].value = `${offsetWidth}`
			jsProgressWith = parseFloat(jsProgress.style.width)
			swProgress.style.width = `${offsetWidth}%`
			swBar.style.left = `${offsetWidth + jsProgressWith}%`
			jgProgress.style.left = `${offsetWidth + jsProgressWith}%`
			jgProgress.style.width = `${100 - offsetWidth - jsProgressWith}%`
            jgPercent.children[0].value = `${Math.round(100 - offsetWidth - jsProgressWith)}`
			break;
		case 'jg-percent':
			max = 100 - parseFloat(jsProgress.style.width)
			offsetWidth = Math.min(max, Math.max(0, percent * 1))
			level = Math.round(max)
			if (offsetWidth >= level)
				jgPercent.children[0].value = `${offsetWidth}`
			jgProgress.style.width = `${offsetWidth}%`
			jgProgress.style.left = `${100 - offsetWidth}%`
			swBar.style.left = `${100 - offsetWidth}%`
			swProgress.style.width = `${max - offsetWidth}%`
			swPercent.children[0].value = `${Math.round(max - offsetWidth)}`
			break;
	}
}

function triggerPercent(activeBar, percent) {
	const currentBarMap = barMap.get(activeBar)
	const referNum = parseFloat(currentBarMap.referProgress.style.width)
	currentBarMap.activeProgress.style.width = `${percent}%`
	if (activeBar.startsWith('js')) {
		currentBarMap.activeBar.style.left = `${percent}%`
		currentBarMap.passiveProgress.style.left = `${percent}%`
	} else if(activeBar === 'jgBar') {
		currentBarMap.activeBar.style.left = `${100 - percent}%`
		currentBarMap.activeProgress.style.left = `${100 - percent}%`
	} else {
		currentBarMap.activeBar.style.left = `${percent + referNum}%`
		currentBarMap.passiveProgress.style.left = `${percent + referNum}%`
	}
	currentBarMap.passiveProgress.style.width = `${100 - percent - referNum}%`
	const activeValue = Math.round(percent)
	currentBarMap.activePercent.children[0].value = `${activeValue}`
	currentBarMap.activeProgress.title = `${currentBarMap.name}` + ` ${activeValue}%`
	const passiveValue = Math.round(100 - activeValue - Math.round(referNum))
	currentBarMap.passivePercent.children[0].value = `${passiveValue}`
	currentBarMap.passiveProgress.title = `${currentBarMap.passiveName}` + ` ${passiveValue}%`
}