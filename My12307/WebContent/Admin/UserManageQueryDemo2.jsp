<%@ page language="java" contentType="text/html; charset=GB2312"
    pageEncoding="GB2312"%>
<%@ include file="../common/comm.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
UserService us=UserService.getInstance();
List<User> list=us.queryUsers(null);
pageContext.setAttribute("userList", list);
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>�����û�����</title>
<link href="../css/css.css" rel="stylesheet" type="text/css">
<script language="javascript">
	function UserAdd(){
	
	
	window.navigate("UserInfo_Add.html");
	
	}
</script>

<script>
function selectAllNullorReserve(obj,type){
   if(obj!=null&&obj!=""){
    if(document.getElementsByName(obj)!=undefined&&document.getElementsByName(obj).length>0){	//getElementsByName���������ð����ֲ��Ҷ��󣬷���һ�����顣
     var userids = document.getElementsByName(obj);
     if(type=="ȫѡ"){
      for(var i=0;i<userids.length;i++){
       if(userids[i].checked == false){
        userids[i].checked = true;
       }
      }
     }else if(type=="ȫ��ѡ"){
      for(var i=0;i<userids.length;i++){
       if(userids[i].checked == true){
        userids[i].checked = false;
       }
      }
     }else if(type=="��ѡ"){
      for(var i=0;i<userids.length;i++){
       if(userids[i].checked == true){
        userids[i].checked = false;
       }else{
        userids[i].checked = true;
       }
      }
     }
    }
   }  
}
</script>
</head>
<body class="write_bg">
 <form name="form1" method="post" action="">

<table width="1107" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30"></td>
  </tr>
</table>
<table width="850" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="20" colspan="2" ></td>
  </tr>
  <tr>
    <td width="13" height="30" align="left" valign="top"  ></td>
    <td width="822" align="left" valign="top"  class="text_blod_title">�û�����</td>
  </tr>
  <tr>
    <td height="15" colspan="2" align="center" ><img src="../images/line.jpg" width="850" height="6"></td>
  </tr>
  <tr>
    <td height="15" colspan="2"  ></td>
  </tr>
