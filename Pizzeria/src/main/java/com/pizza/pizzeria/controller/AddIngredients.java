package com.pizza.pizzeria.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pizza.pizzeria.dao.PizzaDao;
import com.pizza.pizzeria.dao.UserDao;
import com.pizza.pizzeria.model.Impasto;
import com.pizza.pizzeria.model.Ingrediente;

@WebServlet("/AddIngredients")
public class AddIngredients extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("welcome.jsp");
		int idUtenteLoggato = Integer.parseInt(request.getParameter("userId"));
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		if (type.equals("impasto")) {
			PizzaDao.addImpasto(name);
		} else if (type.equals("ingrediente")) {
			PizzaDao.addIngrediente(name);
		}
		List<Impasto> listaImpasti = PizzaDao.findAllImpasti();
		List<Ingrediente> listaIngredienti = PizzaDao.findAllIngredienti();
		request.setAttribute("listaImpasti", listaImpasti);
		request.setAttribute("listaIngredienti", listaIngredienti);
		request.setAttribute("logged", UserDao.findUserById(idUtenteLoggato));
		rd.forward(request, response);
	}

}
