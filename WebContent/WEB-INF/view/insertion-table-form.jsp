<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
				var translations = $("#areaTranslations").val();
				
				//remove # in case it's not the first time check is used
				translations = translations.replaceAll("# ","");
				translations = translations.replaceAll(" #","");
				translations = translations.split("\n");
				
				//remove empty values
				translations = translations.filter(function(e){return e});
				
				//what's between # will go to DB as a translation
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
	</div><hr><br/>
	
	<form method="POST" action="${pageContext.request.contextPath}/translation/insertTranslations">
		<div class="form-group text-center">
			<h4>new translations / nuevas traducciones (ENG - ESP)</h4>
			<textarea id="areaTranslations" name="areaTranslations" class="form-control text-center" rows="12" autofocus></textarea>
		</div>
		
		<div class="text-center">
			<input id="checkTranslations" type="button" class="btn btn-primary btn-lg mt-2" value="CHECK" />
		</div>
		
		<div class="text-center">
			<input type="submit" role="button" class="btn btn-success btn-lg mt-2" value="SAVE!" />
		</div>
	</form>

</body>

</html>