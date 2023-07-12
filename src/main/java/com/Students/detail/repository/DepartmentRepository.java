package com.Students.detail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Students.detail.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
	boolean existsByDeptName(String name);  //custom function
	boolean existsById(Long deptId);

}