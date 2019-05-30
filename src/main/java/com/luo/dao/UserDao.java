package com.luo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.luo.domain.User;

public interface UserDao {
	 /**
     * @param userName
     * @return User
     */
    public User selectUserById(Integer userId);  
    
    public User loginUser(@Param(value="userName") String userName,@Param(value="userPassword")String userPassword);
    
    public User selectUserByName(@Param(value="userName")String userName);
    
    public void signInUser(User user);
    
    public List<User> listUser();

    public void deleteUserById(@Param(value="userId")String userId);

    public List<User> listUser2();

   
}
