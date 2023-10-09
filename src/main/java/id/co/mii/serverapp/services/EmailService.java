package id.co.mii.serverapp.services;

import java.nio.charset.StandardCharsets;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import id.co.mii.serverapp.models.dto.requests.EmailRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
    
    private final JavaMailSender emailSender;
    private final SpringTemplateEngine templateEngine;

    public EmailRequest sendHtml(EmailRequest emailRequest){
        MimeMessage message = emailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED, StandardCharsets.UTF_8.name());
        
            Context context = new Context();

            context.setVariables(emailRequest.getProperties());
            helper.setFrom(emailRequest.getFrom());
            helper.setTo(emailRequest.getTo());
            helper.setSubject(emailRequest.getSubject());
            String html = templateEngine.process(emailRequest.getTemplate(), context);
            helper.setText(html, true);

            log.info("sending email: {} with html body: {}", emailRequest, html);
            emailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        
        return emailRequest;
    }
}
