package com.Students.detail.entity;

import java.util.Set;

import java.util.HashSet;
import org.springframework.data.relational.core.mapping.Table;

//import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
//@Data
@Table(name = "STUDENT")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Column(name = "ID")
	private int studId;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "ADDRESS")
	private String address;
		
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)	
	@JoinTable(
	        name = "student_courses", 
	        joinColumns = {@JoinColumn(name = "student_id", referencedColumnName = "studId")}, 
	        inverseJoinColumns = {@JoinColumn(name = "course_id", referencedColumnName = "cId")})
	
	
	private Set<Course> selectedCourses = new HashSet<>();

		
	//constructors:
	public Student() {
		super();
	}
	public Student(int studId, String name, String address) {
		super();
		this.name = name;
		this.address = address;
	}
	
	public Student(String name, String address) {
		this.name = name;
		this.address = address;
	}

	
	
	//getters and setters
	public int getStudId() {
		return studId;
	}
	public void setStudId(int studId) {
		this.studId = studId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
	
	
	//@JsonIgnore
	//this shud be in either Course or Student class, not both
	public Set<Course> getSelectedCourses() {
	    return selectedCourses;
	}

	public void setSelectedCourses(Set<Course> selectedCourses) {
	    this.selectedCourses = selectedCourses;
	}

}
