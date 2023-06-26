package com.Students.detail.service;

import com.Students.detail.entity.Student;
import com.Students.detail.entity.Course;
import com.Students.detail.entity.Marks;
import com.Students.detail.dto.StudentCourseMarksDTO;
import com.Students.detail.repository.StudentRepository;
import com.Students.detail.repository.CourseRepository;
import com.Students.detail.repository.MarksRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
//import java.util.Optional;

@Service
public class MarksService {
	
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private MarksRepository marksRepository;
    
   
    //ADD A NEW RECORD
    public Marks add(int studentId, int courseId, int marks) {
        
    	//checks if the student_id - course_id combo exists in student_courses table
        int count = studentRepository.countByStudentAndCourse(studentId, courseId); 
        //if it doesn't exist, throw exception
        if(count == 0) {
            throw new RuntimeException("The student-course combination does not exist in the student_courses table.");
        }

        //if it exists, find the student and course using the studentId and courseId
        Student student = studentRepository.findById(studentId).get();
        Course course = courseRepository.findById(courseId).get();
        
        // to check if a marks record already exists for this student-course combination
        List<Marks> existingMarks = marksRepository.findByStudentAndCourse(student, course);
        if(!existingMarks.isEmpty()) {
            throw new RuntimeException("Marks for this student-course combination already exists.");
        }

        Marks mark = new Marks();
        mark.setStudent(student);
        mark.setCourse(course);
        mark.setMarks(marks);
        return marksRepository.save(mark);
    }

  
    //DISPLAY ALL THE RECORDS IN MARKS TABLE
    public List<StudentCourseMarksDTO> getAll() {
        List<Marks> marks = marksRepository.findAll();

        if(marks.isEmpty()) {
            throw new RuntimeException("No records found");
        }

        List<StudentCourseMarksDTO> markDTOs = marks.stream().map(mark -> new StudentCourseMarksDTO(
                mark.getStudent().getStudId(),
                mark.getStudent().getName(),
                mark.getCourse().getcId(),
                mark.getCourse().getCname(),
                mark.getMarks())).collect(Collectors.toList());

        return markDTOs;
    }
   

    //DISPLAY THE RECORD WITH THE GIVEN ID
    public StudentCourseMarksDTO getById(int marksId) {
    	
    	Marks marks = marksRepository.findById(marksId).orElseThrow(() -> new RuntimeException("No record found for this marksId"));

        StudentCourseMarksDTO score = new StudentCourseMarksDTO(
                marks.getStudent().getStudId(),
                marks.getStudent().getName(),
                marks.getCourse().getcId(),
                marks.getCourse().getCname(),
                marks.getMarks());

        return score;    
    }
   
    
    //UPDATE THE MARKS OF A RECORD WITH GIVEN STUDENT ID AND COURSE ID
    public StudentCourseMarksDTO update( int studentId, int courseId, int marks) {
    	
        //checks if the student_id - course_id combo exists in student_courses table
        int count = studentRepository.countByStudentAndCourse(studentId, courseId); 
        //if it doesn't exist, throw exception
        if(count == 0) {
            throw new RuntimeException("The student-course combination does not exist in the student_courses table.");
        }

        //if it exists, find the student and course using the studentId and courseId
        Student student = studentRepository.findById(studentId).get();
        Course course = courseRepository.findById(courseId).get();
        
        //Fetch the Marks object for the student-course pair into mark if only it exists or else throw an error
        Marks mark = marksRepository.findByStudentAndCourse(student, course).stream().findFirst().orElseThrow(() -> new RuntimeException("No marks record found for this student-course combination"));
        
       //if exists ,update marks
        mark.setMarks(marks);
        
        //save updated marks
        marksRepository.save(mark);

        StudentCourseMarksDTO studentCourseMarksDTO = new StudentCourseMarksDTO();
        studentCourseMarksDTO.setStudId(student.getStudId());
        studentCourseMarksDTO.setName(student.getName());
        studentCourseMarksDTO.setcId(course.getcId());
        studentCourseMarksDTO.setCname(course.getCname());
        studentCourseMarksDTO.setMarks(mark.getMarks());

        return studentCourseMarksDTO;
    }


    //DELETE A RECORD FROM MARKS
    public void delete(int marksId) {
        marksRepository.deleteById(marksId);
    }
    
    //FETCH MARK OF A STUDENT FOR A GIVEN COURSE 
    public StudentCourseMarksDTO getStudentCourseMarks(int studentId, int courseId) {
    	
    	   
    	//checks if the student_id - course_id combo exists in student_courses table
        int count = studentRepository.countByStudentAndCourse(studentId, courseId); 
        //if it doesn't exist, throw exception
        if(count == 0) {
            throw new RuntimeException("The student-course combination does not exist in the student_courses table.");
        }

        //if it exists, find the student and course using the studentId and courseId
        Student student = studentRepository.findById(studentId).get();
        Course course = courseRepository.findById(courseId).get();
        
        
        
        //Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
        //Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
        Marks marks = marksRepository.findByStudent_StudIdAndCourse_cId(studentId,courseId);;

        StudentCourseMarksDTO studentCourseMarksDTO = new StudentCourseMarksDTO();
        studentCourseMarksDTO.setStudId(student.getStudId());
        studentCourseMarksDTO.setName(student.getName());
        studentCourseMarksDTO.setcId(course.getcId());
        studentCourseMarksDTO.setCname(course.getCname());
        studentCourseMarksDTO.setMarks(marks.getMarks());

        return studentCourseMarksDTO;
    }

    // GET THE TOTAL MARK OF ALL REGISTERED COURSES FOR A STUDENT 
    public int getTotalMarks(int studentId){
        List<Marks> marksList = marksRepository.findAllByStudentStudId(studentId);
        int totalMarks = 0 ;
        for(Marks marks: marksList) {
            totalMarks += marks.getMarks();
        }
        return totalMarks;
    }
    
    
    
    // GET THE PERCENTAGE OF TOTAL MARK OF ALL REGISTERED COURSES FOR A STUDENT 
    public double getPercentage(int studentId){
    	List<Marks> marksList = marksRepository.findAllByStudentStudId(studentId);
        int totalMarks = 0,noOfSub=0 ;
        for(Marks marks: marksList) {
        	noOfSub++;
            totalMarks += marks.getMarks();
        }
    	double percentage = (totalMarks /noOfSub); //percentage calculation based on the no of courses the student is enrolled in
        return percentage;
    }
    
    //GET NO OF STUDENTS COURSEWISE
        public int getStudentNumber(int id) {
            int count = marksRepository.countByCourse(id); 
            if(count == 0) {
                throw new RuntimeException("No student registered for this course");
            }
            return count;
        }
        
}
 	  
