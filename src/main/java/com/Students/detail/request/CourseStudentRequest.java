package com.Students.detail.request;

public class CourseStudentRequest {
    private Integer studentId;
    private Integer courseId;

    // getters and setters
    public Integer getStudentId() {
        return this.studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getCourseId() {
        return this.courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
}