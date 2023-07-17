package com.Students.detail.service;

import com.Students.detail.entity.Department;
import com.Students.detail.entity.StudentSubject;
import com.Students.detail.entity.Subject;
import com.Students.detail.repository.DepartmentRepository;
import com.Students.detail.repository.SubjectRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

@Service
public class SubjectService implements BaseService<Subject>{

    @Autowired
    private SubjectRepository subRepository;

    @Autowired
    private DepartmentRepository deptRepository; //provides methods to interact with the DB using JPA
    
    
    
    @Transactional
    public Subject createSubject(Subject subject) {
        Department existingDepartment = deptRepository.findDepartmentByDeptId(subject.getDepartment().getDeptId());
        if (existingDepartment == null) {
            throw new RuntimeException("Department does not exist");
        } else {
            return subRepository.save(subject);
        }
    }
    
    @Override
    public List<Subject> getAll() {
        List<Subject> subjects = subRepository.findAll();//findAll() fn exists by default
        if (subjects.isEmpty()) {
            throw new RuntimeException("No records found in subjects"); //exception to display msg if no record exists on calling GET
        }
        return subjects;
    }

    
    @Override
   	public Subject add(Subject sub) {    //POST
   	    if(subRepository.existsBySubName(sub.getSubName())){   //existsByName() doesn't exist by default in the JPA connection made in StudentRepository, hence we define it in Student repository
   			throw new RuntimeException("Duplicate subject record");//if record with same name is entered twice
   		}
   		return subRepository.save(sub);   //save() is also predefined
   	}
    
    
    
    
    
    
    @Override
    public Subject getById(Long Id) {
        if (!subRepository.existsById(Id)) {    // to check if the ID enetered doesnt exist
            throw new RuntimeException("Subject doesn't exist"); 
        }
        return subRepository.findById(Id).orElse(null);  //used optional datatype
    }

    @Override
    public String delete(Long Id) {
        if (!subRepository.existsById(Id)) {
            throw new RuntimeException("Subject not found");
        }
        subRepository.deleteById(Id);
        return "Subject Deleted";
    }

       
    
    @Override
  	public Subject edit(Subject subject) {    //PUT
  		if (subRepository.existsById(subject.getSubId())) {
  			return subRepository.save(subject);
  		} 
  		
  		else {
  			throw new RuntimeException("Subject not found"); //to check if the ID entered for modification exists or not
  		}
  	}    
    
    
    
    
    public Set<StudentSubject> getSubjectStudents(Long subjectId) {
        Subject subject = subRepository.findById(subjectId).orElseThrow(/* exception */);
        return subject.getStudentSubjects();
    }

    
}
