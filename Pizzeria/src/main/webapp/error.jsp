<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
if (request.getAttribute("logged") != null) {
	response.sendRedirect("welcome.jsp");
}
%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="author" content="Giuseppe Marchesiello">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor"
	crossorigin="anonymous">
<title>Pizzeria - Error</title>
</head>

<body>
	<jsp:include page="navbar.jsp" flush="true" />
	<main
		class="py-5 px-2 bg-light d-flex flex-column align-items-center gap-3"
		style="min-height: calc(100vh - 70px)">
		<p class="text-center">Error: Wrong Username or Password</p>
		<a href="login.jsp" class="btn btn-primary">Try Again</a>
	</main>
</body>

</html>