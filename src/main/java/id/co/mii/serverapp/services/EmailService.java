package id.co.mii.serverapp.services;

import java.io.File;
import java.nio.charset.StandardCharsets;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.FileSystemResource;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import id.co.mii.serverapp.models.dto.requests.EmailRequest;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmailService {
    
    private JavaMailSender javaMailSender;

    private final SpringTemplateEngine templateEngine;

    public EmailRequest sendSimpleMessage(EmailRequest request){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(request.getTo());
        message.setSubject(request.getSubject());
        message.setText(request.getText());
        javaMailSender.send(message);
        return request;
    }

    public EmailRequest sendMessageWithAttachment(EmailRequest request){
      try {
          MimeMessage message = javaMailSender.createMimeMessage();
           MimeMessageHelper helper = new MimeMessageHelper(message,true);

           helper.setTo(request.getTo());
           helper.setSubject(request.getSubject());
           helper.setText(request.getText());
           
           FileSystemResource file = new FileSystemResource(
            new File(request.getAttachment())
          );
           helper.addAttachment(file.getFilename(),file);
           
           javaMailSender.send(message);
      } catch (Exception e) {
        System.out.println("Error :" + e.getMessage());
      }
      return request;
    }

     public EmailRequest sendHtmlMessage(EmailRequest request) {
        MimeMessage message = javaMailSender.createMimeMessage();

       try {
         MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

        String name = request.getTo();
        String[] split = name.split("@");

        String username = split[0];

        username = username.replaceAll("[0-9.]", "");

        Context context = new Context();
        context.setVariable("username", username);
        context.setVariable("token",request.getToken());
        context.setVariable("text", request.getText());

        String html = templateEngine.process("verify-email", context);

        helper.setTo(request.getTo());
        helper.setSubject(request.getSubject());
        helper.setText(html, true);

        if (request.getAttachment() != null) {
          FileSystemResource file = new FileSystemResource(new File(request.getAttachment()));
          helper.addAttachment(file.getFilename(), file);;
        }
        
        javaMailSender.send(message);
       
      } catch (MessagingException e) {
           throw new RuntimeException(e.getMessage());
       }
        

        return request;
    }

    public EmailRequest createVerifycationEmail(String email, String token){
      return EmailRequest.builder().to(email).subject("Verifycation Email").token(token).build();
    }
}
