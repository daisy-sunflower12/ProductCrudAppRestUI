<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.13.2/jquery-ui.min.js"
	integrity="sha512-57oZ/vW8ANMjR/KQ6Be9v/+/h6bq9/l3f0Oc7vn6qMqyhvPd1cvKBRWWpzu0QoneImqr2SkmO4MSqU+RpHom3Q=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.js"></script>
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.css" />

<script>
const CONTEXT = "<%=request.getContextPath()%>";
	console.log(CONTEXT);
</script>

<script src="resources/js/buyerproducts.js"></script>
</head>
<body>

	Buyer Id: ${bId}
	<br /> Total Balance:&#8377; ${buyerBal}
	<jsp:include page="base.jsp" />
	<div class="col-md-7">
		<div class="col-md-8">
			<div class="card" style="width: 208%; margin: 8%; margin-left: 24%">
				<div class="card-body">
					<h3 class="text-center text-success">List of Products</h3>
					<table id="buyerproductTable" class="table table-striped"></table>
				</div>
				<div class="container text-center">
					<a href="allPurchases">GoToCart!!</a>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="cartModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">cart Details</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div class="container mt-5">
						<div class="row">
							<div class="col-md-7 offset-md-2">
								<div class="card" style="width: 174%; margin-left: -30%">
									<div class="card-body">
										<h2 class="text-center">Cart Details here</h2>
										<label>Product Id:</label> <input type="number"
											class="form-control" id="cart_id" name="id" required readonly />
										<div class="form-group">
											<label>Product Name:</label> <input type="text"
												id="cart_prodName" class="form-control" name="prodName">
										</div>
										<div class="form-group">
											<label>Product Description:</label> <input type="text"
												id="cart_prodDesc" class="form-control" name="prodDesc">
										</div>
										<div class="form-group">
											<label>Sell Price:</label> <input type="text"
												id="cart_prodSellPrice" class="form-control"
												name="prodSellPrice">
										</div>
										<div class="form-group">
											<label>Cost Price:</label> <input type="text"
												id="cart_prodCostPrice" class="form-control"
												name="cart_prodCostPrice">
										</div>
										<div class="form-group">
											<label>Quantity:</label> <input type="number"
												class="form-control" id="cart_totalUnit" name="totalUnit">
										</div>

									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-bs-dismiss="modal">Close</button>
										<button type="button" id="buyBtn" class="btn btn-primary">Buy
											Now!!</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>