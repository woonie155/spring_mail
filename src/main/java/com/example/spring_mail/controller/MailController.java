package com.example.spring_mail.controller;

import com.example.spring_mail.dto.RequestHtmlMailDto;
import com.example.spring_mail.dto.RequestMailDto;
import com.example.spring_mail.dto.RequestMimeMailDto;
import com.example.spring_mail.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.IOException;


@RestController
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    @PostMapping("/send/text")
    public String send(@RequestBody RequestMailDto dto) throws MessagingException {
        mailService.send(dto);
        return "ok";
    }

    @PostMapping("/send/html")
    public String sendHtml(@RequestBody RequestHtmlMailDto dto) throws MessagingException {
        mailService.htmlSend(dto);
        return "ok";
    }

    @PostMapping("/send/mime")
    public String sendMime(@RequestBody RequestMimeMailDto dto) throws MessagingException, IOException {
        mailService.mimeSend(dto);
        return "ok";
    }
}
