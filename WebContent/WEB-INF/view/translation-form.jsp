<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>

<head>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<title>Save Customer</title>
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>CRM - Customer Relationship Manager</h2>
		</div>	
	</div><br>
	
	<div id="container">
		<h4>Save Customer</h4><hr>
		
		<form:form action="saveCustomer" modelAttribute="customer" method="POST">
		
			<!-- it will set the id to the customer, in case it has any. It will in edit customer, not in new customer -->
			<form:hidden path="id" />
		
			<table>
				<tbody>
					
					<tr>
						<td><label>First name:</label></td>
						<td><form:input path="firstName" /></td>
					</tr>
					
					<tr>
						<td><label>Last name:</label></td>
						<td><form:input path="lastName" /></td>
					</tr>
					
					<tr>
						<td><label>Email:</label></td>
						<td><form:input path="email" /></td>
					</tr>
					
				</tbody>
			</table><br>

			<input type="submit" value="Save" class="btn btn-primary" />
		
		</form:form><br>
		
		<div style="clear; both;">
			<a href="${pageContext.request.contextPath}/customer/list">Back to list</a>
		</div>
		
	</div>

</body>

</html>