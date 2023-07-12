package com.Students.detail.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.Students.detail.entity.Student;
import com.Students.detail.service.StudentService;
import java.util.List;

@RestController
@RequestMapping("/stud")
public class StudentController {

    private final StudentService studService;

    @Autowired
    public StudentController(StudentService studService) {
        this.studService = studService;
    }

    @GetMapping("/get/all")
    public List<Student> getAll(){
        return studService.getAll();
    }

    @PostMapping("/add")
    public Student add(@RequestBody Student stud){
        return studService.add(stud);
    }
	@PutMapping("/edit")
	public Student edit(@RequestBody Student stud){
		return studService.edit(stud);
	}
	
	@DeleteMapping("/delete")
	public String delete(@RequestParam Long Id){
		return studService.delete(Id);
	}
	
	@GetMapping("/student")
	public Student getById(@RequestParam Long Id){
	    return studService.getById(Id);
	}

	
}








/*
  
 the next three crud operations using pathvariable instead of using one parameter


    @PutMapping("/edit/{studId}")
public Student edit(@PathVariable Long studId, @RequestBody Student stud){
    return studService.edit(studId, stud);
}

@DeleteMapping("/delete/{studId}")
public void delete(@PathVariable Long studId){
    studService.delete(studId);
}

@GetMapping("/student/{studId}")
public Student getById(@PathVariable Long studId){
    return studService.getById(studId);
}*/


