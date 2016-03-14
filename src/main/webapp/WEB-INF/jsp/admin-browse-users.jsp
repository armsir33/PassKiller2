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

	<form:form method="POST" modelAttribute="searchTermBackingBean"
		action="/admin/users/search">
		<div class="input-group col-xs-3 pull-right">
			<form:input class="form-control" path="searchTerm" />
			<div class="input-group-btn">
				<input id="trigger-user-tab" type="submit" class="btn btn-primary"
					value="Search" />
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
					<td><a href="/admin/user-remove/${user.id}.html"
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
</div>


<script type="text/javascript">
	$(document).ready(
			function() {
				$('.nav-tabs a:second').tab('show') // Select first tab

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

			});
</script>
</div>