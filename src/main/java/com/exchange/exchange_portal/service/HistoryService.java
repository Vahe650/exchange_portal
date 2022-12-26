package com.exchange.exchange_portal.service;

import com.exchange.exchange_portal.dto.ConverterRequestDto;
import com.exchange.exchange_portal.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HistoryService {

    void save(ConverterRequestDto dto, UserEntity user);

    Page<ConverterRequestDto> findAll(Long userId, Pageable pageable);
}
