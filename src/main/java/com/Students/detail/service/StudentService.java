package com.Students.detail.service;

import com.Students.detail.dto.StudentDto;
import com.Students.detail.entity.Department;
import com.Students.detail.entity.Student;
import com.Students.detail.entity.StudentSubject;
import com.Students.detail.entity.Subject;
import com.Students.detail.repository.DepartmentRepository;
import com.Students.detail.repository.StudentRepository;
import com.Students.detail.repository.StudentSubjectRepository;
import com.Students.detail.repository.SubjectRepository;
import jakarta.transaction.Transactional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
//import com.Students.detail.service.DepartmentService;


import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StudentService implements BaseService<Student> {

	
	
	
    @Autowired     //implicit dependency obj
    private StudentRepository studRepository;    //provides methods to interact with the DB using JPA ig

    @Autowired
    private DepartmentRepository deptRepository; //provides methods to interact with the DB using JPA
    
    @Autowired
    private SubjectRepository subRepository; 
    
    @Autowired
    private StudentSubjectRepository studSubRepository;
    
    
    
    
    @Transactional
    public Student createStudent(Student student) {
        Department existingDepartment = deptRepository.findDepartmentByDeptId(student.getDepartment().getDeptId());
        if (existingDepartment == null) {
            throw new RuntimeException("Department does not exist");
        } else {
            return studRepository.save(student);
        }
    }
    
    @Override
	public List<Student> getAll(){    //GET
		List<Student> students = studRepository.findAll(); //findAll() fn exists by default
		if(students.isEmpty()) {
			throw new RuntimeException("No records found"); //exception to display msg if no record exists on calling GET
		}
		return students;
	}
	

    
//    @Override
//    public Student add(Student stud) {
//        // Get the department from student and check if the department exists in the DepartmentRepository
//        Department department = stud.getDepartment();
//        boolean departmentExists = deptRepository.existsById(department.getDeptId());
//
//        // Check if a student with the same email already exists
//        boolean duplicateEmailExists = studRepository.existsByEmail(stud.getEmail());
//
//        if(duplicateEmailExists || !departmentExists){   
//            throw new RuntimeException("Either duplicate record or department not found"); 
//        }
//
//        return studRepository.save(stud);   
//    }

    
    @Override
    public Student add(Student stud) {
        Long deptId = stud.getDepartment().getDeptId(); //to retrieve the department from student

        // Check if the department exists in the DepartmentRepository
        if(!deptRepository.existsById(deptId)) {
            throw new RuntimeException("Department not found");
        }

        if(studRepository.existsByEmail(stud.getEmail())){   
            throw new RuntimeException("Duplicate email"); 
        }


        Department department = deptRepository.findDepartmentByDeptId(deptId);
        if(department == null){
            throw new RuntimeException("Department not found"); 
        }


        stud.setDepartment(department);  // Sets retrieved department to the student
        return studRepository.save(stud);   
    }





    @Override
  	public Student edit(Student stud) {    //PUT
  		if (studRepository.existsById(stud.getId())) {
  			return studRepository.save(stud);
  		} 
  		
  		else {
  			throw new RuntimeException("Student not found"); //to check if the ID entered for modification exists or not
  		}
  	}    

    @Override
    public String delete(Long Id) {
        if (!studRepository.existsById(Id)) {      // to check if the ID entered doesnt exist
            throw new RuntimeException("Record doesn't exist");
        }
        studRepository.deleteById(Id);
        return "Deleted";
    }

    @Override
    public Student getById(Long Id) {
        if (!studRepository.existsById(Id)) {    // to check if the ID enetered doesnt exist
            throw new RuntimeException("Record doesn't exist"); 
        }
        return studRepository.findById(Id).orElse(null);  //used optional datatype
    }
    
    
    public Set<StudentSubject> getStudentSubjects(Long studentId) {
        Student student = studRepository.findById(studentId).orElseThrow(/* exception */);
        return student.getStudentSubjects();
    }

    
    
    //modificationsssss
    
//    public StudentDto getStudentDetails(Long studentId) {
//        Student student = studRepository.findById(studentId).orElseThrow(() -> new NotFoundException("Student not found"));
//        Department department = deptRepository.findById(student.getDepartmentId()).orElseThrow(() -> new NotFoundException("Department not found"));
//        Set<Subject> subjects = subRepository.findAllByDepartmentId(department.getId());
//        Map<String, Integer> marks = marksRepository.findAllByStudentId(studentId);
//        Double percentage = calculatePercentage(marks); 
//        return new StudentDto(student, department, subjects, marks, percentage);
//    }
    
    
//    public StudentDTO getStudentById(Long id) {
//        Student student = studentRepository.findById(id)
//            .orElseThrow(() -> new ResourceNotFoundException("Student", "id", id));
//        return convertToDTO(student);
//    }
//
//    
//    private studDto convertToDTO(Student student) {
//        StudentDTO studentDTO = new StudentDTO();
//        studentDTO.setId(student.getId());
//        studentDTO.setName(student.getName());
//        studentDTO.setAddress(student.getAddress());
//        studentDTO.setEmail(student.getEmail());
//        studentDTO.setDeptId(student.getDepartment().getDeptId());
//        studentDTO.setDeptName(student.getDepartment().getDeptName());
//        // convert subjects and calculate the overall percentage
//        List<StudentDTO.SubjectDTO> subjectDTOS = new ArrayList<>();
//        double overallMarks = 0;
//        double overallTotMarks = 0;
//        for (Subject subject : student.getSubjects()) {
//            StudentDTO.SubjectDTO subjectDTO = new StudentDTO.SubjectDTO();
//            subjectDTO.setSubId(subject.getDeptId());
//            subjectDTO.setSubName(subject.getDeptName());
//            subjectDTO.setTotMarks(subject.getTotalMarks());
//            subjectDTO.setSem(subject.getSemester());
//            subjectDTO.setPercentage((double) subject.getMarksObtained() / subject.getTotalMarks() * 100);
//            overallMarks += subject.getMarksObtained();
//            overallTotMarks += subject.getTotalMarks();
//            subjectDTOS.add(subjectDTO);
//        }
//        studentDTO.setSubjects(subjectDTOS);
//        studentDTO.setOverallPercentage(overallMarks / overallTotMarks * 100);
//        return studentDTO;
//    }
    
    
    
    
//    public Student getStudentWithDetails(Long studentId) {
//        Student student = null;
//        if (studRepository.findById(studentId).isPresent()) {
//            student = studRepository.findById(studentId).get();
//        } 
//        else {
//            throw new RuntimeException("Student not found"); 
//        }
//
//        Hibernate.initialize(student.getStudentSubjects()); // Initializing my  StudentSubject collection
//        Hibernate.initialize(student.getDepartment()); // Initializing the  Department
//        return student;
//    }
  
//   
//        List<StudentSubject> studentSubjects = studSubRepository.findByStudent(student);
//        
//        StudentDto studDto = new StudentDto();
//        studDto.setStudent(student);
//        studDto.setDepartment(student.getDepartment());
//        studDto.setSubjects(studentSubjects.stream().map(StudentSubject::getSubject).collect(Collectors.toList()));
//        //studDto.setMarks(studentSubjects.stream().collect(Collectors.toMap(StudentSubject::getSubject,StudentSubject::getMarksObtained)));
//        studDto.setMarks(studentSubjects.stream().collect(Collectors.toMap(StudentSubject::getSubject, studentSubject -> (double) studentSubject.getMarksObtained())));
//
//        return studDto;
//    }   
    
    public StudentDto getStudentDetails(Long studentId) {
        Student student = studRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));

        //here im converting Student entity to StudentDto
        StudentDto studentDto = convertToDto(student);
        return studentDto;
    }

    private StudentDto convertToDto(Student student) {
        StudentDto studentDto = new StudentDto();

        // mapping the required fields from student entity
        studentDto.setId(student.getId());
        studentDto.setName(student.getName());
        studentDto.setAddress(student.getAddress());
        studentDto.setEmail(student.getEmail());

        // mapping from department
        Department department = student.getDepartment();
        studentDto.setDepartment(department.getDeptName());

        // mapping subjects and marks
        Set<StudentSubject> studentSubjects = student.getStudentSubjects();
        for (StudentSubject studentSubject : studentSubjects) {
            Subject subject = studentSubject.getSubject();
            
            String subjectName = subject.getSubName(); 
            studentDto.getSubjects().add(subjectName);
            studentDto.getMarks().put(subjectName, studentSubject.getMarksObtained());
            
            //studentDto.getSubjects().add(subject.getSubName());
            //studentDto.getMarks().put(subject, studentSubject.getMarksObtained());
        }
        
        // Calculate percentage for each subject
        for (Map.Entry<String, Integer> entry : studentDto.getMarks().entrySet()) {
            String subjectName = entry.getKey();
            double percentage = ((double) entry.getValue() / student.getSubjectByName(subjectName).getTotMarks()) * 100;
            studentDto.getPercentages().put(subjectName, percentage); //subject is our key
        }

        return studentDto;
    }
    
    
    
    
    
    
    
    
    
    
