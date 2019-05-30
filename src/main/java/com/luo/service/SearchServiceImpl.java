package com.luo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luo.dao.SearchDao;
import com.luo.domain.User;

@Service

public class SearchServiceImpl implements SearchService {
    @Autowired
    private SearchDao searchDao;

    @Override
    public List<User> searchUserByName(String userName) {
        // TODO Auto-generated method stub
        return searchDao.searchUserByName(userName);
    }

    @Override
    public User checkUser(String userName) {
        // TODO Auto-generated method stub
        
        return searchDao.checkUser(userName);
    }

   

    @Override
    public List<User> getPage(int page, int limit) {
        
        // TODO Auto-generated method stub
        int start =(page-1)*limit+1;
        int end = page *limit;
        
        return searchDao.getPage(start,end);
        
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return searchDao.getCount();
    }

    @Override
    public void updateUserById(User user) {
        // TODO Auto-generated method stub
      searchDao.updateUserById(user);
        
    }

    @Override
    public List<User> findListByUserName(int page, int limit, String userName) {
        // TODO Auto-generated method stub
        int start =(page-1)*limit+1;
        int end = page *limit;
        
        return searchDao.findListByUserName(start,end,userName);
    }

    @Override
    public int getCountByUserName(String userName) {
        // TODO Auto-generated method stub
        return searchDao.getCountByUserName(userName);
    }

    
}
