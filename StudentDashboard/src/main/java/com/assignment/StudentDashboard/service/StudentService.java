package com.assignment.StudentDashboard.service;

import com.assignment.StudentDashboard.DTO.LoginDTO;
import com.assignment.StudentDashboard.DTO.StudentDTO;
import com.assignment.StudentDashboard.entity.Student;
import com.assignment.StudentDashboard.response.LoginResponse;

public interface StudentService {
    String register(StudentDTO studentDTO);

    LoginResponse login(LoginDTO loginDTO);

    Student getById(int studentId);
}
