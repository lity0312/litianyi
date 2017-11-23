<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=GB2312"
    pageEncoding="GB2312"%>
<%
List<Map<String,String>>list=new ArrayList<Map<String,String>>();
Map<String,String> map=new HashMap<String,String>();
map.put("title","java编程思想");
map.put("author","tom");
list.add(map);

map=new HashMap<String,String>();
map.put("title","谷歌");
map.put("author","jack");
list.add(map);
//利用gson把java集合对象转换为json字符串
Gson gson=new Gson();
String str=gson.toJson(list);
%>
<%=str %>
