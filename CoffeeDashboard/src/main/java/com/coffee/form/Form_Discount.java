package com.coffee.form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.RowFilter;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.coffee.custom.ComboBoxSuggestion;
import com.coffee.custom.JCheckBoxCustom;
import com.coffee.custom.PanelBorder;
import com.coffee.custom.TableCustom;
import com.coffee.custom.TableScrollButton;
import com.coffee.entity.Discount;
import com.coffee.entity.Product;
import com.coffee.service.DiscountService;
import com.coffee.service.ProductService;
import com.coffee.serviceimpl.DiscountServiceImpl;
import com.coffee.serviceimpl.ProductServiceImpl;
import com.toedter.calendar.JDateChooser;

/**
 * @author Nguyen Phuc Thinh
 */
public class Form_Discount extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7601284058851693783L;

	private DiscountService discountService;
	private ProductService productService;

	private List<Product> productChoose;

	private List<Discount> arrCoupons;
	private List<Product> arrProducts;

	private Discount discount;

	private PanelBorder pDiscount;
	private JLabel lblNhpTnKhuyn;
	private JTextField txtSearchDiscount;
	private JLabel lblDanhSchKhuyn;
	private ComboBoxSuggestion<String> cmbDiscount;
	private JLabel lblLoiKhuynMi;
	private JLabel lblTrngThi;
	private ComboBoxSuggestion<String> cmbStatus;
	private TableScrollButton tableScrollDiscount;
	private JScrollPane srcDiscount;
	private PanelBorder pProduct;
	private JLabel lblNhpTnSn;
	private JTextField txtSearchProduct;
	private JLabel lblDanhSchSn;
	private ComboBoxSuggestion<String> cmbCategory;
	private JLabel lblCategory;
	private TableScrollButton tableScrollProduct;
	private JScrollPane srcProduct;
	private PanelBorder discountDetails;
	private JLabel lblChiTitKhuyn;
	private JLabel lblMKhuynMi;
	private JTextField txtDiscountID;
	private JTextField txtDiscountName;
	private JLabel lblTnKhuynMi;
	private JLabel lblMKhuynMi_1;
	private ComboBoxSuggestion<String> cmbType;
	private JTextField txtValue;
	private JLabel lblTnKhuynMi_1;
	private JDateChooser dateFrom;
	private JLabel lblTnKhuynMi_2;
	private JDateChooser dateTo;
	private JLabel lblTnKhuynMi_3;
	private JTextField txtStatus;
	private JLabel lblTnKhuynMi_4;
	private JButton btnAdd;
	private JTable discoutnTable, productTable;
	private DefaultTableModel discountModel, productModel;
	private JCheckBoxCustom ckShowProductDiscount;
	private JButton btnUpdate;

	/**
	 * Create the panel.
	 * 
	 * @
	 */
	public Form_Discount() {

		productService = new ProductServiceImpl();
		discountService = new DiscountServiceImpl();

		initComponent();

	}

	/**
	 * 
	 */
	private void initComponent() {
		// TODO Auto-generated method stub

		initDiscount();
		initProduct();
		initDetails();
		setUpDiscount();
	}

	/**
	 * 
	 */
	private void initDiscount() {
		// TODO Auto-generated method stub
		pDiscount = new PanelBorder();
		pDiscount.setBackground(new Color(255, 255, 255));

		lblNhpTnKhuyn = new JLabel();
		lblNhpTnKhuyn.setText("Nhập tên khuyến mãi:");
		lblNhpTnKhuyn.setForeground(new Color(127, 127, 127));
		lblNhpTnKhuyn.setFont(new Font("SansSerif", Font.BOLD, 12));
		pDiscount.add(lblNhpTnKhuyn);

		txtSearchDiscount = new JTextField();
		txtSearchDiscount.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtSearchDiscount.setColumns(10);
		txtSearchDiscount.setBackground(new Color(240, 240, 240));
		txtSearchDiscount.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtSearchDiscount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				search(txtSearchDiscount.getText(), discountModel, discoutnTable);
			}
		});
		pDiscount.add(txtSearchDiscount);

		lblDanhSchKhuyn = new JLabel();
		lblDanhSchKhuyn.setText("Danh sách khuyến mãi");
		lblDanhSchKhuyn.setForeground(new Color(127, 127, 127));
		lblDanhSchKhuyn.setFont(new Font("SansSerif", Font.BOLD, 18));
		pDiscount.add(lblDanhSchKhuyn);

		cmbDiscount = new ComboBoxSuggestion<String>();
		cmbDiscount.setBackground(new Color(240, 240, 240));
		pDiscount.add(cmbDiscount);

		lblLoiKhuynMi = new JLabel();
		lblLoiKhuynMi.setText("Loại khuyến mãi:");
		lblLoiKhuynMi.setForeground(new Color(127, 127, 127));
		lblLoiKhuynMi.setFont(new Font("SansSerif", Font.BOLD, 12));
		pDiscount.add(lblLoiKhuynMi);

		lblTrngThi = new JLabel();
		lblTrngThi.setText("Trạng thái");
		lblTrngThi.setForeground(new Color(127, 127, 127));
		lblTrngThi.setFont(new Font("SansSerif", Font.BOLD, 12));
		pDiscount.add(lblTrngThi);

		cmbStatus = new ComboBoxSuggestion<String>();
		cmbStatus.setBackground(new Color(240, 240, 240));
		pDiscount.add(cmbStatus);

		tableScrollDiscount = new TableScrollButton();
		pDiscount.add(tableScrollDiscount);

		srcDiscount = new JScrollPane();
		String[] header = { "STT", "Mã khuyến mãi", "Tên khuyến mãi", "Loại khuyến mãi", "Giá trị", "Trạng thái" };
		discountModel = new DefaultTableModel(header, 0);
		discoutnTable = new JTable(discountModel);

		discoutnTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = discoutnTable.getSelectedRow();
				discount = arrCoupons.get(row);

				txtDiscountID.setText(discount.getDiscountID());
				txtDiscountName.setText(discount.getDiscountName());
				txtStatus.setText(discount.isStatus() ? "Đang diễn ra" : "Hết hạn");
				cmbType.setSelectedItem(discount.getDiscountType());

				txtValue.setText(String.valueOf(discount.getDiscountValue()));

				dateFrom.setDate(discount.getDateStart());
				dateTo.setDate(discount.getDateEnd());

				productChoose = new ArrayList<>(discount.getProducts());
				setUpProductDiscount(discount);

			}
		});

		srcDiscount.setViewportView(discoutnTable);
		TableCustom.apply(srcDiscount, TableCustom.TableType.MULTI_LINE);
		tableScrollDiscount.add(srcDiscount, BorderLayout.CENTER);
		GroupLayout gl_pDiscount = new GroupLayout(pDiscount);
		gl_pDiscount.setHorizontalGroup(gl_pDiscount.createParallelGroup(Alignment.LEADING).addGroup(gl_pDiscount
				.createSequentialGroup().addGap(10)
				.addComponent(lblDanhSchKhuyn, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE).addGap(10)
				.addComponent(lblNhpTnKhuyn, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE).addGap(45)
				.addComponent(lblLoiKhuynMi, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE).addGap(75)
				.addComponent(lblTrngThi, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_pDiscount.createSequentialGroup().addGap(243)
						.addComponent(txtSearchDiscount, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
						.addGap(29)
						.addComponent(cmbDiscount, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
						.addGap(28)
						.addComponent(cmbStatus, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_pDiscount.createSequentialGroup().addGap(10)
						.addComponent(tableScrollDiscount, GroupLayout.DEFAULT_SIZE, 755, Short.MAX_VALUE).addGap(16)));
		gl_pDiscount.setVerticalGroup(gl_pDiscount.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pDiscount.createSequentialGroup().addGap(10)
						.addGroup(gl_pDiscount.createParallelGroup(Alignment.LEADING).addComponent(lblDanhSchKhuyn)
								.addComponent(lblNhpTnKhuyn, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblLoiKhuynMi, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTrngThi, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
						.addGap(1)
						.addGroup(gl_pDiscount.createParallelGroup(Alignment.LEADING)
								.addComponent(txtSearchDiscount, GroupLayout.PREFERRED_SIZE, 20,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(cmbDiscount, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addComponent(cmbStatus, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
						.addGap(17).addComponent(tableScrollDiscount, GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
						.addGap(11)));
		pDiscount.setLayout(gl_pDiscount);

	}

	/**
	 * 
	 */
	private void initProduct() {
		// TODO Auto-generated method stub
		pProduct = new PanelBorder();
		pProduct.setBackground(Color.WHITE);

		lblNhpTnSn = new JLabel();
		lblNhpTnSn.setText("Nhập tên sản phẩm:");
		lblNhpTnSn.setForeground(new Color(127, 127, 127));
		lblNhpTnSn.setFont(new Font("SansSerif", Font.BOLD, 12));
		pProduct.add(lblNhpTnSn);

		txtSearchProduct = new JTextField();
		txtSearchProduct.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				search(txtSearchProduct.getText(), productModel, productTable);
			}
		});
		txtSearchProduct.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtSearchProduct.setColumns(10);
		txtSearchProduct.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtSearchProduct.setBackground(SystemColor.menu);
		pProduct.add(txtSearchProduct);

		lblDanhSchSn = new JLabel();
		lblDanhSchSn.setText("Danh sách sản phẩm");
		lblDanhSchSn.setForeground(new Color(127, 127, 127));
		lblDanhSchSn.setFont(new Font("SansSerif", Font.BOLD, 18));
		pProduct.add(lblDanhSchSn);

		cmbCategory = new ComboBoxSuggestion<String>();
		cmbCategory.setBackground(SystemColor.menu);
		pProduct.add(cmbCategory);

		lblCategory = new JLabel();
		lblCategory.setText("Loại sản phẩm:");
		lblCategory.setForeground(new Color(127, 127, 127));
		lblCategory.setFont(new Font("SansSerif", Font.BOLD, 12));
		pProduct.add(lblCategory);

		tableScrollProduct = new TableScrollButton();
		pProduct.add(tableScrollProduct);

		srcProduct = new JScrollPane();
		String[] headers = { "Mã sản phẩm", "Tên sản phẩm", "Loại sản phẩm", "Giá tiền", "Trạng thái","Mã khuyến mãi", "Chọn" };
		productModel = new DefaultTableModel(headers, 0) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				// TODO Auto-generated method stub
				switch (columnIndex) {
				case 0:
					return Object.class;
				case 1:
					return Object.class;
				case 2:
					return Object.class;
				case 3:
					return Object.class;
				case 4:
					return Object.class;
				case 5:
					return Object.class;
				default:
					return Boolean.class;
				}
			}
		};
		productTable = new JTable(productModel);

		productTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = productTable.getSelectedRow();

				if ((boolean) productTable.getValueAt(row, 6)) {

					for (Product p : productChoose) {
						if (arrProducts.get(row).getProduct_id().equals(p.getProduct_id())) {
							p.setDiscount(discount);
						} else {
							productChoose.add(arrProducts.get(row));
						}
						break;
					}
				} else {
					productChoose.get(row).setDiscount(null);
				}

			}

		});

		srcProduct.setViewportView(productTable);

		tableScrollProduct.add(srcProduct, BorderLayout.CENTER);
		TableCustom.apply(srcProduct, TableCustom.TableType.MULTI_LINE);

		ckShowProductDiscount = new JCheckBoxCustom();
		ckShowProductDiscount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (discount != null) {
					setUpProductDiscount(discount);

				}
			}
		});
		ckShowProductDiscount.setSelected(true);
		ckShowProductDiscount.setFont(new Font("SansSerif", Font.PLAIN, 10));
		ckShowProductDiscount.setText("Sản phẩm đang khuyến mãi");
		ckShowProductDiscount.setForeground(new Color(127, 127, 127));

		GroupLayout gl_pProduct = new GroupLayout(pProduct);
		gl_pProduct.setHorizontalGroup(gl_pProduct.createParallelGroup(Alignment.LEADING).addGroup(gl_pProduct
				.createSequentialGroup().addGap(10)
				.addComponent(lblDanhSchSn, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE).addGap(10)
				.addComponent(lblNhpTnSn, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE).addGap(45)
				.addComponent(lblCategory, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE))
				.addGroup(Alignment.TRAILING, gl_pProduct.createSequentialGroup().addGroup(gl_pProduct
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_pProduct.createSequentialGroup().addGap(243)
								.addComponent(txtSearchProduct, GroupLayout.PREFERRED_SIZE, 155,
										GroupLayout.PREFERRED_SIZE)
								.addGap(29)
								.addComponent(cmbCategory, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(ckShowProductDiscount, GroupLayout.PREFERRED_SIZE, 173,
										GroupLayout.PREFERRED_SIZE)
								.addGap(0, 0, Short.MAX_VALUE))
						.addGroup(gl_pProduct.createSequentialGroup().addGap(10).addComponent(tableScrollProduct,
								GroupLayout.DEFAULT_SIZE, 763, Short.MAX_VALUE)))
						.addGap(10)));
		gl_pProduct.setVerticalGroup(gl_pProduct.createParallelGroup(Alignment.LEADING).addGroup(gl_pProduct
				.createSequentialGroup().addGap(10)
				.addGroup(gl_pProduct.createParallelGroup(Alignment.TRAILING).addGroup(gl_pProduct
						.createSequentialGroup()
						.addGroup(gl_pProduct.createParallelGroup(Alignment.LEADING).addComponent(lblDanhSchSn)
								.addComponent(lblNhpTnSn, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCategory, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
						.addGap(1)
						.addGroup(gl_pProduct.createParallelGroup(Alignment.LEADING)
								.addComponent(txtSearchProduct, GroupLayout.PREFERRED_SIZE, 20,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(cmbCategory, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)))
						.addComponent(ckShowProductDiscount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(17).addComponent(tableScrollProduct, GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
				.addGap(12)));
		pProduct.setLayout(gl_pProduct);

	}

	private void initDetails() {

		discountDetails = new PanelBorder();
		discountDetails.setBackground(Color.WHITE);

		lblChiTitKhuyn = new JLabel();
		lblChiTitKhuyn.setText("Chi tiết khuyến mãi");
		lblChiTitKhuyn.setForeground(new Color(127, 127, 127));
		lblChiTitKhuyn.setFont(new Font("SansSerif", Font.BOLD, 18));

		lblMKhuynMi = new JLabel();
		lblMKhuynMi.setText("Mã khuyến mãi:");
		lblMKhuynMi.setForeground(new Color(127, 127, 127));
		lblMKhuynMi.setFont(new Font("SansSerif", Font.BOLD, 12));

		txtDiscountID = new JTextField();
		txtDiscountID.setEnabled(false);
		txtDiscountID.setBackground(new Color(240, 240, 240));
		txtDiscountID.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtDiscountID.setColumns(10);

		txtDiscountName = new JTextField();
		txtDiscountName.setColumns(10);
		txtDiscountName.setBackground(new Color(240, 240, 240));
		txtDiscountName.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));

		lblTnKhuynMi = new JLabel();
		lblTnKhuynMi.setText("Tên khuyến mãi:");
		lblTnKhuynMi.setForeground(new Color(127, 127, 127));
		lblTnKhuynMi.setFont(new Font("SansSerif", Font.BOLD, 12));

		lblMKhuynMi_1 = new JLabel();
		lblMKhuynMi_1.setText("Loại khuyến mãi");
		lblMKhuynMi_1.setForeground(new Color(127, 127, 127));
		lblMKhuynMi_1.setFont(new Font("SansSerif", Font.BOLD, 12));

		cmbType = new ComboBoxSuggestion<String>();
		cmbType.addItem("%");
		cmbType.addItem("Tiền");
		cmbType.setBackground(SystemColor.menu);

		txtValue = new JTextField();
		txtValue.setColumns(10);
		txtValue.setBackground(new Color(240, 240, 240));
		txtValue.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));

		lblTnKhuynMi_1 = new JLabel();
		lblTnKhuynMi_1.setText("Giá trị:");
		lblTnKhuynMi_1.setForeground(new Color(127, 127, 127));
		lblTnKhuynMi_1.setFont(new Font("SansSerif", Font.BOLD, 12));

		dateFrom = new JDateChooser();
		dateFrom.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		dateFrom.setDateFormatString("yyyy-MM-dd");
		dateFrom.setDate(new Date());

		lblTnKhuynMi_2 = new JLabel();
		lblTnKhuynMi_2.setText("Ngày bắt đầu:");
		lblTnKhuynMi_2.setForeground(new Color(127, 127, 127));
		lblTnKhuynMi_2.setFont(new Font("SansSerif", Font.BOLD, 12));

		dateTo = new JDateChooser();
		dateTo.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		dateTo.setDateFormatString("yyyy-MM-dd");

		lblTnKhuynMi_3 = new JLabel();
		lblTnKhuynMi_3.setText("Ngày kết thúc:");
		lblTnKhuynMi_3.setForeground(new Color(127, 127, 127));
		lblTnKhuynMi_3.setFont(new Font("SansSerif", Font.BOLD, 12));

		txtStatus = new JTextField();
		txtStatus.setEnabled(false);
		txtStatus.setColumns(10);
		txtStatus.setBackground(new Color(240, 240, 240));
		txtStatus.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));

		lblTnKhuynMi_4 = new JLabel();
		lblTnKhuynMi_4.setText("Trạng thái:");
		lblTnKhuynMi_4.setForeground(new Color(127, 127, 127));
		lblTnKhuynMi_4.setFont(new Font("SansSerif", Font.BOLD, 12));

		btnAdd = new JButton("Thêm Khuyến Mãi");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		btnAdd.setBackground(new Color(240, 240, 240));
		btnAdd.setBorder(null);
		btnAdd.setFont(new Font("SansSerif", Font.BOLD, 12));

		btnUpdate = new JButton("Cập nhật");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		btnUpdate.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnUpdate.setBorder(null);
		btnUpdate.setBackground(SystemColor.menu);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(pProduct,
										GroupLayout.PREFERRED_SIZE, 783, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup().addGap(12).addComponent(pDiscount,
										GroupLayout.PREFERRED_SIZE, 781, GroupLayout.PREFERRED_SIZE)))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(discountDetails, GroupLayout.PREFERRED_SIZE, 344, Short.MAX_VALUE)
						.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING,
								groupLayout.createSequentialGroup().addContainerGap().addComponent(discountDetails,
										GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup().addGap(11)
								.addComponent(pDiscount, GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(pProduct, GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)))
				.addGap(12)));
		discountDetails.add(lblChiTitKhuyn);
		discountDetails.add(lblMKhuynMi);
		discountDetails.add(lblTnKhuynMi);
		discountDetails.add(lblMKhuynMi_1);
		discountDetails.add(lblTnKhuynMi_1);
		discountDetails.add(lblTnKhuynMi_2);
		discountDetails.add(dateFrom);
		discountDetails.add(lblTnKhuynMi_3);
		discountDetails.add(dateTo);
		discountDetails.add(lblTnKhuynMi_4);
		discountDetails.add(txtStatus);
		discountDetails.add(txtDiscountID);
		discountDetails.add(txtDiscountName);
		discountDetails.add(cmbType);
		discountDetails.add(txtValue);
		discountDetails.add(btnAdd);
		discountDetails.add(btnUpdate);
		GroupLayout gl_discountDetails = new GroupLayout(discountDetails);
		gl_discountDetails.setHorizontalGroup(gl_discountDetails.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_discountDetails.createSequentialGroup().addGroup(gl_discountDetails
						.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_discountDetails.createSequentialGroup().addGap(10).addComponent(lblChiTitKhuyn,
								GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_discountDetails.createSequentialGroup().addGap(10).addComponent(lblMKhuynMi,
								GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_discountDetails.createSequentialGroup().addGap(10).addComponent(lblTnKhuynMi,
								GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_discountDetails.createSequentialGroup().addGap(10).addComponent(lblMKhuynMi_1,
								GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_discountDetails.createSequentialGroup().addGap(10).addComponent(lblTnKhuynMi_1,
								GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_discountDetails.createSequentialGroup().addGap(10).addComponent(lblTnKhuynMi_2,
								GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_discountDetails.createSequentialGroup().addGap(10).addComponent(lblTnKhuynMi_3,
								GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_discountDetails.createSequentialGroup().addGap(10).addComponent(lblTnKhuynMi_4,
								GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_discountDetails.createSequentialGroup().addContainerGap()
								.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_discountDetails.createSequentialGroup().addContainerGap()
								.addComponent(txtStatus, GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)))
						.addGap(9))
				.addGroup(Alignment.TRAILING, gl_discountDetails.createSequentialGroup().addContainerGap()
						.addComponent(dateFrom, GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE).addContainerGap())
				.addGroup(Alignment.TRAILING,
						gl_discountDetails.createSequentialGroup().addContainerGap()
								.addComponent(dateTo, GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE).addContainerGap())
				.addGroup(Alignment.TRAILING, gl_discountDetails.createSequentialGroup().addContainerGap()
						.addComponent(txtValue, GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE).addContainerGap())
				.addGroup(Alignment.TRAILING, gl_discountDetails.createSequentialGroup().addContainerGap()
						.addComponent(cmbType, GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE).addContainerGap())
				.addGroup(Alignment.TRAILING,
						gl_discountDetails.createSequentialGroup().addContainerGap()
								.addComponent(txtDiscountName, GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
								.addContainerGap())
				.addGroup(Alignment.TRAILING,
						gl_discountDetails.createSequentialGroup().addContainerGap()
								.addComponent(txtDiscountID, GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
								.addContainerGap()));
		gl_discountDetails.setVerticalGroup(gl_discountDetails.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_discountDetails.createSequentialGroup().addGap(10).addComponent(lblChiTitKhuyn).addGap(34)
						.addComponent(lblMKhuynMi, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(txtDiscountID, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblTnKhuynMi, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(txtDiscountName, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addGap(10)
						.addComponent(lblMKhuynMi_1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(cmbType, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE).addGap(10)
						.addComponent(lblTnKhuynMi_1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(txtValue, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE).addGap(10)
						.addComponent(lblTnKhuynMi_2, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(dateFrom, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblTnKhuynMi_3, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(dateTo, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblTnKhuynMi_4, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(txtStatus, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE).addGap(31)
						.addGroup(gl_discountDetails.createParallelGroup(Alignment.LEADING)
								.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
						.addGap(21)));
		discountDetails.setLayout(gl_discountDetails);
		setLayout(groupLayout);
	}

	private void setUpProductDiscount(Discount discount)  {

		discountService = new DiscountServiceImpl();
		 productService = new ProductServiceImpl();
		
		arrProducts = new ArrayList<>();

		if (ckShowProductDiscount.isSelected()) {
			productModel.setRowCount(0);

			productService.getAll().forEach(p -> {

				if (p.getDiscount() != null) {
					if (p.getDiscount().getDiscountID().equals(discount.getDiscountID())) {

						arrProducts.add(p);

					}
				}
			}); 
		} else {
			productModel.setRowCount(0);
			arrProducts = productService.getAll();
		}

		for(int i = 0; i < arrProducts.size(); i++) {
			Product p = arrProducts.get(i);
			
			if (p.getDiscount() != null  ) {
				if(p.getDiscount().getDiscountID().equals(discount.getDiscountID())) {
					productModel.addRow(new Object[] { p.getProduct_id(), p.getProductName(),
							p.getCategory().getCategoryName(), p.getProductPrice(),
							p.isProductStatus() ? "Đang kinh doanh" : "Ngừng kinh doanh",p.getDiscount().getDiscountID(), true });
				}else {
					
			
					productModel.addRow(new Object[] { p.getProduct_id(), p.getProductName(),
							p.getCategory().getCategoryName(), p.getProductPrice(),
							p.isProductStatus() ? "Đang kinh doanh" : "Ngừng kinh doanh",p.getDiscount().getDiscountID(), false });
				}
			} else {
				productModel
						.addRow(new Object[] { p.getProduct_id(), p.getProductName(), p.getCategory().getCategoryName(),
								p.getProductPrice(), p.isProductStatus() ? "Đang kinh doanh" : "Ngừng kinh doanh" });
			}

		}
	}

	public void setUpDiscount() {

		arrCoupons = discountService.getAll();
		discountModel.setRowCount(0);

		for (int i = 0; i < arrCoupons.size(); i++) {
			Discount d = arrCoupons.get(i);
			discountModel.addRow(new Object[] { (i + 1), d.getDiscountID(), d.getDiscountName(), d.getDiscountType(),
					d.getDiscountValue(), d.isStatus() ? "Đang diễn ra" : "Hết hạn" });

		}
	}

	private void save()  {

			if(txtDiscountName.getText().equals("")||txtValue.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
				return;
			}
			
			if(dateFrom.getDate().compareTo(dateTo.getDate()) >0) {
				JOptionPane.showMessageDialog(null, "Ngày kết thúc phải lớn hơn ngày bắt đầu", "Sai thông tin", JOptionPane.WARNING_MESSAGE);
				return;
			}
			Discount newDiscount = new Discount();
			newDiscount.setDiscountID("KM" + (discountService.getAll().size() + 1));
			newDiscount.setDiscountName(txtDiscountName.getText());
			newDiscount.setDiscountType(cmbType.getSelectedItem().toString());
			newDiscount.setDiscountValue(Double.parseDouble(txtValue.getText()));
			newDiscount.setDateEnd(dateTo.getDate());
			newDiscount.setDateStart(dateFrom.getDate());

			if (new Date().compareTo(dateFrom.getDate()) >= 0 && new Date().compareTo(dateTo.getDate()) <= 0) {

				newDiscount.setStatus(true);
			} else {
				newDiscount.setStatus(false);
			}
			

			List<Product> products = new ArrayList<>();
			for (int i = 0; i < productTable.getRowCount(); i++) {
				Boolean value = (Boolean) productTable.getValueAt(i, 6);
				if (value != null && value) {
					Product product = arrProducts.get(i);
					product.setDiscount(discount);
					products.add(product);
				}
			}
			
			if(products != null && products.size()>0) {
				Set<Product> p = new HashSet<>(products);
				newDiscount.setProducts(p);
			}
			if (discountService.save(newDiscount)) {

				JOptionPane.showMessageDialog(null, "Thêm khuyến mãi thành công");
				setUpDiscount();

			} else {
				JOptionPane.showMessageDialog(null, "Thêm khuyến mãi không thành công", "Error Message",
						JOptionPane.ERROR_MESSAGE);
			}
	}

	private void update() {

		Discount newDiscount = new Discount();
		List<Product> products = new ArrayList<>();
		for (int i = 0; i < productTable.getRowCount(); i++) {
			Boolean value = (Boolean) productTable.getValueAt(i, 6);
			if (value != null && value) {
				Product product = arrProducts.get(i);
				product.setDiscount(discount);
				products.add(product);
			}
		}
		if(products != null && products.size()>0) {
			Set<Product> p = new HashSet<>(products);
			newDiscount.setProducts(p);
		}
		System.out.println(products);
		newDiscount.setDiscountID(txtDiscountID.getText());
		newDiscount.setDiscountName(txtDiscountName.getText());
		newDiscount.setDiscountType(cmbType.getSelectedItem().toString());
		newDiscount.setDiscountValue(Double.parseDouble(txtValue.getText()));

		newDiscount.setDateEnd(dateTo.getDate());
		newDiscount.setDateStart(dateFrom.getDate());
	
		if (new Date().compareTo(dateFrom.getDate()) >= 0 && new Date().compareTo(dateTo.getDate()) <= 0) {

			newDiscount.setStatus(true);
		} else {
			newDiscount.setStatus(false);
		}

		/// Còn thiếu cập nhật khuyến mãi cho san rphaamr nên nó méo có chạy
		if (discountService.update(newDiscount, products)) {

			JOptionPane.showMessageDialog(null, "Cập nhật khuyến mãi thành công");
			setUpDiscount();

		} else {
			JOptionPane.showMessageDialog(null, "Cập nhật khuyến mãi không thành công", "Error Message",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void search(String search, DefaultTableModel defaultTableModel, JTable jTable) {
		TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(defaultTableModel);
		jTable.setRowSorter(tr);
		tr.setRowFilter(RowFilter.regexFilter(search));
	}
}
