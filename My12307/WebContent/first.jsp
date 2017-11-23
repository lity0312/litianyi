<%@page contentType="text/html; charset=GB2312" language="java"
 import="java.util.Calendar,java.util.Date" %>
 <%!
 String getHello(String name){
	 return "hi:"+name;
 }
 %>
 <%
 //java代码注释
/*  java代码注释 */
 Date now=new Date();
 String a=request.getParameter("a");
 request.setCharacterEncoding("GB2312");

 %>
 <html>
 <head>
 <!-- html页面注释 -->
 <%--jsp注释 --%>
 <title>第一个jsp页面</title>
 <link type="text/css" href="a.css">
 <style type="text/css">
 h1{
 color:red;
 }
 </style>
 <script type="text/javascript" src="test.js"></script>
 <script type="text/javascript">
 function test(){
	 alert("test");
 }
 </script>
 </head>
 <body>
 <h1>jsp构成</h1>
 <h1>当前系统时间:<%=now %></h1>
 <h1>当前系统时间:<%=new Date() %></h1>
 <br>
<!--  动态引入页面 -->
 <jsp:include page="second.jsp"/>
<!-- 静态引入页面 -->
<%--  <%@ include file="second.jsp" %> --%>
 </body>
 </html>