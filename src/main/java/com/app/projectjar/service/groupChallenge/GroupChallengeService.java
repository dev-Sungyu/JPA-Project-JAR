package com.app.projectjar.service.groupChallenge;

import com.app.projectjar.domain.file.FileDTO;
import com.app.projectjar.domain.groupChallenge.GroupChallengeDTO;
import com.app.projectjar.entity.file.groupChallenge.GroupChallengeFile;
import com.app.projectjar.entity.groupChallenge.GroupChallenge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface GroupChallengeService {

    // 목록
    public Page<GroupChallengeDTO> getGroupChallengeList(Pageable pageable);

    // 종료된 목록
    public Page<GroupChallengeDTO> getEndGroupChallengeList(Pageable pageable);

    // 상세보기
    public GroupChallengeDTO getGroupChallenge(Long groupChallengeId);

    // 현재 시퀀스 가져오기
    public GroupChallenge getCurrentSequence();

    default GroupChallengeDTO toGroupChallengeDTO(GroupChallenge groupChallenge) {
        return GroupChallengeDTO.builder()
                .id(groupChallenge.getId())
                .boardContent(groupChallenge.getBoardContent())
                .boardTitle(groupChallenge.getBoardTitle())
                .endDate(groupChallenge.getEndDate())
                .startDate(groupChallenge.getStartDate())
                .fileDTOS(toFileDTO(groupChallenge.getGroupChallengeFiles()))
                .groupChallengeReplyCount(groupChallenge.getGroupChallengeReplyCount())
                .groupChallengeStatus(groupChallenge.getGroupChallengeStatus())
                .replyCount(groupChallenge.getGroupChallengeReplyCount())
                .attendCount(groupChallenge.getGroupChallengeAttendCount())
                .build();
    }

    default List<FileDTO> toFileDTO(List<GroupChallengeFile> groupChallengeFile) {
        List<FileDTO> fileDTOS = new ArrayList<>();

        groupChallengeFile.stream().forEach(
                file -> {
                    FileDTO fileDTO = FileDTO.builder()
                            .fileType(file.getFileType())
                            .fileOriginalName(file.getFileOriginalName())
                            .filePath(file.getFilePath())
                            .fileUuid(file.getFileUuid())
                            .id(file.getId())
                            .build();
                    fileDTOS.add(fileDTO);
                }
        );
        return fileDTOS;
    }

}
