package id.co.mii.serverapp.services;

import id.co.mii.serverapp.models.dto.requests.EmailRequest;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import java.util.Arrays;
import java.util.Date;

@Service
@AllArgsConstructor
public class EmailService {
    private JavaMailSender sender;
    private TemplateEngine templateEngine;

    public EmailRequest sendMessage(EmailRequest emailRequest) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailRequest.getTo());
        message.setSubject(emailRequest.getSubject());
        message.setText(emailRequest.getText());
        sender.send(message);
        return emailRequest;
    }

    public EmailRequest sendHtmlMessage(EmailRequest emailRequest) {
        Context context = new Context();
        context.setVariable("name", emailRequest.getTo().split("@")[0]);
        String htmlContent = templateEngine.process("quiz.html", context);
        try {
            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(emailRequest.getTo());
            helper.setSubject(emailRequest.getSubject());
            helper.setText(htmlContent, true);
            sender.send(message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return emailRequest;
    }
}
