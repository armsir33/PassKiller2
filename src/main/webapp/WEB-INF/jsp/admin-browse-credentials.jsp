<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layouts/taglib.jsp"%>

<div class="container">

	<div class="dropdown">
		<a id="dLabel" data-target="#" href="http://example.com"
			data-toggle="dropdown" role="button" aria-haspopup="true"
			aria-expanded="false"> Menu <span class="caret"></span>
		</a>

		<ul class="dropdown-menu" aria-labelledby="dLabel">
			<li><a href='<spring:url value="/admin/browse-users" />'>Browse
					Users</a></li>
			<li><a href='<spring:url value="/admin/browse-credentials" />'>Browse
					Credentials</a></li>
			<li><a href='<spring:url value="/admin/browse-contacts" />'>Browse
					Contacts</a></li>
		</ul>
	</div>

	<form:form method="POST" modelAttribute="searchTermBackingBean" >
		<%-- action="/admin/credential/search"> --%>
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
							href="/admin/credentials/searchByCompany">Search by company</a></li>
						<li><a id="searchByEnv"
							href="/admin/credentials/searchByEnv">Search by environment</a></li>
						<li><a id="searchByDesc"
							href="/admin/credentials/searchByDesc">Search by description</a></li>
						<li><a id="searchByModifier" href="/admin/credentials/searchByModifier">Search by modifier</a></li>
					</ul>
				</div>
			</div>
		</div>
	</form:form>

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
				<th>Operations</th>
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
					<td><a href="/admin/credential-remove/${credential.id}.html"
						class="btn btn-danger triggerRemove">Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<nav>
		<ul class="pager">
			<c:if test="${pageNo > 1}">
				<li><a href="/admin/browse-credential/${pageNo - 1}.html">Previous</a></li>
			</c:if>
			<c:if test="${pageNo < pageMax }">
				<li><a href="/admin/browse-credential/${pageNo + 1}.html">Next</a></li>
			</c:if>
		</ul>
	</nav>

	<div class="modal fade" id="modalRemove" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Remove article</h4>
				</div>
				<div class="modal-body">Really remove?</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<a href="" class="btn btn-danger removeBtn">Remove</a>
				</div>
			</div>
		</div>
	</div>
</div>


<script type="text/javascript">
	$(document).ready(
			function() {
				$(".triggerRemove").click(
						function(e) {
							e.preventDefault();
							$("#modalRemove .removeBtn").attr("href",
									$(this).attr("href"));
							$("#modalRemove").modal();
						});

				$(".triggerUserRemove").click(
						function(e) {
							e.preventDefault();
							$("#modalUserRemove .removeBtn").attr("href",
									$(this).attr("href"));
							$("#modalUserRemove").modal();
						});

				$("#searchByCompany").click(
						function() {
							var _href = $(this).attr("href");
							var searchTerm = $("#searchTerm").val();
							if (searchTerm != "undefined"
									&& searchTerm != null
									&& searchTerm != "") {
								$(this).attr("href",
										_href + "/" + searchTerm);
							} else {
								$(this).attr("href",
										"/admin/credentials/searchAll");
							}
						});
				
				$("#searchByEnv").click(
						function() {
							var _href = $(this).attr("href");
							var searchTerm = $("#searchTerm").val();
							if (searchTerm != "undefined"
									&& searchTerm != null
									&& searchTerm != "") {
								$(this).attr("href",
										_href + "/" + searchTerm);
							} else {
								$(this).attr("href",
										"/admin/credentials/searchAll");
							}
						});
				
				$("#searchByDesc").click(
						function() {
							var _href = $(this).attr("href");
							var searchTerm = $("#searchTerm").val();
							if (searchTerm != "undefined"
									&& searchTerm != null
									&& searchTerm != "") {
								$(this).attr("href",
										_href + "/" + searchTerm);
							} else {
								$(this).attr("href",
										"/admin/credentials/searchAll");
							}
						});
				
				$("#searchByModifier").click(
						function() {
							var _href = $(this).attr("href");
							var searchTerm = $("#searchTerm").val();
							if (searchTerm != "undefined"
									&& searchTerm != null
									&& searchTerm != "") {
								$(this).attr("href",
										_href + "/" + searchTerm);
							} else {
								$(this).attr("href",
										"/admin/credentials/searchAll");
							}
						});
			});
</script>
</div>