package com.stockTrading.stockTradingSystem;

import com.stockTrading.stockTradingSystem.model.LimitOrderTransaction;
import com.stockTrading.stockTradingSystem.model.MarketSchedule;
import com.stockTrading.stockTradingSystem.model.Response;
import com.stockTrading.stockTradingSystem.model.TransactionDtl;
import com.stockTrading.stockTradingSystem.service.LimitOrderTransactionService;
import com.stockTrading.stockTradingSystem.service.MarketScheduleService;
import com.stockTrading.stockTradingSystem.service.StockPriceService;
import com.stockTrading.stockTradingSystem.service.TransactionDtlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class StockTradingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockTradingSystemApplication.class, args);
	}
	public static boolean flag = false;

	@Autowired
	private StockPriceService stockPriceService;

	@Autowired
	private LimitOrderTransactionService limitOrderTransactionService;

	@Autowired
	private MarketScheduleService marketScheduleService;

	@Autowired
	private TransactionDtlService transactionDtlService;

	@Transactional
	@Scheduled(fixedDelay = 7000l)
	void scheduledJob(){

		try {

			MarketSchedule ms = marketScheduleService.getMarketScheduleByDates(LocalDate.now());

			if(ms.getEndTime() < System.currentTimeMillis() || ms.getStartTime() > System.currentTimeMillis()){
				return;
			}

			System.out.println("Spring Schedulings");
//			System.out.println(stockPriceService.updateAllStocks());
//			System.out.println(stockPriceService.updateTodayHigh());
//			System.out.println(stockPriceService.updateTodayLow());

			stockPriceService.updateAllStocks();
			stockPriceService.updateTodayHigh();
			stockPriceService.updateTodayLow();

			if(flag == false){
				stockPriceService.updateOpenPrice();
				flag = true;
			}

			List<LimitOrderTransaction> orders = limitOrderTransactionService.getAllLimitOrders();
			for(LimitOrderTransaction order : orders){

				//boolean flag = false;
				//sell and buy
				if((order.getTransactionType() == 1 && order.getStock().getStockPrice().getPrice() >= order.getRate()) ||
						(order.getTransactionType() == 0 && order.getStock().getStockPrice().getPrice() <= order.getRate())){
					order.getStock().setStockPrice(null);
					TransactionDtl transaction = new TransactionDtl(order.getQuantity(),
							order.getRate(),
							0,
							System.currentTimeMillis(),
							order.getTransactionType(),
							order.getUser(),
							order.getStock());
					//System.out.println(order.getOrderId() + "executed");
					transactionDtlService.executeLimitOrders(transaction);
					limitOrderTransactionService.removeLimitOrder(order.getOrderId());
				}

			}
		}
		catch (Exception e){
			e.printStackTrace();
			System.out.println("Script Fail");
		}




	}
}


@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "scheduling.enabled", matchIfMissing = true)
class SpringSchedulingConfig{

}
