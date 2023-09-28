package co.id.ms.mii.serverapp.controllers;

import co.id.ms.mii.serverapp.dto.EmailRequest;
import co.id.ms.mii.serverapp.services.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@AllArgsConstructor
@RequestMapping("/email")
public class EmailController {
    private EmailService emailService;

    @PostMapping("/simple")
    public EmailRequest sendSimpleMessage(
            @RequestBody EmailRequest emailRequest
    ) {
        return emailService.sendmessage(emailRequest);
    }

    @PostMapping("/attach")
    public EmailRequest sendMessageWithAttachment(
            @RequestBody EmailRequest emailRequest
    ) {
        return emailService.sendMessageWithAttachment(emailRequest);
    }
}
