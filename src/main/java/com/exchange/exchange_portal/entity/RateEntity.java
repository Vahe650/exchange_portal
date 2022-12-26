package com.exchange.exchange_portal.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "rates")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal rate;

    private String currency;

    @ManyToMany(mappedBy = "rates")
    private List<DateEntity> dates;

}

