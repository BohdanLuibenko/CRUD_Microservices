package com.example.SecondMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import Controller.SecondStudentController;
import Entity.StudentEntity;
import Repository.StudentRepository;
import Service.SecondStudentService;

@SpringBootApplication
@ComponentScan(basePackageClasses = SecondStudentController.class)
@EntityScan(basePackageClasses = StudentEntity.class)
@EnableJpaRepositories(basePackageClasses = StudentRepository.class)
@ComponentScan(basePackageClasses = SecondStudentService.class)
public class SecondMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecondMicroserviceApplication.class, args);
	}

}
