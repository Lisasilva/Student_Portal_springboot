package com.Students.detail.entity;

import java.util.Set;

import java.util.HashSet;
import org.springframework.data.relational.core.mapping.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.*;
//import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;


@Entity
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
public class Student {
	
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    
	@Column(name = "NAME")
    private String name;
		
	@Column(name = "ADDRESS")
	private String address;
	
	@Column(name = "EMAIL")
    private String email;
	
//	@Column(name = "FEE_STATUS")
//	private String feeStatus;

	@JsonBackReference
    @ManyToOne
    @JoinColumn(name = "deptId", nullable = false)
    private Department department;

    
    
    //getters and setters for all the variables used in the class
    
	public Long getId() {
	    return this.id;
	}


	public void setId(Long Id) {
		this.id = Id;
	}
	
	public String getName() {
		return this.name;
	}
	public void setName(String Name) {
		this.name = Name;
	}
    
	
	public String getAddress() {
		return address;
	}


	public void setAddress(String Address) {
		this.address = Address;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String Email) {
		this.email = Email;
	}


	public Department getDepartment() {
		return department;
	}


	public void setDepartment(Department Department) {
		this.department = Department;
	}
	

//	public Long getDeptId() {
//		return deptId;
//	}
//	public void setDeptId(Long deptId) {
//		this.deptId = deptId;
//	}
	
	
}









//
//@Entity
////@Data
//@Table(name = "STUDENT")
//public class Student {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	//@Column(name = "ID")
//	private int studId;
//	
//	@Column(name = "NAME")
//	private String name;
//	
//	@Column(name = "ADDRESS")
//	private String address;
//			
//	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)	
//	@JoinTable(
//	        name = "student_courses", 
//	        joinColumns = {@JoinColumn(name = "student_id", referencedColumnName = "studId")}, 
//	        inverseJoinColumns = {@JoinColumn(name = "course_id", referencedColumnName = "cId")})
//	
//	
//	private Set<Course> selectedCourses = new HashSet<>();
//
//		
//	//constructors:
//	public Student() {
//		super();
//	}
//	public Student(int studId, String name, String address) {
//		super();
//		this.name = name;
//		this.address = address;
//	}
//	
//	public Student(String name, String address) {
//		this.name = name;
//		this.address = address;
//	}
//
//	
//	
//	//getters and setters
//	public int getStudId() {
//		return studId;
//	}
//	public void setStudId(int studId) {
//		this.studId = studId;
//	}
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public String getAddress() {
//		return address;
//	}
//	public void setAddress(String address) {
//		this.address = address;
//	}
//	
//	
//	
//	
//	
//	//@JsonIgnore
//	//this shud be in either Course or Student class, not both
//	public Set<Course> getSelectedCourses() {
//	    return selectedCourses;
//	}
//
//	public void setSelectedCourses(Set<Course> selectedCourses) {
//	    this.selectedCourses = selectedCourses;
//	}
//
//}
