<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layouts/taglib.jsp"%>

<style>
/* Space out content a bit */
body {
	padding-top: 20px;
	padding-bottom: 20px;
}

/* Everything but the jumbotron gets side spacing for mobile first views */
.header, .marketing, .footer {
	padding-right: 15px;
	padding-left: 15px;
}

/* Custom page header */
.header {
	border-bottom: 1px solid #e5e5e5;
}
/* Make the masthead heading the same height as the navigation */
.header h3 {
	padding-bottom: 19px;
	margin-top: 0;
	margin-bottom: 0;
	line-height: 40px;
}
</style>

<div class="container">
	<c:if test="${param.success eq true}">
		<div class="alert alert-success">Edit successfully</div>
	</c:if>

	<c:if test="${param.password_mismatch_failed eq true}">
		<div class="alert alert-danger">New Password not matched</div>
	</c:if>

	<h1 class="well">User Details</h1>
	<div class="col-lg-12 well">
		<div class="row">
			<form:form id="signinForm" commandName="appuser" action="/account/user-edit">
				<div class="col-sm-12">


					<div>

						<!-- Nav tabs -->
						<ul class="nav nav-tabs" role="tablist">
							<li role="presentation" class="active"><a href="#home"
								aria-controls="home" role="tab" data-toggle="tab">Home</a></li>
							<li role="presentation"><a href="#address"
								aria-controls="address" role="tab" data-toggle="tab">Address</a></li>
							<li role="presentation"><a href="#contact"
								aria-controls="contact" role="tab" data-toggle="tab">Contact</a></li>
							<li role="presentation"><a href="#password"
								aria-controls="contact" role="tab" data-toggle="tab">Password</a></li>
						</ul>
						
						<br/>
						
						<!-- Tab panes -->
						<div class="tab-content">
							<div role="tabpanel" class="tab-pane active" id="home">
								<div class="row">
									<div class="col-sm-6 form-group">
										<label>Username</label>
										<form:input path="username" cssClass="form-control"
											readonly="true" />
									</div>
								</div>
								<div class="row">
									<div class="col-sm-6 form-group">
										<label>First Name</label>
										<form:input path="firstName"
											placeholder="Enter First Name Here.." cssClass="form-control" />
									</div>
									<div class="col-sm-6 form-group">
										<label>Last Name</label>
										<form:input path="lastName"
											placeholder="Enter Last Name Here.." cssClass="form-control" />
									</div>
								</div>
								<div class="row">
									<div class="col-sm-6 form-group">
										<label>Title</label>
										<form:input path="title"
											placeholder="Enter Designation Here.."
											cssClass="form-control" />
									</div>
									<div class="col-sm-6 form-group">
										<label>Company</label>
										<form:input path="company"
											placeholder="Enter Company Name Here.."
											cssClass="form-control" />
									</div>
								</div>
							</div>
							<div role="tabpanel" class="tab-pane" id="address">

								<div class="form-group">
									<label>Address</label>
									<form:textarea path="address"
										placeholder="Enter Address Here.." rows="3"
										class="form-control" />
								</div>
								<div class="row">
									<div class="col-sm-4 form-group">
										<label>City</label>
										<form:input path="city" placeholder="Enter City Name Here.."
											cssClass="form-control" />
									</div>
									<div class="col-sm-4 form-group">
										<label>State</label>
										<form:input path="state" placeholder="Enter State Name Here.."
											cssClass="form-control" />
									</div>
									<div class="col-sm-4 form-group">
										<label>Zip</label>
										<form:input path="zip" placeholder="Enter Zip Code Here.."
											cssClass="form-control" />
									</div>
								</div>
							</div>
							<div role="tabpanel" class="tab-pane" id="contact">

								<div class="form-group">
									<label>Phone Number</label>
									<form:input path="phone"
										placeholder="Enter Phone Number Here.."
										cssClass="form-control" />
								</div>
								<div class="form-group">
									<label>Email Address</label>
									<form:input path="email" cssClass="form-control" />
								</div>
								<div class="form-group">
									<label>Website</label>
									<form:input path="website"
										placeholder="Enter Website Name Here.."
										cssClass="form-control" />
								</div>
							</div>
							
							<div role="tabpanel" class="tab-pane" id="password">
								<div class="form-group">
									<label>New Password</label>
									<form:password path="password" placeholder="Enter New Password again Here.."
										cssClass="form-control" />
								</div>
								<div class="form-group">
									<label>New Password Again</label>
									<form:password path="repassword"
										placeholder="Enter New Password again Here.."
										cssClass="form-control" />
								</div>
							</div>
						</div>

					</div>








					<input type="submit" value="Save" class="btn btn-lg btn-primary">
				</div>
			</form:form>
		</div>
	</div>



</div>

<script>
	$('#myTabs a').click(function(e) {
		e.preventDefault()
		$(this).tab('show')
	})
	
	$(document)
			.ready(
					function() {
						$("#signinForm")
								.validate(
										{
											rules : {
												name : {
													required : true,
													minlength : 3
												},
												password : {
													required : true,
													minlength : 5
												},
												repassword : {
													required : true,
													minlength : 5,
													equalTo : "#password"
												},
												email : {
													required : true,
													email : true
												}
											},
											messages : {
												name : {
													required : "<span class='alert alert-danger error'>Please provide a name</span>",
													minlength : "<span class='alert alert-danger error'>Your name must be at least 3 characters long</span>"
												},
												password : {
													required : "<span class='alert alert-danger error'>Please provide a password</span>",
													minlength : "<span class='alert alert-danger error'>Your password must be at least 5 characters long</span>"
												},
												repassword : {
													required : "<span class='alert alert-danger error'>Please provide a password</span>",
													minlength : "<span class='alert alert-danger error'>Your password must be at least 5 characters long</span>",
													equalTo : "<span class='alert alert-danger error'>Please enter the same password as above</span>"
												},
												email : "<span class='alert alert-danger error'>Please enter a valid email address</span>"
											}
										}

								);

					});
</script>