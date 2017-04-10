<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="/WEB-INF/views/template/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery-1.11.3.min.js"></script>

<script type="text/javascript">
	function onLoad() {

		$("#password").keyup(checkPasswordsMatch);
		$("#confirmpass").keyup(checkPasswordsMatch);

		$("#details").submit(canSubmit);
	}

	function canSubmit() {
		var password = $("#password").val();
		var confirmpass = $("#confirmpass").val();

		if (password != confirmpass) {
			alert("Passwords do not match!")
			return false;
		} else {
			return true;
		}
	}

	function checkPasswordsMatch() {
		var password = $("#password").val();
		var confirmpass = $("#confirmpass").val();

		if (password.length > 3 || confirmpass.length > 3) {

			if (password == confirmpass) {
				$("#matchpass").text("Passwords match.");
				$("#matchpass").addClass("valid");
				$("#matchpass").css({"color": "green", "font-size": "100%"});
				$("#matchpass").removeClass("error");
			} else {
				$("#matchpass").text("Passwords do not match.");
				$("#matchpass").addClass("error");
				$("#matchpass").css({"color": "red", "font-size": "100%"});
				$("#matchpass").removeClass("valid");
			}
		}
	}

	$(document).ready(onLoad);
</script>
<title>Register Customer</title>
</head>
<body>

	<div class="container-wrapper">
		<div class="container">
			<div class="page-header">
				<h1>Register Customer</h1>

				<p class="lead">Please fill in your information below:</p>
			</div>
			<form:form id="details" action="${pageContext.request.contextPath}/register"
				method="post" commandName="customer">

				<h3>Basic Info</h3>

				<div class="form-group">
					<label for="name">Name</label>
					<form:errors path="customerName" cssStyle="color: #ff0000" />
					<br>
					<form:input path="customerName" id="name" class="form-Control" />
				</div>

				<div class="form-group">
					<label for="email">Email</label> <span style="color: #ff0000">${emailMsg}</span>
					<form:errors path="customerEmail" cssStyle="color: #ff0000" />
					<br>
					<form:input path="customerEmail" id="email" class="form-Control" />
				</div>

				<div class="form-group">
					<label for="phone">Phone</label><br>
					<form:input path="customerPhone" id="phone" class="form-Control" />
				</div>
				<!-- validation fails!!!!!!!! -->
				<div class="form-group">
					<label for="username">Username</label> <span style="color: #ff0000">${usernameMsg}</span>
					<form:errors path="username" cssStyle="color: #ff0000" />
					<br>
					<form:input path="username" id="username" class="form-Control" />
				</div>

				<div class="form-group">
					<label for="password">Password</label>
					<form:errors path="password" cssStyle="color: #ff0000" />
					<br>
					<form:password path="password" id="password" class="form-Control" />
				</div>

				<div class="form-group" >
					<label for="confirmpass">Confirm Password</label>
					<br>
					<input id="confirmpass" class="control" name="confirmpass" type="password">
					<!-- <input class="form-Control" name="confirmpass" type="password" /> -->
					<%-- <form:password path="password" id="confirmpass" class="form-Control" /> --%>
					<div id="matchpass"></div>
				</div>


				<h3>Billing Address</h3>

				<div class="form-group">
					<label for="billingStreet">Street Name</label><br>
					<form:input path="billingAddress.streetName" id="billingStreet"
						class="form-Control" />
				</div>

				<div class="form-group">
					<label for="billingApartmentNumber">Apartment Number</label><br>
					<form:input path="billingAddress.apartmentNumber"
						id="billingApartmentNumber" class="form-Control" />
				</div>

				<div class="form-group">
					<label for="billingCity">City</label><br>
					<form:input path="billingAddress.city" id="billingCity"
						class="form-Control" />
				</div>

				<div class="form-group">
					<label for="billingState">State</label><br>
					<form:input path="billingAddress.state" id="billingState"
						class="form-Control" />
				</div>

				<div class="form-group">
					<label for="billingCountry">Country</label><br>
					<form:input path="billingAddress.country" id="billingCountry"
						class="form-Control" />
				</div>

				<div class="form-group">
					<label for="billingZip">Zipcode</label><br>
					<form:input path="billingAddress.zipCode" id="billingZip"
						class="form-Control" />
				</div>


				<h3>Shipping Address</h3>

				<div class="form-group">
					<label for="shippingStreet">Street Name</label><br>
					<form:input path="shippingAddress.streetName" id="shippingStreet"
						class="form-Control" />
				</div>

				<div class="form-group">
					<label for="shippingApartmentNumber">Apartment Number</label><br>
					<form:input path="shippingAddress.apartmentNumber"
						id="shippingApartmentNumber" class="form-Control" />
				</div>

				<div class="form-group">
					<label for="shippingCity">City</label><br>
					<form:input path="shippingAddress.city" id="shippingCity"
						class="form-Control" />
				</div>

				<div class="form-group">
					<label for="shippingState">State</label><br>
					<form:input path="shippingAddress.state" id="shippingState"
						class="form-Control" />
				</div>

				<div class="form-group">
					<label for="shippingCountry">Country</label><br>
					<form:input path="shippingAddress.country" id="shippingCountry"
						class="form-Control" />
				</div>

				<div class="form-group">
					<label for="shippingZip">Zipcode</label><br>
					<form:input path="shippingAddress.zipCode" id="shippingZip"
						class="form-Control" />
				</div>

				<br>
				<br>
				<input type="submit" value="submit" class="btn btn-default">
				<a href="<c:url value="/" />" class="btn btn-default">Cancel</a>
			</form:form>
		</div>
	</div>
</body>
</html>
<%@include file="/WEB-INF/views/template/footer.jsp"%>