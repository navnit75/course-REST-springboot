package com.example.springrest.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springrest.dao.CourseDao;
import com.example.springrest.entities.Course;

@Service
public class CourseServiceImpl implements CourseService {
	
	@Autowired
	private CourseDao courseDao;
//	List<Course> list; 
	
	public CourseServiceImpl() {
//		 list = new ArrayList<>();
//		 list.add(new Course(145,"Java Core Course","this course contains basics of Java"));
//		 list.add(new Course(4343,"spring boot course","creating rest api using spring boot"));
	}
	
	@Override
	public List<Course> getCourses() {
		return courseDao.findAll();
	}

	
	@Override
	public Course getCourse(long courseId) {
//		Course c = null; 
//		for(Course course : list) {
//			if(course.getId() == courseId)
//			{
//				c = course; 
//				break; 
//			}
//		}
//		
//		return c;
		return courseDao.getById(courseId);
	}

	@Override
	public Course addCourse(Course course) {
//		list.add(course);
		courseDao.save(course);
		return course;
	}

	@Override
	public Course updateCourse(Course course) {
//		list.forEach(e -> {
//			if(e.getId() == course.getId()) {
//				e.setTitle(course.getTitle());
//				e.setDescription(course.getDescription());
//			}
//		});
		
		// If for a particular id course exists --> then update or else this command will add the course to the DB
		courseDao.save(course);
		return course;
	}

	@Override
	public void deleteCourse(long courseId) {
		
//		list = this.list.stream().filter(e->e.getId()!=courseId).collect(Collectors.toList());
		courseDao.deleteById(courseId);
		
	}
	
	

}
