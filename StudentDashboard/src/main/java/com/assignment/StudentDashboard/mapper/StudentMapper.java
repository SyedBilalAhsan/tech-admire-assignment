package com.assignment.StudentDashboard.mapper;

import com.assignment.StudentDashboard.DTO.StudentDTO;
import com.assignment.StudentDashboard.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Student mapToStudent(StudentDTO studentDTO) {
        Student student = new Student();
        student.setId(studentDTO.getId());
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        student.setPassword(passwordEncoder.encode(studentDTO.getPassword()));
        return student;
    }
}
