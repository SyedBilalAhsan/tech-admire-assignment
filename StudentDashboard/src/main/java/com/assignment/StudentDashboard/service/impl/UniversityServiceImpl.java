package com.assignment.StudentDashboard.service.impl;

import com.assignment.StudentDashboard.DTO.UniversityDTO;
import com.assignment.StudentDashboard.entity.University;
import com.assignment.StudentDashboard.mapper.UniversityMapper;
import com.assignment.StudentDashboard.repository.UniversityRepository;
import com.assignment.StudentDashboard.service.StudentService;
import com.assignment.StudentDashboard.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UniversityServiceImpl implements UniversityService {

    @Autowired
    private UniversityRepository universityRepository;
    @Autowired
    private UniversityMapper universityMapper;
    @Autowired
    private StudentService studentService;

    @Override
    public String addUniversity(UniversityDTO universityDTO) {
        if (Boolean.FALSE.equals(existByStudentIdAndUniversityNameAndCourse(universityDTO))) {
            University university = universityMapper.mapToUniversity(universityDTO);
            universityRepository.save(university);
            return "success";
        } else {
            throw new RuntimeException("Student cannot be registered in multiple university or have same course");
        }
    }

    private Boolean existByStudentIdAndUniversityNameAndCourse(UniversityDTO universityDTO) {
        // check if student occurs in multiple university or not
        List<String> universityList = universityRepository.findByStudentId
                (universityDTO.getStudentId()).stream().map(University::getName).collect(Collectors.toList());

        // check if student occurs in same university with same course
        University university = universityRepository.findByStudentIdAndNameAndCourse(universityDTO.getStudentId(),
                universityDTO.getName(), universityDTO.getCourse());

        if ( (!universityList.isEmpty() && !universityList.contains(universityDTO.getName())) || university != null) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public List<UniversityDTO> getAll() {
        List<UniversityDTO> universityDTOList = new ArrayList<>();
        for (University university : universityRepository.findAll()) {
            universityDTOList.add(universityMapper.mapToUniversityDTO(university));
        }
        return universityDTOList;
    }

    @Override
    public String deleteById(int id) {
        try {
            universityRepository.deleteById(id);
            return "successfully deleted";
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String update(UniversityDTO universityDTO) {
        if (universityRepository.existsById(universityDTO.getUniversityId())) {
            University university = universityRepository.getById(universityDTO.getUniversityId());

            university.setName(universityDTO.getName());
            university.setStudent(studentService.getById(universityDTO.getStudentId()));
            university.setCourse(universityDTO.getCourse());
            universityRepository.save(university);
            return "successfully updated";
        } else {
            throw new RuntimeException(universityDTO.getUniversityId() + " does not exist");
        }
    }
}
