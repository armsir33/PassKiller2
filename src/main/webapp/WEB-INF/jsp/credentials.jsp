<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layouts/taglib.jsp"%>

<div class="container">

	<form:form method="POST" modelAttribute="searchTermBackingBean"
		action="/credentials/search">
		<div class="input-group col-xs-3 pull-right">
			<form:input class="form-control" path="searchTerm" />
			<div class="input-group-btn">
				<input type="submit" class="btn btn-primary" value="Search" />
			</div>
		</div>
	</form:form>
	<br />

	<table class="table table-striped table-bordered">
		<thead>
			<tr>
				<th>Company</th>
				<th>Environment</th>
				<th>Username</th>
				<th>Password</th>
				<th>Description</th>
				<th>Modifier</th>
				<th>Date</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${credentials}" var="credential">
				<tr>
					<td>${credential.company}</td>
					<td>${credential.environment}</td>
					<td>${credential.name}</td>
					<td>${credential.password}</td>
					<td>${credential.description}</td>
					<td>${credential.modifier}</td>
					<td>${credential.date}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<nav>
		<ul class="pager">
			<c:if test="${pageNo > 1}">
				<li><a href="/credentials/${pageNo - 1}.html">Previous</a></li>
			</c:if>
			<c:if test="${pageNo < pageMax }">
				<li><a href="/credentials/${pageNo + 1}.html">Next</a></li>
			</c:if>
		</ul>
	</nav>
</div>