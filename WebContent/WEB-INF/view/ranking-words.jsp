<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>

<head>
	<meta charset="UTF-8">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
	<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
	<title>Ranking</title>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script type="text/javascript"></script>
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
	</div><hr>
	
	<div id="container">
		<div id="content">
			<div class="text-center">
				<h3>Most frequent words</h3><br>
			</div>
			
			<table class="table table-bordered text-center mt-2">
		    	<thead class="thead-dark">
			        <tr class="d-flex">
			        	<th class="col-10">Palabra</th>
			            <th class="col-2">Apariciones</th>
			        </tr>
			    </thead>
		    
		    	<!-- TABLE BODY -->
		    	<c:forEach var="wordRanked" items="${listWords}">
			        <tr class="d-flex">
			        	<td class="col-10 align-middle">${wordRanked.wordEsp}</td>
			            <td class="col-2 align-middle">${wordRanked.counter}</td>
			        </tr>
	        	</c:forEach>
	        </table><br>
	        
		</div>
	</div>
	
</body>

</html>