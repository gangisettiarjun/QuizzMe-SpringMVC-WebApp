<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>Comment on the quiz</title>
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

<!-- data_tables -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdn.datatables.net/plug-ins/1.10.6/integration/bootstrap/3/dataTables.bootstrap.css">
<!--custom CSS  -->
<link href="/quizzme/resources/css/dashboard.css" rel="stylesheet">

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

.hidden {
	display: none;
}
</style>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
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



	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li><a href="/quizzme/dashboard"><span
							class="glyphicon glyphicon-stats"></span> Dashboard</a></li>
				</ul>
				<ul class="nav nav-sidebar">
					<li  class="active"><a href="/quizzme/quiz/create"><span
							class="glyphicon glyphicon-plus"></span> Create Quizzes</a></li>
					<li><a href="/quizzme/quiz/search"><span
							class="glyphicon glyphicon-search"></span> Search Quizzes</a></li>
					<li><a href="/quizzme/quiz/search"><span
							class="glyphicon glyphicon-thumbs-up"></span> Recommend Quizzes</a></li>
					<li ><a href="/quizzme/user/recommendations"><span
							class="glyphicon glyphicon-thumbs-up"></span> Recommendations for
							me</a></li>
					<li><a href="/quizzme/quiz/search"><span
							class="glyphicon glyphicon-pencil"></span> Comment on Quizzes</a></li>
				</ul>
				<ul class="nav nav-sidebar">
					<li><a href="/quizzme/logout"><span
							class="glyphicon glyphicon-log-in"></span> Logout</a></li>
				</ul>
			</div>
			<div class="col-sm-9col-sm-offset-3 col-md-10 main"
				style="margin-left: 17%; margin-top: 0%; width: 80%;">
				<div class="panel panel-default">
					<div class="panel-heading">Add questions</div>
					<div class="panel-body">
						<div>
							
							<form:form action="/quizzme/quiz/addQuestions" method="POST" class="form-horizontal" modelAttribute="question">

								<div>
									
									
										<div class="form-group">
											<label class="col-sm-2 control-label">Question 
											</label>
											<div class="col-sm-6">
												<form:input type="text"
													path="question"
													class="form-control"></form:input>
											</div>
										</div>
										<div class="form-group">
											<form:label path="answer"
												class="col-sm-2 control-label">Answer 
					</form:label>

											<div class="col-sm-6">
												<form:radiobutton path="answer"
													value="0" />
												False
												<form:radiobutton path="answer"
													value="1" />
												True

											</div>

										</div>
									

								</div>

								<input type="hidden" value="${isLastQuestion }" name="isLast">
							
							
								<button type="submit" class="btn btn-info">Add more</button>
							
							
								<button type="submit" class="btn btn-info">Submit</button>
							
								
								
							</form:form>

						</div>




					</div>
				</div>

			</div>


		</div>
	</div>

	<script
		src="http://cdn.datatables.net/1.10.6/js/jquery.dataTables.min.js"></script>
	<script
		src="http://cdn.datatables.net/plug-ins/1.10.6/integration/bootstrap/3/dataTables.bootstrap.js"></script>
	<script>
		
	</script>
</body>
<script type="text/javascript">
	function showMore() {
		$("#more_questions").removeClass("hidden");
		$("#more_questions").css("display", "block");

	}
</script>
</html>