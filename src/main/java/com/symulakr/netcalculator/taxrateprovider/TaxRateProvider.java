package com.symulakr.netcalculator.taxrateprovider;

import java.math.BigDecimal;
import java.util.Map;

public interface TaxRateProvider {

   Map<String, BigDecimal> taxRates();

}
