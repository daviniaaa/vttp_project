package vttp_project_backend.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ExistingEmailException.class)
    public ResponseEntity<String> handleExistingEmailException(ExistingEmailException e) {
        return new ResponseEntity<String>(e.getMessage(), HttpStatusCode.valueOf(409));
    }

    @ExceptionHandler(CreateAccountException.class)
    public ResponseEntity<String> handleCreateAccountException(CreateAccountException e) {
        return new ResponseEntity<String>(e.getMessage(), HttpStatusCode.valueOf(500));
    }

    @ExceptionHandler(NoExistingEmailException.class)
    public ResponseEntity<String> handleNoExistingEmailException(NoExistingEmailException e) {
        return new ResponseEntity<String>(e.getMessage(), HttpStatusCode.valueOf(404));
    }

    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<String> handleWrongPasswordException(WrongPasswordException e) {
        return new ResponseEntity<String>(e.getMessage(), HttpStatusCode.valueOf(401));
    }

    @ExceptionHandler(PasswordsDoNotMatchException.class)
    public ResponseEntity<String> handleWrongPasswordException(PasswordsDoNotMatchException e) {
        return new ResponseEntity<String>(e.getMessage(), HttpStatusCode.valueOf(400));
    }
}
