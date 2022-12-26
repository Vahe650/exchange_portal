package com.exchange.exchange_portal.mapper;

import com.exchange.exchange_portal.dto.ConverterRequestDto;
import com.exchange.exchange_portal.entity.HistoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HistoryMapper {

    ConverterRequestDto map(HistoryEntity entity);

    HistoryEntity map(ConverterRequestDto dto);

}