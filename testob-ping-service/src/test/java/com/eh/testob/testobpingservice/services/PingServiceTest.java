package com.eh.testob.testobpingservice.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.eh.testob.testobpingservice.dtos.PongResponseDTO;
import com.eh.testob.testobpingservice.dtos.PongResponseDTO;
import com.eh.testob.testobpingservice.entities.PongResponse;
import com.eh.testob.testobpingservice.proxies.PongServiceProxy;
import com.eh.testob.testobpingservice.repositories.PongResponseRepository;

@ExtendWith(SpringExtension.class)
public class PingServiceTest {

   private static final String RESPONSE_MESSAGE = "{message:A|B|C|D}";

   private PingService pingService;

   @Mock
   private PongServiceProxy pongServiceProxy;

   @Mock
   private PongResponseRepository pongResponseRepository;

   @BeforeEach
   public void setUp() {
      pingService = new PingService(pongServiceProxy, pongResponseRepository);
   }


   @Test
   public void retrievePongResponseTest() {
      Mockito.when(pongServiceProxy.retrievePongMessage()).thenReturn(RESPONSE_MESSAGE);
      Optional<PongResponseDTO> response = pingService.retrievePongResponse();
      assertEquals(RESPONSE_MESSAGE, response.get().getJsonResponse());
   }

   @Test
   public void retrieveTop10ResponsesTest() {
      Mockito.when(pongResponseRepository.findTop10ByOrderByIdDesc()).thenReturn(Collections.singletonList(
            PongResponse.builder().jsonResponse(RESPONSE_MESSAGE).responseTimestamp(new Date()).build()));
      List<PongResponseDTO> top10Responses = pingService.retrieveTop10Responses();
      assertNotNull(top10Responses);
      assertEquals(RESPONSE_MESSAGE, top10Responses.stream().findFirst().get().getJsonResponse());
   }

}
