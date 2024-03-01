package com.example.washingstore.util;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import org.springframework.beans.factory.annotation.Value;

public class AliPayUtil {


    private String appid;
    private String url;
    private String privateKey;
    private String publicKey;
    private String notifyUrl;
    private String returnUrl;

    public String pay(String id, String price, String title){
        AlipayClient alipayClient = new DefaultAlipayClient(url,appid,privateKey,"json","GBK",publicKey,"RSA2");
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setNotifyUrl(notifyUrl);
        request.setReturnUrl(returnUrl);
        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", id);
        bizContent.put("total_amount", price);
        bizContent.put("subject", title);
        bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");

        request.setBizContent(bizContent.toString());
        AlipayTradePagePayResponse response = null;

        String form = null;
        try {
            response = alipayClient.pageExecute(request);
            form=response.getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if(response.isSuccess()){
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
        return form;
    }

}
