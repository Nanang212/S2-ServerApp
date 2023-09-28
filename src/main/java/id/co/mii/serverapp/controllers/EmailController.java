package id.co.mii.serverapp.controllers;

import id.co.mii.serverapp.models.dto.requests.EmailRequest;
import id.co.mii.serverapp.services.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/email")
public class EmailController {

    private EmailService emailService;

    @PostMapping("/send-simple")
    public EmailRequest simpleSend(@RequestBody EmailRequest emailRequest) {
        return emailService.sendMessage(emailRequest);
    }

    @PostMapping("/send-html")
    public EmailRequest htmlSend(@RequestBody EmailRequest emailRequest) {
        return emailService.sendHtmlMessage(emailRequest);
    }
}
