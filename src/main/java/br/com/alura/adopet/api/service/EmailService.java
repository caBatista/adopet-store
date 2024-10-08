package br.com.alura.adopet.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender emailSender;
	
	public void enviaEmail(String toEmail, String subject, String text) {
		var fromEmail = "adopet@email.com";
		
		SimpleMailMessage email = new SimpleMailMessage();
		email.setFrom(fromEmail);
		email.setTo(toEmail);
		email.setSubject(subject);
		email.setText(text);
		//emailSender.send(email);
	}
}
