package com.symulakr.netcalculator.validation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidationProperties {

   private BigDecimal minTaxRate;
   private BigDecimal maxTaxRate;

}
