package id.rojak.auth.controllers;

import id.rojak.common.error.ResourceNotFoundException;
import id.rojak.auth.controllers.dto.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLException;

/**
 * Created by inagi on 7/4/17.
 */
@ControllerAdvice
public class ErrorHandler {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ResponseEntity<ErrorMessage> processValidationError(Exception e) {
        log.info("Illegal Argument Exception -> 400 BAD REQUEST because of {} ", e.getMessage());
        return new ResponseEntity<ErrorMessage>(
                new ErrorMessage(
                        400,
                        "Bad Request!",
                        e.getClass().getName(),
                        e.getLocalizedMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private ResponseEntity<ErrorMessage> processNotFoundResource(Exception e) {
        log.info("Resource not found {}", e.getMessage());

        return new ResponseEntity<ErrorMessage>(
                new ErrorMessage(
                        404,
                        "Not Found",
                        e.getClass().getName(),
                        e.getLocalizedMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({SQLException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    private ResponseEntity<ErrorMessage> processSqlException(Exception e) {
        log.info("Internal Server Error Exception -> {}", e.getLocalizedMessage());
        return new ResponseEntity<ErrorMessage>(
                new ErrorMessage(
                        500,
                        "Internal Server Error!",
                        e.getClass().getName(),
                        e.getLocalizedMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
