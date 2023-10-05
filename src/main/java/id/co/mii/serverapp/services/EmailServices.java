package id.co.mii.serverapp.services;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import id.co.mii.serverapp.models.dto.EmailRequest;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmailServices {
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

  public EmailRequest sendMessageUsingHtml(EmailRequest request) {

    // mengirim email dengan format MIME (Multipurpose Internet Mail Extensions)
    MimeMessage message = javaMailSender.createMimeMessage();

    try {
      MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
          StandardCharsets.UTF_8.name());
      helper.setTo(request.getTo());
      helper.setSubject(request.getSubject());

      Context context = new Context();
      context.setVariable("username", request.getTo().split("@")[0].replace(".", " "));
      context.setVariable("text", request.getText());

      String html = springTemplateEngine.process("SimpleTemplate", context);

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
