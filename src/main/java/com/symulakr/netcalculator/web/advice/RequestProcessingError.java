package com.symulakr.netcalculator.web.advice;

import lombok.Value;

@Value
public class RequestProcessingError {

   int errorCode;
   String description;

   public static RequestProcessingError error(ErrorType errorType, String description) {
      return new RequestProcessingError(errorType.getErrorCode(), description);
   }

}
