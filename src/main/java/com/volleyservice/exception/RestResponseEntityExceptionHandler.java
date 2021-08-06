package com.volleyservice.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<Object> handleNotFoundException(
            RuntimeException ex, HttpServletRequest httpServletRequest, WebRequest webRequest) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage(), httpServletRequest.getServletPath());

        return handleExceptionInternal(ex, apiError,
                new HttpHeaders(), HttpStatus.NOT_FOUND, webRequest);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    protected ResponseEntity<Object> handleNotValidInputInRequestBody(
            RuntimeException ex, WebRequest webRequest//, HttpServletRequest httpServletRequest, WebRequest webRequest
    ) {
        return handleExceptionInternal(ex, "chycił go",
                new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
    }
}
