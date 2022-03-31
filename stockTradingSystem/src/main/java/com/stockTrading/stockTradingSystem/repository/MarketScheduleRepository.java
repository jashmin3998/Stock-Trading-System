package com.stockTrading.stockTradingSystem.repository;

import com.stockTrading.stockTradingSystem.model.MarketSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;

@Repository
public interface MarketScheduleRepository extends JpaRepository<MarketSchedule, Long> {

    @Modifying
    @Query(
            value = "update market_schedule set start_time=?2, end_time = ?3, is_holiday = ?4 where dates = ?1",
            nativeQuery = true
    )
    int updateMarketSchedule(LocalDate date, long startTime, long endTime, int isHoliday);

    public MarketSchedule getMarketScheduleByDates(LocalDate date);
}
