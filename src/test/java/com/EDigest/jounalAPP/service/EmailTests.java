package com.EDigest.jounalAPP.service;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

@SpringBootTest
public class EmailTests {

    @Autowired
    EmailService emailService;

    @Test
    void testEmail(){
        emailService.sendEmail("farhantigadi123@gmail.com","Testing Email service","Hi we are in our testing phase");
    }
}