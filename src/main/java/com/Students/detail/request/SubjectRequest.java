package com.Students.detail.request;

public class SubjectRequest {

    private String subName;
    private float totMarks;
    private float marksObtained;
    private int sem;
    private Long departmentId; // Here we are only taking the departmentId instead of the whole department
	
    
    
    //getters and setters
    public String getSubName() {
		return subName;
	}
	public void setSubName(String subName) {
		this.subName = subName;
	}
	public float getTotMarks() {
		return totMarks;
	}
	public void setTotMarks(float totMarks) {
		this.totMarks = totMarks;
	}
	public float getMarksObtained() {
		return marksObtained;
	}
	public void setMarksObtained(float marksObtained) {
		this.marksObtained = marksObtained;
	}
	public int getSem() {
		return sem;
	}
	public void setSem(int sem) {
		this.sem = sem;
	}
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

}
