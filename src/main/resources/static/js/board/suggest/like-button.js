const $heartButton = $(".heart-layout");
const $noHeart = $(".no-heart");
const $heartUp = $(".heart-up");

$heartButton.each((i, e) => {
    let boardId = e.id.replaceAll("heart", "");
    let likeDTO = new Object();
    likeDTO.memberId = memberId;
    likeDTO.boardId = boardId;

    likeService.heartCheck(likeDTO,function(result){
        if(result){
            $($(".heart-up")[i]).hide();
            $($(".no-heart")[i]).show();
            $($(".no-heart")[i]).removeClass("heart-active");
        }else {
            $($(".no-heart")[i]).addClass("heart-active");
            $($(".heart-up")[i]).show();
            $($(".no-heart")[i]).hide();
        }
    });

    $(e).click(() => {
        likeService.heartCheck(likeDTO, function (result) {
            if(result){
                likeService.heartUp(likeDTO, function(){
                    $($(".no-heart")[i]).addClass("heart-active");
                    $($(".heart-up")[i]).show();
                    $($(".no-heart")[i]).hide();
                    likeService.count({boardId: boardId}, function (result) {
                        $($(".like-count")[i]).text(result);
                    });
                });
            } else {
                likeService.heartDown(likeDTO, function(){
                    $($(".heart-up")[i]).hide();
                    $($(".no-heart")[i]).show();
                    $($(".no-heart")[i]).removeClass("heart-active");
                    likeService.count({boardId: boardId}, function (result) {
                        $($(".like-count")[i]).text(result);
                    })
                });
            }
        });

    });
});