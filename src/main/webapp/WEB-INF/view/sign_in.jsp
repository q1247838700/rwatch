<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath(); //web项目的根路径
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style type="text/css">
.loginDiv{width: 500px;margin: 50px auto;background-color: #EEE;padding: 20px 50px;text-align: center;position: relative;}
	a,input{background-color: #0099E6;border:0;margin: 5px 20px;padding: 10px;box-shadow: 2px 2px 2px rgba(0,0,0,.3);border-radius: 5px;text-decoration: none;color: white; }
	a{font-size: 11px;}
</style>

</head>
<body>
	<form action="<%= basePath %>signIn" method="post" class="loginDiv">
		<div>
			<label>帐号：</label>
			<input type="text" name="userName" id="userName" onblur="checkUser()">
		</div>
		<div>
			<label>密码：</label>
			<input type="password" name="password">
		</div>
		<input type="submit" value="sign in">
		<a href="<%= basePath %>logout">go back</a>
		 <p style="color: red;">
		<c:if test="${!empty msg }">
			<c:if test="${msg eq false}">
				注册失败，该帐号可以已存在！
			</c:if>
			<c:if test="${msg eq true}">
				注册成功！
			</c:if>
		</c:if>
		</p> 
	</form>
</body>

</html>