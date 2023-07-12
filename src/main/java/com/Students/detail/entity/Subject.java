package com.Students.detail.entity;

import java.util.Set;

import java.util.HashSet;
import java.util.List;

import org.springframework.data.relational.core.mapping.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

//import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

import lombok.*;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "SUBJECT")
public class Subject {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SUB_ID")
    private Long subId;
    
    @Column(name = "SUB_NAME")
    private String subName;

    @Column(name = "TOT_MARKS")
    private int totMarks;

    @Column(name = "MARKS_OBTAINED")
    private int marksObtained;

    @Column(name = "SEM")
    private int sem;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "deptId", nullable = false)
    private Department department;

    
    
    
    //Getters and Setters
	public Long getSubId() {
		return subId;
	}

	public void setSubId(Long subId) {
		this.subId = subId;
	}

	public String getSubName() {
		return subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}

	public int getTotMarks() {
		return totMarks;
	}

	public void setTotMarks(int totMarks) {
		this.totMarks = totMarks;
	}

	public int getMarksObtained() {
		return marksObtained;
	}

	public void setMarksObtained(int marksObtained) {
		this.marksObtained = marksObtained;
	}

	public int getSem() {
		return sem;
	}

	public void setSem(int sem) {
		this.sem = sem;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
    
    
    
    
}