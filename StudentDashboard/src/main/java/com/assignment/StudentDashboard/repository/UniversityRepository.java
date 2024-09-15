package com.assignment.StudentDashboard.repository;

import com.assignment.StudentDashboard.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UniversityRepository extends JpaRepository<University, Integer> {

    University findByStudentIdAndNameAndCourse(int studentId, String name, String course);

    University findByStudentIdAndName(int studentId, String name);

    List<University> findByStudentId(int studentId);
}
