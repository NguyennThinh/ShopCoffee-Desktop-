package com.coffee.form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.coffee.custom.PanelBorder;
import com.coffee.custom.TableCustom;
import com.coffee.custom.TableScrollButton;
import com.coffee.entity.Account;
import com.coffee.entity.Employee;
import com.coffee.service.EmployeeService;
import com.coffee.serviceimpl.EmployeeServiceImpl;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Nguyen Phuc Thinh
 */
public class Form_Account extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7003830228810757491L;
	private EmployeeService employeeService;
	private List<Employee> arrEmployees;

	private PanelBorder panel;
	private JLabel lblDanhSchTi;
	private JLabel lblNhpMNhn;
	private JTextField txtSearch;
	private TableScrollButton tableScrollButton;
	private JScrollPane scrollPane;
	private JTable table;
	private DefaultTableModel modelTable;
	private JPopupMenu menu;
	/**
	 * Create the panel. @
	 */
	public Form_Account() {
		employeeService = new EmployeeServiceImpl();

		panel = new PanelBorder();
		panel.setBackground(Color.WHITE);

		lblDanhSchTi = new JLabel();
		lblDanhSchTi.setText("Danh sách tài khoản");
		lblDanhSchTi.setForeground(new Color(127, 127, 127));
		lblDanhSchTi.setFont(new Font("SansSerif", Font.BOLD, 18));

		lblNhpMNhn = new JLabel();
		lblNhpMNhn.setText("Nhập mã nhân viên:");
		lblNhpMNhn.setForeground(new Color(127, 127, 127));
		lblNhpMNhn.setFont(new Font("SansSerif", Font.BOLD, 12));

		txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				search(txtSearch.getText());
			}
		});
		txtSearch.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtSearch.setColumns(10);
		txtSearch.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtSearch.setBackground(SystemColor.menu);

		tableScrollButton = new TableScrollButton();

		scrollPane = new JScrollPane();
		String[] header = { "STT", "Mã nhân viên", "Mật khẩu", "Trạng thái" };
		modelTable = new DefaultTableModel(header, 0);
		table = new JTable(modelTable);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				
				System.out.println(row);
				if(SwingUtilities.isRightMouseButton(e)) {
					if(row !=-1) {
						updatePass(arrEmployees.get(row).getId());
						menu.show(table, e.getX(), e.getY());
					}
				}
			}
		});
		scrollPane.setViewportView(table);
		TableCustom.apply(scrollPane, TableCustom.TableType.MULTI_LINE);
		tableScrollButton.add(scrollPane, BorderLayout.CENTER);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 741, Short.MAX_VALUE).addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(11)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 576, Short.MAX_VALUE).addContainerGap()));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addGap(10)
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(tableScrollButton, GroupLayout.DEFAULT_SIZE, 721, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
								.addComponent(lblDanhSchTi, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)
								.addGap(10)
								.addComponent(lblNhpMNhn, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
								.addGap(11)
								.addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(10)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addComponent(lblDanhSchTi)
								.addComponent(lblNhpMNhn, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createSequentialGroup().addGap(2).addComponent(txtSearch,
										GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)))
						.addGap(8).addComponent(tableScrollButton, GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)
						.addContainerGap()));
		panel.setLayout(gl_panel);
		setLayout(groupLayout);

		setUpEmployee();
	}

	public void setUpEmployee() {

		arrEmployees = employeeService.getAll();
		modelTable.setRowCount(0);
		for (int i = 0; i < arrEmployees.size(); i++) {
			Account acc = arrEmployees.get(i).getAccount();

			modelTable.addRow(new Object[] { (i + 1), acc.getId(), acc.getPassword(), "Offline" });

		}

	}
	
	private void updatePass(Long id) {
		menu = new JPopupMenu();
		JMenuItem item = new JMenuItem("Đổi mật khẩu");
		menu.add(item);
		
		item.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String pass = JOptionPane.showInputDialog(null, "Nhập mật khẩu mới", "Đổi mật khẩu", JOptionPane.INFORMATION_MESSAGE);
				if(employeeService.changePass(pass, id)) {
					JOptionPane.showMessageDialog(null, "Đổi mật khẩu thành công");
					setUpEmployee();
				}else {
					JOptionPane.showMessageDialog(null, "Đổi mật khẩu không thành công", "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
	

	private void search(String search) {
		TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(modelTable);
		table.setRowSorter(tr);
		tr.setRowFilter(RowFilter.regexFilter(search));
	}
}
