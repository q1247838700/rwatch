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
    .demo-carousel{height: 200px; line-height: 200px; text-align: center;}
  </style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户列表</title>
<script  src="<%=basePath %>js/jquery.min.js" type="text/javascript"></script>
<script src="<%=basePath %>js/layui2.4.5/layui.js"></script>
<script src="<%=basePath %>js/layui2.4.5/lay/modules/layer.js"></script>
</head>
<body>
<div class="demoTable">
    搜索用户：
    <div class="layui-inline">
        <input class="layui-input" name="userName" id="userName" >
    </div>
    <button class="layui-btn search_btn" data-type="getInfo"id="searchBtn">搜索</button>
</div>
<table id="demo" lay-filter="test"></table>
<script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看详情</a>
  <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script>
layui.use(['table','laypage'], function(){
  var table = layui.table
  ,laypage = layui.laypage
 

  
  
  //第一个实例
  table.render({
    elem: '#demo'
    ,height: 555
    ,url: '<%= basePath%>search/getPage' //数据接口
    ,page: true //开启分页
    ,toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
        ,totalRow: true //开启合计行
    ,cols: [[ //表头
      {field: 'userId', title: '用户ID', width:300, sort: true, fixed: 'left'}
      ,{field: 'userName', title: '用户名', width:300}
      ,{field: 'userPassword', title: '密码', width:300, sort: true}
     ,{fixed: 'right',title:'操作', width: 400, align:'center', toolbar: '#barDemo'} 
     
    ]]
  });

 //监听头工具栏事件
  table.on('toolbar(test)', function(obj){
    var checkStatus = table.checkStatus(obj.config.id)
    ,data = checkStatus.data; //获取选中的数据
    switch(obj.event){
      case 'add':
    	  layer.open({
			  type: 2, //样式 查阅文档 
			  area: ['700px', '450px'], //打开的页面的大小
			  fixed: false, //不固定
			  maxmin: true,
			  content:'<%= basePath %>search/check',
			  });
        
      break;
      case 'update':
        if(data.length === 0){
          layer.msg('请选择一行');
        } else if(data.length > 1){
          layer.msg('只能同时编辑一个');
        } else {
          layer.alert('编辑 [id]：'+ checkStatus.data[0].id);
        }
      break;
      case 'delete':
        if(data.length === 0){
          layer.msg('请选择一行');
        } else {
          layer.msg('删除');
        }
      break;
    };
  });
  $('#searchBtn').on('click',function(){
      var type = $(this).data('type');
      active[type] ? active[type].call(this) : '';
  });
  // 点击获取数据
  var  active = {
getInfo: function () {
          var userName=$('#userName').val();
         
          if (userName !='' && userName != null) {
              var index = layer.msg('查询中，请稍候...',{icon: 16,time:false,shade:0});
              setTimeout(function(){
                  table.reload('demo', {
                      where: {
                          'userName':userName
                         
                      }
                  });
                  layer.close(index);
              },800);
          } else {
              table.reload('demo',{
            	  page: {
              	    curr: 1 //重新从第 1 页开始
              	  }});
          }
          table.reload('demo', {
        	  page: {
        	    curr: 1 //重新从第 1 页开始
        	  }});
      },
};
  
  
  //监听行工具事件
  table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
    var data = obj.data //获得当前行数据
    ,layEvent = obj.event; //获得 lay-event 对应的值
    if(layEvent === 'detail'){
    	layer.open({
			  type: 2, //样式 查阅文档 
			  area: ['435px', '324px'], //打开的页面的大小
			  fixed: false, //不固定
			  maxmin: true,
			  content:'<%= basePath %>search/check' ,
			  success:function(layero,index){
				  var iframe = window['layui-layer-iframe' + index];
				  iframe.child(data);
                }
			});
    } else if(layEvent === 'del'){
      layer.confirm('真的删除行么', function(index){
        obj.del(); //删除对应行（tr）的DOM结构
        /* layer.close(index); */
        //向服务端发送删除指令
        $.ajax({  
        	type : "get",  //提交方式  
        	url : "<%=basePath %>deleteUserById",//路径  
        	data : {  
        	   "userId": data.userId
        	},//数据，必须为 Key/Value 格式  
        	success : function(result) { //返回数据根据结果进行相应的处理 
        	
        		if (result.success) { 
        			layer.msg("删除成功",{icon:6})
        			$(".layui-laypage-btn").click()
        			layer.close(index);
        			
        			
        		} else {  
        			layer.alert("删除失败了")

        		} 
        	
        	},
        	error:function(result){
        		layer.alert("某些原因失败了")
        	}
        });

      });
    } else if(layEvent === 'edit'){
    	layer.open({
			  type: 2, //样式 查阅文档 
			  area: ['435px', '324px'], //打开的页面的大小
			  fixed: false, //不固定
			  maxmin: true,
			  content:'<%= basePath %>search/check' ,
			  success:function(layero,index){
				  var iframe =window['layui-layer-iframe' + index];
				  iframe.child(data)
              }
			   });
    	
			  
			  
			
    }
  });
  
  
});
</script>

</body>
 

</html>