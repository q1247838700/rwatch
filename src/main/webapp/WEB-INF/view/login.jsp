<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath(); //web项目的根路径
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	%>

<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script  src="<%=basePath %>js/jquery.min.js" type="text/javascript"></script>
<script src="<%=basePath %>js/layui2.4.5/layui.js"></script>
<script src="<%=basePath %>js/layui2.4.5/lay/modules/layer.js"></script>

<link rel="stylesheet" href="<%=basePath %>js/layui2.4.5/css/layui.css">


<title>Insert title here</title>

<style type="text/css">
	div.loginDiv{width: 500px;margin: 50px auto;background-color: #EEE;padding: 20px 50px;text-align: center;position: relative;}
	a,input{border:0;background-color: #0099E6;margin: 5px 20px;padding: 10px;box-shadow: 2px 2px 2px rgba(0,0,0,.3);border-radius: 5px;text-decoration: none;color: white; }
	a{font-size: 12px;}
</style>

</head>
<body>
	<div class="loginDiv">
    <h2>LOGIN  USER</h2>
	<form action="<%= basePath %>login" method="post">
		<div>
			<label>帐号：</label>
			<input id ="userName" type="text" name="userName" onblur="checkUser()" >
		</div>
		<div>
			<label>密码：</label>
			<input type="password" name="password" >
		</div>
		<div>
			<input type="submit" value="登录" >
			<a href="<%= basePath %>toSignIn">注册</a>
		</div>	
	</form>
	<h6 style="color:red">${msg}</h6>
	</div>
	
<script src="<%=basePath %>js/layui2.4.5/layui.js"></script>	
</body>
<script type="text/javascript">
	function checkUser(){
		$.ajax({
			type:"get",
			url:"<%= basePath %>search/checkUser",
			data:{
				"userName":$("#userName").val()
			},
			success: function(){
				if(result.success){
					$("#userName").focus()
					layer.tips('用户已存在','#userName')
					
				}else
			}
			
		});
	}
</script>

</html>