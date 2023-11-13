<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js" 
integrity="sha512-3j3VU6WC5rPQB4Ld1jnLV7Kd5xr+cq9avvhwqzbH/taCRNURoeEpoPBK9pDyeukwSxwRPJ8fDgvYXd6SkaZ2TA==" 
crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.js"></script>
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.css" />
<script>
const CONTEXT = "<%= request.getContextPath()%>";
console.log(CONTEXT);
</script>

<script src="resources/js/login_management.js"></script>
</head>
<body>
<jsp:include page="base.jsp"></jsp:include>
	<div class="container mt-5">
		<div class="row">
			<div class="col-md-7 offset-md-2">
				<div class="card">
					<div class="card-body">
						<h2 class="text-center">Login here</h2>
						<h4>${msg}</h4>
						<form action="validate" method="post">
							<div class="form-group">
								<label>Email:</label> <input type="email" class="form-control"
									id="email" name="email" required>
							</div>
							<div class="form-group mt-3">
								<label>Password:</label> <input type="password" id="password"
									class="form-control" name="password" required>
							</div>
							<div class="text-center mt-3">
								<button type="button" class="btn btn-primary btn-success"
									id="login" onclick="loginUser()">Submit</button>
							</div>
						</form>
						<div class="text-center mt-3">
							<a href="register">New User..Register Here!!</a>
						</div>
						<%-- <div class="text-center mt-3">
							<a href="${pageContext.request.contextPath}">Go To Home</a>
						</div> --%>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>