package com.techouts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import com.techouts.service.MailService;

@SpringBootApplication
public class BootMailAppApplication {

	public static void main(String[] args) {
		//SpringApplication.run(BootMailAppApplication.class, args);
		ApplicationContext ctx = null;
		MailService mail = null;
		// create IOC container
		ctx = SpringApplication.run(BootMailAppApplication.class, args);
		// get Bean
		mail = ctx.getBean("sender", MailService.class);
		try {
			// invoke method
			System.out.println(mail.send("", "satyabratarout196@gmail.com"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// close container
		((ConfigurableApplicationContext) ctx).close();
	}

}
