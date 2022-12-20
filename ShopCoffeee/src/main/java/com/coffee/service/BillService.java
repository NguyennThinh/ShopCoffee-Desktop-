package com.coffee.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.coffee.entity.Bill;
import com.coffee.entity.BillDetail;
import com.coffee.entity.CoffeeTable;
import com.coffee.entity.Customer;

/**
 * @author Nguyen Phuc Thinh
 */
public interface BillService {

	Bill findBillByTable(CoffeeTable table);
	
	boolean creatBill(Bill bill);
	
	boolean updateBillDetail(BillDetail billDetail);
	
	boolean addDetailToBill(Bill bill);
	
	boolean joinBill(Bill bill, List<Bill> arrBills);
	
	boolean removeBillDetail(BillDetail billDetail);
	
	boolean payBill(Bill bill, Customer customer);
	
	boolean addDetailToBillSplit(List<BillDetail> billDetails);
	
	boolean deleteBill(Bill bill);
	
	List<Bill> getBillForDay();
	
	List<Bill> getBillByBartend();
	
	List<Bill> getBillByShift(Date date);

	Map<Customer, Double> topCustomerByShift(Date date);
}
