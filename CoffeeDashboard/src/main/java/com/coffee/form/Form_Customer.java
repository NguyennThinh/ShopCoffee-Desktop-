package com.coffee.form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

import com.coffee.custom.PanelBorder;
import com.coffee.custom.TableCustom;
import com.coffee.custom.TableScrollButton;
import com.coffee.entity.Customer;
import com.coffee.service.CustomerService;
import com.coffee.serviceimpl.CustomerServiceImpl;
import com.toedter.calendar.JDateChooser;

/**
 * @author Nguyen Phuc Thinh
 */
public class Form_Customer extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8668490421658733080L;

	private CustomerService customerService;
	private List<Customer> arrCustomers;

	private Customer customer;

	private PanelBorder pCustomer;
	private TableScrollButton tableScrollButton;
	private JScrollPane srcCustomer;
	private PanelBorder pDetail;
	private JLabel lblTnKhchHng;
	private JTextField txtCustomerName;
	private JLabel lblDanhSchKhch;
	private JLabel lblNhpTnKhch;
	private JTextField txtSearch;
	private JLabel lblThngTinKhch;
	private JLabel lblMKhchHng;
	private JTextField txtCustomerID;
	private JLabel lblSinThoi;
	private JTextField txtPhone;
	private JLabel lblEmail;
	private JTextField txtEmail;
	private JLabel lblaCh;
	private JTextField txtAddress;
	private JLabel lblimTchLy;
	private JButton btnSave;
	private JTextField txtPoint;
	private JLabel lblimTchLy_1;
	private JDateChooser date;
	private JTable table;
	private DefaultTableModel tableModel;
	private JPopupMenu menu;

	/**
	 * Create the panel.
	 * 
	 * @
	 */
	public Form_Customer() {
		customerService = new CustomerServiceImpl();

		initComponent();
	}

	/**
	 * 
	 */
	private void initComponent() {

		pCustomer = new PanelBorder();
		pCustomer.setBackground(Color.WHITE);

		tableScrollButton = new TableScrollButton();
		pCustomer.add(tableScrollButton);

		srcCustomer = new JScrollPane();

		String[] header = { "STT", "Mã khách hàng", "Tên khách hàng", "Số điện thoại", "Email", "Ngày tham gia" };
		tableModel = new DefaultTableModel(header, 0);
		table = new JTable(tableModel);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();

				if(row !=-1) {
					customer = arrCustomers.get(row);

					txtCustomerID.setText(String.valueOf(customer.getId()));
					txtCustomerName.setText(customer.getFullName());
					txtPhone.setText(String.valueOf(customer.getPhone()));
					txtEmail.setText(customer.getEmail());
					txtAddress.setText(customer.getAddress());
					date.setDate(customer.getCreateDate());
					txtPoint.setText(String.valueOf(customer.getPoint()));
						
					if(SwingUtilities.isRightMouseButton(e)) {
						delete();
						menu.show(table, e.getX(), e.getY());
					}
				}
			}
		});
		srcCustomer.setViewportView(table);
		TableCustom.apply(srcCustomer, TableCustom.TableType.MULTI_LINE);
		tableScrollButton.add(srcCustomer, BorderLayout.CENTER);

		lblDanhSchKhch = new JLabel();
		lblDanhSchKhch.setText("Danh sách khách hàng");
		lblDanhSchKhch.setForeground(new Color(127, 127, 127));
		lblDanhSchKhch.setFont(new Font("SansSerif", Font.BOLD, 18));
		pCustomer.add(lblDanhSchKhch);

		lblNhpTnKhch = new JLabel();
		lblNhpTnKhch.setText("Nhập tên khách hàng:");
		lblNhpTnKhch.setForeground(new Color(127, 127, 127));
		lblNhpTnKhch.setFont(new Font("SansSerif", Font.BOLD, 12));
		pCustomer.add(lblNhpTnKhch);

		txtSearch = new JTextField();
		txtSearch.setBackground(new Color(240, 240, 240));
		txtSearch.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtSearch.setColumns(10);
		txtSearch.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pCustomer.add(txtSearch);

		pDetail = new PanelBorder();
		pDetail.setBackground(Color.WHITE);

		lblTnKhchHng = new JLabel();
		lblTnKhchHng.setText("Tên khách hàng:");
		lblTnKhchHng.setForeground(new Color(127, 127, 127));
		lblTnKhchHng.setFont(new Font("SansSerif", Font.BOLD, 12));

		txtCustomerName = new JTextField();
		txtCustomerName.setBackground(new Color(240, 240, 240));
		txtCustomerName.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtCustomerName.setColumns(10);
		txtCustomerName.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));

		lblThngTinKhch = new JLabel();
		lblThngTinKhch.setText("Thông tin khách hàng");
		lblThngTinKhch.setForeground(new Color(127, 127, 127));
		lblThngTinKhch.setFont(new Font("SansSerif", Font.BOLD, 18));

		lblMKhchHng = new JLabel();
		lblMKhchHng.setText("Mã khách hàng:");
		lblMKhchHng.setForeground(new Color(127, 127, 127));
		lblMKhchHng.setFont(new Font("SansSerif", Font.BOLD, 12));

		txtCustomerID = new JTextField();
		txtCustomerID.setEnabled(false);
		txtCustomerID.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtCustomerID.setColumns(10);
		txtCustomerID.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));

		lblSinThoi = new JLabel();
		lblSinThoi.setText("Số điện thoại:");
		lblSinThoi.setForeground(new Color(127, 127, 127));
		lblSinThoi.setFont(new Font("SansSerif", Font.BOLD, 12));

		txtPhone = new JTextField();
		txtPhone.setBackground(new Color(240, 240, 240));
		txtPhone.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtPhone.setColumns(10);
		txtPhone.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));

		lblEmail = new JLabel();
		lblEmail.setText("Email:");
		lblEmail.setForeground(new Color(127, 127, 127));
		lblEmail.setFont(new Font("SansSerif", Font.BOLD, 12));

		txtEmail = new JTextField();
		txtEmail.setBackground(new Color(240, 240, 240));
		txtEmail.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtEmail.setColumns(10);
		txtEmail.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));

		lblaCh = new JLabel();
		lblaCh.setText("Địa chỉ:");
		lblaCh.setForeground(new Color(127, 127, 127));
		lblaCh.setFont(new Font("SansSerif", Font.BOLD, 12));

		txtAddress = new JTextField();
		txtAddress.setBackground(new Color(240, 240, 240));
		txtAddress.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtAddress.setColumns(10);
		txtAddress.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));

		lblimTchLy = new JLabel();
		lblimTchLy.setText("Ngày tham gia:");
		lblimTchLy.setForeground(new Color(127, 127, 127));
		lblimTchLy.setFont(new Font("SansSerif", Font.BOLD, 12));

		btnSave = new JButton("Lưu nhân viên");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		btnSave.setBackground(new Color(240, 240, 240));
		btnSave.setBorder(null);
		btnSave.setFont(new Font("SansSerif", Font.PLAIN, 10));

		txtPoint = new JTextField();
		txtPoint.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtPoint.setColumns(10);
		txtPoint.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtPoint.setBackground(SystemColor.menu);

		lblimTchLy_1 = new JLabel();
		lblimTchLy_1.setText("Điểm tích lũy:");
		lblimTchLy_1.setForeground(new Color(127, 127, 127));
		lblimTchLy_1.setFont(new Font("SansSerif", Font.BOLD, 12));

		date = new JDateChooser();
		date.setBackground(new Color(240, 240, 240));
		date.setDateFormatString("yyyy-MM-dd");
		date.setDate(new Date());
		GroupLayout gl_pDetail = new GroupLayout(pDetail);
		gl_pDetail.setHorizontalGroup(gl_pDetail.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pDetail.createSequentialGroup().addGroup(gl_pDetail.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pDetail.createSequentialGroup().addGap(10)
								.addComponent(lblMKhchHng, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
								.addGap(9).addComponent(txtCustomerID, GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE))
						.addGroup(gl_pDetail.createSequentialGroup().addGap(10)
								.addComponent(lblTnKhchHng, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
								.addGap(9)
								.addComponent(txtCustomerName, GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE))
						.addGroup(gl_pDetail.createSequentialGroup().addContainerGap()
								.addComponent(lblSinThoi, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
								.addGap(9).addComponent(txtPhone, GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE))
						.addGroup(gl_pDetail.createSequentialGroup().addContainerGap()
								.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
								.addGap(9).addComponent(txtEmail, GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE))
						.addGroup(gl_pDetail.createSequentialGroup().addContainerGap()
								.addComponent(lblaCh, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
								.addGap(9).addComponent(txtAddress, GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE))
						.addGroup(gl_pDetail.createSequentialGroup().addContainerGap()
								.addComponent(lblimTchLy, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(date, GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE))
						.addGroup(gl_pDetail.createSequentialGroup().addContainerGap()
								.addComponent(lblimTchLy_1, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
								.addGap(9)
								.addGroup(gl_pDetail.createParallelGroup(Alignment.LEADING)
										.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 128,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(txtPoint, GroupLayout.PREFERRED_SIZE, 258,
												GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_pDetail.createSequentialGroup().addContainerGap().addComponent(lblThngTinKhch,
								GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap()));
		gl_pDetail.setVerticalGroup(gl_pDetail.createParallelGroup(Alignment.LEADING).addGroup(gl_pDetail
				.createSequentialGroup().addContainerGap().addComponent(lblThngTinKhch).addGap(28)
				.addGroup(gl_pDetail.createParallelGroup(Alignment.LEADING)
						.addComponent(lblMKhchHng, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtCustomerID, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
				.addGap(29)
				.addGroup(gl_pDetail.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTnKhchHng, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtCustomerName, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
				.addGap(32)
				.addGroup(gl_pDetail.createParallelGroup(Alignment.LEADING)
						.addComponent(lblSinThoi, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtPhone, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
				.addGap(32)
				.addGroup(gl_pDetail.createParallelGroup(Alignment.LEADING)
						.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
				.addGap(35)
				.addGroup(gl_pDetail.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtAddress, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblaCh, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
				.addGap(32)
				.addGroup(gl_pDetail.createParallelGroup(Alignment.LEADING)
						.addComponent(lblimTchLy, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(date, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
				.addGap(31)
				.addGroup(gl_pDetail.createParallelGroup(Alignment.LEADING)
						.addComponent(lblimTchLy_1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtPoint, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
				.addGap(39).addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
				.addGap(512)));
		pDetail.setLayout(gl_pDetail);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				groupLayout.createSequentialGroup().addGap(11)
						.addComponent(pCustomer, GroupLayout.DEFAULT_SIZE, 739, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(pDetail, GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE).addGap(9)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addGap(11).addComponent(pCustomer,
										GroupLayout.DEFAULT_SIZE, 632, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(pDetail,
										GroupLayout.PREFERRED_SIZE, 633, Short.MAX_VALUE)))
						.addContainerGap()));
		GroupLayout gl_pCustomer = new GroupLayout(pCustomer);
		gl_pCustomer.setHorizontalGroup(gl_pCustomer.createParallelGroup(Alignment.LEADING).addGroup(gl_pCustomer
				.createSequentialGroup().addGap(10)
				.addGroup(gl_pCustomer.createParallelGroup(Alignment.LEADING).addGroup(gl_pCustomer
						.createSequentialGroup()
						.addComponent(lblDanhSchKhch, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE)
						.addGap(47)
						.addComponent(lblNhpTnKhch, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
						.addGap(9).addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE))
						.addComponent(tableScrollButton, GroupLayout.DEFAULT_SIZE, 681, Short.MAX_VALUE))
				.addGap(14)));
		gl_pCustomer.setVerticalGroup(gl_pCustomer.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pCustomer.createSequentialGroup().addGap(10)
						.addGroup(gl_pCustomer.createParallelGroup(Alignment.LEADING).addComponent(lblDanhSchKhch)
								.addComponent(lblNhpTnKhch, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
						.addGap(29).addComponent(tableScrollButton, GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
						.addGap(9)));
		pCustomer.setLayout(gl_pCustomer);
		setLayout(groupLayout);

		setUpCustomer();

	}

	private void setUpCustomer() {

		arrCustomers = customerService.getAll();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

		tableModel.setRowCount(0);

		for (int i = 0; i < arrCustomers.size(); i++) {
			Customer customer = arrCustomers.get(i);
			tableModel.addRow(new Object[] { (i + 1), customer.getId(), customer.getFullName(), customer.getPhone(),
					customer.getEmail(), arrCustomers.get(i).getCreateDate()!= null ?dateFormat.format(customer.getCreateDate()):"Trống" });
		}

	}

	private void save() {

		Customer newCustomer = new Customer();

		if (!txtCustomerID.getText().equals("")) {
			newCustomer.setId(customer.getId());
		} else {
			newCustomer.setId(System.currentTimeMillis());
		}
		newCustomer.setAddress(txtAddress.getText());
		newCustomer.setCreateDate(date.getDate());
		newCustomer.setFullName(txtCustomerName.getText());
		newCustomer.setPhone(Long.parseLong(txtPhone.getText()));
		newCustomer.setEmail(txtEmail.getText());
		newCustomer.setPoint(Integer.parseInt(txtPoint.getText()));
		if (customerService.save(newCustomer)) {
			JOptionPane.showMessageDialog(null, "Lưu khách hàng thành công");
			setUpCustomer();
			clear();
		} else {
			JOptionPane.showMessageDialog(null, "Lưu khách hàng không thành công", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void delete() {
		menu = new JPopupMenu();
		JMenuItem item = new JMenuItem("Xóa");
		menu.add(item);

		item.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				int result = JOptionPane.showConfirmDialog(null, "Bạn muốn xóa khách hàng này?", "Xáo khách hàng",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (result == JOptionPane.YES_OPTION) {
					if (customerService.delete(customer)) {
						JOptionPane.showMessageDialog(null, "Xáo khách hàng thành công");
						clear();
						setUpCustomer();
					}else {
						JOptionPane.showMessageDialog(null, "Xáo khách hàng không thành công", "Xóa khách hàng", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

	}

	private void clear() {
		txtAddress.setText("");
		date.setDate(new Date());
		txtCustomerName.setText("");
		txtPhone.setText("");
		txtEmail.setText("");
		txtPoint.setText("");
		txtCustomerID.setText("");

	}

}
