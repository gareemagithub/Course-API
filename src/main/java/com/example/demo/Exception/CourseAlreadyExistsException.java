package com.example.demo.Exceptions;

public class CourseAlreadyExistsException extends RuntimeException {
    public CourseAlreadyExistsException(String str) {
        super(str);
    }
}
