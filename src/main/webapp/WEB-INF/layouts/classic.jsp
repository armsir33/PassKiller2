<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<title><tiles:getAsString name="title" /></title>

<style type="text/css">
html {
	position: relative;
	min-height: 100%;
}

body {
	padding-top: 50px;
	margin-bottom: 60px;
}

.starter-template {
	padding: 40px 15px;
	text-align: center;
}

.footer {
	position: absolute;
	bottom: 0;
	width: 100%;
	/* Set the fixed height of the footer here */
	height: 60px;
	background-color: #f5f5f5;
}
</style>
</head>
<body>
	<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras"
		prefix="tilesx"%>
	<tilesx:useAttribute name="current" />

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
				<a class="navbar-brand" href='<spring:url value="/" />'>PassKiller</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="${current == 'index' ? 'active' : '' }"><a
						href='<spring:url value="/" />'>Home</a></li>
					<li class="${current == 'credentials' ? 'active' : '' }"><a
						href='<spring:url value="/credentials.html" />'>Get Pass</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<security:authorize access="! isAuthenticated()">
						<li class="${current == 'register' ? 'active' : '' }"><a
							href='<spring:url value="/register.html" />'>Join Today</a></li>
						<li class="${current == 'login' ? 'active' : '' }"><a
							href='<spring:url value="/login.html" />'>Sign In</a></li>
					</security:authorize>
					<security:authorize access="isAuthenticated() and hasRole('ADMIN')">
						<li class="${current == 'admin' ? 'active' : '' }"><a
							href='<spring:url value="/admin.html" />'>Admin</a></li>
						<li><a href="<spring:url value="/logout" />">Logout</a></li>
					</security:authorize>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container">
		<p class="lead">
			<tiles:insertAttribute name="body" />
		</p>
	</div>



	<footer class="footer">
		<div class="container">
			<p class="text-muted">
				<tiles:insertAttribute name="footer" />
			</p>
		</div>
	</footer>
</body>
</html>