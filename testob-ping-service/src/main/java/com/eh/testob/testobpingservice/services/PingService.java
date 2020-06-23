package com.eh.testob.testobpingservice.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.stereotype.Service;

import com.eh.testob.testobpingservice.entities.PongResponse;
import com.eh.testob.testobpingservice.proxies.PongServiceProxy;
import com.eh.testob.testobpingservice.repositories.PongResponseRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@Service
@EnableCircuitBreaker
@AllArgsConstructor
public class PingService {

   @NonNull
   private final PongServiceProxy pongServiceProxy;

   @NonNull
   private final PongResponseRepository pongResponseRepository;

   private static final Logger logger = LoggerFactory.getLogger(PingService.class);

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
      logger.info("Fallback method called");
      return Optional.ofNullable(null);
   }

}
