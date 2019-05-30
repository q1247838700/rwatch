package com.luo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.luo.domain.User;
import com.luo.service.UserService;
import com.luo.utils.AjaxJson;
import com.luo.utils.JsonConversion;

@Controller  
public class UserController {  

    @Resource  
    private UserService userService;  

    
  @RequestMapping("/")    
    public ModelAndView getLogin(HttpSession session){      
        ModelAndView mav = new ModelAndView("login");
       
        return mav;    
    }


     
   @RequestMapping("/logout")    
    public ModelAndView getIndex(HttpSession session){   
    	User user = (User)session.getAttribute("user");
    	if(user!=null){
    		session.removeAttribute("user");
    	}
        ModelAndView mav = new ModelAndView("login");   
        return mav;    
    }    
    
    @RequestMapping(value="/signIn",method=RequestMethod.POST)    
    public ModelAndView login(@RequestParam(value="userName") String userName,
    		@RequestParam(value="password") String password){      
        ModelAndView mav = new ModelAndView("sign_in");   
        System.out.println(userName + ":" + password);
        boolean seccuss =  userService.signInUser(userName, password);
        mav.addObject("msg", seccuss);  
        return mav;    
    } 
    
    @RequestMapping("/toSignIn")    
    public ModelAndView toSignIn(HttpSession session){      
        ModelAndView mav = new ModelAndView("sign_in");   
        return mav;    
    } 
    
    @RequestMapping("/tolist2")    
    public ModelAndView tolist2(HttpSession session){      
        ModelAndView mav = new ModelAndView("list2");   
        return mav;    
    } 
    
   
   
    @RequestMapping(value="/login",method=RequestMethod.POST)    
    public ModelAndView login(@RequestParam(value="userName") String userName,
            @RequestParam(value="password") String password,HttpSession session){      
        ModelAndView mav = new ModelAndView();   
        System.out.println(userName + ":" + password);
        User user = userService.loginUser(userName, password);
        if(user != null ){
            
            System.out.println(user.getUserName() + ":" + user.getUserPassword());
            mav.addObject("user", user);  
            session.setAttribute("user", user);
            mav.setViewName("index");
            
             
           
           
        }else{
            String notice = "用户名或密码错误";
            mav.addObject("msg", notice);
            mav.setViewName("login");
           
            
        }
       return mav;
    }
    @RequestMapping("/listUser")    
    public ModelAndView listUser(HttpSession session){      
        ModelAndView mav = new ModelAndView("list");   
        List<User> list = userService.listUser();
        mav.addObject("list", list);  
        return mav;    
    }
    
    
    
   @RequestMapping(value="/listUser2",produces="text/html;charset=utf-8")
    @ResponseBody
    public String  list2(HttpSession session) {

        List<User> list2 = userService.listUser2();

        Map resultMap = new HashMap<>();

        resultMap.put("code",0);

        resultMap.put("msg","");

        resultMap.put("count",1000);

        resultMap.put("data",list2);

        
        return JsonConversion.writeMapJSON(resultMap);
        
        }
   @ResponseBody
   @RequestMapping(value="/deleteUserById")
   public AjaxJson deleteUserById( String userId){
       AjaxJson j = new AjaxJson();
       
       try{
           userService.deleteUserById(userId);
           j.setMsg("删除成功");
           j.setSuccess(true);
           
       } catch (Exception e) {
           e.printStackTrace();
           j.setSuccess(false);
           
       }
       return j;
       
   }
   
   
   
   
    
    
    
  
}
    
       
    
    

   
       

   
 


