package com.coffee.entity;

import java.io.Serializable;
import java.util.List;

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
@Table(name = "Positions")
@NamedQuery(name = "getPositions", query = "SELECT p FROM Position p")
public class Position implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7514444693490171398L;

	@Id
	@Column(name = "position_id")
	private String position_id;

	@Column(name = "position_name", columnDefinition = "nvarchar(100)", unique = true)
	private String positionName;

	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "position")
	private List<Employee> employees;

	public Position() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Position(String position_id, String positionName, List<Employee> employees) {
		super();
		this.position_id = position_id;
		this.positionName = positionName;
		this.employees = employees;
	}

	public String getPosition_id() {
		return position_id;
	}

	public void setPosition_id(String position_id) {
		this.position_id = position_id;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	@Override
	public String toString() {
		return "Position [position_id=" + position_id + ", positionName=" + positionName 
				+ "]";
	}

}
