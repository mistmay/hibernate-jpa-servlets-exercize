<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.pizza.pizzeria.model.*, com.pizza.pizzeria.dao.PizzaDao, java.util.*" %>
 <%
    if(request.getAttribute("logged") == null) {
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
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <title>Pizzeria - Pizze</title>
</head>

<body>
    <jsp:include page="navbar.jsp" flush="true" />
    <main class="py-5 px-2 bg-light" style="min-height: calc(100vh - 70px)">
        <h1 class="fs-1 fw-bold mb-4 text-center">Crea La tua Pizza</h1>
        <section class="container bg-white rounded border border-dark mb-4">
            <form action="MakePizza" method="post" class="p-4 d-flex flex-column align-items-center gap-4">
                <input type="hidden" value="<%= utenteLoggato.getUser_id() %>" name="utenteLoggatoId"/>
                <div class="d-flex flex-column align-items-center gap-2">
                    <label for="name">Nome Pizza</label>
                    <input type="text" name="name" id="name" class="p-2 rounded" placeholder="Nome" required>
                </div>
				<fieldset>
					<legend>Tipo di Impasto:</legend>
					<ul>
						<%
						    for (Impasto impasto : listaImpasti) {
						%>

						<li class="d-flex align-items-center gap-2">
						    <input type="radio" name="impasto" value="<%=impasto.getImpasto_id()%>" required> 
						    <%=impasto.getName()%>
						</li>
						<%
						    }
						%>
					</ul>
				</fieldset>
				<div class="d-flex flex-column align-items-center gap-2">
                    <h3>Scegli Ingredienti:</h3>
                    <%
						    for (Ingrediente ingrediente : listaIngredienti) {
					%>
					<div class="d-flex align-items-center gap-1">
					    <input type="checkbox" name="ingrediente" value="<%= ingrediente.getId_ingrediente() %>" class="checkbox">
					    <%= ingrediente.getName() %>
					</div>
					<%
						    }
					%>
                </div>
                <button type="submit" class="btn btn-primary">Crea Pizza</button>
            </form>
        </section>
        <section class="container bg-white rounded border border-dark p-3">
            <jsp:include page="table.jsp" flush="true" />
        </section>
    </main>
    <script type="text/javascript">
        window.addEventListener('DOMContentLoaded', () => {
        	document.querySelector("button").addEventListener("click", (event) => {
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