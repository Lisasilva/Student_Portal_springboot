package com.Students.detail.repository;

//import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

//import org.springframework.data.jpa.repository.JpaRepository;
import com.Students.detail.entity.Student;

//public interface StudentRepository extends JpaRepository<Student, Integer> {
//    boolean existsByName(String name); //custom function
//
//    
//    //this query is being used in MarksService.java to check if student_id - course_id combo exists in student_courses table
//    @Query(value = "SELECT COUNT(*) FROM student_courses WHERE student_id = ?1 AND course_id = ?2", nativeQuery = true)
//    int countByStudentAndCourse(int studentId, int courseId);
//    
//    Student findByName(String name); //this is required for uploading excel files
//
//}



@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    boolean existsByName(String name);  //custom function
    boolean existsByEmail(String email);



}


 