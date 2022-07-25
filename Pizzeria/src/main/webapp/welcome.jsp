<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="com.pizza.pizzeria.model.*, com.pizza.pizzeria.dao.PizzaDao, java.util.*"%>
<%
if (request.getAttribute("logged") == null) {
	response.sendRedirect("login.jsp");
} else {
	List<Impasto> listaImpasti = PizzaDao.findAllImpasti();
	List<Ingrediente> listaIngredienti = PizzaDao.findAllIngredienti();
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
<title>Pizzeria - Pizze</title>
</head>

<body>
	<jsp:include page="navbar.jsp" flush="true" />
	<main class="py-5 px-2 bg-light" style="min-height: calc(100vh - 70px)">
		<h1 class="fs-1 fw-bold mb-4 text-center">Crea La tua Pizza</h1>
		<section class="container bg-white rounded border border-dark mb-4">
			<div
				class="p-1 d-flex flex-column flex-md-row justify-content-evenly">
				<form action="AddIngredients" method="post"
					class="p-4 d-flex flex-column align-items-center gap-2 text-center">
					<input type="hidden" value="<%=utenteLoggato.getUser_id()%>"
						name="userId" />
					<fieldset class="d-flex flex-column align-items-center">
						<legend class="fw-bold">Impasto o Ingrediente:</legend>
						<ul class="p-0">
							<li class="d-flex align-items-center gap-2"><input
								type="radio" name="type" value="impasto" required>
								Impasto</li>
							<li class="d-flex align-items-center gap-2"><input
								type="radio" name="type" value="ingrediente">
								Ingrediente</li>
						</ul>
					</fieldset>
					<div class="d-flex flex-column align-items-center gap-2">
						<label for="name">Nome</label> <input type="text" name="name"
							id="name" class="p-2 rounded" placeholder="Nome" required>
					</div>
					<button type="submit" class="btn btn-primary">Aggiungi</button>
				</form>
				<%
				if (listaImpasti.size() > 0 && listaIngredienti.size() > 0) {
				%>
				<form action="MakePizza" method="post"
					class="p-4 d-flex flex-column align-items-center gap-4">
					<h3 class="text-center fw-bold">Crea Pizza:</h3>
					<input type="hidden" value="<%=utenteLoggato.getUser_id()%>"
						name="utenteLoggatoId" />
					<div class="d-flex flex-column align-items-center gap-2">
						<label for="name">Nome Pizza</label> <input type="text"
							name="name" id="name" class="p-2 rounded" placeholder="Nome"
							required>
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
								value="<%=impasto.getImpasto_id()%>" required> <%=impasto.getName()%>
								<br /> <%
 }
 %>
							</td>
							<td>
								<%
								for (Ingrediente ingrediente : listaIngredienti) {
								%> <input type="checkbox" name="ingrediente"
								value="<%=ingrediente.getId_ingrediente()%>" class="checkbox">
								<%=ingrediente.getName()%> <br /> <%
 }
 %>
							</td>
						</tr>
					</table>
					<button type="submit" class="btn btn-primary" id="pizza-form">Crea
						Pizza</button>
				</form>
				<%
				} else {
				%>
				<div
					class="p-4 d-flex flex-column justify-content-center align-items-center">
					<p class="text-center">Deve esserci almente un ingrediente ed
						un impasto per poter creare una pizza</p>
				</div>
				<%
				}
				%>
			</div>
		</section>
		<%
		if (listaImpasti.size() > 0 && listaIngredienti.size() > 0) {
		%>
		<section class="container bg-white rounded border border-dark p-3">
			<jsp:include page="table.jsp" flush="true" />
		</section>
		<%
		}
		%>
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