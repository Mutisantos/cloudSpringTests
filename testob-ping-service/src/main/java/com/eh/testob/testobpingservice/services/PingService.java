package com.eh.testob.testobpingservice.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.stereotype.Service;

import com.eh.testob.testobpingservice.dtos.PongResponseDTO;
import com.eh.testob.testobpingservice.entities.PongResponse;
import com.eh.testob.testobpingservice.mappers.PongResponseToDTOMapper;
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
   public Optional<PongResponseDTO> retrievePongResponse() {
      PongResponse pongRes = PongResponse.builder().jsonResponse(pongServiceProxy.retrievePongMessage())
            .responseTimestamp(new Date()).build();
      pongResponseRepository.save(pongRes);
      return Optional.of(PongResponseToDTOMapper.map(pongRes));
   }


   public List<PongResponseDTO> retrieveTop10Responses() {
      return pongResponseRepository.findTop10ByOrderByIdDesc().stream().map(PongResponseToDTOMapper::map)
            .collect(Collectors.toList());
   }


   private Optional<PongResponseDTO> fallBackResponse() {
      logger.info("Fallback method called");
      return Optional.ofNullable(null);
   }

}
