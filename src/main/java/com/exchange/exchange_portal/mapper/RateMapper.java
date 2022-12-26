package com.exchange.exchange_portal.mapper;

import com.exchange.exchange_portal.dto.RateDto;
import com.exchange.exchange_portal.entity.RateEntity;
import com.exchange.exchange_portal.parser.CubeRate;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RateMapper {
    RateDto map(RateEntity entity);

    RateEntity map(RateDto dto);

    List<RateEntity> map(List<CubeRate> cube);
}