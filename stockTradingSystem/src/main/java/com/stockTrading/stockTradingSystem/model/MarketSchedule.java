package com.stockTrading.stockTradingSystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name="market_schedule",
        uniqueConstraints= @UniqueConstraint(columnNames={"dates"})
)
public class MarketSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private LocalDate dates;
    private long startTime;
    private long endTime;
    private int isHoliday;

}
