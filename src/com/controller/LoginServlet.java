package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.db.DbConnection;
import com.model.UserModel;
import com.service.UserService;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public LoginServlet() {
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		//write code here
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		
		//validation
		if(email.isEmpty()) {
			System.out.println("email is required");
			return;
		}
		
		Connection con=DbConnection.getDbConnection();
		
		UserService service=new UserService(con);
		UserModel user=service.userLogin(email, password);
		
		if( user != null)
		{
			HttpSession session=request.getSession();
			session.setAttribute("loginUser", user);
			response.sendRedirect("home.jsp");
		}
		else {
		
			out.println("<script type=\"text/javascript\">");
			out.println("alert('email or password incorrect');");
			out.println("location='login.jsp';");
			out.println("</script>");
			//System.out.println("Invalid userId and password");
			// response.sendRedirect("login.jsp");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
