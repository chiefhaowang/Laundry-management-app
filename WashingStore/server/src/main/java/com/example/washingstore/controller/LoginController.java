package com.example.washingstore.controller;

import com.alibaba.fastjson.JSON;
import com.example.washingstore.bean.User;
import com.example.washingstore.dao.UserDao;
import com.fasterxml.jackson.annotation.JsonAlias;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

//登录注册
@RestController
public class LoginController {
    @Autowired
    UserDao userDao;
    @RequestMapping("/login")
    public String login(@RequestBody User user){
        String flag="error";
        User us=userDao.getUserByMessage(user.getUsername(),user.getPassword());
        //json打包
        HashMap<String, Object> res = new HashMap<>();
        if(us.getUsername()!=null){
            flag="ok";
        }
        res.put("flag",flag);
        res.put("user",us);
        String res_json = JSON.toJSONString(res);
        return res_json;
    }

    @RequestMapping("/register")
    public String register(@RequestBody User user){

        user.setRole(1);
        int i=userDao.register(user);
        return i>0?"ok":"error";
    }
}
