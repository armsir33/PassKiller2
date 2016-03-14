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


	<script type="text/javascript">
		$(document).ready(
				function() {
					$('.nav-tabs a:first').tab('show') // Select first tab

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