package com.symulakr.netcalculator.exception;

public class UnknownCountryException extends ServiceException {

   private static final String ERROR_MESSAGE_TEMPLATE = "'%s' is unknown country";

   public UnknownCountryException(String countryIso) {
      super(String.format(ERROR_MESSAGE_TEMPLATE, countryIso));
   }

}