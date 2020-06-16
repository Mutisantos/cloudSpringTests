package com.eh.testob.testobconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class TestobConfigServerApplication {

   public static void main(String[] args) {
      SpringApplication.run(TestobConfigServerApplication.class, args);
   }

}
