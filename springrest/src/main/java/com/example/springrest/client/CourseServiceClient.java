package com.example.springrest.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.springrest.services.CourseService;
import com.example.springrest.entities.Course;
import java.util.List;

@Component
public class CourseServiceClient {
    
    @Autowired
    private CourseService courseService;
    
    public List<Course> getAllCourses() {
        return courseService.getCourses();
    }
    
    public Course getCourseById(long courseId) {
        return courseService.getCourse(courseId);
    }
    
    public Course createCourse(Course course) {
        return courseService.addCourse(course);
    }
    
    public Course updateExistingCourse(Course course) {
        return courseService.updateCourse(course);
    }
    
    public void removeCourse(long courseId) {
        courseService.deleteCourse(courseId);
    }
}