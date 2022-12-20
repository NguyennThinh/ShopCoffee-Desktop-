package com.coffee.form;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.coffee.custom.ComboBoxSuggestion;
import com.coffee.custom.MaterialTabbed;
import com.coffee.custom.PanelBorder;
import com.coffee.custom.ScrollBar;
import com.coffee.custom.TableCustom;
import com.coffee.custom.TableScrollButton;
import com.coffee.entity.Category;
import com.coffee.entity.Product;
import com.coffee.service.CategoryService;
import com.coffee.service.ProductService;
import com.coffee.serviceimpl.CategoryServiceImpl;
import com.coffee.serviceimpl.ProductServiceImpl;

/**
 * @author Nguyen Phuc Thinh
 */
public class Form_Product extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 976027735174377923L;

	private Locale localeVN = Locale.getDefault();
	private NumberFormat moneyFormat = NumberFormat.getInstance(localeVN);

	private ProductService productService;
	private CategoryService categoryService;

	private List<Product> arrProducts;
	private List<Category> arrCategories;

	private byte[] imageBytes;

	private MaterialTabbed tabProduct;
	private JPanel pProduct;
	private JPanel pCategory;
	private JLabel jLabel1;
	private JScrollPane srcProduct;
	private JLabel lblNhpTnSn;
	private JTextField txtSearch;
	private JLabel lblLoiSnPhm;
	private ComboBoxSuggestion<String> cmbCategory;
	private JTable table, tCategory;
	private DefaultTableModel tableModel, modelCategory;
	private JPopupMenu menu;
	private JLabel jLabel1_1;
	private JScrollPane srcCategory;
	private TableScrollButton tableScrollButton, tableScrollButton1;
	private JPanel panel;
	private JLabel lblNhpTnSn_1;
	private JTextField txtProductId;
	private JLabel lblChiTitSn;
	private JLabel lblImage;
	private JTextField txtProductName;
	private JLabel lblNhpTnSn_2;
	private JLabel lblNhpTnSn_3;
	private JTextField txtPrice;
	private JLabel lblPrice;
	private JTextField txtStatus;
	private JLabel lblNhpTnSn_5;
	private JLabel lblNhpTnSn_6;
	private JTextArea txtDescription;
	private JButton btnSaveProduct;
	private JPanel panel_1;
	private JLabel lblName;
	private JLabel lblAmount;
	private JTextField txtAmount;
	private JTextField txtName;
	private JTextField txtCategoryID;
	private JLabel lblNewLabel;
	private JButton btnSave;
	private JButton btnClear;
	private JLabel lblNhpLoiSn;
	private JTextField txtSearchCategory;
	private JButton btnChooseImage;
	private ComboBoxSuggestion<String> cmbCategory_1;

	/**
	 * Create the panel.
	 * 
	 * @
	 */
	public Form_Product() {
		setBackground(new Color(242, 242, 242));
		productService = new ProductServiceImpl();
		categoryService = new CategoryServiceImpl();
		initComponent();

		TableCustom.apply(srcProduct, TableCustom.TableType.MULTI_LINE);

		panel = new PanelBorder();
		panel.setBackground(Color.WHITE);

		lblNhpTnSn_1 = new JLabel();
		lblNhpTnSn_1.setBounds(10, 130, 121, 24);
		lblNhpTnSn_1.setText("Mã sản phẩm:");
		lblNhpTnSn_1.setForeground(new Color(127, 127, 127));
		lblNhpTnSn_1.setFont(new Font("SansSerif", Font.BOLD, 12));

		txtProductId = new JTextField();
		txtProductId.setEnabled(false);
		txtProductId.setBounds(141, 130, 261, 24);
		txtProductId.setBackground(new Color(240, 240, 240));
		txtProductId.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtProductId.setColumns(10);
		txtProductId.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));

		lblChiTitSn = new JLabel();
		lblChiTitSn.setText("Chi tiết sản phẩm");
		lblChiTitSn.setForeground(new Color(127, 127, 127));
		lblChiTitSn.setFont(new Font("SansSerif", Font.BOLD, 18));

		lblImage = new JLabel("");
		lblImage.setOpaque(true);
		lblImage.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblImage.setBounds(171, 10, 103, 100);

		txtProductName = new JTextField();
		txtProductName.setBounds(141, 180, 261, 24);
		txtProductName.setBackground(new Color(240, 240, 240));
		txtProductName.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtProductName.setColumns(10);
		txtProductName.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));

		lblNhpTnSn_2 = new JLabel();
		lblNhpTnSn_2.setBounds(10, 180, 121, 24);
		lblNhpTnSn_2.setText("Tên sản phẩm:");
		lblNhpTnSn_2.setForeground(new Color(127, 127, 127));
		lblNhpTnSn_2.setFont(new Font("SansSerif", Font.BOLD, 12));

		lblNhpTnSn_3 = new JLabel();
		lblNhpTnSn_3.setBounds(10, 230, 121, 24);
		lblNhpTnSn_3.setText("Loại sản phẩm:");
		lblNhpTnSn_3.setForeground(new Color(127, 127, 127));
		lblNhpTnSn_3.setFont(new Font("SansSerif", Font.BOLD, 12));

		txtPrice = new JTextField();
		txtPrice.setBounds(141, 280, 261, 24);
		txtPrice.setBackground(new Color(240, 240, 240));
		txtPrice.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtPrice.setColumns(10);
		txtPrice.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));

		lblPrice = new JLabel();
		lblPrice.setBounds(10, 280, 121, 24);
		lblPrice.setText("Đơn giá");
		lblPrice.setForeground(new Color(127, 127, 127));
		lblPrice.setFont(new Font("SansSerif", Font.BOLD, 12));

		txtStatus = new JTextField();
		txtStatus.setEnabled(false);
		txtStatus.setBounds(141, 330, 261, 24);
		txtStatus.setBackground(new Color(240, 240, 240));
		txtStatus.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtStatus.setColumns(10);
		txtStatus.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));

		lblNhpTnSn_5 = new JLabel();
		lblNhpTnSn_5.setBounds(10, 330, 121, 24);
		lblNhpTnSn_5.setText("Trạng thái");
		lblNhpTnSn_5.setForeground(new Color(127, 127, 127));
		lblNhpTnSn_5.setFont(new Font("SansSerif", Font.BOLD, 12));

		lblNhpTnSn_6 = new JLabel();
		lblNhpTnSn_6.setBounds(10, 380, 121, 24);
		lblNhpTnSn_6.setText("Giới thiệu:");
		lblNhpTnSn_6.setForeground(new Color(127, 127, 127));
		lblNhpTnSn_6.setFont(new Font("SansSerif", Font.BOLD, 12));

		txtDescription = new JTextArea();
		txtDescription.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtDescription.setBounds(141, 380, 261, 80);
		txtDescription.setBackground(new Color(240, 240, 240));

		btnSaveProduct = new JButton("Lưu sản phẩm");
		btnSaveProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveProduct();
			}
		});
		btnSaveProduct.setBounds(144, 481, 130, 40);
		btnSaveProduct.setBackground(new Color(240, 240, 240));
		btnSaveProduct.setBorder(null);
		btnSaveProduct.setFont(new Font("SansSerif", Font.BOLD, 12));

		btnChooseImage = new JButton();
		btnChooseImage.setBackground(Color.WHITE);
		btnChooseImage.setBorder(null);
		btnChooseImage.setOpaque(false);
		btnChooseImage.setIcon(new ImageIcon("image/add-image.png"));
		btnChooseImage.setBounds(283, 44, 28, 28);
		btnChooseImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooseImage();
			}
		});
		GroupLayout gl_pProduct = new GroupLayout(pProduct);
		gl_pProduct.setHorizontalGroup(gl_pProduct.createParallelGroup(Alignment.LEADING).addGroup(gl_pProduct
				.createSequentialGroup().addGap(10)
				.addGroup(gl_pProduct.createParallelGroup(Alignment.LEADING).addGroup(gl_pProduct
						.createSequentialGroup()
						.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE).addGap(10)
						.addComponent(lblNhpTnSn, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
						.addGap(10).addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
						.addGap(10)
						.addComponent(lblLoiSnPhm, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
						.addGap(10)
						.addComponent(cmbCategory, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblChiTitSn, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pProduct.createSequentialGroup()
								.addComponent(tableScrollButton, GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 412, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap()));
		gl_pProduct.setVerticalGroup(gl_pProduct.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pProduct.createSequentialGroup().addGap(10)
						.addGroup(gl_pProduct.createParallelGroup(Alignment.LEADING).addComponent(jLabel1)
								.addComponent(lblNhpTnSn, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblLoiSnPhm, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_pProduct.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_pProduct.createSequentialGroup()
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(cmbCategory,
														GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
										.addComponent(lblChiTitSn, Alignment.LEADING)))
						.addGap(10)
						.addGroup(gl_pProduct.createParallelGroup(Alignment.LEADING)
								.addComponent(tableScrollButton, GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 547, Short.MAX_VALUE))
						.addContainerGap()));
		panel.setLayout(null);
		panel.add(lblNhpTnSn_1);
		panel.add(txtProductId);
		panel.add(lblNhpTnSn_2);
		panel.add(txtProductName);
		panel.add(lblNhpTnSn_3);
		panel.add(lblPrice);
		panel.add(txtPrice);
		panel.add(lblNhpTnSn_5);
		panel.add(txtStatus);
		panel.add(lblNhpTnSn_6);
		panel.add(txtDescription);
		panel.add(btnSaveProduct);
		panel.add(lblImage);
		panel.add(btnChooseImage);

		cmbCategory_1 = new ComboBoxSuggestion<String>();
		cmbCategory_1.setBounds(141, 230, 261, 24);
		panel.add(cmbCategory_1);
		pProduct.setLayout(gl_pProduct);

		setUpCategory();
		setUpProduct();
		setUpCategories();
	}

	/**
	 * 
	 */
	private void initComponent() {
		// TODO Auto-generated method stub

		tabProduct = new MaterialTabbed();
		tabProduct.setFont(new Font("Tahoma", Font.BOLD, 12));

		pProduct = new JPanel();
		tabProduct.addTab("Sản phẩm", null, pProduct, null);

		pCategory = new JPanel();
		tabProduct.addTab("Loại sản phẩm", null, pCategory, null);

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(tabProduct, GroupLayout.PREFERRED_SIZE, 1157, Short.MAX_VALUE)
						.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(tabProduct,
				GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE));
		setLayout(groupLayout);

		tabProduct();
		tabCategory();
	}

	/**
	 * 
	 */
	private void tabProduct() {
		// TODO Auto-generated method stub
		jLabel1 = new JLabel();
		jLabel1.setText("Danh sách sản phẩm");
		jLabel1.setForeground(new Color(127, 127, 127));
		jLabel1.setFont(new Font("SansSerif", Font.BOLD, 18));

		tableScrollButton = new TableScrollButton();
		srcProduct = new JScrollPane();
		srcProduct.setBorder(null);
		srcProduct.setVerticalScrollBar(new ScrollBar());
		srcProduct.getVerticalScrollBar().setBackground(Color.WHITE);
		srcProduct.getViewport().setBackground(Color.WHITE);
		tableScrollButton.add(srcProduct, java.awt.BorderLayout.CENTER);

		String[] headers = { "STT", "Mã sản phẩm", "Tên sản phẩm", "Loại sản phẩm", "Giá tiền", "Trạng thái" };
		tableModel = new DefaultTableModel(headers, 0);
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int row = table.getSelectedRow();

				Product product = arrProducts.get(row);

				txtProductName.setText(product.getProductName());
				txtProductId.setText(product.getProduct_id());
				txtDescription.setText(product.getDescription());
				txtPrice.setText(String.valueOf(product.getProductPrice()));
				txtStatus.setText(product.isProductStatus() ? "Đang kinh doanh" : "Ngừng kinh doanh");
				if (product.getImage() != null) {
					imageBytes = product.getImage();
					ImageIcon img = new ImageIcon(imageBytes);
					Image newImage = img.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
					lblImage.setIcon(new ImageIcon(newImage));

				}

				if (SwingUtilities.isRightMouseButton(e)) {
					if (row != -1) {
						chooseMenu(arrProducts.get(row));
						menu.show(table, e.getX(), e.getY());
					}
				}
			}
		});

		table.setModel(tableModel);

		srcProduct.setViewportView(table);

		lblNhpTnSn = new JLabel();
		lblNhpTnSn.setText("Nhập tên sản phẩm:");
		lblNhpTnSn.setForeground(new Color(127, 127, 127));
		lblNhpTnSn.setFont(new Font("SansSerif", Font.BOLD, 12));

		txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				search(txtSearch.getText());
			}
		});
		txtSearch.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtSearch.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtSearch.setColumns(10);

		lblLoiSnPhm = new JLabel();
		lblLoiSnPhm.setText("Loại sản phẩm:");
		lblLoiSnPhm.setForeground(new Color(127, 127, 127));
		lblLoiSnPhm.setFont(new Font("SansSerif", Font.BOLD, 12));

		cmbCategory = new ComboBoxSuggestion<String>();
		cmbCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					if(cmbCategory.getSelectedIndex() ==0) {
						search("");
					}else {
						search(cmbCategory.getSelectedItem().toString());
					}
			}
		});
	}

	private void tabCategory() {
		jLabel1_1 = new JLabel();
		jLabel1_1.setText("Danh sách sản phẩm");
		jLabel1_1.setForeground(new Color(127, 127, 127));
		jLabel1_1.setFont(new Font("SansSerif", Font.BOLD, 18));

		srcCategory = new JScrollPane();
		tableScrollButton1 = new TableScrollButton();
		srcCategory.setBorder(null);
		srcCategory.setVerticalScrollBar(new ScrollBar());
		srcCategory.getVerticalScrollBar().setBackground(Color.WHITE);
		srcCategory.getViewport().setBackground(Color.WHITE);

		String[] headers = { "STT", "Mã Loại ản phẩm", "Tên Loại sản phẩm", "Sản phẩm hiện có" };
		modelCategory = new DefaultTableModel(headers, 0);
		tCategory = new JTable();
		tCategory.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tCategory.getSelectedRow();
				txtAmount.setText(tCategory.getValueAt(row, 3) + "");
				txtCategoryID.setText(tCategory.getValueAt(row, 1).toString());
				txtName.setText(tCategory.getValueAt(row, 2) + "");

				if (SwingUtilities.isRightMouseButton(e)) {
					chooseMenuCategory(arrCategories.get(row));
					menu.show(tCategory, e.getX(), e.getY());
				}
			}
		});

		tCategory.setModel(modelCategory);
		srcCategory.setViewportView(tCategory);

		tableScrollButton1.add(srcCategory, java.awt.BorderLayout.CENTER);

		panel_1 = new PanelBorder();
		panel_1.setBackground(Color.WHITE);
		panel_1.setLayout(null);

		lblName = new JLabel("Tên loại sản phẩm:");
		lblName.setForeground(new Color(127, 127, 127));
		lblName.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblName.setBounds(10, 82, 150, 31);
		panel_1.add(lblName);

		lblAmount = new JLabel("Số lượng sản phẩm:");
		lblAmount.setForeground(new Color(127, 127, 127));
		lblAmount.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblAmount.setBounds(10, 145, 150, 31);
		panel_1.add(lblAmount);

		txtAmount = new JTextField();
		txtAmount.setEnabled(false);
		txtAmount.setColumns(10);
		txtAmount.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtAmount.setBounds(170, 145, 282, 31);
		panel_1.add(txtAmount);

		txtName = new JTextField();
		txtName.setBackground(new Color(240, 240, 240));
		txtName.setColumns(10);
		txtName.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtName.setBounds(170, 82, 282, 31);
		panel_1.add(txtName);

		txtCategoryID = new JTextField();
		txtCategoryID.setEnabled(false);
		txtCategoryID.setColumns(10);
		txtCategoryID.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtCategoryID.setBounds(170, 21, 282, 31);
		panel_1.add(txtCategoryID);

		lblNewLabel = new JLabel("Mã loại sản phẩm:");
		lblNewLabel.setForeground(new Color(127, 127, 127));
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 21, 150, 31);
		panel_1.add(lblNewLabel);

		btnSave = new JButton("Lưu");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveCategory();
			}
		});
		btnSave.setBorder(null);
		btnSave.setBackground(new Color(240, 240, 240));
		btnSave.setBounds(170, 214, 124, 31);
		panel_1.add(btnSave);

		btnClear = new JButton("Xóa rỗng");
		btnClear.setBorder(null);
		btnClear.setBackground(new Color(240, 240, 240));
		btnClear.setBounds(328, 214, 124, 31);
		panel_1.add(btnClear);

		lblNhpLoiSn = new JLabel();
		lblNhpLoiSn.setText("Nhập loại sản phẩm");
		lblNhpLoiSn.setForeground(new Color(127, 127, 127));
		lblNhpLoiSn.setFont(new Font("SansSerif", Font.BOLD, 12));

		txtSearchCategory = new JTextField();
		txtSearchCategory.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtSearchCategory.setColumns(10);
		txtSearchCategory.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GroupLayout gl_pCategory = new GroupLayout(pCategory);
		gl_pCategory.setHorizontalGroup(gl_pCategory.createParallelGroup(Alignment.LEADING).addGroup(gl_pCategory
				.createSequentialGroup().addGap(10)
				.addGroup(gl_pCategory.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pCategory.createSequentialGroup()
								.addComponent(jLabel1_1, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)
								.addGap(10)
								.addComponent(lblNhpLoiSn, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
								.addGap(10).addComponent(txtSearchCategory, GroupLayout.PREFERRED_SIZE, 148,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pCategory.createSequentialGroup()
								.addComponent(tableScrollButton1, GroupLayout.DEFAULT_SIZE, 659, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 467, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap()));
		gl_pCategory.setVerticalGroup(gl_pCategory.createParallelGroup(Alignment.LEADING).addGroup(gl_pCategory
				.createSequentialGroup().addGap(10)
				.addGroup(gl_pCategory.createParallelGroup(Alignment.LEADING).addComponent(jLabel1_1)
						.addComponent(lblNhpLoiSn, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtSearchCategory, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
				.addGap(10)
				.addGroup(gl_pCategory.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 264, GroupLayout.PREFERRED_SIZE)
						.addComponent(tableScrollButton1, GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE))
				.addContainerGap()));
		pCategory.setLayout(gl_pCategory);
	}

	/**
	 * @
	 *****************************************************************************/
	private void setUpProduct() {

		arrProducts = productService.getAll();
		tableModel.setRowCount(0);

		for (int i = 0; i < arrProducts.size(); i++) {
			Product p = arrProducts.get(i);
			String status = p.isProductStatus() ? "Đang kinh doanh" : "Ngừng kinh doanh";
			tableModel.addRow(new Object[] { (i + 1), p.getProduct_id(), p.getProductName(),
					p.getCategory().getCategoryName(), moneyFormat.format(p.getProductPrice()) + "đ", status });
		}

	}

	private void search(String productName) {
		TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(tableModel);
		table.setRowSorter(tr);
		tr.setRowFilter(RowFilter.regexFilter(productName));
	}
	
	
	
	
	

	public void setUpCategory() {

		arrCategories = categoryService.getAll();
		cmbCategory.removeAllItems();
		cmbCategory_1.removeAllItems();

		cmbCategory.addItem("Tất cả");
		if (arrCategories != null) {
			arrCategories.forEach(c -> {
				cmbCategory.addItem(c.getCategoryName());
				cmbCategory_1.addItem(c.getCategoryName());

			});
		}

	}

	private void chooseMenu(Product product) {

		menu = new JPopupMenu();
		JMenuItem mDelete = new JMenuItem("Xóa");
		menu.add(mDelete);
		mDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int result = JOptionPane.showConfirmDialog(null, "Bạn muốn xóa sản phẩm này?", "Xáo sản phẩm",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (result == JOptionPane.YES_OPTION) {
					if (productService.delete(product)) {

						JOptionPane.showMessageDialog(null, "Xóa sản phẩm thành công");
						setUpProduct();

					} else {
						JOptionPane.showMessageDialog(null, "Xóa sản phẩm không thành công", "Error Message",
								JOptionPane.ERROR_MESSAGE);
					}
				}

			}
		});
	}

	/******************************************************************************/
	private void chooseMenuCategory(Category category) {
		menu = new JPopupMenu();

		JMenuItem mDelete = new JMenuItem("Xóa");
		menu.add(mDelete);
		mDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (categoryService.delete(category)) {

					JOptionPane.showMessageDialog(null, "Xóa loại sản phẩm thành công");
					setUpProduct();

				} else {
					JOptionPane.showMessageDialog(null, "Xóa loại sản phẩm không thành công", "Error Message",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	private void setUpCategories() {
		categoryService = new CategoryServiceImpl();
		arrCategories = categoryService.getAll();
		modelCategory.setRowCount(0);
		
		for (int i = 0; i < arrCategories.size(); i++) {
			Category c = arrCategories.get(i);

			modelCategory.addRow(new Object[] { (i + 1), c.getCategory_id(), c.getCategoryName(),
					new ArrayList<>(c.getProducts()).size(), });
		}
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

	private void saveProduct() {

		if(txtProductName.getText().equals("")|| txtPrice.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin", "Lưu sản phẩm", JOptionPane.WARNING_MESSAGE);

		}else {
			Product product = new Product();
			if (!txtProductId.getText().equals("")) {
				product.setProduct_id(txtProductId.getText());
			} else {
				product.setProduct_id("SP" + (productService.getAll().size() + 1));
			}
			product.setCategory(arrCategories.get(cmbCategory_1.getSelectedIndex()));
			product.setDescription(txtDescription.getText());
			product.setProductName(txtProductName.getText());

			product.setDiscount(null);
			product.setProductPrice(Double.parseDouble(txtPrice.getText()));
			product.setProductStatus(true);
		

			if (imageBytes != null) {
				product.setImage(imageBytes);
			}

			if (productService.save(product)) {
				JOptionPane.showMessageDialog(null, "Lưu sản phẩm thành công!");
			} else {
				JOptionPane.showMessageDialog(null, "Lưu sản phẩm không thành công!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	private void saveCategory() {
		if(txtName.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin", "Lưu loại sản phẩm", JOptionPane.WARNING_MESSAGE);
		}else {
			Category newCategory = new Category();
		categoryService = new CategoryServiceImpl();
		if (txtCategoryID.getText().equals("")) {
			newCategory.setCategory_id("LPS" + (arrCategories.size() + 1));
		} else {
			newCategory.setCategory_id(txtCategoryID.getText());
		}
		newCategory.setCategoryName(txtName.getText());

		if (categoryService.save(newCategory)) {
			JOptionPane.showMessageDialog(null, "Thêm loại sản phẩm thành công");
			setUpCategories();
			txtCategoryID.setText("");
			txtName.setText("");
			txtAmount.setText("");
		} else {
			JOptionPane.showMessageDialog(null, "Thêm loại sản phẩm không thành công", "Thêm sản phẩm",
					JOptionPane.ERROR_MESSAGE);
		}
		}
	}

}
