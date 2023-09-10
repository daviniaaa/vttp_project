package vttp_project_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoExistingEmailException extends RuntimeException {

    public NoExistingEmailException() {
        super("No existing email in database");
    }


}
