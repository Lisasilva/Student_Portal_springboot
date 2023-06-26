package com.Students.detail.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "MARKS")
public class Marks {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int marksId;
    
        
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
    
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;
    
    @Column(name = "MARKS")
    private int marks;
    
    public Marks(int studentId, int courseId, int marks) {
        super();
        this.student = new Student();
        this.student.setStudId(studentId);
        this.course = new Course();
        this.course.setcId(courseId);
        this.marks = marks;
    }

	public Marks() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getMarksId() {
		return marksId;
	}

	public void setMarksId(int marksId) {
		this.marksId = marksId;
	}

	
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	} 
    
	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}
}
