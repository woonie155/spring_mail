package com.example.spring_mail.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RequestMimeMailDto {
    private List<String> receive_email;
    private String title;
    private String content;
}
