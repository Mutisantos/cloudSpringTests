package com.eh.testob.testobpingservice.controllers;

import javax.ws.rs.core.Response;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class PingController {

   @GetMapping(path = "/ping")
   public Response retrieveExchangeValue() {

      throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Randomizer Config Disabled");
   }
}
