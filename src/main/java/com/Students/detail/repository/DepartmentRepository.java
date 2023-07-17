package com.Students.detail.repository;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Students.detail.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
	boolean existsByDeptName(String deptName);
    boolean existsByDeptId(Long deptId); 
    Department findByDeptName(String deptName);

    @Query("SELECT d FROM Department d WHERE d.deptId = :deptId")
    Department findDepartmentByDeptId(@Param("deptId") Long deptId);
}