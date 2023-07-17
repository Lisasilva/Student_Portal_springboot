package com.Students.detail.controller;

import com.Students.detail.service.StudentSubjectService;
import com.Students.detail.dto.StudentSubjectDto;
import com.Students.detail.entity.StudentSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/studsub")
public class StudentSubjectController {

    private final StudentSubjectService studSubService;

    @Autowired
    public StudentSubjectController(StudentSubjectService studSubService) {
        this.studSubService = studSubService;
    }

    @GetMapping("/get/all")
    public List<StudentSubject> getAll(){
        return studSubService.getAll();
    }

    @PostMapping("/add")
    public StudentSubject add(@RequestBody StudentSubjectDto studSubDto){
        return studSubService.add(studSubDto);
    }

    @PutMapping("/edit")
    public StudentSubject edit(@RequestBody StudentSubject studSub){
        return studSubService.edit(studSub);
    }
	
    @DeleteMapping("/delete")
    public String delete(@RequestParam Long Id){
        return studSubService.delete(Id);
    }
	
    @GetMapping("/studentSubject")
    public StudentSubject getById(@RequestParam Long Id){
        return studSubService.getById(Id);
    }
}
