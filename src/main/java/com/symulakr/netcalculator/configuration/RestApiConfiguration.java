package com.symulakr.netcalculator.configuration;

import com.symulakr.netcalculator.service.NetCalculatorService;
import com.symulakr.netcalculator.web.NetCalculatorServiceRestApi;
import com.symulakr.netcalculator.web.advice.ServiceExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestApiConfiguration {

   @Bean
   public NetCalculatorServiceRestApi netCalculatorServiceRestApi(NetCalculatorService netCalculatorService) {
      return new NetCalculatorServiceRestApi(netCalculatorService);
   }

   @Bean
   public ServiceExceptionHandler serviceExceptionHandler() {
      return new ServiceExceptionHandler();
   }

}
