package com.Students.detail.controller;


import com.Students.detail.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/excel/upload")
public class ExcelController {

    private final ExcelService excelService;

    @Autowired
    public ExcelController(ExcelService excelService) {
        this.excelService = excelService;
    }

    @PostMapping("/student")
    public ResponseEntity<String> uploadFileStudent(@RequestParam("file") MultipartFile file) {
        try {
            excelService.save(file,"student");
            return new ResponseEntity<>("Excel file has been uploaded and processed successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/course")
    public ResponseEntity<String> uploadFileCourse(@RequestParam("file") MultipartFile file) {
        try {
            excelService.save(file,"course");
            return new ResponseEntity<>("Excel file has been uploaded and processed successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/marks")
    public ResponseEntity<String> uploadFileMarks(@RequestParam("file") MultipartFile file) {
        try {
            excelService.save(file,"marks");
            return new ResponseEntity<>("Excel file has been uploaded and processed successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
