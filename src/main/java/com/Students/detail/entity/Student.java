package com.Students.detail.entity;

import java.util.Set;

import java.util.HashSet;
import org.springframework.data.relational.core.mapping.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
    
	@Column(name = "NAME", nullable = false)
    private String name;
		
	@Column(name = "ADDRESS", nullable = false)
	private String address;
	
	@Column(name = "EMAIL", nullable = false)
    private String email;
	
//	@Column(name = "FEE_STATUS")
//	private String feeStatus;

	//@JsonBackReference
	@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "deptId", nullable = false)
    private Department department;

	 @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
	 @JsonManagedReference
	 private Set<StudentSubject> studentSubjects = new HashSet<>();

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
	
	
	//modifications


    public Set<StudentSubject> getStudentSubjects() {
        return studentSubjects;
    }

    public void setStudentSubjects(Set<StudentSubject> studentSubjects) {
        this.studentSubjects = studentSubjects;
    }
    
    
    
    public Subject getSubjectByName(String subjectName) {
        return studentSubjects.stream().filter(studentSubject -> studentSubject.getSubject().getSubName().equals(subjectName)).map(StudentSubject::getSubject).findFirst().orElse(null);
    }

	
}
