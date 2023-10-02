package id.co.mii.serverapp.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.mii.serverapp.models.dto.EmailRequest;
import id.co.mii.serverapp.services.EmailServices;

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

    @PostMapping(
        value = "/attachment",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EmailRequest attachment(@RequestBody EmailRequest request) {
        return emailServices.sendMessageWithAttachment(request);
    }

    
    @PostMapping(
        value = "/html",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EmailRequest sendMessageWithHtml(@RequestBody EmailRequest request) {
        return emailServices.sendMessageUsingHtml(request);
    }

}
