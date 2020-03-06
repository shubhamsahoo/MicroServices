package com.nt.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.nt.config.AppConfig;

@SpringBootApplication
@Import(AppConfig.class)
public class OAuthTest1Application{

 
  public static void main(String[] args) {
    SpringApplication.run(OAuthTest1Application.class, args);
  }
   
}
