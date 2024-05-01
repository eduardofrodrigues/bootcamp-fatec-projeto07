package com.abutua.alunos.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
