package id.co.mii.serverapp.controllers;

import id.co.mii.serverapp.models.dto.requests.EmailRequest;
import id.co.mii.serverapp.services.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@AllArgsConstructor
@RequestMapping("/email")
@PreAuthorize("hasRole('ADMIN')")
public class EmailController {
    private final EmailService emailService;

    @PostMapping(
        value = "/html",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EmailRequest sendMessageWithHtml(@RequestBody EmailRequest request) {
        return emailService.sendMessageWithHtml(request);
    }

    @PostMapping(
        value = "/simple",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EmailRequest send(@RequestBody EmailRequest request) {
        return emailService.sendSimpleMessage(request);
    }

    @PostMapping(
        value = "/attachment",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EmailRequest attachment(@RequestBody EmailRequest request) {
        return emailService.sendMessageWithAttachment(request);
    }

    @GetMapping(
        value = "/newsletter",
        produces = MediaType.TEXT_HTML_VALUE
    )
    public ModelAndView newsletter() {
        return new ModelAndView("views/newsletter_form");
    }
}
