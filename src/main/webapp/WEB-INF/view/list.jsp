<%@ page language="java" contentType="text/html; charset=utf-8"
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
<title>用户列表</title>
<style type="text/css">
div.list {
	width: 500px;
	margin: 50px auto;
	background-color: #EEE;
	padding: 20px 50px;
	text-align: center;
	position: relative;
}

table {
	width: 100%
}

table tr th, td {
	border: 1px solid #FFF;
	width: 25%;
}

a.logout{
	border: 0;
	background-color: #0099E6;
	margin: 5px 20px;
	padding: 10px;
	box-shadow: 2px 2px 2px rgba(0, 0, 0, .3);
	border-radius: 5px;
	text-decoration: none;
	color: white;
}
a.delete{
color:red;
}
</style>
</head>
<body>
	<div class="list">
		<a class="logout" href="<%= basePath %>logout" style="right: 10px; position: absolute;">返回</a>
		<h2>用户列表</h2>
		<form action="<%= basePath %>login" method="post">
		<div>
			<label>帐号：</label>
			<input type="text" name="userName" >
			<input type="submit" value="查询" >
		</div>
		<h6 style="color:red">${msg}</h6>
		
		
	</form>
		<table>
			<thead>
				<tr>
					<th>ID</th>
					<th>帐号</th>
					<th>密码</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list }" var="d">
					<tr>
						<td>${d.userId }</td>
						<td>${d.userName }</td>
						<td>${d.userPassword }</td>
						<td><a class="delete"
							href="<%= basePath %>deleteUserById?userId=${d.userId }">删除</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>