</table>
<table width="835" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="835" background="../images/wb_01 (3).jpg"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="5"></td>
        <td width="4%" height="25" align="left" class="text_cray1">����</td>
        <td width="11%" align="left" class="text_cray1"><label>
          <input name="textfield" type="text" class="text_cray" style="width:80px">
        </label></td>
        <td width="6%" align="center" class="text_cray1">�Ա�</td>
        <td width="6%" align="left" class="text_cray1"><label>
          <select name="select" class="text_cray">
            <option value="N">��</option>
            <option value="M">Ů</option>
          </select>
        </label></td>
        <td width="9%" align="center" class="text_cray1">֤������</td>
        <td width="13%" align="left" class="text_cray1"><label>
          <select class="text_cray" name="loginUserDTO.id_type_code" id="cardType">
            <option value="1">��������֤</option>
            <option value="2">�۰�ͨ��֤ </option>
            <option value="3">̨��ͨ��֤</option>
            <option value="4">����</option>
          </select>
        </label></td>
        <td width="8%" align="center" class="text_cray1">֤������</td>
        <td width="13%" align="left" class="text_cray1"><label>
          <input name="textfield2" type="text" class="text_cray" style="width:100px">
        </label></td>
        <td width="8%" align="center" class="text_cray1">�ÿ�����</td>
        <td width="13%" align="left" class="text_blod"><label>
          <select class="text_cray" id="passengerType" name="passenger_type" style="width:100px">
            <option value="1">����</option>
            <option value="2">��ͯ</option>
            <option value="3">ѧ��</option>
            <option value="4">�м����ˡ��˲����񾯲�</option>
          </select>
        </label></td>
        <td width="8%" align="center" valign="middle" class="text_craybold"><label>
          <input name="Submit" type="submit" class="butcx" value="">
        </label></td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td height="20" colspan="11" align="center">&nbsp;</td>
      </tr>
    </table>
        <table width="553" border="1" align="center" cellpadding="0" cellspacing="1" bordercolor="#dadada" bgcolor="#FFFFFF">
          <tr align="center">
            <td width="44" height="25" valign="middle" bordercolor="#FFFFFF" bgcolor="#FFFFFF"   class="text_cray1">ѡ��</td>
            <td width="98" height="25" valign="middle" bordercolor="#FFFFFF" bgcolor="#FFFFFF"  class="text_cray1">����</td>
            <td width="80" height="25" valign="middle" bordercolor="#FFFFFF" bgcolor="#FFFFFF"  class="text_cray1">�Ա�</td>
            <td width="132" height="25" valign="middle" bordercolor="#FFFFFF" bgcolor="#FFFFFF"  class="text_cray1">֤������</td>
            <td width="247" height="25" valign="middle" bordercolor="#FFFFFF" bgcolor="#FFFFFF"  class="text_cray1">֤������</td>
            <td width="82" height="25" valign="middle" bordercolor="#FFFFFF" bgcolor="#FFFFFF"  class="text_cray1">�ÿ�����</td>
            <td width="89" height="25" valign="middle" bordercolor="#FFFFFF" bgcolor="#FFFFFF"  class="text_cray1">����</td>
          </tr>
          <tr align="center">
            <td height="15" colspan="7" bordercolor="#FFFFFF" bgcolor="#FFFFFF"   class="text_cray1"><img src="../images/line1.jpg" width="790" height="6"></td>
          </tr>
          <c:forEach items="${userList}" var="user" varStatus="status">
            <tr align="center" bgcolor='${status.count%2==0?"#FFFFFF":"#ccc" }'>
            <td bordercolor="#FFFFFF"   class="text_cray1">${status.index+1 }<input type="checkbox" name="checkbox" value="${user.id}"${pageScope.user.id==userSession.id?"disabled":"" }></td>
            <td width="98" bordercolor= "#FFFFFF"  class="text_cray1">${user.userName}</td>
            <td width="80" bordercolor="#FFFFFF"  class="text_cray1">${pageScope.user.sex=="M"?"��":"Ů"}</td>
            <td width="132" bordercolor="#FFFFFF"  class="text_cray1">${pageScope.user.certType.content }</td>
            <td width="247" bordercolor="#FFFFFF"  class="text_cray1">${pageScope.user.cert }</td>
            <td width="82" bordercolor="#FFFFFF"  class="text_cray1">${pageScope.user.userType.content }</td>
            <td width="89" bordercolor="#FFFFFF"  class="text_cray1">
            <c:choose>
            <c:when test="${pageScope.user.id==userSession.id }">
           	  �鿴
            </c:when>
            <c:otherwise>
            <a href="UserManageInfo_Amind_Edit.jsp" class="text_red">�༭</a>
            </c:otherwise>
            </c:choose>
            <c:if test="${user.id==userSession.id }">��ǰ</c:if>
            </td>
          </tr>
          </c:forEach>
          
         <%--  <%
          if(null!=list&&list.size()>0){
        	  for(User user:list){
        		  pageContext.setAttribute("user",user);
        		  %>
        	<tr align="center" bgcolor="#FFFFFF">
            <td bordercolor="#FFFFFF"   class="text_cray1"><input type="checkbox" name="checkbox" value="<%=user.getId() %>"${pageScope.user.id==userSession.id?"disabled":"" }></td>
            <td width="98" bordercolor="#FFFFFF"  class="text_cray1"><%=user.getUserName() %></td>
            <td width="80" bordercolor="#FFFFFF"  class="text_cray1">${pageScope.user.sex=="M"?"��":"Ů"}</td>
            <td width="132" bordercolor="#FFFFFF"  class="text_cray1">${pageScope.user.certType.content }</td>
            <td width="247" bordercolor="#FFFFFF"  class="text_cray1">${pageScope.user.cert }</td>
            <td width="82" bordercolor="#FFFFFF"  class="text_cray1">${pageScope.user.userType.content }</td>
            <td width="89" bordercolor="#FFFFFF"  class="text_cray1"><a href="UserManageInfo_Amind_Edit.jsp" class="text_red">�༭</a></td>
          </tr>
        		  <% 
        	  }
          }
          %> --%>
          <tr align="center" bgcolor="#FFFFFF">
            <td bordercolor="#FFFFFF"   class="text_cray1"><input type="checkbox" name="checkbox" value="1">            </td>
            <td width="98" bordercolor="#FFFFFF"  class="text_cray1">�û�1</td>
            <td width="80" bordercolor="#FFFFFF"  class="text_cray1">��</td>
            <td width="132" bordercolor="#FFFFFF"  class="text_cray1">��������֤</td>
            <td width="247" bordercolor="#FFFFFF"  class="text_cray1">220198112081022</td>
            <td width="82" bordercolor="#FFFFFF"  class="text_cray1">����</td>
            <td width="89" bordercolor="#FFFFFF"  class="text_cray1"><a href="UserManageInfo_Amind_Edit.jsp" class="text_red">�༭</a></td>
          </tr>
          <tr align="center" bgcolor="#F5F5F5">
            <td bordercolor="#FFFFFF"   class="text_cray1"><input type="checkbox" name="checkbox" value="2"></td>
            <td width="98" bordercolor="#FFFFFF"  class="text_cray1">�û�2</td>
            <td width="80" bordercolor="#FFFFFF"  class="text_cray1">��</td>
            <td width="132" bordercolor="#FFFFFF"  class="text_cray1">��������֤</td>
            <td width="247" bordercolor="#FFFFFF"  class="text_cray1">23621519701208101x</td>
            <td width="82" bordercolor="#FFFFFF"  class="text_cray1">����</td>
            <td width="89" bordercolor="#FFFFFF"  class="text_cray1"><a href="UserManageInfo_Amind_Edit.jsp" class="text_red">�༭</a></td>
          </tr>
          <tr align="center" bgcolor="#FFFFFF">
            <td bordercolor="#FFFFFF"   class="text_cray1"><input type="checkbox" name="checkbox" value="3"></td>
            <td width="98" bordercolor="#FFFFFF"  class="text_cray1">�û�3</td>
            <td width="80" bordercolor="#FFFFFF"  class="text_cray1">��</td>
            <td width="132" bordercolor="#FFFFFF"  class="text_cray1">��������֤</td>
            <td width="247" bordercolor="#FFFFFF"  class="text_cray1">221251197112081021</td>
            <td width="82" bordercolor="#FFFFFF"  class="text_cray1">����</td>
            <td width="89" bordercolor="#FFFFFF"  class="text_cray1"><a href="UserManageInfo_Amind_Edit.jsp" class="text_red">�༭</a></td>
          </tr>
          <tr align="center">
            <td bordercolor="#FFFFFF" bgcolor="#F5F5F5"   class="text_cray1"><input type="checkbox" name="checkbox" value="4"></td>
            <td width="98" bordercolor="#FFFFFF" bgcolor="#F5F5F5"  class="text_cray1">�û�4</td>
            <td width="80" bordercolor="#FFFFFF" bgcolor="#F5F5F5"  class="text_cray1">Ů</td>
            <td width="132" bordercolor="#FFFFFF" bgcolor="#F5F5F5"  class="text_cray1">��������֤</td>
            <td width="247" bordercolor="#FFFFFF" bgcolor="#F5F5F5"  class="text_cray1">210211197012081019</td>
            <td width="82" bordercolor="#FFFFFF" bgcolor="#F5F5F5"  class="text_cray1">����</td>
            <td width="89" bordercolor="#FFFFFF" bgcolor="#F5F5F5"  class="text_cray1"><a href="UserManageInfo_Amind_Edit.jsp" class="text_red">�༭</a></td>
          </tr>
          <tr align="center">
            <td bordercolor="#FFFFFF" bgcolor="#FFFFFF"   class="text_cray1"><input type="checkbox" name="checkbox" value="5"></td>
            <td width="98" bordercolor="#FFFFFF" bgcolor="#FFFFFF"  class="text_cray1">�û�5</td>
            <td width="80" bordercolor="#FFFFFF" bgcolor="#FFFFFF"  class="text_cray1">Ů</td>
            <td width="132" bordercolor="#FFFFFF" bgcolor="#FFFFFF"  class="text_cray1">��������֤</td>
            <td width="247" bordercolor="#FFFFFF" bgcolor="#FFFFFF"  class="text_cray1">210211197012081019</td>
            <td width="82" bordercolor="#FFFFFF" bgcolor="#FFFFFF"  class="text_cray1">����</td>
            <td width="89" bordercolor="#FFFFFF" bgcolor="#FFFFFF"  class="text_cray1"><a href="UserManageInfo_Amind_Edit.jsp" class="text_red">�༭</a></td>
          </tr>
          <tr align="center">
            <td bordercolor="#FFFFFF" bgcolor="#F5F5F5"   class="text_cray1"><input type="checkbox" name="checkbox" value="6"></td>
            <td width="98" bordercolor="#FFFFFF" bgcolor="#F5F5F5"  class="text_cray1">�û�6</td>
            <td width="80" bordercolor="#FFFFFF" bgcolor="#F5F5F5"  class="text_cray1">��</td>
            <td width="132" bordercolor="#FFFFFF" bgcolor="#F5F5F5"  class="text_cray1">��������֤</td>
            <td width="247" bordercolor="#FFFFFF" bgcolor="#F5F5F5"  class="text_cray1">210211197012081019</td>
            <td width="82" bordercolor="#FFFFFF" bgcolor="#F5F5F5"  class="text_cray1">����</td>
            <td width="89" bordercolor="#FFFFFF" bgcolor="#F5F5F5"  class="text_cray1"><a href="UserManageInfo_Amind_Edit.jsp" class="text_red">�༭</a></td>
          </tr>
          <tr align="center">
            <td bordercolor="#FFFFFF" bgcolor="#FFFFFF"   class="text_cray1"><input type="checkbox" name="checkbox" value="7"></td>
            <td width="98" bordercolor="#FFFFFF" bgcolor="#FFFFFF"  class="text_cray1">�û�7</td>
            <td width="80" bordercolor="#FFFFFF" bgcolor="#FFFFFF"  class="text_cray1">Ů</td>
            <td width="132" bordercolor="#FFFFFF" bgcolor="#FFFFFF"  class="text_cray1">��������֤</td>
            <td width="247" bordercolor="#FFFFFF" bgcolor="#FFFFFF"  class="text_cray1">210211197012081019</td>
            <td width="82" bordercolor="#FFFFFF" bgcolor="#FFFFFF"  class="text_cray1">����</td>
            <td width="89" bordercolor="#FFFFFF" bgcolor="#FFFFFF"  class="text_cray1"><a href="UserManageInfo_Amind_Edit.jsp" class="text_red">�༭</a></td>
          </tr>
          <tr align="center">
            <td bordercolor="#FFFFFF" bgcolor="#F5F5F5"   class="text_cray1"><input type="checkbox" name="checkbox" value="8"></td>
            <td width="98" bordercolor="#FFFFFF" bgcolor="#F5F5F5"  class="text_cray1">�û�8</td>
            <td width="80" bordercolor="#FFFFFF" bgcolor="#F5F5F5"  class="text_cray1">Ů</td>
            <td width="132" bordercolor="#FFFFFF" bgcolor="#F5F5F5"  class="text_cray1">��������֤</td>
            <td width="247" bordercolor="#FFFFFF" bgcolor="#F5F5F5"  class="text_cray1">210211197012081019</td>
            <td width="82" bordercolor="#FFFFFF" bgcolor="#F5F5F5"  class="text_cray1">����</td>
            <td width="89" bordercolor="#FFFFFF" bgcolor="#F5F5F5"  class="text_cray1"><a href="UserManageInfo_Amind_Edit.jsp" class="text_red">�༭</a></td>
          </tr>
          <tr align="center">
            <td bordercolor="#FFFFFF" bgcolor="#FFFFFF"   class="text_cray1"><input type="checkbox" name="checkbox" value="9"></td>
            <td width="98" bordercolor="#FFFFFF" bgcolor="#FFFFFF"  class="text_cray1">�û�9</td>
            <td width="80" bordercolor="#FFFFFF" bgcolor="#FFFFFF"  class="text_cray1">��</td>
            <td width="132" bordercolor="#FFFFFF" bgcolor="#FFFFFF"  class="text_cray1">��������֤</td>
            <td width="247" bordercolor="#FFFFFF" bgcolor="#FFFFFF"  class="text_cray1">210211197012081019</td>
            <td width="82" bordercolor="#FFFFFF" bgcolor="#FFFFFF"  class="text_cray1">����</td>
            <td width="89" bordercolor="#FFFFFF" bgcolor="#FFFFFF"  class="text_cray1"><a href="UserManageInfo_Amind_Edit.jsp" class="text_red">�༭</a></td>
          </tr>
          <tr align="center">
            <td bordercolor="#FFFFFF" bgcolor="#F5F5F5"   class="text_cray1"><input type="checkbox" name="checkbox" value="10"></td>
            <td width="98" bordercolor="#FFFFFF" bgcolor="#F5F5F5"  class="text_cray1">�û�10</td>
            <td width="80" bordercolor="#FFFFFF" bgcolor="#F5F5F5"  class="text_cray1">Ů</td>
            <td width="132" bordercolor="#FFFFFF" bgcolor="#F5F5F5"  class="text_cray1">��������֤</td>
            <td width="247" bordercolor="#FFFFFF" bgcolor="#F5F5F5"  class="text_cray1">210211197012081019</td>
            <td width="82" bordercolor="#FFFFFF" bgcolor="#F5F5F5"  class="text_cray1">����</td>
            <td width="89" bordercolor="#FFFFFF" bgcolor="#F5F5F5"  class="text_cray1"><a href="UserManageInfo_Amind_Edit.jsp" class="text_red">�༭</a></td>
          </tr>
        </table>
      <br>
        <table width="773" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr align="center">
            <td width="102" align="left"  class="text_cray1"><a href="#">
              <label></label>
              <label></label>
              <label>
              <input type="checkbox" name="checkbox2" value="11"  onclick="selectAllNullorReserve('checkbox','��ѡ');" ><span class="text_blue">ȫѡ</span></label>
            </a></td>
            <td width="525" align="right"  class="text_cray1"><a href="#">
              <!--<input type="button" name="Submit23" value="����" onClick="UserAdd()"> -->
            </a></td>
            <td width="55" align="right"  class="text_cray1"><a href="#">
              <input name="Submit22" type="button" class="butsc" value="">
            </a></td>
            <td width="91" align="right"  class="text_cray1"><label>
              <input name="Submit3" type="submit" class="butdc" value="">
            </label></td>
          </tr>
        </table>
      <br>
        <table width="773" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr align="center" style="width:60%">
            <td width="335" align="center"  class="text_cray">&nbsp;</td>
            <td width="284" align="center"  class="text_cray">>> 1 2 3 4 5 6 7 8 &lt;&lt; </td>
            <td width="154" align="right"  class="text_cray1" style="width:20%"><label class="text_cray"> ÿҳ��ʾ
                <select name="select2">
                    <option>10</option>
                    <option>20</option>
                    <option>30</option>
                  </select>
            ����Ϣ</label></td>
          </tr>
        </table>
      <br></td>
  </tr>
  <tr>
    <td height="20"></td>
  </tr>
</table>
<table width="100%" border="0" cellspacing="0">
  <tr>
    <td height="2" background="../images/bottom_point.gif"></td>
  </tr>
  <tr>
    <td height="25" align="center" background="../images/bottom_ny_bg.gif" class="text_cray">copyright@12306 ��Ʊ��</td>
  </tr>
</table>
</form>
</body>
</html>
    