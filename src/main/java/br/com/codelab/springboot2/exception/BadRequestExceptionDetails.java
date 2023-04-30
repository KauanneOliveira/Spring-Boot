package br.com.codelab.springboot2.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BadRequestExceptionDetails {
    private String title;
    private int status;
    private String datails;
    private String developerMessage;
    private LocalDateTime timestamp;
}
