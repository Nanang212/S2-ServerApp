package id.co.mii.serverapp.services;

import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.spring5.SpringTemplateEngine;
import id.co.mii.serverapp.models.dto.requests.EmailRequest;
import org.thymeleaf.context.Context;
import lombok.RequiredArgsConstructor;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender emailSender;
    private final SpringTemplateEngine templateEngine;

    // ngeset data 
    public void sendHtmlMessage(EmailRequest emailrRequest) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name()); 
        // MULTIPART_MODE_MIXED_RELATED --> mengirim berbagai jenis media
        Context context = new Context(); // memasukkan variable yg digunakan dalam mengganti placeholder dlm template email
        context.setVariables(emailrRequest.getProperties());
        helper.setFrom(emailrRequest.getFrom());
        helper.setTo(emailrRequest.getTo());
        helper.setSubject(emailrRequest.getSubject());
        String html = templateEngine.process(emailrRequest.getTemplate(), context);
        helper.setText(html, true);

        // Mengirim email
        emailSender.send(message);
    }
}
