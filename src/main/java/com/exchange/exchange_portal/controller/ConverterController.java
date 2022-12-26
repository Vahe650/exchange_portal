package com.exchange.exchange_portal.controller;

import com.exchange.exchange_portal.dto.ConverterRequestDto;
import com.exchange.exchange_portal.service.RateService;
import com.exchange.exchange_portal.util.ConverterUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/convert")
public class ConverterController {
    private final RateService rateService;

    @PostMapping
    public ResponseEntity<BigDecimal> convert(@RequestBody ConverterRequestDto converterResponseDto) {
        String fromCurrency = converterResponseDto.getFromCurrency();
        String toCurrency = converterResponseDto.getToCurrency();
        BigDecimal value = converterResponseDto.getValue();
        Map<String, BigDecimal> byCurrentCurrencies = rateService.findByCurrentCurrencies(
                fromCurrency,
                toCurrency,
                LocalDate.now());
        if (!byCurrentCurrencies.isEmpty()) {
            BigDecimal from = byCurrentCurrencies.get(fromCurrency);
            BigDecimal to = byCurrentCurrencies.get(toCurrency);
            return ResponseEntity.ok(ConverterUtil.convert(value, from, to));
        }
        return ResponseEntity.internalServerError().build();

    }

}
