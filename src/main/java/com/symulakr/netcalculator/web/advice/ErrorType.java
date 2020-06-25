package com.symulakr.netcalculator.web.advice;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
@RequiredArgsConstructor
public enum ErrorType {

   UNKNOWN_ERROR(1),
   UNKNOWN_COUNTRY(2),
   INVALID_TAX_RATE(3);

   private int errorCode;

}
