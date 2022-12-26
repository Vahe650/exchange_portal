package com.exchange.exchange_portal.repository;

import com.exchange.exchange_portal.entity.RateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RateRepository extends JpaRepository<RateEntity, Long> {

    @Query("SELECT r from RateEntity r join r.dates d where " +
            "(r.currency = :fromCurrency or r.currency = :toCurrency) and d.requestDate=:requestDate")
    List<RateEntity> findByCurrentCurrency(@Param("fromCurrency") String fromCurrency,
                                           @Param("toCurrency") String toCurrency,
                                           @Param("requestDate") LocalDate requestDate);
}
