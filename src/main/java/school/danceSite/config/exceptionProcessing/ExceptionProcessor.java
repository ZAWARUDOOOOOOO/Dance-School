package school.danceSite.config.exceptionProcessing;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import school.danceSite.config.exceptionProcessing.apiExceptions.CredentialsExistException;
import school.danceSite.config.exceptionProcessing.apiExceptions.ClientNotFoundException;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ExceptionProcessor {

    @ExceptionHandler(value = {ClientNotFoundException.class})
    public ResponseEntity<Object> handleClientNotFoundException(ClientNotFoundException e) {
        HttpStatus badRequest = HttpStatus.BAD_GATEWAY;//правильный ли статус?
        ExceptionDetails exceptionDetails = new ExceptionDetails(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z")),
                e
        );
        return new ResponseEntity<>(exceptionDetails, badRequest);
    }

    @ExceptionHandler(value = {CredentialsExistException.class})
    public ResponseEntity<Object> handleCredentialsExistException(CredentialsExistException e) {
        HttpStatus badRequest = HttpStatus.BAD_GATEWAY;//правильный ли статус?
        ExceptionDetails exceptionDetails = new ExceptionDetails(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z")),
                e
        );
        return new ResponseEntity<>(exceptionDetails, badRequest);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleGlobalException(Exception e) {
        HttpStatus badRequest = HttpStatus.INTERNAL_SERVER_ERROR;
        ExceptionDetails exceptionDetails = new ExceptionDetails(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z")),
                e
        );
        return new ResponseEntity<>(exceptionDetails, badRequest);
    }
}
