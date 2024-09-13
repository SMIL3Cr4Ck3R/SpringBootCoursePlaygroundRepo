package io.sc0.REST.API.Demo.Exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.Date;

public class ExceptionResponse {

    private HttpStatus status;
    private String message;
    private ZonedDateTime timeStamp;

    public ExceptionResponse() {
    }

    public ExceptionResponse(HttpStatus status, String message, ZonedDateTime timeStamp) {
        this.status = status;
        this.message = message;
        this.timeStamp = timeStamp;

    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public ZonedDateTime getTimeStamp() {
        return timeStamp;
    }

    @Override
    public String toString() {
        return "ExceptionResponse{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
