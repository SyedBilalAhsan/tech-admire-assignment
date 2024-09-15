package com.assignment.StudentDashboard.controller;

import com.assignment.StudentDashboard.DTO.LoginDTO;
import com.assignment.StudentDashboard.DTO.StudentDTO;
import com.assignment.StudentDashboard.response.LoginResponse;
import com.assignment.StudentDashboard.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping(path = "/register")
    public String saveStudent(@RequestBody StudentDTO studentDTO) throws Exception {
        try {
            return studentService.register(studentDTO);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> loginStudent(@RequestBody LoginDTO loginDTO) {
        LoginResponse loginResponse = studentService.login(loginDTO);
        return ResponseEntity.ok(loginResponse);
    }
}
