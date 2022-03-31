package com.stockTrading.stockTradingSystem.service;

import com.stockTrading.stockTradingSystem.model.MarketSchedule;
import com.stockTrading.stockTradingSystem.model.Response;

import java.time.LocalDate;
import java.util.List;

public interface MarketScheduleService {
    public Response setSchedule(MarketSchedule schedule);
    public Response addSchedule(MarketSchedule schedule);
    List<MarketSchedule> getAllSchedules();
    MarketSchedule getMarketScheduleByDates(LocalDate date);
}
