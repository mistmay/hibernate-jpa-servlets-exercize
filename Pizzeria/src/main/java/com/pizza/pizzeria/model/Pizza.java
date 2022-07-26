package com.pizza.pizzeria.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Pizza")
public class Pizza {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_pizza")
	private int id_pizza;
	
	@Column(name="name")
	private String name;
	
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name="impasto_id", nullable=false)
	private Impasto impasto;
	
	@ManyToMany(targetEntity = Ingrediente.class, fetch = FetchType.EAGER)
	@JoinTable(name = "Pizza_Ingrediente", 
				joinColumns = { @JoinColumn(name = "id_pizza") }, 
				inverseJoinColumns = { @JoinColumn(name = "id_ingrediente") })
	private List<Ingrediente> ingredienti = new ArrayList<>();

	public int getId_pizza() {
		return id_pizza;
	}

	public void setId_pizza(int id_pizza) {
		this.id_pizza = id_pizza;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Impasto getImpasto() {
		return impasto;
	}

	public void setImpasto(Impasto impasto) {
		this.impasto = impasto;
	}

	public List<Ingrediente> getIngredienti() {
		return ingredienti;
	}

	public void setIngredienti(List<Ingrediente> ingredienti) {
		this.ingredienti = ingredienti;
	}
}
