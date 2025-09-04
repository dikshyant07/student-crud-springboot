package com.data.jpa.exceptionhandler;

import com.data.jpa.exceptions.*;
import com.data.jpa.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({InvalidCredentialsException.class,
            InvalidEmailException.class, InvalidPasswordException.class, SamePasswordException.class, StudentAlreadyExistsException.class,
            StudentDoesNotExistsException.class,
            PasswordDoesNotMatchException.class})
    public ResponseEntity<ErrorResponse> handleAllError(RuntimeException e) {
        HttpStatus httpStatus;
        if (e instanceof StudentDoesNotExistsException) {
            httpStatus = HttpStatus.NOT_FOUND;
        } else if (e instanceof StudentAlreadyExistsException) {
            httpStatus = HttpStatus.CONFLICT;
        } else {

            httpStatus = HttpStatus.BAD_REQUEST;
        }

        ErrorResponse response = new ErrorResponse(httpStatus, "Failed", e.getMessage(), new Date().toString());
        return new ResponseEntity<>(response, httpStatus);

    }
}
