package com.techouts.service.impl;

import java.util.Date;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.techouts.service.MailService;

@Service("sender")
public class MailServiceImpl implements MailService {

	@Autowired
	private JavaMailSender sender;

	@Override
	public String send(String message, String toEmail) throws Exception {
		// String toMail = "satyabratarout196@gmail.com";
		sendEmail(toEmail, "Hey <br>" + "Satyabrata Das <br>" + "I Love U darling");
		return null;
	}

	private void sendEmail(String ToEmail, String msg) throws Exception {
		MimeMessageHelper helper = null;
		MimeMessage message = null;
		// prepare Email messages
		message = sender.createMimeMessage();
		helper = new MimeMessageHelper(message, true);
		helper.setFrom(new InternetAddress("litusahu12341234@gmail.com"));
		helper.setTo(new InternetAddress(ToEmail));
		helper.setSubject("Shubham:: Java Mail Integration");
		helper.setText(msg);
		helper.setSentDate(new Date());
		helper.addAttachment("ford.jpg", new ClassPathResource("ford-mustang.jpg"));
		sender.send(message);
		System.out.println("mail has been delivered");
	}
}
