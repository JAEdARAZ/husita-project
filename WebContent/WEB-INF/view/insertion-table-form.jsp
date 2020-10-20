<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>
<head>
	<meta charset="UTF-8">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
	<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
	<title>Insert translations</title>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$("#checkTranslations").click(function(){
				var translations = $("#areaTranslations").val().split("\n");
				//remove empty values
				translations = translations.filter(function(e){return e});
				
				translations.forEach((val, index) => translations[index] = "# " + val + " #");
				$("#areaTranslations").val(translations.join("\n"));
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
	
	<form>
		<div class="form-group text-center">
			<h4>new translations / nuevas traducciones</h4>
			<textarea id="areaTranslations" class="form-control text-center" rows="15" autofocus></textarea>
		</div>
		
		<div class="text-center">
			<a id="checkTranslations" class="btn btn-primary btn-lg" role="button">
				<span>CHECK</span>
			</a>
		</div>
		
		<div class="text-center">
			<a class="btn btn-success btn-lg mt-2" href="${pageContext.request.contextPath}/translation/insertTranslations" role="button">
				<span>SAVE!</span>
			</a>
		</div>
	</form>

</body>

</html>