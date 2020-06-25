package com.symulakr.netcalculator.taxrateprovider;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfigurationBasedTaxRateProvider implements TaxRateProvider {

   private Map<String, BigDecimal> taxRates;

   @Override
   public Map<String, BigDecimal> taxRates() {
      return taxRates;
   }

}
