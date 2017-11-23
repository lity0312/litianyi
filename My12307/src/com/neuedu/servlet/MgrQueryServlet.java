package com.neuedu.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.business.service.UserService;
import com.neuedu.domain.CertType;
import com.neuedu.domain.User;
import com.neuedu.domain.UserType;
import com.neuedu.util.PageBean;
import com.neuedu.util.StringUtil;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.BorderLineStyle;
import jxl.write.Border;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 * Servlet implementation class MgrQueryServlet
 */
@WebServlet("/Admin/MgrQueryServlet")
public class MgrQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MgrQueryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("开始查询");
		String action=StringUtil.parseNull(request,"action");
		if(action.equals("excel")) {
			doPageExcel(request, response);
		}else {
			doPageQuery(request, response);
		}
	}
	
	
	
	
	private void doPageExcel(HttpServletRequest request, HttpServletResponse response) {
		List<User> list=new ArrayList<>();
		list=(List<User>)request.getSession().getAttribute("exportExcelUserList");
		if(null==list || list.size()==0){
			return;
		}
		ServletOutputStream os=null;
		WritableWorkbook workBook=null;
		try{
			String fileName=new String("用户".getBytes("GB2312"),"ISO8859-1");
			//设置响应信息
			response.setHeader("Content-disposition", "attachment;filename="+fileName+".xls");
			response.setHeader("parama", "no-cache");
			response.setContentType("application/msexcel");
			//获取输出流，完成向浏览器的输出
			os=response.getOutputStream();
			//创建Workbook以及sheet对象
			workBook=Workbook.createWorkbook(os)	;
			WritableSheet ws=workBook.createSheet("用户信息列表", 0);
			//创建一些格式化对象
			WritableFont title=new WritableFont(
					WritableFont.TIMES,16,WritableFont.BOLD);
			WritableFont head=new WritableFont(
					WritableFont.TIMES,12,WritableFont.BOLD);
			WritableCellFormat titleFormat=new WritableCellFormat(title);
			//设置居中
			titleFormat.setAlignment(jxl.format.Alignment.CENTRE);
			//添加边框
			titleFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
			Label titleLabel=new Label(0,0,"导出用户列表",titleFormat);
			ws.addCell(titleLabel);
			//合并单元格
			ws.mergeCells(0, 0, 7, 0);
			//声明一组对header的格式化
			WritableCellFormat headerFormat=new WritableCellFormat(head);
			headerFormat.setAlignment(Alignment.CENTRE);
			headerFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
			headerFormat.setBackground(jxl.format.Colour.BLUE_GREY);
			
			WritableCellFormat rowFormat=new WritableCellFormat();
			rowFormat.setAlignment(Alignment.CENTRE);
			rowFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
			//指定列宽（列、列宽）
			ws.setColumnView(2, 20);
			ws.setColumnView(3, 25);
			ws.setColumnView(7, 60);
			//指定行高（行、行高）
			//ws.setRowView(0, 50);
			
			//添加数据表格的题头
			ws.addCell(new Label(0,1,"编号",headerFormat));
			ws.addCell(new Label(1,1,"用户名",headerFormat));
			ws.addCell(new Label(2,1,"角色",headerFormat));
			ws.addCell(new Label(3,1,"真实姓名",headerFormat));
			ws.addCell(new Label(4,1,"性别",headerFormat));
			ws.addCell(new Label(5,1,"城市",headerFormat));
			ws.addCell(new Label(6,1,"用户类型",headerFormat));
			ws.addCell(new Label(7,1,"证件号码",headerFormat));
			
			//添加数据到表格
			int rowNum=2;
			for(int i=0;i<list.size();i++,rowNum++){
				User user=list.get(i);
				ws.addCell(new Label(0,rowNum,user.getId()+"",headerFormat));
				ws.addCell(new Label(1,rowNum,user.getUserName(),headerFormat));
				ws.addCell(new Label(2,rowNum,user.getRule().equals("1")?"管理员":"普通用户",headerFormat));
				ws.addCell(new Label(3,rowNum,user.getRealName(),headerFormat));
				ws.addCell(new Label(4,rowNum,user.getSex().equals("M")?"男":"女",headerFormat));
				ws.addCell(new Label(5,rowNum,user.getCity().getCity(),headerFormat));
				ws.addCell(new Label(6,rowNum,user.getUserType().getContent(),headerFormat));
				ws.addCell(new Label(7,rowNum,user.getCert(),headerFormat));
			}
			
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			try {
				workBook.write();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				workBook.close();
			} catch (WriteException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				os.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void doPageQuery(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
	
		List<User> list=new ArrayList<>();
		String loginName=StringUtil.parseNull(request,"loginName");
		String sex=StringUtil.parseNull(request,"sex");
		String certType=StringUtil.parseNull(request,"idTypeCode");
		String cert=StringUtil.parseNull(request,"cert");
		String userType=StringUtil.parseNull(request,"passenger_type");
		//获取分页数据
		String pageSize=StringUtil.parseNull(request,"pageSize");
		String recordCnt=StringUtil.parseNull(request,"recordCnt");
		String curPage=StringUtil.parseNull(request,"curPage");
		//拼装查询条件对象
		User user=new User();
		user.setUserName(loginName);
		user.setSex(sex);
		user.setCertType(new CertType(Integer.parseInt(certType), ""));
		user.setCert(cert);
		user.setUserType(new UserType(Integer.parseInt(userType),""));
		//拼装分页对象
		PageBean page=new PageBean(Integer.parseInt(pageSize),Integer.parseInt(curPage));
		
		UserService us=UserService.getInstance();
		list=us.queryUsers(user,page);
		request.setAttribute("userList", list);
		//把list存放到session中，以便于excel导出
		request.getSession().setAttribute("exportExcelUserList",list);
		request.setAttribute("recordCnt", page.getRecordCnt());
		request.setAttribute("pageCnt", page.getPageCnt());
		request.setAttribute("curPage", curPage);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("pageUserParam", user);
		request.getRequestDispatcher("/Admin/UserManageQueryDemo3.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
