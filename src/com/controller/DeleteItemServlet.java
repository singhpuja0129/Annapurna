package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.db.DbConnection;
import com.model.UserModel;
import com.service.ItemService;

@WebServlet("/DeleteItemServlet")
public class DeleteItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteItemServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		HttpSession ses = request.getSession();

		UserModel user = (UserModel) ses.getAttribute("loginUser");
		if (user != null) {
			int item_id = Integer.parseInt(request.getParameter("item_id"));
			System.out.println(item_id);

			Connection con = DbConnection.getDbConnection();

			ItemService service = new ItemService(con);
			boolean res = service.deleteItem(item_id);

			if (res) {
				try {
					con.close();
					out.println("<script type=\"text/javascript\">");
					out.println("alert('Item deleted successfully !');");
					out.println("</script>");
					RequestDispatcher rd = request.getRequestDispatcher("ViewItemsServlet");
					rd.forward(request, response);
				} catch (SQLException e) {

					e.printStackTrace();
				}

			} else {

				try {
					con.close();
					out.println("<script type=\"text/javascript\">");
					out.println("alert('Something went wrong plz try again !');");
					out.println("</script>");
					RequestDispatcher rd = request.getRequestDispatcher("ViewItemsServlet");
					rd.forward(request, response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		} else {
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Please login first !');");
			out.println("location='login.jsp';");
			out.println("</script>");

		}

	}

}
