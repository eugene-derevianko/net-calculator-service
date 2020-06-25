package com.symulakr.netcalculator;

import com.symulakr.netcalculator.configuration.RestApiConfiguration;
import com.symulakr.netcalculator.configuration.ServiceConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;

@Import({
      ServiceConfiguration.class,
      RestApiConfiguration.class
})
@SpringBootConfiguration
@EnableAutoConfiguration
public class NetCalculatorServiceApplication {

   public static void main(String[] args) {
      SpringApplication.run(NetCalculatorServiceApplication.class, args);
   }

}
