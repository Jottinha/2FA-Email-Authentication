package com.project.auth.services;

import antlr.CodeGenerator;
import com.project.auth.utils.CodeGenerate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Slf4j
@Service
public class EmailService {
    @Autowired
    private JavaMailSender emailSender;

    public String sendEmail(String email){
        String code = "";
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            code = new CodeGenerate().randomCode();

            message.setTo(email);
            message.setSubject("Codigo verificação");
            message.setText(code);
            emailSender.send(message);
        }catch (Exception e){
            code = "";
            log.error("Fail to send email", e);
        }finally {
            return code;
        }
    }

}
