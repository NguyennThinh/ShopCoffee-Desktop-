package com.coffee.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * @author Nguyen Phuc Thinh
 */
@Entity
@Table(name = "Bills")
public class Bill implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3146196781111428428L;

	@Id
	@Column(name = "bill_id")
	private String bill_id;

	@OneToOne()
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@OneToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;

	@Column(name = "bill_status", columnDefinition = "nvarchar(100)")
	private String billStatus;

	@Column(name = "create_date")
	private Date createDate;

	@Column(name = "pay_date")
	private Date payDate;

	@OneToMany(mappedBy = "bill", cascade = { CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REMOVE })
	private Set<BillDetail> billDetails = new HashSet<>();

	@ManyToOne()
	@JoinColumn(name = "table_id")
	private CoffeeTable table;

	@Column(name = "bill_note", columnDefinition = "nvarchar(max)")
	private String billNote;

	@Column(name = "payment_method",columnDefinition = "nvarchar(100)")
	private String payment;

	@Column(name = "total_price")
	private double totalPrice;

	public String getBill_id() {
		return bill_id;
	}

	public void setBill_id(String bill_id) {
		this.bill_id = bill_id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getBillStatus() {
		return billStatus;
	}

	public void setBillStatus(String billStatus) {
		this.billStatus = billStatus;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public Set<BillDetail> getBillDetails() {
		return billDetails;
	}

	public void setBillDetails(Set<BillDetail> billDetails) {
		this.billDetails = billDetails;
	}

	public CoffeeTable getTable() {
		return table;
	}

	public void setTable(CoffeeTable table) {
		this.table = table;
	}

	public String getBillNote() {
		return billNote;
	}

	public void setBillNote(String billNote) {
		this.billNote = billNote;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Bill(String bill_id, Customer customer, Employee employee, String billStatus, Date createDate, Date payDate,
			Set<BillDetail> billDetails, CoffeeTable table, String billNote, String payment, double totalPrice) {
		super();
		this.bill_id = bill_id;
		this.customer = customer;
		this.employee = employee;
		this.billStatus = billStatus;
		this.createDate = createDate;
		this.payDate = payDate;
		this.billDetails = billDetails;
		this.table = table;
		this.billNote = billNote;
		this.payment = payment;
		this.totalPrice = totalPrice;
	}

	public Bill() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Bill [bill_id=" + bill_id + ", customer=" + customer + ", employee=" + employee + ", billStatus="
				+ billStatus + ", createDate=" + createDate + ", payDate=" + payDate 
				+ ", billNote=" + billNote + ", payment=" + payment + ", totalPrice=" + totalPrice + "]";
	}

}
