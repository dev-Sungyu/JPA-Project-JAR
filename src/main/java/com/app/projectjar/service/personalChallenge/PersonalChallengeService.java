package com.app.projectjar.service.personalChallenge;

import com.app.projectjar.domain.challenge.ChallengeDTO;
import com.app.projectjar.domain.file.FileDTO;
import com.app.projectjar.domain.personalChallenge.PersonalChallengeDTO;
import com.app.projectjar.entity.challenge.Challenge;
import com.app.projectjar.entity.file.challenge.ChallengeFile;
import com.app.projectjar.entity.personalChallenge.PersonalChallenge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface PersonalChallengeService {

    // 목록 가져오기
    public Page<PersonalChallengeDTO> getListByChallengeStatus(String challengeStatus, Pageable pageable);

    // 어제 insert된 목록
    public List<PersonalChallenge> getListToYesterday(LocalDateTime yesterday);

    // challengeStatus private로 수정
    public void updateChallengeStatus(List<PersonalChallenge> challengeList);

    // 상세보기
    public PersonalChallengeDTO getPersonalChallenge(Long personalChallengeId);


    default PersonalChallengeDTO toPersonalChallengeDTO(PersonalChallenge personalChallenge) {
        return PersonalChallengeDTO.builder()
                .attendCount(personalChallenge.getChallengeAttendCount())
                .replyCount(personalChallenge.getChallengeReplyCount())
                .challengeDTO(toChallengeDTO(personalChallenge.getChallenge()))
                .challengeStatus(personalChallenge.getChallengeStatus())
                .id(personalChallenge.getId())
                .startDate(personalChallenge.getCreatedDate())
                .build();
    }

    default ChallengeDTO toChallengeDTO(Challenge challenge) {
        return ChallengeDTO.builder()
                .boardContent(challenge.getBoardContent())
                .boardTitle(challenge.getBoardTitle())
                .fileDTOS(toFileDTOS(challenge.getChallengeFiles()))
                .id(challenge.getId())
                .build();
    }

    default List<FileDTO> toFileDTOS(List<ChallengeFile> challengeFileList) {
        List<FileDTO> fileDTOS = new ArrayList<>();

        challengeFileList.forEach(
                challengeFile -> {
                    FileDTO fileDTO = FileDTO.builder()
                            .id(challengeFile.getId())
                            .fileOriginalName(challengeFile.getFileOriginalName())
                            .fileUuid(challengeFile.getFileUuid())
                            .filePath(challengeFile.getFilePath())
                            .fileType(challengeFile.getFileType())
                            .build();
                    fileDTOS.add(fileDTO);
                }
        );
        return fileDTOS;
    }
}
