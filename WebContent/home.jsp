<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.model.UserModel"%>
<%
	UserModel user = (UserModel) session.getAttribute("loginUser");
	if (user != null) {
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>

</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="container">

		

		<div class="d-flex justify-content-center h-100">
			<div class="card">
				<div class="card-body">

					<h1 class="card-title">
						Welcome:
						<%=user.getFullName()%></h1>


					<h2 class="card-text">
						Email:
						<%=user.getEmail()%></h2>
					<h2 class="card-text">
						User Role:
						<%=user.getUserRole()%></h2>
				</div>
				<div class="card-footer">
					<a href="LogoutServlet" class="btn btn-primary">Logout</a>
				</div>
			</div>
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