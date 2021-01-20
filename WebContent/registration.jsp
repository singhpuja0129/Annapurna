<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.model.UserModel"%>
<%
	UserModel user = (UserModel) session.getAttribute("loginUser");
	if (user != null) {	
		response.sendRedirect("home.jsp");
	}
%>	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration Page</title>

<link rel="stylesheet" type="text/css" href="CSS/register_style.css" />
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
	<div class="container h-100">
		<div class="d-flex justify-content-center h-100">
			<div class="user_card">
				<div class="d-flex justify-content-center">
					<div class="brand_logo_container">
						<img src="images/logo.png"
							class="brand_logo" alt="Logo">
					</div>
				</div>
				<div class="d-flex justify-content-center form_container">
					<form action="RegistrationServlet" method="post">
						
						<div class="input-group mb-2">
							<div class="input-group-append">
								<span class="input-group-text"><i class="fas fa-user"></i></span>
							</div>
							<input required type="text" name="fullname" class="form-control input_user"
								 placeholder="fullname">
						</div>
						<div class="input-group mb-2">
							<div class="input-group-append">
								<span class="input-group-text"><i class="fas fa-envelope"></i></span>
							</div>
							<input type="email" name="email" class="form-control input_user"
								placeholder="email">
						</div>
						<div class="input-group mb-2">
							<div class="input-group-append">
								<span class="input-group-text"><i class="fas fa-phone"></i></span>
							</div>
							<input required type="text" name="phoneNo" class="form-control input_user"
								 placeholder="phone no">
						</div>
						<div class="input-group mb-2">
							<div class="input-group-append">
								<span class="input-group-text"><i class="fas fa-key"></i></span>
							</div>
							<input required type="password" name="password" class="form-control input_pass"
								 placeholder="password">
						</div>

						<div class="input-group mb-2">
							<div class="input-group-append">
								<span class="input-group-text"><i class="fas fa-key"></i></span>
							</div>
							<input required type="password" name="cpassword" class="form-control input_pass"
								 placeholder="confirm password">
						</div>


						<div class="d-flex justify-content-center mt-3 login_container">
							<button type="submit" name="button" class="btn login_btn">Sign
								Up</button>
						</div>

					</form>
				</div>

				<div class="mt-4">
					<div class="d-flex justify-content-center links">
						Already have an account? <a href="login.jsp" class="ml-2">Login</a>
					</div>

				</div>
			</div>
		</div>
	</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>