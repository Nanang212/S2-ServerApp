package id.co.mii.serverapp.controllers;

import id.co.mii.serverapp.models.Email;
import id.co.mii.serverapp.services.EmailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/email")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public String sendHtmlEmail(@RequestBody Email email) {
        try {
            emailService.sendHtmlMessage(email);
            return "Email berhasil dikirim!";
        } catch (MessagingException e) {
            return "Gagal mengirim email: " + e.getMessage();
        }
    }
}
