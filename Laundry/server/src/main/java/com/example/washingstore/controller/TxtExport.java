package com.example.washingstore.controller;
import com.example.washingstore.bean.Order;
import com.example.washingstore.bean.ProfitMonth;
import com.example.washingstore.bean.User;
import com.example.washingstore.dao.OrderDao;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.WorkbookUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RestController
public class TxtExport {
    @Autowired
    OrderDao orderDao;

    @RequestMapping("/txtprint")
    public void Txtprinter() {
        int average=0;
        int highPro=0;
        int lowPro=0;
        int total;
        int wholePrice=0;
        List<Order> orders=orderDao.getPriceList();
        List<ProfitMonth> profitMonths=new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);

        for(int i=1;i<=31;i++){
            total=0;
            for(int j=0;j<orders.size();j++){
                if(orders.get(j).getYear()==year && orders.get(j).getMonth()==month && orders.get(j).getDay()==i){
                    total+=orders.get(j).getOrderpay();
                }
            }
            profitMonths.add(new ProfitMonth(i,total));
            System.out.println(total);
            if(total>highPro){
                highPro=total;
            }
            if(total<lowPro){
                lowPro=total;
            }
            wholePrice+=total;
        }
        average=(int)(wholePrice/31);
        //月度流水表

        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet userTable = wb.createSheet("月度日流水");

        HSSFRow row0 = userTable.createRow(0);
        HSSFCell cell0 = row0.createCell(0);
        // 设置内容
        cell0.setCellValue("店铺本月流水一览");
        // 合并单元格 起始行 截至列 起始列 截至行
        userTable.addMergedRegion(new CellRangeAddress(0,0,0,4));

        //设置单元列名
        HSSFRow row1 = userTable.createRow(1);
        row1.createCell(0).setCellValue("日期");
        row1.createCell(1).setCellValue("流水总额（元）");

        //  循环内容
        for (int i = 0; i < profitMonths.size(); i++) {
            ProfitMonth profitMonth=profitMonths.get(i);
            HSSFRow row = userTable.createRow(i + 2);
            row.createCell(0).setCellValue(profitMonth.getDate());
            row.createCell(1).setCellValue(profitMonth.getProfit());
        }
        try {
            // 文件名称
            String fileName = "店铺月度流水" + ".xls";
            FileOutputStream fileOutputStream = new FileOutputStream("D:\\" + fileName);
            wb.write(fileOutputStream);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

