package com.abutua.alunos.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abutua.alunos.models.Course;

import jakarta.annotation.PostConstruct;

@RestController
public class CoursesController {
    List<Course> courses = new ArrayList<>();

    @PostConstruct
    public void initList() {
        Course crs1 = new Course(1, "Java");
        Course crs2 = new Course(2, "HTML");
        Course crs3 = new Course(3, "JavaScript");

        courses.add(crs1);
        courses.add(crs2);
        courses.add(crs3);
    }

    @GetMapping("/courses")
    public List<Course> allCourses() {
        return courses;
    }
}
