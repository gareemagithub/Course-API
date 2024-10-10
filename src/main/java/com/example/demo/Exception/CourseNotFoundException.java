package com.example.demo.Exceptions;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(String str) {
        super(str);
    }
}
