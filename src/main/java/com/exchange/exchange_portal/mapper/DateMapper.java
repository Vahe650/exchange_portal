package com.exchange.exchange_portal.mapper;

import com.exchange.exchange_portal.dto.DateDto;
import com.exchange.exchange_portal.entity.DateEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DateMapper {

    DateDto map(DateEntity entity);

    DateEntity map(DateDto dto);

}