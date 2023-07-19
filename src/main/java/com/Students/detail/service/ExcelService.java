package com.Students.detail.service;


import com.Students.detail.entity.*;
import com.Students.detail.repository.StudentRepository;
import com.Students.detail.repository.StudentSubjectRepository;
import com.Students.detail.repository.SubjectRepository;
import com.Students.detail.repository.DepartmentRepository;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.Iterator;

@Service
public class ExcelService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private SubjectRepository subjectRepository;
    
    @Autowired
    private StudentSubjectRepository stuSubRepository;
    
    
    //public void save(MultipartFile file, String entityType) {
    public List<Integer> save(MultipartFile file, String entityType) {
    	int countUpdated = 0;
        int countAppended = 0;
        
        try {
            Workbook workbook = new XSSFWorkbook(file.getInputStream());// Parse the file and save each line as a new student
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();

            while (rows.hasNext()) {
                Row currentRow = rows.next();

                if (currentRow.getRowNum() == 0) {  // this skips header
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();
                  
                
               
        	    
                switch(entityType) { //used switch case to decide which of three API call is being used and based on that decide the columns
  
                
                
                
                //API call for POST is http://localhost:8080/excel/upload/department
	            	case "department":
	            		Department department = new Department();
	            		String deptName = null;
//	            		countUpdated = 0;
//	            		countAppended = 0;
	            		
	            		while (cellsInRow.hasNext()) {
	            		    Cell currentCell = cellsInRow.next();

	            		    if (currentCell.getColumnIndex() == 0) {
	            		    	deptName = currentCell.getStringCellValue(); // deptName
	            		    }
	            		}

	            		// to check if this department name already exists
	            		Department existingDept = departmentRepository.findByDeptName(deptName);
	            		if (existingDept != null) {
	            		    //System.out.println("Department " + deptName + " already exists!");  //dont append to the DB
	            			countUpdated++;
	            		} 
	            		else {
	            		    department.setDeptName(deptName);
	            		    departmentRepository.save(department);
	            		    //System.out.println("Created record for department " + department.getDeptId());
	            		    countAppended++;
	            		}
	            		break;

	                    
	                    
                
                	//API call for POST is http://localhost:8080/excel/upload/student

	            	case "student":
	            	    if (departmentRepository.count() == 0) {
	            	        throw new RuntimeException("No Department data exists. Please upload department data first");
	            	    }
	            	    
	            	    String email = null;
	            	    String name = null;
	            	    String address = null;
	            	    Long departmentId = null;
//	            	    countUpdated=0;
//	            	    countAppended=0;
	            	    
	            	    while (cellsInRow.hasNext()) {
	            	        Cell currentCell = cellsInRow.next();
	            	        
                            //since columns are in order: studId,address, email, name
	            	        
	            	        if (currentCell.getColumnIndex() == 0) {
	            	            name = currentCell.getStringCellValue(); //name
	            	        } 
	            	        else if (currentCell.getColumnIndex() == 1) { 
	            	            email = currentCell.getStringCellValue();//email
	            	        }
	            	        else if (currentCell.getColumnIndex() == 2) { 
	            	            address = currentCell.getStringCellValue();//address
	            	        }
	            	        else if (currentCell.getColumnIndex() == 3) {
	            	            double numericValue = currentCell.getNumericCellValue();
	            	            departmentId = (long) numericValue;
	            	        }
	            	    }
	            	    
	            	    // Check if student with this email already exists, if yes, then i update it, if no, then append to the database
	            	    Student student = studentRepository.findByEmail(email);
	            	    Department dept = departmentRepository.findById(departmentId).orElse(null);

	            	    if (dept == null) {
	            	        throw new RuntimeException("Department does not exist");
	            	    }

	            	    if (student != null) {    	        // If it does, update the existing record
	            	        student.setName(name);
	            	        student.setAddress(address);
	            	        student.setDepartment(dept);
	            	        studentRepository.save(student);
	            	        //System.out.println("Updated record for student " + student.getId());
	            	        countUpdated++;
	            	    } 
	            	    else {   	            	        // If it does not, create a new record
	            	        Student newStudent = new Student();
	            	        newStudent.setName(name);
	            	        newStudent.setEmail(email);
	            	        newStudent.setAddress(address);
	            	        newStudent.setDepartment(dept);
	            	        studentRepository.save(newStudent);
	            	        //System.out.println("Created record for student " + newStudent.getId());
	            	        countAppended++;
	            	    }
	            	    break;
                       
	            	    
                     // Assuming the API call for POST is http://localhost:8080/excel/upload/subject
	            	case "subject":
	            	    if (departmentRepository.count() == 0) {
	            	        throw new RuntimeException("No Department data exists. Please upload department data first");
	            	    }

	            	    String subName = null;
	            	    int sem = 0;
	            	    int totMarks = 0;
	            	    Department dept1 = null;

//	            	    countUpdated = 0;
//	            	    countAppended = 0;

	            	    while (cellsInRow.hasNext()) {
	            	        Cell currentCell = cellsInRow.next();

	            	        // Columns are in order: subName, sem, totMarks, departmentId

	            	        if (currentCell.getColumnIndex() == 0) { 
	            	            subName = currentCell.getStringCellValue();
	            	        }
	            	        else if (currentCell.getColumnIndex() == 1) { 
	            	            double numericValue = currentCell.getNumericCellValue();
	            	            sem = (int) numericValue;
	            	        }
	            	        else if (currentCell.getColumnIndex() == 2) {
	            	            double numericValue = currentCell.getNumericCellValue();
	            	            totMarks = (int) numericValue;
	            	        }
	            	        else if (currentCell.getColumnIndex() == 3) {
	            	            double numericValue = currentCell.getNumericCellValue();
	            	            dept1 = departmentRepository.findById((long) numericValue).orElse(null);
	            	            if (dept1 == null) {
	            	                throw new RuntimeException("Department does not exist");
	            	            }
	            	        }
	            	    }

	            	    // Check if subject with this name already exists
	            	    Subject existingSubject = subjectRepository.findBySubName(subName);
	            	    if (existingSubject != null) {     // If it does, update the existing record
	            	        existingSubject.setSem(sem);
	            	        existingSubject.setTotMarks(totMarks);
	            	        existingSubject.setDepartment(dept1);
	            	        subjectRepository.save(existingSubject);
	            	        countUpdated++;
	            	    } 
	            	    else {   	            	        // If it does not, create a new record
	            	        Subject newSubject = new Subject();
	            	        newSubject.setSubName(subName);
	            	        newSubject.setSem(sem);
	            	        newSubject.setTotMarks(totMarks);
	            	        newSubject.setDepartment(dept1);
	            	        subjectRepository.save(newSubject);
	            	        countAppended++;
	            	    }
	            	    break;

	            	

                	case "student_subject":
                		if (studentRepository.count() == 0 || subjectRepository.count() == 0) {
                		    throw new RuntimeException("Student or Subject data do not exist. Please upload student and subject data first");
                		}
                		                     
                		Student stud = null;
                		Subject sub = null;
                		int marksObtained = 0;
//                		countUpdated = 0;
//	            	    countAppended = 0;


                		while (cellsInRow.hasNext()) {
                		    Cell currentCell = cellsInRow.next();

                		    if (currentCell.getColumnIndex() == 0) {
                		        double numericValue = currentCell.getNumericCellValue();
                		        stud = studentRepository.findById((long) numericValue).orElse(null);
                		        if (stud == null) {
                		            throw new RuntimeException("StudentId does not exist");
                		        }   
                		    } 
                		    else if (currentCell.getColumnIndex() == 1) {
                		        double numericValue = currentCell.getNumericCellValue();
                		        sub = subjectRepository.findById((long) numericValue).orElse(null);
                		        if (sub == null) {
                		            throw new RuntimeException("SubjectId does not exist");
                		        }
                		    } 
                		    else if (currentCell.getColumnIndex() == 2) {
                		        double numericValue = currentCell.getNumericCellValue();
                		        marksObtained = (int) numericValue;
                		    }
                		}

                		// Check if this combination of student and subject already exists
                		List<StudentSubject> existingStuSubList = stuSubRepository.findByStudentAndSubject(stud, sub);
                		if (!existingStuSubList.isEmpty()) {
                		    // If it does, update the existing record
                		    StudentSubject studentSubject = existingStuSubList.get(0);
                		    studentSubject.setMarksObtained(marksObtained);
                		    stuSubRepository.save(studentSubject);
                		    //System.out.println("Updated record for student " + stud.getId() + " and subject " + sub.getSubId());
                		    countUpdated++;

                		} 
                		else {
                		    // Otherwise, create a new record
                		    StudentSubject studentSubject = new StudentSubject();
                		    studentSubject.setStudent(stud);
                		    studentSubject.setSubject(sub);
                		    studentSubject.setMarksObtained(marksObtained);
                		    stuSubRepository.save(studentSubject);
                		    countAppended++;
                		}
                	    break;

                     default:
                        throw new IllegalArgumentException("Invalid entity type: " + entityType);
               }
            }
            workbook.close();

        }
        catch(IOException e) {
            throw new RuntimeException("File not stored. Error: " + e.getMessage());
        }
        
     // At the end of the method, returns counts:
     	List<Integer> counts = Arrays.asList(countUpdated, countAppended);
     	return counts;
    }
 
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
