package com.exchange.exchange_portal.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "histories")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class HistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private UserEntity user;
    private String fromCurrency;
    private String toCurrency;
    private BigDecimal value;
}

