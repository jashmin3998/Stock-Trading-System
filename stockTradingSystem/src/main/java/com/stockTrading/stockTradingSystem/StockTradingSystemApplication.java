package com.stockTrading.stockTradingSystem;

import com.stockTrading.stockTradingSystem.service.StockPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
public class StockTradingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockTradingSystemApplication.class, args);
	}

	@Autowired
	private StockPriceService stockPriceService;

	@Scheduled(fixedDelay = 7000l)
	void scheduledJob(){
		System.out.println("Spring Schedulings");
		System.out.println(stockPriceService.updateAllStocks());
		System.out.println(stockPriceService.updateTodayHigh());
		System.out.println(stockPriceService.updateTodayLow());

	}
}


@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "scheduling.enabled", matchIfMissing = true)
class SpringSchedulingConfig{

}
