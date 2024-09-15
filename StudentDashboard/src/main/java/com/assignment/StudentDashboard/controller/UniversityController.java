package com.assignment.StudentDashboard.controller;

import com.assignment.StudentDashboard.DTO.StudentDTO;
import com.assignment.StudentDashboard.DTO.UniversityDTO;
import com.assignment.StudentDashboard.entity.Student;
import com.assignment.StudentDashboard.entity.University;
import com.assignment.StudentDashboard.service.StudentService;
import com.assignment.StudentDashboard.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/university")
public class UniversityController {

    @Autowired
    private UniversityService universityService;

    @Autowired
    private StudentService studentService;

    @GetMapping()
    public List<UniversityDTO> getAll() {
        return universityService.getAll();
    }

    @PostMapping(path = "/save")
    public String addUniversity(@RequestBody UniversityDTO universityDTO) throws Exception {
        try {
            Student student = studentService.getById(universityDTO.getStudentId());
            if (student == null) {
                throw new Exception("Student not found");
            }
            return universityService.addUniversity(universityDTO);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @PutMapping(path = "/update")
    public String updateUniversity(@RequestBody UniversityDTO universityDTO) {
        return universityService.update(universityDTO);
    }

    @DeleteMapping(path = "/delete/{id}")
    public String deleteUniversity(@PathVariable("id") int id) {
        return universityService.deleteById(id);
    }
}
