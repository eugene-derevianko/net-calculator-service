package com.symulakr.netcalculator.configuration;

import com.symulakr.netcalculator.validation.TaxRateValidator;
import com.symulakr.netcalculator.validation.RangeTaxRateValidator;
import com.symulakr.netcalculator.validation.ValidationProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidationConfiguration {

   @Bean
   @ConditionalOnProperty(prefix = "net-calculator-service.tax-rate-validation", name = "enabled", havingValue = "false")
   public TaxRateValidator disabledTaxRateValidator() {
      return (taxRate) -> {
      };
   }

   @Configuration
   @ConditionalOnProperty(prefix = "net-calculator-service.tax-rate-validation", name = "enabled", havingValue = "true")
   public static class RangeTaxRateValidatorConfiguration {

      @Bean
      @ConfigurationProperties(prefix = "net-calculator-service.tax-rate-validation")
      public ValidationProperties validationProperties() {
         return new ValidationProperties();
      }

      @Bean
      public TaxRateValidator rangeTaxRateValidator(ValidationProperties validationProperties) {
         return new RangeTaxRateValidator(validationProperties.getMinTaxRate(), validationProperties.getMaxTaxRate());
      }
   }

}
