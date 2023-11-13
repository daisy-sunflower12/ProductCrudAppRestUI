<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.js"></script>
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.css" />
<script>
const CONTEXT = "<%= request.getContextPath()%>";
console.log(CONTEXT);
</script>

<script src="resources/js/login_management.js"></script>

</head>
<body><jsp:include page="base.jsp"></jsp:include>
	<%-- <%
	User u = (User) session.getAttribute("auth");
	%> --%>
	${msg}
	<div class="container mt-5">
		<div class="row">
			<div class="col-md-7 offset-md-2">
				<div class="card">
					<div class="card-body">
						<h2 class="text-center">Register here</h2>
						<form action="registrationValid" method="post">
							<div class="form-group">
								<label>First Name:</label> <input type="text" name="firstName"
									id="firstName" class="form-control" required="required" />
							</div>
							<div class="form-group">
								<label>Last Name:</label> <input type="text" name="lastName"
									id="lastName" class="form-control" required="required" />
							</div>
							<div class="form-group">
								<label>Email:</label> <input type="email" name="email"
									id="email" class="form-control" required="required" />
							</div>
							<div class="form-group">
								<label>Password:</label> <input type="password" name="password"
									id="password" class="form-control" required="required" />
							</div>
							<div class="form-group">
								<label>Mobile:</label> <input type="number" name="mobile"
									id="mobile" class="form-control" required="required" />
							</div>
							<div class="form-group">
								<label>DoB:</label> <input type="date" name="dob"
									id="dob" class="form-control" required="required" />
							</div>

							<div class="form-group mt-2">
								<label>Role Type :</label> 
								<select class="form-select" id="roleType"
									name="userRole" required="required" onchange="showDiv(this)">
									   <option value="0">Choose Role</option>									
								</select>
							</div>
							<div id="hidden_div" class="form-group mt-2">
								<label>Balance:</label> <input type="number" name="balance"
									id="balance" class="form-control mt-1" value="0" required />
							</div>

							<div class="text-center mt-3">
								<button type="button" class="btn btn-primary btn-success" id="save"
										onclick="addUser()">Submit</button> 
							</div>
						</form>
						<div class="text-center mt-3">
							<h5><a href="${pageContext.request.contextPath}/">Already a User..Login Here!!</a></h5>
						</div>
						<div class="text-center mt-3">
							<h5>
								<a href="${pageContext.request.contextPath}/logout">Logout</a>
							</h5>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function showDiv(select) {
			if (select.value == 1) {
				document.getElementById('hidden_div').style.display = "block";
			} else {
				document.getElementById('hidden_div').style.display = "none";
			}
		}
	</script>
</body>
</html>
<!-- <button type="button">Click Me!!</button>
</body>
</html> -->
