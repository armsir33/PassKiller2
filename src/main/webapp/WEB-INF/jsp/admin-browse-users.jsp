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

	<form:form method="POST" modelAttribute="searchTermBackingBean">
		<%-- action="/admin/users/search"> --%>
		<div class="input-group col-xs-3 pull-right">
			<form:input id="searchTerm" class="form-control" path="searchTerm" />
			<div class="input-group-btn">
				<div class="dropdown">
					<button class="btn btn-primary dropdown-toggle" type="button"
						id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="true">
						SearchBy <span class="caret"></span>
					</button>
					<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
						<li><a id="searchByUsername"
							href="/admin/users/searchByUsername">Search by username</a></li>
						<li><a id="searchByFirstName"
							href="/admin/users/searchByFirstName">Search by first name</a></li>
						<li><a id="searchByLastName"
							href="/admin/users/searchByLastName">Search by last name</a></li>
						<li><a id="searchByEmail" href="/admin/users/searchByEmail">Search
								by email</a></li>
						<li><a id="searchByStatus" href="/admin/users/searchByStatus">Search
								by status</a></li>
					</ul>
				</div>
			</div>
		</div>
	</form:form>

	<table class="table table-striped table-bordered">
		<thead>
			<tr>
				<th>Username</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email</th>
				<th>Phone Number</th>
				<th>Status</th>
				<th>Operations</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${users}" var="user">
				<tr>
					<td>${user.username}</td>
					<td>${user.firstName}</td>
					<td>${user.lastName}</td>
					<td>${user.email}</td>
					<td>${user.phone}</td>
					<td>${user.enabled}</td>
					<td><c:if test="${user.enabled eq false}">
							<a href="/admin/user-activate/${user.id}.html"
								class="btn btn-info triggerUserActivate">Activate</a>
						</c:if> <c:if test="${user.enabled eq true}">
							<a href="/admin/user-deactivate/${user.id}.html"
								class="btn btn-info triggerUserDeactivate">Deactivate</a>
						</c:if> <a href="/admin/user-remove/${user.id}.html"
						class="btn btn-danger triggerUserRemove">Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<nav>
		<ul class="pager">
			<c:if test="${pageNo > 1}">
				<li><a href="/admin/browse-users/${pageNo - 1}.html">Previous</a></li>
			</c:if>
			<c:if test="${pageNo < pageMax }">
				<li><a href="/admin/browse-users/${pageNo + 1}.html">Next</a></li>
			</c:if>
		</ul>
	</nav>

	<div class="modal fade" id="modalUserActivate" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Activate User</h4>
				</div>
				<div class="modal-body">Really activate?</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<a href="" class="btn btn-info activateBtn">Activate</a>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="modalUserDeactivate" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Deactivate User</h4>
				</div>
				<div class="modal-body">Really deactivate?</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<a href="" class="btn btn-info deactivateBtn">Deactivate</a>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="modalUserRemove" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Remove User</h4>
				</div>
				<div class="modal-body">Really remove?</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<a href="" class="btn btn-danger removeBtn">Remove</a>
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

					$(".triggerUserActivate").click(
							function(e) {
								e.preventDefault();
								$("#modalUserActivate .activateBtn").attr(
										"href", $(this).attr("href"));
								$("#modalUserActivate").modal();
							});

					$(".triggerUserDeactivate").click(
							function(e) {
								e.preventDefault();
								$("#modalUserDeactivate .deactivateBtn").attr(
										"href", $(this).attr("href"));
								$("#modalUserDeactivate").modal();
							});

					$("#searchByUsername").click(
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
											"/admin/users/searchAll");
								}
							});

					$("#searchByFirstName").click(
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
											"/admin/users/searchAll");
								}
							});

					$("#searchByLastName").click(
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
											"/admin/users/searchAll");
								}
							});

					$("#searchByEmail").click(
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
											"/admin/users/searchAll");
								}
							});

					$("#searchByStatus").click(
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
											"/admin/users/searchAll");
								}
							});
				});
	</script>
</div>