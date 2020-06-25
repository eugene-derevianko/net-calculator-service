package com.symulakr.netcalculator.configuration;

import com.symulakr.netcalculator.service.DefaultNetCalculatorService;
import com.symulakr.netcalculator.service.NetCalculatorService;
import com.symulakr.netcalculator.taxrateprovider.TaxRateDictionary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(TaxRateDictionaryConfiguration.class)
@Configuration
@EnableConfigurationProperties
public class ServiceConfiguration {

   @Bean
   public NetCalculatorService NetCalculatorService(TaxRateDictionary validatingTaxRateDictionary,
                                                    @Value("${net-calculator-service.scalePrecision:2}") Integer scalePrecision) {
      return new DefaultNetCalculatorService(validatingTaxRateDictionary, scalePrecision);
   }

}
