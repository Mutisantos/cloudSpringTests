package com.eh.testob.testobpingservice.controllers;

import java.util.List;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

   private final Function<PongResponseDTO, ResponseEntity> generateEndpointResponse = pongResponse -> {
      logger.info("Message {} saved", pongResponse);
      return ResponseEntity.status(HttpStatus.OK)
            .header(HttpHeaders.CONTENT_TYPE,
                  MediaType.APPLICATION_JSON_VALUE)
            .body(String.format(TIMESTAMP_EPOCH, pongResponse.getResponseTimestamp().getTime()));
   };

   @NonNull
   private final PingService pingService;

   @GetMapping(path = "/ping")
   public ResponseEntity retrieveFromPingService() throws FallbackException {
      return pingService.retrievePongResponse().map(generateEndpointResponse).orElseThrow(FallbackException::new);
   }

   @GetMapping(path = "/top10")
   public ResponseEntity retriveTop10PingResponses() {
      List<PongResponseDTO> pongResponses = pingService.retrieveTop10Responses();
      return ResponseEntity.status(HttpStatus.OK).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .body(pongResponses);
   }
}
