package id.co.mii.serverapp.services;

import id.co.mii.serverapp.models.dto.request.EmailRequest;
import lombok.AllArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Set;

@Service
@AllArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine springTemplateEngine;
    private final Validator validator;

    public EmailRequest sendSimpleMessage(EmailRequest request) {
        Set<ConstraintViolation<EmailRequest>> constraintViolations = validator.validate(request);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(request.getTo());
        message.setSubject(request.getSubject());
        message.setText(request.getText());

        javaMailSender.send(message);

        return request;
    }

    public EmailRequest sendMessageWithAttachment(EmailRequest request) {
        Set<ConstraintViolation<EmailRequest>> constraintViolations = validator.validate(request);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }

        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(request.getTo());
            helper.setSubject(request.getSubject());
            helper.setText(request.getText());

            if (request.getAttachment() != null) {
                FileSystemResource file = new FileSystemResource(new File(request.getAttachment()));
                helper.addAttachment(Objects.requireNonNull(file.getFilename()), file);
            }

            javaMailSender.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        return request;
    }

    public EmailRequest sendMessageWithHtml(EmailRequest request) {
        Set<ConstraintViolation<EmailRequest>> constraintViolations = validator.validate(request);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }

        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            helper.setTo(request.getTo());
            helper.setSubject(request.getSubject());

            Context context = new Context();
            context.setVariable("username", request.getTo().split("@")[0].replace(".", " "));
            context.setVariable("text", request.getText());

            String html = springTemplateEngine.process("newsletter", context);

            helper.setText(html, true);

            if (request.getAttachment() != null) {
                FileSystemResource file = new FileSystemResource(new File(request.getAttachment()));
                helper.addAttachment(Objects.requireNonNull(file.getFilename()), file);
            }

            javaMailSender.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        return request;
    }
}
