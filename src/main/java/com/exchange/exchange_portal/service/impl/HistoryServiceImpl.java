package com.exchange.exchange_portal.service.impl;

import com.exchange.exchange_portal.dto.ConverterRequestDto;
import com.exchange.exchange_portal.entity.HistoryEntity;
import com.exchange.exchange_portal.entity.UserEntity;
import com.exchange.exchange_portal.mapper.HistoryMapper;
import com.exchange.exchange_portal.repository.HistoryRepository;
import com.exchange.exchange_portal.service.HistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class HistoryServiceImpl implements HistoryService {
    private final HistoryRepository historyRepository;
    private final HistoryMapper historyMapper;

    @Override
    public void save(ConverterRequestDto dto, UserEntity user) {
        HistoryEntity entity = historyMapper.map(dto);
        entity.setUser(user);
        historyRepository.save(entity);
    }

    @Override
    public Page<ConverterRequestDto> findAll(Long userId, Pageable pageable) {
        Page<HistoryEntity> historyEntities = historyRepository.findByUser_Id(userId, pageable);
        List<ConverterRequestDto> converterRequestDtos = historyEntities.map(historyMapper::map).toList();
        return new PageImpl<>(converterRequestDtos, pageable, historyEntities.getTotalElements());
    }
}
