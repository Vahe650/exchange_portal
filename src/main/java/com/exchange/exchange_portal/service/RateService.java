package com.exchange.exchange_portal.service;

import com.exchange.exchange_portal.entity.RateEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface RateService {

    List<RateEntity> saveCurrentRates();

    Map<String, BigDecimal> findByCurrentCurrencies(String fromCurrency, String toCurrency, LocalDate requestDate);
}
