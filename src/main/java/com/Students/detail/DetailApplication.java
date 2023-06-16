package com.Students.detail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.Students.detail.repository.StudentRepository;

@SpringBootApplication
public class DetailApplication implements CommandLineRunner{

	@Autowired
	StudentRepository studRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(DetailApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {  
	}
}
