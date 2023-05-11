package com.app.projectjar.service.suggest.reply;

import com.app.projectjar.domain.ReplyDTO;
import com.app.projectjar.domain.file.FileDTO;
import com.app.projectjar.domain.member.MemberDTO;
import com.app.projectjar.domain.suggest.SuggestDTO;
import com.app.projectjar.entity.file.member.MemberFile;
import com.app.projectjar.entity.member.Member;
import com.app.projectjar.entity.suggest.Suggest;
import com.app.projectjar.entity.suggest.SuggestReply;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface SuggestReplyService {

    // 댓글 저장
    public void insertReply(Long suggestId,Long memberId, String replyContent);
    // 댓글 수정
    public void modifyReply(Long replyId, String replyContent);
    // 댓글 삭제
    public void deleteReply(Long replyId);
    // 댓글 목록
    public Slice<ReplyDTO> getReplyList(Long suggestId, Pageable pageable);


    default SuggestReply toSuggestReplyEntity(ReplyDTO replyDTO){
        return SuggestReply.builder()
                .id(replyDTO.getId())
                .member(toMemberEntity(replyDTO.getMemberDTO()))
                .replyContent(replyDTO.getReplyContent())
                .suggest(toSuggestEntity(replyDTO.getSuggestDTO()))
                .build();
    }

    default Member toMemberEntity(MemberDTO memberDTO){
        return Member.builder()
                .id(memberDTO.getId())
                .badgeType(memberDTO.getBadgeType())
                .memberStatus(memberDTO.getMemberStatus())
                .memberPhoneNumber(memberDTO.getMemberPhoneNumber())
                .memberPassword(memberDTO.getMemberPassword())
                .memberNickname(memberDTO.getMemberNickname())
                .memberName(memberDTO.getMemberName())
                .memberEmail(memberDTO.getMemberEmail())
                .memberFile(toMemberFileEntity(memberDTO.getFileDTO()))
                .build();
    }

    default MemberFile toMemberFileEntity(FileDTO fileDTO){
        return MemberFile.builder()
                .fileOriginalName(fileDTO.getFileOriginalName())
                .filePath(fileDTO.getFilePath())
                .fileType(fileDTO.getFileType())
                .fileUuid(fileDTO.getFileUuid())
                .id(fileDTO.getId())
                .build();
    }

    default Suggest toSuggestEntity(SuggestDTO suggestDTO){
        return Suggest.builder()
                .id(suggestDTO.getId())
                .boardContent(suggestDTO.getBoardContent())
                .boardTitle(suggestDTO.getBoardTitle())
                .boardType(suggestDTO.getBoardType())
                .build();
    }
}
