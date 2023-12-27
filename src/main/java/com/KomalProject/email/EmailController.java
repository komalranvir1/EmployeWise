package com.KomalProject.email;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {

	@Value("${spring.mail.host}")
	private String host;

	@Value("${spring.mail.port}")
	private int port;

	@Value("${spring.mail.username}")
	private String username;

	@Value("${spring.mail.password}")
	private String password;

	@Autowired
	private JavaMailSender javaMailSender;

	@PostMapping("/add")
	public void sendFeedback(@RequestBody Feedback feedback, BindingResult bindingResult) throws ValidationException {
		if (bindingResult.hasErrors())
			throw new ValidationException("Feedback is not valid ");

		// create mail sender
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

		mailSender.setHost(host);
		mailSender.setPort(port);
		mailSender.setUsername(username);
		mailSender.setPassword(password);

		// create an email instant;
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(feedback.getManagerEmail());
		message.setTo("komalranvir07@gmail.com");
		message.setSubject("New Employee Added");
		message.setText(feedback.getEmployeeName() + " will now work under you. Mobile number is "
				+ feedback.getPhoneNumber() + " and email is " + feedback.getEmailId());

		javaMailSender.send(message);
	}

}
