package com.coffee.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * @author Nguyen Phuc Thinh
 */
@Entity
@Table(name = "Tables")
public class CoffeeTable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "table_id")
	private int id;
	@Column(name = "table_name", columnDefinition = "nvarchar(100)")
	private String name;

	@Column(name = "status")
	private boolean status;

	@ManyToOne
	@JoinColumn(name = "area_id")
	private Area area;

	@Column(name = "open_time")
	private Date openTime;

	@OneToMany(mappedBy = "table", cascade = CascadeType.REMOVE)
	private Set<Bill> bills = new HashSet<>();

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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Date getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}

	public Set<Bill> getBills() {
		return bills;
	}

	public void setBills(Set<Bill> bills) {
		this.bills = bills;
	}

	public CoffeeTable(int id, String name, boolean status, Area area, Set<Bill> bills) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
		this.area = area;
		this.bills = bills;
	}

	public CoffeeTable() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "CoffeeTable [id=" + id + ", name=" + name + ", status=" + status + "]";
	}

}
