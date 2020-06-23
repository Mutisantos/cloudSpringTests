package com.eh.testob.testobpingservice.dtos;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PongResponseDTO {

   private Long id;

   private String jsonResponse;

   private Date responseTimestamp;

}
