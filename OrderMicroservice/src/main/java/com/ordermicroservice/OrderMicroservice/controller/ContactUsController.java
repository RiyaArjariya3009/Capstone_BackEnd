package com.ordermicroservice.OrderMicroservice.controller;

import com.ordermicroservice.OrderMicroservice.dtos.ContactUsDto;
import com.ordermicroservice.OrderMicroservice.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/contact")
public class ContactUsController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send/{mail}")
    public ResponseEntity<String> sendContactEmail(@PathVariable String mail,@RequestBody ContactUsDto contactUsDto) {
        // Pass the sender's email to be used as the Reply-To email
        String recipients = "riyaarjariya62@gmail.com";
        emailService.sendEmail(mail,contactUsDto);

        return new ResponseEntity<>("Email sent successfully!", HttpStatus.OK);
    }
}
/*public class ContactUsController {




    @Autowired
    private EmailService emailService;


    @PostMapping("/send/{mail}")
    public ResponseEntity<String> sendContactEmail(@PathVariable String mail, @RequestBody ContactUsDto contactUsDto) {
//        String recipients = "riyaarjariya62@gmail.com"; // Add your email addresses

        emailService.sendEmail(contactUsDto,mail);

        return new ResponseEntity<>("Email sent successfully!", HttpStatus.OK);
    }


}*/

