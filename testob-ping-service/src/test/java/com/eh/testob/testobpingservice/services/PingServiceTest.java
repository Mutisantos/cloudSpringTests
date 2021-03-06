package com.eh.testob.testobpingservice.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.eh.testob.testobpingservice.dtos.PongResponseDTO;
import com.eh.testob.testobpingservice.entities.PongResponse;
import com.eh.testob.testobpingservice.proxies.PongServiceProxy;
import com.eh.testob.testobpingservice.repositories.PongResponseRepository;

@ExtendWith(SpringExtension.class)
public class PingServiceTest {

   private static final String RESPONSE_MESSAGE = "{message:A|B|C|D}";

   @Mock
   private PongServiceProxy pongServiceProxy;

   @Mock
   private PongResponseRepository pongResponseRepository;

   @InjectMocks
   private PingService pingService;


   @Test
   public void retrievePongResponseTest() {
      when(pongServiceProxy.retrievePongMessage()).thenReturn(RESPONSE_MESSAGE);
      Optional<PongResponseDTO> response = pingService.retrievePongResponse();
      assertEquals(RESPONSE_MESSAGE, response.get().getJsonResponse());
   }

   @Test
   public void retrieveTop10ResponsesTest() {
      when(pongResponseRepository.findTop10ByOrderByIdDesc()).thenReturn(Collections
            .singletonList(
            PongResponse.builder().jsonResponse(RESPONSE_MESSAGE).responseTimestamp(new Date()).build()));
      List<PongResponseDTO> top10Responses = pingService.retrieveTop10Responses();
      assertNotNull(top10Responses);
      assertEquals(RESPONSE_MESSAGE, top10Responses.stream().findFirst().get().getJsonResponse());
   }

}
