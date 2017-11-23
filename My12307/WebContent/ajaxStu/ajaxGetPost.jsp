<%@ page language="java" contentType="text/html; charset=GB2312"
    pageEncoding="GB2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB2312">
<title>Insert title here</title>
<script type="text/javascript" src="../js/jquery-1.11.3.js"></script>
<script type="text/javascript">
$(function(){
	$("#btn").click(function(){
	/* 	$.get("txt.jsp",{name:"tom",age:10},function(respTxt,status,xhr){
			console.log(respTxt);
			$("#box").html(respTxt);
		}); */
		/* $.post("txt.jsp",{name:"tom",age:10},function(respTxt,status,xhr){
			console.log(respTxt);
			$("#box").html(respTxt);
		}); */
		//第四个参数一般不需要，一旦填写，就要保证与后台一致
		/* $.post("xml.jsp",{name:"tom",age:10},function(respTxt,status,xhr){
			console.log(respTxt);
			$("#box").html(respTxt);
		},"html"
		); */
		/* $.post(
			"xml.jsp",
			{name:"tom",age:10},
			function(respTxt,status,xhr){
				//console.log(respTxt);
				//$("#box").html(respTxt);
				//$("#box").html(xhr.responseXML);
				//吧xml对象转化为jQuery对象，通过find方法找到对应的标签和标签的值
				$("#box").html($(respTxt).find("book:first").find("title").text());
		      },
		      "xml"
		); */
		$.post(
				"json.jsp",
				{name:"汤姆",age:10},
				function(respTxt,status,xhr){
					console.log(respTxt);
					var json=JSON.parse(respTxt);
					$("#box").html(json[0].title);
					
			      }
			);
	});
});
</script>
</head>
<body>

<button id="btn">异步加载数据</button>
<a href="#" onclick="myTest();">测试JS</a>
<div id="box" style="color:red">555</div>
</body>
</html>