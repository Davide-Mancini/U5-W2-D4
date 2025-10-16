package davidemancini.U5_W2_D4.exceptions;

import davidemancini.U5_W2_D4.payloads.ErrorsPayload;
import davidemancini.U5_W2_D4.payloads.ErrorsWithListDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsWithListDTO handleValidationErrors( ValidationException ex){
        return new ErrorsWithListDTO(ex.getMessage(),LocalDateTime.now(),ex.getErrorsMessage());
    }


    @ExceptionHandler(NotFoundExceptions.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorsPayload handleNotFound(NotFoundExceptions ex){
        return new ErrorsPayload(ex.getMessage(), LocalDateTime.now());
    }
}

