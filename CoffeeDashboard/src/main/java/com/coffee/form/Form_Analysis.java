package com.coffee.form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.RowFilter;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.coffee.custom.PanelBorder;
import com.coffee.custom.TableCustom;
import com.coffee.custom.TableScrollButton;
import com.coffee.entity.Bill;
import com.coffee.entity.BillDetail;
import com.coffee.service.BillService;
import com.coffee.serviceimpl.BillServiceImpl;
import com.toedter.calendar.JDateChooser;

/**
 * @author Nguyen Phuc Thinh
 */
public class Form_Analysis extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4339828616061687096L;
	private Locale localeVN = Locale.getDefault();
	private NumberFormat moneyFormat = NumberFormat.getInstance(localeVN);
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

	private BillService billService;
	private List<Bill> arrBills;

	private JPanel pTop;
	private JTextField txtBillID;
	private JLabel lblNhpMHa;
	private JLabel lblNhpTnNhn;
	private JTextField txtEmployeeName;
	private JLabel lblNhpTnKhch;
	private JTextField txtCustomerName;
	private JLabel lblDateFrom;
	private JDateChooser dateFrom;
	private JDateChooser dateTo;
	private JLabel lblDateTo;
	private JLabel lblTotalPay;
	private JLabel lblTotalCancel;
	private JLabel lblTotal;
	private JLabel lblMoney;
	private JLabel lblMoneyCancel;
	private JLabel lblMoneyComplete;
	private JLabel lblSLBillComplete;
	private JLabel lblSLBillCancel;
	private JLabel lblSLBill;
	private JLabel lblTotalBill;
	private JLabel lblBillCancel;
	private JLabel lblBillComplete;
	private JButton btnFilter;
	private JButton btnMonth;
	private JButton btnAll;
	private JButton btnDay;
	private JLabel lbl;
	private JPanel pBill;
	private JPanel pDetails;
	private TableScrollButton tableScrollButton;
	private TableScrollButton tableScrollButton_1;
	private JScrollPane srcBillDetail;
	private JScrollPane srcBill;
	private JLabel lblMHan;
	private JLabel lblBillID;
	private JLabel lblTnNhnVin;
	private JLabel lblEmployee;
	private JLabel lblTnKhchHng;
	private JLabel lblCustomer;
	private JLabel lblMHan_3;
	private JLabel lblDate;
	private JLabel lblTotalBillSelect;
	private JLabel lblMHan_5;
	private JLabel lblStatus;
	private JLabel lblMHan_1;
	private JLabel lblMHan_2;
	private JLabel lblPayment;
	private JLabel lblMHan_4;
	private JTextArea txtNote;

	private JTable tableBill, tableDetails;
	private DefaultTableModel modelBill, modelDetails;

	/**
	 * Create the panel.
	 * 
	 * @
	 */
	public Form_Analysis() {
		billService = new BillServiceImpl();

		pTop = new PanelBorder();
		pTop.setBackground(Color.WHITE);

		pBill = new PanelBorder();
		pBill.setBackground(Color.WHITE);

		pDetails = new PanelBorder();
		pDetails.setBackground(Color.WHITE);

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(pBill, GroupLayout.DEFAULT_SIZE, 704, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(pDetails, GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE))
								.addComponent(pTop, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE))
						.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(pTop, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(pDetails, GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
								.addComponent(pBill, GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE))
						.addContainerGap()));

		setLayout(groupLayout);
		initComponentTop();
		initCenter();
		setUpBill();
		getValueListBill();
	
	}

	/**
	 * 
	 */
	private void initComponentTop() {
		// TODO Auto-generated method stub

		txtBillID = new JTextField();
		txtBillID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				search(txtBillID.getText(), modelBill, tableBill);
			}
		});
		txtBillID.setBackground(new Color(240, 240, 240));
		txtBillID.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtBillID.setColumns(10);
		txtBillID.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));

		lblNhpMHa = new JLabel();
		lblNhpMHa.setText("Nhập mã hóa đơn:");
		lblNhpMHa.setForeground(new Color(127, 127, 127));
		lblNhpMHa.setFont(new Font("SansSerif", Font.BOLD, 12));

		lblNhpTnNhn = new JLabel();
		lblNhpTnNhn.setText("Nhập tên nhân viên:");
		lblNhpTnNhn.setForeground(new Color(127, 127, 127));
		lblNhpTnNhn.setFont(new Font("SansSerif", Font.BOLD, 12));

		txtEmployeeName = new JTextField();
		txtEmployeeName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				search(txtEmployeeName.getText(), modelBill, tableBill);
			}
		});
		txtEmployeeName.setBackground(new Color(240, 240, 240));
		txtEmployeeName.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtEmployeeName.setColumns(10);
		txtEmployeeName.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));

		lblNhpTnKhch = new JLabel();
		lblNhpTnKhch.setText("Nhập tên khách hàng:");
		lblNhpTnKhch.setForeground(new Color(127, 127, 127));
		lblNhpTnKhch.setFont(new Font("SansSerif", Font.BOLD, 12));

		txtCustomerName = new JTextField();
		txtCustomerName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				search(txtCustomerName.getText(), modelBill, tableBill);
			}
		});
		txtCustomerName.setBackground(new Color(240, 240, 240));
		txtCustomerName.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtCustomerName.setColumns(10);
		txtCustomerName.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));

		lblDateFrom = new JLabel("Từ ngày:");
		lblDateFrom.setForeground(new Color(127, 127, 127));
		lblDateFrom.setFont(new Font("SansSerif", Font.BOLD, 12));

		dateFrom = new JDateChooser();
		dateFrom.setDateFormatString("yyyy-MM-dd");

		dateTo = new JDateChooser();
		dateTo.setDateFormatString("yyyy-MM-dd");

		lblDateTo = new JLabel("Đến ngày:");
		lblDateTo.setForeground(new Color(127, 127, 127));
		lblDateTo.setFont(new Font("SansSerif", Font.BOLD, 12));

		lblTotalPay = new JLabel("Tổng tiền đã hoàn thành:");
		lblTotalPay.setForeground(new Color(127, 127, 127));
		lblTotalPay.setFont(new Font("SansSerif", Font.BOLD, 12));

		lblTotalCancel = new JLabel("Tổng tiền đã hủy:");
		lblTotalCancel.setForeground(new Color(127, 127, 127));
		lblTotalCancel.setFont(new Font("SansSerif", Font.BOLD, 12));

		lblTotal = new JLabel("Tổng tiền:");
		lblTotal.setForeground(new Color(127, 127, 127));
		lblTotal.setFont(new Font("SansSerif", Font.BOLD, 12));

		lblMoney = new JLabel("");
		lblMoney.setFont(new Font("Tahoma", Font.BOLD, 12));

		lblMoneyCancel = new JLabel("");
		lblMoneyCancel.setFont(new Font("Tahoma", Font.BOLD, 12));

		lblMoneyComplete = new JLabel("");
		lblMoneyComplete.setFont(new Font("Tahoma", Font.BOLD, 12));

		lblSLBillComplete = new JLabel("");
		lblSLBillComplete.setFont(new Font("Tahoma", Font.BOLD, 12));

		lblSLBillCancel = new JLabel("");
		lblSLBillCancel.setFont(new Font("Tahoma", Font.BOLD, 12));

		lblSLBill = new JLabel("");
		lblSLBill.setFont(new Font("Tahoma", Font.BOLD, 12));

		lblTotalBill = new JLabel("Tổng hóa đơn:");
		lblTotalBill.setForeground(new Color(127, 127, 127));
		lblTotalBill.setFont(new Font("SansSerif", Font.BOLD, 12));

		lblBillCancel = new JLabel("Hóa đơn bị hủy:");
		lblBillCancel.setForeground(new Color(127, 127, 127));
		lblBillCancel.setFont(new Font("SansSerif", Font.BOLD, 12));

		lblBillComplete = new JLabel("Hóa đơn hoàn thành:");
		lblBillComplete.setForeground(new Color(127, 127, 127));
		lblBillComplete.setFont(new Font("SansSerif", Font.BOLD, 12));

		/***********************************/
		btnFilter = new JButton("Lọc");
		btnFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filter(dateFrom.getDate(), dateTo.getDate());
			}
		});
		btnFilter.setFont(new Font("SansSerif", Font.PLAIN, 10));
		btnFilter.setBackground(new Color(240, 240, 240));
		btnFilter.setForeground(new Color(127, 127, 127));

		btnMonth = new JButton("Lọc tháng");
		btnMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filterMonth();
			}
		});
		btnMonth.setFont(new Font("SansSerif", Font.PLAIN, 10));
		btnMonth.setForeground(new Color(127, 127, 127));
		btnMonth.setBackground(SystemColor.menu);

		btnAll = new JButton("Tất cả");
		btnAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setUpBill();
			}
		});
		btnAll.setFont(new Font("SansSerif", Font.PLAIN, 10));
		btnAll.setForeground(new Color(127, 127, 127));
		btnAll.setBackground(SystemColor.menu);

		btnDay = new JButton("Lọc ngày");
		btnDay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filterDay();
			}
		});
		btnDay.setFont(new Font("SansSerif", Font.PLAIN, 10));
		btnDay.setForeground(new Color(127, 127, 127));
		btnDay.setBackground(SystemColor.menu);

		lbl = new JLabel("");
		lbl.setForeground(new Color(127, 127, 127));
		lbl.setFont(new Font("SansSerif", Font.BOLD, 12));
		GroupLayout gl_pTop = new GroupLayout(pTop);
		gl_pTop.setHorizontalGroup(gl_pTop.createParallelGroup(Alignment.LEADING).addGroup(gl_pTop
				.createSequentialGroup().addGap(10)
				.addGroup(gl_pTop.createParallelGroup(Alignment.LEADING).addGroup(gl_pTop.createSequentialGroup()
						.addComponent(lblNhpMHa, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE).addGap(12)
						.addComponent(txtBillID, GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE).addGap(10)
						.addComponent(lblDateFrom, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE).addGap(4)
						.addComponent(dateFrom, GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE).addGap(30)
						.addComponent(btnAll, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE).addGap(26)
						.addComponent(lblTotalPay, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
						.addGap(10)
						.addComponent(lblMoneyComplete, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
						.addGap(10)
						.addComponent(lblBillComplete, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
						.addGap(22)
						.addComponent(lblSLBillComplete, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pTop.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_pTop.createParallelGroup(Alignment.LEADING).addGroup(gl_pTop
										.createSequentialGroup()
										.addComponent(lblNhpTnNhn, GroupLayout.PREFERRED_SIZE, 121,
												GroupLayout.PREFERRED_SIZE)
										.addGap(12)
										.addComponent(txtEmployeeName, GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
										.addGap(10)
										.addComponent(lblDateTo, GroupLayout.PREFERRED_SIZE, 66,
												GroupLayout.PREFERRED_SIZE)
										.addGap(7).addComponent(dateTo, GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
										.addGap(30))
										.addGroup(gl_pTop.createSequentialGroup()
												.addGroup(gl_pTop.createParallelGroup(Alignment.LEADING)
														.addGroup(gl_pTop.createSequentialGroup().addGap(133)
																.addComponent(txtCustomerName, GroupLayout.DEFAULT_SIZE,
																		136, Short.MAX_VALUE))
														.addComponent(lblNhpTnKhch, GroupLayout.PREFERRED_SIZE, 136,
																GroupLayout.PREFERRED_SIZE))
												.addGap(10)
												.addComponent(lbl, GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
												.addGap(10)
												.addComponent(btnFilter, GroupLayout.PREFERRED_SIZE, 85,
														GroupLayout.PREFERRED_SIZE)
												.addGap(79)))
								.addGroup(gl_pTop.createParallelGroup(Alignment.LEADING)
										.addComponent(btnDay, GroupLayout.PREFERRED_SIZE, 85,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnMonth, GroupLayout.PREFERRED_SIZE, 85,
												GroupLayout.PREFERRED_SIZE))
								.addGap(26)
								.addGroup(gl_pTop.createParallelGroup(Alignment.LEADING)
										.addComponent(lblTotalCancel, GroupLayout.PREFERRED_SIZE, 145,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblTotal, GroupLayout.PREFERRED_SIZE, 145,
												GroupLayout.PREFERRED_SIZE))
								.addGap(10)
								.addGroup(gl_pTop.createParallelGroup(Alignment.LEADING)
										.addComponent(lblMoneyCancel, GroupLayout.PREFERRED_SIZE, 109,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblMoney, GroupLayout.PREFERRED_SIZE, 109,
												GroupLayout.PREFERRED_SIZE))
								.addGap(10)
								.addGroup(gl_pTop.createParallelGroup(Alignment.LEADING)
										.addComponent(lblBillCancel, GroupLayout.PREFERRED_SIZE, 145,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblTotalBill, GroupLayout.PREFERRED_SIZE, 145,
												GroupLayout.PREFERRED_SIZE))
								.addGap(22)
								.addGroup(gl_pTop.createParallelGroup(Alignment.LEADING)
										.addComponent(lblSLBillCancel, GroupLayout.PREFERRED_SIZE, 28,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblSLBill, GroupLayout.PREFERRED_SIZE, 28,
												GroupLayout.PREFERRED_SIZE))))
				.addGap(24)));
		gl_pTop.setVerticalGroup(gl_pTop.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pTop.createSequentialGroup().addGap(10)
						.addGroup(gl_pTop.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNhpMHa, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtBillID, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDateFrom, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(dateFrom, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnAll, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_pTop.createSequentialGroup().addGap(10).addComponent(lblTotalPay,
										GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_pTop.createSequentialGroup().addGap(10).addComponent(lblMoneyComplete,
										GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_pTop.createSequentialGroup().addGap(10).addComponent(
										lblBillComplete, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
								.addGroup(
										gl_pTop.createSequentialGroup().addGap(10)
												.addComponent(
														lblSLBillComplete, GroupLayout.PREFERRED_SIZE, 25,
														GroupLayout.PREFERRED_SIZE)))
						.addGap(3)
						.addGroup(gl_pTop.createParallelGroup(Alignment.LEADING).addGroup(gl_pTop
								.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pTop.createSequentialGroup()
										.addComponent(btnDay, GroupLayout.PREFERRED_SIZE, 21,
												GroupLayout.PREFERRED_SIZE)
										.addGap(16).addComponent(btnMonth, GroupLayout.PREFERRED_SIZE, 21,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_pTop.createSequentialGroup().addGap(7)
										.addComponent(lblTotalCancel, GroupLayout.PREFERRED_SIZE, 25,
												GroupLayout.PREFERRED_SIZE)
										.addGap(12).addComponent(lblTotal, GroupLayout.PREFERRED_SIZE, 25,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_pTop.createSequentialGroup().addGap(15)
										.addComponent(lblMoneyCancel, GroupLayout.PREFERRED_SIZE, 25,
												GroupLayout.PREFERRED_SIZE)
										.addGap(4).addComponent(lblMoney, GroupLayout.PREFERRED_SIZE, 25,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_pTop.createSequentialGroup().addGap(6)
										.addComponent(lblBillCancel, GroupLayout.PREFERRED_SIZE, 25,
												GroupLayout.PREFERRED_SIZE)
										.addGap(12).addComponent(lblTotalBill, GroupLayout.PREFERRED_SIZE, 25,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_pTop
										.createSequentialGroup().addGap(7)
										.addComponent(lblSLBillCancel, GroupLayout.PREFERRED_SIZE, 25,
												GroupLayout.PREFERRED_SIZE)
										.addGap(12).addComponent(lblSLBill, GroupLayout.PREFERRED_SIZE, 25,
												GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_pTop.createSequentialGroup().addGap(5).addGroup(gl_pTop
										.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_pTop.createParallelGroup(Alignment.TRAILING)
												.addComponent(dateTo, GroupLayout.PREFERRED_SIZE, 25,
														GroupLayout.PREFERRED_SIZE)
												.addGroup(gl_pTop.createParallelGroup(Alignment.LEADING)
														.addComponent(lblNhpTnNhn, GroupLayout.PREFERRED_SIZE, 24,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(txtEmployeeName, GroupLayout.PREFERRED_SIZE, 24,
																GroupLayout.PREFERRED_SIZE)))
										.addComponent(lblDateTo, GroupLayout.PREFERRED_SIZE, 25,
												GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(gl_pTop.createParallelGroup(Alignment.LEADING)
												.addComponent(btnFilter, GroupLayout.PREFERRED_SIZE, 21,
														GroupLayout.PREFERRED_SIZE)
												.addGroup(gl_pTop.createParallelGroup(Alignment.TRAILING)
														.addGroup(gl_pTop.createParallelGroup(Alignment.LEADING)
																.addComponent(txtCustomerName,
																		GroupLayout.PREFERRED_SIZE, 24,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(lblNhpTnKhch, GroupLayout.PREFERRED_SIZE,
																		24, GroupLayout.PREFERRED_SIZE))
														.addComponent(lbl, GroupLayout.PREFERRED_SIZE, 25,
																GroupLayout.PREFERRED_SIZE)))))
						.addGap(19)));
		pTop.setLayout(gl_pTop);

	}

	/**
	 * 
	 */
	private void initCenter() {
		// TODO Auto-generated method stub
		tableScrollButton = new TableScrollButton();

		srcBill = new JScrollPane();
		String[] header = { "Mã hóa đơn", "Người bán", "Khách hàng", "Ngày bán", "Tổng tiền", "Trạng thái", "Ghi chú" };
		modelBill = new DefaultTableModel(header, 0);
		tableBill = new JTable(modelBill);

		tableBill.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int row = tableBill.getSelectedRow();

				Bill b = arrBills.get(row);

				lblBillID.setText(b.getBill_id());
				lblEmployee.setText(b.getEmployee() == null ? "" : b.getEmployee().getFullName());
				lblCustomer.setText(b.getCustomer() == null ? "" : b.getCustomer().getFullName());
				lblDate.setText(dateFormat.format(b.getPayDate()));
				lblPayment.setText(b.getPayment());
				lblTotalBillSelect.setText(moneyFormat.format(b.getTotalPrice()) + "đ");
				lblStatus.setText(b.getBillStatus());
				txtNote.setText(b.getBillNote());

				getBillDetails(new ArrayList<>(b.getBillDetails()));
			}
		});

		srcBill.setViewportView(tableBill);

		TableCustom.apply(srcBill, TableCustom.TableType.MULTI_LINE);
		tableScrollButton.add(srcBill, BorderLayout.CENTER);
		GroupLayout gl_pBill = new GroupLayout(pBill);
		gl_pBill.setHorizontalGroup(
				gl_pBill.createParallelGroup(Alignment.LEADING).addGroup(gl_pBill.createSequentialGroup().addGap(10)
						.addComponent(tableScrollButton, GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE).addGap(10)));
		gl_pBill.setVerticalGroup(
				gl_pBill.createParallelGroup(Alignment.LEADING).addGroup(gl_pBill.createSequentialGroup().addGap(10)
						.addComponent(tableScrollButton, GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE).addGap(10)));
		pBill.setLayout(gl_pBill);

		tableScrollButton_1 = new TableScrollButton();

		srcBillDetail = new JScrollPane();
		String[] headers = { "STT", "Tên SP", "Loại SP", "Số lượng", "Giá tiền", "Tổng tiền" };
		modelDetails = new DefaultTableModel(headers, 0);
		tableDetails = new JTable(modelDetails);
		srcBillDetail.setViewportView(tableDetails);
		TableCustom.apply(srcBillDetail, TableCustom.TableType.MULTI_LINE);
		tableScrollButton_1.add(srcBillDetail, BorderLayout.CENTER);

		lblMHan = new JLabel("Mã hóa đơn:");
		lblMHan.setForeground(new Color(127, 127, 127));
		lblMHan.setFont(new Font("SansSerif", Font.BOLD, 12));

		lblBillID = new JLabel("");
		lblBillID.setForeground(new Color(127, 127, 127));
		lblBillID.setFont(new Font("SansSerif", Font.BOLD, 12));

		lblTnNhnVin = new JLabel("Tên nhân viên:");
		lblTnNhnVin.setForeground(new Color(127, 127, 127));
		lblTnNhnVin.setFont(new Font("SansSerif", Font.BOLD, 12));

		lblEmployee = new JLabel("");
		lblEmployee.setForeground(new Color(127, 127, 127));
		lblEmployee.setFont(new Font("SansSerif", Font.BOLD, 12));

		lblTnKhchHng = new JLabel("Tên khách hàng:");
		lblTnKhchHng.setForeground(new Color(127, 127, 127));
		lblTnKhchHng.setFont(new Font("SansSerif", Font.BOLD, 12));

		lblCustomer = new JLabel("");
		lblCustomer.setForeground(new Color(127, 127, 127));
		lblCustomer.setFont(new Font("SansSerif", Font.BOLD, 12));

		lblMHan_3 = new JLabel("Ngày giao dịch:");
		lblMHan_3.setForeground(new Color(127, 127, 127));
		lblMHan_3.setFont(new Font("SansSerif", Font.BOLD, 12));

		lblDate = new JLabel();

		lblDate.setBackground(Color.RED);
		lblDate.setForeground(new Color(127, 127, 127));
		lblDate.setFont(new Font("SansSerif", Font.BOLD, 12));

		lblTotalBillSelect = new JLabel("");
		lblTotalBillSelect.setForeground(new Color(127, 127, 127));
		lblTotalBillSelect.setFont(new Font("SansSerif", Font.BOLD, 12));

		lblMHan_5 = new JLabel("Tổng hóa đơn:");
		lblMHan_5.setForeground(new Color(127, 127, 127));
		lblMHan_5.setFont(new Font("SansSerif", Font.BOLD, 12));

		lblStatus = new JLabel("");
		lblStatus.setForeground(new Color(127, 127, 127));
		lblStatus.setFont(new Font("SansSerif", Font.BOLD, 12));

		lblMHan_1 = new JLabel("Trạng thái:");
		lblMHan_1.setForeground(new Color(127, 127, 127));
		lblMHan_1.setFont(new Font("SansSerif", Font.BOLD, 12));

		lblMHan_2 = new JLabel("Loại thanh toán:");
		lblMHan_2.setForeground(new Color(127, 127, 127));
		lblMHan_2.setFont(new Font("SansSerif", Font.BOLD, 12));

		lblPayment = new JLabel("");
		lblPayment.setForeground(new Color(127, 127, 127));
		lblPayment.setFont(new Font("SansSerif", Font.BOLD, 12));

		lblMHan_4 = new JLabel("Ghi chú:");
		lblMHan_4.setForeground(new Color(127, 127, 127));
		lblMHan_4.setFont(new Font("SansSerif", Font.BOLD, 12));

		txtNote = new JTextArea();
		txtNote.setBackground(new Color(240, 240, 240));
		GroupLayout gl_pDetails = new GroupLayout(pDetails);
		gl_pDetails.setHorizontalGroup(gl_pDetails.createParallelGroup(Alignment.LEADING).addGroup(gl_pDetails
				.createSequentialGroup().addGap(10)
				.addGroup(gl_pDetails.createParallelGroup(Alignment.LEADING)
						.addComponent(tableScrollButton_1, GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
						.addGroup(gl_pDetails.createSequentialGroup()
								.addComponent(lblMHan, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
								.addGap(36).addComponent(lblBillID, GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE))
						.addGroup(gl_pDetails.createSequentialGroup()
								.addComponent(lblTnNhnVin, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
								.addGap(24).addComponent(lblEmployee, GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE))
						.addGroup(gl_pDetails.createSequentialGroup()
								.addComponent(lblTnKhchHng, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
								.addGap(10).addComponent(lblCustomer, GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE))
						.addGroup(gl_pDetails.createSequentialGroup()
								.addComponent(lblMHan_3, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
								.addGap(24).addComponent(lblDate, GroupLayout.PREFERRED_SIZE, 97, Short.MAX_VALUE)
								.addGap(10)
								.addComponent(lblMHan_1, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblStatus, GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE))
						.addGroup(gl_pDetails.createSequentialGroup()
								.addComponent(lblMHan_2, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
								.addGap(24).addComponent(lblPayment, GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE))
						.addGroup(gl_pDetails.createSequentialGroup()
								.addComponent(lblMHan_5, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
								.addGap(36)
								.addComponent(lblTotalBillSelect, GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE))
						.addGroup(gl_pDetails.createSequentialGroup()
								.addComponent(lblMHan_4, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
								.addGap(36).addComponent(txtNote, GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)))
				.addGap(10)));
		gl_pDetails.setVerticalGroup(gl_pDetails.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pDetails.createSequentialGroup().addGap(10)
						.addComponent(tableScrollButton_1, GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE).addGap(10)
						.addGroup(gl_pDetails.createParallelGroup(Alignment.LEADING)
								.addComponent(lblMHan, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblBillID, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addGap(10)
						.addGroup(gl_pDetails.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTnNhnVin, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEmployee, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addGap(10)
						.addGroup(gl_pDetails.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTnKhchHng, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCustomer, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addGap(10)
						.addGroup(gl_pDetails.createParallelGroup(Alignment.LEADING)
								.addComponent(lblMHan_3, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDate, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMHan_1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblStatus, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addGap(10)
						.addGroup(gl_pDetails.createParallelGroup(Alignment.LEADING)
								.addComponent(lblMHan_2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPayment, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addGap(10)
						.addGroup(
								gl_pDetails.createParallelGroup(Alignment.LEADING)
										.addComponent(lblMHan_5, GroupLayout.PREFERRED_SIZE, 25,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblTotalBillSelect, GroupLayout.PREFERRED_SIZE, 25,
												GroupLayout.PREFERRED_SIZE))
						.addGap(10)
						.addGroup(gl_pDetails.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pDetails.createSequentialGroup().addGap(7).addComponent(txtNote,
										GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblMHan_4, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addContainerGap()));
		pDetails.setLayout(gl_pDetails);
	}

	private void setUpBill()  {

		arrBills = billService.getAll();
		modelBill.setRowCount(0);

		for (int i = 0; i < arrBills.size(); i++) {
			Bill b = arrBills.get(i);

			modelBill.addRow(new Object[] { b.getBill_id(),
					b.getEmployee() == null ? "" : b.getEmployee().getFullName(),
					b.getCustomer() == null ? "" : b.getCustomer().getFullName(), dateFormat.format(b.getPayDate()),
					moneyFormat.format(b.getTotalPrice()), b.getBillStatus(), b.getBillNote() });

		}

	}

	private void getBillDetails(List<BillDetail> arrBillDetails) {

		modelDetails.setRowCount(0);

		for (int i = 0; i < arrBillDetails.size(); i++) {
			BillDetail detail = arrBillDetails.get(i);
			modelDetails.addRow(new Object[] { (i + 1), detail.getProduct().getProductName(),
					detail.getProduct().getCategory().getCategoryName(), detail.getAmount(),
					moneyFormat.format(detail.getProduct().getProductPrice()) + "đ",
					moneyFormat.format(detail.getTotal()) + "đ" });
		}
	}

	private void filter(Date from, Date to) {

		modelBill.setRowCount(0);

		for (int i = 0; i < arrBills.size(); i++) {
			Bill b = arrBills.get(i);

			if (b.getPayDate().compareTo(from) >= 0 && b.getPayDate().compareTo(to) <= 0) {

				modelBill.addRow(new Object[] { b.getBill_id(),
						b.getEmployee() == null ? "" : b.getEmployee().getFullName(),
						b.getCustomer() == null ? "" : b.getCustomer().getFullName(), dateFormat.format(b.getPayDate()),
						moneyFormat.format(b.getTotalPrice()), b.getBillStatus(), b.getBillNote() });
			}
		}

	}

	private void filterDay() {

		modelBill.setRowCount(0);
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		for (int i = 0; i < arrBills.size(); i++) {
			Bill b = arrBills.get(i);

			if (format.format(b.getPayDate()).equals(format.format(new Date()))) {

				modelBill.addRow(new Object[] { b.getBill_id(),
						b.getEmployee() == null ? "" : b.getEmployee().getFullName(),
						b.getCustomer() == null ? "" : b.getCustomer().getFullName(), dateFormat.format(b.getPayDate()),
						moneyFormat.format(b.getTotalPrice()), b.getBillStatus(), b.getBillNote() });
			}
		}

	}

	private void filterMonth() {

		modelBill.setRowCount(0);

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int month = cal.get(Calendar.MONTH);

		Calendar cal1 = Calendar.getInstance();

		for (int i = 0; i < arrBills.size(); i++) {
			Bill b = arrBills.get(i);

			cal1.setTime(b.getPayDate());
			int month1 = cal.get(Calendar.MONTH);

			if (month == month1) {

				modelBill.addRow(new Object[] { b.getBill_id(),
						b.getEmployee() == null ? "" : b.getEmployee().getFullName(),
						b.getCustomer() == null ? "" : b.getCustomer().getFullName(), dateFormat.format(b.getPayDate()),
						moneyFormat.format(b.getTotalPrice()), b.getBillStatus(), b.getBillNote() });
			}
		}

	}

	private void getValueListBill()  {

		arrBills = billService.getAll();
		double priceCom = 0;
		double priceCancel = 0;
		int billCom = 0;
		int billCancel = 0;

		for (int i = 0; i < arrBills.size(); i++) {
			Bill b = arrBills.get(i);

			if (b.getBillStatus().equals("Hoàn thành")) {
				billCom = billCom + 1;
				priceCom = priceCom + b.getTotalPrice();

			} else if (b.getBillStatus().equals("Đã hủy")) {
				billCancel = billCancel + 1;
				priceCancel = priceCancel + b.getTotalPrice();

			}

		}
		lblSLBillComplete.setText(billCom + "");
		lblSLBillCancel.setText(billCancel + "");
		lblSLBill.setText(String.valueOf(billCancel + billCom));
		lblMoneyComplete.setText(moneyFormat.format(priceCom) + "đ");
		lblMoneyCancel.setText(moneyFormat.format(priceCancel) + "đ");

		lblMoney.setText(moneyFormat.format(priceCom + priceCancel) + "đ");
	}

	private void search(String search, DefaultTableModel model, JTable t) {
		TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(model);
		t.setRowSorter(tr);
		tr.setRowFilter(RowFilter.regexFilter(search));
	}
}
