package com.bigsale.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service("mailService")
public class MailService {
    private static final String ADMIN_MAIL = "forhim185@gmail.com";


	@Autowired
	private MailSender mailSender;

	public void sendMail(String from, String to, String subject, String body) {

		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(body);
		mailSender.send(message);

	}

    public void sendSignUpSuccessMail(String to, String userId, String userPassword){
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(ADMIN_MAIL);
//        message.setTo(to);
//        message.setSubject("[BigSale] Thank you For join our Site! your");
//        message.setText("Your User Information is below!\n User ID: "+userId+"\nPassword: "+userPassword);
//        mailSender.send(message);
    }

}
