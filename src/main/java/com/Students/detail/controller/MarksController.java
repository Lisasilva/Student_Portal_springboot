package com.Students.detail.controller;

import com.Students.detail.dto.StudentCourseMarksDTO;
import com.Students.detail.entity.Marks;
import com.Students.detail.service.MarksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/marks")
public class MarksController {

    @Autowired
    MarksService marksService;

    @PostMapping("/add")   //to add marks for a given studentId - course_id combo that exists in the join table ie, student_courses
    public Marks add(@RequestParam int studentId, @RequestParam int courseId, @RequestParam int marks){
        return marksService.add(studentId, courseId, marks);
    }

    @GetMapping("/get/all")
    public List<StudentCourseMarksDTO> getAll(){
        return marksService.getAll();
    }

    @GetMapping("/get")
    public StudentCourseMarksDTO getById(@RequestParam int id){
        return marksService.getById(id);
    }

     @PutMapping("/update")
    public StudentCourseMarksDTO update(@RequestParam int studentId,@RequestParam int courseId, @RequestParam int marks){
        return marksService.update(studentId, courseId, marks);
    }
    
    @DeleteMapping("/delete")
    public void delete(int id){
        marksService.delete(id);
    }

    @GetMapping("/getStudentCourseMarks")
    public StudentCourseMarksDTO getStudentCourseMarks(@RequestParam int studentId, @RequestParam int courseId){
        return marksService.getStudentCourseMarks(studentId, courseId);
    }

    @GetMapping("/getTotalMarks")
    public int getTotalMarks(@RequestParam int studentId){
        return marksService.getTotalMarks(studentId);
    }

    @GetMapping("/getPercentage")
    public double getPercentage(@RequestParam int studentId){
        return marksService.getPercentage(studentId);
    }
    
       
    @GetMapping("/numberOfStudentsPerCourse")
    public int getStudentNumber(@RequestParam int id){
        return marksService.getStudentNumber(id);
    }
}
