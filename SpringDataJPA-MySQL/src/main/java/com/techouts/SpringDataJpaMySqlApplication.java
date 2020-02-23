package com.techouts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.techouts.repository.UserRepository;

@SpringBootApplication
public class SpringDataJpaMySqlApplication implements CommandLineRunner {
	private static final Logger log = LoggerFactory.getLogger(SpringDataJpaMySqlApplication.class);

	@Autowired
	private UserRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaMySqlApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("StartApplication...");

//		repository.save(new Book("Java"));
//		repository.save(new Book("Node"));
//		repository.save(new Book("Python"));

		System.out.println("\nfindAll()");
		repository.findAll().forEach(x -> System.out.println(x));

		System.out.println("\nfindById(1)");
		repository.findById(1).ifPresent(x -> System.out.println(x));

		System.out.println("\nfindByUserName('shubham.s@techouts.com')");
		repository.findByUserName("shubham.s@techouts.com").forEach(x -> System.out.println(x));

		System.out.println("\nCout User('shubham.s@techouts.com')");
		System.out.println(repository.countByUserNameAndPassword("shubham.s@techouts.com", "shubhamsahoo"));

	}
}
