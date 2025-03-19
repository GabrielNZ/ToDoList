package com.gabrielnz.todolist.controller.exception;

import com.gabrielnz.todolist.service.exception.DataBaseException;
import com.gabrielnz.todolist.service.exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardError> notFoundException(NotFoundException e, HttpServletRequest request) {
        String msg = "Resource not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(new StandardError(Instant.now(), status.value(), msg, e.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(DataBaseException.class)
    public ResponseEntity<StandardError> dataBaseException(DataBaseException e, HttpServletRequest request) {
        String msg = "Database error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(new StandardError(Instant.now(), status.value(), msg, e.getMessage(), request.getRequestURI()));
    }
}
