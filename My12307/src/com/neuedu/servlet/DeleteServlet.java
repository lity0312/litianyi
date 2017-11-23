package com.neuedu.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.business.service.UserService;
import com.neuedu.util.StringUtil;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/Admin/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("开始删除");
		String action=StringUtil.parseNull(request,"action");
		if(action.equals("delete")) {
			doDelete(request, response);
		}else {
			
		}
	}
	
	public void doDelete(HttpServletRequest req, HttpServletResponse response)throws ServletException, IOException{
		String[] dode=StringUtil.parseNullArray(req, "checkOne");
		UserService us=UserService.getInstance();
		us.delUser(dode);
		req.getRequestDispatcher("/Admin/UserManageQueryDemo3.jsp").forward(req, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
