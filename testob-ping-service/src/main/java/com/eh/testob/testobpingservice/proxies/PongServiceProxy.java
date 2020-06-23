package com.eh.testob.testobpingservice.proxies;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(name = "testob-api-gateway")
@RibbonClient(name = "testob-pong-service")
public interface PongServiceProxy {

   @GetMapping(path = "/testob-pong-service/pong")
   public String retrievePongMessage();

}
