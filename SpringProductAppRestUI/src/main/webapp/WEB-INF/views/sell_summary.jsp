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

<script src="resources/js/buy_summary.js"></script><script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
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

<script src="resources/js/sell_summary.js"></script>
</head>
<body>
<jsp:include page="base.jsp" />
	<div class="col-md-7">
		<div class="col-md-8">
			<div class="card" style="width: 208%; margin: 8%; margin-left: 24%">
				<div class="card-body">
					<h3 class="text-center text-success">Buyer Summary</h3>
					<table id="sellsummaryTable" class="table table-striped"></table>
				</div>
			</div>

		</div>
	</div>
	<div class="container text-center">
		<h3 style="color: green;">We are Happy To Serve!!</h3>
	</div>
</body>
</html>