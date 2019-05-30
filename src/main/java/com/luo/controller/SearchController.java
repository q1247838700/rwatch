package com.luo.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.luo.domain.User;
import com.luo.service.SearchService;
import com.luo.utils.AjaxJson;
import com.luo.utils.JsonConversion;

@Controller
@RequestMapping("/search")
public class SearchController {
    @InitBinder  
    public void initBinder(WebDataBinder binder) {  
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
            dateFormat.setLenient(false);  
            binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));  
    }
    @Resource
    private SearchService searchService;
   
   
    
    
    
    @RequestMapping("/toSearch")
    public String toSearch(){
        return "search";
    }
    
    @RequestMapping("/searchUserByName")
    public String searchUserByName(@RequestParam(value="userName")String userName,Model model){
        List<User> userList = searchService.searchUserByName(userName);
        model.addAttribute("userList",userList);
        return "search";
    }
    
    
    @RequestMapping("/checkUser")
    @ResponseBody
    public AjaxJson checkUser(String userName){
        AjaxJson j =new AjaxJson();
        
        User user=searchService.checkUser(userName);
        if(user!=null){
            j.setSuccess(true);
            
        }else {
            j.setSuccess(false);
        }
        return j;
        
        
    }
    @ResponseBody
    @RequestMapping(value="/getPage",produces = "application/json; charset=UTF-8")
    public String getPage(@RequestParam(value="page") int page,
            @RequestParam(value="limit") int limit,
            HttpServletRequest request,
            Model model){ 
        String userName = request.getParameter("userName");
        List<User> List = null;
        int count;
        if(userName != null&&userName !=""){
            
            List =searchService.findListByUserName(page,limit,userName);
            count = searchService.getCountByUserName(userName);
            
            
        }else{
                List = searchService.getPage(page, limit);
                count = searchService.getCount();
        }
        
       
       
        Map resultMap = new HashMap<>();

        resultMap.put("code",0);

        resultMap.put("msg","success");

        resultMap.put("count",count);

        resultMap.put("data",List);

        
        return JsonConversion.writeMapJSON(resultMap);
     }
    @RequestMapping("/check")    
    public ModelAndView getLogin(HttpSession session){      
        ModelAndView mav = new ModelAndView("check");
       
        return mav;    
    }
    @ResponseBody
    @RequestMapping(value="/updateUserById",produces="application/json;charset=utf-8")
   
    
    public AjaxJson updateUserById(User user){
        AjaxJson j = new AjaxJson();
        
        try{
            searchService.updateUserById(user);
            
            j.setSuccess(true);
            j.setMsg("修改成功");
            
        } catch (Exception e) {
            e.printStackTrace();
            j.setSuccess(false);
            
        }
        return j;
        
    }
    
    
    
        
    } 
   

   
