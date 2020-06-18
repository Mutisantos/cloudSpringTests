package com.eh.testob.testobpongservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import com.eh.testob.testobpongservice.beans.PongService;
import com.eh.testob.testobpongservice.beans.PongServiceStatusConfig;
import com.eh.testob.testobpongservice.controllers.PongController;

@ExtendWith(SpringExtension.class)
public class PongControllerTest {

   private PongController pongController;

   @Mock
   private PongService pongService;

   @Mock
   private PongServiceStatusConfig serviceStatusConfig;

   @BeforeEach
   public void setUp() {
      pongController = new PongController(pongService, serviceStatusConfig);
   }

   @Test
   public void retrieveRandomValuesTest() {
      Mockito.when(serviceStatusConfig.isActive()).thenReturn(true);
      Mockito.when(pongService.pongResponseGenerator()).thenReturn("Response");
      Response response = pongController.retrieveRandomValues();
      assertEquals("Response", response.getEntity());
   }

   @Test
   public void retrieveRandomValuesTestException() {
      Mockito.when(serviceStatusConfig.isActive()).thenReturn(false);
      ResponseStatusException expectedException = Assertions.assertThrows(ResponseStatusException.class, () -> {
         pongController.retrieveRandomValues();
      });
      assertEquals(HttpStatus.SERVICE_UNAVAILABLE, expectedException.getStatus());
   }

}
