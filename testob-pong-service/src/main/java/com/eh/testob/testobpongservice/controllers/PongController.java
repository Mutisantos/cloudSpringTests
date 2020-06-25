package com.eh.testob.testobpongservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
   public ResponseEntity<String> retrieveRandomValues() {
      if (serviceStatusConfig.isActive()) {
         return ResponseEntity.status(HttpStatus.OK).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
               .body(pongService.pongResponseGenerator());
      } else {
         throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Randomizer Config Disabled");
      }

   }

}
