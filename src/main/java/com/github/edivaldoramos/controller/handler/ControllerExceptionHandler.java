package com.github.edivaldoramos.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    private ResponseEntity<Object> construirResponseEntity(HttpStatus httpStatus, String msgErro) {
        String tagError = "error";
        if (logger.isErrorEnabled()) {
            logger.error(msgErro);
        }
        return ResponseEntity.status(httpStatus).header(tagError, msgErro).build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> exception(Throwable e, HttpServletRequest request) {
        return construirResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
}
