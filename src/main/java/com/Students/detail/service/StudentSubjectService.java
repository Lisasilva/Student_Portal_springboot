package com.Students.detail.service;

import com.Students.detail.dto.StudentSubjectDto;
import com.Students.detail.entity.Student;
import com.Students.detail.entity.StudentSubject;
import com.Students.detail.entity.Subject;
import com.Students.detail.repository.StudentRepository;
import com.Students.detail.repository.StudentSubjectRepository;
import com.Students.detail.repository.SubjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentSubjectService {
    @Autowired
    private StudentSubjectRepository studentSubjectRepository;
    
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    
    
    public List<StudentSubject> getAll() {
        return studentSubjectRepository.findAll();
    }

    public StudentSubject add(StudentSubjectDto studentSubjectDto) {
        StudentSubject studentSubject = new StudentSubject();
        studentSubject.setMarksObtained(studentSubjectDto.getMarksObtained());
        
        Student student = studentRepository.findById(studentSubjectDto.getStudentId())
            .orElseThrow(() -> new RuntimeException("Student not found with id " + studentSubjectDto.getStudentId()));
        studentSubject.setStudent(student);
        
        Subject subject = subjectRepository.findById(studentSubjectDto.getSubjectId())
            .orElseThrow(() -> new RuntimeException("Subject not found with id " + studentSubjectDto.getSubjectId()));
        studentSubject.setSubject(subject);
        
        return studentSubjectRepository.save(studentSubject);
    }


    public StudentSubject getById(Long id) {
        return studentSubjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("StudentSubject not found: " + id));
    }

    public String delete(Long id) {
        studentSubjectRepository.deleteById(id);
        return "StudentSubject removed !! " + id;
    }

    public StudentSubject edit(StudentSubject studentSubject) {
        StudentSubject existingStudentSubject = studentSubjectRepository.findById(studentSubject.getId())
                .orElseThrow(() -> new RuntimeException("StudentSubject not found: " + studentSubject.getId()));
        
        Long studentId = studentSubject.getStudent().getId();
        Long subjectId = studentSubject.getSubject().getSubId();
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found: " + studentId));
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found: " + subjectId));

        existingStudentSubject.setSubject(subject);
        existingStudentSubject.setStudent(student);
        existingStudentSubject.setMarksObtained(studentSubject.getMarksObtained());
        
        return studentSubjectRepository.save(existingStudentSubject);
        }
}
