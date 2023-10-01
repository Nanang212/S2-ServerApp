package id.co.mii.serverapp.services;

import javax.mail.internet.MimeMessage;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import id.co.mii.serverapp.models.dto.request.EmailRequest;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmailService {
    private JavaMailSender javaMailSender;

    public EmailRequest sendSimpleMessage(EmailRequest emailRequest) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailRequest.getTo());
        message.setSubject(emailRequest.getSubject());
        message.setText(emailRequest.getText());
        javaMailSender.send(message);
        return emailRequest;
    }

    public EmailRequest sendMessageWithAttachment(EmailRequest emailRequest) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true); // mengirim file dengan cara multipart
            helper.setTo(emailRequest.getTo());
            helper.setSubject(emailRequest.getSubject());
            helper.setText(emailRequest.getText());
            FileSystemResource file = new FileSystemResource(emailRequest.getAttachment());
            helper.addAttachment(file.getFilename(), file);
            javaMailSender.send(message);
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
        return emailRequest;
    }
}
