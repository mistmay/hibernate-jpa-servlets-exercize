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
import com.pizza.pizzeria.model.Pizza;
import com.pizza.pizzeria.model.User;

@WebServlet("/ToUpdatePage")
public class ToUpdatePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("update-form.jsp");
		int idUtenteLoggato = Integer.parseInt(request.getParameter("userId"));
		int idCurrentPizza = Integer.parseInt(request.getParameter("currentPizzaId"));
		User user = UserDao.findUserById(idUtenteLoggato);
		Pizza pizza = PizzaDao.findPizzaById(idCurrentPizza);
	    request.setAttribute("logged", user);
	    request.setAttribute("currentPizza", pizza);
		List<Impasto> listaImpasti = PizzaDao.findAllImpasti();
		List<Ingrediente> listaIngredienti = PizzaDao.findAllIngredienti();
		request.setAttribute("listaImpasti", listaImpasti);
		request.setAttribute("listaIngredienti", listaIngredienti);
		dispatcher.forward(request, response);
	}

}
