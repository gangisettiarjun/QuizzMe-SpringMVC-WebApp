<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>

<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Quiz Me</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

 


<style>
div {
	display: block;
}

p {
	font-size: 100%;
}

h2 {
	font-size: 1.875em;
}
</style>

</head>

<body>

	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="/home.jsp">QuizzMe</a>
			</div>


		</div>


	</nav>

	<br>
	<br>



	<div class="container">
	<br></br></br>		
		<div class="span12">
			<h2>Please login to access this webpage.</h2>
		</div>
		</br></br>
		
<!-- 			<button type="button" class="btn btn-primary">Login</button> -->
			
			<form id="main" method="post" name="main" action="" onsubmit="redirect(this);">
			    <button type="submit" class="btn btn-success">Login</button> 
			</form>

	</div>




<script type="text/javascript">
function redirect(elem){
     elem.setAttribute("action","/quizzme/pleaseLogin/");
     elem.submit();
}
</script>
	

</body>
</html>