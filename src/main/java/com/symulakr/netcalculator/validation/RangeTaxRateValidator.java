package com.symulakr.netcalculator.validation;

import com.symulakr.netcalculator.exception.InvalidTaxRateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

import static java.util.Objects.nonNull;

@Slf4j
@RequiredArgsConstructor
public class RangeTaxRateValidator implements TaxRateValidator {

   private static final String ERROR_MESSAGE_TEMPLATE = "Requested country has invalid tax rate: '%s', %s valid value is '%s'";

   private final BigDecimal minTaxRate;
   private final BigDecimal maxTaxRate;

   @Override
   public void validate(BigDecimal taxRate) {
      if (nonNull(minTaxRate) && minTaxRate.compareTo(taxRate) > 0) {
         throw new InvalidTaxRateException(String.format(ERROR_MESSAGE_TEMPLATE, taxRate, "minimum", minTaxRate));
      }
      if (nonNull(maxTaxRate) && taxRate.compareTo(maxTaxRate) > 0) {
         throw new InvalidTaxRateException(String.format(ERROR_MESSAGE_TEMPLATE, taxRate, "maximum", maxTaxRate));
      }
   }
}
