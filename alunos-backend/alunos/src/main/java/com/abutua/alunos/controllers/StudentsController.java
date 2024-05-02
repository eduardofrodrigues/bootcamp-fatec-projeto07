package com.abutua.alunos.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.abutua.alunos.models.Student;

import jakarta.annotation.PostConstruct;

@RestController
public class StudentsController {

    List<Student> students = new ArrayList<>();

    @PostConstruct
    public void initList() {
        Student std1 = new Student(1, "Eduardo Rodrigues", "email@email.com", "15999999999", 1, 3);
        Student std2 = new Student(2, "Eduardo Affonso", "email@email.com", "15999999999", 2, 2);
        Student std3 = new Student(3, "Eduardo Gutierres", "email@email.com", "15999999999", 1, 3);

        students.add(std1);
        students.add(std2);
        students.add(std3);
    }

    @GetMapping("/students")
    public List<Student> allStudents() {
        return students;
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        Student student = students.stream()
                .filter(std -> std.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));

        return ResponseEntity.ok(student);
    }

    @PostMapping("/students")
    public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
        student.setId(students.size() + 1);
        students.add(student);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(student.getId())
                .toUri();

        return ResponseEntity.created(location).body(student);
    }
}
