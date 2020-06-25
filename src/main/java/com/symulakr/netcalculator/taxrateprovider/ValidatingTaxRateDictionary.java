package com.symulakr.netcalculator.taxrateprovider;

import com.symulakr.netcalculator.validation.TaxRateValidator;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Optional;

@RequiredArgsConstructor
public class ValidatingTaxRateDictionary implements TaxRateDictionary {

   private final TaxRateValidator taxRateValidator;
   private final TaxRateDictionary taxRateDictionary;

   @Override
   public Optional<BigDecimal> taxRateFor(String countryIso) {
      return taxRateDictionary.taxRateFor(countryIso)
            .map(this::checkedTaxRate);
   }

   public BigDecimal checkedTaxRate(BigDecimal taxRate) {
      taxRateValidator.validate(taxRate);
      return taxRate;
   }
}
