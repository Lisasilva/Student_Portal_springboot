package com.Students.detail.service;

import com.Students.detail.dto.StudentCourseDTO;
import com.Students.detail.entity.Course;
import com.Students.detail.entity.Student;
import com.Students.detail.repository.CourseRepository;
import com.Students.detail.repository.StudentRepository;
import com.Students.detail.request.CourseStudentRequest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class CourseStudentService implements BaseService<CourseStudentRequest> {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

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

    @Override
    public List<CourseStudentRequest> getAll() {
        throw new UnsupportedOperationException("Method not supported");
    }

    @Override
    public CourseStudentRequest add(CourseStudentRequest obj) {
        CourseStudentRequest request = (CourseStudentRequest) obj;
        addCourseAndStudent(request);
        return null;
    }

    @Override
    public CourseStudentRequest edit(CourseStudentRequest obj) {
        throw new UnsupportedOperationException("Method not supported");
    }

    @Override
    public String delete(int id) {
        throw new UnsupportedOperationException("Method not supported");
    }

    @Override
    public CourseStudentRequest getById(int id) {
        throw new UnsupportedOperationException("Method not supported");
    }
}
