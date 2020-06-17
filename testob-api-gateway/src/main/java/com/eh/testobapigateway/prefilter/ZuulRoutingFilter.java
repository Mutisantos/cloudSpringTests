package com.eh.testobapigateway.prefilter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class ZuulRoutingFilter extends ZuulFilter {

   private static final String SERVICE_ID = "serviceId";
   private static final String TESTOB_PING_SERVICE = "testob-ping-service";

   @Override
   public boolean shouldFilter() {
      HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
      String requestURI = request.getRequestURI();
      if (requestURI.contains(TESTOB_PING_SERVICE)) {
         return false;
      }
      return true;
   }

   @Override
   public Object run() throws ZuulException {
      HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
      String requestURI = request.getRequestURI();
      throw new ZuulException(requestURI, 0, requestURI);
   }

   @Override
   public String filterType() {
      return "pre";
   }

   @Override
   public int filterOrder() {
      return 2;
   }

}
