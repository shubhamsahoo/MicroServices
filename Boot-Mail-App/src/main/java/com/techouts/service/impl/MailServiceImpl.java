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
		return sendEmail(toEmail,
				"<h1 style='color:red;text-align:center;'>Hey Ravi Ranjan</h1> "
						+ "<h1 style='color:red;text-align:center;'>Hey Ravi Ranjan</h1>"
						+ "<h1 style='color:blue;text-align:center;'>Hey Ravi Ranjan</h1>"
						+ "<h1 style='color:yellow;text-align:center;'>Hey Ravi Ranjan</h1>"
						+ "<h1 style='color:green;text-align:center;'>Hey Ravi Ranjan</h1>"
						+ "<h3 style='text-align:center;'><a href='www.google.com'>google</a></h3>");
	}

	private String sendEmail(String ToEmail, String msg) throws Exception {
		MimeMessageHelper helper = null;
		MimeMessage message = null;
		// prepare Email messages
		message = sender.createMimeMessage();
		helper = new MimeMessageHelper(message, true);
		helper.setFrom(new InternetAddress("shubhamsahoo19981@gmail.com"));
		helper.setTo(new InternetAddress(ToEmail));
		helper.setSubject("Shubham:: Java Mail Integration");
		helper.setSentDate(new Date());
		//		helper.setText(msg);
		helper.setText(msg, true);
		helper.addAttachment("ford.jpg", new ClassPathResource("ford-mustang.jpg"));
		helper.addAttachment("ford1.jpg", new ClassPathResource("ford-mustang2.jpg"));
		helper.addAttachment("ford2.jpg", new ClassPathResource("ford-mustang3.jpg"));
		sender.send(message);
		return "mail has been delivered";
	}
}
