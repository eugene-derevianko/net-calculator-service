package com.symulakr.netcalculator.service;

import com.symulakr.netcalculator.exception.UnknownCountryException;
import com.symulakr.netcalculator.taxrateprovider.TaxRateDictionary;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RequiredArgsConstructor
public class DefaultNetCalculatorService implements NetCalculatorService {

   private final TaxRateDictionary taxRateDictionary;
   private final Integer scalePrecision;

   @Override
   public BigDecimal calculateNetPrice(BigDecimal grossPrice, String countryIso) {
      return taxRateDictionary.taxRateFor(countryIso)
            .map(taxRate -> calculateNetPrice(grossPrice, taxRate))
            .map(taxRate -> taxRate.setScale(scalePrecision, RoundingMode.HALF_UP))
            .orElseThrow(() -> new UnknownCountryException(countryIso));
   }

   private BigDecimal calculateNetPrice(BigDecimal grossPrice, BigDecimal taxRate) {
      return grossPrice.subtract(grossPrice.multiply(taxRate));
   }
}
