package com.app.projectjar.search.board;

import com.app.projectjar.domain.member.MemberDTO;
import lombok.Data;


@Data
public class SuggestSearch {
    private String boardTitle;
    private Integer likeCount;
    private Integer replyCount;
    private MemberDTO memberDTO;
}
