package com.assignment.StudentDashboard.repository;

import com.assignment.StudentDashboard.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    Student findByEmailAndPassword(String email, String password);

    Student findByEmail(String email);
}
