package com.Students.detail.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentRequest {
		private String deptName;

	    // getters and setters
	    public String getDeptName() {
	        return deptName;
	    }

	    public void setDeptName(String deptName) {
	        this.deptName = deptName;
	    }

}
