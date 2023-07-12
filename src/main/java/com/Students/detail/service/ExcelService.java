package com.Students.detail.service;


import com.Students.detail.entity.*;
import com.Students.detail.repository.StudentRepository;
import com.Students.detail.repository.SubjectRepository;
import com.Students.detail.repository.DepartmentRepository;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
//import java.io.InputStream;
import java.util.Iterator;

@Service
public class ExcelService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private SubjectRepository subjectRepository;
    
    public void save(MultipartFile file, String entityType) {
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
                    
                
                	//API call for POST is http://localhost:8080/excel/upload/student
                	case "student":
                        Student student = new Student();
                        while (cellsInRow.hasNext()) {
                            Cell currentCell = cellsInRow.next();
                            
                            //since columns are in order: studId,address, email, name
                            
                            if (currentCell.getColumnIndex() == 0) { 
                                double numericValue = currentCell.getNumericCellValue();
                            	student.setId((long) numericValue);
                            } 
                            else if (currentCell.getColumnIndex() == 1) {
                                student.setName(currentCell.getStringCellValue()); //name
                            } 
                           
                            else if (currentCell.getColumnIndex() == 2) { 
                                student.setEmail(currentCell.getStringCellValue());//address
                            }
                            else if (currentCell.getColumnIndex() == 3) { 
                                student.setAddress(currentCell.getStringCellValue());//address
                            }
                            
                        }
                        studentRepository.save(student);
                        break;

                        
                    //API call for POST is http://localhost:8080/excel/upload/department
                	case "department":
                        Department department = new Department();
                        while (cellsInRow.hasNext()) {
                            Cell currentCell = cellsInRow.next();

                            // since columns are in order: deptId, deptName
                            if (currentCell.getColumnIndex() == 0) { 
                            	
                            	double numericValue = currentCell.getNumericCellValue();
                            	department.setDeptId((long) numericValue);
                            } 
                            else if (currentCell.getColumnIndex() == 1) {
                                department.setDeptName(currentCell.getStringCellValue());//deptName
                            } 
                            
                        }
                        departmentRepository.save(department);
                        break;
                        
                        
                                             
                     // Assuming the API call for POST is http://localhost:8080/excel/upload/subject

                	case "subject":
                	    Subject subject = new Subject();
                	    while (cellsInRow.hasNext()) {
                	        Cell currentCell = cellsInRow.next();

                	        // Columns are in order: subId, subName, sem, totMarks, marksObtained

                	        if (currentCell.getColumnIndex() == 0) { 
                	            double numericValue = currentCell.getNumericCellValue();
                	            subject.setSubId((long) numericValue);
                	        } 
                	        else if (currentCell.getColumnIndex() == 1) { 
                	            subject.setSubName(currentCell.getStringCellValue());
                	        }
                	        else if (currentCell.getColumnIndex() == 2) { 
                	            double numericValue = currentCell.getNumericCellValue();
                	            subject.setSem((int) numericValue);
                	        }
                	        else if (currentCell.getColumnIndex() == 3) {
                	            double numericValue = currentCell.getNumericCellValue();
                	            subject.setTotMarks((int) numericValue);
                	        }
                	        else if (currentCell.getColumnIndex() == 4) {
                	            double numericValue = currentCell.getNumericCellValue();
                	            subject.setMarksObtained((int) numericValue);
                	        } 
                	    }
                	    subjectRepository.save(subject);
                	    break;
   
                        
                        
                        
                }
            }
            workbook.close();

        }
        catch(IOException e) {
            throw new RuntimeException("File not stored. Error: " + e.getMessage());
        }
    }
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
