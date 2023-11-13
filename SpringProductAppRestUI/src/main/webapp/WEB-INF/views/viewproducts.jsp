<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page isELIgnored="false"%>
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
<link rel="stylesheet" href="resources/css/myStyle.css"/>
<script>
const CONTEXT = "<%=request.getContextPath()%>";
	console.log(CONTEXT);
</script>

<!-- <script src="resources/js/seller_management.js"></script> -->
<script src="resources/js/viewproducts.js"></script>
</head>
<body>

	Seller Id: ${uId}
	<jsp:include page="base.jsp" />
	<div class="col-md-7">
		<div class="col-md-8">
			<div class="card" style="width: 208%; margin: 8%; margin-left: 24%">
				<div class="card-body">
					<h3 class="text-center text-success">List of Products</h3>
					<table id="productTable" class="table table-striped"></table>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal -->
	<div class="modal fade" id="productModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Product Details</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div class="container mt-5">
						<div class="row">
							<div class="col-md-7 offset-md-2">
								<div class="card" style="width:174%; margin-left: -30%">
									<div class="card-body">
										<h2 class="text-center">Edit Product here</h2>
										<input type="text" class="form-control" id="edit_id" name="id"
											required readonly />
										<div class="form-group">
											<label>Product Name:</label> <input type="text"
												id="edit_prodName" class="form-control" name="prodName">
										</div>
										<div class="form-group">
											<label>Product Description:</label> <input type="text"
												id="edit_prodDesc" class="form-control" name="prodDesc">
										</div>
										<div class="form-group">
											<label>Sell Price:</label> <input type="text"
												id="edit_prodSellPrice" class="form-control"
												name="prodSellPrice">
										</div>
										<div class="form-group">
											<label>Cost Price:</label> <input type="text"
												id="edit_prodCostPrice" class="form-control"
												name="prodCostPrice">
										</div>
										<div class="form-group">
											<label>Stock Unit:</label> <input type="text"
												id="edit_stockUnit" class="form-control" name="stockUnit">
										</div>
										<h4 id="stockUnit"></h4>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-bs-dismiss="modal">Close</button>
										<button type="button" id="update" class="btn btn-primary">Update!!</button>
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