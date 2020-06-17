package com.eh.testob.testobpingservice.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PONG_RESP")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PongResponse {
   @Id
   @Column(name = "RESPONSE_ID", nullable = false)
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "JSON_RESP")
   private String jsonResponse;

   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "RESP_TMSTMP")
   private Date responseTimestamp;

}
