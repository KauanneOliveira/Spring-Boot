package br.com.codelab.springboot2.exception;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
public class ExceptionDetails {
    protected String title;
    protected int status;
    protected String datails;
    protected String developerMessage;
    protected LocalDateTime timestamp;
}
