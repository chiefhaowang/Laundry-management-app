package com.example.washingstore.controller.admin;

import com.alibaba.fastjson.JSON;
import com.example.washingstore.bean.PriceClothe;
import com.example.washingstore.bean.PriceStuff;
import com.example.washingstore.bean.QueryInfo;
import com.example.washingstore.dao.PriceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class AdminPriceSet {
    @Autowired
    PriceDao priceDao;

    //衣物controller
    @RequestMapping("/setclotheprice")
    public String getClothePriceList(QueryInfo queryInfo){
        int priceCounts = priceDao.getClothePriceCounts("%"+queryInfo.getQuery()+"%");//模糊查询
        int pageStart = (queryInfo.getPageNum()-1)*queryInfo.getPageSize();
        List<PriceClothe> clothes = priceDao.getAllClothePrice("%"+queryInfo.getQuery()+"%",pageStart,queryInfo.getPageSize());

        HashMap<String, Object> res = new HashMap<>();

        res.put("number",priceCounts);
        res.put("data",clothes);

        String res_string = JSON.toJSONString(res);
        return res_string;
    }

    @RequestMapping("/addclotheprice")
    public String addClothePrice(@RequestBody PriceClothe priceClothe){
        int i=priceDao.addClothePrice(priceClothe);
        return i>0?"success":"error";
    }

    @RequestMapping("/deleteclotheprice")
    public String deleteClothePrice(String name){
        int i=priceDao.deleteClothePrice(name);
        return i>0?"success":"error";
    }

    @RequestMapping("getupdateclotheprice")
    public String getUpdateClothePrice(String name){
        PriceClothe priceClothe=priceDao.getUpdateClothePrice(name);
        System.out.println(name);
        String str = JSON.toJSONString(priceClothe);
        return str;
    }

    @RequestMapping("editclotheprice")
    public String editClothePrice(@RequestBody PriceClothe priceClothe){
        int i=priceDao.editClothePrice(priceClothe);
        return i>0?"success":"error";
    }
    //物品controller
    @RequestMapping("/setstuffprice")
    public String getStuffPriceList(QueryInfo queryInfo){
        int priceCounts = priceDao.getStuffPriceCounts("%"+queryInfo.getQuery()+"%");//模糊查询
        int pageStart = (queryInfo.getPageNum()-1)*queryInfo.getPageSize();
        List<PriceStuff> stuffs = priceDao.getAllStuffPrice("%"+queryInfo.getQuery()+"%",pageStart,queryInfo.getPageSize());

        HashMap<String, Object> res = new HashMap<>();

        res.put("number",priceCounts);
        res.put("data",stuffs);

        String res_string = JSON.toJSONString(res);
        return res_string;
    }

    @RequestMapping("/addstuffprice")
    public String addStuffPrice(@RequestBody PriceStuff priceStuff){
        int i=priceDao.addStuffPrice(priceStuff);
        return i>0?"success":"error";
    }

    @RequestMapping("/deletestuffprice")
    public String deleteStuffPrice(String name){
        int i=priceDao.deleteStuffPrice(name);
        return i>0?"success":"error";
    }

    @RequestMapping("getupdatestuffprice")
    public String getUpdateStuffPrice(String name){
        PriceStuff priceStuff=priceDao.getUpdateStuffPrice(name);
        System.out.println(name);
        String str = JSON.toJSONString(priceStuff);
        return str;
    }

    @RequestMapping("editstuffprice")
    public String editStuffPrice(@RequestBody PriceStuff priceStuff){
        int i=priceDao.editStuffPrice(priceStuff);
        return i>0?"success":"error";
    }
}
