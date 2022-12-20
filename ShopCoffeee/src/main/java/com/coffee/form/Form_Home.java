package com.coffee.form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.coffee.component.Card;
import com.coffee.custom.PanelBorder;
import com.coffee.custom.ScrollBar;
import com.coffee.custom.TableCustom;
import com.coffee.custom.TableScrollButton;
import com.coffee.entity.Bill;
import com.coffee.entity.Customer;
import com.coffee.model.Model_Card;
import com.coffee.service.BillService;
import com.coffee.serviceimpl.BillServiceImpl;

public class Form_Home extends JPanel {
	
	private Locale localeVN = Locale.getDefault();
	private NumberFormat moneyFormat = NumberFormat.getInstance(localeVN);
	private List<Bill> arrBills;

	private BillService billService;
	
	private Card card1;
	private Card card2;
	private Card card3;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLayeredPane panel;
	private PanelBorder panelBorder1;
	private JScrollPane spTable;
	private TableScrollButton tableScrollButton ;
	private JTable table;
	private DefaultTableModel model;
	private static final long serialVersionUID = 1L;

	public Form_Home(Date date) {
		initComponents();

		// add row table
		spTable.setVerticalScrollBar(new ScrollBar());
		spTable.getVerticalScrollBar().setBackground(Color.WHITE);
		spTable.getViewport().setBackground(Color.WHITE);
		JPanel p = new JPanel();
		p.setBackground(Color.WHITE);
		spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
		getValueListBill(date);
	}

	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		panel = new JLayeredPane();
		card1 = new Card();
		card2 = new Card();
		card3 = new Card();
		panelBorder1 = new PanelBorder();
		jLabel1 = new JLabel();
		
		tableScrollButton = new TableScrollButton();
		
		spTable = new JScrollPane();
	

		setBackground(new java.awt.Color(242, 242, 242));

		panel.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

		card1.setColor1(new java.awt.Color(142, 142, 250));
		card1.setColor2(new java.awt.Color(123, 123, 245));
		panel.add(card1);

		card2.setColor1(new java.awt.Color(186, 123, 247));
		card2.setColor2(new java.awt.Color(167, 94, 236));
		panel.add(card2);

		card3.setColor1(new java.awt.Color(241, 208, 62));
		card3.setColor2(new java.awt.Color(211, 184, 61));
		panel.add(card3);

		panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

		jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
		jLabel1.setForeground(new java.awt.Color(127, 127, 127));
		jLabel1.setText("Top khách hàng mua sản phẩm nhiều nhất");

		spTable.setBorder(null);

		String[] header= { "Name", "Email", "Phone", "Point", "Total price" };
		model = new DefaultTableModel(header,0);
		table = new JTable(model);
		spTable.setViewportView(table);
		
		TableCustom.apply(spTable, TableCustom.TableType.MULTI_LINE);
		
		tableScrollButton.add(spTable, BorderLayout.CENTER);
		javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
		panelBorder1.setLayout(panelBorder1Layout);
		panelBorder1Layout
				.setHorizontalGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(panelBorder1Layout.createSequentialGroup().addGap(20, 20, 20)
								.addGroup(panelBorder1Layout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(panelBorder1Layout.createSequentialGroup().addComponent(jLabel1)
												.addGap(0, 0, Short.MAX_VALUE))
										.addComponent(tableScrollButton))
								.addContainerGap()));
		panelBorder1Layout
				.setVerticalGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(panelBorder1Layout.createSequentialGroup().addGap(20, 20, 20).addComponent(jLabel1)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(tableScrollButton, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
								.addGap(20, 20, 20)));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGap(20, 20, 20)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
								.addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 875, Short.MAX_VALUE))
						.addGap(20, 20, 20)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(20, 20, 20)
						.addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(20, 20, 20).addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGap(20, 20, 20)));
	}// </editor-fold>//GEN-END:initComponents

	// Variables declaration - do not modify//GEN-BEGIN:variables

	
	
	
	
	private void getValueListBill(Date date)  {
		billService = new BillServiceImpl();
		arrBills = billService.getBillByShift(date);
		Map<Customer, Double> map = billService.topCustomerByShift(date);
		double priceCom = 0;
		double priceCancel = 0;
		int billCom = 0;
		int billCancel = 0;

		
		System.out.println(map);
		
		
		for (int i = 0; i < arrBills.size(); i++) {
			Bill b = arrBills.get(i);

			if (b.getBillStatus().equals("Đã hoàn thành")) {
				billCom = billCom + 1;
				priceCom = priceCom + b.getTotalPrice();

			} else if (b.getBillStatus().equals("Đã hủy")) {
				billCancel = billCancel + 1;
				priceCancel = priceCancel + b.getTotalPrice();

			}

		}
		
		card1.setData(new Model_Card(new ImageIcon(getClass().getResource("")), "Tổng tiền", moneyFormat.format(priceCom + priceCancel) + "đ",
				"Tổng hóa đơn "+String.valueOf(billCancel + billCom)));
		card2.setData(new Model_Card(new ImageIcon(getClass().getResource("")), "Tổng tiền hoàn thành",moneyFormat.format(priceCom) + "đ",
				"Hóa đơn hoành thành "+billCom));
		card3.setData(new Model_Card(new ImageIcon(getClass().getResource("")), "Tổng tiền hủy", moneyFormat.format(priceCancel) + "đ",
				"Hóa đơn bị hủy "+billCancel));
		
		
	


		
			map.entrySet().forEach(entry->{
				
				Customer c = entry.getKey();
			
				model.addRow(new Object[] { c.getFullName(),c.getEmail(), c.getPhone(),c.getPoint(),moneyFormat.format(entry.getValue())+"đ" });
			});

	}
	
	
}
