package com.Students.detail.service;

//import com.Students.detail.entity.Student;
//import com.Students.detail.entity.Course;
import com.Students.detail.entity.Marks;
import com.Students.detail.dto.StudentCourseMarksDTO;
import java.util.List;

public interface BaseMarksService {

	//this interface is exclusively for MarksService.java
   // Hence, it specifies the method signatures for each method declared in MarksService

    Marks add(int studentId, int courseId, int marks);
    
    List<StudentCourseMarksDTO> getAll();

    StudentCourseMarksDTO getById(int marksId);

    StudentCourseMarksDTO update(int studentId, int courseId, int marks);

    void delete(int marksId);

    StudentCourseMarksDTO getStudentCourseMarks(int studentId, int courseId);

    int getTotalMarks(int studentId);
    
    double getPercentage(int studentId);

    int getStudentNumber(int id);
}
