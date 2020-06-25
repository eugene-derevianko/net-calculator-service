package com.symulakr.netcalculator.taxrateprovider;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

public class InMemoryTaxRateDictionary implements TaxRateDictionary {

   private final Map<String, BigDecimal> taxRates;

   public InMemoryTaxRateDictionary(TaxRateProvider taxRateProvider) {
      taxRates = Map.copyOf(taxRateProvider.taxRates());
   }

   @Override
   public Optional<BigDecimal> taxRateFor(String countryIso) {
      return Optional.ofNullable(taxRates.get(countryIso));
   }
}
