package com.example.demo.layer4;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exceptions.CourseAlreadyExistsException;
import com.example.demo.Exceptions.CourseNotFoundException;
import com.example.demo.layer2.Course;
import com.example.demo.layer3.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService{
    @Autowired
    CourseRepository courseRepo;

    public void addCourseService(Course obj) {
        Optional<Course> courseObj = courseRepo.findById(obj.getCourseId());
        if (courseObj.isPresent()) {
            throw new CourseAlreadyExistsException("This ID already exists: " + obj.getCourseId());
        } else {
            courseRepo.save(obj);
            System.out.println("Course added....");
        }
    }

    public void modifyCourseService(Course obj) {
        Optional<Course> courseObj = courseRepo.findById(obj.getCourseId());
        if (courseObj.isPresent()) {
            courseRepo.save(obj);
            System.out.println("Course modified....");
        } else {
            throw new CourseNotFoundException("This course does not exists: " + obj.getCourseName());
        }
    }

    public void removeCourseService(Course obj) {
        Optional<Course> courseObj = courseRepo.findById(obj.getCourseId());
        if (courseObj.isPresent()) {
            courseRepo.delete(obj);
            System.out.println("Account deleted....");
        } else {
            throw new CourseNotFoundException("This course does not exists: " + obj.getCourseName());
        }
    }

    public Course findCourseService(int course_id) {
        Course courseObj = null;
        Optional<Course> courObj = courseRepo.findById(course_id);
        if (courObj.isPresent()) {
            courseObj = courObj.get();
        } else {
            throw new CourseNotFoundException("This course does not exists: " + course_id);
        }
        return courseObj;
    }

    public List<Course> findAllCourseService() {
        List<Course> courseObjList = (List<Course>) courseRepo.findAll();
        return courseObjList;
    }
}
