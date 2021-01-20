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
import com.model.ItemModel;
import com.model.UserModel;
import com.service.ItemService;

@WebServlet("/AddItemServlet")
public class AddItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public AddItemServlet() {
        super();
     }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		HttpSession ses=request.getSession();
			
		UserModel user = (UserModel) ses.getAttribute("loginUser");
		if(user != null){
			String itemName=request.getParameter("item_name");
			double itemPrice=Double.parseDouble(request.getParameter("item_price"));
			String itemType=request.getParameter("item_type");
			
			ItemModel item=new ItemModel();
			item.setItemName(itemName);
			item.setItemType(itemType);
			item.setItemPrice(itemPrice);
			item.setItemAvailable(1);
			item.setProviderId(user.getUserId());
			
			Connection con=DbConnection.getDbConnection();
			
			ItemService service=new ItemService(con);
			boolean res=service.addItem(item);
			
			if(res) {
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Item added successfully !');");
				out.println("location='addItem.jsp';");
				out.println("</script>");
			}
			else {
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Something went wrong plz try again !');");
				out.println("location='addItem.jsp';");
				out.println("</script>");
			}
			
		}
		else {
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Please login first !');");
			out.println("location='login.jsp';");
			out.println("</script>");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
