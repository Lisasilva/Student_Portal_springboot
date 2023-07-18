package com.Students.detail.dto;

import java.util.*;
import java.util.Map;

import com.Students.detail.entity.Department;
import com.Students.detail.entity.Student;
import com.Students.detail.entity.Subject;


public class StudentDto {
	
	    private Long id;
	    private String name;
	    private String address;
	    private String email;
	    private String department;
	    private List<String> subjects = new ArrayList<>();
//	    private Map<Subject, Integer> marks = new HashMap<>();
//	    private Map<Subject, Double> percentages = new HashMap<>();
	    private Map<String, Integer> marks = new HashMap<>();
	    private Map<String, Double> percentages = new HashMap<>();

	    
	    
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
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
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getDepartment() {
			return department;
		}
		public void setDepartment(String department) {
			this.department = department;
		}
		public List<String> getSubjects() {
			return subjects;
		}
		public void setSubjects(List<String> subjects) {
			this.subjects = subjects;
		}
		public Map<String, Integer> getMarks() {
			return marks;
		}
		public void setMarks(Map<String, Integer> marks) {
			this.marks = marks;
		}
		public Map<String, Double> getPercentages() {
			return percentages;
		}
		public void setPercentages(Map<String, Double> percentages) {
			this.percentages = percentages;
		}    
	    
	}




//public Map<Subject, Integer> getMarks() {
//	return marks;
//}
//public void setMarks(Map<Subject, Integer> marks) {
//	this.marks = marks;
//}
//public Map<Subject, Double> getPercentages() {
//	return percentages;
//}
//public void setPercentages(Map<Subject, Double> percentages) {
//	this.percentages = percentages;
//}	