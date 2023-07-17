package com.Students.detail.service;

import com.Students.detail.entity.Department;
import com.Students.detail.entity.Student;
import com.Students.detail.entity.StudentSubject;
import com.Students.detail.repository.DepartmentRepository;
import com.Students.detail.repository.StudentRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import com.Students.detail.service.DepartmentService;


import java.util.List;
import java.util.Optional;
//import java.util.ArrayList;
import java.util.Set;

@Service
public class StudentService implements BaseService<Student> {

    @Autowired     //implicit dependency obj
    private StudentRepository studRepository;    //provides methods to interact with the DB using JPA ig

    @Autowired
    private DepartmentRepository deptRepository; //provides methods to interact with the DB using JPA
    
    
    
    @Transactional
    public Student createStudent(Student student) {
        Department existingDepartment = deptRepository.findDepartmentByDeptId(student.getDepartment().getDeptId());
        if (existingDepartment == null) {
            throw new RuntimeException("Department does not exist");
        } else {
            return studRepository.save(student);
        }
    }
    
    @Override
	public List<Student> getAll(){    //GET
		List<Student> students = studRepository.findAll(); //findAll() fn exists by default
		if(students.isEmpty()) {
			throw new RuntimeException("No records found"); //exception to display msg if no record exists on calling GET
		}
		return students;
	}
	

    
//    @Override
//    public Student add(Student stud) {
//        // Get the department from student and check if the department exists in the DepartmentRepository
//        Department department = stud.getDepartment();
//        boolean departmentExists = deptRepository.existsById(department.getDeptId());
//
//        // Check if a student with the same email already exists
//        boolean duplicateEmailExists = studRepository.existsByEmail(stud.getEmail());
//
//        if(duplicateEmailExists || !departmentExists){   
//            throw new RuntimeException("Either duplicate record or department not found"); 
//        }
//
//        return studRepository.save(stud);   
//    }

    
    @Override
    public Student add(Student stud) {
        Long deptId = stud.getDepartment().getDeptId(); //to retrieve the department from student

        // Check if the department exists in the DepartmentRepository
        if(!deptRepository.existsById(deptId)) {
            throw new RuntimeException("Department not found");
        }

        if(studRepository.existsByEmail(stud.getEmail())){   
            throw new RuntimeException("Duplicate email"); 
        }


        Department department = deptRepository.findDepartmentByDeptId(deptId);
        if(department == null){
            throw new RuntimeException("Department not found"); 
        }


        stud.setDepartment(department);  // Sets retrieved department to the student
        return studRepository.save(stud);   
    }





    @Override
  	public Student edit(Student stud) {    //PUT
  		if (studRepository.existsById(stud.getId())) {
  			return studRepository.save(stud);
  		} 
  		
  		else {
  			throw new RuntimeException("Student not found"); //to check if the ID entered for modification exists or not
  		}
  	}    

    @Override
    public String delete(Long Id) {
        if (!studRepository.existsById(Id)) {      // to check if the ID entered doesnt exist
            throw new RuntimeException("Record doesn't exist");
        }
        studRepository.deleteById(Id);
        return "Deleted";
    }

    @Override
    public Student getById(Long Id) {
        if (!studRepository.existsById(Id)) {    // to check if the ID enetered doesnt exist
            throw new RuntimeException("Record doesn't exist"); 
        }
        return studRepository.findById(Id).orElse(null);  //used optional datatype
    }
    
    
    public Set<StudentSubject> getStudentSubjects(Long studentId) {
        Student student = studRepository.findById(studentId).orElseThrow(/* exception */);
        return student.getStudentSubjects();
    }

}



