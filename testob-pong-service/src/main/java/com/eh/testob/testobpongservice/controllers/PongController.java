package com.eh.testob.testobpongservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eh.testob.testobpongservice.beans.PongService;

@RestController
public class PongController {

   @Autowired
   private PongService pongService;

   @GetMapping(path = "/pong")
   public String retrieveExchangeValue() {
      return pongService.pongResponseGenerator();
   }

}
