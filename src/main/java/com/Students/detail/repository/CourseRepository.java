package com.Students.detail.repository;

import com.Students.detail.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {

	boolean existsByCname(String cname);
}