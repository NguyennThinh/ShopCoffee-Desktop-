package com.coffee.serviceimpl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.coffee.dao.BillDao;
import com.coffee.daoimpl.BillDaoImpl;
import com.coffee.entity.Bill;
import com.coffee.entity.BillDetail;
import com.coffee.entity.CoffeeTable;
import com.coffee.entity.Customer;
import com.coffee.service.BillService;

/**
 * @author Nguyen Phuc Thinh
 */
public class BillServiceImpl implements BillService {
	private BillDao dao;

	/**
	 * 
	 */
	public BillServiceImpl() {
		// TODO Auto-generated constructor stub
		dao = new BillDaoImpl();
	}

	@Override
	public Bill findBillByTable(CoffeeTable table) {
		// TODO Auto-generated method stub
		return dao.findBillByTable(table);
	}

	@Override
	public boolean creatBill(Bill bill) {
		// TODO Auto-generated method stub
		return dao.creatBill(bill);
	}

	@Override
	public boolean updateBillDetail(BillDetail billDetail) {
		// TODO Auto-generated method stub
		return dao.updateBillDetail(billDetail);
	}

	@Override
	public boolean addDetailToBill(Bill bill) {
		// TODO Auto-generated method stub
		return dao.addDetailToBill(bill);
	}

	@Override
	public boolean joinBill(Bill bill, List<Bill> arrBills) {
		// TODO Auto-generated method stub
		return dao.joinBill(bill,arrBills);
	}

	@Override
	public boolean removeBillDetail(BillDetail billDetail) {
		// TODO Auto-generated method stub
		return dao.removeBillDetail(billDetail);
	}

	@Override
	public boolean payBill(Bill bill, Customer customer) {
		// TODO Auto-generated method stub
		return dao.payBill(bill,customer);
	}

	@Override
	public boolean addDetailToBillSplit(List<BillDetail> billDetails) {
		// TODO Auto-generated method stub
		return dao.addDetailToBillSplit(billDetails);
	}

	@Override
	public boolean deleteBill(Bill bill) {
		// TODO Auto-generated method stub
		return dao.deleteBill(bill);
	}

	@Override
	public List<Bill> getBillForDay() {
		// TODO Auto-generated method stub
		return dao.getBillForDay();
	}

	@Override
	public List<Bill> getBillByBartend() {
		// TODO Auto-generated method stub
		return dao.getBillByBartend();
	}

	@Override
	public List<Bill> getBillByShift(Date date) {
		// TODO Auto-generated method stub
		return dao.getBillByShift(date);
	}

	@Override
	public Map<Customer, Double> topCustomerByShift(Date date) {
		// TODO Auto-generated method stub
		return dao.topCustomerByShift(date);
	}



}
