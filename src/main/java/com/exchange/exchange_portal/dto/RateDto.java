package com.exchange.exchange_portal.dto;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class RateDto {

    private Long id;

    private BigDecimal rate;

    private String currency;

}

