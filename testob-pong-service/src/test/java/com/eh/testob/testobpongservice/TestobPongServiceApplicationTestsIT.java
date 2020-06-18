package com.eh.testob.testobpongservice;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.eh.testob.testobpongservice.beans.PongService;
import com.eh.testob.testobpongservice.beans.PongServiceStatusConfig;
import com.eh.testob.testobpongservice.controllers.PongController;

@AutoConfigureMockMvc
@WebMvcTest
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
@ContextConfiguration(classes = { PongServiceStatusConfig.class, PongService.class, PongController.class })
class TestobPongServiceApplicationTestsIT {

   @Autowired
   private MockMvc mockMvc;

   @Test
   public void testPongEndpoint() throws Exception {
      MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/pong").contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
      String serviceResult = result.getResponse().getContentAsString();
      assertNotNull(serviceResult);
   }

}
