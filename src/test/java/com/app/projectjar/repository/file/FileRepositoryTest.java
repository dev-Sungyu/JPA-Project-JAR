package com.app.projectjar.repository.file;

import com.app.projectjar.entity.file.Files;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@Rollback(false)
@Slf4j
public class FileRepositoryTest {
    @Autowired
    private FileRepository fileRepository;

    @Test
    public void findAllTest() {
        fileRepository.findAll().stream().map(Files::toString).forEach(log::info);
    }

}
