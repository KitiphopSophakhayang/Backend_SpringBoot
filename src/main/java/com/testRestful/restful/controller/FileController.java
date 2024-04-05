package com.testRestful.restful.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.testRestful.restful.entity.FileEntity;
import com.testRestful.restful.repository.FileRepository;

@RestController
@RequestMapping("/api")
public class FileController {

    @Autowired
    private FileRepository fileRepository;

    // @PostMapping("/upload")
    // public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file)
    // {
    // try {
    // FileEntity fileEntity = new FileEntity();
    // fileEntity.setFilename(file.getOriginalFilename());
    // // fileEntity.setContentType(file.getContentType());
    // fileEntity.setData(file.getBytes());
    // fileRepository.save(fileEntity);
    // String message = "File uploaded successfully!";
    // HttpStatus httpStatus = HttpStatus.CREATED;
    // return new ResponseEntity<>(message, httpStatus);
    // } catch (IOException e) {
    // return ResponseEntity.status(500).build();
    // }
    // }

    // FileController.java

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            // สร้าง FileEntity และบันทึกข้อมูลไฟล์
            FileEntity fileEntity = new FileEntity();
            fileEntity.setFilename(file.getOriginalFilename());
            fileEntity.setData(file.getBytes());
            fileRepository.save(fileEntity);

            String message = "File uploaded successfully!";
            HttpStatus httpStatus = HttpStatus.CREATED;
            return new ResponseEntity<>(message, httpStatus);
        } catch (IOException e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<FileEntity>> getFile() {
        List<FileEntity> files = fileRepository.findAll();
        return ResponseEntity.ok(files);
    }

}