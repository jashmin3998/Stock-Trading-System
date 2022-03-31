package com.stockTrading.stockTradingSystem.service;

import com.stockTrading.stockTradingSystem.model.MarketSchedule;
import com.stockTrading.stockTradingSystem.model.Response;
import com.stockTrading.stockTradingSystem.repository.MarketScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class MarketScheduleServiceImpl implements MarketScheduleService{

    @Autowired
    private MarketScheduleRepository marketScheduleRepository;

    @Override
    @Transactional
    public Response setSchedule(MarketSchedule schedule) {
        marketScheduleRepository.updateMarketSchedule(schedule.getDates(),
                                                            schedule.getStartTime(),
                                                            schedule.getEndTime(),
                                                            schedule.getIsHoliday());
        return new Response(true,"Schedule updated successful");
    }

    @Override
    public Response addSchedule(MarketSchedule schedule) {
        marketScheduleRepository.save(schedule);
        return new Response(true,"Schedule added successfully");
    }

    @Override
    public List<MarketSchedule> getAllSchedules() {
        return marketScheduleRepository.findAll();
    }

    @Override
    public MarketSchedule getMarketScheduleByDates(LocalDate date) {
        return marketScheduleRepository.getMarketScheduleByDates(date);
    }


}
