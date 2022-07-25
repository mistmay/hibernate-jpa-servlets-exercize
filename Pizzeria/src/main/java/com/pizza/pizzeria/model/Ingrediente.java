package com.pizza.pizzeria.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Ingrediente")
public class Ingrediente {
	@Id
	@Column(name="id_ingrediente")
	private long id_ingrediente;
	
	@Column(name="name")
	private String name;
	
	@ManyToMany(mappedBy="ingredienti")
	private List<Pizza> pizze = new ArrayList<>();

	public long getId_ingrediente() {
		return id_ingrediente;
	}

	public void setId_ingrediente(long id_ingrediente) {
		this.id_ingrediente = id_ingrediente;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Pizza> getPizze() {
		return pizze;
	}

	public void setPizze(List<Pizza> pizze) {
		this.pizze = pizze;
	}
}
