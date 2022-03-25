package com.stockTrading.stockTradingSystem.controller;

import com.stockTrading.stockTradingSystem.model.MarketSchedule;
import com.stockTrading.stockTradingSystem.model.Response;
import com.stockTrading.stockTradingSystem.service.MarketScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("schedule")
public class MarketScheduleController {



    private MarketScheduleService marketScheduleService ;

    public MarketScheduleController(MarketScheduleService marketScheduleService){
        super();
        this.marketScheduleService = marketScheduleService;
    }
    @PostMapping(path = "/add")
    public Response addNewSchedule(@RequestBody MarketSchedule marketSchedule){
        return marketScheduleService.addSchedule(marketSchedule);
    }

    @PutMapping("/set")
    public Response setAddedSchedule(@RequestBody MarketSchedule marketSchedule){
        return marketScheduleService.setSchedule(marketSchedule);
    }

    @GetMapping(path = "/getSchedule", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MarketSchedule> getAllSchedule(){
        return  marketScheduleService.getAllSchedules();
    }
}
