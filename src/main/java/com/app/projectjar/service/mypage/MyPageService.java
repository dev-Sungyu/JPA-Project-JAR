package com.app.projectjar.service.mypage;

import com.app.projectjar.domain.diary.DiaryDTO;
import com.app.projectjar.domain.file.FileDTO;
import com.app.projectjar.domain.member.MemberDTO;
import com.app.projectjar.entity.diary.Diary;
import com.app.projectjar.entity.file.diary.DiaryFile;
import com.app.projectjar.entity.file.suggest.SuggestFile;
import com.app.projectjar.entity.member.Member;
import com.app.projectjar.entity.suggest.Suggest;

import java.util.ArrayList;
import java.util.List;

public interface MyPageService {

    public void registerDiary(DiaryDTO diaryDTO, Long memberId);

    // 현재 시퀀스 가져오기
    public Diary getCurrentSequence();


    default MemberDTO toMemberDTO(Member member) {
        return MemberDTO.builder()
                .id(member.getId())
                .memberEmail(member.getMemberEmail())
                .memberPassword(member.getMemberPassword())
                .memberName(member.getMemberName())
                .memberNickname(member.getMemberNickname())
                .memberPhoneNumber(member.getMemberPhoneNumber())
                .memberStatus(member.getMemberStatus())
                .build();
    }

    default Diary toDiaryEntity(DiaryDTO diaryDTO){
        return Diary.builder()
                .boardContent(diaryDTO.getBoardContent())
                .boardTitle(diaryDTO.getBoardTitle())
                .diaryStatus(diaryDTO.getDiaryStatus())
                .member(toMemberEntity(diaryDTO.getMemberDTO()))
                .build();
    }

    default Member toMemberEntity(MemberDTO memberDTO) {
        return Member.builder()
                .id(memberDTO.getId())
                .memberEmail(memberDTO.getMemberEmail())
                .memberName(memberDTO.getMemberName())
                .memberNickname(memberDTO.getMemberNickname())
                .memberPassword(memberDTO.getMemberPassword())
                .memberPhoneNumber(memberDTO.getMemberPhoneNumber())
                .memberStatus(memberDTO.getMemberStatus())
                .badgeType(memberDTO.getBadgeType())
                .build();
    }

    default DiaryFile toDiaryFileEntity(FileDTO fileDTO){
        return DiaryFile.builder()
                .id(fileDTO.getId())
                .fileOriginalName(fileDTO.getFileOriginalName())
                .fileUuid(fileDTO.getFileUuid())
                .filePath(fileDTO.getFilePath())
                .diary(fileDTO.getDiary())
                .fileType(fileDTO.getFileType())
                .build();
    }

}
