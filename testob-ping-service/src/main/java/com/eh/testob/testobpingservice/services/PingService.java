package com.eh.testob.testobpingservice.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.stereotype.Service;

import com.eh.testob.testobpingservice.entities.PongResponse;
import com.eh.testob.testobpingservice.proxies.PongServiceProxy;
import com.eh.testob.testobpingservice.repositories.PongResponseRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
@EnableCircuitBreaker
public class PingService {

   @Autowired
   PongServiceProxy pongServiceProxy;

   @Autowired
   PongResponseRepository pongResponseRepository;

   @HystrixCommand(fallbackMethod = "fallBackResponse")
   public Optional<PongResponse> retrievePongResponse() {
      PongResponse pongRes = PongResponse.builder().jsonResponse(pongServiceProxy.retrievePongMessage())
            .responseTimestamp(new Date()).build();
      pongResponseRepository.save(pongRes);
      return Optional.of(pongRes);
   }


   public List<PongResponse> retrieveTop10Responses() {
      return pongResponseRepository.findTop10ByOrderByIdDesc();
   }


   private Optional<PongResponse> fallBackResponse() {
      return Optional.ofNullable(null);
   }

}
