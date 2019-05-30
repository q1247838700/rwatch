<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath(); //web项目的根路径
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>主页</title>
<style type="text/css">
	div.index{width: 500px;margin: 50px auto;background-color: #EEE;padding: 20px 50px;text-align: center;position: relative;}
	a{background-color: #0099E6;margin: 5px 20px;padding: 5px 10px;box-shadow: 2px 2px 2px rgba(0,0,0,.3);border-radius: 5px;text-decoration: none;color: white; }
</style>
</head>
<body>
<div class="index">
<h2>欢迎您回来!</h2>
<h4>用户：${user.userName}<br>密码：${user.userPassword}</h4>
 <a href="<%= basePath %>tolist2">用户列表</a>
 
 <a href="<%= basePath %>logout">注销</a>
 <a href="<%= basePath %>search/toSearch">查询用户</a>
 
 </div>
</body>
</html>