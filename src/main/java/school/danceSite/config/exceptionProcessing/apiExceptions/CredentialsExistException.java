package school.danceSite.config.exceptionProcessing.apiExceptions;

public class CredentialsExistException extends RuntimeException {

    public CredentialsExistException(String message) {
        super(message);
    }

    public CredentialsExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
