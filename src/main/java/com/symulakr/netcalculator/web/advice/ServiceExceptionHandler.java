package com.symulakr.netcalculator.web.advice;

import com.symulakr.netcalculator.exception.InvalidTaxRateException;
import com.symulakr.netcalculator.exception.UnknownCountryException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ServiceExceptionHandler {

   @ExceptionHandler(UnknownCountryException.class)
   @ResponseStatus(HttpStatus.BAD_REQUEST)
   public RequestProcessingError handleUnknownCountryException(UnknownCountryException ex) {
      return RequestProcessingError.error(ErrorType.UNKNOWN_COUNTRY, ex.getMessage());
   }

   @ExceptionHandler(InvalidTaxRateException.class)
   @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
   public RequestProcessingError handleInvalidTaxRateException(InvalidTaxRateException ex) {
      return RequestProcessingError.error(ErrorType.INVALID_TAX_RATE, ex.getMessage());
   }

   @ExceptionHandler(Exception.class)
   @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
   public RequestProcessingError handleException(Exception ex) {
      return RequestProcessingError.error(ErrorType.UNKNOWN_ERROR, ex.getMessage());
   }

}
