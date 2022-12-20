package com.coffee.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * @author Nguyen Phuc Thinh
 */
@Entity
@Table(name = "Accounts")
public class Account implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -780969381080846273L;

	@Id
	@Column(name="employee_id")
	private Long id;
	
	@OneToOne
	@MapsId
	@JoinColumn(name="employee_id")
	private Employee employee;
	
	private String password;


	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}





	public Account(long id, Employee employee, String password) {
		super();
		this.id = id;
		this.employee = employee;
		this.password = password;
	}





	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}





	public void setEmployee(Employee employee) {
		this.employee = employee;
	}





	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}





	@Override
	public String toString() {
		return "Account [id=" + id + ", password=" + password + "]";
	}




	
}
