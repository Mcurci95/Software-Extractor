package main.java.com.sotwareextractor.cecs547.Controller;

import main.java.com.sotwareextractor.cecs547.Service.SourceFileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin("*")
@RestController
public class SourceFileController {

    @Autowired
    private SourceFileStorageService sourceFileStorageService;
    
    Logger logger = LoggerFactory.getLogger(SourceFileController.class);

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity uploadFile(@RequestParam MultipartFile file) {
        logger.info("Logging file from FileController. File name %s", file.getOriginalFilename());
        return ResponseEntity.ok().build();
    }
}
