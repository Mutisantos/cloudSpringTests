package com.eh.testob.testobpingservice;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@AutoConfigureMockMvc
@WebMvcTest(TestobPingServiceApplication.class)
@ContextConfiguration(classes = TestobPingServiceApplication.class)
class TestobPingServiceApplicationTests {

   @Autowired
   private MockMvc mockMvc;

   @Test
   public void testPongEndpoint() throws Exception {
      MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/ping").contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound()).andReturn();
      String serviceResult = result.getResponse().getContentAsString();
      assertNotNull(serviceResult);
   }

}
