package com.exchange.exchange_portal.repository;

import com.exchange.exchange_portal.entity.DateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface DateRepository extends JpaRepository<DateEntity, Long> {


    Optional<DateEntity> findByRequestDate(LocalDate date);
}
