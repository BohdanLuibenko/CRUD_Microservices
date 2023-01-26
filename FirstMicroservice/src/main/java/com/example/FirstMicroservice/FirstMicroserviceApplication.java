package com.example.FirstMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import Controller.StudentController;
import Entity.StudentEntity;
import Repository.StudentRepository;
import Service.StudentService;

@SpringBootApplication
@ComponentScan(basePackageClasses = StudentController.class)
@EntityScan(basePackageClasses = StudentEntity.class)
@EnableJpaRepositories(basePackageClasses = StudentRepository.class)
@ComponentScan(basePackageClasses = StudentService.class)
public class FirstMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstMicroserviceApplication.class, args);
	}

}
