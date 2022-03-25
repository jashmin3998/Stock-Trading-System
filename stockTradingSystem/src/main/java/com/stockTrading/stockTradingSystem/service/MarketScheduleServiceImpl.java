package com.stockTrading.stockTradingSystem.service;

import com.stockTrading.stockTradingSystem.model.MarketSchedule;
import com.stockTrading.stockTradingSystem.model.Response;
import com.stockTrading.stockTradingSystem.repository.MarketScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class MarketScheduleServiceImpl implements MarketScheduleService{

    @Autowired
    private MarketScheduleRepository marketScheduleRepository;

    @Override
    @Transactional
    public Response setSchedule(MarketSchedule schedule) {

        Response res;
        try{
            marketScheduleRepository.updateMarketSchedule(schedule.getTodayDate(),
                                                            schedule.getStartTime(),
                                                            schedule.getStartTime(),
                                                            schedule.getIsHoliday());
            res = new Response(true,"");
        }
        catch (Exception e){
            System.out.println("MarketScheduleServiceImpl: Updation Failed");
            e.printStackTrace();
            res = new Response(false,"Updation Failed");
            return res;
        }

        return res;
    }

    @Override
    public Response addSchedule(MarketSchedule schedule) {
        Response res;
        try{
            marketScheduleRepository.save(schedule);
            res = new Response(true,"");
        }
        catch (Exception e){
            System.out.println("MarketScheduleServiceImpl: Insertion Failed");
            res = new Response(false,"Insertion Failed");
            return res;
        }

        return res;
    }

    @Override
    public List<MarketSchedule> getAllSchedules() {
        return marketScheduleRepository.findAll();
    }


}
