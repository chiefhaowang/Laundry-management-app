package com.example.washingstore.dao;

import com.example.washingstore.bean.PriceClothe;
import com.example.washingstore.bean.PriceStuff;
import com.example.washingstore.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceDao {
    //提取衣物价格
    public List<PriceClothe> getPriceClothe();
    public List<PriceStuff> getPriceStuff();
    //设置衣物价格
    public List<PriceClothe> getAllClothePrice(@Param("name")String name, @Param("pageStart") int pageStart,
                                   @Param("pageSize")int pageSize);
    public int getClothePriceCounts(@Param("name") String name);
    public int addClothePrice(PriceClothe priceClothe);
    public int deleteClothePrice(String name);
    public PriceClothe getUpdateClothePrice(String name);
    public int editClothePrice(PriceClothe priceClothe);

    //设置物品价格
    public List<PriceStuff> getAllStuffPrice(@Param("name")String name, @Param("pageStart") int pageStart,
                                               @Param("pageSize")int pageSize);
    public int getStuffPriceCounts(@Param("name") String name);
    public int addStuffPrice(PriceStuff priceStuff);
    public int deleteStuffPrice(String name);
    public PriceStuff getUpdateStuffPrice(String name);
    public int editStuffPrice(PriceStuff priceStuff);
}
