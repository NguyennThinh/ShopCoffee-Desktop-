package com.coffee.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * @author Nguyen Phuc Thinh
 */

@Entity
@Table(name = "BillDetails")
public class BillDetail implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7960667041367649368L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "detail_id")
	private int detail_id;

	@OneToOne()
	@JoinColumn(name = "product_id")
	private Product product;

	@Column(name = "amount_product")
	private int amount;

	@Column(name = "total_price")
	private double total;

	@ManyToOne()
	@JoinColumn(name = "bill_id")
	private Bill bill;

	public int getDetail_id() {
		return detail_id;
	}

	public void setDetail_id(int detail_id) {
		this.detail_id = detail_id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public BillDetail(Product product, int amount, double total) {
		super();

		this.product = product;
		this.amount = amount;
		this.total = total;
	}

	public BillDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		return Objects.hash(product);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BillDetail other = (BillDetail) obj;
		return Objects.equals(product, other.product);
	}

	@Override
	public String toString() {
		return "BillDetail [detail_id=" + detail_id + ", product=" + product + ", amount=" + amount + ", total=" + total
				+ ", bill=" + bill + "]";
	}



}
