package com.sotwareextractor.cecs547.Controller;

import com.sotwareextractor.cecs547.Model.MAccess;
import com.sotwareextractor.cecs547.Model.MClass;
import com.sotwareextractor.cecs547.Model.MPackage;
import com.sotwareextractor.cecs547.Model.MSourceFile;
import com.sotwareextractor.cecs547.POJO.DClass;
import com.sotwareextractor.cecs547.POJO.DClassField;
import com.sotwareextractor.cecs547.POJO.DClassMethod;
import com.sotwareextractor.cecs547.POJO.DConstructor;
import com.sotwareextractor.cecs547.Service.MClassService;
import com.sotwareextractor.cecs547.Service.MPackageService;
import com.sotwareextractor.cecs547.Service.SourceFileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
public class SourceFileController {

    @Autowired
    private SourceFileStorageService sourceFileStorageService;

    @Autowired
    private MClassService mClassService;

    @Autowired
    private MPackageService mPackageService;

    Logger logger = LoggerFactory.getLogger(SourceFileController.class);

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity uploadFile(@RequestParam("files") MultipartFile[] files) throws IOException {
        for (MultipartFile file : files) {
            logger.info("Saving file: {}",  file.getOriginalFilename());
            sourceFileStorageService.saveFile(file);
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/postMaintenanceUpload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity postMaintenanceUpload(@RequestParam("files") MultipartFile[] files) throws IOException {
        for (MultipartFile file : files) {
            logger.info("Saving file: {}",  file.getOriginalFilename());
            sourceFileStorageService.saveFile(file);
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/allClasses")
    public List<MClass> getClasses() {
        List<String> returnString = new ArrayList<>();
        logger.info("Getting all class information");
        return mClassService.findAll();
    }

    @GetMapping(value = "/fileNames")
    @ResponseBody
    public List<MSourceFile> getFiles() {
        logger.info("Grabbing all source files");
        return sourceFileStorageService.getFiles();
    }

    @GetMapping(value = "/distinctClasses")
    @ResponseBody
    public List<String> getDistinctClasses() {
        logger.info("Grabbing distinct classes");
        return this.mClassService.distinctClassNames();
    }

    @GetMapping(value = "/packages")
    public List<MPackage> getPackages() {
        logger.info("Grabbing all packages");
        return mPackageService.getPackages();
    }

    @GetMapping(value = "/parents")
    public List<String> getParents(@RequestParam(name = "name") String parent) {
        logger.info("Calling parent endpoint with parent name " + parent);
        return mClassService.parentNames(parent).stream().map(MClass::getName).collect(Collectors.toList());
    }

    @GetMapping(value = "/downloadFile")
    public ResponseEntity<byte[]> getSourceFile(@RequestParam String classId,  @RequestParam String comment) throws IOException {

        logger.info("CALLING download with id " + classId +  " and comment: " + comment);
        Long classIdLong = Long.parseLong(classId);
        Optional<MClass> mClassOpt = mClassService.findById(classIdLong);
        if (mClassOpt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        MClass mClass = mClassOpt.get();
        byte[] data = mClass.getData();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] commentBytes = comment.getBytes();
        out.write(commentBytes);
        out.write(data);
        byte[] fileWithComments = out.toByteArray();
        return new ResponseEntity<>(fileWithComments, headers, HttpStatus.OK);
    }
}
