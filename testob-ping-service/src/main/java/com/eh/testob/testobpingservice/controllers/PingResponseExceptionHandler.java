package com.eh.testob.testobpingservice.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.eh.testob.testobpingservice.exceptions.FallbackException;

@ControllerAdvice
public class PingResponseExceptionHandler extends ResponseEntityExceptionHandler {

   private static final Logger logger = LoggerFactory.getLogger(PingResponseExceptionHandler.class);

   @ExceptionHandler(value = { FallbackException.class })
   protected ResponseEntity<Object> handleUnresponsivePingService(RuntimeException ex, WebRequest request) {
      String bodyOfResponse = "The Pong Service is currently unavailable to provide responses";
      logger.error("Error while retriving Pong Response: Service is unresponsive");
      return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NO_CONTENT, request);
   }
}
