package com.coffee.form;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.coffee.custom.LoginPanel;
import com.coffee.custom.MyButton;
import com.coffee.custom.PasswordField;
import com.coffee.custom.TextField;
import com.coffee.entity.Employee;
import com.coffee.service.EmployeeService;
import com.coffee.serviceimpl.EmployeeServiceImpl;

/**
 * @author Nguyen Phuc Thinh
 */
public class Form_Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7958410676933735716L;
	private LoginPanel contentPane;
	private JLabel lblLogo;
	private TextField txtID;
	private PasswordField txtPassword;
	private MyButton btnNewButton;
	private JLabel lblClose;

	private Employee emp;
	private EmployeeService service;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Form_Login frame = new Form_Login();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame. @
	 */
	public Form_Login() {

		service = new EmployeeServiceImpl();

		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 769, 553);
		contentPane = new LoginPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblLogo = new JLabel();
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setBounds(319, 52, 130, 130);
		ImageIcon icon = new ImageIcon("image/logo-coffee.png");
		Image scaleImage = icon.getImage().getScaledInstance(130, 130, Image.SCALE_DEFAULT);
		lblLogo.setIcon(new ImageIcon(scaleImage)); // NOI18N

		contentPane.add(lblLogo);

		txtID = new TextField();
		txtID.setForeground(Color.WHITE);
		txtID.setOpaque(false);
		txtID.setFont(new Font("SansSerif", Font.BOLD, 12));
		txtID.setLabelText("M?? nh??n vi??n");
		txtID.setBounds(190, 215, 389, 45);
		contentPane.add(txtID);
		txtID.setColumns(10);

		txtPassword = new PasswordField();
		txtPassword.setForeground(Color.WHITE);
		txtPassword.setOpaque(false);
		txtPassword.setBorder(new EmptyBorder(10, 0, 0, 10));
		txtPassword.setFont(new Font("SansSerif", Font.BOLD, 12));
		txtPassword.setLabelText("M???t kh???u");
		txtPassword.setColumns(10);
		txtPassword.setBounds(190, 288, 389, 45);
		contentPane.add(txtPassword);

		btnNewButton = new MyButton();
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.setText("????NG NH???P");
		btnNewButton.setBounds(282, 369, 205, 45);
		contentPane.add(btnNewButton);

		lblClose = new JLabel("X");
		lblClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		lblClose.setVerticalAlignment(SwingConstants.TOP);
		lblClose.setForeground(Color.WHITE);
		lblClose.setOpaque(true);
		lblClose.setBackground(Color.RED);
		lblClose.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblClose.setHorizontalAlignment(SwingConstants.CENTER);
		lblClose.setBounds(739, 10, 20, 20);
		contentPane.add(lblClose);
	}

	@SuppressWarnings("deprecation")
	private boolean Login() {

		if (txtID.getText().equals("") || txtPassword.getText().equals("")) {

			JOptionPane.showMessageDialog(null, "Vui long nh???p ?????y ????? th??ng tin!", null, JOptionPane.WARNING_MESSAGE);
			return false;
		}
		Long id = Long.parseLong(txtID.getText());
		String pass = txtPassword.getText();

		emp = service.Login(id);
		if (emp == null) {
			JOptionPane.showMessageDialog(null, "M?? ????ng nh???p ko ch??nh x??c!", null, JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if (!emp.getAccount().getPassword().equals(pass)) {
			JOptionPane.showMessageDialog(null, "M???t kh???u ko ch??nh x??c!", null, JOptionPane.WARNING_MESSAGE);
			return false;
		}

		if(!emp.getPosition().getPositionName().equals("Qu???n l??")) {
			JOptionPane.showMessageDialog(null, "B???n kh??ng c?? quy???n truy c???p", null, JOptionPane.WARNING_MESSAGE);
			return false;
		}

		JOptionPane.showMessageDialog(null, "????ng nh???p th??nh c??ng!");
		Form_Main main = new Form_Main(emp);
		main.setExtendedState(JFrame.MAXIMIZED_BOTH);
		main.setLocationRelativeTo(null);
		main.setVisible(true);
		dispose();

		
	return true;


	}
	
}
