package com.coffee.entity;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * @author Nguyen Phuc Thinh
 */

@Entity
@Table(name = "Categories")
@NamedQuery(name = "getCategories", query = "SELECT c FROM Category c")
public class Category implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2134428387554426874L;

	@Id
	@Column(name = "category_id")
	private String category_id;

	@Column(name = "category_name", columnDefinition = "nvarchar(200)", unique = true)
	private String categoryName;

	@OneToMany(mappedBy = "category", cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	private Set<Product> products;

	public String getCategory_id() {
		return category_id;
	}

	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public Category(String category_id, String categoryName, Set<Product> products) {
		super();
		this.category_id = category_id;
		this.categoryName = categoryName;
		this.products = products;
	}

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Category [category_id=" + category_id + ", categoryName=" + categoryName 
				+ "]";
	}

}
