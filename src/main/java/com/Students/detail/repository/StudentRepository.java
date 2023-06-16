package com.Students.detail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Students.detail.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    boolean existsByName(String name);
}



