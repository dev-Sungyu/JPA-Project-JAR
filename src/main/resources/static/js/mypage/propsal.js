const $ul =$(".list-box")
let page = 0;

suggestService = (function () {
    function list(page, callback) {
        $.ajax({
            url: '/mypage/propsal-list',
            type: 'get',
            data: page,
            success: function(list){
                if(callback){
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
    let suggestDTOS = list.content;
    console.log(suggestDTOS);
    let text = '';

    $(suggestDTOS).each((i, suggestDTO) => {
    var createDate = getDate(suggestDTO.createdDate);
        text += `
                                        <div class="list-layout">
                                            <div class="flex-between">
                                                <p class="date">${createDate}</p>
                                                <div>
                                                    <button type="button" class="btn modify-btn" onclick="location.href='/board/suggest/modify/${suggestDTO.id}'">수정</button>
                                                    <button type="button" class="btn delete-btn"  data-item-id="${suggestDTO.id}"  >삭제</button>
                                                </div>
                                            </div>
                                            <a href="/board/suggest/detail/${suggestDTO.id}">
                                                <div class="list-content-title-box">
                                                    <h3>${suggestDTO.boardTitle}</h3>
                                                </div>
                                            </a>
                                        </div>              
    `;


    });
    $ul.append(text);

}






function getList(page, memberId) {
    suggestService.list({
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

$(".delete-btn").on("click", function() {
    // 삭제 버튼이 클릭되었을 때 수행할 동작을 여기에 작성합니다.
    const itemId = $(this).data("item-id"); // 예시: 삭제 대상 항목의 ID를 가져오는 방법
    // Ajax 요청을 사용하여 서버에 삭제 요청을 보냅니다.
    $.ajax({
        url: "/delete/{boardId}", // 실제 삭제 요청을 처리하는 서버의 URL로 대체해야 합니다.
        type: "POST", // 적절한 HTTP 메소드를 선택해야 합니다.
        data: { itemId: itemId }, // 삭제 대상 항목의 ID를 서버에 전달합니다.
        success: function(response) {
            // 삭제 요청이 성공적으로 처리되었을 때 수행할 동작을 여기에 작성합니다.


            console.log("삭제가 완료되었습니다.");
            // 필요한 경우 페이지 새로고침 등의 동작을 수행할 수 있습니다.
        },
        error: function(xhr, status, error) {
            // 삭제 요청이 실패했을 때 수행할 동작을 여기에 작성합니다.
            console.error("삭제 요청이 실패했습니다.");
            console.error(error);
        }
    });
});