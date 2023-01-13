package com.example.springbootrestapi.controller;

import com.example.springbootrestapi.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class StudentController {
    private List<Student> students = new ArrayList<Student>();
    private int id = 0;

    @GetMapping("student/path-variables/{first-name}/{last-name}")
    public ResponseEntity<Student> makeStudentWithPathVariables(@PathVariable("first-name") String firstName, @PathVariable("last-name") String lastName) {
        Student student = new Student(this.id++, firstName, lastName);
        this.students.add(student);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @GetMapping("student/query-params")
    public ResponseEntity<Student> makeStudentWithQueryParams(@RequestParam String firstName, @RequestParam(required = false) String lastName) {
        Student student = new Student(this.id++, firstName, lastName);
        this.students.add(student);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @GetMapping("students")
    public ResponseEntity<List<Student>> getStudents() {
        return ResponseEntity.ok(this.students);
    }

    @GetMapping("students/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable int id) {
        try {
        Student student = this.students.get(id);
        return ResponseEntity.ok(student);
        } catch (IndexOutOfBoundsException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("students")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        student.setId(this.id++);
        this.students.add(student);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @PutMapping("students/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable int id, @RequestBody Student student) {
        try {
            this.students.set(id, student);
            student.setId(id);
            return ResponseEntity.ok(student);
        } catch (IndexOutOfBoundsException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("students/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id) {
        try {
            Student student = this.students.stream().filter(s -> s.getId() == id).collect(Collectors.toList()).get(0);
            this.students.remove(student);
            return ResponseEntity.ok("Student of id " + id + " deleted successfully");
        } catch (IndexOutOfBoundsException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
