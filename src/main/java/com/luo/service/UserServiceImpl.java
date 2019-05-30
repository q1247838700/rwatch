package com.luo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luo.dao.UserDao;
import com.luo.domain.User;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
    private UserDao userDao;  

    public User selectUserById(Integer userId) {  
        return userDao.selectUserById(userId);  

    }

	public User loginUser(String userName, String password) {
		
		User user = null;
		try { 
			user = userDao.loginUser(userName, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
		
	}
	

	public boolean signInUser(String userName, String password) {
		User user = null;
		boolean seccuss = false;
		try { 
			user = userDao.selectUserByName(userName);
			if(user==null){
				User user1 = new User();
				user1.setUserName(userName);
				user1.setUserPassword(password);
				userDao.signInUser(user1);
				seccuss = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return seccuss;
	}

	public List<User> listUser() {
		// TODO �Զ����ɵķ������
		return userDao.listUser();
	}

    @Override
    public void deleteUserById(String userId) {
        
        userDao.deleteUserById(userId);
        
    }

    @Override
    public List<User> listUser2() {
        // TODO Auto-generated method stub
        return userDao.listUser2();
    }

   
    
}
