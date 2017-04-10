<!-- Import spring text -->
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@include file="/WEB-INF/views/template/header.jsp"%>


<div class="container-wrapper">
	<div class="container">
		<div class="page-header">
			<h1>Product Detail</h1>

			<p class="lead">Here is the detail information of the product</p>
		</div>

		<!-- Grid system from Bootstrap -->
		<div class="container" ng-app="cartApp">
			<div class="row">
				<div class="col-md-5">
					<img
						src="<c:url value="/resources/images/${product.productId}.png" /> "
						alt="image" style="width: 100%" />
				</div>
				<div class="col-md-5">
					<h3>${product.productName}</h3>
					<p>${product.productDescription}</p>
					<p>
						<strong>Manufacture</strong> : ${product.productManufacture}
					</p>
					<p>
						<strong>Category</strong> : ${product.productCategory}
					</p>
					<p>
						<strong>Condition</strong> : ${product.productCondition}
					</p>
					<p>${product.productPrice}USD</p>

					<br>

					<c:set var="role" scope="page" value="${param.role}" />
					<c:set var="url" scope="page" value="/product/productList" />
					<!-- if the user is admin we change the back page for the following "back" button -->
					<c:if test="${role='admin'}">
						<c:set var="url" scope="page" value="/admin/productInventory" />
					</c:if>

					<p ng-controller="cartCtrl">
						<a href="<c:url value="${url}" />" class="btn btn-default">Back</a>
						<!-- we use the angular funtction (#)  -->
						<a href="#" class="btn btn-warning btn-large"
							ng-click="addToCart('${product.productId}')"><span
							class="glyphicon glyphicon-shopping-cart"></span>Order Now</a> <a
							href="<spring:url value="/customer/cart" />" class="btn btn-default"><span
							class="glyphicon glyphicon-hand-right"></span>View Cart</a>
					</p>
				</div>
			</div>
		</div>

	</div>
</div>

<script src="<c:url value="/resources/js/controller.js" /> "></script>
<%@include file="/WEB-INF/views/template/footer.jsp"%>