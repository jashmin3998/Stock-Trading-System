package com.stockTrading.stockTradingSystem.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Table(
        name = "stocks",
        uniqueConstraints= @UniqueConstraint(columnNames={"name", "stockSymbol"})
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Stocks {

    @Id
    @SequenceGenerator(
            name = "stocks_sequence",
            sequenceName = "stocks_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "stocks_sequence"
    )
    private long stockId;
    private String name;
    private String stockSymbol;
    private long totalQuantity;
    private long purchasedQuantity;
    private long creationTime;
    //@Column(precision=2, scale = 6)
    private double price;
    @OneToOne(
            mappedBy = "stocks",
           cascade = CascadeType.ALL
    )
    @JsonManagedReference
    private StockPrice stockPrice;




//    public Stocks(String name, String stockSymbol, long totalQuantity, long purchasedQuantity, long creationTime, double price) {
//        this.name = name;
//        this.stockSymbol = stockSymbol;
//        this.totalQuantity = totalQuantity;
//        this.purchasedQuantity = purchasedQuantity;
//        this.creationTime = creationTime;
//        this.price = price;
//    }








}
