package com.pizza.pizzeria.dao;

import java.util.ArrayList;
import java.util.List;

import com.pizza.pizzeria.model.User;
import com.pizza.pizzeria.util.JPAUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class UserDao {
	public static User login(String username, String password) {
		List<User> listaUser = new ArrayList<>();
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		TypedQuery<User> query = entityManager.createQuery(
				"select u from User u where u.username = :username and u.password = :password", User.class);
		query.setParameter("username", username);
		query.setParameter("password", password);
		listaUser = query.getResultList();
		entityManager.getTransaction().commit();
		entityManager.close();
		return listaUser.isEmpty() ? null : listaUser.get(0);
	}

	public static User findUserById(int user_id) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		User userTrovato = entityManager.find(User.class, user_id);
		entityManager.refresh(userTrovato);
		entityManager.getTransaction().commit();
		entityManager.close();
		return userTrovato;
	}
}
