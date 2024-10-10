package com.example.demo;

import java.util.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.layer2.Course;
import com.example.demo.layer3.CourseRepository;
import com.example.demo.layer4.CourseService;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	CourseRepository courseRepo;

	@Test
	public void insertCourseTest() {
		Course courseObj = new Course();
		courseObj.setCourseId(103);
		courseObj.setCourseName("Ruby");
		courseObj.setCoursePrice(9000);
		courseRepo.save(courseObj);
	}

	@Test
	void updateCourseTest() {
		Optional<Course> courseObj = courseRepo.findById(103);
		if (courseObj.isPresent()) {
			Course actualCourseObj = courseObj.get();
			System.out.println("--Current details--");
			System.out.println("course id : " + actualCourseObj.getCourseId());
			System.out.println("course name : " + actualCourseObj.getCourseName());
			System.out.println("course price : " + actualCourseObj.getCoursePrice());

			actualCourseObj.setCourseName("Jenkins");
			actualCourseObj.setCoursePrice(2000);
			courseRepo.save(actualCourseObj);
			System.out.println("Current account updated....");
		} else {
			throw new RuntimeException("Course not found");
		}
	}

	@Test
	void deleteCourseTest() {
		Optional<Course> courseObj = courseRepo.findById(103);
		if (courseObj.isPresent()) {
			Course actualCourseObj = courseObj.get();
			System.out.println("---Current Details--");
			System.out.println("course id: " + actualCourseObj.getCourseId());
			System.out.println("course name: " + actualCourseObj.getCourseName());
			System.out.println("course price: " + actualCourseObj.getCoursePrice());
			courseRepo.delete(actualCourseObj);
			System.out.println("Current account delete");
		} else {
			throw new RuntimeException("Current account not found....");
		}
	}

	@Test
	void selectCourseTest() {
		Optional<Course> courseObj = courseRepo.findById(103);
		if (courseObj.isPresent()) {
			Course actualCourseObj = courseObj.get();
			System.out.println("--Current details--");
			System.out.println("course id: " + actualCourseObj.getCourseId());
			System.out.println("course name: " + actualCourseObj.getCourseName());
			System.out.println("course price: " + actualCourseObj.getCoursePrice());

			courseRepo.save(actualCourseObj);
			System.out.println("Current account found...");
		} else {
			throw new RuntimeException("Current account not found....");
		}
	}

	@Test
	void selectAllCourseTest() {
		List<Course> courseList = (List<Course>) courseRepo.findAll();
		for (Course course : courseList) {
			System.out.println("course id: " + course.getCourseId());
			System.out.println("course name: " + course.getCourseName());
			System.out.println("course price: " + course.getCoursePrice());
			System.out.println("-------------------");

		}
	}

	// Service layer test cases

	@Autowired
	CourseService courseServiceObj;

	@Test
	public void serviceTestingForAddingCourse() {
		Course courseObj = new Course();
		courseObj.setCourseId(104);
		courseObj.setCourseName("Spring boot");
		courseObj.setCoursePrice(5000);
		courseServiceObj.addCourseService(courseObj);
	}

	@Test
	public void serviceTestingForModifyingCourse() {
		Course course = new Course();
		course.setCourseId(104);
		course.setCourseName("cpp");
		course.setCoursePrice(2000);
		courseServiceObj.modifyCourseService(course);
	}

	@Test
	public void serviceTestingForDeletingCourse() {
		Course courseObj = new Course();
		courseObj.setCourseId(104);
		courseServiceObj.removeCourseService(courseObj);
	}

	@Test
	public void serviceTestingForFindingSingleCourse() {
		Course course = courseServiceObj.findCourseService(104);
		System.out.println("Course found......");
		System.out.println("Course id: " + course.getCourseId());
		System.out.println("Course name: " + course.getCourseName());
		System.out.println("Course price: " + course.getCoursePrice());
	}

	@Test
	public void serviceTestForFindingAllCourse() {
		List<Course> courseList = courseServiceObj.findAllCourseService();

		for (Course course : courseList) {
			System.out.println("course id: " + course.getCourseId());
			System.out.println("course name: " + course.getCourseName());
			System.out.println("Course price: " + course.getCoursePrice());
		}
	}
}
