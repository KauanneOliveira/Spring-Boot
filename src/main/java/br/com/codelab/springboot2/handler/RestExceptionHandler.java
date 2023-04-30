//O Handler Global é usado para personalizar/padronizar suas excecções, então terá uma classe com os atributos e outra que vai informar para usar sempre essas variáveis quando a exceção ocorrer


package br.com.codelab.springboot2.handler;

import br.com.codelab.springboot2.exception.BadRequestException;
import br.com.codelab.springboot2.exception.BadRequestExceptionDetails;
import br.com.codelab.springboot2.exception.ExceptionDetails;
import br.com.codelab.springboot2.exception.ValidationExceptionDetails;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice //essa notação meio que diz para todos os controllers que eles têm que usar o que colocar dentro dessa classe baseado como se fosse uma flag
@Log4j2
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(BadRequestException.class) //@ExceptionHandler é a flag nesse caso. Significado dela: caso seja uma exceção BadRequestException terá que usar o método que tem aqui e retornar esse valor
    public ResponseEntity<BadRequestExceptionDetails> handleBadRequestException(BadRequestException bre){
        return new ResponseEntity<>(
                BadRequestExceptionDetails.builder()
                        .timestamp( LocalDateTime.now() )
                        .status( HttpStatus.BAD_REQUEST.value() )
                        .title("Bad Request Exception, Check the Documentation (Erro, verifique seu documento)")
                        .datails( bre.getMessage() )
                        .developerMessage( bre.getClass().getName() )
                        .build(), HttpStatus.BAD_REQUEST);
    }

    protected ResponseEntity<Object> handleMethodArgumentNotValid( MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request){
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));

        return new ResponseEntity<>(
                ValidationExceptionDetails.builder()
                        .timestamp( LocalDateTime.now() )
                        .status( HttpStatus.BAD_REQUEST.value() )
                        .title("Bad Request Exception,Invalid Fields (Erro, campo inválido)")
                        .datails( "Check the field(s) error (Verifique seus campos com erro)" )
                        .developerMessage( exception.getClass().getName() )
                        .fields(fields)
                        .fieldsMessage(fieldsMessage)
                        .build(), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .timestamp( LocalDateTime.now() )
                .status( status.value() )
                .title( ex.getCause().getMessage() )
                .datails( ex.getMessage() )
                .developerMessage( ex.getClass().getName() )
                .build();

        return new ResponseEntity<>(exceptionDetails, headers, status);
    }
}
