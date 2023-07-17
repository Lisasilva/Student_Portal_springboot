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
            return new ResponseEntity<>("The Student excel file has been uploaded and processed successfully.", HttpStatus.OK);
        } 
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/department")
    public ResponseEntity<String> uploadFileDepartment(@RequestParam("file") MultipartFile file) {
        try {
            excelService.save(file,"department");
            return new ResponseEntity<>("The department excel file has been uploaded and processed successfully.", HttpStatus.OK);
        } 
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } 
    
    @PostMapping("/subject")
    public ResponseEntity<String> uploadFileSubject(@RequestParam("file") MultipartFile file) {
        try {
            excelService.save(file,"subject");
            return new ResponseEntity<>("The subject excel file has been uploaded and processed successfully.", HttpStatus.OK);
        } 
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/student_subject")
    public ResponseEntity<String> uploadFileStudentSubject(@RequestParam("file") MultipartFile file) {
        try {
            excelService.save(file,"student_subject");
            return new ResponseEntity<>("The student_subject excel file has been uploaded and processed successfully.", HttpStatus.OK);
        } 
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    
    //need to add for studentsubject aswell
    
}   
    
    
    /*
    
    @PostMapping("/student")
    public ResponseEntity<String> uploadFileStudent(@RequestParam("file") MultipartFile file) {
        try {
            excelService.save(file,"student");
            return new ResponseEntity<>("The Student excel file has been uploaded and processed successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/department")
    public ResponseEntity<String> uploadFileCourse(@RequestParam("file") MultipartFile file) {
        try {
            excelService.save(file,"department");
            return new ResponseEntity<>("The Department Excel file has been uploaded and processed successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/subject")
    public ResponseEntity<String> uploadFileMarks(@RequestParam("file") MultipartFile file) {
        try {
            excelService.save(file,"subject");
            return new ResponseEntity<>("The Student Excel file has been uploaded and processed successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    */

