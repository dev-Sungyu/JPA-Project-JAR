package com.app.projectjar.service.file;

import com.app.projectjar.domain.file.FileDTO;
import com.app.projectjar.repository.file.suggest.SuggestFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Qualifier("suggestFile") @Primary
public class SuggestFileImpl implements SuggestFileService {

    private final SuggestFileRepository suggestFileRepository;

    @Override
    public void writeList(List<FileDTO> fileDTOS) {
        fileDTOS.forEach(
                fileDTO -> suggestFileRepository.save(toSuggestFileEntity(fileDTO))
        );
    }
}
