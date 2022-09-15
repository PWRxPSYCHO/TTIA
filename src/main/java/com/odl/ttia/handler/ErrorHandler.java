package com.odl.ttia.handler;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.webjars.NotFoundException;

import com.odl.ttia.model.ErrorDto;

@ControllerAdvice
public class ErrorHandler {
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(ErrorHandler.class);

    @ExceptionHandler
    public ResponseEntity<ErrorDto> handle(Exception exception) {
        LOGGER.error("Internal server error occurred", exception);
        return response(HttpStatus.INTERNAL_SERVER_ERROR, "Unknown error occurred.");

    }

    @ExceptionHandler
    public ResponseEntity<ErrorDto> handle(NotFoundException exception) {
        return response(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    private ResponseEntity<ErrorDto> response(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(new ErrorDto(message));
    }

}
