package id.rojak.election.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by inagi on 7/4/17.
 */
@ControllerAdvice
public class    ErrorHandler {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private void processValidateionError(IllegalArgumentException e) {
        log.info("Illegal Argument Exception -> 400 BAD REQUEST because of {} ", e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    private void internalServerError(Exception e) {
        log.info("Internal Server Error Exception -> {}", e.getMessage());
    }
}
