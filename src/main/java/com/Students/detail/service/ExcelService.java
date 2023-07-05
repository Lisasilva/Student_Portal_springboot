package com.Students.detail.service;

/*import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Students.detail.entity.Course;
import com.Students.detail.entity.Student;
import com.Students.detail.entity.Marks;

import com.Students.detail.repository.CourseRepository;
import com.Students.detail.repository.MarksRepository;
import com.Students.detail.repository.StudentRepository;

import org.apache.poi.xssf.usermodel.*;

//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFRow;

@Service
public class ExcelUploadService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private MarksRepository marksRepository;

    public void uploadFile(MultipartFile file) throws IOException {
        // Use Apache POI to read the Excel file
        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);

        for(int index = 0; index < worksheet.getPhysicalNumberOfRows() ; index++) {
            if(index > 0) {
                XSSFRow row = worksheet.getRow(index);

                // Assuming your Excel structure is Student Name | Address | Course Name | TextBook | Marks
                String studentName = row.getCell(0).getStringCellValue();
                String address = row.getCell(1).getStringCellValue();
                String courseName = row.getCell(2).getStringCellValue();
                String textbook = row.getCell(3).getStringCellValue();
                int marks = (int) row.getCell(4).getNumericCellValue();

                // Now, we will save these details in the database
                
                // Before creating a new student, we check if the student already exists in the database
                Student student = studentRepository.findByName(studentName);
                if(student == null){
                    // Student not found, so we create a new one
                    student = new Student(studentName, address);
                    student = studentRepository.save(student);  // saving to get the generated ID
                }
                
                // Similar for Course
                Course course = courseRepository.findByCname(courseName);
                if(course == null){
                    // Course not found, so we create a new one
                    course = new Course(courseName, textbook);
                    course = courseRepository.save(course);  // saving to get the generated ID
                }

                // Now we can create the Marks entity
                Marks marksEntity = new Marks(student.getStudId(), course.getcId(), marks);
                marksRepository.save(marksEntity);
            }
        }
    }
}*/


import com.Students.detail.entity.Student;
import com.Students.detail.entity.Course;
import com.Students.detail.entity.Marks;
import com.Students.detail.repository.StudentRepository;
import com.Students.detail.repository.CourseRepository;
import com.Students.detail.repository.MarksRepository;

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
    private CourseRepository courseRepository;

    @Autowired
    private MarksRepository marksRepository;
    
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

                            //since columns are in order: studId,address, name
                            if (currentCell.getColumnIndex() == 0) { 
                                student.setStudId((int) currentCell.getNumericCellValue());//studId
                            } 
                            else if (currentCell.getColumnIndex() == 2) { 
                                student.setAddress(currentCell.getStringCellValue());//address
                            }
                            else if (currentCell.getColumnIndex() == 1) {
                                student.setName(currentCell.getStringCellValue()); //name
                            } 
                        }
                        studentRepository.save(student);
                        break;

                        
                    //API call for POST is http://localhost:8080/excel/upload/course
                	case "course":
                        Course course = new Course();
                        while (cellsInRow.hasNext()) {
                            Cell currentCell = cellsInRow.next();

                            // since columns are in order: cId, cname , textbook
                            if (currentCell.getColumnIndex() == 0) { 
                                course.setcId((int) currentCell.getNumericCellValue());//cId
                            } 
                            else if (currentCell.getColumnIndex() == 1) {
                                course.setCname(currentCell.getStringCellValue());//cname
                            } 
                            else if (currentCell.getColumnIndex() == 2) { 
                                course.setTextbook(currentCell.getStringCellValue());//textbook
                            }
                        }
                        courseRepository.save(course);
                        break;
                        
                        
                        
                    //API call for POST is http://localhost:8080/excel/upload/studentcourses
//                	case "studentcourses":
//                        Course course = new Course();
//                        while (cellsInRow.hasNext()) {
//                            Cell currentCell = cellsInRow.next();
//
//                            // since columns are in order: cId, cname , textbook
//                            if (currentCell.getColumnIndex() == 0) { 
//                                course.setcId((int) currentCell.getNumericCellValue());//cId
//                            } 
//                            else if (currentCell.getColumnIndex() == 1) {
//                                course.setCname(currentCell.getStringCellValue());//cname
//                            } 
//                            else if (currentCell.getColumnIndex() == 2) { 
//                                course.setTextbook(currentCell.getStringCellValue());//textbook
//                            }
//                        }
//                        courseRepository.save(course);
//                        break;   
//                        
                        
                        
                        
                        

                        
                    //API call for POST is http://localhost:8080/excel/upload/marks    
//                    case "marks":
//                        Marks marks = new Marks();
//                        while (cellsInRow.hasNext()) {
//                            Cell currentCell = cellsInRow.next();
//
//                            // since columns are in order: marks_id, marks, course_id, student_id
//                            if (currentCell.getColumnIndex() == 0) { 
//                                course.setcId((int) currentCell.getNumericCellValue());//marks_id
//                            } 
//                            else if (currentCell.getColumnIndex() == 1) { 
//                                course.set((int) currentCell.getNumericCellValue());//marks_id
//                            } 
//                            else if (currentCell.getColumnIndex() == 2) {
//                                course.setCname(currentCell.getStringCellValue());//course_name
//                            } 
//                            else if (currentCell.getColumnIndex() == 3) { 
//                                course.setTextbook(currentCell.getStringCellValue());//text_book
//                            }
//                        }
//                        courseRepository.save(course);
//                        break;
//    
//    
//    
//                        }
                }
            }
            workbook.close();

        }
        catch(IOException e) {
            throw new RuntimeException("File not stored. Error: " + e.getMessage());
        }
    }
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
