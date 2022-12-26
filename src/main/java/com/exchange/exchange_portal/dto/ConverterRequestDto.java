package com.exchange.exchange_portal.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ConverterRequestDto {
    private String fromCurrency;
    private String toCurrency;
    private BigDecimal value;
}
