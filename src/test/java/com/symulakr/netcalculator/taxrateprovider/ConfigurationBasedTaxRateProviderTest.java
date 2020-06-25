package com.symulakr.netcalculator.taxrateprovider;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
      classes = ConfigurationBasedTaxRateProviderTest.TestConfiguration.class
)
@ActiveProfiles("taxRatesConfiguration")
class ConfigurationBasedTaxRateProviderTest {

   @Autowired
   private TaxRateProvider configurationBasedTaxRateProvider;

   @Test
   void createsConfigurationBasedTaxRateProvider() {
      assertThat(configurationBasedTaxRateProvider.taxRates())
            .contains(Map.entry("EE", BigDecimal.valueOf(0.0001)))
            .contains(Map.entry("LOW_TAX", BigDecimal.valueOf(-0.19)))
            .contains(Map.entry("HIGH_TAX", BigDecimal.valueOf(1.19)));
   }

   @SpringBootConfiguration
   @EnableConfigurationProperties
   public static class TestConfiguration {

      @Bean
      @ConfigurationProperties(prefix = "net-calculator-service")
      public TaxRateProvider configurationBasedTaxRateProvider() {
         return new ConfigurationBasedTaxRateProvider();
      }

   }

}