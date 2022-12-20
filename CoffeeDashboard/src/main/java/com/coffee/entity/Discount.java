package com.coffee.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
@Table(name = "Discounts")
@NamedQuery(name = "getDiscounts", query = "SELECT c FROM Discount c")
public class Discount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1192189301558080974L;

	@Id
	@Column(name = "discount_id")
	private String discountID;

	@Column(name = "discount_name", columnDefinition = "nvarchar(200)")
	private String discountName;

	private String discountType;

	@Column(name = "discount_value")
	private double discountValue;

	@OneToMany(mappedBy = "discount")
	private Set<Product> products = new HashSet<>();

	@Column(name = "date_start")
	private Date dateStart;

	@Column(name = "date_end")
	private Date dateEnd;

	@Column(name = "discount_status")
	private boolean status;

	public Discount() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Discount(String discountID, String discountName, String discountType, double discountValue,
			Set<Product> products, Date dateStart, Date dateEnd, boolean status) {
		super();
		this.discountID = discountID;
		this.discountName = discountName;
		this.discountType = discountType;
		this.discountValue = discountValue;
		this.products = products;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.status = status;
	}

	public String getDiscountID() {
		return discountID;
	}

	public void setDiscountID(String discountID) {
		this.discountID = discountID;
	}

	public String getDiscountName() {
		return discountName;
	}

	public void setDiscountName(String discountName) {
		this.discountName = discountName;
	}

	public String getDiscountType() {
		return discountType;
	}

	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}

	public double getDiscountValue() {
		return discountValue;
	}

	public void setDiscountValue(double discountValue) {
		this.discountValue = discountValue;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "discount [discountID=" + discountID + ", discountName=" + discountName + ", discountType="
				+ discountType + ", discountValue=" + discountValue + ", dateStart="
				+ dateStart + ", dateEnd=" + dateEnd + "]";
	}

}
