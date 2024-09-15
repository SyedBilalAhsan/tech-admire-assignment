package com.assignment.StudentDashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class StudentDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentDashboardApplication.class, args);
	}

}
