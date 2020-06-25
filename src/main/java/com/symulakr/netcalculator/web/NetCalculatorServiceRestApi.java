package com.symulakr.netcalculator.web;

import com.symulakr.netcalculator.service.NetCalculatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/netPrice")
@RequiredArgsConstructor
public class NetCalculatorServiceRestApi {

   private final NetCalculatorService netCalculatorService;

   @GetMapping
   public BigDecimal calculateNetPrice(@RequestParam("grossPrice") BigDecimal grossPrice,
                                       @RequestParam("countryIso") String countryIso) {
      return netCalculatorService.calculateNetPrice(grossPrice, countryIso);
   }

}
