package com.Students.detail.service;

//import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Students.detail.dto.StudentCourseDTO;
import com.Students.detail.entity.Course;
import com.Students.detail.entity.Student;
import com.Students.detail.repository.CourseRepository;
import com.Students.detail.repository.StudentRepository;
import com.Students.detail.request.CourseStudentRequest;

import jakarta.transaction.Transactional;

@Service
public class CourseStudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CourseRepository courseRepository;
    
    @Transactional
    public void addCourseAndStudent(CourseStudentRequest request) {
        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        if (student.getSelectedCourses().contains(course)) {              // Checking if student is already associated to the course
            throw new RuntimeException("The student is already associated with the course");
        }

        student.getSelectedCourses().add(course);            // If not then associate the student with the specified course
        studentRepository.save(student);
    }
    
    
    public StudentCourseDTO getStudentCourse(int studentId, int courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
                
        return new StudentCourseDTO(student.getStudId(), student.getName(), course.getcId(), course.getCname());
    }


}