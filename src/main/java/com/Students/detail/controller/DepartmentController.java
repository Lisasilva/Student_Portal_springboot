package com.Students.detail.controller;

import com.Students.detail.entity.Department;
import com.Students.detail.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dept")
public class DepartmentController {

    private final DepartmentService deptService;

    @Autowired
    public DepartmentController(DepartmentService deptService) {
        this.deptService = deptService;
    }

    @GetMapping("/get/all")
    public List<Department> getAllDepartments() {
        return deptService.getAll();
    }
    
    @PostMapping("/add")
    public Department addDepartment(@RequestBody Department department) {
        return deptService.add(department);
    }
       
    @GetMapping("/department")
	public Department getDepartmentById(@RequestParam Long id){
	    return deptService.getById(id);
	}
    
    @DeleteMapping("/delete")
    public String deleteDepartment(@RequestParam Long id) {
        return deptService.delete(id);
    }
    
    @PutMapping("/update")
    public Department updateDepartment(@RequestBody Department department) {
        return deptService.edit(department);
    }
}






/*
the next three crud operations using pathvariable instead of using one parameter

@GetMapping("/department/{id}")
public Department getDepartmentById(@PathVariable Long id) {
   return deptService.getDepartmentById(id);
}

@PutMapping("/update/{id}")
public Department updateDepartment(@PathVariable Long id, @RequestBody Department department) {
   return deptService.updateDepartment(id, department);
}

@DeleteMapping("/delete/{id}")
public void deleteDepartment(@PathVariable Long id) {
   deptService.deleteDepartment(id);
}*/

