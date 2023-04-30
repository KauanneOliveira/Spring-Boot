//O Handler Global é usado para personalizar/padronizar suas excecções, então terá uma classe com os atributos e outra que vai informar para usar sempre essas variáveis quando a exceção ocorrer


package br.com.codelab.springboot2.handler;

import br.com.codelab.springboot2.exception.BadRequestException;
import br.com.codelab.springboot2.exception.BadRequestExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice //essa notação meio que diz para todos os controllers que eles têm que usar o que colocar dentro dessa classe baseado como se fosse uma flag
public class RestExceptionHandler {
    @ExceptionHandler(BadRequestException.class) //@ExceptionHandler é a flag nesse caso. Significado dela: caso seja uma exceção BadRequestException terá que usar o método que tem aqui e retornar esse valor
    public ResponseEntity<BadRequestExceptionDetails> handlerBadRequestException(BadRequestException bre){
        return new ResponseEntity<>(
                BadRequestExceptionDetails.builder()
                        .timestamp( LocalDateTime.now() )
                        .status( HttpStatus.BAD_REQUEST.value() )
                        .title("Bad Request Exception, Check the Documentation (Erro, verifique seu documento)")
                        .datails( bre.getMessage() )
                        .developerMessage( bre.getClass().getName() )
                        .build(), HttpStatus.BAD_REQUEST);
    }
}
