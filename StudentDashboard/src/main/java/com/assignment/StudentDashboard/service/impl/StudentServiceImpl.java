package com.assignment.StudentDashboard.service.impl;

import com.assignment.StudentDashboard.DTO.LoginDTO;
import com.assignment.StudentDashboard.DTO.StudentDTO;
import com.assignment.StudentDashboard.entity.Student;
import com.assignment.StudentDashboard.mapper.StudentMapper;
import com.assignment.StudentDashboard.repository.StudentRepository;
import com.assignment.StudentDashboard.response.LoginResponse;
import com.assignment.StudentDashboard.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String register(StudentDTO studentDTO) {
        validateStudent(studentDTO);
        Student student = studentMapper.mapToStudent(studentDTO);
        studentRepository.save(student);
        return "success";
    }

    private void validateStudent(StudentDTO studentDTO) {
        Student student = studentRepository.findByEmail(studentDTO.getEmail());
        if(student != null) {
            throw new RuntimeException("Email already exists");
        }
    }

    @Override
    public LoginResponse login(LoginDTO loginDTO) {
        Student student = studentRepository.findByEmail(loginDTO.getEmail());
        if (student != null) {
            String password = loginDTO.getPassword();
            String encodedPassword = student.getPassword();
            Boolean verifyPassword = passwordEncoder.matches(password, encodedPassword);
            if (Boolean.TRUE.equals(verifyPassword)) {
                Student studentOptional = studentRepository.findByEmailAndPassword(loginDTO.getEmail(), encodedPassword);
                if (studentOptional != null) {
                    return new LoginResponse("Login Success", Boolean.TRUE);
                } else {
                    return new LoginResponse("Login Unsuccessful", Boolean.FALSE);
                }
            } else {
                return new LoginResponse("Incorrect Password", Boolean.FALSE);
            }
        } else {
            return new LoginResponse("Email does not exist", Boolean.FALSE);
        }
    }

    @Override
    public Student getById(int studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        return student.orElse(null);
    }
}
