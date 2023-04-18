$(".toggle-box").click(() => {
    if($(".accept").is(':checked')){
        $(".toggle-box").removeClass("toggle-active");
    }else {
        $(".toggle-box").addClass("toggle-active");
    }
});


//  이름 검사
const $nameInput = $("input[name=name]");
let nameCheck = false;

$nameInput.blur(() => {
    if(!$nameInput.val()) {
        nameCheck = false;
    }else {
        nameCheck = true;
    }
    check();
});

// 닉네임 검사
const $nickNameInput = $("input[name=nickName]");
let nickNameCheck = false;

$nickNameInput.blur(() => {
    if(!$nickNameInput.val()){
        nickNameCheck = false;

    }else {
        nickNameCheck = true;

    }
    check();
});

// 핸드폰 모달 관련 js
$(".phone-number-modify-button").click(() => {
    $(".phone-modal-display").show();
});

$(".exit").each((i, e) => {
    $(e).click(() => {
        $(".phone-modal-display").hide();
        $sendAuthButton.attr("disabled", true);
        $phoneNumberInput.attr("disabled", false);
        $phoneErrorMsg.text("");
        $phoneNumberInput.val(""); 
        $(".auth-box").hide();
        $("auth-msg").text("");
        $codeInput.val("");
    });
});

// 핸드폰 검사
const $phoneErrorMsg = $(".phone-number-error");
const regPhone = /^010([0|1|6|7|8|9])?([0-9]{4})?([0-9]{4})$/;
const $phoneNumberInput = $("input[name='phoneNumber']");
const $sendAuthButton = $(".send-auth-button");

$phoneNumberInput.blur(() => {
    if(!$phoneNumberInput.val()){
        $sendAuthButton.attr("disabled", true);
        $phoneErrorMsg.text("핸드폰 번호를 입력해주세요.");

    } else if(!regPhone.test($phoneNumberInput.val())){
        $sendAuthButton.attr("disabled", true);
        $phoneErrorMsg.text("형식에 맞게 입력해주세요.");

    } else {
        $sendAuthButton.attr("disabled", false);
        $phoneErrorMsg.text("");  
    }
});

$sendAuthButton.click(() => {
    $(".auth-box").show();
    $sendAuthButton.attr("disabled", true);
    $phoneNumberInput.attr("disabled", true);
    $(".auth-msg").text("인증번호가 발송되었습니다.");
});

// 인증번호 검사
const $codeInput = $("input[name=code]");
const $codeCheckButton = $(".auth-check-button");4
// 보낸 인증번호 
let code;

$codeInput.blur(() => {

    if(!$codeInput.val()){
        $codeCheckButton.attr("disabled", true);
        $(".auth-msg").css("color","red");
        $(".auth-msg").text("인증번호를 입력해주세요.");
    }else {
        $(".auth-msg").text("");
        $(".auth-msg").css("color","#36f");
        $codeInput.attr("disabled", false);
        $codeCheckButton.attr("disabled", false);
    }
});

$codeCheckButton.click(() => {

    if($codeInput.val() == code){
        $(".phone-number-change-button").attr("disabled", false);
    }
});

function check(){
    if(nameCheck && nickNameCheck) {
        $(".modify-button").attr("disabled", false);
    }else {
        $(".modify-button").attr("disabled", true);
    }
}


// 회원탈퇴 모달
const $withdrawModal = $(".withdraw-open");
$withdrawModal.click(() => {
    $(".withdraw-display").show();
});

$(".exit-withdraw").each((i, e) => {
    $(e).click(() => {
        $(".withdraw-display").hide(); 
        $(".reson-textarea").val("");
    });
});