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
			<h1>Husita Project</h1>
		</div>	
	</div><hr><br>
	
	
	<div id="container">
		<div id="content">
		
		<div class="row">
			<div class="col-md-10">
			    <form class="form-inline" method="GET" action="${pageContext.request.contextPath}/translation/searchTranslations">
				    <input id="word" name="word" 
				    	class="form-control form-control-lg" type="text" placeholder="Search / Buscar">
				      
					<button class="btn btn-dark btn-lg mr-sm-2 active" type="submit">Search</button>
				      
					<div class="btn-group">
						<button type="button" class="btnChanges btn btn-lg btn-dark active">ENG</button>
						<button type="button" class="btnChanges btn btn-lg btn-secondary inactive">ESP</button>
					</div>
			    </form><br>
		    </div>
		    
		    <div class="col-md-2 text-right">
		    	<form>
					<a class="btn btn-success btn-lg" href="${pageContext.request.contextPath}/translation/showInsertForm" role="button">
						<i class="fas fa-plus"></i>
					</a>
			    </form><br>
		    </div>
	    </div>

		    
	    <!-- loop and print translations using Map<String, List<Translation>> -->
	    <c:forEach var="dateAndTranslations" items="${mapTranslations}">
	    
	    	<!-- TABLE HEADER -->
	    	<table class="table table-bordered text-center">
		    	<thead class="thead-dark">
			        <tr class="d-flex">
			        	<th class="col-5">English</th>
			            <th class="col-5">Spanish</th>
			            <th class="col-2">${dateAndTranslations.key}</th>
			        </tr>
			    </thead>
		    
		    	<!-- TABLE BODY -->
		    	<c:forEach var="tempTranslation" items="${dateAndTranslations.value}">
			    	<!-- UPDATE AND DELETE LINKS -->
					<c:url var="updateLink" value="/translation/showFormUpdate">
						<c:param name="translationId" value="${tempTranslation.id}" />
					</c:url>
					
					<c:url var="deleteLink" value="/translation/deleteTranslation">
						<c:param name="translationId" value="${tempTranslation.id}" />
					</c:url>
			        
			        <tr class="d-flex">
			        	<td class="col-5 align-middle">${tempTranslation.sentEnglish}</td>
			            <td class="col-5 align-middle">${tempTranslation.sentSpanish}</td>
			            <td class="col-1 align-middle">
			            	<button class="btn btn-info" onclick="window.location.href='${updateLink}'; return false;">
		                        <i class="fas fa-pencil-alt"></i>
		                    </button>
			            </td>
			            <td class="col-1 align-middle">
		                    <button class="btn btn-danger" onclick="window.location.href='${deleteLink}'; return false;">
		                        <i class="fas fa-minus"></i>
		                    </button>
			            </td>
			        </tr>
	        	</c:forEach>
	        </table><br>
	        
	    </c:forEach>
		
		
		</div>	
	</div>

</body>

</html>