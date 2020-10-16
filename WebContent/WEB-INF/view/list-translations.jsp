<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>

<head>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
	<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
	<title>She a good student</title>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$('.btn').click(function(){
				$('.btnChanges').removeClass('active').addClass('inactive');
				$('.btnChanges').removeClass('btn-dark').addClass('btn-secondary');
				
				$(this).removeClass('inactive').addClass('active');
				$(this).removeClass('btn-secondary').addClass('btn-dark');
			});
		});
	</script>
	
	
</head>

<body>

	<div id="wrapper">
		<div id="header" style="text-align:center;">
			<h2>Husita Project</h2>
		</div>	
	</div><br>
	
	
	<div id="container">
		<div id="content">
		
	    <form class="form-inline ml-auto">
	      
			<div class="md-form my-0">
		    	<input class="form-control" type="text" placeholder="Search" aria-label="Search">
			</div>
		      
			<button href="#!" class="btn btn-dark btn-md my-0 mr-sm-2" type="submit">Search</button>
		      
			<div class="btn-group">
				<button type="button" class="btnChanges btn btn-dark active">English</button>
				<button type="button" class="btnChanges btn btn-secondary inactive">Spanish</button>
			</div>
	      
	    </form><br>

		
		<!-- TABLE WITH TRANSLATIONS -->
		<table class="table table-bordered text-center">
		    <thead class="thead-dark">
		        <tr class="d-flex">
		        	<th class="col-5">English</th>
		            <th class="col-5">Spanish</th>
		            <th class="col-2">17/10/20</th>
		        </tr>
		    </thead>
		    
		    <!-- loop and print translations -->
		    <c:forEach var="tempTranslation" items="${translations}">
		        
		        <tr class="d-flex">
		        	<td class="col-5 align-middle">${tempTranslation.sentEnglish}</td>
		            <td class="col-5 align-middle">${tempTranslation.sentSpanish}</td>
		            <td class="col-1 align-middle">
		            	<button class="btn btn-info">
	                        <i class="fas fa-pencil-alt"></i>
	                    </button>
		            </td>
		            <td class="col-1 align-middle">
	                    <button class="btn btn-danger">
	                        <i class="fas fa-minus"></i>
	                    </button>
		            </td>
		        </tr>
		    </c:forEach>
		</table>
		
		</div>	
	</div>

</body>

</html>