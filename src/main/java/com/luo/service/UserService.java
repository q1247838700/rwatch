package com.luo.service;

import java.util.List;

import com.luo.domain.User;

public interface UserService {
    User selectUserById(Integer userId);  
    
    User loginUser(String userName,String password);
    
    boolean signInUser(String userName,String password);
    
    List<User> listUser();

    void deleteUserById(String userId);

    List<User> listUser2();

    

    

    

   
}