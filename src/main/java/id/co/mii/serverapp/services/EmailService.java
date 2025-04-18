package id.co.mii.serverapp.services;

import id.co.mii.serverapp.models.dto.requests.EmailRequest;
import java.io.File;
import javax.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

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
}
