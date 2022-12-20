package com.coffee.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

/**
 * @author Nguyen Phuc Thinh
 */

@Entity
@Table(name = "Employee")
@NamedQuery(name = "getEmployee", query = "SELECT c FROM Employee c")
public class Employee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3611492365031685579L;
	@Id
	@Column(name = "employee_id")
	private long id;
	@Column(name = "employee_name", columnDefinition = "nvarchar(100)")
	private String fullName;
	private long phone;
	private String email;
	@Column(columnDefinition = "nvarchar(max)")
	private String address;

	private Date birthday;

	@ManyToOne
	@JoinColumn(name = "position_id")
	private Position position;

	@Column(name = "employee_image", columnDefinition = "varbinary(MAX)")
	private byte[] image;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "employee", fetch = FetchType.EAGER)
	@PrimaryKeyJoinColumn
	private Account account;

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(long id, String fullName, Long phone, String email, String address, Date birthday,
			Position position, byte[] image) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.birthday = birthday;
		this.position = position;
		this.image = image;

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

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", fullName=" + fullName + ", phone=" + phone + ", email=" + email + ", address="
				+ address + ", birthday=" + birthday + ", account=" + account + "]";
	}

}
