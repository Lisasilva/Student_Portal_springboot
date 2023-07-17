package com.Students.detail.entity;

import jakarta.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class StudentSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    //@JsonBackReference
    @JsonIgnore
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    //@JsonBackReference
    @JsonIgnore
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @Column(name = "MARKS_OBTAINED", nullable = false)
    private int marksObtained;

    
    
    
    //Getters and setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public int getMarksObtained() {
		return marksObtained;
	}

	public void setMarksObtained(int marksObtained) {
		this.marksObtained = marksObtained;
	}
    
    
    
    
}