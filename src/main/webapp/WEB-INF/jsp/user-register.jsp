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
		<div class="alert alert-success">Registration successfully</div>
	</c:if>

	<h1 class="well">User Registration</h1>
	<div class="col-lg-12 well">
		<div class="row">
			<form:form id="signupForm" commandName="userform">
				<div class="col-sm-12">
					<div class="row">
						<div class="col-sm-6 form-group has-error">
							<label>Username</label> 
							<form:input path="username" placeholder="Enter Username Here.." cssClass="form-control" />
						</div>
						<form:errors path="username" cssClass="alert alert-danger error" />
					</div>
					<div class="row">
						<div class="col-sm-6 form-group has-error">
							<label>Password</label> 
							<form:password id="password" path="password" placeholder="Enter Password Here.." cssClass="form-control" />
						</div>
						<div class="col-sm-6 form-group has-error">
							<label>Re-Password</label> 
							<form:password path="repassword" placeholder="Re-Enter Password Here.." cssClass="form-control" />
						</div>
						<form:errors path="password" cssClass="alert alert-danger error" />
					</div>
					<div class="row">
						<div class="col-sm-6 form-group">
							<label>First Name</label> 
							<form:input path="firstName" placeholder="Enter First Name Here.." cssClass="form-control" />
						</div>
						<div class="col-sm-6 form-group">
							<label>Last Name</label> 
							<form:input path="lastName" placeholder="Enter Last Name Here.." cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label>Address</label>
						<form:textarea path="address" placeholder="Enter Address Here.." rows="3" class="form-control"/>
					</div>
					<div class="row">
						<div class="col-sm-4 form-group">
							<label>City</label> 
							<form:input path="city" placeholder="Enter City Name Here.." cssClass="form-control" />
						</div>
						<div class="col-sm-4 form-group">
							<label>State</label> 
							<form:input path="state" placeholder="Enter State Name Here.." cssClass="form-control" />
						</div>
						<div class="col-sm-4 form-group">
							<label>Zip</label> 
							<form:input path="zip" placeholder="Enter Zip Code Here.." cssClass="form-control" />
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6 form-group">
							<label>Title</label> 
							<form:input path="title" placeholder="Enter Designation Here.." cssClass="form-control" />
						</div>
						<div class="col-sm-6 form-group">
							<label>Company</label> 
							<form:input path="company" placeholder="Enter Company Name Here.." cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label>Phone Number</label> 
						<form:input path="phone" placeholder="Enter Phone Number Here.." cssClass="form-control" />
					</div>
					<div class="form-group">
						<label>Email Address</label> 
						<form:input path="email" placeholder="Enter Email Address Here.." cssClass="form-control" />
					</div>
					<form:errors path="email" cssClass="alert alert-danger error" />
					<div class="form-group">
						<label>Website</label> 
						<form:input path="website" placeholder="Enter Website Name Here.." cssClass="form-control" />
					</div>
					<input type="submit" value="Sign up" class="btn btn-lg btn-primary">
				</div>
			</form:form>
		</div>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function() {
	$("#signupForm").validate({
			rules: {
				name: {
					required : true,
					minlength : 3
				},
				password: {
					required: true,
					minlength: 5
				},
				repassword: {
					required: true,
					minlength: 5,
					equalTo: "#password"
				},
				email: {
					required: true,
					email: true
				},
				message: {
					required : true,
					minlength : 5
				}
			},
			messages: {
				name: {
					required: "<span class='alert alert-danger error'>Please provide a name</span>",
					minlength: "<span class='alert alert-danger error'>Your name must be at least 3 characters long</span>"
				},
				password: {
					required: "Please provide a password",
					minlength: "Your password must be at least 5 characters long"
				},
				repassword: {
					required: "Please provide a password",
					minlength: "Your password must be at least 5 characters long",
					equalTo: "Please enter the same password as above"
				},
				email: "<span class='alert alert-danger error'>Please enter a valid email address</span>",
				message: {
					required: "<span class='alert alert-danger error'>Please provide messages</span>",
					minlength: "<span class='alert alert-danger error'>Your message must be at least 5 characters long</span>"
				}
			}
		}		
	
	);
	
});
</script>