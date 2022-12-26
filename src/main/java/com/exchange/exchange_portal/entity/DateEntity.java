package com.exchange.exchange_portal.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "dates")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate requestDate;

    @ManyToMany
    @JoinTable(
            name = "rate_dates",
            joinColumns = @JoinColumn(name = "date_id"),
            inverseJoinColumns = @JoinColumn(name = "rate_id"))
    private List<RateEntity> rates;

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "requestDate = " + requestDate + ")";
    }
}

