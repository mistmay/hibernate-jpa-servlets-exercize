package com.pizza.pizzeria.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pizza.pizzeria.dao.PizzaDao;
import com.pizza.pizzeria.dao.UserDao;

@WebServlet("/MakePizza")
public class MakePizza extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("welcome.jsp");
		String name = request.getParameter("name");
		String currentImpasto = request.getParameter("impasto");
		String[] ingredienti = request.getParameterValues("ingrediente");
		int idUtenteLoggato = Integer.parseInt(request.getParameter("utenteLoggatoId"));
		PizzaDao.makePizza(name, currentImpasto, ingredienti, idUtenteLoggato);
	    request.setAttribute("logged", UserDao.findUserById(idUtenteLoggato));
		rd.forward(request, response);
	}

}
