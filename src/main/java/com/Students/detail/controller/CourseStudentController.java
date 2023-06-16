package com.Students.detail.controller;

import com.Students.detail.other.CourseStudentRequest;
import com.Students.detail.service.CourseStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/course-student")
public class CourseStudentController {

    @Autowired
    CourseStudentService courseStudentService;

    @PostMapping("/associate")
    public void associateCourseAndStudent(@RequestBody CourseStudentRequest request) {
        courseStudentService.addCourseAndStudent(request);
		System.out.println("Welcome Maria");

    }
}
