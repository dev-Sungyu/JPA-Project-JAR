$(document).ready(function() {
    // 목록과 페이징 버튼 초기화
    getInquireList(1);

    // 이전 버튼 클릭 시 이벤트 핸들러
    $("#prevBtn").on("click", function() {
        var currentPage = parseInt($("#pagingList").find(".active").text());
        if (currentPage > 1) {
            getInquireList(currentPage - 1);
        }
    });

    // 페이지 버튼 클릭 시 이벤트 핸들러
    $(document).on("click", ".pageBtn", function() {
        var page = $(this).text();
        getInquireList(page);
    });

    // 다음 버튼 클릭 시 이벤트 핸들러
    $("#nextBtn").on("click", function() {
        var currentPage = parseInt($("#pagingList").find(".active").text());
        var lastPage = parseInt($("#pagingList").find(".pageBtn:last-child").text());
        if (currentPage < lastPage) {
            getInquireList(currentPage + 1);
        }
    });
});

// Ajax를 사용해 목록 데이터와 페이지 정보 가져오기
function getInquireList(page) {
    $.ajax({
        url: "/inquire/list?page=" + page,
        type: "GET",
        dataType: "json",
        success: function(response) {
            // 목록 생성
            var listHtml = "";
            response.content.forEach(function(data) {
                listHtml += `
                 <div class="inquire-list-layout">
                        <div class="flex-between">
                            <p class="date">${data.createDate}</p>
                            <div>
                                <button type="button" class="btn modify-btn">수정</button>
                                <button type="button" class="btn delete-btn">삭제</button>
                            </div>
                        </div>
                        <div class="inquire-title-box">
                            <a href="javascript:void(0)">
                                <h3>${data.title}</h3>
                            </a>
                            <div class="inquire-answer-status-box">
                                <div class="inquire-answer-status-layout">
                                    ${data.answered ? `
                                        <div class="inquire-answer-border">
                                            <p class="answer">답변 완료</p>
                                        </div>
                                    ` : `
                                        <div class="inquire-answer-border">
                                            <p class="answer-status">미답변</p>
                                        </div>
                                              <p class="answer-status">미답변</p>-->
                                    `}
                                </div>
                            </div>
                        </div>
                    `;
            });
            $(".inquire-list").html(listHtml);
        }
    });
}

// 페이지 번호 생성
var pagingHtml = "";
if (response.totalPages > 0) {
    if (response.first) {
        pagingHtml += `
                    <div class="paging-border-none paging-disable">
                        <a href="javascript:void(0)">
                            <img src="/image/mypage/arrow.png" class="prev">
                        </a>
                    </div>
                `;
    } else {
        pagingHtml += `
                    <div class="paging-border-none">
                        <a href="javascript:void(0)" onclick="getInquireList(${response.number - 1})">
                            <img src="/image/mypage/arrow.png" class="prev">
                        </a>
                    </div>
                `;
    }

    for (var i = 0; i < response.totalPages; i++) {
        if (i === response.number) {
            pagingHtml += `
                        <div class="paging-border-none paging-active">
                            <a href="javascript:void(0)">
                                <span class="page">${i + 1}</span>
                            </a>
                        </div>
                    `;
        } else {
            pagingHtml += `
                        <div class="paging-border-none">
                            <a href="javascript:void(0)" onclick="getInquireList(${i})">
                                <span class="page">${i + 1}</span>
                            </a>
                        </div>
                    `;
        }
    }

    if (response.last) {
        pagingHtml += `
                    <div class="paging-border-none paging-disable">
                        <a href="javascript:void(0)">
                            <img src="/image/mypage/arrow.png" class="next">
                        </a>
                    </div>
                `;
    } else {
        pagingHtml += `
                    <div class="paging-border-none">
                        <a href="javascript:void(0)" onclick="getInquireList(${response.number + 1})">
                            <img src="/image/mypage/arrow.png" class="next">
                        </a>
                    </div>
                `;
    }
}
