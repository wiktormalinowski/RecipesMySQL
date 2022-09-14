package pl.maliniak.recipesmysql.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such element")
public class NoSuchElementFoundException extends ResponseStatusException {

    public NoSuchElementFoundException() {

        super(HttpStatus.NOT_FOUND);
    }
}
