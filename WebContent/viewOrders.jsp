<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  
<%@ page import="com.model.UserModel"%>	
<%@ page import="com.model.OrderModel"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>  
<%
	UserModel user = (UserModel) session.getAttribute("loginUser");
	if (user != null) {
%>    
<%
	if(user.getUserRole().equals("CUSTOMER")){
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
      <th scope="col">Order Id</th>
      <th scope="col">Product Id</th>
      <th scope="col">Product Name</th>
      <th scope="col">Total Price</th>
      <th scope="col">Quantity</th>
      <th scope="col">Order date</th>
      <th scope="col">Location</th>
	  <th scope="col">Provider Name</th>
      
    </tr>
  </thead>
  <tbody>
  <%
  List<OrderModel> orders=(ArrayList<OrderModel>)request.getAttribute("orders");
  for(OrderModel order:orders){
  %>
    <tr class="table-success">
      <td><%=order.getOderId() %></td>
      <td><%=order.getProductId() %></td>
      <td><%=order.getProductName() %></td>
      <td><%=order.getOrderPrice() %></td>
      <td><%=order.getQuantity()%></td>
      <td><%=order.getOrderDate() %></td>
      <td><%=order.getLocation() %></td>
      <td><%=order.getProviderName() %></td>
    </tr>
   <%} %> 
  </tbody>
</table>
</div>
 </div>		

<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>
<% } else{
	response.sendRedirect("unauthorized_user.jsp");
}
%>

<%
	} else {
		response.sendRedirect("login.jsp");
	}
%>