package com.example.washingstore.dao;

import com.example.washingstore.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    //登录
    public User getUserByMessage(@Param("username") String username, @Param("password") String password);
    public int register(User user);
    //客户信息管理（分页）
    public List<User> getAllClient(@Param("username")String username, @Param("pageStart") int pageStart,
                                 @Param("pageSize")int pageSize);
    public int getClientCounts(@Param("username") String username);
    public int addClient(User user);
    public int deleteClient(String id);
    public User getUpdateClient(String id);
    public int editClient(User user);
    //店员信息管理
    public List<User> getAllClerk(@Param("username")String username, @Param("pageStart") int pageStart,
                                   @Param("pageSize")int pageSize);
    public int getClerkCounts(@Param("username") String username);
    public int addClerk(User user);
    public int deleteClerk(String id);
    public User getUpdateClerk(String id);
    public int editClerk(User user);

}
