package com.coffee.form;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.coffee.entity.Customer;
import com.coffee.service.CustomerService;
import com.coffee.serviceimpl.CustomerServiceImpl;

/**
 * @author Nguyen Phuc Thinh
 */
public class Dialog_AddAndUpdateCustomer extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4720431504715270860L;
	private final JPanel contentPanel = new JPanel();
	private CustomerService customerService;
	
	private JTextField txtName;
	private JTextField txtEmail;
	private JTextField txtPhone;
	private JTextArea txtAddress;
	private JButton btnSave;
	private JLabel lblNewLabel2;
	private JLabel lblNewLabel1;
	private JLabel lblNewLabel3;

	/**
	 * Create the dialog.
	 */
	public Dialog_AddAndUpdateCustomer(Customer customer, Form_Customer form) {
		setBounds(100, 100, 570, 516);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Tên khách hàng:");
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel.setBounds(60, 100, 106, 30);
		contentPanel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Thông tin khách hàng");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.BOLD, 23));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 10, 536, 56);
		contentPanel.add(lblNewLabel_1);

		txtName = new JTextField();

		txtName.setBounds(176, 100, 283, 30);
		contentPanel.add(txtName);
		txtName.setColumns(10);

		lblNewLabel1 = new JLabel("Email:");
		lblNewLabel1.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel1.setBounds(60, 153, 106, 30);
		contentPanel.add(lblNewLabel1);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(176, 153, 283, 30);
		contentPanel.add(txtEmail);

		lblNewLabel2 = new JLabel("Số điện thoại:");
		lblNewLabel2.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel2.setBounds(60, 217, 106, 30);
		contentPanel.add(lblNewLabel2);

		txtPhone = new JTextField();
		txtPhone.setColumns(10);
		txtPhone.setBounds(176, 217, 283, 30);
		contentPanel.add(txtPhone);

		lblNewLabel3 = new JLabel("Địa chỉ:");
		lblNewLabel3.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel3.setBounds(60, 278, 106, 30);
		contentPanel.add(lblNewLabel3);

		txtAddress = new JTextArea();
		txtAddress.setBounds(176, 278, 283, 89);
		contentPanel.add(txtAddress);

		btnSave = new JButton("Lưu khách hàng");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveCustomr(customer, form);
			}

		});
		btnSave.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnSave.setBounds(176, 399, 188, 45);
		contentPanel.add(btnSave);

		if (customer != null) {
			txtName.setText(customer.getFullName());
			txtEmail.setText(customer.getEmail());
			txtAddress.setText(customer.getAddress());
			txtPhone.setText(String.valueOf(customer.getPhone()));
		}
	}
	
	private void saveCustomr(Customer customer, Form_Customer form) {
		// TODO Auto-generated method stub
		
		customerService = new CustomerServiceImpl();
		if(customer !=null) {
			customer.setAddress(txtAddress.getText());
			customer.setEmail(txtEmail.getText());
			customer.setFullName(txtName.getText());
			customer.setPhone(Long.parseLong(txtPhone.getText()));
		}else {
			customer = new Customer();
			customer.setId(System.currentTimeMillis());
			customer.setAddress(txtAddress.getText());
			customer.setEmail(txtEmail.getText());
			customer.setFullName(txtName.getText());
			customer.setPhone(Long.parseLong(txtPhone.getText()));
			customer.setPoint(0);
			customer.setCreateDate(new Date());
			
		}
		
		if(customerService.save(customer)) {
			JOptionPane.showMessageDialog(null, "Lưu khách hàng thành công");
			dispose();
			form.getCustomer();
		}else {
			JOptionPane.showMessageDialog(null, "Đã có lỗi xảy ra");
		}
		
	}
}
