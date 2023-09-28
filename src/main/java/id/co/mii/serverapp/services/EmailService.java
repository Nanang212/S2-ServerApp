package id.co.mii.serverapp.services;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import id.co.mii.serverapp.models.dto.requests.EmailRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class EmailService {
    
    private JavaMailSender javaMailSender;

    public EmailRequest sendSimpleMessage(EmailRequest request){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(request.getTo());
        message.setSubject(request.getSubject());
        message.setText(request.getText());
        log.info(request.toString());
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

           FileSystemResource file  = new FileSystemResource(new File(request.getAttachment()));

        //    FileSystemResource file = new FileSystemResource(new File(request.getAttachment()));
           helper.addAttachment("Atteachment",file);
           
           javaMailSender.send(message);
      } catch (Exception e) {
        System.out.println("Error :" + e.getMessage());
      }
      return request;
    }
}
