package com.Students.detail.repository;

//import com.Students.detail.dto.StudentCourseMarksDTO;
import com.Students.detail.entity.Course;
import com.Students.detail.entity.Marks;
import com.Students.detail.entity.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MarksRepository extends JpaRepository<Marks, Integer> {

	Marks findByStudent_StudIdAndCourse_cId(int studId, int cId);//custom method to find by ids
	
	List<Marks> findByStudentAndCourse(Student student, Course course);//custom method to find the whole student-course record
    
	List<Marks> findAllByStudentStudId(int studentId); //custom method to find by studentId

	@Query(value= "SELECT COUNT(DISTINCT student_id) FROM Marks WHERE course_id = ?1", nativeQuery=true)
	int countByCourse(int id);

}
