package com.symulakr.netcalculator.taxrateprovider;

import com.symulakr.netcalculator.validation.TaxRateValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ValidatingTaxRateDictionaryTest {


   private TaxRateDictionary validatingTaxRateDictionary;
   private TaxRateValidator validator;
   private TaxRateDictionary taxRateDictionary;

   @BeforeEach
   void setUp() {
      validator = mock(TaxRateValidator.class);
      taxRateDictionary = mock(TaxRateDictionary.class);
      validatingTaxRateDictionary = new ValidatingTaxRateDictionary(validator, taxRateDictionary);
   }

   @Test
   void shouldValidateTaxRate() {
      var taxRate = BigDecimal.ONE;
      var expectedTaxRate = Optional.of(BigDecimal.ONE);
      when(taxRateDictionary.taxRateFor(anyString()))
            .thenReturn(expectedTaxRate);
      var actualTaxRate = validatingTaxRateDictionary.taxRateFor("anyString");
      assertThat(actualTaxRate).isEqualTo(expectedTaxRate);
      verify(validator).validate(taxRate);
   }

}