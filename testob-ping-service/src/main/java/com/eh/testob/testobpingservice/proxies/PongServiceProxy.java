package com.eh.testob.testobpingservice.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(name = "testob-pong-service", url = "localhost:8100")
public interface PongServiceProxy {

   @GetMapping(path = "/pong")
   public String retrievePongMessage();

}
