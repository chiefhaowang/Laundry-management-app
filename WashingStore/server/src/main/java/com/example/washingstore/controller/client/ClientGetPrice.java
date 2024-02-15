package com.example.washingstore.controller.client;

import com.alibaba.fastjson.JSON;
import com.example.washingstore.bean.PriceClothe;
import com.example.washingstore.bean.PriceStuff;
import com.example.washingstore.dao.PriceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientGetPrice {
    @Autowired
    PriceDao priceDao;
    @RequestMapping("/clientclotheprice")
    public String getClothePriceList(){

        List<PriceClothe> clotheList=priceDao.getPriceClothe();
        String res= JSON.toJSONString(clotheList);
        return res;
    }

    @RequestMapping("/clientstuffprice")
    public String getStuffPriceList(){
        List<PriceStuff> stuffList=priceDao.getPriceStuff();
        String res=JSON.toJSONString(stuffList);
        return res;
    }
}
