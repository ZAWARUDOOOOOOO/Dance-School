package school.danceSite.config.exceptionProcessing;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ExceptionDetails {//поля - то что будет видеть пользователь, при возникновении ошибки

    private final String message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timestamp;
    private final Throwable throwable;

    public ExceptionDetails(String message, HttpStatus httpStatus, ZonedDateTime timestamp, Throwable throwable) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
        this.throwable = throwable;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public Throwable getThrowable() {
        return throwable;
    }
}
