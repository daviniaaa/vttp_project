package vttp_project_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ExistingEmailException extends RuntimeException {

    public ExistingEmailException() {
        super("Existing email in database");
    }

    // public ExistingEmailException(String arg0) {
    //     super(arg0);
    // }

    // public ExistingEmailException(Throwable arg0) {
    //     super(arg0);
    // }

    // public ExistingEmailException(String arg0, Throwable arg1) {
    //     super(arg0, arg1);
    // }

    // public ExistingEmailException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
    //     super(arg0, arg1, arg2, arg3);
    // }


}
