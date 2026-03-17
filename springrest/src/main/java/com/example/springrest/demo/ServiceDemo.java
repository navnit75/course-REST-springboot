package com.example.springrest.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.example.springrest.client.CourseServiceClient;
import com.example.springrest.entities.Course;

@Component
public class ServiceDemo implements CommandLineRunner {

    private final CourseServiceClient courseServiceClient;
    
    public ServiceDemo(CourseServiceClient courseServiceClient) {
        this.courseServiceClient = courseServiceClient;
    }
    
    @Override
    @Transactional
    public void run(String... args) throws Exception {
        System.out.println("=== Course Service Demo ===");
        
        // Create a new course
        Course newCourse = new Course(100L, "Java Fundamentals", "Learn core Java programming concepts");
        Course created = courseServiceClient.createCourse(newCourse);
        System.out.println("Created course with ID: " + created.getId());
        
        // Retrieve the course
        Course retrieved = courseServiceClient.getCourseById(100L);
        System.out.println("Retrieved course: " + retrieved.getTitle());
        
        // Get all courses
        var allCourses = courseServiceClient.getAllCourses();
        System.out.println("Total courses: " + allCourses.size());
        
        System.out.println("=== Demo Completed ===");
    }
}
