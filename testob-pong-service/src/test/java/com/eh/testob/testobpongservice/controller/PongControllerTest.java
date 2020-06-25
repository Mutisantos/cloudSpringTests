package com.eh.testob.testobpongservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import com.eh.testob.testobpongservice.beans.PongService;
import com.eh.testob.testobpongservice.beans.PongServiceStatusConfig;
import com.eh.testob.testobpongservice.controllers.PongController;

@ExtendWith(SpringExtension.class)
public class PongControllerTest {

   @Mock
   private PongService pongService;

   @Mock
   private PongServiceStatusConfig serviceStatusConfig;

   @InjectMocks
   private PongController pongController;

   @Test
   public void retrieveRandomValuesTest() {
      when(serviceStatusConfig.isActive()).thenReturn(true);
      when(pongService.pongResponseGenerator()).thenReturn("Response");
      ResponseEntity response = pongController.retrieveRandomValues();
      assertEquals("Response", response.getBody());
   }

   @Test
   public void retrieveRandomValuesTestException() {
      when(serviceStatusConfig.isActive()).thenReturn(false);
      ResponseStatusException expectedException = Assertions.assertThrows(ResponseStatusException.class, () -> {
         pongController.retrieveRandomValues();
      });
      assertEquals(HttpStatus.SERVICE_UNAVAILABLE, expectedException.getStatus());
   }

}
