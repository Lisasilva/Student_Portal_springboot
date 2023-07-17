package com.Students.detail.service;

import com.Students.detail.entity.Department;
import com.Students.detail.repository.DepartmentRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService implements BaseService<Department>{

    @Autowired     //implicit dependency obj
    private DepartmentRepository deptRepository; //provides methods to interact with the DB using JPA ig

    
    
    @Transactional
    public Department createDepartment(Department department) {
        Department existingDepartment = deptRepository.findByDeptName(department.getDeptName());
        if (existingDepartment != null) {
            throw new RuntimeException("Department already exists");
        } else {
            return deptRepository.save(department);
        }
    }

    
    @Override
    public List<Department> getAll() {	//GET
        List<Department> departments = deptRepository.findAll(); //findAll() fn exists by default
        if (departments.isEmpty()) {
            throw new RuntimeException("No records found in Department");
        }
        return departments;
    }

    
    

    @Override
    public Department add(Department dept) { //POST
	    if(deptRepository.existsByDeptName(dept.getDeptName())){   //existsByName() doesn't exist by default in the JPA connection made in StudentRepository, hence we define it in Student repository
			throw new RuntimeException("Duplicate Department record");//if record with same name is entered twice
		}
        return deptRepository.save(dept);   //save() is also predefined
	}
      
    
    @Override
    public Department edit(Department dept) {	//PUT
        if (!deptRepository.existsById(dept.getDeptId())) {  //to check if the ID entered for modification exists or not
            throw new RuntimeException("Department not found");
        }
        return deptRepository.save(dept);
    }
   
        
    @Override
    public String delete(Long Id) {
        if (!deptRepository.existsById(Id)) {
            throw new RuntimeException("Department not found");
        }
        deptRepository.deleteById(Id);
        return "Department Deleted";
    }
 
    
    
    @Override
    public Department getById(Long Id) {
        if (!deptRepository.existsByDeptId(Id)) {    
            throw new RuntimeException("Department Record doesn't exist"); 
        }
        Department department = deptRepository.findDepartmentByDeptId(Id); 
        if(department == null) {
            throw new RuntimeException("Department Record is null");
        }
        return department;
    }
}


























