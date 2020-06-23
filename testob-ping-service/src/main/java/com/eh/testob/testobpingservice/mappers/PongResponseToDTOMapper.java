package com.eh.testob.testobpingservice.mappers;

import com.eh.testob.testobpingservice.dtos.PongResponseDTO;
import com.eh.testob.testobpingservice.entities.PongResponse;

public class PongResponseToDTOMapper {

   public static PongResponseDTO map(PongResponse pongResponse) {
      return PongResponseDTO.builder().id(pongResponse.getId()).jsonResponse(pongResponse.getJsonResponse())
            .responseTimestamp(pongResponse.getResponseTimestamp()).build();
   }

}
