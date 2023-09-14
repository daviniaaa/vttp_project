package vttp_project_backend.exception;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class DeleteAccountException extends RuntimeException {
    
    public DeleteAccountException() {
        super("There was an error while processing. Please try again or contact support for help.");
    }
}
