package com.Students.detail.repository;

import com.Students.detail.entity.Student;
import com.Students.detail.entity.StudentSubject;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentSubjectRepository extends JpaRepository<StudentSubject, Long> {

	List<StudentSubject> findByStudent(Student student);


}
