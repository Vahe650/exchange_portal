package com.exchange.exchange_portal.service.impl;

import com.exchange.exchange_portal.dto.DateDto;
import com.exchange.exchange_portal.entity.DateEntity;
import com.exchange.exchange_portal.entity.RateEntity;
import com.exchange.exchange_portal.mapper.DateMapper;
import com.exchange.exchange_portal.repository.DateRepository;
import com.exchange.exchange_portal.service.DateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DateServiceImpl implements DateService {
    private final DateRepository dateRepository;
    private final DateMapper dateMapper;

    @Transactional
    public DateDto save(List<RateEntity> rateEntities) {
        DateEntity dateEntity = new DateEntity();
        dateEntity.setRequestDate(LocalDate.now());
        DateEntity saved = dateRepository.save(dateEntity);
        dateEntity.setRates(rateEntities);
        log.info(" DateEntity saved : {}", saved.getRequestDate());
        return dateMapper.map(saved);
    }

    @Override
    public DateEntity findByRequestDate(LocalDate date) {
        return dateRepository.findByRequestDate(date).orElse(null);
    }
}
