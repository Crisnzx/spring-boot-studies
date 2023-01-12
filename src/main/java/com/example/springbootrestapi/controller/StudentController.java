package com.example.springbootrestapi.controller;

import com.example.springbootrestapi.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {
    @GetMapping("student")
    public Student getStudent() {
        Student student = new Student(1, "Cristovão", "Neto");
        return student;
    }

    @GetMapping("student/path-variables/{id}/{first-name}/{last-name}")
    public Student makeStudentWithPathVariables(@PathVariable("id") int id, @PathVariable("first-name") String firstName, @PathVariable("last-name") String lastName) {
        return new Student(id, firstName, lastName);
    }

    @GetMapping("student/query-params")
    public Student makeStudentWithQueryParams(@RequestParam int id, @RequestParam String firstName, @RequestParam(required = false) String lastName) {
        return new Student(id, firstName, lastName);
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

    @PostMapping("students")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student) {
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;
    }

    @PutMapping("students/{id}")
    public Student updateStudent(@PathVariable int id, @RequestBody Student student) {
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;
    }

    @DeleteMapping("students/{id}")
    public String deleteStudent(@PathVariable int id) {
        return "Student of id " + id + " deleted successfully";
    }


}
