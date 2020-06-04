package com.bridgelabz.bookstore.utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.*;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Service
public class Email {

    private JavaMailSender mailSender;
    private MailContentBuilder mailContentBuilder;

    @Autowired
    public Email(JavaMailSender mailSender, MailContentBuilder mailContentBuilder) {
        this.mailSender = mailSender;
        this.mailContentBuilder = mailContentBuilder;
    }

    public Email() {

    }



    public void sendEmail(String recipient, String message) {

        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(new InternetAddress("no_reply@gmail.com", "NoReply-JD"));
            messageHelper.setTo(InternetAddress.parse(recipient));
            messageHelper.setSubject("Sample mail subject");
            String content = mailContentBuilder.build(message);
            messageHelper.setText(content, true);
            System.out.println("sss");
        };
        try {
            mailSender.send(messagePreparator);
            System.out.println("Email Send");
        } catch (MailException e) {
            System.out.println("Email Sdend");
            e.printStackTrace();
            // runtime exception; compiler will not force you to handle it
        }
    }

}
