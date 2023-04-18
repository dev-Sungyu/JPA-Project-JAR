HTMLCollection.prototype.forEach = Array.prototype.forEach;
// ======================================================================================
const banner = document.querySelector("div.banner-image-wrap");
const $bannerIndex = $(".banner-index");
const $bannerCount = $(".banner-count");
const width = $(".banner-mobileChecking").width();

// 관리자페이지쪽에서 넣은 이미지 가져와서 배열로 만들기
var bannerImages = [
    "https://cdn.class101.net/images/0e9ed883-375e-49ef-bdc2-e3b1839fb336/750xauto.webp",
    "https://cdn.class101.net/images/f7f9c5e8-50b4-4d77-9007-93bec85aa52d/750xauto.webp",
    "https://cdn.class101.net/images/69ac96c1-9d60-4316-9f1b-79311e667b78/750xauto.webp",
];
$(".banner-back-image").attr("src",bannerImages[0]);

// 이미지의 전체 width
const totalWidth = (bannerImages.length+2)* width;
$(banner).css("width",`${totalWidth}px`);  

var bannerColor = [
    "#FF3E56",
    "rgb(51, 96, 255)",
    "rgb(0, 0, 0)",
]

$(".banner-back-image-box").css("background-color",bannerColor[0]);
$(".banner-position").css("background-color",bannerColor[0]);

var bannerMainText = [
    "내일배움카드로 클래스 무료수강하세요.",
    "평생교육바우처로 1년동안 무료수강하기",
    "LOVERS #1 <br> 이연이라는 잭팟",
]

$(".slide-main-text").html(bannerMainText[0]);

var bannerSubText = [
    "2023년 K-디지털 기초역량훈련 오픈",
    "평생교육 희망카드로 연간 구독 결제가 가능해요.",
    "'이연으로' 어디까지 갈 것인가?",
]

$(".slide-sub-text").text(bannerSubText[0]);
$bannerCount.text("0"+bannerImages.length);

// ==================================================================================

function bannerBackgroundColorAndText(count) {
    let $backgroundColor = $(".banner-back-image-box");
    let backgroundColor = bannerColor[count];

    let $bannerMainText = $(".slide-main-text");
    let $bannerSubText = $(".slide-sub-text");

    let $bannerBackImage = $(".banner-back-image");

    $backgroundColor.css("background-color", backgroundColor);
    $backgroundColor.css("transition","0.7s");

    $(".banner-position").css("background-color", backgroundColor);
    $(".banner-position").css("transition","0.7s");

    $bannerMainText.html(bannerMainText[count]);
    $bannerSubText.text(bannerSubText[count]);

    $bannerBackImage.attr("src",bannerImages[count]);
}

// 이미지 갯수만큼 태그 추가
bannerImages.forEach((e, i) => {
    let text = `
        <div class="banner-slide-box">
            <div class="banner-none-area">
                <div class="banner-slide-image-box">
                    <picture class="banner-slide-image">
                        <img src="${bannerImages[i]}">
                    </picture>
                </div>
            </div>
        </div>
    `;
    $(banner).append(text);
    if(i == bannerImages.length - 1){
        appendImg();
    }
});


// 앞뒤 이미지 추가
function appendImg(){
    let text = `
    <div class="banner-slide-box">
        <div class="banner-none-area">
            <div class="banner-slide-image-box">
                <picture class="banner-slide-image">
                    <img src="${bannerImages[bannerImages.length-1]}" class="first-image">
                </picture>
            </div>
        </div>
    </div>
    `;

    let text2 = `
    <div class="banner-slide-box">
        <div class="banner-none-area">
            <div class="banner-slide-image-box">
                <picture class="banner-slide-image">
                    <img src="${bannerImages[0]}">
                </picture>
            </div>
        </div>
    </div>
    `;
    $(banner).prepend(text);
    $(banner).append(text2);
    }

// =========================================================================
/* slide */
let checkArrow = false;
let count = 1;
let index = 1;
let auto = setInterval(autoSlide, 5000);
let $prev = $("#prev");
let $next = $("#next");

banner.style.transform = `translate(-${width}px)`;

function autoSlide(){
    banner.style.transition = "transform 1s";
    banner.style.transform = `translate(${-width * ++count}px)`;

    indexCheck(count);

    if(count == bannerImages.length + 1) {
        count = 1;
        setTimeout(function(){
            banner.style.transition = "transform 0s";
            banner.style.transform = `translate(-${width}px)`;
        }, 1000);
    }
}

$prev.click(function(){
    if(checkArrow){return;}
    checkArrow = true;
    clearInterval(auto);
    banner.style.transition = "transform 1s";
    banner.style.transform = `translate(${-width * --count}px)`;

    indexCheck(count);

    if(count == 0) {
        count = bannerImages.length;
        setTimeout(function(){
            banner.style.transition = "transform 0s";
            banner.style.transform = `translate(${-width * (bannerImages.length)}px)`;
        }, 1000);
    }

    auto = setInterval(autoSlide, 5000);
    setTimeout(()=>{checkArrow = false}, 1000);
});

$next.click(function(){
    if(checkArrow){return;}
    checkArrow = true;
    clearInterval(auto);
    banner.style.transition = "transform 0.7s";
    banner.style.transform = `translate(${-width * ++count}px)`;

    indexCheck(count);
    
    if(count == bannerImages.length + 1) {
        count = 1;
        setTimeout(function(){
            banner.style.transition = "transform 0s";
            banner.style.transform = `translate(-${width}px)`;
        }, 1000);
    }

    auto = setInterval(autoSlide, 5000);
    setTimeout(()=>{checkArrow = false}, 1000);
});


function indexCheck(count) {
    let index = count;

    if(count == 0){
        index = bannerImages.length;
    }

    if(count == bannerImages.length + 1){ 
        index = 1;
    }

    $bannerIndex.text("0" + index);
    bannerBackgroundColorAndText(index - 1);
}