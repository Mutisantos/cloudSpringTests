package com.eh.testob.testobpongservice.controllers;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.eh.testob.testobpongservice.beans.PongService;
import com.eh.testob.testobpongservice.beans.PongServiceStatusConfig;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RestController
@RefreshScope
@NoArgsConstructor
@AllArgsConstructor
public class PongController {

   @Autowired
   private PongService pongService;

   @Autowired
   private PongServiceStatusConfig serviceStatusConfig;

   @GetMapping(path = "/pong")
   public Response retrieveRandomValues() {
      if (serviceStatusConfig.isActive()) {
         return Response.status(Status.OK).entity(pongService.pongResponseGenerator()).build();
      } else {
         throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Randomizer Config Disabled");
      }

   }

}
