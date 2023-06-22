package com.project.auth.services;

import antlr.CodeGenerator;
import com.project.auth.model.EmailBody;
import com.project.auth.utils.CodeGenerate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
@Slf4j
@Service
public class EmailService {
    @Autowired
    private JavaMailSender emailSender;

    public String sendEmail(String email){
        String code = "";
        try{
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            code = new CodeGenerate().randomCode();

            helper.setTo(email);
            helper.setSubject("Codigo verificação");
            helper.setText(new EmailBody().createBody(code), true);
            emailSender.send(message);
        }catch (Exception e){
            code = "";
            log.error("Fail to send email", e);
        }finally {
            return code;
        }
    }

}
