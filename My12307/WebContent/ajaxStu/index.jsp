<%@ page language="java" contentType="text/html; charset=GB2312"
    pageEncoding="GB2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB2312">
<title>Insert title here</title>
<script type="text/javascript">
function createXHR() {
	//创建Ajax请求需要XHR对象(考虑浏览器兼容性)
	var req=false;
	if(window.XMLHttpRequest){
		//针对IE7+,Firefox,Chrome,Opera,Safari的代码
		req=new XMLHttpRequest();
		console.log("我req由XMLHttpRequest创建");
	}else if(window.ActiveXObject){
		//为了兼容IE5/IE6
		req=new ActiveXObject("Microsoft.XMLHTTP");
		console.log("我req由ActiveXObject创建");
	}
	return req;
}
function doAjaxTxt() {
	var req=createXHR();
	if(req){
		/**
		1.请求方式:get,post
		2.url(要请求的资源地址)
		3.异步(true)/同步(false)
		*/
		req.open("POST","txt.jsp",true);
		req.onreadystatechange=parseDate;
		req.send();
		console.log("我已经执行了...");
	}else{
		console.log("您的浏览器不支持Ajax应用");
	}



	function parseDate() {
		console.log("req.readyState="+req.readyState);
		if(req.readyState==4&&req.status==200){
			var resp=req.responseText;
			console.log("请求结果="+resp);
		}else if(req.readyState==4&&req.status!=200){
			console.log("Ajax运行正常:"+req.status+";"+req.statusText);
		}
	}
}
	function doAjaxTxtParam() {
		var req=createXHR();
		if(req){
			/**
			1.请求方式:get,post
			2.url(要请求的资源地址)
			3.异步(true)/同步(false)
			*/
			req.open("POST","txt.jsp?name=汤姆&age=10",true);
			req.onreadystatechange=parseDate;
			/**
			通过对req对象设置请求头Content-Type
			使得send方法中的参数以普通form的形式提交给后台
			对于URL中的参数，与是否设置Content-Type无关
			*/
			req.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=GB2312");
			/**
			在这里添加一个自定义的X-Requested-With请求头，用来标识这个请求头是Ajax请求
			以便于在编码过滤器中对它进行特殊得编码处理
			*/
			req.setRequestHeader("X-Requested-With","XMLHttpRequest");
			//发送请求
			req.send("sex=男");
			console.log("我已经执行了...");
		}else{
			console.log("您的浏览器不支持Ajax应用");
		}
	
		function parseDate() {
			console.log("req.readyState="+req.readyState);
			if(req.readyState==4&&req.status==200){
				var resp=req.responseText;
				console.log("请求结果="+resp);
			}else if(req.readyState==4&&req.status!=200){
				console.log("Ajax运行正常:"+req.status+";"+req.statusText);
			}
		}
	}
	
	function doAjaxXML() {
		var req=createXHR();
		if(req){
			req.open("POST","xml.jsp",true);
			req.onreadystatechange=parseDate;
			req.send();
			console.log("我已经执行了...");
		}else{
			console.log("您的浏览器不支持Ajax应用");
		}
		function parseDate() {
			console.log("req.readyState="+req.readyState);
			if(req.readyState==4&&req.status==200){
				var resp=req.responseXML;
				var xmlDoc=resp.documentElement;
				var titleArray=xmlDoc.getElementsByTagName("title");
				for(var i=0;i<titleArray.length;i++){
					//循环这些xml元素，获取对应的值
					//通过js完成对 Ajax返回的xml的解析
					console.log("tagName="+titleArray[i].tagName);
					console.log("title value="+titleArray[i].firstChild.nodeValue);
				}
				console.log("请求结果="+resp);
			}else if(req.readyState==4&&req.status!=200){
				console.log("Ajax运行正常:"+req.status+";"+req.statusText);
			}
		}
 
	}
	function doAjaxJSON() {
		var req=createXHR();
		if(req){
			/**
			1.请求方式:get,post
			2.url(要请求的资源地址)
			3.异步(true)/同步(false)
			*/
			req.open("POST","json.jsp",false);
			req.onreadystatechange=parseDate;
			req.send();
			console.log("我已经执行了...");
		}else{
			console.log("您的浏览器不支持Ajax应用");
		}
		function parseDate() {
			console.log("req.readyState="+req.readyState);
			if(req.readyState==4&&req.status==200){
				var resp=req.responseText;
				console.log("请求结果="+resp.trim());
				//通过JSON这个类，把后台返回的json字符串转化为js对象
				var jsonArray=JSON.parse(resp.trim());
				for(var i=0;i<jsonArray.length;i++){
					var obj=jsonArray[i];
					console.log("title=="+obj.title);
					console.log("author=="+obj.author);
				}
			}else if(req.readyState==4&&req.status!=200){
				console.log("Ajax运行正常:"+req.status+";"+req.statusText);
			}
		}
	}
</script>
</head>
<body>
<!-- form表单有两种编码形式，默认的类型是application/x-www-form-urlencoded -->
<form action="" enctype="application/x-www-form-urlencoded;charset=GB2312"></form>
<form action="" enctype="multipart/form-data"></form>
<button onclick="doAjaxTxt()">测试Ajax的文本操作</button>
<button onclick="doAjaxTxtParam()">测试Ajax的参数操作</button>
<button onclick="doAjaxXML()">测试XML操作</button>
<button onclick="doAjaxJSON()">测试JSON操作</button>
</body>
</html>