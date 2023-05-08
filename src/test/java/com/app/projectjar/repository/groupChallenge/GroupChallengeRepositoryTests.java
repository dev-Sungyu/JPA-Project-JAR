package com.app.projectjar.repository.groupChallenge;

import com.app.projectjar.domain.dto.BoardDTO;
import com.app.projectjar.entity.file.groupChallenge.GroupChallengeFile;
import com.app.projectjar.entity.groupChallenge.GroupChallenge;
import com.app.projectjar.repository.file.groupChallenge.GroupChallengeFileRepository;
import com.app.projectjar.type.GroupChallengeType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@Transactional
@Rollback(false)
@Slf4j
public class GroupChallengeRepositoryTests {

    @Autowired
    private GroupChallengeRepository groupChallengeRepository;

    @Autowired
    private GroupChallengeFileRepository groupChallengeFileRepository;

    @Test
    public void saveTest() {
        for (int i = 0; i < 10; i++) {
            groupChallengeRepository.save(new GroupChallenge(
                    "그룹 챌린지 제목" + (i + 1),
                    "그룹 챌린지 내용" + (i + 1),
                    GroupChallengeType.PRIVATE,
                    LocalDate.of(2023,05,8 + i),
                    LocalDate.of(2023,05,10 + i)));
        }
    }

    @Test
    public void fileSaveTest() {
        groupChallengeRepository.findById(230L).ifPresent(
                groupChallenge ->
                        groupChallengeFileRepository.save(new GroupChallengeFile(
                                "테스트12312.png",
                                UUID.randomUUID().toString(),
                                "2023/05/08",
                                groupChallenge
                        ))
        );
    }

    @Test
    public void findAllGroupChallengeWithPagingTest() {
        List<BoardDTO> boardDTOList = new ArrayList<>();
        PageRequest pageRequest = PageRequest.of(1,10);
//        groupChallengeRepository.findAllGroupChallengeWithPaging_QueryDsl(pageRequest).stream().map(GroupChallenge::toString).forEach(log::info);
        groupChallengeRepository.findAllGroupChallengeWithPaging_QueryDsl(pageRequest).stream()
                .forEach(groupChallenge -> {
                                BoardDTO boardDTO = new BoardDTO();
                                boardDTO.setBoardId(groupChallenge.getId());
                                boardDTO.setBoardTitle(groupChallenge.getBoardTitle());
                                boardDTO.setBoardContent(groupChallenge.getBoardContent());
                                boardDTO.setStartDate(groupChallenge.getStartDate());
                                boardDTO.setEndDate(groupChallenge.getEndDate());
                                boardDTO.setGroupChallengeStatus(groupChallenge.getGroupChallengeStatus());
                    groupChallengeFileRepository.findTop1ByGroupChallengeFileId_QueryDsl(groupChallenge.getId()).ifPresent(
                            groupChallengeFile ->{
                                boardDTO.setFileId(groupChallengeFile.getId());
                                boardDTO.setFileOriginalName(groupChallengeFile.getFileOriginalName());
                                boardDTO.setFileUuid(groupChallengeFile.getFileUuid());
                                boardDTO.setFilePath(groupChallengeFile.getFilePath());
                            }
                    );
                    boardDTOList.add(boardDTO);
                });
        boardDTOList.stream().map(BoardDTO::toString).forEach(log::info);

    }

}
