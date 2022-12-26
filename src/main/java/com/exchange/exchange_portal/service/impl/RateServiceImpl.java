package com.exchange.exchange_portal.service.impl;

import com.exchange.exchange_portal.entity.RateEntity;
import com.exchange.exchange_portal.exceptions.NotFoundProblem;
import com.exchange.exchange_portal.feign.CEBClient;
import com.exchange.exchange_portal.mapper.RateMapper;
import com.exchange.exchange_portal.parser.CubeRate;
import com.exchange.exchange_portal.parser.Envelope;
import com.exchange.exchange_portal.repository.RateRepository;
import com.exchange.exchange_portal.service.DateService;
import com.exchange.exchange_portal.service.RateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RateServiceImpl implements RateService {
    private final RateRepository rateRepository;
    private final DateService dateService;
    private final CEBClient cebClient;
    private final RateMapper rateMapper;

    public List<RateEntity> saveCurrentRates() {
        try {
            String xml = cebClient.getSbpBankIcons();
            StringReader sr = new StringReader(xml);
            JAXBContext jaxbContext = JAXBContext.newInstance(Envelope.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Envelope response = (Envelope) unmarshaller.unmarshal(sr);
            List<CubeRate> cubes = response.getCube().getCubeTime().getCubes();
            List<RateEntity> rateEntities = rateMapper.map(cubes);
            return rateRepository.saveAll(rateEntities);
        } catch (JAXBException e) {
            log.error("Unable get and parse rates for: {}", LocalDate.now());
        }
        return Collections.emptyList();
    }

    @Override
    public Map<String, BigDecimal> findByCurrentCurrencies(
            String fromCurrency,
            String toCurrency,
            LocalDate requestDate
    ) {
        List<RateEntity> rateEntities = rateRepository.findByCurrentCurrency(fromCurrency, toCurrency, requestDate);
        if (rateEntities.size() != 2) {
            rateEntities = saveCurrentRates();
            if (!rateEntities.isEmpty()) {
                dateService.save(rateEntities);
            }

        }
        Map<String, BigDecimal> rateByCurrency = rateEntities.stream()
                .filter(rate ->
                        rate.getCurrency().equals(fromCurrency) || rate.getCurrency().equals(toCurrency))
                .collect(Collectors.toMap(RateEntity::getCurrency, RateEntity::getRate));
        if (rateByCurrency.size() == 2) {
            log.info("Current rates are available for currencies: {}, : {}", fromCurrency, toCurrency);
            return rateByCurrency;
        } else {
            throw NotFoundProblem.with(String.format("Current rate are NOT available for currencies: %s, : %s", fromCurrency, toCurrency));
        }

    }
}
