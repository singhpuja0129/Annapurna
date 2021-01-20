<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.model.UserModel"%>	
<%@ page import="com.model.ItemModel"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%
	UserModel user = (UserModel) session.getAttribute("loginUser");
	if (user != null) {
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
	
 <div class="container">
 	<div class="row justify-content-center">
 	
 	<table class="table table-hover">
  <thead>
    <tr>
      <th scope="col">Item Id</th>
      <th scope="col">Item Name</th>
      <th scope="col">Item Price</th>
      <th scope="col">Item Type</th>
      <th scope="col">Item Available Status</th>
      <th scope="col">Manage</th>
    </tr>
  </thead>
  <tbody>
  <%
  List<ItemModel> items=(ArrayList<ItemModel>)request.getAttribute("items");
  for(ItemModel item:items){
  %>
    <tr class="table-success">
      <td><%=item.getItemId() %></td>
      <td><%=item.getItemName() %></td>
      <td><%=item.getItemPrice() %></td>
      <td><%=item.getItemType() %></td>
      <td><%=( item.getItemAvailable()==1 ? "Available" : "Not Available" ) %></td>
      <td><a href="#"><i class="fas fa-edit"></i></a> <a href=<%= "\"DeleteItemServlet?item_id=" + item.getItemId() + "\"" %> ><i class="fas fa-trash-alt"></i></a></td>
    </tr>
   <%} %> 
  </tbody>
</table>
</div>
 </div>		

<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
<%
	} else {
		response.sendRedirect("login.jsp");
	}
%>