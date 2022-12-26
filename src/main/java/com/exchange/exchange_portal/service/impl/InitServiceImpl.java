package com.exchange.exchange_portal.service.impl;

import com.exchange.exchange_portal.entity.DateEntity;
import com.exchange.exchange_portal.entity.RateEntity;
import com.exchange.exchange_portal.service.DateService;
import com.exchange.exchange_portal.service.InitService;
import com.exchange.exchange_portal.service.RateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InitServiceImpl implements InitService {
    private final DateService dateService;
    private final RateService rateService;


    @PostConstruct
    public void init() {
        LocalDate currentDate = LocalDate.now();
        DateEntity byRequestDate = dateService.findByRequestDate(currentDate);
        if (byRequestDate == null) {
            List<RateEntity> rateEntities = rateService.saveCurrentRates();
            if (!rateEntities.isEmpty()) {
                dateService.save(rateEntities);
            }
        }

    }


}
