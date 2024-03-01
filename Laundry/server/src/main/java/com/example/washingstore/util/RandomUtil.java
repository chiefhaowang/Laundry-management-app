package com.example.washingstore.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

//随机4位验证码生成
public class RandomUtil {
    public String rand() {
        //定义取值范围
        String str = "0123456789";
        //容量为4
        StringBuilder sb = new StringBuilder(4);
        for (int i = 0; i < 4; i++) {
            //遍历4次，拿到某个字符并且拼接
            char ch = str.charAt(new Random().nextInt(str.length()));
            sb.append(ch);
        }
        return sb.toString();
    }

}
