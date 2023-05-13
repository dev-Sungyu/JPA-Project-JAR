
const $ul = $(".challenge-wrapper-list");
let page = 0;

groupChallengeService = (function () {
    function list(page, callback) {
        $.ajax({
            url: '/board/challenge/group/content-list',
            type: 'get',
            data: page,
            success: function (list) {
                if (callback) {
                    callback(list);
                }
            }
        });
    }

    return {
        list: list
    }
})();
getList(page);

$(".paging-layout").on("click", "a", function(e) {
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
    getList(page);
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
    let groupChallengeDTOS = list.content;

    $(groupChallengeDTOS).each((i, groupChallengeDTO) => {
        let text = `
                                                <li smcolumn="2" lgcolumn="3" class="challenge-board">
                                                        <div class="challenge-board-box">
                                                            <a href="/board/challenge/group/detail/${groupChallengeDTO.id}"  class="challenge-board-box-a">
                                                                <div class="challenge-board-box-a-box">
                                                                    <div class="board-box">
                                                                        <span class="board-box-span-1 board-box-span-2">
                                                                            <picture class="board-box-picture" ratio="0.75">
                                                                                <img sizes="(min-width: 1024px) 300px, 50vw" src="/image/board/board-img.png" alt="챌린지 게시판" class="board-box-picture-img">
                                                                            </picture>
                                                                        </span>
                                                                    </div>
                                                                    <div class="">
                                                                        <div class="board-container-1">
                                                                            <p class="board-container-name">${groupChallengeDTO.boardTitle}</p>
                                                                        </div>
                                                                        <div class="board-container-2">${groupChallengeDTO.boardContent}</div>
                                                                        <div class="board-container-3">
                                                                            <div class="board-container-set">
                                                                                <div class="board-container-set-wrap" data-test-group="product-card-wishlited-count1">
                                                                                    <div class="board-container-set-box">
                                                                                        <img src="/image/board/human-icon.png" alt="" class="human-icon">
                                                                                        51832
                                                                                    </div>
                                                                                </div>
                                                                                <div class="board-container-set-like" data-test-group="product-card-like-count">
                                                                                    <div class="board-container-set-box">
                                                                                        <img src="/image/board/reply-icon.png" alt="" class="reply-icon">
                                                                                        <span>${groupChallengeDTO.replyCount}</span>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                    <div class="board-box-line"></div>
                                                                    <div class="board-box-line-flex"></div>
                                                                </div>
                                                            </a>
                                                        </div>
                                                    </li>

        `;
        $ul.append(text);
    });

}

function getList(page){
    groupChallengeService.list({
        page: page
    }, function (list) {
        window.scrollTo(0, 0);
        listText(list);
        displayPagination(list.totalPages);
    })
}
