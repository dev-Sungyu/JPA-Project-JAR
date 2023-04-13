const $searchBox = $(".search-div");
const $searchCancel = $(".search-cancel-button");

$searchBox.click(() => {
    $(".search-display").show();
});

$searchCancel.click(() => {
    $(".search-display").hide();
});

const $modalActive = $(".header-login-box");
const $arrowOn = $(".header-arrow-on");
const $arrowOff = $(".header-arrow-off");
const $arrowActive = $(".active-arrow");

$modalActive.click(() => {
    $arrowActive.show();
});

$(".header-profile-modal").click(() => {
    $arrowOff.hide();
    $arrowOn.show();
    $arrowActive.hide();
});

$arrowOn.click(() => {
    $arrowOn.hide();
    $arrowOff.show();
});

$(".background-dark").click(()=>{
    $(".search-display").hide();
});
