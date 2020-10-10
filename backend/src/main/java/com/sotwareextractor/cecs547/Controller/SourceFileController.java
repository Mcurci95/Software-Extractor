package com.sotwareextractor.cecs547.Controller;

import com.sotwareextractor.cecs547.Model.MSourceFile;
import com.sotwareextractor.cecs547.Service.SourceFileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin("*")
@RestController
public class SourceFileController {

    @Autowired
    private SourceFileStorageService sourceFileStorageService;

    Logger logger = LoggerFactory.getLogger(SourceFileController.class);

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity uploadFile(@RequestParam("files") MultipartFile[] files) throws IOException {
        for (MultipartFile file : files) {
            logger.info("Saving file: {}",  file.getOriginalFilename());
            sourceFileStorageService.saveFile(file);
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/fileNames")
    @ResponseBody
    public List<MSourceFile> getFiles() {
        logger.info("Grabbing all source files");
        return sourceFileStorageService.getFiles();

    }
}
