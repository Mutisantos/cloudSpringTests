package com.eh.testob.testobpingservice.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class PingResponseExceptionHandler extends ResponseEntityExceptionHandler {
   @ExceptionHandler(value = { IllegalStateException.class })
   protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
      String bodyOfResponse = "The Pong Service is currently unavailable to provide responses";
      return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NO_CONTENT, request);
   }
}
