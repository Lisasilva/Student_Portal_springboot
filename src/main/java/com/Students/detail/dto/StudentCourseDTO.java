package com.Students.detail.dto;

public class StudentCourseDTO {
	 private int studId;
	 private String name;
	 private int cId;
	 private String cname;
	 
	 
	 public StudentCourseDTO() { //default constructor
			super();
		}
	 
	public StudentCourseDTO(int studId, String name, int cId, String cname) {
		super();
		this.studId = studId;
		this.name = name;
		this.cId = cId;
		this.cname = cname;
	}
	
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
	 
	 
}
