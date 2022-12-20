package com.coffee.form;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.coffee.entity.Bill;
import com.coffee.entity.BillDetail;
import com.coffee.entity.Customer;
import com.coffee.entity.Employee;
import com.coffee.service.BillService;
import com.coffee.serviceimpl.BillServiceImpl;

/**
 * @author Nguyen Phuc Thinh
 */
public class Dialog_SplitBill extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8396801938493950860L;

	private Locale localeVN = Locale.getDefault();
	private NumberFormat moneyFormat = NumberFormat.getInstance(localeVN);

	private BillService billService;
	
	private Customer customer;
	private double total = 0;
	private double totalProduct = 0;
	
	
	private final JPanel contentPanel = new JPanel();
	private JScrollPane scrDetails;
	private JLabel lblNewLabel;
	private JTextField txtCustomer;
	private JLabel lblimTchLy;
	private JTextField txtPoint;
	private JLabel lblSinThoi;
	private JTextField txtPhone;
	private JLabel lblTinSnPhm;
	private JTextField txtProductPrice;
	private JLabel lblThnhTin;
	private JTextField txtTotal;
	private JButton btnDiscount;
	private JButton btnPay;
	private JButton btnFind;
	private JLabel lblThanhTon;
	private JComboBox<String> cbmPayment;
	private JTable table;
	private DefaultTableModel tableModel;

	/**
	 * Create the dialog.
	 */
	public Dialog_SplitBill(List<BillDetail> arrBillDetails, Bill bill, Form_Sell form_Sell,Employee employee) {
		setBounds(100, 100, 788, 415);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		scrDetails = new JScrollPane();
		scrDetails.setBounds(10, 10, 496, 362);
		String header[] = { "STT", "Tên sản phẩm", "Số lượng", "Tổng tiền" };
		tableModel = new DefaultTableModel(header, 0);
		table = new JTable(tableModel);
		scrDetails.setViewportView(table);

		for (int i = 0; i < arrBillDetails.size(); i++) {
			total = total + arrBillDetails.get(i).getTotal();
			totalProduct = totalProduct +(arrBillDetails.get(i).getProduct().getProductPrice() * arrBillDetails.get(i).getAmount());
			tableModel.addRow(new Object[] {(i+1), arrBillDetails.get(i).getProduct().getProductName(), arrBillDetails.get(i).getAmount(), moneyFormat.format(arrBillDetails.get(i).getTotal())+"d"});
		}

		contentPanel.add(scrDetails);

		lblNewLabel = new JLabel("Tên khách hàng");
		lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblNewLabel.setBounds(516, 10, 110, 25);
		contentPanel.add(lblNewLabel);

		txtCustomer = new JTextField();
		txtCustomer.setBounds(516, 34, 248, 25);
		contentPanel.add(txtCustomer);
		txtCustomer.setColumns(10);

		lblimTchLy = new JLabel("Điểm tích lũy");
		lblimTchLy.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblimTchLy.setBounds(516, 69, 110, 25);
		contentPanel.add(lblimTchLy);

		txtPoint = new JTextField();
		txtPoint.setColumns(10);
		txtPoint.setBounds(516, 93, 248, 25);
		contentPanel.add(txtPoint);

		lblSinThoi = new JLabel("Số điện thoại");
		lblSinThoi.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblSinThoi.setBounds(516, 128, 110, 25);
		contentPanel.add(lblSinThoi);

		txtPhone = new JTextField();
		txtPhone.setColumns(10);
		txtPhone.setBounds(516, 152, 194, 25);
		contentPanel.add(txtPhone);

		lblTinSnPhm = new JLabel("Tiền sản phẩm");
		
		lblTinSnPhm.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblTinSnPhm.setBounds(516, 187, 110, 25);
		contentPanel.add(lblTinSnPhm);

		txtProductPrice = new JTextField();
		txtProductPrice.setColumns(10);
		txtProductPrice.setText(moneyFormat.format(totalProduct)+"đ");
		txtProductPrice.setBounds(516, 211, 248, 25);
		contentPanel.add(txtProductPrice);

		lblThnhTin = new JLabel("Thành tiền");
		lblThnhTin.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblThnhTin.setBounds(516, 246, 110, 25);
		contentPanel.add(lblThnhTin);

		txtTotal = new JTextField();
		txtTotal.setColumns(10);
		txtTotal.setText(moneyFormat.format(total)+"đ");
		txtTotal.setBounds(516, 270, 248, 25);
		contentPanel.add(txtTotal);

		btnDiscount = new JButton("Đổi điểm");
		btnDiscount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (customer != null) {
					String point = JOptionPane.showInputDialog(null,
							"Bạn có " + customer.getPoint() + " điểm.\nBạn có muốn đổi điêm (1 điểm = 1đ) ",
							"Nhập điểm cần đổi");

					if(isNumeric(point)) {
						if(Integer.parseInt(point) <=  customer.getPoint()) {
							txtPoint.setText(customer.getPoint() - Integer.parseInt(point)+"");
							total  = total - Integer.parseInt(point);
							txtTotal.setText(moneyFormat.format(total)+"đ");
						}else {
							JOptionPane.showMessageDialog(null, "Giá trị nhập phải nhỏ hơn hoặc bằng điểm của khách hàng");
						}
					}else {
						JOptionPane.showMessageDialog(null, "Giá trị sai định dạng");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Chưa có khách hàng để sử dụng");
				}
			}
		});
		btnDiscount.setBounds(516, 347, 110, 25);
		contentPanel.add(btnDiscount);

		btnPay = new JButton("Thanh toán");
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				billService = new BillServiceImpl();
				Bill b = new Bill();
				
				b.setBill_id("HD"+System.currentTimeMillis());
			
				b.setBillNote("Tách từ hóa đơn "+bill.getBill_id());
				b.setBillStatus("Đã hoàn thành");
				b.setCreateDate(new Date());
				b.setCustomer(customer != null ?customer:null);
				b.setEmployee(employee);
				b.setPayDate(new Date());
				b.setPayment(cbmPayment.getSelectedItem().toString());
				b.setTotalPrice(total);
				
				if(billService.creatBill(b)) {
						for(BillDetail bd: arrBillDetails) {
							bd.setBill(b);
						}
			
					
					if(billService.addDetailToBillSplit(arrBillDetails)) {
						JOptionPane.showMessageDialog(null, "Thanh toán thành công");
						form_Sell.updateProductToTable();
						dispose();
					}else {
						JOptionPane.showMessageDialog(null, "Lỗi cập nhật chi tiết hóa đơn");
					}
				}else {
					JOptionPane.showMessageDialog(null, "Lỗi tạo hóa đơn");
				}
				
			}
		});
		btnPay.setBounds(654, 347, 110, 25);
		contentPanel.add(btnPay);

		btnFind = new JButton("Tìm");
		btnFind.setBounds(713, 152, 51, 25);
		contentPanel.add(btnFind);

		lblThanhTon = new JLabel("Thanh toán");
		lblThanhTon.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblThanhTon.setBounds(516, 305, 110, 25);
		contentPanel.add(lblThanhTon);

		cbmPayment = new JComboBox<String>();
		cbmPayment.addItem("Mã QR");
		cbmPayment.addItem("Tiền mặt");
		cbmPayment.setBounds(592, 305, 172, 25);
		contentPanel.add(cbmPayment);
	}
	public static boolean isNumeric(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
