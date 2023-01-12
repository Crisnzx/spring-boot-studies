package com.example.springbootrestapi.controller;

import com.example.springbootrestapi.bean.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {
    @GetMapping("student")
    public Student getStudent() {
        Student student = new Student(1, "Cristovão", "Neto");
        return student;
    }

    @GetMapping("students")
    public List<Student> getStudents() {
        List<Student> students = new ArrayList<Student>();
        students.add(new Student(1, "Cristovão", "Neto"));
        students.add(new Student(2, "Ismael", "Neto"));
        students.add(new Student(3, "Suelen", "Félix"));
        students.add(new Student(4, "Hugo", "Figueiredo"));
        return students;
    }

}
