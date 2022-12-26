package com.exchange.exchange_portal.service;

import com.exchange.exchange_portal.dto.DateDto;
import com.exchange.exchange_portal.entity.DateEntity;
import com.exchange.exchange_portal.entity.RateEntity;

import java.time.LocalDate;
import java.util.List;

public interface DateService {

    DateDto save(List<RateEntity> rateEntities);

    DateEntity findByRequestDate(LocalDate date);
}
