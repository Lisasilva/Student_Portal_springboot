package com.Students.detail.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.Students.detail.entity.Student;
import com.Students.detail.service.StudentService;
import java.util.List;

@RestController
@RequestMapping("/stud")
public class StudentController {

	@Autowired
	StudentService studService;
	
	@GetMapping("get/all")
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
	public String delete(@RequestParam int studNo){
		return studService.delete(studNo);
	}
	
	@GetMapping("/student")
	public Student getById(@RequestParam int studNo){
	    return studService.getById(studNo);
	}

	
}
