package com.example.Stock.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer stockId;


    @Column(unique = true , name = "stock_name")
    private String stockName;

    @Column(name = "stock_price")
    private Double stockPrice;

    @Column(name = "stock_owner_count")
    private Integer stockOwnerCount;

    @Column(name = "stock_type")
    @Enumerated(EnumType.STRING)//only for watching strings in DB.
    private String stockType;

    @Column(name = "stock_market_cap")
    private Double stockMarketCap;

    @Column(name = "stock_birth_time")
    private LocalDateTime stockBirthTimestamp;


}
