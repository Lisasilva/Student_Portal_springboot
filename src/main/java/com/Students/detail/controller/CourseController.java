package com.Students.detail.controller;

import com.Students.detail.entity.Course;
//import com.Students.detail.entity.Student;
import com.Students.detail.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    CourseService courseService;

    @GetMapping("/get/all")
    public List<Course> getAllCourses(){
        return courseService.getAll();
    }
  
       

	@PostMapping("/add")
	public Course add(@RequestBody Course cour){
		return courseService.add(cour);
	}
	
	@PutMapping("/edit")
	public Course edit(@RequestBody Course cour){
		return courseService.edit(cour);
	}
	
	@DeleteMapping("/delete")
	public String delete(@RequestParam int courNo){
		return courseService.delete(courNo);
	}
	
	@GetMapping("/course")
	public Course getById(@RequestParam int courNo){
	    return courseService.getById(courNo);
	}

}
