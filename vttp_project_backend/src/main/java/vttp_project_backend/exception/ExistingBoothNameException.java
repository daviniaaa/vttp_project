package vttp_project_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ExistingBoothNameException extends RuntimeException{
    public ExistingBoothNameException() {
        super("Existing email in database");
    }
}
