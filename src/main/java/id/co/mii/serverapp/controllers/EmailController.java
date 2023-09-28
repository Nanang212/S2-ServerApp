package id.co.mii.serverapp.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.mii.serverapp.models.dto.requests.EmailRequest;
import id.co.mii.serverapp.services.EmailService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/email")
public class EmailController {
    
    private EmailService emailService;

    @PostMapping("/simple")
    public EmailRequest sendSimpleMessage(@RequestBody EmailRequest request){
        return emailService.sendSimpleMessage(request);
    }

    @PostMapping("/attach")
    public EmailRequest sendMessageWithAttachment(@RequestBody EmailRequest request){
        return emailService.sendMessageWithAttachment(request);
    }
}
