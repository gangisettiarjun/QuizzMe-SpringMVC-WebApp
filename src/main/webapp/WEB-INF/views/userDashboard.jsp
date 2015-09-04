<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>QuizMe Dashboard</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Optional theme -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">

<!-- Bootstrap core CSS -->
<link href="resources/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="resources/css/dashboard.css" rel="stylesheet">
<script type="text/javascript" src="resources/js/canvasjs.min.js"></script>
<script type="text/javascript">
	window.onload = function() {
		var hashMap = '${categoryWiseDistribution}';
		var count = 1;
		var newWord = "";
		var dataP = [];
		while (hashMap[count] != '}') {

			if (hashMap[count] == ",") {
				count = count + 1;
				continue;
			}
			if (hashMap[count] != "=") {
				newWord = newWord + hashMap[count];

			} else {
				var categoryCount = hashMap[count + 1];
				var newEntry = {
					y : categoryCount,
					indexLabel : newWord
				}
				dataP.push(newEntry);
				newWord = "";
				count = count + 1;
			}
			count = count + 1;
		}
		var chart = new CanvasJS.Chart("chartContainer", {
			data : [ {
				type : "doughnut",
				dataPoints : dataP
			} ]
		});

		chart.render();
	}
</script>

</head>

<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">QuizzMe</a>
		</div>
		<div>
			<ul class="nav navbar-nav navbar-right">

			</ul>
		</div>
	</div>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li class="active"><a href="/quizzme/dashboard"><span
							class="glyphicon glyphicon-stats"></span> Dashboard</a></li>
				</ul>
				<ul class="nav nav-sidebar">
					<li><a href="/quizzme/quiz/create"><span
							class="glyphicon glyphicon-plus"></span> Create Quizzes</a></li>
					<li><a href="/quizzme/quiz/search"><span
							class="glyphicon glyphicon-search"></span> Search Quizzes</a></li>
					<li><a href="/quizzme/quiz/search"><span
							class="glyphicon glyphicon-thumbs-up"></span> Recommend Quizzes</a></li>

					<li><a href="/quizzme/user/recommendations"><span
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


			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<h4 class="page-header">
					Welcome <small>${userName}</small>
				</h4>

				<div class="col-md-6 col-sm-12 col-xs-12">
					<div class="panel panel-default">
						<div class="panel-heading">Total quizzes attempted by
							category</div>
						<div class="panel-body">
							<div id="chartContainer" style="height: 282px; width: 100%;"></div>
						</div>
					</div>
				</div>

				<div class="col-md-6 col-sm-12 col-xs-12">
					<div class="panel panel-default">
						<div class="panel-heading">Recently attempted quizzes</div>
						<div class="panel-body">
							<div class="table-responsive">
								<table class="table table-striped">
									<thead>
										<tr>
											<th>Quiz Name</th>
											<th>Level</th>
											<th>Category</th>
											<th>Your Score</th>
											<th>Highest Score</th>
										</tr>
									</thead>
									<tbody>
										<c:if test="${attemptedQuizzes.size()==0}">
											<tr>
												<td>Go ahead and attempt some quizzes.</td>
											</tr>
										</c:if>
										<c:forEach items="${attemptedQuizzes}" var="attempted">
											<tr>
												<td>${attempted.quiz.name}</td>
												<td>${attempted.quiz.level}</td>
												<td>${attempted.quiz.category.categoryName}</td>
												<td>${attempted.score}</td>
												<td>${attempted.highestScore}</td>
											</tr>
										</c:forEach>
								</table>
							</div>
						</div>
					</div>
				</div>

				<h4 class="sub-header">Most Popular Quizzes</h4>
				<div class="table-responsive">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>Quiz Name</th>
								<th>Popularity</th>
								<th>Level</th>
								<th>Category</th>

							</tr>
						</thead>
						<tbody>
							<c:forEach items="${popularQuizList}" var="popQuiz">
								<tr>
									<td>${popQuiz.name}</td>
									<td>${popQuiz.popularity}</td>
									<td>${popQuiz.level}</td>
									<td>${popQuiz.category.categoryName}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>




	<script src="resources/js/bootstrap.min.js"></script>
	<script src="resources/js/holder.js"></script>
	<script src="resources/js/ie10-viewport-bug-workaround.js"></script>


</body>
</html>
