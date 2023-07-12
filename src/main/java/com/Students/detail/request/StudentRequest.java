package com.Students.detail.request;


import lombok.*;

//import lombok.Getter;
//import lombok.Setter;

@Getter
@Setter
public class StudentRequest {
	private String name;
    private String address;
    private String email;
  //  private String feeStatus;
    private Long departmentId;// only took departmentId instead of the whole department

    
    
    //just check if these are needed cause @Getter @Setter doesn't seem to function properly
 
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
//	public String getFeeStatus() {
//		return feeStatus;
//	}
//	public void setFeeStatus(String feeStatus) {
//		this.feeStatus = feeStatus;
//	}
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	
}
