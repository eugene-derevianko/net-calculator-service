package com.symulakr.netcalculator.configuration;

import com.symulakr.netcalculator.taxrateprovider.ConfigurationBasedTaxRateProvider;
import com.symulakr.netcalculator.taxrateprovider.TaxRateProvider;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class TaxRateProviderConfiguration {

   @Bean
   @Profile("taxRatesConfiguration")
   @ConfigurationProperties(prefix = "net-calculator-service")
   public TaxRateProvider configurationBasedTaxRateProvider() {
      return new ConfigurationBasedTaxRateProvider();
   }

/*
This is just example of different possible implementation of TaxRateProvider's based on different source of data

   @Bean
   public TaxRateProvider dataBaseBasedTaxRateProvider(){
      return new DataBaseBasedTaxRateProvider();
   }

   @Bean
   public TaxRateProvider restBasedTaxRateProvider(){
      return new RestBaseBasedTaxRateProvider();
   }
*/

}
