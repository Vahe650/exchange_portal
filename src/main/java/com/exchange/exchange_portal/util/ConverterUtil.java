package com.exchange.exchange_portal.util;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.math.RoundingMode;

@UtilityClass
public class ConverterUtil {

    public BigDecimal convert(BigDecimal wanted, BigDecimal from, BigDecimal to) {
        return wanted.multiply(from).divide(to, 3, RoundingMode.DOWN);
    }

}
