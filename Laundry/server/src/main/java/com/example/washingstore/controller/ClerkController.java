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

//处理admin端的clerk的用户名单
@RestController
public class ClerkController {
    @Autowired
    private UserDao userDao;
    @RequestMapping("/clerkuser")
    public String getClerkList(QueryInfo queryInfo){
        //获取最大列表数和当前编号
        int clerkCounts = userDao.getClerkCounts("%"+queryInfo.getQuery()+"%");//模糊查询
        int pageStart = (queryInfo.getPageNum()-1)*queryInfo.getPageSize();
        List<User> users = userDao.getAllClerk("%"+queryInfo.getQuery()+"%",pageStart,queryInfo.getPageSize());

        HashMap<String, Object> res = new HashMap<>();

        res.put("number",clerkCounts);
        res.put("data",users);

        String res_string = JSON.toJSONString(res);
        return res_string;
    }

    @RequestMapping("/addclerk")
    public String addClerk(@RequestBody User user){
        user.setRole(2);
        int i=userDao.addClerk(user);
        return i>0?"success":"error";
    }

    @RequestMapping("/deleteclerk")
    public String deleteClerk(String id){
        int i=userDao.deleteClerk(id);
        System.out.println(" "+id+"  "+i);
        return i > 0 ? "success" : "error";
    }

    @RequestMapping("/updateclerk")
    public String getUpdateClerk(String id){
        User user = userDao.getUpdateClerk(id);
        System.out.println(user.getUsername());
        String str = JSON.toJSONString(user);
        return str;
    }

    @RequestMapping("/editclerk")
    public String editClerk(@RequestBody User user){
        int i = userDao.editClerk(user);
        return i>0?"success":"error";
    }


}
