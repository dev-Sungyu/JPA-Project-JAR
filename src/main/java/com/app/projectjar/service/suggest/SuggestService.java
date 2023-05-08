package com.app.projectjar.service.suggest;

import com.app.projectjar.domain.dto.file.FileDTO;
import com.app.projectjar.domain.dto.member.MemberDTO;
import com.app.projectjar.domain.dto.suggest.SuggestDTO;
import com.app.projectjar.entity.file.suggest.SuggestFile;
import com.app.projectjar.entity.member.Member;
import com.app.projectjar.entity.suggest.Suggest;

import java.util.ArrayList;
import java.util.List;

public interface SuggestService {

    // 저장
    public void register(SuggestDTO suggestDTO);
    // 목록
    // 상세 보기
    // 수정


//    default SuggestDTO SuggestToDTO(Suggest suggest) {
//        return SuggestDTO.builder()
//                .id(suggest.getId())
//                .boardTitle(suggest.getBoardTitle())
//                .boardContent(suggest.getBoardContent())
//                .boardType(suggest.getBoardType())
//                .memberDTO(toMemberDTO(suggest.getMember()))
//                .fIleDTOS(FileToDTO(suggest.getSuggestFiles()))
//                .build();
//    }

//    default MemberDTO toMemberDTO(Member member) {
//        return MemberDTO.builder()
//                .memberId(member.getId())
//                .memberEmail(member.getMemberEmail())
//                .memberPassword(member.getMemberPassword())
//                .memberName(member.getMemberName())
//                .memberNickname(member.getMemberNickname())
//                .memberPhoneNumber(member.getMemberPhoneNumber())
//                .memberStatus(member.getMemberStatus())
//                .build();
//    }

    default List<FileDTO> FileToDTO(List<SuggestFile> suggestFiles) {
        List<FileDTO> suggestFileList = new ArrayList<>();
        suggestFiles.forEach(
                suggestFile -> {
                    FileDTO fileDTO = FileDTO.builder()
                            .id(suggestFile.getId())
                            .fileOriginalName(suggestFile.getFileOriginalName())
                            .fileUuid(suggestFile.getFileUuid())
                            .filePath(suggestFile.getFilePath())
                            .build();
                    suggestFileList.add(fileDTO);
                }
        );
        return suggestFileList;
    }
}
