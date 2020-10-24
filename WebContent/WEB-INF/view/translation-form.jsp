<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
	<title>Edit Translation</title>
</head>

<body>

	<div id="wrapper">
		<div class="row" id="header" style="text-align:center;">
			<div class="col-md-2 text-left">
				<a class="btn btn-info btn-lg" href="${pageContext.request.contextPath}/translation/list" role="button">
					<i class="fas fa-arrow-left"></i>
				</a>
			</div>
			<div class="col-md-8">
				<h1>Husita Project</h1>
			</div>
			<div class="col-md-2">
			</div>
		</div>	
	</div><hr><br>
	
	<div id="container">
		<form:form action="updateTranslation" modelAttribute="translation" 
			method="POST" class="text-center">
		
			<!-- it will set the id to the customer, in case it has any. It will in edit customer, not in new customer -->
			<form:hidden path="id" />
			<form:hidden path="date" />
			
			<form:input path="sentEnglish" type="text" class="form-control form-control-lg text-center" placeholder="English sentence" />		
			<form:input path="sentSpanish" type="text" class="form-control form-control-lg text-center mt-2" placeholder="Frase en español" />
			<input type="submit" value="Save" class="btn btn-success btn-lg mt-4" />

		</form:form>
	</div>

</body>

</html>