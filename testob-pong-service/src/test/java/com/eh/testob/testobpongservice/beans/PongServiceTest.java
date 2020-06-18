package com.eh.testob.testobpongservice.beans;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class PongServiceTest {

   private PongService pongService;

   @BeforeEach
   public void setUp() {
      pongService = new PongService();
   }

   @Test
   public void pongResponseGeneratorTest() {
      assertNotNull(pongService.pongResponseGenerator());
   }


}
