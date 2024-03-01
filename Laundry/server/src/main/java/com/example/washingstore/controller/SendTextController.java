package com.example.washingstore.controller;

import com.example.washingstore.util.RandomUtil;
import com.alibaba.fastjson.JSONException;
import com.example.washingstore.bean.Sms;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.HttpRetryException;


//用户注册验证码发送controller
@RestController
@RequestMapping("/sms")
public class SendTextController {
    @RequestMapping(value = "/sendcode", method = RequestMethod.POST)
    public String Sms(@RequestBody Sms sms){
        int appID=1400665467;
        String appKey="e8d9e2f3b7d95bddf4f08a78910d7338";

        int templateID=1371263;
        String smsSigh="Newcomers公众号";

        RandomUtil randomUtil = new RandomUtil();
        String code=randomUtil.rand();

        try{
            String[] params={code};
            SmsSingleSender ssender=new SmsSingleSender(appID,appKey);
            SmsSingleSenderResult result=ssender.sendWithParam("86",sms.getPhone(),templateID,
                    params,smsSigh,"","");
            System.out.println(result);
        }catch (JSONException e){
            e.printStackTrace();
        } catch (HTTPException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return code;
    }
}
