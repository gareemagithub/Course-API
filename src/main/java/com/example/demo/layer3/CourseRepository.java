package com.example.demo.layer3;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.layer2.Course;

@Repository
public interface CourseRepository extends CrudRepository<Course,Integer>{
    
}
