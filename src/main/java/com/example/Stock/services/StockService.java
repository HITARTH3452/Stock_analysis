package com.example.Stock.services;

import com.example.Stock.models.Stock;
import com.example.Stock.models.StockType;
import com.example.Stock.repository.IStockRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class StockService {

    @Autowired
    IStockRepository stockRepo;

    public List<Stock> getStockByType(String stockType) {
        return stockRepo.findByStockType(stockType);
    }

    public String addStock(List<Stock> stockList) {
       Iterable<Stock> list =  stockRepo.saveAll(stockList);
       if(list != null){
           return "Added list of Stocks.....";
       }else{
           return "Could not added....";
       }
    }

    public List<Stock> getStocksAbovePriceAndLowerDate(Double price, String date) {
        return stockRepo.findByStockPriceGreaterThanAndDateStockBirthTimeStampLessThan(price, LocalDateTime.parse(date));
    }


    public List<Stock> getAllStocksAboveMarketCap(Double capPercentage) {
        return stockRepo.getAllStocksAboveMarketCap(capPercentage);
    }

    public void updateMarketCap(Double marketCap, Integer id) {
        stockRepo.updateMarketCapById(marketCap,id);
    }

    @Transactional
    public void updateTypeById(StockType stockType, Integer id) {
        String enumValue = stockType.name();
        stockRepo.modifyStockTypeById(enumValue, id);

        throw new IllegalStateException("Hitarth testing transactional ");
    }

    public void updateStockById(Integer id, Stock myStock) {
        stockRepo.updateStockById(id,myStock.getStockName(),myStock.getStockPrice(),myStock.getStockBirthTimestamp());
    }

    @Transactional
    public void deleteStocksBasedOnCount(Integer count) {
        stockRepo.deleteStocksBasedOnCount(count);
    }
}
