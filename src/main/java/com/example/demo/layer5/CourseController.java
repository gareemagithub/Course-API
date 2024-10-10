package com.example.demo.layer5;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Exceptions.CourseAlreadyExistsException;
import com.example.demo.Exceptions.CourseNotFoundException;
import com.example.demo.layer2.Course;
import com.example.demo.layer4.CourseService;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    CourseService courseServiceObj;

    @GetMapping("/")
    public List<Course> showAllCourses() {
        return courseServiceObj.findAllCourseService();
    }

    @GetMapping("/{num}")
    public ResponseEntity showCourse(@PathVariable("num") int x) {
        try {
            Course courseObj = courseServiceObj.findCourseService(x);
            return ResponseEntity.status(HttpStatus.FOUND).body(courseObj);
        } catch (CourseNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity addCourse(@RequestBody Course courseObj) {
        try {
            courseServiceObj.addCourseService(courseObj);
            return ResponseEntity.status(HttpStatus.CREATED).body("Course Created");
        } catch (CourseAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity updatecourse(@RequestBody Course courseObj) {
        try {
            courseServiceObj.modifyCourseService(courseObj);
            return ResponseEntity.status(HttpStatus.OK).body("Course Modified");
        } catch (CourseNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    public ResponseEntity deleteCourse(@PathVariable("num") int x) {
        try {
            Course course = new Course();
            course.setCourseId(x);
            courseServiceObj.removeCourseService(course);
            return ResponseEntity.status(HttpStatus.OK).body("Course Removed");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
