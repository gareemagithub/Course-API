package com.example.demo.layer4;

import org.springframework.stereotype.Service;

import com.example.demo.layer2.Course;
import java.util.*;

@Service
public interface CourseService {
    void addCourseService(Course obj);
    void modifyCourseService(Course obj);
    void removeCourseService(Course obj);
    Course findCourseService(int course_id);
    List<Course> findAllCourseService(); 
}
