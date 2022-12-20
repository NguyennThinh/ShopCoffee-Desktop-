package com.coffee.form;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.coffee.entity.Category;
import com.coffee.entity.Product;
import com.coffee.service.CategoryService;
import com.coffee.service.ProductService;
import com.coffee.serviceimpl.CategoryServiceImpl;
import com.coffee.serviceimpl.ProductServiceImpl;

import java.awt.Color;
import java.awt.Image;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;

import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.RowFilter;

import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author Nguyen Phuc Thinh
 */
public class Form_Product extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1843976071318158558L;
	private Locale localeVN = Locale.getDefault();
	private NumberFormat moneyFormat = NumberFormat.getInstance(localeVN);
	private ProductService productService;
	private Product product;
	private List<Product> arrProducts;
	
	
	private JPanel panel;
	private JPanel panel_1;
	private JScrollPane scrollPane;
	private JLabel lblImage;
	private JLabel lblNewLabel_1;
	private JLabel lblProductID;
	private JLabel lblNewLabel_3;
	private JLabel lblName;
	private JLabel lblNewLabel_5;
	private JLabel lblCategory;
	private JLabel lblNewLabel_7;
	private JLabel lblPrice;
	private JLabel lblNewLabel_9;
	private JLabel lblDiscount;
	private JLabel lblNewLabel_11;
	private JLabel lblTotal;
	private JTable proTable;
	private DefaultTableModel tableModel;
	private JLabel lblNewLabel;
	private JTextField txtSearch;
	private JLabel lblLoiSnPhm;
	private JComboBox<String> cbmCategory;

	/**
	 * Create the panel.
	 */
	public Form_Product() {
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		
		scrollPane = new JScrollPane();
		String[] header =  {"STT","Mã sản phẩm", "Tên sản phẩm", "Loại sản phẩm", "Đơn giá"};
		tableModel = new DefaultTableModel(header, 0);
		proTable = new JTable(tableModel);
		proTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				product = arrProducts.get(proTable.getSelectedRow());
				
				lblCategory.setText(product.getCategory().getCategoryName());
				lblName.setText(product.getProductName());
			
				lblPrice.setText(product.getProductPrice()+"");
				lblProductID.setText(product.getProduct_id());
				ImageIcon img = new ImageIcon(product.getImage());
				Image newImage = img.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
				lblImage.setIcon(new ImageIcon(newImage));
				if(product.getDiscount()!= null) {
					if(product.getDiscount().getDateEnd().compareTo(new Date())>0) {
						lblTotal.setVisible(true);
						lblNewLabel_11.setVisible(true);
						lblDiscount.setText( product.getDiscount().getDiscountName());
						
						
						if(product.getDiscount().getDiscountType().equals("%")) {
							lblTotal.setText(moneyFormat.format(product.getProductPrice() - (product.getProductPrice()* product.getDiscount().getDiscountValue()/100))+"đ");
						}else {
							lblTotal.setText(moneyFormat.format(product.getProductPrice() -  product.getDiscount().getDiscountValue())+"đ");
							
						}
					}else {
						lblTotal.setVisible(false);
						lblNewLabel_11.setVisible(false);
						lblDiscount.setText( product.getDiscount().getDiscountName()+" (Đã hết hạn)");
						lblDiscount.setForeground(Color.red);
					}
					
				}else {
					lblTotal.setVisible(false);
					lblNewLabel_11.setVisible(false);
					lblDiscount.setText("Không có khuyến mãi");
				}
				
			}
		});
		scrollPane.setViewportView(proTable);
		
		
		
		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setLayout(null);
		
		lblImage = new JLabel("");
		ImageIcon img = new ImageIcon("image/coffee_image.png");
		Image newImage = img.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
		lblImage.setIcon(new ImageIcon(newImage));
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblImage.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblImage.setBounds(163, 10, 105, 105);
		panel_1.add(lblImage);
		
		lblNewLabel_1 = new JLabel("Mã sản phẩm");
		lblNewLabel_1.setBounds(10, 143, 128, 20);
		panel_1.add(lblNewLabel_1);
		
		lblProductID = new JLabel("Trống");
		lblProductID.setBounds(148, 143, 260, 20);
		panel_1.add(lblProductID);
		
		lblNewLabel_3 = new JLabel("Tên sản phẩm:");
		lblNewLabel_3.setBounds(10, 187, 128, 20);
		panel_1.add(lblNewLabel_3);
		
		lblName = new JLabel("Trống");
		lblName.setBounds(148, 187, 260, 20);
		panel_1.add(lblName);
		
		lblNewLabel_5 = new JLabel("Loại sản phẩm:");
		lblNewLabel_5.setBounds(10, 231, 128, 20);
		panel_1.add(lblNewLabel_5);
		
		lblCategory = new JLabel("Trống");
		lblCategory.setBounds(148, 231, 260, 20);
		panel_1.add(lblCategory);
		
		lblNewLabel_7 = new JLabel("Giá tiền:");
		lblNewLabel_7.setBounds(10, 282, 128, 20);
		panel_1.add(lblNewLabel_7);
		
		lblPrice = new JLabel("Trống");
		lblPrice.setBounds(148, 282, 260, 20);
		panel_1.add(lblPrice);
		
		lblNewLabel_9 = new JLabel("Khuyến mãi:");
		lblNewLabel_9.setBounds(10, 327, 128, 20);
		panel_1.add(lblNewLabel_9);
		
		lblDiscount = new JLabel("Trống");
		lblDiscount.setBounds(148, 327, 260, 20);
		panel_1.add(lblDiscount);
		
		lblNewLabel_11 = new JLabel("Thành tiền:");
		lblNewLabel_11.setBounds(10, 381, 128, 20);
		panel_1.add(lblNewLabel_11);
		
		lblTotal = new JLabel("Trống");
		lblTotal.setBounds(148, 381, 260, 20);
		panel_1.add(lblTotal);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 394, GroupLayout.PREFERRED_SIZE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(2)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)))
		);
		
		lblNewLabel = new JLabel("Tìm sản phẩm");
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		
		txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				search(txtSearch.getText(), proTable, tableModel);;	
			}
		});
		txtSearch.setColumns(10);
		
		lblLoiSnPhm = new JLabel("Loại sản phẩm");
		lblLoiSnPhm.setFont(new Font("SansSerif", Font.BOLD, 12));
		
		cbmCategory = new JComboBox<String>();
		cbmCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbmCategory.getSelectedIndex()==0) {
					search("", proTable, tableModel);;
				}else {
					search(cbmCategory.getSelectedItem().toString(), proTable, tableModel);;	
				}
			}
		});
		cbmCategory.addItem("Tất cả");

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
							.addGap(28)
							.addComponent(lblLoiSnPhm, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(cbmCategory, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE))
					.addGap(10))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblLoiSnPhm, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbmCategory, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
					.addGap(10))
		);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);
		getProduct();
		getCategory();
	}
	
	private void getProduct() {
		productService = new ProductServiceImpl();
		
		arrProducts = productService.getAll();
		
		tableModel.setRowCount(0);
	
		for(int i =0; i < arrProducts.size(); i++) {
			
			tableModel.addRow(new Object[] {(i+1), arrProducts.get(i).getProduct_id(),arrProducts.get(i).getProductName(), arrProducts.get(i).getCategory().getCategoryName(), arrProducts.get(i).getProductPrice()});
		}
		
	}
	private void getCategory() {
		CategoryService categoryService = new CategoryServiceImpl();
		
		List<Category> arrCategories = categoryService.getAll();
		
		for(Category c: arrCategories) {
			cbmCategory.addItem(c.getCategoryName());
		}
		
	}
	private void search(String str , JTable table, DefaultTableModel model ) {
		TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(model);
		table.setRowSorter(tr);
		tr.setRowFilter(RowFilter.regexFilter(str));
	}
}
