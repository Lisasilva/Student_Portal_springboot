package com.Students.detail.controller;

import com.Students.detail.entity.Subject;
import com.Students.detail.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.Students.detail.service.StudentSubjectService;
import com.Students.detail.entity.StudentSubject;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/sub")
public class SubjectController {

    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/get/all")
    public List<Subject> getSubjects() {
        return subjectService.getAll();
    }
    
    @PostMapping("/add")
    public Subject addSubject(@RequestBody Subject subject) {
        return subjectService.add(subject);
    }

    @GetMapping("/subject")
    public Subject getSubject(@RequestParam("subjectId") Long id) {
        return subjectService.getById(id);
    }

    

    @PutMapping("/update")
    public Subject updateSubject(@RequestBody Subject subject) {

    //public Subject updateSubject(@RequestParam("subjectId") Long id, @RequestBody Subject subject) {
        return subjectService.edit(subject);
    }

    @DeleteMapping("/delete")
    public void deleteSubject(@RequestParam("subjectId") Long id) {
        subjectService.delete(id);
    }
    
    
    
    //extra addition
    
    @GetMapping("/{subjectId}/students")
    public Set<StudentSubject> getSubjectStudents(@PathVariable Long subjectId) {
        return subjectService.getSubjectStudents(subjectId);
    }
}
