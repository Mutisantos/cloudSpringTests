package com.eh.testob.testobpingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.eh.testob.testobpingservice")
@EnableDiscoveryClient
public class TestobPingServiceApplication {

   public static void main(String[] args) {
      SpringApplication.run(TestobPingServiceApplication.class, args);
   }

}
