package com.app.projectjar.controller.board.suggest.file;

import com.app.projectjar.domain.file.FileDTO;
import com.app.projectjar.service.file.SuggestFileService;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/suggest/image/*")
@RequiredArgsConstructor
public class SuggestFileRestController {
    private final SuggestFileService suggestFileService;

    //    파일 업로드
    @PostMapping("upload")
    public List<String> suggestUpload(@RequestParam("file") List<MultipartFile> multipartFiles) throws IOException {
        List<String> uuids = new ArrayList<>();
        String path = "C:/upload/" + getPath();
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }

        for (int i = 0; i < multipartFiles.size(); i++) {
            uuids.add(UUID.randomUUID().toString());
            multipartFiles.get(i).transferTo(new File(path, uuids.get(i) + "_" + multipartFiles.get(i).getOriginalFilename()));

            if (multipartFiles.get(i).getContentType().startsWith("image")) {
                FileOutputStream out = new FileOutputStream(new File(path, "t_" + uuids.get(i) + "_" + multipartFiles.get(i).getOriginalFilename()));
                Thumbnailator.createThumbnail(multipartFiles.get(i).getInputStream(), out, 250, 175);
                out.close();
            }
        }
        return uuids;
    }


    //    파일 저장
    @PostMapping("save")
    public RedirectView businessSave(@RequestBody List<FileDTO> fileDTOS) {
        suggestFileService.writeList(fileDTOS);
        return new RedirectView("/board/suggest/list");
    }

    //    파일 불러오기
    @GetMapping("display")
    public byte[] businessDisplay(String fileName) throws Exception {
        try {
            return fileName.contentEquals("null") || fileName.isBlank() ? null : FileCopyUtils.copyToByteArray(new File("C:/upload", fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //    현재 날짜 경로 구하기
    private String getPath() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

}
