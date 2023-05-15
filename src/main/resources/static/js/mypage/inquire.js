const $ul = $(".inquire-list-box");
let page = 0;

inquireService = (function () {
    function list(page, callback) {
        $.ajax({
            url: '/mypage/inquire-list',
            type: 'get',
            data: page,
            success: function (list) {
                if (callback) {
                    callback(list);
                    console.log(list);
                }
            }
        });
    }

    return {
        list: list,
    }
})();

getList(page, memberId);

$(".paging-layout").on("click", "a", function (e) {
    e.preventDefault();
    $ul.empty();
    const targetPage = $(this).text();

    if ($(this).hasClass("arrow")) {
        // 화살표 클릭 시 이전 페이지 또는 다음 페이지로 이동
        if ($(this).attr("id") === "prev") {
            page--;
        } else if ($(this).attr("id") === "next") {
            page++;
        }
    } else {
        // 페이지 번호 클릭 시 해당 페이지로 이동
        page = parseInt(targetPage) - 1;
    }

    getList(page, memberId);
});


function displayPagination(totalPages) {
    const $pagination = $(".paging-layout");
    $pagination.empty();

    if (page > 0) {
        $pagination.append("<a href='#' class='arrow' id='prev'><img class='prev'  src='/image/mypage/arrow.png'></a>");
    }

    for (let i = 1; i <= totalPages; i++) {
        if (i === page + 1) {
            // 현재 페이지를 텍스트로 표시
            $pagination.append(
                "<div class='paging-border-none paging-active'><span class='page'>" + i + "</span></div>"
            );
        } else {
            // 다른 페이지는 a 태그로 표시
            $pagination.append("<div class='paging-border-none'><a href='#' class='page'>" + i + "</a></div>");
        }
    }
    if (page < totalPages - 1) {
        $pagination.append("<a href='#' class='arrow' id='next'><img class='next' src='/image/mypage/arrow.png'></a>");
    }
}

function listText(list) {
    let inquireDTOS = list.content;
    console.log(inquireDTOS);
    let text = '';
    $(inquireDTOS).each((i, inquireDTO) => {
        var answertype = inquireDTO.answerType == 'ANSWER' ? '답변 완료' : '미답변';
        var createDate = getDate(inquireDTO.createDate);

        text += `
      <div class="inquire-list-layout">
        <div class="flex-between">
          <p class="date">${createDate}</p>
          <div>
            <button type="button" class="btn modify-btn">수정</button>
            <button type="button" class="btn delete-btn">삭제</button>
          </div>
        </div>
        <div class="inquire-title-box">
          <a href="javascript:void(0)">
            <h3>${inquireDTO.inquireTitle}</h3>
          </a>
          <div class="inquire-answer-status-box">
            <div class="inquire-answer-status-layout">
                <div class="inquire-answer-border">
                `;
        if(answertype == "답변 완료"){
            text += `
                   <p class="answer">${answertype}</p>
                   `;
        }else {
            text += `
                    <p class="answer-status">${answertype}</p>
                    `;
        }
          text += `   
                 </div>
            </div>
          </div>
        </div>
      </div>
    `;


    });
    $ul.append(text);

}

function getList(page, memberId) {
    inquireService.list({
        page: page,
        memberId : memberId
    }, function (list) {
        window.scrollTo(0, 0);
        listText(list);
        displayPagination(list.totalPages);

    });
}

function getDate(register){
    const newDate = new Date(register);
    const year = newDate.getFullYear();
    const month = newDate.getMonth() + 1;
    const date = newDate.getDate();

    console.log(`${year}-${month >= 10 ? month : '0' + month}-${date >= 10 ? date : '0' + date}`);


    return `${year}-${month >= 10 ? month : '0' + month}-${date >= 10 ? date : '0' + date}`;
    // 2021-01-01
}
//
//
// // Ajax를 사용해 목록 데이터와 페이지 정보 가져오기
// function getInquireList(page) {
//     $.ajax({
//         url: "/inquire/list?page=" + page,
//         type: "GET",
//         dataType: "json",
//         success: function(response) {
//             // 목록 생성
//             var listHtml = "";
//             response.content.forEach(function(data) {
//                 listHtml += `
//                  <div class="inquire-list-layout">
//                         <div class="flex-between">
//                             <p class="date">${data.createDate}</p>
//                             <div>
//                                 <button type="button" class="btn modify-btn">수정</button>
//                                 <button type="button" class="btn delete-btn">삭제</button>
//                             </div>
//                         </div>
//                         <div class="inquire-title-box">
//                             <a href="javascript:void(0)">
//                                 <h3>${data.title}</h3>
//                             </a>
//                             <div class="inquire-answer-status-box">
//                                 <div class="inquire-answer-status-layout">
//                                 ';
//                         if(
//                         listHtml += '
//                                         <div class="inquire-answer-border">
//                                             <p class="answer">답변 완료</p>
//                                         </div>
//                                 ';
//                         listHtml += '
//                                         <div class="inquire-answer-border">
//                                             <p class="answer-status">미답변</p>
//                                         </div>
//                                 ';
//
//                                 </div>
//                             </div>
//                         </div>
//                     `;
//             });
//             $(".inquire-list").html(listHtml);
//         }
//     });
// }
//
// // 페이지 번호 생성
// var pagingHtml = "";
// if (response.totalPages > 0) {
//     if (response.first) {
//         pagingHtml += `
//                     <div class="paging-border-none paging-disable">
//                         <a href="javascript:void(0)">
//                             <img src="/image/mypage/arrow.png" class="prev">
//                         </a>
//                     </div>
//                 `;
//     } else {
//         pagingHtml += `
//                     <div class="paging-border-none">
//                         <a href="javascript:void(0)" onclick="getInquireList(${response.number - 1})">
//                             <img src="/image/mypage/arrow.png" class="prev">
//                         </a>
//                     </div>
//                 `;
//     }
//
//     for (var i = 0; i < response.totalPages; i++) {
//         if (i === response.number) {
//             pagingHtml += `
//                         <div class="paging-border-none paging-active">
//                             <a href="javascript:void(0)">
//                                 <span class="page">${i + 1}</span>
//                             </a>
//                         </div>
//                     `;
//         } else {
//             pagingHtml += `
//                         <div class="paging-border-none">
//                             <a href="javascript:void(0)" onclick="getInquireList(${i})">
//                                 <span class="page">${i + 1}</span>
//                             </a>
//                         </div>
//                     `;
//         }
//     }
//
//     if (response.last) {
//         pagingHtml += `
//                     <div class="paging-border-none paging-disable">
//                         <a href="javascript:void(0)">
//                             <img src="/image/mypage/arrow.png" class="next">
//                         </a>
//                     </div>
//                 `;
//     } else {
//         pagingHtml += `
//                     <div class="paging-border-none">
//                         <a href="javascript:void(0)" onclick="getInquireList(${response.number + 1})">
//                             <img src="/image/mypage/arrow.png" class="next">
//                         </a>
//                     </div>
//                 `;
//     }
// }
