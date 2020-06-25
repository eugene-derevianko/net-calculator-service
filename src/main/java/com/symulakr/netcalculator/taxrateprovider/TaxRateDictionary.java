package com.symulakr.netcalculator.taxrateprovider;

import java.math.BigDecimal;
import java.util.Optional;

public interface TaxRateDictionary {

   Optional<BigDecimal> taxRateFor(String countryIso);

}
