package thiago.silveira.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TeamIncorrectFieldException extends RuntimeException{
    public TeamIncorrectFieldException(String msg) {
        super();
    }
}
