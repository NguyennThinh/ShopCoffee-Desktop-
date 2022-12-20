package com.coffee.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * @author Nguyen Phuc Thinh
 */

@Entity
@Table(name = "Areas")

public class Area {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "area_id")
	private int id;
	
	@Column(name = "area_name", columnDefinition = "nvarchar(100)")
	private String name;
	
	@OneToMany(mappedBy = "area", cascade = CascadeType.ALL, fetch =  FetchType.EAGER)
	private List<CoffeeTable> table;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<CoffeeTable> getTable() {
		return table;
	}

	public void setTable(List<CoffeeTable> table) {
		this.table = table;
	}

	public Area(int id, String name, List<CoffeeTable> table) {
		super();
		this.id = id;
		this.name = name;
		this.table = table;
	}

	public Area() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Area [id=" + id + ", name=" + name + ", table=" + table + "]";
	}


	
	
}
