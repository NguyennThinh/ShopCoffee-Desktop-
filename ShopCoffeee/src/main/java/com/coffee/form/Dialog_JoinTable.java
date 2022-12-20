package com.coffee.form;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

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
import com.coffee.entity.CoffeeTable;
import com.coffee.entity.Customer;
import com.coffee.entity.Employee;
import com.coffee.service.BillService;
import com.coffee.service.CoffeeTableService;
import com.coffee.service.CustomerService;
import com.coffee.serviceimpl.BillServiceImpl;
import com.coffee.serviceimpl.CoffeeTableServiceImpl;
import com.coffee.serviceimpl.CustomerServiceImpl;

/**
 * @author Nguyen Phuc Thinh
 */
public class Dialog_JoinTable extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 417168001901563209L;
	private Locale localeVN = Locale.getDefault();
	private NumberFormat moneyFormat = NumberFormat.getInstance(localeVN);
	private BillService billService;
	private CoffeeTableService tableService;
	private CustomerService customerService;

	private double Total = 0;
	private List<CoffeeTable> arrCoffeeTables;
	private Set<BillDetail> arrBillDetails;

	private Customer customer;

	private final JPanel contentPanel = new JPanel();
	private JScrollPane scrListTable;
	private JButton btnJoin;
	private JTextField txtTable;
	private JLabel lblNewLabel;
	private JLabel lblChnKhuVc;
	private JComboBox<String> cmbArea;
	private JTable table;
	private DefaultTableModel modelTable;
	private JLabel lblNewLabel_1;
	private JTextField txtName;
	private JLabel lblimTchLy;
	private JTextField txtPoint;
	private JLabel lblSinThoi;
	private JTextField txtPhone;
	private JButton btnFind;
	private JLabel lblTinSnPhm;
	private JTextField txtProductPrice;
	private JLabel lblThnhTin;
	private JTextField txtTotal;
	private JComboBox<String> cbmPayment;
	private JLabel lblThanhTon;
	private JButton btnDiscount;
	double total = 0;
	private JScrollPane scrDetail;
	private JTable tableDetail;
	private DefaultTableModel detailModel;

	/**
	 * Create the dialog.
	 */
	public Dialog_JoinTable(CoffeeTable coffeeTable, Bill bill, Form_Sell form_Sell, Employee employee) {
		arrBillDetails = new HashSet<>();

		bill.getBillDetails().forEach(b -> {
			arrBillDetails.add(b);
			Total = Total + b.getTotal();
			total = total + b.getTotal();
		});

		billService = new BillServiceImpl();
		setTitle("Gôp bàn và thanh toán");
		billService = new BillServiceImpl();
		tableService = new CoffeeTableServiceImpl();
		customerService = new CustomerServiceImpl();

		setBounds(100, 100, 862, 710);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		scrListTable = new JScrollPane();
		String[] header = { "Mã bàn", "Khu vực", "Bàn", "Trạng thái", "Gộp" };
		modelTable = new DefaultTableModel(header, 0) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				// TODO Auto-generated method stub
				switch (columnIndex) {
				case 0:
					return Object.class;
				case 1:
					return Object.class;
				case 2:
					return Object.class;
				case 3:
					return Object.class;
				default:
					return Boolean.class;
				}
			}
		};
		table = new JTable(modelTable);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Total = 0;
				int row = table.getSelectedRow();
				for (int i = 0; i < table.getRowCount(); i++) {
					if(row == i) {
						if ((boolean) table.getValueAt(i, 4) && table.getValueAt(i, 4) != null) {
							CoffeeTable coffeeTable = arrCoffeeTables.get(row);
						
							Bill billnew = billService.findBillByTable(coffeeTable);

							for (BillDetail bd : billnew.getBillDetails()) {
						
								if(arrBillDetails.contains(bd)) {
									BillDetail billDetail = new ArrayList<>(arrBillDetails).get(new ArrayList<>(arrBillDetails).indexOf(bd));
									billDetail.setAmount(bd.getAmount()+billDetail.getAmount());
									billDetail.setTotal(bd.getTotal()+billDetail.getTotal());
								}else {
									arrBillDetails.add(bd);
								}
							
						}
						
			
						} else {
							CoffeeTable coffeeTable = arrCoffeeTables.get(row);
							
							Bill billnew = billService.findBillByTable(coffeeTable);
							for (BillDetail bd : billnew.getBillDetails()) {
								
								if(arrBillDetails.contains(bd)) {
									BillDetail billDetail = new ArrayList<>(arrBillDetails).get(new ArrayList<>(arrBillDetails).indexOf(bd));
										if(billDetail.getAmount()-bd.getAmount()>0) {
			
											billDetail.setAmount(billDetail.getAmount()-bd.getAmount());
											billDetail.setTotal(billDetail.getTotal()-bd.getTotal());
										}else {
											arrBillDetails.remove(billDetail);
										}
								}
							
						}
			
					
						}

					}
				}
				for(BillDetail bd: arrBillDetails) {
					Total = Total + bd.getTotal();
				
				}
			
				txtTotal.setText(moneyFormat.format(Total));
				detailModel.setRowCount(0);
				for (int j = 0; j < arrBillDetails.size(); j++) {
					detailModel
							.addRow(new Object[] { new ArrayList<>(arrBillDetails).get(j).getProduct().getProductName(),
									new ArrayList<>(arrBillDetails).get(j).getAmount(),
									new ArrayList<>(arrBillDetails).get(j).getTotal() });
				}
				
			}
		});
		scrListTable.setViewportView(table);

		scrListTable.setBounds(10, 46, 570, 327);
		contentPanel.add(scrListTable);

		btnJoin = new JButton("Thanh toán");
		btnJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (joinTable(bill, form_Sell, employee)) {
					form_Sell.getAreaAndTable();
					dispose();
				}
			}
		});
		btnJoin.setFont(new Font("SansSerif", Font.PLAIN, 10));
		btnJoin.setBounds(753, 348, 85, 25);
		contentPanel.add(btnJoin);

		txtTable = new JTextField();
		txtTable.setBounds(105, 10, 117, 25);
		contentPanel.add(txtTable);
		txtTable.setColumns(10);

		lblNewLabel = new JLabel("Nhập tên bàn");
		lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 10, 85, 25);
		contentPanel.add(lblNewLabel);

		lblChnKhuVc = new JLabel("Chọn khu vực");
		lblChnKhuVc.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblChnKhuVc.setBounds(253, 10, 85, 25);
		contentPanel.add(lblChnKhuVc);

		cmbArea = new JComboBox<String>();
		cmbArea.setFont(new Font("SansSerif", Font.PLAIN, 12));
		cmbArea.setBounds(348, 10, 150, 25);
		contentPanel.add(cmbArea);

		lblNewLabel_1 = new JLabel("Tên khách hàng");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(590, 10, 110, 25);
		contentPanel.add(lblNewLabel_1);

		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(590, 34, 248, 25);
		contentPanel.add(txtName);

		lblimTchLy = new JLabel("Điểm tích lũy");
		lblimTchLy.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblimTchLy.setBounds(590, 69, 110, 25);
		contentPanel.add(lblimTchLy);

		txtPoint = new JTextField();
		txtPoint.setColumns(10);
		txtPoint.setBounds(590, 93, 248, 25);
		contentPanel.add(txtPoint);

		lblSinThoi = new JLabel("Số điện thoại");
		lblSinThoi.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblSinThoi.setBounds(590, 128, 110, 25);
		contentPanel.add(lblSinThoi);

		txtPhone = new JTextField();
		txtPhone.setColumns(10);
		txtPhone.setBounds(590, 152, 194, 25);
		contentPanel.add(txtPhone);

		btnFind = new JButton("Tìm");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				customer = customerService.findByPhone(Long.parseLong(txtPhone.getText()));

				if (customer != null) {
					txtName.setText(customer.getFullName());
					txtPhone.setText(String.valueOf(customer.getPoint()));
				} else {
					JOptionPane.showMessageDialog(null, "Không tìm thấy khách hàng");
				}
			}
		});
		btnFind.setBounds(787, 152, 51, 25);
		contentPanel.add(btnFind);

		lblTinSnPhm = new JLabel("Tiền sản phẩm");
		lblTinSnPhm.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblTinSnPhm.setBounds(590, 187, 110, 25);
		contentPanel.add(lblTinSnPhm);

		txtProductPrice = new JTextField();
		txtProductPrice.setColumns(10);
		txtProductPrice.setBounds(590, 211, 248, 25);
		contentPanel.add(txtProductPrice);

		lblThnhTin = new JLabel("Thành tiền");
		lblThnhTin.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblThnhTin.setBounds(590, 246, 110, 25);
		contentPanel.add(lblThnhTin);

		txtTotal = new JTextField();
		txtTotal.setColumns(10);
		txtTotal.setBounds(590, 270, 248, 25);
		contentPanel.add(txtTotal);

		cbmPayment = new JComboBox<String>();
		cbmPayment.addItem("Mã QR");
		cbmPayment.addItem("Tiền mặt");
		cbmPayment.setBounds(666, 305, 172, 25);
		contentPanel.add(cbmPayment);

		lblThanhTon = new JLabel("Thanh toán");
		lblThanhTon.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblThanhTon.setBounds(590, 305, 110, 25);
		contentPanel.add(lblThanhTon);

		btnDiscount = new JButton("Đổi điểm");
		btnDiscount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (customer != null) {
					String point = JOptionPane.showInputDialog(null,
							"Bạn có " + customer.getPoint() + " điểm.\nBạn có muốn đổi điêm (1 điểm = 1đ) ",
							"Nhập điểm cần đổi");

					if (isNumeric(point)) {
						if (Integer.parseInt(point) <= customer.getPoint()) {
							txtPoint.setText(customer.getPoint() - Integer.parseInt(point) + "");
							total = total - Integer.parseInt(point);
							txtTotal.setText(moneyFormat.format(total) + "đ");
						} else {
							JOptionPane.showMessageDialog(null,
									"Giá trị nhập phải nhỏ hơn hoặc bằng điểm của khách hàng");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Giá trị sai định dạng");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Chưa có khách hàng để sử dụng");
				}
			}
		});
		btnDiscount.setBounds(590, 346, 110, 25);
		contentPanel.add(btnDiscount);

		scrDetail = new JScrollPane();
		scrDetail.setBounds(10, 384, 570, 289);
		String headers[] = { "Tên sản phẩm", "Số lượng", "Thành tiền" };
		detailModel = new DefaultTableModel(headers, 0);
		tableDetail = new JTable(detailModel);
		scrDetail.setViewportView(tableDetail);

		contentPanel.add(scrDetail);
		txtTotal.setText(moneyFormat.format(Total));
		for (int j = 0; j < arrBillDetails.size(); j++) {
			detailModel.addRow(new Object[] { new ArrayList<>(arrBillDetails).get(j).getProduct().getProductName(),
					new ArrayList<>(arrBillDetails).get(j).getAmount(),
					new ArrayList<>(arrBillDetails).get(j).getTotal() });
		}

		listAllTable(coffeeTable);
	}

	private void listAllTable(CoffeeTable coffeeTable) {

		arrCoffeeTables = tableService.getTableActive();

		modelTable.setRowCount(0);
		for (int i = 0; i < arrCoffeeTables.size(); i++) {
			modelTable.addRow(
					new Object[] { arrCoffeeTables.get(i).getId(), arrCoffeeTables.get(i).getArea().getName(),
							arrCoffeeTables.get(i).getName(), "Đang hoạt động",arrCoffeeTables.get(i).getId() != coffeeTable.getId()? false:true });
		}

	}

	private boolean joinTable(Bill bill, Form_Sell form_Sell, Employee employee) {
		
		List<Bill> arrBills = new ArrayList<>();
	//	List<BillDetail> stringsList = new ArrayList<>(bill.getBillDetails());

		for (int i = 0; i < table.getRowCount(); i++) {
			Boolean value = (Boolean) table.getValueAt(i, 4);
			int id = Integer.parseInt(table.getValueAt(i, 0).toString());
			if (value != null && value) {
				CoffeeTable coffeeTable = arrCoffeeTables.stream().filter(s -> id == s.getId()).findFirst()
						.orElse(null);

				Bill b = billService.findBillByTable(coffeeTable);

				arrBills.add(b);

//				for (BillDetail bd : b.getBillDetails()) {
//					if (stringsList.contains(bd)) {
//						bill.setBillNote("Hóa đơn gộp");
//						BillDetail detail = stringsList.get(stringsList.indexOf(bd));
//
//						detail.setAmount(detail.getAmount() + bd.getAmount());
//						detail.setTotal(detail.getTotal() + bd.getTotal());
//
//						bill.getBillDetails().remove(detail);
//						bill.getBillDetails().add(detail);
//						total = total + detail.getTotal();
//					} else {
//						total = total + bd.getTotal();
//						bill.getBillDetails().add(bd);
//					}
//				}

			}
		}
		if (customer != null) {
			bill.setCustomer(customer);
		}
		bill.setBillNote("Hóa đơn gộp");
		bill.setBillDetails(arrBillDetails);
		bill.setBillStatus("Đã hoàn thành");
		bill.setPayment(cbmPayment.getSelectedItem().toString());
		bill.setPayDate(new Date());
		bill.setTotalPrice(Total);
		bill.setEmployee(employee);
		if (billService.joinBill(bill, arrBills)) {
			JOptionPane.showMessageDialog(null, "Thanh toán thành công");

			CoffeeTable coffeeTable = bill.getTable();
			coffeeTable.setStatus(false);
			tableService.closeTable(coffeeTable);

			arrBills.forEach(b -> {
				CoffeeTable c = b.getTable();
				c.setStatus(false);

				tableService.closeTable(c);
			});

			return true;

		} else {
			JOptionPane.showMessageDialog(null, "Đã có lỗi xảy ra", "Gộp bàn và thanh toán", JOptionPane.ERROR_MESSAGE);
			return false;
		}
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
