package com.stockTrading.stockTradingSystem.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(
        name="market_schedule",
        uniqueConstraints= @UniqueConstraint(columnNames={"todayDate"})
)
public class MarketSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;



    private LocalDate todayDate;
    private long startTime;
    private long endTime;
    private int isHoliday;


    public LocalDate getTodayDate() {
        return todayDate;
    }

    public void setTodayDate(LocalDate todayDate) {
        this.todayDate = todayDate;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public int getIsHoliday() {
        return isHoliday;
    }

    public void setIsHoliday(int isHoliday) {
        this.isHoliday = isHoliday;
    }
}
