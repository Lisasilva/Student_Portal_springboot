package com.Students.detail.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.Students.detail.dto.StudentDto;
import com.Students.detail.entity.Student;
import com.Students.detail.entity.StudentSubject;
import com.Students.detail.service.StudentService;
import java.util.List;
import java.util.Set;

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

	
	//to get all the details of the student from just the id
	@GetMapping("/id/details")
	public ResponseEntity<StudentDto> getStudentDetails(@RequestParam("id") Long id) {
	    StudentDto studentDto = studService.getStudentDetails(id);
	    return ResponseEntity.ok(studentDto);
	}

}





//gets the details of the student from only student table
//@GetMapping("/student")
//public Student getById(@RequestParam Long Id){
//    return studService.getById(Id);
//}

//@GetMapping("/student/{id}")            //this was latest one
//public StudentDto getStudentDetails(@PathVariable Long id){
//    return studService.getStudentDetails(id);
//}
//
//@GetMapping("/{studentId}/details")
//public StudentDto getStudentDetails(@PathVariable Long studentId) {
//    return studService.getStudentDetails(studentId);
//}


//  @GetMapping("/{id}")
//    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {
//        StudentDTO studentDTO = studentService.getStudentById(id);
//        return new ResponseEntity<>(studentDTO, HttpStatus.OK);
//    }
//
//extra calculations
//@GetMapping("/{studentId}/subjects")
//public Set<StudentSubject> getStudentSubjects(@PathVariable Long studentId) {
//    return studService.getStudentSubjects(studentId);
//}
//




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


