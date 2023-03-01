package com.example.spring_mail.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestHtmlMailDto {
    private String receive_email;
    private String title;
    private String content;
}
