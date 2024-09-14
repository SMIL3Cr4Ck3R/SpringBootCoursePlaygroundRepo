package io.sc0.REST.API.Demo.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleException(NotFoundException ex) {

        ExceptionResponse er = new ExceptionResponse(
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                ZonedDateTime.now());

        return new ResponseEntity<>(er,HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleException(IllegalDataRequestFormatException ex) {

        ExceptionResponse er = new ExceptionResponse(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                ZonedDateTime.now());

        return new ResponseEntity<>(er,HttpStatus.BAD_REQUEST);
    }
}
