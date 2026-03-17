package com.example.springrest.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.springrest.dao.CourseDao;
import com.example.springrest.entities.Course;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CourseServiceImplTest {
    
    @Mock
    private CourseDao courseDao;
    
    @InjectMocks
    private CourseServiceImpl courseService;
    
    @Test
    void getCourses_ShouldReturnAllCourses() {
        // Arrange
        Course course1 = new Course(1L, "Java", "Java Course");
        Course course2 = new Course(2L, "Spring", "Spring Course");
        List<Course> expectedCourses = Arrays.asList(course1, course2);
        
        when(courseDao.findAll()).thenReturn(expectedCourses);
        
        // Act
        List<Course> actualCourses = courseService.getCourses();
        
        // Assert
        assertEquals(2, actualCourses.size());
        assertEquals("Java", actualCourses.get(0).getTitle());
        verify(courseDao).findAll();
    }
    
    @Test
    void getCourse_ShouldReturnCourseById() {
        // Arrange
        Course expectedCourse = new Course(1L, "Java", "Java Course");
        when(courseDao.getById(1L)).thenReturn(expectedCourse);
        
        // Act
        Course actualCourse = courseService.getCourse(1L);
        
        // Assert
        assertNotNull(actualCourse);
        assertEquals("Java", actualCourse.getTitle());
        verify(courseDao).getById(1L);
    }
    
    @Test
    void addCourse_ShouldSaveAndReturnCourse() {
        // Arrange
        Course courseToSave = new Course(1L, "Java", "Java Course");
        when(courseDao.save(any(Course.class))).thenReturn(courseToSave);
        
        // Act
        Course savedCourse = courseService.addCourse(courseToSave);
        
        // Assert
        assertNotNull(savedCourse);
        assertEquals("Java", savedCourse.getTitle());
        verify(courseDao).save(courseToSave);
    }
    
    @Test
    void updateCourse_ShouldUpdateAndReturnCourse() {
        // Arrange
        Course courseToUpdate = new Course(1L, "Java Updated", "Updated Java Course");
        when(courseDao.save(any(Course.class))).thenReturn(courseToUpdate);
        
        // Act
        Course updatedCourse = courseService.updateCourse(courseToUpdate);
        
        // Assert
        assertNotNull(updatedCourse);
        assertEquals("Java Updated", updatedCourse.getTitle());
        verify(courseDao).save(courseToUpdate);
    }
    
    @Test
    void deleteCourse_ShouldCallDeleteById() {
        // Arrange
        long courseId = 1L;
        
        // Act
        courseService.deleteCourse(courseId);
        
        // Assert
        verify(courseDao).deleteById(courseId);
    }
}