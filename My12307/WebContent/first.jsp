<%@page contentType="text/html; charset=GB2312" language="java"
 import="java.util.Calendar,java.util.Date" %>
 <%!
 String getHello(String name){
	 return "hi:"+name;
 }
 %>
 <%
 //java����ע��
/*  java����ע�� */
 Date now=new Date();
 String a=request.getParameter("a");
 request.setCharacterEncoding("GB2312");

 %>
 <html>
 <head>
 <!-- htmlҳ��ע�� -->
 <%--jspע�� --%>
 <title>��һ��jspҳ��</title>
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
 <h1>jsp����</h1>
 <h1>��ǰϵͳʱ��:<%=now %></h1>
 <h1>��ǰϵͳʱ��:<%=new Date() %></h1>
 <br>
<!--  ��̬����ҳ�� -->
 <jsp:include page="second.jsp"/>
<!-- ��̬����ҳ�� -->
<%--  <%@ include file="second.jsp" %> --%>
 </body>
 </html>