<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Product</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.js"></script>
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.css" />
<script>
const CONTEXT = "<%=request.getContextPath()%>";
	console.log(CONTEXT);
</script>
<script src="resources/js/seller_management.js"></script>
</head>
<body>
	<jsp:include page="base.jsp"></jsp:include>
	<div class="container mt-5">
		<div class="row">
			<div class="col-md-7 offset-md-2">
				<div class="card">
					<div class="card-body">
						<h2 class="text-center">Add Product here</h2>
						<form action="addProduct" method="post" name="addProduct" enctype="multipart/form-data">
							<input type="hidden" class="form-control" id="id" name="id" required readonly/> 
							<div class="form-group">
								<label>Product Name:</label> <input type="text" id="prodName"
									class="form-control" name="prodName" required>
							</div>
							<div class="form-group mt-3">
								<label>Product Description:</label> <input type="text"
									id="prodDesc" class="form-control" name="prodDesc" required>
							</div>
							<div class="form-group mt-3">
								<label>Product Image:</label> <input type="file" id="prodImg"
									class="form-control" name="prodImg" onchange="uploadFile()" required>
							</div>
							<div class="form-group mt-3">
								<label>Product Sell Price:</label> <input type="text"
									id="prodSellPrice" class="form-control" name="prodSellPrice" required>
							</div>
							<div class="form-group mt-3">
								<label>Product Cost Price:</label> <input type="text"
									id="prodCostPrice" class="form-control" name="prodCostPrice" required>
							</div>
							<div class="form-group mt-3">
								<label>Stock Unit:</label> <input type="text" id="stockUnit"
									class="form-control" name="stockUnit" required>
							</div>

							<div class="form-group mt-3">
								<input type="number" class="form-control" name="user" id="user"
									value="${roleId}" readonly>
							</div>
							<div class="text-center mt-3">
								<button type="button" class="btn btn-primary btn-success"
									id="save" onclick="addProducts()">Add</button>
								<button type="button" class="btn btn-primary btn-primary"
									id="update" style="display: none">Update</button>

							</div>
						</form>
						<div class="text-center mt-3">
							<a href="viewproducts">View Product</a>
						</div>
						<div class="text-center mt-3">
							<a href="${pageContext.request.contextPath}/logout">Logout</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>