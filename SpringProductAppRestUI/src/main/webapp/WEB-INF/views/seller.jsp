<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Seller</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.js"></script>
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.css" />

<script type="text/javascript">
const CONTEXT = "<%=request.getContextPath()%>";
	console.log(CONTEXT);
</script>
<script src="resources/js/seller_management.js"></script>

<style>
a {
	text-decoration: none;
	color: azure;
}

.effect{
	background-image: linear-gradient(to right, rgb(0, 51, 128),
		rgb(128, 128, 128));
	text-align: center;
	height: 35vh;
}

</style>
</head>
<body>

	<div class="container effect">

		<h3>
			<a href="addProduct">Add Product</a>
		</h3>
		<h3>
			<a href="allProducts">View Product</a>
		</h3>
		<h3>
			<a href="sellSummary">View Sale Summary!!</a>
		</h3>

	</div>
</body>
</html>