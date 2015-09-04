<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script type="text/javascript" src="resources/js/canvasjs.min.js"></script>
<script src="http://www.technicalkeeda.com/js/bootstrap/jquery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-modal/2.2.5/css/bootstrap-modal.css"></script>
</head>
<body>
<div class="container">
  <div class="row">
  	<div class="col-md-6">
    <p>${message}</p>
          <form:form class="form-horizontal" action="/register" method="POST" modelAttribute="newUser">
          <fieldset>
            <div id="legend">
            <br>
              <legend class="">Register</legend>
            </div>
            
            <div class="control-group">
              <form:label class="control-label" path="firstName">First name</form:label>
              <div class="controls">
                 <form:input path="firstName" type="text" placeholder="First name" class="form-control input-lg" required="required"></form:input>
              </div>
            </div>
            
            <div class="control-group">
              <form:label class="control-label" path="lastName">Last name</form:label>
              <div class="controls">
                 <form:input path="lastName" type="text" placeholder="Last name" class="form-control input-lg" required="required"></form:input>
              </div>
            </div>
            
             <div class="control-group">
              <form:label class="control-label" path="email">Email address</form:label>
              <div class="controls">
                 <form:input path="email" type="text" placeholder="Email address" class="form-control input-lg" required="required"></form:input>
              </div>
            </div>
            
            <div class="control-group">
              <form:label class="control-label" path="password">Password</form:label>
              <div class="controls">
                 <form:input path="password" type="password" placeholder="Password" class="form-control input-lg" required="required"></form:input>
                 <p class="help-block">Password should be at least 6 characters</p>
              </div>
            </div>
            
             <div class="control-group">
              <form:label class="control-label" path="confirmPassword">Confirm password</form:label>
              <div class="controls">
                 <form:input path="confirmPassword" type="password" placeholder="Password" class="form-control input-lg" required="required"></form:input>
                 <p class="help-block">Please confirm your password</p>
              </div>
            </div>
         
            
         
            <div class="control-group">
              <!-- Button -->
              <div class="controls">
                <button type="submit" class="btn btn-success">Register</button>
              </div>
            </div>
          </fieldset>
        </form:form>
    
    </div> 
  </div>
</div>
</body>
</html>