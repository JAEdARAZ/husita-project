<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>

<head>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
	<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
	<title>She a good student</title>
</head>

<body>

	<div id="wrapper">
		<div id="header" style="text-align:center;">
			<h2>Husita Project</h2>
		</div>	
	</div><br>
	
	
	<div id="container">
		<div id="content">
		
		<!-- TABLE WITH TRANSLATIONS -->
		<table class="table table-bordered table-striped text-center">
		    <thead class="thead-dark">
		        <tr class="d-flex">
		        	<th class="col-5">English</th>
		            <th class="col-5">Spanish</th>
		            <th class="col-2"></th>
		        </tr>
		    </thead>
		    
		    <!-- loop and print translations -->
		    <c:forEach var="tempTranslation" items="${translations}">
		        
		        <tr class="d-flex">
		        	<td class="col-5 align-middle">${tempTranslation.sentEnglish}</td>
		            <td class="col-5 align-middle">${tempTranslation.sentSpanish}</td>
		            <td class="col-2 align-middle">
		                <div>
		                    <button class="btn btn-info mr-2">
		                        <i class="fas fa-pencil-alt"></i>
		                    </button>
		                    <button class="btn btn-danger ml-2 ">
		                        <i class="fas fa-minus"></i>
		                    </button>
		                </div> 
		                
		            </td>
		        </tr>
		    </c:forEach>
		</table>
		
		</div>	
	</div>

</body>

</html>