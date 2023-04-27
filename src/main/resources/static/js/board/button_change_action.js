    // 개인/단체 챌린지 전환 버튼
    let $selectType = $($('.selection_label')[0]);
    $selectType.on("click", (e) => {
        console.log("버튼 누름");
        var $target = $(e.currentTarget);
        var $selected = $('div.selection.selected');
        var $hiddened = $('div.selection.is-hidden');
        $selected.removeClass('selected').addClass('is-hidden');
        $hiddened.removeClass('is-hidden').addClass('selected');
    });