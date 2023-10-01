package co.id.mii.serverapp.controllers;

import javax.mail.MessagingException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.id.mii.serverapp.models.dto.request.EmailRequest;
import co.id.mii.serverapp.services.EmailServices;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/email")
public class EmailController {
    private EmailServices emailServices;

    @PostMapping("/simple")
    public EmailRequest sendSimpleMessage(
            @RequestBody EmailRequest emailRequest) {
        return emailServices.sendSimpleMessage(emailRequest);
    }

    @PostMapping("/attach")
    public EmailRequest sendMessageWithAttachment(
            @RequestBody EmailRequest emailRequest) {
        return emailServices.sendMessageWithAttachment(emailRequest);
    }

    @PostMapping("/html")
    public EmailRequest sendHtmlMessage(
            @RequestBody EmailRequest emailRequest) {
        return emailServices.sendHtmlMessage(emailRequest);
    }

}
