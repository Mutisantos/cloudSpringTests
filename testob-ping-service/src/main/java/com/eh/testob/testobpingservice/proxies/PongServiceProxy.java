package com.eh.testob.testobpingservice.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


//Without API Gateways, the other microservice can be called directly by service name  
//@FeignClient(name = "currency-exchange-service")
//@RibbonClient(name = "currency-exchange-service")

// Without Ribbon, it's required to set the URL also.
@FeignClient(name = "testob-pong-service", url = "localhost:8100")
public interface PongServiceProxy {
   // With ZUUL, calling the service requires to add the Service name to the URL
   // @GetMapping(path = "/currency-exchange-service/currency-exchange/from/{from}/to/{to}")

   // Without the API Gateway, the exposed end point can be called directly
   @GetMapping(path = "/pong")
   public String retrievePongMessage();

}
