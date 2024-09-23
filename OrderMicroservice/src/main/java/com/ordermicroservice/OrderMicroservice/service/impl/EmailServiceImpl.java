package com.ordermicroservice.OrderMicroservice.service.impl;

import com.ordermicroservice.OrderMicroservice.dtos.ContactUsDto;
import com.ordermicroservice.OrderMicroservice.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;


    @Override
    public void sendEmail(String mail,ContactUsDto contactUsDto) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();


        String body = "Hello " + ",\n\nThank you for using our service! We hope to serve you again soon.";

        // This is a static 'from' email, which should match your authenticated email in application.properties
        mailMessage.setFrom("riyaarjariya1234@gmail.com");  // Use your system's email configured in application.properties

        // Set the recipient email
        mailMessage.setTo(mail);  // Admin or Support email

        // Set subject and message body
        mailMessage.setSubject(contactUsDto.getSubject());
        mailMessage.setText(contactUsDto.getMessage());

        // Set the Reply-To email dynamically
        mailMessage.setReplyTo(mail);  // This will allow replies to go to the user's email

        // Send the email
        mailSender.send(mailMessage);
    }
    }

//@Autowired
//private JavaMailSender mailSender;
//public void sendEmail(String to, String name) {
//    // Prepare the email body by injecting the user's name
//    String body = "Hello " + name + ",\n\nThank you for using our service! We hope to serve you again soon.";
//    // Create the email message
//    SimpleMailMessage message = new SimpleMailMessage();
//    message.setTo(to);
//    message.setSubject("Personalized Greeting");
//    message.setText(body); // Set the plain text body
//    // Send the email
//    mailSender.send(message);
//}

//    @Value("$(spring.mail.username)")
//    private String fromMail;

   /* @Override
    public void sendEmail(ContactUsDto contactUsDto, String mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(mail); // Change to your email
        mailMessage.setTo(contactUsDto.getSender()); // List of recipients
        mailMessage.setSubject(contactUsDto.getSubject());
        mailMessage.setReplyTo(mail);
        mailMessage.setText(contactUsDto.getMessage());

        mailSender.send(mailMessage);
    }
}*/

