package com.example.Stock.repository;

import com.example.Stock.models.Stock;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface IStockRepository extends CrudRepository<Stock,Integer>{

    //custom finder
    public List<Stock> findByStockType(String stockType);

    public List<Stock> findByStockPriceGreaterThanAndDateStockBirthTimeStampLessThan(Double price, LocalDateTime date);

    @Query(value = "select * from STOCK where STOCK_MARKET_CAP > :capPercentage" , nativeQuery = true)
    List<Stock> getAllStocksAboveMarketCap(Double capPercentage);

    @Modifying
    @Query(value = "update STOCK set STOCK_MARKET_CAP = :capPercentage where Stock_id = :id" , nativeQuery = true)
    void updateMarketCapById(Double marketCap, Integer id);

    @Modifying
    @Query(value = "update stock set STOCK_TYPE = :myType where Stock_id = :id", nativeQuery = true)
    void modifyStockTypeById(String enumValue, Integer id);

    @Modifying
    @Query(value = "Delete from Stock where Stock_owner_count <= :clientCount" , nativeQuery = true)
    void deleteStocksBasedOnCount(Integer count);

    @Modifying
    @Transactional
    @Query(value = "update stock set stock_name =:stockName , stock_price = stockPrice , stock_birth_time = stockBirthTimestamp where id =:stockId")
    void updateStockById(Integer stockId, String stockName, Double stockPrice, LocalDateTime stockBirthTimestamp);

}
