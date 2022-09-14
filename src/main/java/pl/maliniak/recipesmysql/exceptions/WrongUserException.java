package pl.maliniak.recipesmysql.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class WrongUserException extends ResponseStatusException {

    public WrongUserException() {

        super(HttpStatus.FORBIDDEN);
    }
}
