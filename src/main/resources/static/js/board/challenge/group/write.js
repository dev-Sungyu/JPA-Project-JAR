let fileDTOS = null;
let groupChallengeDTO = [[${groupChallengeDTO}]];
if (groupChallengeDTO == null || groupChallengeDTO == undefined) {
} else {
    fileDTOS = groupChallengeDTO.fileDTOS;
}

$(document).ready(() => {
    $("#header").load("../../main/header.html");
    $("#footer").load("../../main/footer.html");
});

let inputFiles = [];
let $imgUl = $($('ul.img_ul')[0]);
let $lists;

$(".file").change((e) => {
    let errorMsg = "확장자는 이미지 확장자만 가능합니다.";
    let index = 0;
    let files = e.target.files;
    let filesArr = Array.prototype.slice.call(files);

    inputFiles = [];

    filesArr.forEach((file) => {
        if (!file.type.match("image.*")) {
            alertModal(errorMsg);
            return;
        }
        inputFiles.push(file);

        let reader = new FileReader();
        $imgUl.empty();

        reader.onload = (e) => {
            if (index >= 3) {
                return;
            }
            let text = `
        <li class="img_list" id="li${index}">
          <div class="img_box_wrapper">
            <header class="delete_button_wrapper">
              <label id="label${index}" for="button${index}">
                <button icon-position="0" color="white" fill="false" type="button" class="pasing-button-1 pasing-no-select" id="button${index}">
                  <span class="pasing-button-span">
                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="none" viewBox="0 0 24 24">
                      <path d="M18.5 4L12 10.5 5.5 4 4 5.5l6.5 6.5L4 18.5 5.5 20l6.5-6.5 6.5 6.5 1.5-1.5-6.5-6.5L20 5.5 18.5 4z" fill="#cacaca"></path>
                    </svg>
                  </span>
                </button>
              </label>
            </header>
            <article class="img_wrapper">
              <div class="img_div">
                <img src="${e.target.result}" alt="" class="inserted_img">
              </div>
            </article>
          </div>
        </li>
      `;

            $imgUl.append(text);
            index++;
            $(".img_ul>li").on("click", (e) => {
                console.log($(e.currentTarget));
                console.log("딜리트 버튼 눌림");
                $(e.currentTarget).remove();
            });
        };
        reader.readAsDataURL(file);
    });
});

function alertModal(errorMsg) {
    $(".alert").text(errorMsg);
    $alertModal.fadeIn();
    setTimeout(function () {
        $alertModal.fadeOut();
    }, 2000);
}
