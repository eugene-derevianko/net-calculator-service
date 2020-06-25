package com.symulakr.netcalculator.configuration;

import com.symulakr.netcalculator.taxrateprovider.InMemoryTaxRateDictionary;
import com.symulakr.netcalculator.taxrateprovider.TaxRateDictionary;
import com.symulakr.netcalculator.taxrateprovider.TaxRateProvider;
import com.symulakr.netcalculator.taxrateprovider.ValidatingTaxRateDictionary;
import com.symulakr.netcalculator.validation.TaxRateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({
      TaxRateProviderConfiguration.class,
      ValidationConfiguration.class
})
@Configuration
public class TaxRateDictionaryConfiguration {

   @Bean
   public TaxRateDictionary taxRateDictionary(TaxRateProvider taxRateProvider) {
      return new InMemoryTaxRateDictionary(taxRateProvider);
   }

   @Bean
   public TaxRateDictionary validatingTaxRateDictionary(TaxRateValidator taxRateValidator,
                                                        TaxRateDictionary taxRateDictionary) {
      return new ValidatingTaxRateDictionary(taxRateValidator, taxRateDictionary);
   }

/*
This is just example of different possible implementation of TaxRateDictionary

   @Bean
   public TaxRateDictionary cachedTaxRateDictionary(TaxRateProvider taxRateProvider) {
      return new CachedTaxRateDictionary(taxRateProvider);
   }

   @Bean
   public TaxRateDictionary somethingElseTaxRateDictionary(TaxRateProvider taxRateProvider) {
      return new SomethingElseTaxRateDictionary(taxRateProvider);
   }
*/

}
