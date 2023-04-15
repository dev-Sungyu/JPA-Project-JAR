HTMLCollection.prototype.forEach = Array.prototype.forEach;
const banner = document.querySelector("div.banner-image-wrap");
const $bannerIndex = $(".banner-index");
const $bannerCount = $(".banner-count");

// 관리자페이지쪽에서 넣은 이미지 가져와서 배열로 만들기
var bannerImages = [
    "https://cdn.class101.net/images/69ac96c1-9d60-4316-9f1b-79311e667b78/750xauto.webp",
    "https://cdn.class101.net/images/0e9ed883-375e-49ef-bdc2-e3b1839fb336/750xauto.webp",
    "https://cdn.class101.net/images/f7f9c5e8-50b4-4d77-9007-93bec85aa52d/750xauto.webp"
];

$bannerCount.text("0"+bannerImages.length);

// 이미지의 전체 width
const totalWidth = (bannerImages.length+2)* 676;
$(banner).css("width",`${totalWidth}px`);  

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


let checkArrow = false;
let count = 1;
let index = 1;
let auto = setInterval(autoSlide, 5000);

banner.style.transform = `translate(-676px)`;


function autoSlide(){
    banner.style.transition = "transform 1s";
    banner.style.transform = `translate(${-676 * ++count}px)`;
    index = count;
    if(count == bannerImages.length + 1){
        index = 1;
    }
    $bannerIndex.text("0" + index);
    if(count == bannerImages.length + 1) {
        count = 1;
        setTimeout(function(){
            banner.style.transition = "transform 0s";
            banner.style.transform = "translate(-676px)";
        }, 1000);
    }
}

// prev.addEventListener("click", function(){
//     if(checkArrow){return;}
//     checkArrow = true;
//     clearInterval(auto);
//     banner.style.transition = "transform 0.7s";
//     banner.style.transform = `translate(${-676 * --count}px)`;
//     if(count == 0) {
//         count = 2;
//         setTimeout(function(){
//             banner.style.transition = "transform 0.7s";
//             banner.style.transform = `translate(${-676 * (imageDiv.length)}px)`;
//         }, 300);
//     }
//     auto = setInterval(autoSlide, 5000);
//     setTimeout(()=>{checkArrow = false}, 300);
// });

// next.addEventListener("click", function(){
//     if(checkArrow){return;}
//     checkArrow = true;
//     clearInterval(auto);
//     banner.style.transition = "transform 0.7s";
//     banner.style.transform = `translate(${-676 * ++count}px)`;
//     if(count == 3) {
//         count = 1;
//         setTimeout(function(){
//             banner.style.transition = "transform 0.7";
//             banner.style.transform = "translate(-676px)";
//         }, 300);
//     }
//     auto = setInterval(autoSlide, 5000);
//     setTimeout(()=>{checkArrow = false}, 300);
// });
