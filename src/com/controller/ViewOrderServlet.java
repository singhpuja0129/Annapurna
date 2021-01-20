package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.db.DbConnection;
import com.model.OrderModel;
import com.model.UserModel;
import com.service.OrderService;

@WebServlet("/ViewOrderServlet")
public class ViewOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ViewOrderServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		HttpSession ses=request.getSession();
			
		UserModel user = (UserModel) ses.getAttribute("loginUser");
		if(user != null){
			int u_id=user.getUserId();
			
			Connection con=DbConnection.getDbConnection();
			
			OrderService service=new OrderService(con);
			List<OrderModel> orders=service.viewOrders(u_id);
			
			if(orders != null)
			{
				try {
					con.close();
					request.setAttribute("orders", orders);
			        RequestDispatcher rd =  
			        request.getRequestDispatcher("viewOrders.jsp");			         
			         rd.forward(request, response); 
			         
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
			else {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		else {
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Please login first !');");
			out.println("location='login.jsp';");
			out.println("</script>");
		}
	

	}

	
}
