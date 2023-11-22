package com.example.studentdemo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.studentdemo.model.Student;

public interface StudentDemoRepo extends MongoRepository<Student, Integer> {

}
