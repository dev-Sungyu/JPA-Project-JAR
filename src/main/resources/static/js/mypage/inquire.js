
const $ul = $(".challenge-wrapper-list");
let page = 0;

inquireService = (function (){
    function list(page, callback){
        $.ajax({
            url: '/mypage/inquire-list',
            type:'get',
            data: page,
            success: function(list){
                if (callback){
                    callback(list);
                }
            }
        });
    }
    return {
        list:list,
    }
});


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
                                ';
                        if(        
                        listHtml += '        
                                        <div class="inquire-answer-border">
                                            <p class="answer">답변 완료</p>
                                        </div>
                                ';
                        listHtml += '            
                                        <div class="inquire-answer-border">
                                            <p class="answer-status">미답변</p>
                                        </div>
                                ';   
                                    
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
