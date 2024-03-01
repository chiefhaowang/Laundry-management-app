package com.example.washingstore.controller;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.example.washingstore.bean.AliPayContent;
import com.example.washingstore.util.AliPayUtil;
import com.example.washingstore.util.AlipayConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

@RestController
public class AliPayController {

    @RequestMapping("alipay")
    public String pay(@RequestBody AliPayContent aliPayContent){
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key,
                "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setNotifyUrl(AlipayConfig.notify_url);
        request.setReturnUrl(AlipayConfig.return_url);
        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", aliPayContent.getId());
        bizContent.put("total_amount", aliPayContent.getPrice());
        bizContent.put("subject", aliPayContent.getTitle());
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
            System.out.println("支付宝页面调用成功");
        } else {
            System.out.println("支付宝页面调用失败");
        }
        return form;
    }
}
