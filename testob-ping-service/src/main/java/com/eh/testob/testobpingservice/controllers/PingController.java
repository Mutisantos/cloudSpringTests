package com.eh.testob.testobpingservice.controllers;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eh.testob.testobpingservice.entities.PongResponse;
import com.eh.testob.testobpingservice.services.PingService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class PingController {

   private static final String TIMESTAMP_EPOCH = "{timestamp:%s}";

   private final Logger logger = LoggerFactory.getLogger(this.getClass());

   @Autowired
   private final PingService pingService;

   @GetMapping(path = "/ping")
   public Response retrieveFromPingService() {
      Optional<PongResponse> pongRes = pingService.retrievePongResponse();
      if (pongRes.isPresent()) {
         logger.info("Message {} saved", pongRes);
         return Response.status(Status.OK)
               .entity(String.format(TIMESTAMP_EPOCH, pongRes.get().getResponseTimestamp().getTime())).build();
      } else {
         logger.info("Error while retriving Pong Response: Service is unresponsive");
         throw new IllegalStateException();
      }

   }

   @GetMapping(path = "/top10")
   public Response retriveTop10PingResponses() {
      List<PongResponse> pongResponses = pingService.retrieveTop10Responses();
      return Response.status(Status.OK).entity(pongResponses).build();
   }
}
