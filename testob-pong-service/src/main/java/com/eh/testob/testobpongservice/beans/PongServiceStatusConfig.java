package com.eh.testob.testobpongservice.beans;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties("testob-pong-service")
@Component
public class PongServiceStatusConfig {
   private boolean active = true;
}
