package com.eh.testob.testobpingservice.exceptions;

public class FallbackException extends Exception {

   private static final String EXCEPTION_MESSAGE = "A fallback exception happened";

   public FallbackException() {
      super(EXCEPTION_MESSAGE);
   }

}
