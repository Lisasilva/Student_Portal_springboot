package com.Students.detail.service;

import com.Students.detail.entity.Course;
import com.Students.detail.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService implements BaseService<Course> {
	@Autowired
    CourseRepository courRepository;
	
	@Override
    public List<Course> getAll(){       
        List<Course> courses = courRepository.findAll(); //findAll() fn exists by default
		if(courses.isEmpty()) {
			throw new RuntimeException("No records found"); //exception to display msg if no record exists on calling GET
		}
		return courses;
    }

   	
	@Override
	public Course add(Course cour) {    //POST
	    if(courRepository.existsByCname(cour.getCname())){   //existsByName() doesn't exist by default in the JPA connection made in StudentRepository, hence we define it in Student repository
			throw new RuntimeException("Duplicate record");//if record with same name is entered twice
		}
		return courRepository.save(cour);   //save() is also predefined
	}
	
	@Override	
	public Course edit(Course cour) {    //PUT
		if (courRepository.existsById(cour.getcId())) {
			return courRepository.save(cour);
		} 
		
		else {
			throw new RuntimeException("Course not found"); //to check if the ID entered for modification exists or not
		}
	}
	
	@Override
	public String delete(int courNo) {
		if(!courRepository.existsById(courNo)){        // to check if the ID enetered doesnt exist
			throw new RuntimeException("Record doesn't exist");
		}
		courRepository.deleteById(courNo);
		return "Deleted";

	}
	
	@Override
	public Course getById(int courNo) {
		if(!courRepository.existsById(courNo)){        // to check if the ID enetered doesnt exist
			throw new RuntimeException("Record doesn't exist");
		}
		return courRepository.findById(courNo).orElse(null);  //used optional datatype
	}
    
    
    
}
	
	
	
	
//    @Autowired
//    CourseRepository courseRepository;
//
//    @Override
//    public List<Course> getAll() {
//        List<Course> courses = courseRepository.findAll();
//        if (courses.isEmpty()) {
//            throw new RuntimeException("No records found");
//        }
//        return courses;
//    }
//
//    @Override
//    public Course add(Object obj) {
//        Course course = (Course) obj;
//        if (courseRepository.existsByCname(course.getCname())) {
//            throw new RuntimeException("Duplicate record");
//        }
//        return courseRepository.save(course);
//    }
//
//    @Override
//    public Course edit(Object obj) {
//        Course course = (Course) obj;
//        if (courseRepository.existsById(course.getcId())) {
//            return courseRepository.save(course);
//        } else {
//            throw new RuntimeException("Course not found");
//        }
//    }
//
//    @Override
//    public String delete(int courNo) {
//		if(!courseRepository.existsById(courNo)){        // to check if the ID enetered doesnt exist
//			throw new RuntimeException("Record doesn't exist");
//		}
//		courseRepository.deleteById(courNo);
//		return "Deleted";
//
//	}
//	
//
//    @Override
//    public Course getById(int courNo) {
//		if(!courseRepository.existsById(courNo)){        // to check if the ID enetered doesnt exist
//			throw new RuntimeException("Record doesn't exist");
//		}
//		return courseRepository.findById(courNo).orElse(null);  //used optional datatype
//	}
//    
//}
