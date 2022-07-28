<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="com.pizza.pizzeria.model.*, java.util.*"%>
<%
if (request.getAttribute("logged") == null) {
	response.sendRedirect("login.jsp");
} else {
	Pizza currentPizza = (Pizza) request.getAttribute("currentPizza");
	List<Impasto> listaImpasti = (List<Impasto>) request.getAttribute("listaImpasti");
	List<Ingrediente> listaIngredienti = (List<Ingrediente>) request.getAttribute("listaIngredienti");
	User utenteLoggato = (User) request.getAttribute("logged");
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
<title>Pizzeria - Update Pizza</title>
</head>
<body>
	<jsp:include page="navbar.jsp" flush="true" />
	<main class="py-5 px-2 bg-light" style="min-height: calc(100vh - 70px)">
		<h1 class="fs-1 fw-bold mb-4 text-center">Update Pizza</h1>
		<section class="container bg-white rounded border border-dark mb-4">
			<%
			if (listaImpasti.size() > 0 && listaIngredienti.size() > 0) {
			%>
			<form action="UpdatePizza" method="post"
				class="p-4 d-flex flex-column align-items-center gap-4">
				<h3 class="text-center fw-bold">Update Pizza:</h3>
				<input type="hidden" value="<%=utenteLoggato.getUser_id()%>"
					name="utenteLoggatoId" /> <input type="hidden"
					value="<%=currentPizza.getId_pizza()%>" name="idPizza">
				<div class="d-flex flex-column align-items-center gap-2">
					<label for="name">Nome Pizza</label> <input type="text" name="name"
						id="name" class="p-2 rounded" placeholder="Nome" required
						value="<%=currentPizza.getName()%>">
				</div>
				<table class="table text-center">
					<tr>
						<th class="fw-bold fs-5">Tipo di Impasto</th>
						<th class="fw-bold fs-5">Scegli Ingredienti</th>
					</tr>
					<tr>
						<td>
							<%
							for (Impasto impasto : listaImpasti) {
							%> <input type="radio" name="impasto"
							value="<%=impasto.getImpasto_id()%>" required
							<%if (impasto.getImpasto_id() == currentPizza.getImpasto().getImpasto_id()) {%>
							checked <%}%>> <%=impasto.getName()%> <br /> <%
 }
 %>
						</td>
						<td>
							<%
							for (Ingrediente ingrediente : listaIngredienti) {
							%> <input type="checkbox" name="ingrediente"
							value="<%=ingrediente.getId_ingrediente()%>" class="checkbox"
							<%boolean check = false;
for (Ingrediente currentPizzaIngrediente : currentPizza.getIngredienti()) {
	if (currentPizzaIngrediente.getId_ingrediente() == ingrediente.getId_ingrediente()) {
		check = true;
		break;
	}
}
if (check) {%>
							checked <%}%>> <%=ingrediente.getName()%> <br /> <%
 }
 %>
						</td>
					</tr>
				</table>
				<button type="submit" class="btn btn-primary" id="pizza-form">Update
					Pizza</button>
			</form>
			<%
			} else {
			%>
			<div
				class="p-4 d-flex flex-column justify-content-center align-items-center">
				<p class="text-center">Deve esserci almente un ingrediente ed un
					impasto per poter creare una pizza</p>
			</div>
			<%
			}
			%>
		</section>
	</main>
	<script type="text/javascript">
        window.addEventListener('DOMContentLoaded', () => {
        	document.querySelector("#pizza-form").addEventListener("click", (event) => {
            	let checked = false;
            	document.querySelectorAll(".checkbox").forEach(element => {
            		if(element.checked) {
            			checked = true;
            		}
            	});
            	if (!checked) {
            		event.preventDefault();
            		alert("Scegli almeno un ingrediente");
            	}
            });
        });
    </script>
</body>
</html>
<%
}
%>