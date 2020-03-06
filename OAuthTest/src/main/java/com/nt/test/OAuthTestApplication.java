package com.nt.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.nt.config.AppConfig;

@SpringBootApplication
@Import({AppConfig.class})
public class OAuthTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(OAuthTestApplication.class, args);
	}
	
}
