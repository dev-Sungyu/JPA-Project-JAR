const $contentPrev = $("#content-prev");
const $contentNext = $("#content-next");
const $firstContent = $(".first-content-slide");
const $firstContentAll = $(".first-content-box");
let checkCount = 1;

$contentNext.click(() => {

    $firstContent.css("transition", "transform 0.3s");
    $firstContent.css("transform", `translate(-${400 * checkCount++}px)`);

    $contentPrev.show();

    if(checkCount == $firstContentAll.length - 2) {
        $contentNext.hide();
    }
});

$contentPrev.click(() => {

    $firstContent.css("transition", "transform 0.3s");
    $firstContent.css("transform", `translate(-${400 * --checkCount}px)`); 
    $contentNext.show();
    
    if(checkCount == 0){
        $contentPrev.hide();
    }
});

const $secondPrev = $("#second-prev");
const $secondNext = $("#second-next");
const $secondContent = $(".second-content-slide");
const $secondContentAll = $(".content-image-box2");
let checkCount2 = 1;

$secondNext.click(() => {

    $secondContent.css("transition", "transform 0.3s");
    $secondContent.css("transform", `translate(-${300 * checkCount2++}px)`);

    $secondPrev.show();

    if(checkCount2 == $secondContentAll.length - 3) {
        $secondNext.hide();
    }
});

$secondPrev.click(() => {

    $secondContent.css("transition", "transform 0.3s");
    $secondContent.css("transform", `translate(-${300 * --checkCount2}px)`); 
    $secondNext.show();
    
    if(checkCount2 == 0){
        $secondPrev.hide();
    }
});


const $heartButton = $(".heart-layout");
const $noHeart = $(".no-heart");
const $heartUp = $(".heart-up");

$heartButton.each((i, e) => {

    $(e).click(() => {
        if($($noHeart[i]).hasClass("heart-active")){
            $($heartUp[i]).hide();
            $($noHeart[i]).show();
            $($noHeart[i]).removeClass("heart-active");
        }else {
            $($noHeart[i]).addClass("heart-active");
            $($heartUp[i]).show();
            $($noHeart[i]).hide();
        }
    });
});