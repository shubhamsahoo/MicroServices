package com.techouts.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.techouts.config.RootAppConfig;
import com.techouts.config.WebMVCConfig;

@SpringBootApplication
@Import({ RootAppConfig.class, WebMVCConfig.class })
@EnableScheduling
public class SpringDataMongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataMongoApplication.class, args);
	}

}
