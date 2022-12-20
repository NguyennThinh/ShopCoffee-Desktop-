package com.coffee.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

/**
 * @author Nguyen Phuc Thinh
 */

@Entity
@Table(name = "Products")
@NamedQuery(name = "getProducts", query = "SELECT p FROM Product p")
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -368104232275299211L;

	@Id
	@Column(name = "product_id")
	private String product_id;

	@Column(name = "product_name", columnDefinition = "nvarchar(200)")
	private String productName;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	@Column(name = "product_price")
	private double productPrice;

	@Column( columnDefinition = "text")
	private String description;

	@Column(name = "product_status")
	private boolean productStatus;

	@Column(name = "product_image", columnDefinition = "varbinary(MAX)")
	private byte[] image;

	@ManyToOne()
	@JoinColumn(name = "discount_id")
	private Discount discount;

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



	public boolean isProductStatus() {
		return productStatus;
	}

	public void setProductStatus(boolean productStatus) {
		this.productStatus = productStatus;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Discount getDiscount() {
		return discount;
	}

	public void setDiscount(Discount discount) {
		this.discount = discount;
	}

	public Product(String product_id, String productName, Category category, double productPrice, String description,
		 boolean productStatus, byte[] image, Discount discount) {
		super();
		this.product_id = product_id;
		this.productName = productName;
		this.category = category;
		this.productPrice = productPrice;
		this.description = description;
		this.productStatus = productStatus;
		this.image = image;
		this.discount = discount;
	}

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		return Objects.hash(product_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(product_id, other.product_id);
	}

	@Override
	public String toString() {
		return "Product [product_id=" + product_id + ", productName=" + productName + ", category=" + category
				+ ", productPrice=" + productPrice + ", description=" + description + ", productStatus=" + productStatus
				+ ", discount=" + discount + "]";
	}



}
