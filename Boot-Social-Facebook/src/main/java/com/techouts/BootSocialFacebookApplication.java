package com.techouts;

import javax.inject.Inject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;

@SpringBootApplication
public class BootSocialFacebookApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootSocialFacebookApplication.class, args);
	}

//	@Bean
//	public ConnectionFactoryLocator connectionFactoryLocator() {
//		ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
//
//		registry.addConnectionFactory(new FacebookConnectionFactory(environment.getProperty("facebook.appId"),
//				environment.getProperty("facebook.appSecret")));
//		return registry;
//	}
//
//	@Inject
//	private Environment environment;
}
