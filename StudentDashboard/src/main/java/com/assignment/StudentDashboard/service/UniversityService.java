package com.assignment.StudentDashboard.service;

import com.assignment.StudentDashboard.DTO.UniversityDTO;
import com.assignment.StudentDashboard.entity.University;

import java.util.List;

public interface UniversityService {
    String addUniversity(UniversityDTO universityDTO);

    List<UniversityDTO> getAll();

    String deleteById(int id);

    String update(UniversityDTO universityDTO);
}
