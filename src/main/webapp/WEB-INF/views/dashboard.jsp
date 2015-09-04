<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<title>Quiz Me</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script type="text/javascript" src="resources/js/canvasjs.min.js"></script>
<script src="http://www.technicalkeeda.com/js/bootstrap/jquery.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-modal/2.2.5/css/bootstrap-modal.css"></script>


<style>
.icon-image{
	 background: transparent url("/quizzme/resources/images/icon1.PNG") no-repeat;
	
}
div {
	display: block;
}

#first {
	position: relative;
	top: 30px;
	left: -50px;
}

#second {
	position: fixed;
	top: 60px;
	right: 300px;
	color: brown;
}

#third {
	position: fixed;
	top: 100px;
	right: 220px;
	color: forestgreen;
	width: 400px;
}
</style>

</head>

<body link="white">

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
			<a class="navbar-brand" href="#">QuizzMe</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<form:form class="navbar-form navbar-right" action="/quizzme/Login"
				method="POST" modelAttribute="loginModel">

				<div class="form-group">
					<form:input path="username" type="text" placeholder="Email"
						class="form-control" required="required"></form:input>
				</div>
				<div class="form-group">
					<form:input path="password" type="password" placeholder="Password"
						class="form-control" required="required"></form:input>

				</div>
				<button type="submit" class="btn btn-success">Login</button>
				<a href="/quizzme/register" class="btn btn-success">Register</a>
			</form:form>

		</div>

	</div>
	</nav>

	<!-- Main jumbotron for a primary marketing message or call to action -->
	<br>
	<br>
	<div id="first">
		<div id="chartContainer" style="height: 300px; width: 50%;"></div>

	</div>
	<div id="second">

		<h3>Trending Scores</h3>

	</div>
	<div id="third">
		<table class="table">
			<thead>
				<br>
				<tr>
					<th>Quiz</th>
					<th>Name</th>
					<th>Score</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${topScores}" var="scoreCard">
					<tr class="active">
						<td>${scoreCard.quiz.name}</td>
						<td>${scoreCard.user.firstName}</td>
						<td>${scoreCard.score}</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>

	</div>


	<br>
	<br>
	<br>
	<div class="container">
		<!-- Example row of columns -->
		<div class="row">

				<h2>Global and User Dashboard Statistics</h2>
				<p>Quiz me features global and user specific dashboards that project stats of total quizzes available, 
				most popular quizzes, recent user quizzes and quizzes taken by category</p>
				<p>
			</div>
			<div class="col-md-4">
				<h2>Create and Attempt Quizzes</h2>
				<p>Quiz me facilitates its users to create customized quizzes based on their 
				personal interests, map them to categories offered by the application and allows all the registered users to
				attempt any avaiable quiz</p>
			</div>
			<div class="col-md-4">
				<h2>Search and Recommend Quizzes</h2>
				<p>All the registered Quiz Me users can search the available quizzes and they have an option to pick aany quiz that might 
				interest their friends and recommend it to their friends</p>
			</div>

		</div>
	</div>


	<script src="resources/js/bootstrap.min.js"></script>
	<script src="resources/js/ie10-viewport-bug-workaround.js"></script>
	<script type="text/javascript">
		window.onload = function() {
			var hashMap = '${categoryDistribution}';
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
						label : newWord
					}
					dataP.push(newEntry);
					newWord = "";
					count = count + 1;
				}
				count = count + 1;
			}	
			var chart = new CanvasJS.Chart("chartContainer", {
				animationEnabled : true,
				data : [ {
					type : "pie",
					dataPoints : dataP
				} ]
			});

			chart.render();
			
		}
	</script>
</body>
</html>