package co.id.mii.serverapp.services;

import java.io.File;
import java.nio.charset.StandardCharsets;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import co.id.mii.serverapp.models.dto.request.EmailRequest;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmailServices {
    private JavaMailSender javaMailSender;
    private TemplateEngine templateEngine;

    public EmailRequest sendSimpleMessage(EmailRequest emailRequest){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailRequest.getTo());
        message.setSubject(emailRequest.getSubject());
        message.setText(emailRequest.getText());
        javaMailSender.send(message);
        return emailRequest;
    }
    public EmailRequest sendMessageWithAttachment(EmailRequest emailRequest){
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,true);

            helper.setText(emailRequest.getTo());
            helper.setSubject(emailRequest.getSubject());
            helper.setText(emailRequest.getText());

            FileSystemResource file = new FileSystemResource(
                new File(emailRequest.getAttachment())
            );

            helper.addAttachment(file.getFilename(), file);
            javaMailSender.send(message);

        } catch (Exception e) {
            System.out.println("error =" + e.getMessage());
        }
        return emailRequest;
    }
    public EmailRequest sendHtmlMessage(EmailRequest emailRequest) {
        Context context = new Context();
        context.setVariable("name", emailRequest.getTo().split("@")[0]);
        String htmlContent = templateEngine.process("kirimTugas.html", context);
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(emailRequest.getTo());
            helper.setSubject(emailRequest.getSubject());
            helper.setText(htmlContent, true);
            javaMailSender.send(message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return emailRequest;
    }
}
