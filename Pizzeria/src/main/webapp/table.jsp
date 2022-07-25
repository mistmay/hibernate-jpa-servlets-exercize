
<%
if (request.getAttribute("logged") != null) {
	com.pizza.pizzeria.model.User currentUser = (com.pizza.pizzeria.model.User) request.getAttribute("logged");
%>
<h2 class="fs-4 fw-bold text-center mb-2">
	Le pizze di
	<%=currentUser.getUsername()%></h2>
<%
if (currentUser.getPizze().size() > 0) {
%>
<table class="table text-center">
	<thead>
		<tr>
			<th scope="col">Nome</th>
			<th scope="col">Impasto</th>
			<th scope="col">Ingredienti</th>
		</tr>
	</thead>
	<tbody>
		<%
		for (com.pizza.pizzeria.model.Pizza currentPizza : currentUser.getPizze()) {
		%>
		<tr>
			<td><%=currentPizza.getName()%></td>
			<td><%=currentPizza.getImpasto().getName()%></td>
			<td>
				<%
				for (com.pizza.pizzeria.model.Ingrediente currentIngrediente : currentPizza.getIngredienti()) {
				%> <%=currentIngrediente.getName()%><br /> <%
 }
 %>
			</td>
		</tr>
		<%
		}
		%>
	</tbody>
</table>
<%
} else {
%>
<h3 class="fs-5 fw-bold text-center">Questo utente non ha ancora
	creato pizze</h3>
<%
}
}
%>