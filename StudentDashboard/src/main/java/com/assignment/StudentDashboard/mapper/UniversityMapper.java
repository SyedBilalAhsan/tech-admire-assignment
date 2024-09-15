package com.assignment.StudentDashboard.mapper;

import com.assignment.StudentDashboard.DTO.UniversityDTO;
import com.assignment.StudentDashboard.entity.University;
import com.assignment.StudentDashboard.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UniversityMapper {

    @Autowired
    private StudentService studentService;

    public University mapToUniversity(UniversityDTO universityDTO) {
        University university = new University();
        university.setName(universityDTO.getName());
        university.setCourse(universityDTO.getCourse());
        university.setStudent(studentService.getById(universityDTO.getStudentId()));
        return university;
    }

    public UniversityDTO mapToUniversityDTO(University university) {
        UniversityDTO universityDTO = new UniversityDTO();
        universityDTO.setName(university.getName());
        universityDTO.setCourse(university.getCourse());
        universityDTO.setStudentName(university.getStudent().getName());
        return universityDTO;
    }
}
