package com.symulakr.netcalculator.service;

import com.symulakr.netcalculator.exception.UnknownCountryException;
import com.symulakr.netcalculator.taxrateprovider.TaxRateDictionary;
import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DefaultNetCalculatorServiceTest {

   private NetCalculatorService netCalculatorService;
   private TaxRateDictionary taxRateDictionary;

   private static Stream<Arguments> argumentsForTest() {
      return Stream.of(
            Arguments.of(BigDecimal.valueOf(100), BigDecimal.valueOf(0.20), BigDecimal.valueOf(80)),
            Arguments.of(BigDecimal.valueOf(100.99), BigDecimal.valueOf(0.10), BigDecimal.valueOf(90.89)),
            Arguments.of(BigDecimal.valueOf(100.01), BigDecimal.valueOf(0.20), BigDecimal.valueOf(80.01)),
            Arguments.of(BigDecimal.valueOf(100), BigDecimal.valueOf(1.20), BigDecimal.valueOf(-20)),
            Arguments.of(BigDecimal.valueOf(100), BigDecimal.valueOf(-.20), BigDecimal.valueOf(120)),
            Arguments.of(BigDecimal.valueOf(-100), BigDecimal.valueOf(0.20), BigDecimal.valueOf(-80)),
            Arguments.of(BigDecimal.valueOf(0), BigDecimal.valueOf(0.20), BigDecimal.valueOf(0)),
            Arguments.of(BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(0)),
            Arguments.of(BigDecimal.valueOf(10), BigDecimal.valueOf(0), BigDecimal.valueOf(10)),
            Arguments.of(BigDecimal.valueOf(100), BigDecimal.valueOf(0.19), BigDecimal.valueOf(81))
      );
   }

   @BeforeEach
   void setUp() {
      taxRateDictionary = mock(TaxRateDictionary.class);
      Integer scalePrecision = 2;
      netCalculatorService = new DefaultNetCalculatorService(taxRateDictionary, scalePrecision);
   }

   @ParameterizedTest
   @MethodSource("argumentsForTest")
   void calculateNetPrice(BigDecimal grossPrice, BigDecimal tax, BigDecimal expectedNetPrice) {
      var countryIso = "DE";
      when(taxRateDictionary.taxRateFor(anyString()))
            .thenReturn(Optional.of(tax));
      assertThat(netCalculatorService.calculateNetPrice(grossPrice, countryIso))
            .isCloseTo(expectedNetPrice, Percentage.withPercentage(0.001));
   }

   @Test
   void shouldThrowExceptionForUnknownCountryIso() {
      var countryIso = "UNKNOWN";
      when(taxRateDictionary.taxRateFor(countryIso))
            .thenReturn(Optional.empty());
      assertThatThrownBy(() -> netCalculatorService.calculateNetPrice(any(), countryIso))
            .isInstanceOf(UnknownCountryException.class)
            .hasMessageContaining("is unknown country");
   }
}