package com.Students.detail.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Students.detail.repository.StudentRepository;
import com.Students.detail.entity.Student;

@Service
public class StudentService implements BaseService<Student>{
	
	@Autowired         //implicit dependency obj
	StudentRepository studRepository;  //provides methods to interact with the DB using JPA ig
	
	@Override
	public List<Student> getAll(){    //GET
		List<Student> students = studRepository.findAll(); //findAll() fn exists by default
		if(students.isEmpty()) {
			throw new RuntimeException("No records found"); //exception to display msg if no record exists on calling GET
		}
		return students;
	}
	

	@Override
	public Student add(Student stud) {    //POST
	    if(studRepository.existsByName(stud.getName())){   //existsByName() doesn't exist by default in the JPA connection made in StudentRepository, hence we define it in Student repository
			throw new RuntimeException("Duplicate record");//if record with same name is entered twice
		}
		return studRepository.save(stud);   //save() is also predefined
	}
	
	  
    @Override
	public Student edit(Student stud) {    //PUT
		if (studRepository.existsById(stud.getStudId())) {
			return studRepository.save(stud);
		} 
		
		else {
			throw new RuntimeException("Student not found"); //to check if the ID entered for modification exists or not
		}
	}


	@Override
	public String delete(int studNo) {
		if(!studRepository.existsById(studNo)){        // to check if the ID entered doesnt exist
			throw new RuntimeException("Record doesn't exist");
		}
		studRepository.deleteById(studNo);
		return "Deleted";
	}
	
	@Override
	public Student getById(int studNo) {
		if(!studRepository.existsById(studNo)){        // to check if the ID enetered doesnt exist
			throw new RuntimeException("Record doesn't exist");
		}
		return studRepository.findById(studNo).orElse(null);  //used optional datatype
	}
}
