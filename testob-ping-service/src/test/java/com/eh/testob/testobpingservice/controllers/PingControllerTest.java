package com.eh.testob.testobpingservice.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.eh.testob.testobpingservice.dtos.PongResponseDTO;
import com.eh.testob.testobpingservice.exceptions.FallbackException;
import com.eh.testob.testobpingservice.services.PingService;

@ExtendWith(SpringExtension.class)
public class PingControllerTest {

   private static final String RESPONSE_MESSAGE = "{message:A|B|C|D}";

   @Mock
   private PingService pingService;

   @InjectMocks
   private PingController pingController;

   @Test
   public void retrieveFromPingServiceTest() throws FallbackException {
      Mockito.when(pingService.retrievePongResponse())
      .thenReturn(Optional
            .of(PongResponseDTO.builder().jsonResponse(RESPONSE_MESSAGE).responseTimestamp(new Date()).build()));
      ResponseEntity response = pingController.retrieveFromPingService();
      assertNotNull(response.getBody());
   }

   @Test
   public void retrieveFromPingServiceTestException() {
      Mockito.when(pingService.retrievePongResponse())
      .thenReturn(Optional.ofNullable(null));
      FallbackException expectedException = Assertions.assertThrows(FallbackException.class, () -> {
         pingController.retrieveFromPingService();
      });
      assertNotNull(expectedException);
   }

   @Test
   public void retriveTop10PingResponsesTest() {
      Mockito.when(pingService.retrieveTop10Responses()).thenReturn(Collections.singletonList(
            PongResponseDTO.builder().jsonResponse(RESPONSE_MESSAGE).responseTimestamp(new Date()).build()));
      ResponseEntity response = pingController.retriveTop10PingResponses();
      List<PongResponseDTO> top10Responses = (List<PongResponseDTO>) response.getBody();
      assertNotNull(top10Responses);
      assertEquals(RESPONSE_MESSAGE, top10Responses.stream().findFirst().get().getJsonResponse());
   }

}
