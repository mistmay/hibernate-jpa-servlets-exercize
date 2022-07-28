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
			entityManager.flush();
			entityManager.getTransaction().commit();
		    entityManager.close();
	}
	
	public static void updatePizza(String name, String impasto, String[] ingredienti, int userId, int idPizza) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		Impasto currentImpasto = entityManager.find(Impasto.class, Integer.parseInt(impasto));
		User user = entityManager.find(User.class, userId);
		List<Ingrediente> currentIngredienti = new ArrayList<>();
		for (String current : ingredienti) {
			currentIngredienti.add(entityManager.find(Ingrediente.class, Integer.parseInt(current)));
		}
		Pizza pizza = entityManager.find(Pizza.class, idPizza);
		pizza.setImpasto(currentImpasto);
		pizza.setIngredienti(currentIngredienti);
		pizza.setName(name);
		pizza.setUser(user);
		entityManager.getTransaction().commit();
	    entityManager.close();
	}
	
	public static void removePizza(int id) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		Pizza pizza = entityManager.find(Pizza.class, id);
		entityManager.remove(pizza);
		entityManager.getTransaction().commit();
	    entityManager.close();
	}
	
	public static void addImpasto(String name) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		Impasto impasto = new Impasto();
		impasto.setName(name);
		entityManager.persist(impasto);
		entityManager.getTransaction().commit();
	    entityManager.close();
	}
	
	public static void addIngrediente(String name) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		Ingrediente ingrediente = new Ingrediente();
		ingrediente.setName(name);
		entityManager.persist(ingrediente);
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
	
	public static Pizza findPizzaById(int id) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		Pizza pizzaTrovato = entityManager.find(Pizza.class, id);
		entityManager.refresh(pizzaTrovato);
		entityManager.getTransaction().commit();
		entityManager.close();
		return pizzaTrovato;
	}
}
