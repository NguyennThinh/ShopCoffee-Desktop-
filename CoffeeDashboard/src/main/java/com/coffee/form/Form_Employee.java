package com.coffee.form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import com.coffee.custom.ComboBoxSuggestion;
import com.coffee.custom.MaterialTabbed;
import com.coffee.custom.PanelBorder;
import com.coffee.custom.TableCustom;
import com.coffee.custom.TableScrollButton;
import com.coffee.entity.Account;
import com.coffee.entity.Employee;
import com.coffee.entity.Position;
import com.coffee.service.EmployeeService;
import com.coffee.service.PositionService;
import com.coffee.serviceimpl.EmployeeServiceImpl;
import com.coffee.serviceimpl.PostionServiceImpl;
import com.toedter.calendar.JDateChooser;

/**
 * @author Nguyen Phuc Thinh
 */
public class Form_Employee extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -517911612103617791L;
	private EmployeeService employeeService;
	private PositionService postionService;

	private List<Employee> arrEmployees;
	private List<Position> arrPositions;

	private Employee employee;
	private Position position;
	private byte[] imageBytes;

	private MaterialTabbed tabProduct;
	private JPanel pEmployee;
	private JPanel pPosition;
	private PanelBorder pEmp;
	private PanelBorder pDetails;
	private JLabel lblDanhSchNhn;
	private JLabel lblNhpTnNhn;
	private JTextField txtSearch;
	private ComboBoxSuggestion<String> cmbFilter;
	private JLabel lblLoiSnPhm;
	private JLabel lblChiTitNhn;
	private TableScrollButton tableScrollButton;
	private JScrollPane srcEmployee;
	private JTextField txtEmployeeID;
	private JLabel lblMSnPhm;
	private JLabel lblTnSnPhm;
	private JTextField txtEmployeeName;
	private JLabel lblNgySinh;
	private JTextField txtPhone;
	private JLabel lblSinThoi;
	private JTextField txtEmail;
	private JLabel lblEmail;
	private JDateChooser birthday;
	private JLabel lblChcV;
	private ComboBoxSuggestion<String> cmbPosition;
	private JLabel lblaCh;
	private JTextField txtAddress;
	private JPanel pImage;
	private JButton btnChooseImage;
	private JLabel lblImage;
	private JButton btnLuNhnVin;
	private PanelBorder pPos;
	private PanelBorder pPos_Details;
	private JLabel lblDanhSchChc;
	private JLabel lblNhpTnChc;
	private JTextField textField;
	private TableScrollButton tableScroll;
	private JScrollPane scrollPane;
	private JLabel lblMChcV;
	private JTextField txtPostionID;
	private JTextField txtPositionName;
	private JLabel lblTnChcV;
	private JLabel lblNhnVinHin;
	private JTextField txtAmount;
	private JLabel lblChiTitChc;
	private JButton btnLuChcV;
	private DefaultTableModel tableModel, modelPos;
	private JTable table, tablePos;
	private JPopupMenu menu;

	/**
	 * Create the panel.
	 * 
	 * @
	 */
	public Form_Employee() {

		employeeService = new EmployeeServiceImpl();
		postionService = new PostionServiceImpl();

		tabProduct = new MaterialTabbed();
		tabProduct.setFont(new Font("Tahoma", Font.BOLD, 12));

		pEmployee = new JPanel();
		tabProduct.addTab("Nhân viên", null, pEmployee, null);

		initEmployee();

		pPosition = new JPanel();
		tabProduct.addTab("Chức vụ", null, pPosition, null);

		initPosition();
		setUpPosition();
		setUpEmployee();

	}

	/**
	 * 
	 */
	private void initEmployee() {
		// TODO Auto-generated method stub
		pEmp = new PanelBorder();
		pEmp.setBackground(Color.WHITE);

		lblDanhSchNhn = new JLabel();
		lblDanhSchNhn.setText("Danh sách nhân viên");
		lblDanhSchNhn.setForeground(new Color(127, 127, 127));
		lblDanhSchNhn.setFont(new Font("SansSerif", Font.BOLD, 18));

		lblNhpTnNhn = new JLabel();
		lblNhpTnNhn.setText("Nhập tên nhân viên:");
		lblNhpTnNhn.setForeground(new Color(127, 127, 127));
		lblNhpTnNhn.setFont(new Font("SansSerif", Font.BOLD, 12));

		txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				search(txtSearch.getText(), tableModel, table);

			}
		});
		txtSearch.setBackground(new Color(240, 240, 240));
		txtSearch.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtSearch.setColumns(10);
		txtSearch.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));

		cmbFilter = new ComboBoxSuggestion<String>();
		cmbFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cmbFilter.getSelectedIndex() ==0) {
					search("", tableModel, table);
				}else {
					search(cmbFilter.getSelectedItem().toString(), tableModel, table);
				}
			}
		});
		cmbFilter.setBackground(new Color(240, 240, 240));

		lblLoiSnPhm = new JLabel();
		lblLoiSnPhm.setText("Chức vụ:");
		lblLoiSnPhm.setForeground(new Color(127, 127, 127));
		lblLoiSnPhm.setFont(new Font("SansSerif", Font.BOLD, 12));

		tableScrollButton = new TableScrollButton();

		srcEmployee = new JScrollPane();

		String[] header = { "STT", "Mã nhân viên", "Tên nhân viên", "Chức vụ", "Số điện thoại", "Email", "Địa chỉ" };
		tableModel = new DefaultTableModel(header, 0);

		table = new JTable(tableModel) {
			/**
			* 
			*/
			private static final long serialVersionUID = 1L;

			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component c = super.prepareRenderer(renderer, row, column);

				// Alternate row color

				if (!isRowSelected(row))
					c.setBackground(row % 2 == 0 ? getBackground() : Color.LIGHT_GRAY);

				return c;
			}
		};

		table.getColumnModel().getColumn(0).setPreferredWidth(10);
		table.setRowHeight(25);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();

				if(row != -1) {
					employee = arrEmployees.get(row);

					txtEmployeeID.setText(String.valueOf(arrEmployees.get(row).getId()));
					txtAddress.setText(arrEmployees.get(row).getAddress());
					txtEmployeeName.setText(arrEmployees.get(row).getFullName());
					txtEmail.setText(arrEmployees.get(row).getEmail());
					txtPhone.setText(arrEmployees.get(row).getPhone().toString());
					birthday.setDate(arrEmployees.get(row).getBirthday());
					if (arrEmployees.get(row).getImage() != null) {
						ImageIcon img = new ImageIcon(arrEmployees.get(row).getImage());
						Image newImage = img.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
						lblImage.setIcon(new ImageIcon(newImage));
					}
					cmbPosition.setSelectedItem(table.getValueAt(row, 3));

					if (SwingUtilities.isRightMouseButton(e)) {
						delete();
						menu.show(table, e.getX(), e.getY());
					}
				}
			}
		});

		srcEmployee.setViewportView(table);
		TableCustom.apply(srcEmployee, TableCustom.TableType.MULTI_LINE);
		tableScrollButton.add(srcEmployee, BorderLayout.CENTER);
		GroupLayout gl_pEmp = new GroupLayout(pEmp);
		gl_pEmp.setHorizontalGroup(gl_pEmp.createParallelGroup(Alignment.LEADING).addGroup(gl_pEmp
				.createSequentialGroup().addGap(10)
				.addComponent(lblDanhSchNhn, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE).addGap(10)
				.addComponent(lblNhpTnNhn, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE).addGap(115)
				.addComponent(lblLoiSnPhm, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_pEmp.createSequentialGroup().addGap(223)
						.addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE).addGap(52)
						.addComponent(cmbFilter, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_pEmp.createSequentialGroup().addGap(10)
						.addComponent(tableScrollButton, GroupLayout.DEFAULT_SIZE, 655, Short.MAX_VALUE).addGap(10)));
		gl_pEmp.setVerticalGroup(gl_pEmp.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pEmp.createSequentialGroup().addGap(10)
						.addGroup(gl_pEmp.createParallelGroup(Alignment.LEADING).addComponent(lblDanhSchNhn)
								.addComponent(lblNhpTnNhn, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblLoiSnPhm, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pEmp.createParallelGroup(Alignment.LEADING)
								.addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
								.addComponent(cmbFilter, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
						.addGap(12).addComponent(tableScrollButton, GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
						.addGap(10)));
		pEmp.setLayout(gl_pEmp);

		pDetails = new PanelBorder();
		pDetails.setBackground(Color.WHITE);

		lblChiTitNhn = new JLabel();
		lblChiTitNhn.setText("Chi tiết nhân viên");
		lblChiTitNhn.setForeground(new Color(127, 127, 127));
		lblChiTitNhn.setFont(new Font("SansSerif", Font.BOLD, 18));

		txtEmployeeID = new JTextField();
		txtEmployeeID.setEnabled(false);
		txtEmployeeID.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtEmployeeID.setColumns(10);
		txtEmployeeID.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));

		lblMSnPhm = new JLabel();
		lblMSnPhm.setText("Mã nhân viên:");
		lblMSnPhm.setForeground(new Color(127, 127, 127));
		lblMSnPhm.setFont(new Font("SansSerif", Font.BOLD, 12));

		lblTnSnPhm = new JLabel();
		lblTnSnPhm.setText("Tên nhân viên:");
		lblTnSnPhm.setForeground(new Color(127, 127, 127));
		lblTnSnPhm.setFont(new Font("SansSerif", Font.BOLD, 12));

		txtEmployeeName = new JTextField();
		txtEmployeeName.setBackground(new Color(240, 240, 240));
		txtEmployeeName.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtEmployeeName.setColumns(10);
		txtEmployeeName.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));

		lblNgySinh = new JLabel();
		lblNgySinh.setText("Ngày sinh:");
		lblNgySinh.setForeground(new Color(127, 127, 127));
		lblNgySinh.setFont(new Font("SansSerif", Font.BOLD, 12));

		txtPhone = new JTextField();
		txtPhone.setBackground(new Color(240, 240, 240));
		txtPhone.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtPhone.setColumns(10);
		txtPhone.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));

		lblSinThoi = new JLabel();
		lblSinThoi.setText("Số điện thoại:");
		lblSinThoi.setForeground(new Color(127, 127, 127));
		lblSinThoi.setFont(new Font("SansSerif", Font.BOLD, 12));

		txtEmail = new JTextField();
		txtEmail.setBackground(new Color(240, 240, 240));
		txtEmail.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtEmail.setColumns(10);
		txtEmail.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));

		lblEmail = new JLabel();
		lblEmail.setText("Email:");
		lblEmail.setForeground(new Color(127, 127, 127));
		lblEmail.setFont(new Font("SansSerif", Font.BOLD, 12));

		birthday = new JDateChooser();
		birthday.setBackground(new Color(240, 240, 240));
		birthday.setFont(new Font("Tahoma", Font.BOLD, 12));
		birthday.setDateFormatString("yyyy-MM-dd");

		lblChcV = new JLabel();
		lblChcV.setText("Chức vụ:");
		lblChcV.setForeground(new Color(127, 127, 127));
		lblChcV.setFont(new Font("SansSerif", Font.BOLD, 12));

		cmbPosition = new ComboBoxSuggestion<String>();
		cmbPosition.setBackground(SystemColor.menu);

		lblaCh = new JLabel();
		lblaCh.setText("Địa chỉ:");
		lblaCh.setForeground(new Color(127, 127, 127));
		lblaCh.setFont(new Font("SansSerif", Font.BOLD, 12));

		txtAddress = new JTextField();
		txtAddress.setBackground(new Color(240, 240, 240));
		txtAddress.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtAddress.setColumns(10);
		txtAddress.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));

		pImage = new JPanel();
		pImage.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pImage.setLayout(new BorderLayout(0, 0));

		lblImage = new JLabel("Hình ảnh");
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		pImage.add(lblImage, BorderLayout.CENTER);

		btnChooseImage = new JButton("Chọn ảnh");
		btnChooseImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooseImage();
			}
		});
		btnChooseImage.setFont(new Font("Tahoma", Font.BOLD, 12));

		btnLuNhnVin = new JButton("Lưu nhân viên");
		btnLuNhnVin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		btnLuNhnVin.setBorder(null);
		btnLuNhnVin.setBackground(new Color(240, 240, 240));
		btnLuNhnVin.setFont(new Font("SansSerif", Font.PLAIN, 12));
		GroupLayout gl_pDetails = new GroupLayout(pDetails);
		gl_pDetails.setHorizontalGroup(gl_pDetails.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pDetails.createSequentialGroup().addGap(10).addComponent(lblChiTitNhn,
						GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_pDetails.createSequentialGroup().addGap(180).addComponent(pImage,
						GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_pDetails.createSequentialGroup().addGap(180).addComponent(btnChooseImage,
						GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_pDetails.createSequentialGroup().addGap(10)
						.addComponent(lblMSnPhm, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE).addGap(10)
						.addComponent(txtEmployeeID, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE).addGap(10))
				.addGroup(gl_pDetails.createSequentialGroup().addContainerGap()
						.addComponent(lblTnSnPhm, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
						.addGap(10).addComponent(txtEmployeeName, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
						.addContainerGap())
				.addGroup(Alignment.TRAILING,
						gl_pDetails.createSequentialGroup().addContainerGap()
								.addComponent(lblNgySinh, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
								.addGap(10).addComponent(birthday, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
								.addContainerGap())
				.addGroup(gl_pDetails.createSequentialGroup().addContainerGap()
						.addComponent(lblSinThoi, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
						.addGap(10).addComponent(txtPhone, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
						.addContainerGap())
				.addGroup(gl_pDetails.createSequentialGroup().addContainerGap()
						.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE).addGap(10)
						.addComponent(txtEmail, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE).addContainerGap())
				.addGroup(Alignment.TRAILING, gl_pDetails.createSequentialGroup().addContainerGap()
						.addComponent(lblaCh, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE).addGap(10)
						.addComponent(txtAddress, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE).addContainerGap())
				.addGroup(Alignment.TRAILING,
						gl_pDetails.createSequentialGroup().addGap(153)
								.addComponent(btnLuNhnVin, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(108, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_pDetails.createSequentialGroup().addContainerGap()
						.addComponent(lblChcV, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE).addGap(10)
						.addComponent(cmbPosition, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE).addContainerGap()));
		gl_pDetails.setVerticalGroup(gl_pDetails.createParallelGroup(Alignment.LEADING).addGroup(gl_pDetails
				.createSequentialGroup().addGap(10).addComponent(lblChiTitNhn).addGap(10)
				.addComponent(pImage, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE).addGap(10)
				.addComponent(btnChooseImage, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE).addGap(24)
				.addGroup(gl_pDetails.createParallelGroup(Alignment.LEADING)
						.addComponent(lblMSnPhm, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtEmployeeID, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_pDetails.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTnSnPhm, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtEmployeeName, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_pDetails.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNgySinh, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(birthday, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_pDetails.createParallelGroup(Alignment.LEADING)
						.addComponent(lblSinThoi, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtPhone, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_pDetails.createParallelGroup(Alignment.LEADING)
						.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_pDetails.createParallelGroup(Alignment.LEADING)
						.addComponent(lblChcV, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(cmbPosition, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
				.addGap(15)
				.addGroup(gl_pDetails.createParallelGroup(Alignment.LEADING)
						.addComponent(lblaCh, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtAddress, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
				.addGap(18).addComponent(btnLuNhnVin, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
				.addGap(39)));
		pDetails.setLayout(gl_pDetails);
		GroupLayout gl_pEmployee = new GroupLayout(pEmployee);
		gl_pEmployee.setHorizontalGroup(gl_pEmployee.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pEmployee.createSequentialGroup().addGap(10)
						.addComponent(pEmp, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGap(10).addComponent(pDetails, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(10)));
		gl_pEmployee.setVerticalGroup(gl_pEmployee.createParallelGroup(Alignment.LEADING).addGroup(gl_pEmployee
				.createSequentialGroup().addGap(10)
				.addGroup(gl_pEmployee.createParallelGroup(Alignment.LEADING)
						.addComponent(pEmp, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(pDetails, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGap(10)));
		pEmployee.setLayout(gl_pEmployee);

	}

	/**
	 * 
	 */
	private void initPosition() {
		// TODO Auto-generated method stub
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(10)
						.addComponent(tabProduct, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGap(10)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(10)
						.addComponent(tabProduct, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGap(10)));
		setLayout(groupLayout);

		pPos = new PanelBorder();
		pPos.setBackground(Color.WHITE);

		lblDanhSchChc = new JLabel();
		lblDanhSchChc.setText("Danh sách chức vụ");
		lblDanhSchChc.setForeground(new Color(127, 127, 127));
		lblDanhSchChc.setFont(new Font("SansSerif", Font.BOLD, 18));
		pPos.add(lblDanhSchChc);

		lblNhpTnChc = new JLabel();
		lblNhpTnChc.setText("Nhập tên chức vụ:");
		lblNhpTnChc.setForeground(new Color(127, 127, 127));
		lblNhpTnChc.setFont(new Font("SansSerif", Font.BOLD, 12));
		pPos.add(lblNhpTnChc);

		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				search(textField.getText(), modelPos, tablePos);
			}
		});
		textField.setFont(new Font("SansSerif", Font.PLAIN, 12));
		textField.setColumns(10);
		textField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textField.setBackground(SystemColor.menu);
		pPos.add(textField);

		tableScroll = new TableScrollButton();
		pPos.add(tableScroll);

		scrollPane = new JScrollPane();
		String[] header = { "STT", "Mã chức vụ", "Tên chức vụ" };
		modelPos = new DefaultTableModel(header, 0);
		tablePos = new JTable(modelPos);
		tablePos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tablePos.getSelectedRow();
				position = arrPositions.get(row);

				txtPostionID.setText(position.getPosition_id());
				txtPositionName.setText(position.getPositionName());
				txtAmount.setText(position.getEmployees().size() + "");
				if (SwingUtilities.isRightMouseButton(e)) {
					if (row != -1) {
						deletePos();
						menu.show(tablePos, e.getX(), e.getY());
					}
				}
			}
		});
		scrollPane.setViewportView(tablePos);
		TableCustom.apply(scrollPane, TableCustom.TableType.MULTI_LINE);
		tableScroll.add(scrollPane, BorderLayout.CENTER);

		pPos_Details = new PanelBorder();
		pPos_Details.setBackground(Color.WHITE);
		pPos_Details.setLayout(null);

		lblMChcV = new JLabel();
		lblMChcV.setText("Mã chức vụ:");
		lblMChcV.setForeground(new Color(127, 127, 127));
		lblMChcV.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblMChcV.setBounds(10, 64, 121, 24);
		pPos_Details.add(lblMChcV);

		txtPostionID = new JTextField();
		txtPostionID.setBackground(new Color(240, 240, 240));
		txtPostionID.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtPostionID.setEnabled(false);
		txtPostionID.setColumns(10);
		txtPostionID.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtPostionID.setBounds(141, 64, 260, 24);
		pPos_Details.add(txtPostionID);

		txtPositionName = new JTextField();
		txtPositionName.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtPositionName.setColumns(10);
		txtPositionName.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtPositionName.setBackground(SystemColor.menu);
		txtPositionName.setBounds(141, 127, 260, 24);
		pPos_Details.add(txtPositionName);

		lblTnChcV = new JLabel();
		lblTnChcV.setText("Tên chức vụ:");
		lblTnChcV.setForeground(new Color(127, 127, 127));
		lblTnChcV.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblTnChcV.setBounds(10, 127, 121, 24);
		pPos_Details.add(lblTnChcV);

		lblNhnVinHin = new JLabel();
		lblNhnVinHin.setText("Nhân viên hiện tại:");
		lblNhnVinHin.setForeground(new Color(127, 127, 127));
		lblNhnVinHin.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNhnVinHin.setBounds(10, 190, 121, 24);
		pPos_Details.add(lblNhnVinHin);

		txtAmount = new JTextField();
		txtAmount.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtAmount.setColumns(10);
		txtAmount.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtAmount.setBackground(SystemColor.menu);
		txtAmount.setBounds(141, 190, 260, 24);
		pPos_Details.add(txtAmount);

		lblChiTitChc = new JLabel();
		lblChiTitChc.setText("Chi tiết chức vụ");
		lblChiTitChc.setForeground(new Color(127, 127, 127));
		lblChiTitChc.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblChiTitChc.setBounds(10, 10, 203, 24);
		pPos_Details.add(lblChiTitChc);

		btnLuChcV = new JButton("Lưu chức vụ");
		btnLuChcV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				savePosition();
			}
		});
		btnLuChcV.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnLuChcV.setBorder(null);
		btnLuChcV.setBackground(SystemColor.menu);
		btnLuChcV.setBounds(160, 254, 150, 37);
		pPos_Details.add(btnLuChcV);
		GroupLayout gl_pPosition = new GroupLayout(pPosition);
		gl_pPosition
				.setHorizontalGroup(
						gl_pPosition.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_pPosition.createSequentialGroup().addContainerGap()
										.addComponent(pPos, GroupLayout.DEFAULT_SIZE, 810, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(pPos_Details,
												GroupLayout.PREFERRED_SIZE, 413, GroupLayout.PREFERRED_SIZE)
										.addContainerGap()));
		gl_pPosition.setVerticalGroup(gl_pPosition.createParallelGroup(Alignment.TRAILING).addGroup(gl_pPosition
				.createSequentialGroup()
				.addGroup(gl_pPosition.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING,
								gl_pPosition.createSequentialGroup().addGap(11).addComponent(pPos,
										GroupLayout.DEFAULT_SIZE, 645, Short.MAX_VALUE))
						.addGroup(gl_pPosition.createSequentialGroup().addContainerGap().addComponent(pPos_Details,
								GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
				.addContainerGap()));
		GroupLayout gl_pPos = new GroupLayout(pPos);
		gl_pPos.setHorizontalGroup(gl_pPos.createParallelGroup(Alignment.LEADING).addGroup(gl_pPos
				.createSequentialGroup().addGap(10)
				.addGroup(gl_pPos.createParallelGroup(Alignment.LEADING).addGroup(gl_pPos.createSequentialGroup()
						.addComponent(lblDanhSchChc, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)
						.addGap(10)
						.addComponent(lblNhpTnChc, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
						.addGap(11)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE))
						.addComponent(tableScroll, GroupLayout.DEFAULT_SIZE, 657, Short.MAX_VALUE))
				.addGap(10)));
		gl_pPos.setVerticalGroup(gl_pPos.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pPos.createSequentialGroup().addGap(10)
						.addGroup(gl_pPos.createParallelGroup(Alignment.LEADING).addComponent(lblDanhSchChc)
								.addComponent(lblNhpTnChc, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_pPos.createSequentialGroup().addGap(2).addComponent(textField,
										GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)))
						.addGap(34).addComponent(tableScroll, GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
						.addGap(9)));
		pPos.setLayout(gl_pPos);
		pPosition.setLayout(gl_pPosition);
	}

	private void chooseImage() {
		JFileChooser fileChooser = new JFileChooser();

		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

		int result = fileChooser.showOpenDialog(this);

		if (result == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			try {
				imageBytes = LoadImage(file.getPath());
				ImageIcon img = new ImageIcon(imageBytes);
				Image newImage = img.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
				lblImage.setIcon(new ImageIcon(newImage));

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public static byte[] LoadImage(String filePath) throws Exception {
		File file = new File(filePath);
		int size = (int) file.length();
		byte[] buffer = new byte[size];
		FileInputStream in = new FileInputStream(file);
		in.read(buffer);
		in.close();
		return buffer;
	}

	public void setUpPosition() {

		arrPositions = postionService.getAll();
		cmbPosition.removeAllItems();
		cmbFilter.removeAllItems();

		cmbFilter.addItem("Tất cả");

		modelPos.setRowCount(0);
		for (int i = 0; i < arrPositions.size(); i++) {
			cmbPosition.addItem(arrPositions.get(i).getPositionName());
			cmbFilter.addItem(arrPositions.get(i).getPositionName());
			modelPos.addRow(new Object[] { (i + 1), arrPositions.get(i).getPosition_id(),
					arrPositions.get(i).getPositionName() });
		}

	}

	public void setUpEmployee() {

		arrEmployees = employeeService.getAll();
		tableModel.setRowCount(0);
		for (int i = 0; i < arrEmployees.size(); i++) {
			Employee emp = arrEmployees.get(i);

			tableModel.addRow(new Object[] { (i + 1), emp.getId(), emp.getFullName(),
					emp.getPosition() == null ? "" : emp.getPosition().getPositionName(), emp.getPhone(),
					emp.getEmail(), emp.getAddress() });

		}

	}

	private void save() {

		long generatedLong = System.currentTimeMillis();

		Employee emp = new Employee();
		emp.setAddress(txtAddress.getText());

		emp.setBirthday(birthday.getDate());
		emp.setEmail(txtEmail.getText());
		emp.setFullName(txtEmployeeName.getText());
		if (imageBytes != null) {
			emp.setImage(imageBytes);
		}
		emp.setPhone(Long.parseLong(txtPhone.getText()));
		emp.setPosition(arrPositions.get(cmbPosition.getSelectedIndex()));

		if (txtEmployeeID.getText().equals("")) {
			emp.setId(generatedLong);
			Account account = new Account();
			account.setId(generatedLong);
			account.setPassword("123456");
			account.setEmployee(emp);
			emp.setAccount(account);
		} else {
			emp.setId(Long.parseLong(txtEmployeeID.getText()));
		}

		if (employeeService.save(emp)) {

			JOptionPane.showMessageDialog(null, "Lưu nhân viên thành công");
			setUpEmployee();
			clear();
		} else {
			JOptionPane.showMessageDialog(null, "Lưu nhân viên không thành công", "Error Message",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	private void delete() {

		menu = new JPopupMenu();

		JMenuItem mDelete = new JMenuItem("Xóa");
		menu.add(mDelete);

		mDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				int result = JOptionPane.showConfirmDialog(null, "Bạn muốn xóa chức vụ này?", "	Xóa nhân viên",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (result == JOptionPane.YES_OPTION) {
					if (employeeService.delete(employee.getId())) {

						JOptionPane.showMessageDialog(null, "Xóa nhân viên thành công");
						setUpEmployee();

					} else {
						JOptionPane.showMessageDialog(null, "Xóa nhân viên không thành công", "Error Message",
								JOptionPane.ERROR_MESSAGE);
					}
				}

			}
		});
	}

	/**
	 * @throws RemoteException
	 * @throws HeadlessException
	 *************************************************************************/

	private void savePosition() {

		List<Position> arrPositions = postionService.getAll();
		String code = "CV" + (arrPositions.size() + 1);
		Position newPostion = new Position();
		if (txtPostionID.getText().equals("")) {
			newPostion.setPosition_id(code);
		} else {
			newPostion.setPosition_id(position.getPosition_id());
		}
		newPostion.setPositionName(txtPositionName.getText());

		if (postionService.save(newPostion)) {
			JOptionPane.showMessageDialog(null, "Lưu chức vụ thành công");
			setUpPosition();
			clearPos();

		} else {
			JOptionPane.showMessageDialog(null, "Lưu chức vụ không thành công", "Error Message",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	private void deletePos() {

		menu = new JPopupMenu();

		JMenuItem mDelete = new JMenuItem("Xóa");
		menu.add(mDelete);

		mDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				int result = JOptionPane.showConfirmDialog(null, "Bạn muốn xóa chức vụ này?", "	Xóa chức vụ",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (result == JOptionPane.YES_OPTION) {

					if (postionService.delete(position)) {
						JOptionPane.showMessageDialog(null, "Xóa chức vụ thành công");
						setUpPosition();
					} else {
						JOptionPane.showMessageDialog(null, "Xóa chức vụ không thành công", "Error Message",
								JOptionPane.ERROR_MESSAGE);
					}
				}
				
				
			}
		});

	}

	private void search(String search, DefaultTableModel model, JTable t) {
		TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(model);
		t.setRowSorter(tr);
		tr.setRowFilter(RowFilter.regexFilter(search));
	}

	private void clear() {
		txtAddress.setText("");
		txtEmail.setText("");
		txtEmployeeID.setText("");
		txtEmployeeName.setText("");
		birthday.setDate(null);
		txtPhone.setText("");
		lblImage.setIcon(new ImageIcon());
		imageBytes = null;
	}

	private void clearPos() {
		txtAmount.setText("");
		txtPositionName.setText("");
		txtPostionID.setText("");

	}

}
