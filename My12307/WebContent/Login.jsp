<%@ page language="java" contentType="text/html; charset=GB2312"
    pageEncoding="GB2312"%>
    <%@ page import="com.neuedu.util.StringUtil" %>
    <%
    String logout=StringUtil.parseNull(request, "logout");
    if(logout.equals("true")){
    	//用户点击退出，进行注销
    	session.invalidate();
    }
    String loginName="";
    //如果参数中可以获取loginName，那么使用参数中的
    loginName=StringUtil.parseNull(request, "loginName");
    if("".equals(loginName)){
    	//如果参数中没有loginName，那么我们取用cookie中的
    	Cookie[] cooks=request.getCookies();
        if(null!=cooks){
        	for(Cookie ck:cooks){
        		if(ck.getName().equals("loginName")){
        			loginName=ck.getValue();
        		}
        	}
       }
    
    }
    %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>12306购票系统</title>
<link href="css/css.css" rel="stylesheet" type="text/css">
<script language="javascript">
	function UserRegistration(){
	
	//window.location.href="UserRegistration.html";
	window.open("UserRegistration.html","register");
	}

	function UserLogin(){	
	//window.navigate("Admin/Index.html");
	var form=document.form1;
	if(validateForm(form)){
		form.action="doLogin";
		form.method="post";
		form.submit();
	  }
	
	}
	function validateForm(form){
		var loginName=form.loginName.value;
		var pwd=form.pwd.value;
		var validateCode=form.validateCode.value;
		var autoLogin=form.autoLogin.checked;
		console.log("loginName="+loginName);
		console.log("pwd="+pwd);
		console.log("validateCode="+validateCode);
		console.log("autoLogin="+autoLogin);
		var array=document.getElementsByClassName("tempTip");
		for(var i=0;i<array.length;i++){
			array[i].innerText="";
		}
		var flag=true;
		if(loginName==""){
			document.getElementById("loginNameTip").innerText="用户名必填";
			flag=false;
		}
		if(pwd==""){
			document.getElementById("pwdTip").innerText="密码必填";
			flag=false;
		}
		
		if(validateCode==""){
			document.getElementById("validateCodeTip").innerText="验证码必填";
			flag=false;
		}
		<%-- console.log("validateCode=="+validateCode);
		console.log("validateCode2=="+'<%=session.getAttribute("code")%>');
		if(validateCode!='<%=session.getAttribute("code")%>'){
			document.getElementById("validateCodeTip").innerText="请输入正确的验证码";
			flag=false;
		} --%>
		return flag;
	}
	var msg='<%=StringUtil.covertEmpty(request.getAttribute("msg"))%>';
	console.log("msg="+msg);
	if(msg!=''){
		alert(msg);
	}
	function reloadCode() {
		var img=document.getElementById("codeImg");
		img.src="ValidateCode?dt="+new Date();
		console.log("ee--"+new Date());
	}
	function keyDown(e){
       //13是键盘上面固定的回车键
		var ev=window.event||e;
		if(ev.keyCode==13){
			//你要执行的方法
			UserLogin();
		}
	}
</script>
<style type="text/css">
<!--
body {
	background-image: url(images/bg_point.gif);
}
-->
</style></head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
 <form name="form1" method="post" action="">
   <table width="933" border="0" align="center" cellpadding="0" cellspacing="0" style="margin:120px;">
  <tr>
    <td height="412" valign="top" background="images/bg_img1.jpg"><table height="300" border="0" cellspacing="0">
      <tr>
        <td width="538">&nbsp;</td>
        <td height="130" colspan="6">&nbsp;</td>
        </tr>
      <tr>
        <td rowspan="9">&nbsp;</td>
        <td width="98" height="20" align="right"><img src="images/text_yh.gif" width="60" height="18"></td>
        <td width="16">&nbsp;</td>
        <td width="136"><input name="loginName" value="<%=loginName %>" type="text" id="loginName" size="18" /><font color="red"><span id="loginNameTip" class="tempTip"></span></font></td>
        <td width="55">&nbsp;</td>
        <td width="44">&nbsp;</td>
        <td width="32">&nbsp;</td>
      </tr>
      <tr>
        <td height="20" align="right">&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td height="20" align="right"><img src="images/text_password.gif" width="60" height="18"></td>
        <td>&nbsp;</td>
        <td><input name="pwd" type="password" id="pwd" size="18" /><font color="red"><span id="pwdTip" class="tempTip"></span></font></td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td height="20" align="right">&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td height="20" align="right"><img src="images/text_yzm.gif" width="60" height="18"></td>
        <td>&nbsp;</td>
        <td><input name="validateCode" type="text" id="validateCode" size="18" onkeydown="keyDown(event)"/><font color="red"><span id="validateCodeTip" class="tempTip"></span></font></td>
        <td><span class="text_cray1"><img src="ValidateCode" id="codeImg" alt="" height="20" /></span></td>
        <td><img src="images/text_sx.gif" width="32" height="18"onclick="reloadCode();"></td>
        <td align="left">&nbsp;</td>
      </tr>
      <tr>
        <td height="30">&nbsp;</td>
        <td>&nbsp;</td>
        <td valign="bottom"><table width="100%" border="0" cellspacing="0">
          <tr>
            <td width="26" align="left"><input name="autoLogin" type="checkbox" value="true" style=" margin:0 auto;"/></td>
            <td width="170"><img src="images/text_zddl.gif" width="60" height="18"></td>
          </tr>
        </table></td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td height="20">&nbsp;</td>
        <td>&nbsp;</td>
        <td colspan="2">&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td height="20">&nbsp;</td>
        <td>&nbsp;</td>
        <td colspan="2"><table width="200" border="0" cellspacing="0">
          <tr>
            <td width="78"><input name="button"  type="button"  class="butlogin" id="button" value="" onClick="UserLogin()"></td>
            <td>&nbsp;</td>
            <td width="78"><input name="button2"  type="button"  class="butzc" id="button2"value="" onClick="UserRegistration()"></td>
          </tr>
        </table></td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td height="20">&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
    </table></td>
  </tr>
</table>
 </form>
</body>
</html>