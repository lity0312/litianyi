<%@ page language="java" contentType="text/html; charset=GB2312"
    pageEncoding="GB2312"%>
<%
//�趨���󷵻ص����ͺͱ���
response.setContentType("text/xml;charset=GB2312");
//�趨�����߲���Ҫ����
response.setHeader("Cache-Control","no-cache");
StringBuffer sb=new StringBuffer();
sb.append("<?xml version=\"1.0\"?>");
sb.append("<books>");
sb.append("<book>");
sb.append("<title>");
sb.append("JAVA���˼��");
sb.append("</title>");
sb.append("</book>");
sb.append("<book>");
sb.append("<title>");
sb.append("���ݽṹ���㷨");
sb.append("</title>");
sb.append("</book>");
sb.append("</books>");
//�����write��print����һ����println���������ǻ��һ���س�
//���ַ���������
//response.getWriter().write(sb.toString());
//response.getWriter().print(sb.toString());
response.getWriter().println(sb.toString());
%>
<%-- ��Ҫ�����ַ�ʽ����xml���ݣ�Ajax�޷�����
<%=sb.toString()%> --%>