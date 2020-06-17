package com.eh.testob.testobapigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.eh.testobapigateway.prefilter.ZuulRoutingFilter;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class TestobApiGatewayApplication {

   public static void main(String[] args) {
      SpringApplication.run(TestobApiGatewayApplication.class, args);
   }

   @Bean
   public ZuulRoutingFilter zuulRoutingFilter() {
      return new ZuulRoutingFilter();
   }

}
