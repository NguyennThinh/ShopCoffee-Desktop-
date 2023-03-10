package com.coffee.form;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Hibernate;

import com.coffee.custom.PanelBorder;
import com.coffee.entity.Bill;
import com.coffee.entity.BillDetail;
import com.coffee.entity.Employee;
import com.coffee.service.BillService;
import com.coffee.serviceimpl.BillServiceImpl;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

/**
 * @author Nguyen Phuc Thinh
 */
public class Form_Bill extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4434283570951910400L;
	
	private BillService billService;
	
	private Locale localeVN = Locale.getDefault();
	private NumberFormat moneyFormat = NumberFormat.getInstance(localeVN);
	private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	private 	List<Bill> arrBill;
	private Bill bill;
	
	private double totalMoney = 0;
	
	private PanelBorder panel;
	private JScrollPane scrBill;
	private JLabel lblNewLabel;
	private JScrollPane scrDetail;
	private JLabel lblNote;
	private JLabel lblTotal;
	private JComboBox lblPayment;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblCreate;
	private JLabel lblEmployee;
	private JLabel lblBillId;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private JLabel lblCustomer;
	private JLabel lblPay;
	private JButton btnCancel;
	private JButton btnPay;
	
	private JTable billTable, detailTable;
	private DefaultTableModel billModel, detailModel;

	/**
	 * Create the panel.
	 */
	public Form_Bill(Employee employee) {
		
		panel = new PanelBorder();
		panel.setBackground(Color.WHITE);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 1182, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
		);
		
		scrBill = new JScrollPane();
		String[] header= {"STT", "M?? h??a ????n", "Ng?????i b??n", "Ng?????i mua", "Ng??y t???o", "Ng??y thanh to??n", "Lo???i thanh to??n", "T???ng","Tr???ng th??i"};
		billModel = new DefaultTableModel(header, 0);
		billTable = new JTable(billModel);
		billTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = billTable.getSelectedRow();
				bill = new Bill();
				 bill = arrBill.get(row);
				 totalMoney=0;
				int i=0;
				detailModel.setRowCount(0);
				for(BillDetail b: bill.getBillDetails()) {
					i++;
					totalMoney = totalMoney+b.getTotal();
					detailModel.addRow(new Object[] {i, b.getProduct().getProductName(), b.getAmount(), moneyFormat.format(b.getTotal())+"??"});
					
				}
			lblBillId.setText(bill.getBill_id());
			lblCreate.setText(format.format(bill.getCreateDate()));
			lblCustomer.setText(bill.getCustomer()!= null?bill.getCustomer().getFullName():"Tr???ng");
			lblEmployee.setText(bill.getEmployee()!= null?bill.getEmployee().getFullName():"Tr???ng");
			lblNote.setText(bill.getBillNote());
			lblPay.setText(bill.getPayDate()!=null?format.format(bill.getPayDate()):"Tr???ng");
			if(!bill.getPayment().equals("")) {
				lblPayment.setSelectedItem(bill.getPayment());
			}
			lblTotal.setText(moneyFormat.format(bill.getTotalPrice()!= 0?bill.getTotalPrice():totalMoney)+"??");
			
			if(!bill.getBillStatus().equals("???? ho??n th??nh")) {
				btnCancel.setVisible(true);
				btnPay.setVisible(true);
			}else {
				btnCancel.setVisible(false);
				btnPay.setVisible(false);
			}
			}
		});
		

		
		scrBill.setViewportView(billTable);
		
		
		lblNewLabel = new JLabel("CHI TI???T H??A ????N");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
		
		scrDetail = new JScrollPane();
		String[] header1= {"STT", "T??n s???n ph???m", "S??? l?????ng", "Th??nh ti???n"};
		detailModel = new DefaultTableModel(header1, 0);
		detailTable = new JTable(detailModel);
		scrDetail.setViewportView(detailTable);
		
		lblNote = new JLabel("Tr???ng");
		lblNote.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		lblTotal = new JLabel("Tr???ng");
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		lblPayment = new JComboBox<String>();
		lblPayment.addItem("M?? QR");
		lblPayment.addItem("Ti???n m???t");
		lblPayment.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		lblNewLabel_1 = new JLabel("Lo???i thanh to??n:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		lblNewLabel_2 = new JLabel("T???ng ti???n:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		lblNewLabel_3 = new JLabel("Ghi ch??");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		lblCreate = new JLabel("Tr???ng");
		lblCreate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		lblEmployee = new JLabel("Tr???ng");
		lblEmployee.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		lblBillId = new JLabel("Tr???ng");
		lblBillId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		lblNewLabel_4 = new JLabel("M?? h??a ????n:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		lblNewLabel_5 = new JLabel("Ng?????i b??n:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		lblNewLabel_6 = new JLabel("Ng??y t???o:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		lblNewLabel_7 = new JLabel("Ng??y thanh to??n:");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		lblNewLabel_8 = new JLabel("Kh??ch h??ng:");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		lblCustomer = new JLabel("Tr???ng");
		lblCustomer.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		lblPay = new JLabel("Tr???ng");
		lblPay.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		btnCancel = new JButton("H???y h??a ????n");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(bill!= null) {
					if(!bill.getBillStatus().equals("Ch??? thanh to??n")) {
						JOptionPane.showMessageDialog(null, "H??a ????n n??y kh??ng th??? h???y");
					}else {
						bill.setBillStatus("???? h???y");
						bill.setBillNote("H???y l??c "+new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
						bill.setTotalPrice(totalMoney);
						 int result = JOptionPane.showConfirmDialog(null,"B???n mu???n h???y h??a ????n n??y?", "Swing Tester",
					               JOptionPane.YES_NO_OPTION,
					               JOptionPane.QUESTION_MESSAGE);
					            if(result == JOptionPane.YES_OPTION){
					            	if (billService.deleteBill(bill)) {
					    				JOptionPane.showMessageDialog(null, "H???y h??a ????n th??nh c??ng");
					    				getBill();
					    			} else {
					    				JOptionPane.showMessageDialog(null, "???? c?? l???i x???y ra", "H???y h??a ????n", JOptionPane.WARNING_MESSAGE);
					    			}
					            }
					}
				}else {
					JOptionPane.showMessageDialog(null, "Vui l??ng ch???n h??a ????n");
				}
			}
		});
		
		btnPay = new JButton("Thanh to??n");
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(bill!= null) {
					if(!bill.getBillStatus().equals("Ch??? thanh to??n")) {
						JOptionPane.showMessageDialog(null, "H??a ????n n??y kh??ng th??? thanh to??n");
					}else {
						bill.setBillStatus("???? ho??n th??nh");
						bill.setPayDate(new Date());
						bill.setTotalPrice(totalMoney);;
						if(billService.payBill(bill, null)){
							JOptionPane.showMessageDialog(null, "Thanh to??n th??nh c??ng");
							getBill();
						} else {
		    				JOptionPane.showMessageDialog(null, "???? c?? l???i x???y ra", "H???y h??a ????n", JOptionPane.WARNING_MESSAGE);
		    			}
					}
				}else {
					JOptionPane.showMessageDialog(null, "Vui l??ng ch???n h??a ????n");
				}
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(10)
					.addComponent(scrBill, GroupLayout.DEFAULT_SIZE, 1142, Short.MAX_VALUE)
					.addGap(10))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_7, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_8, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblBillId, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEmployee, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCreate, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPay, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCustomer, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE))
					.addGap(25)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
					.addGap(29)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPayment, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTotal, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNote, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPay))
					.addGap(41)
					.addComponent(scrDetail, GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)
					.addGap(14))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(378)
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
					.addGap(377))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(10)
					.addComponent(scrBill, GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
					.addGap(10)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(10)
							.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addGap(20)
							.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addGap(20)
							.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addGap(20)
							.addComponent(lblNewLabel_7, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addGap(20)
							.addComponent(lblNewLabel_8, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(10)
							.addComponent(lblBillId, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addGap(20)
							.addComponent(lblEmployee, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addGap(20)
							.addComponent(lblCreate, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addGap(20)
							.addComponent(lblPay, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addGap(20)
							.addComponent(lblCustomer, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(10)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addGap(20)
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addGap(20)
							.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addGap(60)
							.addComponent(btnCancel))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(10)
							.addComponent(lblPayment, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addGap(20)
							.addComponent(lblTotal, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addGap(20)
							.addComponent(lblNote, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(30)
							.addComponent(btnPay))
						.addComponent(scrDetail, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE))
					.addGap(24))
		);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);
		
		
		getBill();

	}
	
	private void getBill() {
		billService = new BillServiceImpl();
		
		arrBill = billService.getBillForDay();
		billModel.setRowCount(0);
		for(int i = 0; i < arrBill.size(); i++) {
			
			billModel.addRow(new  Object[] {(i+1), arrBill.get(i).getBill_id(), arrBill.get(i).getEmployee() != null?arrBill.get(i).getEmployee().getFullName():null,
					arrBill.get(i).getCustomer() != null? arrBill.get(i).getCustomer().getFullName(): null, format.format(arrBill.get(i).getCreateDate())
							, arrBill.get(i).getPayDate()!= null? format.format(arrBill.get(i).getPayDate()): "Tr???ng", arrBill.get(i).getPayment(), moneyFormat.format(arrBill.get(i).getTotalPrice())+"??", arrBill.get(i).getBillStatus()});
			
		}
		
		billTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer()
		{
		    @Override
		    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
		    {
		        final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		        if(table.getValueAt(row, 8).equals("???? h???y")) {
		        	c.setBackground(Color.red);
		        }else if(table.getValueAt(row, 8).equals("Ch??? thanh to??n")) {
		        	c.setBackground(Color.orange);
		        }else {
		        	c.setBackground(null);
		        }
		        return c;
		    }
		});
	}
}
