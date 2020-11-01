<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
	<title>Edit Translation</title>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script type="text/javascript">
		function cleanSentence(sentence) {
			sentence = sentence.trim();
			sentence = sentence.toLowerCase();
			
			sentence = sentence.replaceAll("á","a");
			sentence = sentence.replaceAll("é","e");
			sentence = sentence.replaceAll("í","i");
			sentence = sentence.replaceAll("ó","o");
			sentence = sentence.replaceAll("ú","u");
			
			return sentence;
		}
	
		$(document).ready(function(){
			$("#checkTranslations").click(function(){
				var sentEng = $("#sentEnglish").val();
				var sentEsp = $("#sentSpanish").val();
				
				//clean sentences
				sentEng = cleanSentence(sentEng);
				sentEsp = cleanSentence(sentEsp);
				
				$("#sentEnglish").val(sentEng);
				$("#sentSpanish").val(sentEsp);
				
				//no empty sentences allowed
				if(sentEng && sentEsp){
					$("#btnSave").prop("disabled", false);
				}
				else{
					$("#btnSave").prop("disabled", true);
					alert("None of the sentences can be empty hon!");
				}
		    });

			
			//if the button is enabled whey typing, it gets disabled (force CHECK)
			$("#sentEnglish, #sentSpanish").on("keyup", function(e) {
				if(!$("#btnSave").attr("disabled")){
					$("#btnSave").prop("disabled", true);
				}
			})
		});
	</script>
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

			<form:input id="sentEnglish" path="sentEnglish" type="text" autocomplete="off" class="form-control form-control-lg text-center" placeholder="English sentence" />			
			<form:input id="sentSpanish" path="sentSpanish" type="text" autocomplete="off" class="form-control form-control-lg text-center mt-2" placeholder="Frase en español" />
			
			<input id="checkTranslations" type="button" class="btn btn-primary btn-lg mt-4" value="CHECK" /><br/>
			<input id="btnSave" type="submit" value="SAVE!" class="btn btn-success btn-lg mt-2" />

		</form:form>
	</div>

</body>

</html>