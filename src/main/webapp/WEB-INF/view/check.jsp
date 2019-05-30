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
 <link rel="stylesheet" href="<%=basePath %>js/layui2.4.5/css/layui.css">
  <style>
  
 
    body{margin: 10px;}
    .demo-carousel{height: 210px; line-height: 200px; text-align: center;}
  </style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户列表</title>
<script  src="<%=basePath %>js/jquery.min.js" type="text/javascript"></script>
<script src="<%=basePath %>js/layui2.4.5/layui.js"></script>
<script src="<%=basePath %>js/layui2.4.5/lay/modules/layer.js"></script>
</head>
<body>
<form class="layui-form" action="">
 <div class="layui-form-item">
    <label class="layui-form-label">用户Id:</label>
    <div class="layui-input-inline">
      <input type="text" name="userId" id="userId"   class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">用户名:</label>
    <div class="layui-input-inline">
      <input type="text" name="userName" id="userName"   class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">用户密码:</label>
    <div class="layui-input-inline">
      <input type="text" name="userPassword" id="userPassword"   class="layui-input">
    </div>
  </div>
  <input type="button" button lay-submit lay-filter="go" value="编辑" class="layui-btn">
</form>    
  
  
  <script type="text/javascript">
  function child(data){
	  $("#userId").val(data.userId);
	  $("#userName").val(data.userName);
	  $("#userPassword").val(data.userPassword);
  }
 
layui.use('form', function(){
  var form = layui.form;
  form.on('submit(go)', function(data){
  console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}

  $.ajax({  
	type : "get",  //提交方式  
	url : "<%=basePath %>search/updateUserById",//路径  
	data:data.field,
	success : function(result) { //返回数据根据结果进行相应的处理 
	
		if (result.success) { 
			parent.layer.msg('修改完成',{icon:6})
			var index = parent.layer.getFrameIndex(window.name)
			parent.layer.close(index)
			parent.$(".layui-laypage-btn").click()
			
		} else { 
			layer.alert("修改失败")

		}  
	},
	error:function(result){
		layer.alert("修改失败请重试")
	}
});


  return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
});});
</script>
  
</body>
 

</html>