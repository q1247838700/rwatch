package com.luo.service;

import java.util.List;

import com.luo.domain.User;

public interface SearchService {

    List<User> searchUserByName(String userName);

     User checkUser(String userName);

   

     List<User> getPage(int page, int limit);

    int getCount();

    void updateUserById( User user);

    List<User> findListByUserName(int page, int limit, String userName);

    int getCountByUserName(String userName);

 

}
