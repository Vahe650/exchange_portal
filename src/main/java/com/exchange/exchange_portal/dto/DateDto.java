package com.exchange.exchange_portal.dto;

import com.exchange.exchange_portal.entity.RateEntity;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class DateDto {


    private Long id;

    private LocalDate requestDate;


    private Set<RateEntity> rates;


}

