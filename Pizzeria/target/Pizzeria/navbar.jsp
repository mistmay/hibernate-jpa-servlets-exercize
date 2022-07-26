<header style="height: 70px;" class="w-100">
	<nav
		class="w-100 h-100 py-2 px-4 d-flex justify-content-between align-items-center bg-primary">
		<h1 class="text-white">Pizzeria</h1>
		<%
		if (request.getAttribute("logged") != null) {
		%>
		<a href="LogoutServlet" class="text-white">Logout</a>
		<%
		}
		%>
	</nav>
</header>