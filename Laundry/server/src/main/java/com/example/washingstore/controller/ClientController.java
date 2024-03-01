package com.example.washingstore.controller;

import com.alibaba.fastjson.JSON;
import com.example.washingstore.bean.QueryInfo;
import com.example.washingstore.bean.User;
import com.example.washingstore.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

//处理admin端的client的用户名单
@RestController
public class ClientController {
    @Autowired
    private UserDao userDao;
    @RequestMapping("/clientuser")
    public String getClientList(QueryInfo queryInfo){
        //获取最大列表数和当前编号
        int clientCounts = userDao.getClientCounts("%"+queryInfo.getQuery()+"%");//模糊查询
        int pageStart = (queryInfo.getPageNum()-1)*queryInfo.getPageSize();
        List<User> users = userDao.getAllClient("%"+queryInfo.getQuery()+"%",pageStart,queryInfo.getPageSize());

        HashMap<String, Object> res = new HashMap<>();

        res.put("number",clientCounts);
        res.put("data",users);

        String res_string = JSON.toJSONString(res);
        return res_string;
    }

    @RequestMapping("/addclient")
    public String addClient(@RequestBody User user){
        user.setRole(1);
        int i=userDao.addClient(user);
        return i>0?"success":"error";
    }

    @RequestMapping("/deleteclient")
    public String deleteClient(String id){
        int i=userDao.deleteClient(id);
        System.out.println(" "+id+"  "+i);
        return i > 0 ? "success" : "error";
    }

    @RequestMapping("/updateclient")
    public String getUpdateClient(String id){
        User user = userDao.getUpdateClient(id);
        System.out.println(user.getUsername());
        String str = JSON.toJSONString(user);
        return str;
    }

    @RequestMapping("/editclient")
    public String editClient(@RequestBody User user){
        int i = userDao.editClient(user);
        return i>0?"success":"error";
    }

}
