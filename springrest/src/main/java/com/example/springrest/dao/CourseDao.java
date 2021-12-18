package com.example.springrest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springrest.entities.Course;

// The two types which we need to provide while using JpaRepository is 
// One the type of entity we would be handling 
// Another the type of primary key we would be handling, according to the entity
public interface CourseDao extends JpaRepository<Course,Long> {

}
