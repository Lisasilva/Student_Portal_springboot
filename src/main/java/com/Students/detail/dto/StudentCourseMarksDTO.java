package com.Students.detail.dto;

public class StudentCourseMarksDTO {
    private int studId;
    private String name;
    private int cId;
    private String cname;
    private int marks;

    // constructors
    public StudentCourseMarksDTO() {
        super();
    }

    public StudentCourseMarksDTO(int studId, String name, int cId, String cname, int marks) {
        super();
        this.studId = studId;
        this.name = name;
        this.cId = cId;
        this.cname = cname;
        this.marks = marks;
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

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}

    
}
