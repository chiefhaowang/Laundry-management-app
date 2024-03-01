package com.example.washingstore.controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//跨域测试
@CrossOrigin("http://localhost:8080")
@RestController
public class Test {
    @RequestMapping("/test")
    public String TestController(){
        return "OK";
    }
}
