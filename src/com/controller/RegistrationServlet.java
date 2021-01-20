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

@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public RegistrationServlet() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		HttpSession ses=request.getSession();
		
		//Get the user data from registration.jsp page
		String name=request.getParameter("fullname");
		String email=request.getParameter("email");
		String phone=request.getParameter("phoneNo");
		String pass=request.getParameter("password");
		String cpass=request.getParameter("cpassword");
		System.out.println(name + " "+ email + " "+phone + " "+pass+ " "+cpass);
				
		//validation of fields..
		if(name.isEmpty()) {
			return;
		}
		if(!pass.equals(cpass)) {
			System.out.println("Confirm Password does not match");
			return;
		}
		
		UserModel user=new UserModel();
		//set the value of each fields by using setter method
		user.setFullName(name);
		user.setEmail(email);
		user.setPhoneNo(phone);
		user.setPassword(pass);
		user.setUserRole("PROVIDER");
		
		Connection con=DbConnection.getDbConnection(); //get DB connection from DbConnection class by calling getConnection() static method
		
		UserService service=new UserService(con); //calling constructor of UserService with Connection parameter
		boolean res=service.userRegistration(user);
		if(res) {
			System.out.println("Success");
			response.sendRedirect("login.jsp");
		}
		else {
			//System.out.println("failed");
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Something went wrong plz try agin !');");
			out.println("location='registration.jsp';");
			out.println("</script>");
			
			ses.setAttribute("regError", "Something went wrong plz try agin !");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
