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
import com.pizza.pizzeria.model.User;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		RequestDispatcher dispatcher = null;
		User result = UserDao.login(username, password);
		if (result == null) {
			response.sendRedirect("error.jsp");
		} else {
			request.setAttribute("logged", result);
			List<Impasto> listaImpasti = PizzaDao.findAllImpasti();
			List<Ingrediente> listaIngredienti = PizzaDao.findAllIngredienti();
			request.setAttribute("listaImpasti", listaImpasti);
			request.setAttribute("listaIngredienti", listaIngredienti);
			dispatcher = request.getRequestDispatcher("welcome.jsp");
			dispatcher.forward(request, response);
		}
	}
}
