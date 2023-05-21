<script>
let userPassword = $("input[name=password]");
let userConfirmPassword = $("input[name=confirmPassword]");

// 비밀번호 눈표시 JS
$(".password_span").click(function(){
    if(userPassword.attr("type") == "password"){
        $("#eye_close").css('display', 'none');
        $("#eye_open").css('display', 'block');
        userPassword.attr("type","text");
    }else if(userPassword.attr("type") == "text"){
        $("#eye_close").css('display', 'block');
        $("#eye_open").css('display', 'none');
        userPassword.attr("type","password");
    }
});


$(".confirm_span").click(function(){
    if(userConfirmPassword.attr("type") == "password"){
        $("#eye_close_confirm").css('display', 'none');
        $("#eye_open_confirm").css('display', 'block');
        userConfirmPassword.attr("type","text");
    }else if(userConfirmPassword.attr("type") == "text"){
        $("#eye_close_confirm").css('display', 'block');
        $("#eye_open_confirm").css('display', 'none');
        userConfirmPassword.attr("type","password");
    }
});


let passwordInput = $("input[name=password]");
let passwordErrorMsg = $("#red_password");
let confirmPasswordInput = $("input[name=confirmPassword]");
let confirmPasswordErrorMsg = $("#red_confirm_password");

passwordInput.on("input", function() {
    let password = passwordInput.val().trim();
    let passwordRegex = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#$%^&*()_+\-=[\]{};':"\\|,.<>/?]).{8,32}$/;
    let passwordContinuity = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*()_+\-=[\]{};':"\\|,.<>/?]).{8,32}(?!.*([0-9a-zA-Z])\1\1)/;

    if (password === "") {
        passwordErrorMsg.text("비밀번호를 입력해주세요.").show();
        passwordInput.addClass("error-input");
    } else if (!passwordRegex.test(password)) {
        passwordErrorMsg.text("비밀번호는 영문 대소문자, 숫자, 특수문자 중 2가지 이상 조합된 8자 ~ 32자만 가능합니다.").show();
        $("input[name='password']").css('border-color', 'rgb(255, 62, 85)');
        passwordInput.addClass("error-input");
    } else if (!passwordContinuity.test(password)) {
        passwordErrorMsg.text("3회 이상 연속되거나 동일한 숫자, 문자 등 타인이 쉽게 알아낼 수 있는 비밀번호를 사용할 수 없습니다.").show();
        $("input[name='password']").css('border-color', 'rgb(255, 62, 85)');
        passwordInput.addClass("error-input");
    } else {
        passwordErrorMsg.hide();
        passwordInput.removeClass("error-input");
        $("#red_password").css('display', 'none');
        $("input[name='password']").css('border-color', 'rgba(0, 0, 0, 0.15)');
    }
});

confirmPasswordInput.on("input", function() {
    let password = passwordInput.val().trim();
    let confirmPassword = confirmPasswordInput.val().trim();

    if (password === confirmPassword) {
        confirmPasswordErrorMsg.hide();
        confirmPasswordInput.removeClass("error-input");
        $("input[name='confirmPassword']").css('border-color', 'rgba(0, 0, 0, 0.15)');
    } else {
        $("input[name='confirmPassword']").css('border-color', 'rgb(255, 62, 85)');
        confirmPasswordErrorMsg.text("비밀번호가 일치하지 않습니다.").show();
        confirmPasswordInput.addClass("error-input");
    }
});

const input = document.querySelector('input[name="password"]');
const button = document.querySelector('button[type="submit"]');
// input 값이 변경될 때마다 이벤트 리스너 추가
input.addEventListener('input', function() {
    // input 값이 존재하는 경우, 버튼에 클래스 추가
    if (input.value) {
        button.classList.add('button_change');
    }
    // input 값이 없는 경우, 버튼에 클래스 제거
    else {
        button.classList.remove('button_change');
    }
});

