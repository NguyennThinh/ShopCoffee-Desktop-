package com.coffee.form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.coffee.custom.PanelBorder;
import com.coffee.entity.Discount;
import com.coffee.entity.Product;
import com.coffee.service.DiscountService;
import com.coffee.serviceimpl.DiscountServiceImpl;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author Nguyen Phuc Thinh
 */
public class Form_Discount extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9148427227563326628L;
	private Locale localeVN = Locale.getDefault();
	private NumberFormat moneyFormat = NumberFormat.getInstance(localeVN);
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy HH:mm");
	
	
	private DiscountService discountService;
	private List<Discount> arrDiscounts;
	
	private JPanel panel;
	private PanelBorder panel_1;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JLabel lblDiscountID;
	private JLabel lblNewLabel_8;
	private JLabel lblName;
	private JLabel lblType;
	private JLabel lblStảt;
	private JLabel lblEnd;
	private PanelBorder panel_2;
	private JScrollPane scrollPane;
	private JLabel lblNewLabel;
	private JTextField txtSearchP;
	private JScrollPane scrollPane_1;
	private JComboBox<String> cbmDiscountType;
	private JLabel lblNewLabel_1;
	private JTextField txtSearchD;
	private JLabel lblNewLabel_2;

	private JTable disountTable, proTable;
	private DefaultTableModel discountModel, proModel;
	private JLabel lblNewLabel_9;
	private JLabel lblValue;
	/**
	 * Create the panel.
	 */
	public Form_Discount() {
		setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		
		panel_1 = new PanelBorder();
		panel_1.setBackground(Color.WHITE);
		panel_1.setLayout(null);
		
		lblNewLabel_3 = new JLabel("Ngày bắt đầu:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(42, 383, 110, 20);
		panel_1.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Ngày kết thúc:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(42, 435, 110, 20);
		panel_1.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("Loại khuyến mãi:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(42, 273, 110, 20);
		panel_1.add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("Tên khuyến mãi:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_6.setBounds(42, 218, 110, 20);
		panel_1.add(lblNewLabel_6);
		
		lblNewLabel_7 = new JLabel("Mã khuyến mãi:");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_7.setBounds(42, 163, 110, 20);
		panel_1.add(lblNewLabel_7);
		
		lblDiscountID = new JLabel("Trống");
		lblDiscountID.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDiscountID.setBounds(180, 163, 188, 20);
		panel_1.add(lblDiscountID);
		
		lblNewLabel_8 = new JLabel();
		
		ImageIcon img = new ImageIcon("image/sale_image.png");
		Image newImage = img.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		lblNewLabel_8.setIcon(new ImageIcon(newImage));
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setBounds(144, 38, 100, 100);
		panel_1.add(lblNewLabel_8);
		
		lblName = new JLabel("Trống");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblName.setBounds(180, 218, 188, 20);
		panel_1.add(lblName);
		
		lblType = new JLabel("Trống");
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblType.setBounds(180, 273, 188, 20);
		panel_1.add(lblType);
		
		lblStảt = new JLabel("Trống");
		lblStảt.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblStảt.setBounds(180, 383, 188, 20);
		panel_1.add(lblStảt);
		
		lblEnd = new JLabel("Trống");
		lblEnd.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEnd.setBounds(180, 435, 188, 20);
		panel_1.add(lblEnd);
		
		panel_2 = new PanelBorder();
		panel_2.setBackground(Color.WHITE);
		
		scrollPane = new JScrollPane();
		String header1[] = {"STT","Mã sản phẩm", "Tên sản phẩm", "Loại sản phẩm", "Đơn giá"};
		proModel = new DefaultTableModel( header1, 0);
		proTable = new JTable(proModel);
		scrollPane.setViewportView(proTable);
		
		lblNewLabel = new JLabel("Tìm sản phẩm:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		txtSearchP = new JTextField();
		txtSearchP.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				search(txtSearchP.getText(), proTable, proModel);
			}
		});
		txtSearchP.setColumns(10);
		
		scrollPane_1 = new JScrollPane();
		String header[] = {"STT", "Mã khuyến mãi", "Tên khuyến mãi", "Loại khuyến mãi", "Ngày bắt đầu", "Ngày kết thúc"};
		discountModel = new DefaultTableModel(header, 0);
		disountTable = new JTable(discountModel);
		disountTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			Discount discount = arrDiscounts.get(disountTable.getSelectedRow());
			
			lblDiscountID.setText(discount.getDiscountID());
			lblEnd.setText(dateFormat.format(discount.getDateEnd()));
			lblName.setText(discount.getDiscountName());
			lblStảt.setText(dateFormat.format(discount.getDateStart()));
			lblType.setText(discount.getDiscountType());
			
			if(discount.getDateEnd().compareTo(new Date()) <0) {
				lblEnd.setForeground(Color.red);
			}
			if(discount.getDiscountType().equals("%")) {
				lblValue.setText(discount.getDiscountValue()+"%");
			}else {
				lblValue.setText(moneyFormat.format(discount.getDiscountValue())+"đ");
			}
			
			int i = 0;
			proModel.setRowCount(0);
			for(Product p : discount.getProducts()) {
				i++;
				proModel.addRow(new Object[] {i, p.getProduct_id(),p.getProductName(), p.getCategory().getCategoryName(),moneyFormat.format(p.getProductPrice())+"đ"});
				
			}
			
				
			}
		});
		scrollPane_1.setViewportView(disountTable);

		
		
		cbmDiscountType = new JComboBox<String>();
		cbmDiscountType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbmDiscountType.getSelectedIndex()==0) {
					search("", disountTable, discountModel);
				}else if(cbmDiscountType.getSelectedIndex()==1){
					search("%", disountTable, discountModel);
				}else {
					search("Tiền", disountTable, discountModel);
				}
			}
		});
		cbmDiscountType.addItem("Tất cả");
		cbmDiscountType.addItem("Phần trăm(%)");
		cbmDiscountType.addItem("Tiền hóa đơn(đ)");

		
		
		
		
		lblNewLabel_1 = new JLabel("Loại khuyến mãi:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		txtSearchD = new JTextField();
		txtSearchD.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				search(txtSearchD.getText(), disountTable, discountModel);
			}
		});
		txtSearchD.setColumns(10);
		
		lblNewLabel_2 = new JLabel("Tìm khuyến mãi:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 399, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE)
				.addComponent(panel_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE)
		);
		
		lblNewLabel_9 = new JLabel("Giá trị:");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_9.setBounds(42, 328, 110, 20);
		panel_1.add(lblNewLabel_9);
		
		lblValue = new JLabel("Trống");
		lblValue.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblValue.setBounds(180, 328, 188, 20);
		panel_1.add(lblValue);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_panel_2.createSequentialGroup()
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtSearchD, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
							.addGap(36)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(cbmDiscountType, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_panel_2.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtSearchP, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)))
					.addGap(10))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbmDiscountType, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addComponent(txtSearchD, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
					.addGap(16)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
					.addGap(21)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtSearchP, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
					.addGap(30))
		);
		panel_2.setLayout(gl_panel_2);
		panel.setLayout(gl_panel);
		getDiscount() ;
	}
	
	private void getDiscount() {
		discountService = new DiscountServiceImpl();
		arrDiscounts = discountService.getAll();
		
		discountModel.setRowCount(0);
		
		for(int i =0; i < arrDiscounts.size(); i++) {
			discountModel.addRow(new Object[] {(i+1), arrDiscounts.get(i).getDiscountID(), arrDiscounts.get(i).getDiscountName(), arrDiscounts.get(i).getDiscountType(), arrDiscounts.get(i).getDateStart(), arrDiscounts.get(i).getDateEnd()});
		}
		
		
	}
	private void search(String str , JTable table, DefaultTableModel model ) {
		TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(model);
		table.setRowSorter(tr);
		tr.setRowFilter(RowFilter.regexFilter(str));
	}
}
