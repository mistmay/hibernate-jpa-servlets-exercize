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
<title>Pizzeria - Login</title>
</head>

<body>
	<jsp:include page="navbar.jsp" flush="true" />
	<main class="py-5 px-2 bg-light" style="min-height: calc(100vh - 70px)">
		<h1 class="fs-1 fw-bold mb-4 text-center">LOGIN</h1>
		<section class="container bg-white rounded border border-dark">
			<form action="LoginServlet" method="post"
				class="p-4 d-flex flex-column align-items-center gap-4">
				<div class="d-flex flex-column align-items-center gap-2">
					<label for="username">Username</label> <input type="text"
						id="username" name="username" placeholder="username" required
						minlength="3" class="rounded p-2">
				</div>
				<div class="d-flex flex-column align-items-center gap-2">
					<label for="password">Password</label> <input type="password"
						id="password" name="password" placeholder="password" required
						minlength="3" class="rounded p-2">
				</div>
				<button type="submit" class="btn btn-primary">Submit</button>
			</form>
		</section>
	</main>
</body>

</html>