package com.symulakr.netcalculator.service;

import java.math.BigDecimal;

public interface NetCalculatorService {

   BigDecimal calculateNetPrice(BigDecimal grossPrice, String countryIso);

}
