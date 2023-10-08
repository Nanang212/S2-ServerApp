package id.co.mii.serverapp.services;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Arrays;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import id.co.mii.serverapp.models.dto.request.EmailRequest;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmailService {
    private JavaMailSender javaMailSender;
    private SpringTemplateEngine springTemplateEngine;

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
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(emailRequest.getTo());
            helper.setSubject(emailRequest.getSubject());
            helper.setText(emailRequest.getText());

            FileSystemResource file = new FileSystemResource(
                    new File(emailRequest.getAttachment()));

            helper.addAttachment(file.getFilename(), file);
            javaMailSender.send(message);

        } catch (Exception e) {

            System.out.println("Error = " + e.getMessage());
        }
        return emailRequest;
    }

    public EmailRequest sendHtmlMessage(EmailRequest emailRequest) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            Context context = new Context();
            context.setVariable("name", emailRequest.getNameSender());
            // context.setVariable("date", LocalDate.now().toString());
            // context.setVariable("technologies", Arrays.asList("tes1","tes2"));

            helper.setTo(emailRequest.getTo());
            helper.setSubject(emailRequest.getSubject());
            String html = springTemplateEngine.process("index.html", context);
            helper.setText(html, true);

            javaMailSender.send(message);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return emailRequest;
    }

}
