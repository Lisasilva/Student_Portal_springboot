package com.Students.detail.entity;

import java.util.HashSet;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "COURSE")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cId;

	@Column(name = "COURSE_NAME")
    private String cname;
	
	@Column(name = "TextBook")
	private String textbook;
	
	@ManyToMany(mappedBy = "selectedCourses", fetch = FetchType.LAZY)
	private Set<Student> students = new HashSet<>();

	
	
	
	public Course() { //default constructor
		super();
	}
	
	public Course(int cId, String cname, String textbook) {
		super();
		//this.cId = cId;
		this.cname = cname;
		this.textbook = textbook;
	}
	
	public Course(String cname, String textbook) {
		this.cname = cname;
		this.textbook = textbook;
	}

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getTextbook() {
		return textbook;
	}

	public void setTextbook(String textbook) {
		this.textbook = textbook;
	}

	
	@JsonIgnore
	public Set<Student> getStudents() {
	    return students;
	}

	public void setStudents(Set<Student> students) {
	    this.students = students;
	}

	  
}
