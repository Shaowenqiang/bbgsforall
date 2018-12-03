var swiper = new Swiper('.swiper-container', {
    slidesPerView: 4
});
var wrapper = document.getElementById('wrapper'), firstTime = localStorage.getItem('firstTime'),
    cards = document.querySelectorAll('.function-card-box')
if (firstTime === null)
    parent.guide(cards, wrapper)