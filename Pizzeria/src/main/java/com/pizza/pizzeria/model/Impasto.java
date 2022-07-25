package com.pizza.pizzeria.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Impasto")
public class Impasto {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="impasto_id")
	private int impasto_id;
	
	@Column(name="name")
	private String name;
	
	@OneToMany(mappedBy="impasto")
	private List<Pizza> pizze = new ArrayList<>();

	public int getImpasto_id() {
		return impasto_id;
	}

	public void setImpasto_id(int impasto_id) {
		this.impasto_id = impasto_id;
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
