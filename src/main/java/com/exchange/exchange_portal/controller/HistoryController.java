package com.exchange.exchange_portal.controller;

import com.exchange.exchange_portal.dto.ConverterRequestDto;
import com.exchange.exchange_portal.security.CurrentUser;
import com.exchange.exchange_portal.service.HistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/history")
public class HistoryController {
    private final HistoryService historyService;

    @PostMapping
    public void save(@RequestBody ConverterRequestDto converterResponseDto,
                     @AuthenticationPrincipal CurrentUser currentUser
    ) {
        historyService.save(converterResponseDto, currentUser.getUser());

    }

    @PostMapping("/page")
    public Page<ConverterRequestDto> convert(
            @PageableDefault Pageable pageable,
            @AuthenticationPrincipal CurrentUser currentUser
    ) {
        return historyService.findAll(currentUser.getUser().getId(), pageable);

    }

}
