package com.example.washingstore.dao;

import com.example.washingstore.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.example.washingstore.bean.Order;

import java.util.List;

@Repository
public interface OrderDao {
    //client业务
    public List<Order> getOrderActived(String phone);//查询正在进行中的订单
    public List<Order> getHisOrder(String phone);//查询历史订单
    public int addOrder(Order order);

    //clerk业务
    //查询历史订单
    public List<Order> getAllOrder(@Param("username")String username, @Param("pageStart") int pageStart,
                                   @Param("pageSize")int pageSize);
    public int getOrderCounts(@Param("username") String username);
    //查询当前各状态订单
    public List<Order> getStateOneOrder(@Param("first_clerkname")String first_clerkname,
                                        @Param("first_clerkphone")String first_clerkphone);
    public List<Order> getStateTwoOrder(@Param("second_clerkname")String second_clerkname,
                                        @Param("second_clerkphone")String second_clerkphone);
    public List<Order> getStateThreeOrder(@Param("third_clerkname")String third_clerkname,
                                          @Param("third_clerkphone")String third_clerkphone);
    //可申领订单
    public List<Order> getFreeOneOrder();
    public List<Order> getFreeTwoOrder();
    public List<Order> getFreeThreeOrder();
    //订单状态变更
    public int editOrderState(Order order);
    public int OrderInChargeOne(Order order);
    public int OrderInChargeTwo(Order order);
    public int OrderInChargeThree(Order order);

    //公用
    public int endOrder(Order order);
    public List<Order> getPriceList();
    public int getAllOrderCounts();
}
