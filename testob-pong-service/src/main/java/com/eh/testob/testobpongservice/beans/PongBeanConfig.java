package com.eh.testob.testobpongservice.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PongBeanConfig {

   @Bean
   public PongService pongService() {
      return new PongService();
   }

}
