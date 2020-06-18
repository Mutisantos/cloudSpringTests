package com.eh.testob.testobpingservice.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.eh.testob.testobpingservice.entities.PongResponse;
import com.eh.testob.testobpingservice.services.PingService;

@ExtendWith(SpringExtension.class)
public class PingControllerTest {

   private static final String RESPONSE_MESSAGE = "{message:A|B|C|D}";

   private PingController pingController;

   @Mock
   private PingService pingService;

   @BeforeEach
   public void setUp() {
      pingController = new PingController(pingService);
   }

   @Test
   public void retrieveFromPingServiceTest() {
      Mockito.when(pingService.retrievePongResponse())
      .thenReturn(Optional
            .of(PongResponse.builder().jsonResponse(RESPONSE_MESSAGE).responseTimestamp(new Date()).build()));
      Response response = pingController.retrieveFromPingService();
      assertNotNull(response.getEntity());
   }

   @Test
   public void retrieveFromPingServiceTestException() {
      Mockito.when(pingService.retrievePongResponse())
      .thenReturn(Optional.ofNullable(null));
      IllegalStateException expectedException = Assertions.assertThrows(IllegalStateException.class, () -> {
         pingController.retrieveFromPingService();
      });
      assertNotNull(expectedException);
   }

   @Test
   public void retriveTop10PingResponsesTest() {
      Mockito.when(pingService.retrieveTop10Responses()).thenReturn(Collections.singletonList(
            PongResponse.builder().jsonResponse(RESPONSE_MESSAGE).responseTimestamp(new Date()).build()));
      Response response = pingController.retriveTop10PingResponses();
      List<PongResponse> top10Responses = (List<PongResponse>) response.getEntity();
      assertNotNull(top10Responses);
      assertEquals(RESPONSE_MESSAGE, top10Responses.stream().findFirst().get().getJsonResponse());
   }

}
