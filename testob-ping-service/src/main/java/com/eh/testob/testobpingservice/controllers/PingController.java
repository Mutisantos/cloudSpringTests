package com.eh.testob.testobpingservice.controllers;

import java.util.List;
import java.util.function.Function;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eh.testob.testobpingservice.dtos.PongResponseDTO;
import com.eh.testob.testobpingservice.exceptions.FallbackException;
import com.eh.testob.testobpingservice.services.PingService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PingController {

   private static final String TIMESTAMP_EPOCH = "{timestamp:%s}";

   private static final Logger logger = LoggerFactory.getLogger(PingController.class);

   private final Function<PongResponseDTO, Response> generateEndpointResponse = pongResponse -> {
      logger.info("Message {} saved", pongResponse);
      return Response.status(Status.OK).entity(String.format(TIMESTAMP_EPOCH, pongResponse.getResponseTimestamp()
            .getTime()))
            .build();
   };

   @NonNull
   private final PingService pingService;

   @GetMapping(path = "/ping")
   public Response retrieveFromPingService() throws FallbackException {
      return pingService.retrievePongResponse().map(generateEndpointResponse).orElseThrow(FallbackException::new);
   }

   @GetMapping(path = "/top10")
   public Response retriveTop10PingResponses() {
      List<PongResponseDTO> pongResponses = pingService.retrieveTop10Responses();
      return Response.status(Status.OK).entity(pongResponses).build();
   }
}
