package com.luo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.luo.domain.User;

public interface SearchDao {

    List<User> searchUserByName(@Param(value="userName")String userName);

    User checkUser(@Param(value="userName")String userName);

    List<User> getPage(@Param(value="start")int start, @Param(value="end")int end);

    public int getCount();

    public void updateUserById(User user);

    public List<User> findListByUserName(@Param(value="start")int start, @Param(value="end")int end, @Param(value="userName")String userName);

    public int getCountByUserName(@Param(value="userName")String userName);

    

    
}
