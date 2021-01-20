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
<title>Add Food Item</title>
 <style type="text/css">
 	.card-style{
 		margin-top:1%;
 		margin-bottom:1.2%;
 		width:400px;
 	}

 </style>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="container">
		<div class="row  justify-content-center">
			<div class="card border-primary card-style">
				<div class="card-header">Add Item</div>
				<div class="card-body">
					<form action="AddItemServlet" method="post">
						<fieldset>
							<div class="form-group">
								<label for="itemName">Item Name</label>
								<input required type="text"
									class="form-control" name="item_name"
									aria-describedby="itemHelp" placeholder="Enter Item Name">
								<small id="itemHelp" class="form-text text-muted">This
									field is required</small>
							</div>
							<div class="form-group">
								<label for="itemPrice">Item Price</label> 
								<input required type="number"
									class="form-control" name="item_price"
									aria-describedby="priceHelp" placeholder="Enter Item Price">
								<small id="priceHelp" class="form-text text-muted">This
									field is required</small>
							</div>
							<div class="form-group">
								<label for="exampleSelect1">Select Item Type</label> 
								<select required class="form-control" name="item_type">
									<option value="veg">Vegetarian</option>
									<option value="non-veg">Non-Vegetarian</option>
									<option value="south">South Indian Food</option>
									<option value="sweets">Sweets</option>
								</select>
							</div>
						
							
						</fieldset>
	
								<button type="submit" class="btn btn-primary">ADD</button>
								<button type="reset" class="btn btn-secondary">RESET</button>
							
					</form>
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