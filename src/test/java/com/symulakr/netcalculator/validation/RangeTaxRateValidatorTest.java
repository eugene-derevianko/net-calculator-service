package com.symulakr.netcalculator.validation;

import com.symulakr.netcalculator.exception.InvalidTaxRateException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RangeTaxRateValidatorTest {

   private TaxRateValidator validator;

   @ParameterizedTest
   @ValueSource(doubles = {0.0, 1, Double.MAX_VALUE, Double.MIN_VALUE, -Double.MAX_VALUE, -1.1})
   void shouldPassTheValidationWhenRageIsDefined(double taxRate) {
      validator = new RangeTaxRateValidator(BigDecimal.valueOf(Double.MAX_VALUE).negate(), BigDecimal.valueOf(Double.MAX_VALUE));
      validator.validate(BigDecimal.valueOf(taxRate));
   }

   @ParameterizedTest
   @ValueSource(doubles = {0.0, 1, Double.MAX_VALUE, Double.MIN_VALUE, -Double.MAX_VALUE, -1.1})
   void shouldPassTheValidationWhenRageNotDefined(double taxRate) {
      validator = new RangeTaxRateValidator(null, null);
      validator.validate(BigDecimal.valueOf(taxRate));
   }

   @Test
   void shouldThrowExceptionWheTaxRateLessThenMinimum() {
      var minimum = BigDecimal.ZERO;
      var lessThenMinimum = minimum.subtract(BigDecimal.ONE);
      validator = new RangeTaxRateValidator(minimum, null);
      assertThatThrownBy(() -> validator.validate(lessThenMinimum))
            .isInstanceOf(InvalidTaxRateException.class)
            .hasMessageContaining("minimum");
   }

   @Test
   void shouldThrowExceptionWheTaxRateMoreThenMaximum() {
      var maximum = BigDecimal.ONE;
      var moreThenMaximum = maximum.add(BigDecimal.ONE);
      validator = new RangeTaxRateValidator(null, maximum);
      assertThatThrownBy(() -> validator.validate(moreThenMaximum))
            .isInstanceOf(InvalidTaxRateException.class)
            .hasMessageContaining("maximum");
   }
}