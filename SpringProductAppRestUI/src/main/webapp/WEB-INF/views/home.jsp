<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Page</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.js"></script>
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"
	integrity="sha512-3j3VU6WC5rPQB4Ld1jnLV7Kd5xr+cq9avvhwqzbH/taCRNURoeEpoPBK9pDyeukwSxwRPJ8fDgvYXd6SkaZ2TA=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	
	<script src="resources/js/login_management.js"></script>
	
<script type="text/javascript">
const CONTEXT = "<%=request.getContextPath()%>";
	console.log(CONTEXT);
	
</script>
</head>
<body>

<!-- onload="displayCookie()" -->
    
  <div id="myDiv" ></div> 
  <!-- <script type="text/javascript">
  document.getElementById("myDiv").innerHTML = localStorage.getItem("userInfo");
  
  </script> -->

	<h2>Welcome User</h2>

	<c:set value="${roles}" var="role" />
	<c:choose>
		<c:when test="${role == 1}">
			<h3>
				<a href="buyer">Go To Buyer</a>
			</h3>
		</c:when>
		<c:when test="${role == 2}">
			<h3>
				<a href="seller">Go To Seller</a>
			</h3>
		</c:when>
		<c:otherwise>
			<h3>
				<a href="/">Login/Register</a>
			</h3>
		</c:otherwise>
	</c:choose>
</body>
</html>