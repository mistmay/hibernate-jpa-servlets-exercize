package com.pizza.pizzeria.dao;

import java.util.ArrayList;
import java.util.List;

import com.pizza.pizzeria.model.Impasto;
import com.pizza.pizzeria.model.Ingrediente;
import com.pizza.pizzeria.model.Pizza;
import com.pizza.pizzeria.model.User;
import com.pizza.pizzeria.util.JPAUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class PizzaDao {
	public static void makePizza(String name, String impasto, String[] ingredienti, int userId) {
			EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
			entityManager.getTransaction().begin();

			Impasto currentImpasto = entityManager.find(Impasto.class, Integer.parseInt(impasto));
			User user = entityManager.find(User.class, userId);
			List<Ingrediente> currentIngredienti = new ArrayList<>();
			Pizza pizza = new Pizza();
			pizza.setName(name);
			pizza.setUser(user);
			pizza.setImpasto(currentImpasto);

			for (String current : ingredienti) {
				currentIngredienti.add(entityManager.find(Ingrediente.class, Integer.parseInt(current)));
			}

			pizza.setIngredienti(currentIngredienti);
			entityManager.persist(pizza);
			entityManager.getTransaction().commit();
		    entityManager.close();
	}

	public static List<Impasto> findAllImpasti() {
		List<Impasto> listaImpasti = new ArrayList<>();
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		TypedQuery<Impasto> query = entityManager.createQuery("select i from Impasto i", Impasto.class);
		listaImpasti = query.getResultList();
		entityManager.getTransaction().commit();
	    entityManager.close();
		return listaImpasti;
	}
	
	public static List<Ingrediente> findAllIngredienti() {
		List<Ingrediente> listaIngredienti = new ArrayList<>();
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		TypedQuery<Ingrediente> query = entityManager.createQuery("select i from Ingrediente i", Ingrediente.class);
		listaIngredienti = query.getResultList();
		entityManager.getTransaction().commit();
	    entityManager.close();
		return listaIngredienti;
	}
}
