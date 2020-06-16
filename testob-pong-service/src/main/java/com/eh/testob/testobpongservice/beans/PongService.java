package com.eh.testob.testobpongservice.beans;

import java.util.Random;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class PongService {

   final static Supplier<Integer> intRandomGenerator = () -> {
      Random rand = new Random();
      return rand.nextInt(20);
   };

   final Function<Integer, String> intToAscii = x -> (char) x.intValue() + "|";

   public String pongResponseGenerator() {
      return Stream.iterate(100 + intRandomGenerator.get(), x -> x + 1).limit(10).map(intToAscii::apply)
            .map(x -> x.toUpperCase())
            .reduce((fullstring, nextletter) -> fullstring + "" + nextletter).get();
   }

}
