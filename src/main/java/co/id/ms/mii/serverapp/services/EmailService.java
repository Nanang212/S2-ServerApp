package co.id.ms.mii.serverapp.services;

import co.id.ms.mii.serverapp.dto.request.EmailRequest;
import co.id.ms.mii.serverapp.utils.SpringTemplateEmailEngine;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.nio.charset.StandardCharsets;

@Service
@AllArgsConstructor
@Slf4j
public class EmailService {
    private JavaMailSender javaMailSender;
    private SpringTemplateEmailEngine templateEmailEngine;

    public EmailRequest sendmessage(EmailRequest emailRequest){
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
                    new File(emailRequest.getAttachment())
            );

            helper.addAttachment(file.getFilename(), file);
            javaMailSender.send(message);
        } catch (Exception e) {
            System.out.println("Error = " + e.getMessage());
        }
        return emailRequest;
    }

    public EmailRequest sendHtmlMessage(EmailRequest emailRequest){
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            helper.setTo(emailRequest.getTo());
            helper.setSubject(emailRequest.getSubject());

            Context context = new Context();
            context.setVariable("emailRequest", emailRequest);
            String html = templateEmailEngine.springTemplateEngine().process("templateEmail",context);
            helper.setText(html, true);
            javaMailSender.send(message);
        } catch (Exception e){
            System.out.println("Error = " + e.getMessage());
        }

        return emailRequest;
    }
}
