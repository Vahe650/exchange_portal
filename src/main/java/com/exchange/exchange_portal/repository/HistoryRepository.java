package com.exchange.exchange_portal.repository;

import com.exchange.exchange_portal.entity.HistoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<HistoryEntity, Long> {


    Page<HistoryEntity> findByUser_Id(Long userId, Pageable pageable);
}
