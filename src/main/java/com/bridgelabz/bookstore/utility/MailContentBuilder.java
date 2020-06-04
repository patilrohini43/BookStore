package com.bridgelabz.bookstore.utility;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailContentBuilder {

    private TemplateEngine templateEngine;

 //   private static final String EMAIL_INLINEIMAGE_TEMPLATE_NAME = "/home/rohini/Videos/BookStore/src/main/resources/template/emailTemplate.html";

    @Autowired
    public MailContentBuilder(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public String build(String message) {
        System.out.println(message+"message");
        Context context = new Context();
        context.setVariable("message", message);
        return templateEngine.process("emailTemplate", context);
    }
}