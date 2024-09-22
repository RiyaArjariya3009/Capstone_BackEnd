package com.ordermicroservice.OrderMicroservice.service;

import com.ordermicroservice.OrderMicroservice.dtos.ContactUsDto;

public interface EmailService {
    void sendEmail(String mail,ContactUsDto contactUsDto);
}

