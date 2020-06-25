package com.symulakr.netcalculator.taxrateprovider;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class InMemoryTaxRateDictionaryTest {
   private static final String DE_ISO = "DE";
   private static final String FR_ISO = "FR";

   private static final BigDecimal DE_TAX_RATE = BigDecimal.valueOf(0.19);
   private static final BigDecimal FR_TAX_RATE = BigDecimal.valueOf(0.20);
   private static final Map<String, BigDecimal> TAX_RATES = Map.of(DE_ISO, DE_TAX_RATE, FR_ISO, FR_TAX_RATE);

   private InMemoryTaxRateDictionary taxRateDictionary;
   private TaxRateProvider taxRateProvider;

   @BeforeEach
   void setUp() {
      taxRateProvider = mock(TaxRateProvider.class);
      when(taxRateProvider.taxRates())
            .thenReturn(TAX_RATES);
      taxRateDictionary = new InMemoryTaxRateDictionary(taxRateProvider);
   }

   @Test
   void shouldReturnTaxRateForKnownCountryIso() {
      assertThat(taxRateDictionary.taxRateFor(DE_ISO))
            .isEqualTo(Optional.of(DE_TAX_RATE));
      assertThat(taxRateDictionary.taxRateFor(FR_ISO))
            .isEqualTo(Optional.of(FR_TAX_RATE));
   }

   @Test
   void shouldReturnEmptyTaxRateForUnknownCountry() {
      assertThat(taxRateDictionary.taxRateFor("UNKNOWN"))
            .isEmpty();
   }
}