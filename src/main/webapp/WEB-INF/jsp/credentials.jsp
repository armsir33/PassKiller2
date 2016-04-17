<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layouts/taglib.jsp"%>

<div class="container">

	<form:form method="POST" modelAttribute="searchTermBackingBean">
		<%-- action="/credentials/search"> --%>
		<div class="input-group col-xs-3 pull-right">
			<form:input class="form-control" path="searchTerm" />
			<div class="input-group-btn">
				<div class="dropdown">
					<button class="btn btn-primary dropdown-toggle" type="button"
						id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="true">
						SearchBy <span class="caret"></span>
					</button>
					<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
						<li><a id="searchByCompany"
							href="/credentials/searchByCompany/${pageNo}">Search by company</a></li>
						<li><a id="searchByEnv"
							href="/credentials/searchByEnv/${pageNo}">Search by environment</a></li>
						<li><a id="searchByDesc"
							href="/credentials/searchByDesc/${pageNo}">Search by description</a></li>
						<li><a id="searchByModifier"
							href="/credentials/searchByModifier/${pageNo}">Search by modifier</a></li>
					</ul>
				</div>
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
				<c:if test="${key == 'none'}">
					<li><a href="/credentials/${pageNo - 1}">Previous</a></li>
				</c:if>
				<c:if test="${key == 'company'}">
					<li><a href="/credentials/searchByCompany/${pageNo - 1}/${company}">Previous</a></li>
				</c:if>
				<c:if test="${key == 'env'}">
					<li><a href="/credentials/searchByEnv/${pageNo - 1}/${env}">Previous</a></li>
				</c:if>
				<c:if test="${key == 'desc'}">
					<li><a href="/credentials/searchByDesc/${pageNo - 1}/${desc}">Previous</a></li>
				</c:if>
				<c:if test="${key == 'modifier'}">
					<li><a href="/credentials/searchByModifier/${pageNo - 1}/${modifier}">Previous</a></li>
				</c:if>
			</c:if>
			<c:if test="${pageNo < pageMax }">
				<c:if test="${key == 'none'}">
					<li><a href="/credentials/${pageNo + 1}">Next</a></li>
				</c:if>
				<c:if test="${key == 'company'}">
					<li><a href="/credentials/searchByCompany/${pageNo + 1}/${company}">Next</a></li>
				</c:if>
				<c:if test="${key == 'env'}">
					<li><a href="/credentials/searchByEnv/${pageNo + 1}/${env}">Next</a></li>
				</c:if>
				<c:if test="${key == 'desc'}">
					<li><a href="/credentials/searchByDesc/${pageNo + 1}/${desc}">Next</a></li>
				</c:if>
				<c:if test="${key == 'modifier'}">
					<li><a href="/credentials/searchByModifier/${pageNo + 1}/${modifier}">Next</a></li>
				</c:if>
			</c:if>
		</ul>
	</nav>
	
	<script type="text/javascript">
		$(document).ready(
				function() {
					$("#searchByCompany").click(
							function() {
								var _href = $(this).attr("href"); 
								var searchTerm = $("#searchTerm").val();
								if(searchTerm != "undefined" && searchTerm != null && searchTerm != "") {
									$(this).attr("href", _href + "/" + searchTerm);
								} else {
									$(this).attr("href", "/credentials/searchAll");
								}
							});
					
					$("#searchByEnv").click(
							function() {
								var _href = $(this).attr("href"); 
								var searchTerm = $("#searchTerm").val();
								if(searchTerm != "undefined" && searchTerm != null && searchTerm != "") {
									$(this).attr("href", _href + "/" + searchTerm);
								} else {
									$(this).attr("href", "/credentials/searchAll");
								}
							});
					
					$("#searchByDesc").click(
							function() {
								var _href = $(this).attr("href"); 
								var searchTerm = $("#searchTerm").val();
								if(searchTerm != "undefined" && searchTerm != null && searchTerm != "") {
									$(this).attr("href", _href + "/" + searchTerm);
								} else {
									$(this).attr("href", "/credentials/searchAll");
								}
							});
					
					$("#searchByModifier").click(
							function() {
								var _href = $(this).attr("href"); 
								var searchTerm = $("#searchTerm").val();
								if(searchTerm != "undefined" && searchTerm != null && searchTerm != "") {
									$(this).attr("href", _href + "/" + searchTerm);
								} else {
									$(this).attr("href", "/credentials/searchAll");
								}
							});

				});
	</script>
</div>