//    public Map<Integer, StudentDto> getStudentDetailsBySemester(Long studentId) {
//        Student student = studRepository.findById(studentId)
//            .orElseThrow(() -> new RuntimeException("Student not found"));
//
//        Map<Integer, List<StudentSubject>> subjectsBySemester = studSubRepository.findByStudent(student).stream().collect(Collectors.groupingBy(StudentSubject::getSemester));
//
//        Map<Integer, StudentDto> detailsBySemester = new HashMap<>();
//        for (Map.Entry<Integer, List<StudentSubject>> entry : subjectsBySemester.entrySet()) {
//            StudentDto stuDto = new StudentDto();
//            stuDto.setStudent(student);
//            stuDto.setDepartment(student.getDepartment());
//            stuDto.setSubjects(entry.getValue().stream().map(StudentSubject::getSubject).collect(Collectors.toList()));
//            //stuDto.setMarks(entry.getValue().stream().collect(Collectors.toMap(StudentSubject::getSubject,StudentSubject::getMarksObtained)));
//            stuDto.setMarks(entry.getValue().stream().collect(Collectors.toMap(StudentSubject::getSubject, studentSubject -> (double) studentSubject.getMarksObtained())));
//
//            
//            
//            detailsBySemester.put(entry.getKey(), stuDto);
//        }
//        return detailsBySemester;
//    }


    
}
    
    




