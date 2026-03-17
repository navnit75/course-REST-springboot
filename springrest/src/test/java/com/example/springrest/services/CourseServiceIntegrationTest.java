package com.example.springrest.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.context.annotation.Import;
import com.example.springrest.dao.CourseDao;
import com.example.springrest.entities.Course;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Import(CourseServiceImpl.class)
class CourseServiceIntegrationTest {
    
    @Autowired
    private CourseDao courseDao;
    
    @Autowired
    private CourseServiceImpl courseService;
    
    @Test
    void addCourse_ShouldPersistCourseToDatabase() {
        // Arrange
        Course course = new Course(1100L, "Integration Test", "Test integration with database");
        
        // Act
        Course savedCourse = courseService.addCourse(course);
        
        // Assert
        assertNotNull(savedCourse.getId());
        assertEquals("Integration Test", savedCourse.getTitle());
        
        // Verify it's actually in the database
        Course foundCourse = courseDao.findById(1100L).orElse(null);
        assertNotNull(foundCourse);
        assertEquals("Integration Test", foundCourse.getTitle());
    }
    
    @Test
    void getCourses_ShouldReturnAllCoursesFromDatabase() {
        // Arrange
        Course course1 = new Course(1001L, "Course One", "First test course");
        Course course2 = new Course(1002L, "Course Two", "Second test course");
        
        courseDao.save(course1);
        courseDao.save(course2);
        
        // Act
        var courses = courseService.getCourses();
        
        // Assert - Check that our specific courses are in the result
        assertTrue(courses.stream().anyMatch(c -> c.getTitle().equals("Course One")));
        assertTrue(courses.stream().anyMatch(c -> c.getTitle().equals("Course Two")));
        
        // Count only our test courses
        long testCourseCount = courses.stream()
            .filter(c -> c.getId() == 1001L || c.getId() == 1002L)
            .count();
        assertEquals(2, testCourseCount);
    }
    
    @Test
    void getCourse_ShouldReturnCourseByIdFromDatabase() {
        // Arrange
        Course course = new Course(1103L, "Specific Course", "Course for specific retrieval");
        courseDao.save(course);
        
        // Act
        Course foundCourse = courseService.getCourse(1103L);
        
        // Assert
        assertNotNull(foundCourse);
        assertEquals("Specific Course", foundCourse.getTitle());
        assertEquals("Course for specific retrieval", foundCourse.getDescription());
    }
    
    @Test
    void updateCourse_ShouldUpdateExistingCourseInDatabase() {
        // Arrange
        Course originalCourse = new Course(1104L, "Original", "Original description");
        courseDao.save(originalCourse);
        
        Course updatedCourse = new Course(1104L, "Updated", "Updated description");
        
        // Act
        Course result = courseService.updateCourse(updatedCourse);
        
        // Assert
        assertEquals("Updated", result.getTitle());
        assertEquals("Updated description", result.getDescription());
        
        // Verify database was updated
        Course dbCourse = courseDao.findById(1104L).orElse(null);
        assertNotNull(dbCourse);
        assertEquals("Updated", dbCourse.getTitle());
    }
    
    @Test
    void deleteCourse_ShouldRemoveCourseFromDatabase() {
        // Arrange
        Course course = new Course(1105L, "To Delete", "Course to be deleted");
        courseDao.save(course);
        
        // Verify it exists
        assertTrue(courseDao.findById(1105L).isPresent());
        
        // Act
        courseService.deleteCourse(1105L);
        
        // Assert
        assertFalse(courseDao.findById(1105L).isPresent());
    }
}