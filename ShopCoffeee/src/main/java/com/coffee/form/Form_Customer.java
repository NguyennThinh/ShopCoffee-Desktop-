package com.coffee.form;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.coffee.custom.PanelBorder;
import com.coffee.entity.Customer;
import com.coffee.service.CustomerService;
import com.coffee.serviceimpl.CustomerServiceImpl;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.util.List;

import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author Nguyen Phuc Thinh
 */
public class Form_Customer extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7472087535548726704L;
	private CustomerService customerService;
	private List<Customer> arrCustomers;
	private Customer customer;
	
	private PanelBorder panel;
	private JScrollPane scrollPane;
	private JLabel lblNewLabel;
	private JLabel lblName;
	private JLabel lblNewLabel_2;
	private JLabel lblCusId;
	private JLabel lblNewLabel_4;
	private JLabel lblPhone;
	private JLabel lblNewLabel_6;
	private JLabel lblEmail;
	private JLabel lblNewLabel_8;
	private JLabel lblAddress;
	private JLabel lblNewLabel_10;
	private JLabel lblCreateDate;
	private JLabel lblNewLabel_12;
	private JLabel lblPoint;
	private JLabel lblImage;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;

	private JTable cusTable;
	private DefaultTableModel tableModel;
	private JLabel lblNewLabel_1;
	private JTextField txtSearch;
	/**
	 * Create the panel.
	 */
	public Form_Customer() {
		setLayout(new BorderLayout(0, 0));
		
		panel = new PanelBorder();
		panel.setBackground(Color.WHITE);
		add(panel, BorderLayout.CENTER);
		
		scrollPane = new JScrollPane();
		String[] header = {"STT", "Tên khách hàng", "Số điện thoại","Địa chỉ", "Điểm"};
		tableModel = new DefaultTableModel(header, 0);
		cusTable = new JTable(tableModel);
		cusTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				
				customer = arrCustomers.get(cusTable.getSelectedRow());
				
				lblAddress.setText(customer.getAddress());
				lblCreateDate.setText(customer.getCreateDate()!=null?format.format(customer.getCreateDate()):"Trống");
				lblCusId.setText(customer.getId()+"");
				
				lblEmail.setText(customer.getEmail());
				lblName.setText(customer.getFullName());
				lblPhone.setText(customer.getPhone()+"");
				lblPoint.setText(customer.getPoint()+"");
				
				
				
				
			}
		});
		scrollPane.setViewportView(cusTable);
		
		
		lblNewLabel = new JLabel("Tên khách hàng:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		lblName = new JLabel("Trống");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		lblNewLabel_2 = new JLabel("Mã khách hàng:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		lblCusId = new JLabel("Trống");
		lblCusId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		lblNewLabel_4 = new JLabel("Số điện thoại:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		lblPhone = new JLabel("Trống");
		lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		lblNewLabel_6 = new JLabel("Email:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		lblEmail = new JLabel("Trống");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		lblNewLabel_8 = new JLabel("Địa chỉ:");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		lblAddress = new JLabel("Trống");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		lblNewLabel_10 = new JLabel("Ngày tạo:");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		lblCreateDate = new JLabel("Trống");
		lblCreateDate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		lblNewLabel_12 = new JLabel("Điểm tích lũy:");
		lblNewLabel_12.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		lblPoint = new JLabel("Trống");
		lblPoint.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		lblImage = new JLabel();
		ImageIcon img = new ImageIcon("image/customer_image.png");
		Image newImage = img.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		lblImage.setIcon(new ImageIcon(newImage));
		
		btnNewButton = new JButton("Thêm khách hàng");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dialog_AddAndUpdateCustomer AddandUpdateCustomer = new Dialog_AddAndUpdateCustomer(null, Form_Customer.this);
				
				AddandUpdateCustomer.setLocationRelativeTo(null);
				AddandUpdateCustomer.setVisible(true);
			}
		});
		
		btnNewButton_1 = new JButton("Xóa khách hàng");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(customer != null) {
					int result = JOptionPane.showConfirmDialog(null,"Bạn muốn xóa khách hàng này?", "Xóa khách hàng",
				               JOptionPane.YES_NO_OPTION,
				               JOptionPane.QUESTION_MESSAGE);
				            if(result == JOptionPane.YES_OPTION){
				               if(customerService.delete(customer)) {
				            	   JOptionPane.showMessageDialog(null, "Xóa thành công");
				               }else {
				            	   JOptionPane.showMessageDialog(null, "Đã có lỗi xảy ra", "Xóa khách hàng", JOptionPane.ERROR_MESSAGE);
				               }
				            }
				}else {
					   JOptionPane.showMessageDialog(null, "Bạn chưa chọn khách hàng");
				}
			}
		});
		
		btnNewButton_2 = new JButton("Cập nhật");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dialog_AddAndUpdateCustomer AddandUpdateCustomer = new Dialog_AddAndUpdateCustomer(customer, Form_Customer.this);
				
				AddandUpdateCustomer.setLocationRelativeTo(null);
				AddandUpdateCustomer.setVisible(true);
				
			}
		});
		
		lblNewLabel_1 = new JLabel("Tìm khách hàng:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				search(txtSearch.getText(), cusTable, tableModel);
			}
		});
		txtSearch.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(111)
							.addComponent(lblImage, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(lblCusId, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(lblPhone, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_8, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(lblAddress, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_10, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(lblCreateDate, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_12, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(lblPoint, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)))
					.addGap(10))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblImage, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
							.addGap(25)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCusId, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
							.addGap(25)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPhone, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
							.addGap(25)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
							.addGap(25)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_8, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAddress, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
							.addGap(25)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_10, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCreateDate, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
							.addGap(25)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_12, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPoint, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
							.addGap(31)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnNewButton)
								.addComponent(btnNewButton_1)
								.addComponent(btnNewButton_2))))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		getCustomer();
	}

	public void getCustomer() {
		customerService = new CustomerServiceImpl();
		
		arrCustomers = customerService.getAll();
		
		tableModel.setRowCount(0);
		
		for(int i =0; i < arrCustomers.size(); i++) {
			tableModel.addRow(new Object[] {(i+1), arrCustomers.get(i).getFullName(), arrCustomers.get(i).getPhone(), arrCustomers.get(i).getAddress(), arrCustomers.get(i).getPoint()});
		}
		
		
		
	}
	private void search(String str , JTable table, DefaultTableModel model ) {
		TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(model);
		table.setRowSorter(tr);
		tr.setRowFilter(RowFilter.regexFilter(str));
	}
}
