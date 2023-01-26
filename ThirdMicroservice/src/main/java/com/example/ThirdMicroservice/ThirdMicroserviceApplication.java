package com.example.ThirdMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import Controller.ThirdStudentController;
import Entity.StudentEntity;
import Repository.StudentRepository;
import Service.ThirdStudentService;

@SpringBootApplication
@ComponentScan(basePackageClasses = ThirdStudentController.class)
@EntityScan(basePackageClasses = StudentEntity.class)
@EnableJpaRepositories(basePackageClasses = StudentRepository.class)
@ComponentScan(basePackageClasses = ThirdStudentService.class)
public class ThirdMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThirdMicroserviceApplication.class, args);
	}

}
