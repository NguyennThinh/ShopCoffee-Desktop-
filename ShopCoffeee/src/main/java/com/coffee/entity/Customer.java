package com.coffee.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

/**
 * @author Nguyen Phuc Thinh
 */
@Entity
@Table(name = "Customers")
@NamedQueries(value = { 
		@NamedQuery(name = "select all customer", query = "SELECT c FROM Customer c"),
		@NamedQuery(name = "find Customer by phone", query = "SELECT c FROM Customer c WHERE c.phone =:phone")	
})
public class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8152602201622540275L;
	@Id
	@Column(name = "customer_id")
	private long id;
	
	@Column(name = "customer_name",columnDefinition = "nvarchar(100)")
	private String fullName;
	
	private long phone;
	
	private String email;
	
	@Column(columnDefinition = "text")
	private String address;
	
	@Column(name = "create_name")
	private Date createDate;

	private int point;

	public Customer(long id, String fullName, long phone, String email, String address, Date createDate, int point) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.createDate = createDate;
		this.point = point;
	}

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

}
