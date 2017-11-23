<%@ page language="java" contentType="text/html; charset=GB2312"
    pageEncoding="GB2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB2312">
<title>Insert title here</title>
<script type="text/javascript">
function createXHR() {
	//����Ajax������ҪXHR����(���������������)
	var req=false;
	if(window.XMLHttpRequest){
		//���IE7+,Firefox,Chrome,Opera,Safari�Ĵ���
		req=new XMLHttpRequest();
		console.log("��req��XMLHttpRequest����");
	}else if(window.ActiveXObject){
		//Ϊ�˼���IE5/IE6
		req=new ActiveXObject("Microsoft.XMLHTTP");
		console.log("��req��ActiveXObject����");
	}
	return req;
}
function doAjaxTxt() {
	var req=createXHR();
	if(req){
		/**
		1.����ʽ:get,post
		2.url(Ҫ�������Դ��ַ)
		3.�첽(true)/ͬ��(false)
		*/
		req.open("POST","txt.jsp",true);
		req.onreadystatechange=parseDate;
		req.send();
		console.log("���Ѿ�ִ����...");
	}else{
		console.log("�����������֧��AjaxӦ��");
	}



	function parseDate() {
		console.log("req.readyState="+req.readyState);
		if(req.readyState==4&&req.status==200){
			var resp=req.responseText;
			console.log("������="+resp);
		}else if(req.readyState==4&&req.status!=200){
			console.log("Ajax��������:"+req.status+";"+req.statusText);
		}
	}
}
	function doAjaxTxtParam() {
		var req=createXHR();
		if(req){
			/**
			1.����ʽ:get,post
			2.url(Ҫ�������Դ��ַ)
			3.�첽(true)/ͬ��(false)
			*/
			req.open("POST","txt.jsp?name=��ķ&age=10",true);
			req.onreadystatechange=parseDate;
			/**
			ͨ����req������������ͷContent-Type
			ʹ��send�����еĲ�������ͨform����ʽ�ύ����̨
			����URL�еĲ��������Ƿ�����Content-Type�޹�
			*/
			req.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=GB2312");
			/**
			���������һ���Զ����X-Requested-With����ͷ��������ʶ�������ͷ��Ajax����
			�Ա����ڱ���������ж�����������ñ��봦��
			*/
			req.setRequestHeader("X-Requested-With","XMLHttpRequest");
			//��������
			req.send("sex=��");
			console.log("���Ѿ�ִ����...");
		}else{
			console.log("�����������֧��AjaxӦ��");
		}
	
		function parseDate() {
			console.log("req.readyState="+req.readyState);
			if(req.readyState==4&&req.status==200){
				var resp=req.responseText;
				console.log("������="+resp);
			}else if(req.readyState==4&&req.status!=200){
				console.log("Ajax��������:"+req.status+";"+req.statusText);
			}
		}
	}
	
	function doAjaxXML() {
		var req=createXHR();
		if(req){
			req.open("POST","xml.jsp",true);
			req.onreadystatechange=parseDate;
			req.send();
			console.log("���Ѿ�ִ����...");
		}else{
			console.log("�����������֧��AjaxӦ��");
		}
		function parseDate() {
			console.log("req.readyState="+req.readyState);
			if(req.readyState==4&&req.status==200){
				var resp=req.responseXML;
				var xmlDoc=resp.documentElement;
				var titleArray=xmlDoc.getElementsByTagName("title");
				for(var i=0;i<titleArray.length;i++){
					//ѭ����ЩxmlԪ�أ���ȡ��Ӧ��ֵ
					//ͨ��js��ɶ� Ajax���ص�xml�Ľ���
					console.log("tagName="+titleArray[i].tagName);
					console.log("title value="+titleArray[i].firstChild.nodeValue);
				}
				console.log("������="+resp);
			}else if(req.readyState==4&&req.status!=200){
				console.log("Ajax��������:"+req.status+";"+req.statusText);
			}
		}
 
	}
	function doAjaxJSON() {
		var req=createXHR();
		if(req){
			/**
			1.����ʽ:get,post
			2.url(Ҫ�������Դ��ַ)
			3.�첽(true)/ͬ��(false)
			*/
			req.open("POST","json.jsp",false);
			req.onreadystatechange=parseDate;
			req.send();
			console.log("���Ѿ�ִ����...");
		}else{
			console.log("�����������֧��AjaxӦ��");
		}
		function parseDate() {
			console.log("req.readyState="+req.readyState);
			if(req.readyState==4&&req.status==200){
				var resp=req.responseText;
				console.log("������="+resp.trim());
				//ͨ��JSON����࣬�Ѻ�̨���ص�json�ַ���ת��Ϊjs����
				var jsonArray=JSON.parse(resp.trim());
				for(var i=0;i<jsonArray.length;i++){
					var obj=jsonArray[i];
					console.log("title=="+obj.title);
					console.log("author=="+obj.author);
				}
			}else if(req.readyState==4&&req.status!=200){
				console.log("Ajax��������:"+req.status+";"+req.statusText);
			}
		}
	}
</script>
</head>
<body>
<!-- form�������ֱ�����ʽ��Ĭ�ϵ�������application/x-www-form-urlencoded -->
<form action="" enctype="application/x-www-form-urlencoded;charset=GB2312"></form>
<form action="" enctype="multipart/form-data"></form>
<button onclick="doAjaxTxt()">����Ajax���ı�����</button>
<button onclick="doAjaxTxtParam()">����Ajax�Ĳ�������</button>
<button onclick="doAjaxXML()">����XML����</button>
<button onclick="doAjaxJSON()">����JSON����</button>
</body>
</html>