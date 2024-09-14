package io.sc0.REST.API.Demo.rest.controllers;


import io.sc0.REST.API.Demo.Exception.NotFoundException;
import io.sc0.REST.API.Demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class EmployeeRESTController {

    private List<Student> studentList ;

    @PostConstruct
    private void init() {

        studentList = List.of(
                new Student("Mo","Ta"),
                new Student("He","Ba"),
                new Student("Sa","Ta")
        );

    }

    @GetMapping("/")
    public ResponseEntity<List<Student>> getStudentList () {
        return ResponseEntity.ok(studentList);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudent(@PathVariable int studentId) {

        if(studentId < 0 || studentId >= studentList.size())
            throw new NotFoundException("Student out of bound");


        return ResponseEntity.ok(studentList.get(studentId));


    }

}
