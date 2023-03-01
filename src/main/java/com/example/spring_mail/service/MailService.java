package com.example.spring_mail.service;


import com.example.spring_mail.dto.RequestHtmlMailDto;
import com.example.spring_mail.dto.RequestMailDto;
import com.example.spring_mail.dto.RequestMimeMailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class MailService {

    @Value("${spring.mail.username}")
    private String send_email;
    private final JavaMailSender javaMailSender;
    private final MailSender mailSender;


    public void send(RequestMailDto dto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(send_email);
        message.setTo(dto.getReceive_email());
        message.setSubject(dto.getTitle());
        message.setText(dto.getContent());
        mailSender.send(message);
    }

    public void htmlSend(RequestHtmlMailDto dto) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();

        StringBuilder sb = new StringBuilder();
        sb.append("<html><body>\n html <b>전송</b> \n</body></html>");

        message.setFrom(send_email);
        message.setRecipients(Message.RecipientType.TO, dto.getReceive_email());
        message.setSubject("제목작성");
        message.setText(sb.toString(), "utf-8", "html");
        javaMailSender.send(message);
    }


    public void mimeSend(RequestMimeMailDto dto) throws MessagingException, IOException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true, "utf-8");

        mimeMessageHelper.setFrom(send_email);
        mimeMessageHelper.setTo(dto.getReceive_email().stream().toArray(String[]::new));
        mimeMessageHelper.setSubject("제목작성");
        mimeMessageHelper.addAttachment("첨부파일_1", new ClassPathResource("static/spring.jpg"));

        StringBuilder sb = new StringBuilder();
        sb.append("<html><body>\n html <b>전송</b> \n</body></html>");
        mimeMessageHelper.setText(sb.toString(), true);

        javaMailSender.send(message);
    }
}
