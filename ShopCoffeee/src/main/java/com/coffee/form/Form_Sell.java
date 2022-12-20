package com.coffee.form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.coffee.custom.PanelBorder;
import com.coffee.custom.PanelShadow;
import com.coffee.custom.WrapLayout;
import com.coffee.entity.Area;
import com.coffee.entity.Bill;
import com.coffee.entity.BillDetail;
import com.coffee.entity.Category;
import com.coffee.entity.CoffeeTable;
import com.coffee.entity.Customer;
import com.coffee.entity.Discount;
import com.coffee.entity.Employee;
import com.coffee.entity.Product;
import com.coffee.service.AreaService;
import com.coffee.service.BillService;
import com.coffee.service.CategoryService;
import com.coffee.service.CoffeeTableService;
import com.coffee.service.CustomerService;
import com.coffee.service.ProductService;
import com.coffee.serviceimpl.AreaServiceImpl;
import com.coffee.serviceimpl.BillServiceImpl;
import com.coffee.serviceimpl.CategoryServiceImpl;
import com.coffee.serviceimpl.CoffeeTableServiceImpl;
import com.coffee.serviceimpl.CustomerServiceImpl;
import com.coffee.serviceimpl.ProductServiceImpl;

/**
 * @author Nguyen Phuc Thinh
 */
public class Form_Sell extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2590936053076482802L;

	private Locale localeVN = Locale.getDefault();
	private NumberFormat moneyFormat = NumberFormat.getInstance(localeVN);

	private Employee employee;
	private AreaService areaService;
	private CategoryService categoryService;
	private ProductService productService;
	private BillService billService;
	private CoffeeTableService tableService;
	private CustomerService customerService;

	private List<Product> arrProducts;
	private List<Category> arrCategories;
	private List<Area> arrAreas;

	private Bill bill;
	private CoffeeTable coffeeTable;
	private Customer customer;

	private double totalMoney;
	private double moneyProduct;

	private PanelBorder pSeat;
	private PanelBorder pProduct;
	private PanelBorder pBill;
	private JScrollPane scrSitPosition;
	private JPanel pArea;
	private JPanel[] pAreaItem;
	private PanelShadow[] pTableItem;
	private JLabel[] lblTableName;
	private JLabel[] lblImage;
	private JComboBox<String> cmbCategory;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextField txtSearchProduct;
	private JButton btnSearchProduct;
	private JScrollPane scrProduct;
	private JPanel pProductItem;
	private PanelShadow[] pItem;
	private JLabel[] lblImageProduct;
	private JLabel[] lblProductName;
	private JLabel[] lblPriceDisount;
	private JLabel[] lblProductPrice;
	private JLabel[] lblImageDisscount;
	private JLabel lblNewLabel_2;
	private JTextField txtCustomer;
	private JLabel lblNewLabel_3;
	private JTextField txtBillID;
	private JLabel lblNewLabel_4;
	private JTextField txtEmployee;
	private JTextField txtPhone;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JTextField txtProductPrice;
	private JTextField txtPoint;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private JTextField txtTotal;
	private JTextArea txtNote;
	private JLabel lblNewLabel_9;
	private JButton btnFindCustomer;
	private JButton btnDiscount;
	private JScrollPane scrBillDetails;
	private JButton btnCancel;
	private JButton btnSplit;
	private JButton btnPay;
	private JButton btnJoinTable;
	private JTable tableDetails;
	private DefaultTableModel modelDetails;
	private JPopupMenu menuProduct;
	private JComboBox<String> cbmPayment;
	private JLabel lblNewLabel_10;

	/**
	 * Create the panel.
	 */
	public Form_Sell(Employee emp)  {
		employee = emp;
		areaService = new AreaServiceImpl();
		categoryService = new CategoryServiceImpl();
		productService = new ProductServiceImpl();
		billService = new BillServiceImpl();
		tableService = new CoffeeTableServiceImpl();

		pBill = new PanelBorder();

		intitAreaComponent();

		initProductComponent();

		GroupLayout();

		getAreaAndTable();

		getCategoriesAndProducts();
		// initBillComponent(coffeeTable);
	}

	/**
	 * SeatUI
	 */

	private void intitAreaComponent() {
		// TODO Auto-generated method stub
		pSeat = new PanelBorder();
		pSeat.setBackground(Color.WHITE);
		pSeat.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192)), "Danh s\u00E1ch b\u00E0n",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.LIGHT_GRAY));
		pSeat.setLayout(new BorderLayout());

		pArea = new JPanel();
		pArea.setBackground(Color.WHITE);
		pArea.setLayout(new GridLayout(0, 1, 10, 10));
		scrSitPosition = new JScrollPane();
		scrSitPosition.setViewportView(pArea);
		pSeat.add(scrSitPosition, BorderLayout.CENTER);

	}

	/**
	 * 
	 */
	private void createItemTable(List<Area> arrArea) {
		// TODO Auto-generated method stub
		pArea.removeAll();
		pAreaItem = new JPanel[arrArea.size()];

		for (int i = 0; i < arrArea.size(); i++) {

			Area postion = arrArea.get(i);
			List<CoffeeTable> tables = postion.getTable();

			pAreaItem[i] = new JPanel();
			pAreaItem[i].setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192)),
					"Khu vực " + postion.getId(), TitledBorder.LEADING, TitledBorder.TOP, null, Color.LIGHT_GRAY));
			pAreaItem[i].setBackground(Color.WHITE);

			pArea.add(pAreaItem[i]);
			pAreaItem[i].setLayout(new WrapLayout(FlowLayout.LEADING, 6, 10));

			pTableItem = new PanelShadow[tables.size()];
			lblImage = new JLabel[tables.size()];
			lblTableName = new JLabel[tables.size()];

			for (int j = 0; j < tables.size(); j++) {

				CoffeeTable table = tables.get(j);

				pTableItem[j] = new PanelShadow();
				pTableItem[j].setBackground(null);
				pTableItem[j].setBackground(table.isStatus() ? Color.ORANGE : null);
				pTableItem[j].setBorder(null);
				pTableItem[j].setPreferredSize(new Dimension(80, 80));
				pTableItem[j].setLayout(new BorderLayout(0, 0));

				lblImage[j] = new JLabel();
				lblImage[j].setIcon(new ImageIcon("image/coffee-table.png"));
				lblImage[j].setHorizontalAlignment(SwingConstants.CENTER);
				pTableItem[j].add(lblImage[j], BorderLayout.CENTER);

				lblTableName[j] = new JLabel();
				lblTableName[j].setBorder(new EmptyBorder(0, 0, 5, 0));
				lblTableName[j].setHorizontalAlignment(SwingConstants.CENTER);
				lblTableName[j].setText(table.getName());
				pTableItem[j].add(lblTableName[j], BorderLayout.SOUTH);

				pAreaItem[i].add(pTableItem[j]);

				pTableItem[j].addMouseListener(new MouseAdapter() {

					@Override
					public void mouseClicked(MouseEvent e) {
						PanelShadow tableClick = (PanelShadow) e.getSource();
						coffeeTable = table;

						if (SwingUtilities.isLeftMouseButton(e)) {

							if (table.isStatus()) {

								getBillOfTable(table);

							} else {

								if (OpenTable(table)) {
									tableClick.setBackground(Color.orange);
								}

							}
						}

					}
				});
			}

		}
		pArea.validate();

	}

	/**
	 * ProductUI
	 */
	private void initProductComponent() {
		// TODO Auto-generated method stub
		pProduct = new PanelBorder();
		pProduct.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192)), "Sản phẩm", TitledBorder.LEADING,
				TitledBorder.TOP, null, Color.LIGHT_GRAY));
		pProduct.setBackground(Color.WHITE);

		cmbCategory = new JComboBox<String>();
		cmbCategory.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (cmbCategory.getSelectedIndex() == 0) {
					creatItemProduct(arrProducts);
				} else {
					List<Product> products = arrProducts.stream()
							.filter(p -> p.getCategory().getCategoryName().equals(cmbCategory.getSelectedItem()))
							.collect(Collectors.toList());

					creatItemProduct(products);
				}
			}
		});

		lblNewLabel = new JLabel("Chọn loại sản phẩm");
		lblNewLabel.setForeground(new Color(192, 192, 192));
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 12));

		lblNewLabel_1 = new JLabel("Nhập tên sản phẩm");
		lblNewLabel_1.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_1.setFont(new Font("SansSerif", Font.BOLD, 12));

		txtSearchProduct = new JTextField();
		txtSearchProduct.setBackground(new Color(240, 240, 240));
		txtSearchProduct.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));

		btnSearchProduct = new JButton();
		btnSearchProduct.setIcon(new ImageIcon("image/find.png"));
		btnSearchProduct.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				List<Product> products = arrProducts.stream().filter(
						p -> p.getProductName().toUpperCase().contains(txtSearchProduct.getText().toUpperCase()))
						.collect(Collectors.toList());

				creatItemProduct(products);
			}
		});

		pProductItem = new JPanel();
		pProductItem.setBackground(Color.WHITE);
		pProductItem.setLayout(new WrapLayout(FlowLayout.LEADING, 20, 10));

		scrProduct = new JScrollPane();
		scrProduct.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 0), "Danh sách sản phẩm",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.LIGHT_GRAY));
		scrProduct.setViewportView(pProductItem);
		GroupLayout gl_pProduct = new GroupLayout(pProduct);
		gl_pProduct.setHorizontalGroup(gl_pProduct.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pProduct.createSequentialGroup().addGap(5)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
						.addGap(117)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_pProduct.createSequentialGroup().addGap(5)
						.addComponent(txtSearchProduct, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
						.addGap(6)
						.addComponent(btnSearchProduct, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addGap(70)
						.addComponent(cmbCategory, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE))
				.addComponent(scrProduct, GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE));
		gl_pProduct.setVerticalGroup(gl_pProduct.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pProduct.createSequentialGroup()
						.addGroup(gl_pProduct.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addGap(5)
						.addGroup(gl_pProduct.createParallelGroup(Alignment.LEADING)
								.addComponent(txtSearchProduct, GroupLayout.PREFERRED_SIZE, 25,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(btnSearchProduct, GroupLayout.PREFERRED_SIZE, 25,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(cmbCategory, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addGap(10).addComponent(scrProduct, GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE)
						.addGap(10)));
		pProduct.setLayout(gl_pProduct);

	}

	/**
	 * 
	 */
	private void creatItemProduct(List<Product> products) {
		// TODO Auto-generated method stub
		pProductItem.removeAll();
		pItem = new PanelShadow[products.size()];
		lblImageProduct = new JLabel[products.size()];
		lblProductName = new JLabel[products.size()];
		lblPriceDisount = new JLabel[products.size()];
		lblProductPrice = new JLabel[products.size()];
		lblImageDisscount = new JLabel[products.size()];

		for (int i = 0; i < products.size(); i++) {
			double TOTAL = 0;
			Product product = products.get(i);

			pItem[i] = new PanelShadow();
			pItem[i].setPreferredSize(new Dimension(120, 160));
			pProductItem.add(pItem[i]);
			pItem[i].setLayout(null);

			lblImageProduct[i] = new JLabel();
			ImageIcon img = null;
			if(product.getImage()!=null) {
				img = new ImageIcon(product.getImage());
			}else {
				img = new ImageIcon("image/coffee_image.png");
			}
			Image newImage = img.getImage().getScaledInstance(70, 60, Image.SCALE_SMOOTH);
			lblImageProduct[i].setIcon(new ImageIcon(newImage));
			lblImageProduct[i].setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
			lblImageProduct[i].setBounds(17, 10, 78, 60);
			pItem[i].add(lblImageProduct[i]);

			lblProductName[i] = new JLabel(product.getProductName());
			lblProductName[i].setHorizontalAlignment(SwingConstants.CENTER);
			lblProductName[i].setFont(new Font("SansSerif", Font.PLAIN, 12));
			lblProductName[i].setBounds(0, 83, 110, 15);
			pItem[i].add(lblProductName[i]);

			lblPriceDisount[i] = new JLabel();
			lblPriceDisount[i].setHorizontalAlignment(SwingConstants.CENTER);
			lblPriceDisount[i].setFont(new Font("SansSerif", Font.PLAIN, 10));
			lblPriceDisount[i].setBounds(0, 100, 110, 15);
			pItem[i].add(lblPriceDisount[i]);

			lblProductPrice[i] = new JLabel();
			lblProductPrice[i].setHorizontalAlignment(SwingConstants.CENTER);
			lblProductPrice[i].setFont(new Font("SansSerif", Font.PLAIN, 12));
			lblProductPrice[i].setBounds(0, 100, 110, 15);
			pItem[i].add(lblProductPrice[i]);

			lblImageDisscount[i] = new JLabel();
			lblImageDisscount[i].setBounds(5, 125, 20, 20);
			lblImageDisscount[i].setIcon(new ImageIcon("image/discount_product.png"));
			pItem[i].add(lblImageDisscount[i]);

			if (product.getDiscount() == null) {
				lblPriceDisount[i].setVisible(false);
				lblImageDisscount[i].setVisible(false);
				lblProductPrice[i].setText(moneyFormat.format(product.getProductPrice()) + " đ");
			} else {

				Date start = product.getDiscount().getDateStart();
				Date end = product.getDiscount().getDateEnd();
				if (start.compareTo(new Date()) <= 0 && end.compareTo(new Date()) >= 0) {
					lblProductPrice[i].setVisible(false);
					Discount discount = product.getDiscount();
					if (discount.getDiscountType().equals("%")) {
						TOTAL = product.getProductPrice()
								- (product.getProductPrice() * discount.getDiscountValue() / 100);

					} else {
						TOTAL = product.getProductPrice() - discount.getDiscountValue();
					}
					lblPriceDisount[i].setText("<html><strike>" + moneyFormat.format(product.getProductPrice()) + "đ"
							+ "</strike>" + " " + "<font color=#ff0000 size=\"3\">" + moneyFormat.format(TOTAL)
							+ "đ</font></html>");
				} else {
					lblPriceDisount[i].setVisible(false);
					lblImageDisscount[i].setVisible(false);
					lblProductPrice[i].setText(moneyFormat.format(product.getProductPrice()) + " đ");
				}
			}

			pItem[i].addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent e) {
					PanelShadow productClick = (PanelShadow) e.getSource();

					if (SwingUtilities.isRightMouseButton(e)) {
						showMenuProduct(product);
						menuProduct.show(productClick, e.getX(), e.getY());
					}
				}
			});

		}
		pProductItem.updateUI();

	}

	/**
	 * Bill UI
	 * 
	 * @param seat
	 */
	private void initBillComponent(CoffeeTable table) {
		// TODO Auto-generated method stub
		pBill.removeAll();

		pBill.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192)), "Hóa đơn " + table.getName(),
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(192, 192, 192)));
		pBill.setBackground(Color.WHITE);

		lblNewLabel_2 = new JLabel("Tên khách hàng:");
		lblNewLabel_2.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_2.setFont(new Font("SansSerif", Font.BOLD, 12));

		txtCustomer = new JTextField();
		txtCustomer.setBackground(new Color(240, 240, 240));
		txtCustomer.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));

		lblNewLabel_3 = new JLabel("Mã sản phẩm:");
		lblNewLabel_3.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_3.setFont(new Font("SansSerif", Font.BOLD, 12));

		txtBillID = new JTextField();
		txtBillID.setText(bill.getBill_id() != null ? bill.getBill_id() : "HD" + System.currentTimeMillis());
		txtBillID.setEnabled(false);
		txtBillID.setBackground(new Color(240, 240, 240));
		txtBillID.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));

		lblNewLabel_4 = new JLabel("Tên nhân viên:");
		lblNewLabel_4.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_4.setFont(new Font("SansSerif", Font.BOLD, 12));

		txtEmployee = new JTextField();
		txtEmployee.setText(bill.getEmployee() != null ? bill.getEmployee().getFullName() : "");
		txtEmployee.setBackground(new Color(240, 240, 240));
		txtEmployee.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));

		txtPhone = new JTextField();
		txtPhone.setBackground(new Color(240, 240, 240));
		txtPhone.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));

		lblNewLabel_5 = new JLabel("Số điện thoại:");
		lblNewLabel_5.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_5.setFont(new Font("SansSerif", Font.BOLD, 12));

		lblNewLabel_6 = new JLabel("Tổng sản phẩm:");
		lblNewLabel_6.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_6.setFont(new Font("SansSerif", Font.BOLD, 12));

		txtProductPrice = new JTextField();
		txtProductPrice.setBackground(new Color(240, 240, 240));
		txtProductPrice.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));

		txtPoint = new JTextField();
		txtPoint.setBackground(new Color(240, 240, 240));
		txtPoint.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));

		lblNewLabel_7 = new JLabel("Điểm tích lũy:");
		lblNewLabel_7.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_7.setFont(new Font("SansSerif", Font.BOLD, 12));

		lblNewLabel_8 = new JLabel("Thành tiền:");
		lblNewLabel_8.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_8.setFont(new Font("SansSerif", Font.BOLD, 12));

		txtTotal = new JTextField();
		txtTotal.setBackground(new Color(240, 240, 240));
		txtTotal.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));

		txtNote = new JTextArea();
		txtNote.setBackground(new Color(240, 240, 240));
		txtNote.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));

		lblNewLabel_9 = new JLabel("Ghi chú:");
		lblNewLabel_9.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_9.setFont(new Font("SansSerif", Font.BOLD, 12));

		btnFindCustomer = new JButton();
		btnFindCustomer.setIcon(new ImageIcon("image/find.png"));
		btnFindCustomer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				customer = findCustomer();

				if (customer != null) {
					txtCustomer.setText(customer.getFullName());
					txtPoint.setText(customer.getPoint() + "");
				} else {
					JOptionPane.showMessageDialog(null, "Không tìm thấy khách hàng");
				}

			}
		});

		btnDiscount = new JButton();
		btnDiscount.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (customer != null) {
					String point = JOptionPane.showInputDialog(null,
							"Bạn có " + customer.getPoint() + " điểm.\nBạn có muốn đổi điêm (1 điểm = 1đ) ",
							"Nhập điểm cần đổi");

					if(isNumeric(point)) {
						if(Integer.parseInt(point) <=  customer.getPoint()) {
							txtPoint.setText(customer.getPoint() - Integer.parseInt(point)+"");
							totalMoney  = totalMoney - Integer.parseInt(point);
							txtTotal.setText(moneyFormat.format(totalMoney)+"đ");
						}else {
							JOptionPane.showMessageDialog(null, "Giá trị nhập phải nhỏ hơn hoặc bằng điểm của khách hàng");
						}
					}else {
						JOptionPane.showMessageDialog(null, "Giá trị sai định dạng");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Chưa có khách hàng để sử dụng");
				}
			}
		});
		String[] header = { "#", "Tên SP", "Số lượng", "Tổng", "" };
		modelDetails = new DefaultTableModel(header, 0) {
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
				default:
					return Boolean.class;
				}
			}
		};
		tableDetails = new JTable(modelDetails);
		tableDetails.getColumnModel().getColumn(0).setMaxWidth(30);
		tableDetails.getColumnModel().getColumn(4).setMaxWidth(30);

		scrBillDetails = new JScrollPane();
		scrBillDetails.setBackground(Color.WHITE);
		scrBillDetails.setViewportView(tableDetails);
		scrBillDetails.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192)), "Chi tiết hóa đơn",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.LIGHT_GRAY));

		btnCancel = new JButton("Hủy hóa đơn");
		btnCancel.setFont(new Font("SansSerif", Font.PLAIN, 10));
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				BillCancel();
			}
		});

		btnSplit = new JButton("Tách && Thanh toán");
		btnSplit.setFont(new Font("SansSerif", Font.PLAIN, 10));
		btnSplit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if (spiltBill().size() > 0) {
					Dialog_SplitBill splitBill = new Dialog_SplitBill(spiltBill(), bill, Form_Sell.this, employee);
					splitBill.setLocationRelativeTo(null);
					splitBill.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Bạn chưa chọn san phẩm cần tách", "Tách hóa đơn",
							JOptionPane.WARNING_MESSAGE);
				}

			}
		});

		btnPay = new JButton("Thanh toán");
		btnPay.setFont(new Font("SansSerif", Font.PLAIN, 10));
		btnPay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (bill.getBillStatus().equals("Chờ thanh toán")) {
					if (bill.getBillDetails().size() < 0) {
						JOptionPane.showMessageDialog(null, "Hóa đơn chưa có sản phẩm", "Thanh toán",
								JOptionPane.WARNING_MESSAGE);
					} else {
						PayBill();

						int result = JOptionPane.showConfirmDialog(null, "Bạn muốn in hóa đơn?", "Thanh toán",
								JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (result == JOptionPane.YES_OPTION) {
							JOptionPane.showMessageDialog(null, "In hóa đơn thành công");
						}
					}
				} 
				
				
				
				
				
				
	
			}

		});

		btnJoinTable = new JButton("Gộp & Thanh toán");
		btnJoinTable.setFont(new Font("SansSerif", Font.PLAIN, 10));
		btnJoinTable.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Dialog_JoinTable joinTable = new Dialog_JoinTable(table, bill, Form_Sell.this,employee);
				joinTable.setLocationRelativeTo(null);
				joinTable.setVisible(true);
			}
		});

		cbmPayment = new JComboBox<String>();
		cbmPayment.addItem("Mã QR");
		cbmPayment.addItem("Tiền mặt");
		cbmPayment.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		cbmPayment.setBackground(SystemColor.menu);

		lblNewLabel_10 = new JLabel("Loại thanh toán");
		lblNewLabel_10.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_10.setFont(new Font("SansSerif", Font.BOLD, 12));
		GroupLayout gl_pBill = new GroupLayout(pBill);
		gl_pBill.setHorizontalGroup(gl_pBill.createParallelGroup(Alignment.LEADING).addGroup(gl_pBill
				.createSequentialGroup().addGap(5)
				.addGroup(gl_pBill.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pBill.createSequentialGroup()
								.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 116,
										GroupLayout.PREFERRED_SIZE)
								.addGap(52).addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 116,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pBill.createSequentialGroup()
								.addComponent(txtCustomer, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(txtBillID, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pBill.createSequentialGroup()
								.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 116,
										GroupLayout.PREFERRED_SIZE)
								.addGap(52).addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 116,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pBill.createSequentialGroup()
								.addComponent(txtPhone, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
								.addGap(10)
								.addComponent(btnFindCustomer, GroupLayout.PREFERRED_SIZE, 25,
										GroupLayout.PREFERRED_SIZE)
								.addGap(17)
								.addComponent(txtEmployee, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pBill.createSequentialGroup()
								.addComponent(lblNewLabel_7, GroupLayout.PREFERRED_SIZE, 116,
										GroupLayout.PREFERRED_SIZE)
								.addGap(52).addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 116,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pBill.createSequentialGroup()
								.addComponent(txtPoint, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
								.addGap(18).addComponent(txtProductPrice, GroupLayout.PREFERRED_SIZE, 150,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pBill.createSequentialGroup()
								.addComponent(lblNewLabel_9, GroupLayout.PREFERRED_SIZE, 116,
										GroupLayout.PREFERRED_SIZE)
								.addGap(52).addComponent(lblNewLabel_8, GroupLayout.PREFERRED_SIZE, 116,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pBill.createSequentialGroup()
								.addComponent(txtNote, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addGroup(gl_pBill.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_pBill.createSequentialGroup()
												.addComponent(txtTotal, GroupLayout.PREFERRED_SIZE, 116,
														GroupLayout.PREFERRED_SIZE)
												.addGap(9).addComponent(btnDiscount, GroupLayout.PREFERRED_SIZE, 25,
														GroupLayout.PREFERRED_SIZE))
										.addComponent(lblNewLabel_10, GroupLayout.PREFERRED_SIZE, 116,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(cbmPayment, GroupLayout.PREFERRED_SIZE, 150,
												GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_pBill.createSequentialGroup()
								.addComponent(btnSplit, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
								.addGap(18).addComponent(btnJoinTable, GroupLayout.PREFERRED_SIZE, 150,
										GroupLayout.PREFERRED_SIZE))
						.addComponent(scrBillDetails, GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
						.addGroup(gl_pBill.createSequentialGroup()
								.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
								.addGap(110)
								.addComponent(btnPay, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)))));
		gl_pBill.setVerticalGroup(gl_pBill.createParallelGroup(Alignment.LEADING).addGroup(gl_pBill
				.createSequentialGroup()
				.addGroup(gl_pBill.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
				.addGap(5)
				.addGroup(gl_pBill.createParallelGroup(Alignment.LEADING)
						.addComponent(txtCustomer, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtBillID, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
				.addGap(10)
				.addGroup(gl_pBill.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
				.addGap(5)
				.addGroup(gl_pBill.createParallelGroup(Alignment.LEADING)
						.addComponent(txtPhone, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnFindCustomer, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtEmployee, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
				.addGap(10)
				.addGroup(gl_pBill.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_7, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
				.addGap(5)
				.addGroup(gl_pBill.createParallelGroup(Alignment.LEADING)
						.addComponent(txtPoint, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtProductPrice, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
				.addGap(10)
				.addGroup(gl_pBill.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_9, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_8, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
				.addGap(5)
				.addGroup(gl_pBill.createParallelGroup(Alignment.LEADING)
						.addComponent(txtNote, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_pBill.createSequentialGroup()
								.addGroup(gl_pBill.createParallelGroup(Alignment.LEADING)
										.addComponent(txtTotal, GroupLayout.PREFERRED_SIZE, 25,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnDiscount, GroupLayout.PREFERRED_SIZE, 25,
												GroupLayout.PREFERRED_SIZE))
								.addGap(10)
								.addComponent(lblNewLabel_10, GroupLayout.PREFERRED_SIZE, 25,
										GroupLayout.PREFERRED_SIZE)
								.addGap(5)
								.addComponent(cbmPayment, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
				.addGap(18)
				.addGroup(gl_pBill.createParallelGroup(Alignment.LEADING)
						.addComponent(btnSplit, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnJoinTable, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
				.addGap(10).addComponent(scrBillDetails, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE).addGap(4)
				.addGroup(gl_pBill.createParallelGroup(Alignment.LEADING)
						.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPay, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
				.addGap(15)));
		pBill.setLayout(gl_pBill);

		pBill.updateUI();
	}

	private void GroupLayout() {
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(pSeat, GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(pProduct, GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(pBill, GroupLayout.PREFERRED_SIZE, 338, GroupLayout.PREFERRED_SIZE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(pBill, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE)
				.addComponent(pSeat, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE)
				.addComponent(pProduct, GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE));
		setLayout(groupLayout);
	}

	/****************************** Method ***********************************/

	private void getCategoriesAndProducts() {
		categoryService = new CategoryServiceImpl();
		productService = new ProductServiceImpl();

		arrCategories = categoryService.getAll();
		arrProducts = productService.getAll();

		cmbCategory.removeAllItems();
		cmbCategory.addItem("Tất cả");

		if (arrCategories != null) {
			arrCategories.forEach(c -> {
				cmbCategory.addItem(c.getCategoryName());
			});
		}

		if (arrProducts != null) {
			creatItemProduct(arrProducts);
		}

	}

	public void getAreaAndTable() {
		areaService = new AreaServiceImpl();
		arrAreas = areaService.getAll();

		if (arrAreas != null) {
			createItemTable(arrAreas);
		}

	}

	private void getBillOfTable(CoffeeTable coffeeTable) {

		totalMoney = 0;
		moneyProduct = 0;

		billService = new BillServiceImpl();
		bill = billService.findBillByTable(coffeeTable);

		if (bill != null) {
			initBillComponent(coffeeTable);
			totalMoney = getTotalBill(bill.getBillDetails());
			moneyProduct = getMoneyProductinBill(bill.getBillDetails());

			txtProductPrice.setText(moneyFormat.format(moneyProduct) + "đ");
			txtTotal.setText(moneyFormat.format(totalMoney) + "đ");

			updateProductToTable();

		} else {
			JOptionPane.showMessageDialog(null, "Ko tìm thấy hóa đơn");
		}
	}

	private boolean OpenTable(CoffeeTable coffeeTable) {
		totalMoney = 0;
		moneyProduct = 0;
		tableService = new CoffeeTableServiceImpl();
		int result = JOptionPane.showConfirmDialog(null, "Bạn muốn mở bàn này", "Mở bàn", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		if (result == JOptionPane.YES_OPTION) {

			coffeeTable.setOpenTime(new Date());
			coffeeTable.setStatus(true);

			bill = new Bill();
			bill.setBill_id("HD" + System.currentTimeMillis());
			bill.setBillStatus("Chờ thanh toán");
			bill.setCreateDate(coffeeTable.getOpenTime());
			bill.setTable(coffeeTable);
			bill.setTotalPrice(totalMoney);
			bill.setEmployee(employee);
			initBillComponent(coffeeTable);

			txtProductPrice.setText(moneyFormat.format(moneyProduct) + "đ");
			txtTotal.setText(moneyFormat.format(totalMoney) + "đ");

			if (tableService.openTable(coffeeTable) && billService.creatBill(bill)) {
				JOptionPane.showMessageDialog(null, "Mở bàn thành công");
				return true;
			} else {

				JOptionPane.showMessageDialog(null, "Đã có lỗi xảy ra");
				return false;
			}
		}
		return false;

	}

	private void showMenuProduct(Product product) {
		menuProduct = new JPopupMenu();

		JMenuItem mAddProduct = new JMenuItem("Thêm sản phẩm");
		JMenuItem mDeleteProduct = new JMenuItem("Xóa sản phẩm");

		mAddProduct.setIcon(new ImageIcon("image/add-product.png"));
		mDeleteProduct.setIcon(new ImageIcon("image/remove-product.png"));

		menuProduct.add(mAddProduct);

		bill.getBillDetails().forEach(b -> {
			if (b.getProduct().getProduct_id().equals(product.getProduct_id())) {
				menuProduct.add(mDeleteProduct);
			}
		});

		mAddProduct.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				BillDetail billDetail = new BillDetail(product, 1, moneyProduct(product));
				addProductToBill(billDetail);
			}
		});

		mDeleteProduct.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				BillDetail billDetail = new BillDetail(product, 1, moneyProduct(product));
				deleteProductFromBill(billDetail);
			}

		});

	}

	// Thêm sản phẩm vào tbale
	private void addProductToBill(BillDetail billDetail) {
		// TODO Auto-generated method stub
		billService = new BillServiceImpl();

		List<BillDetail> stringsList = new ArrayList<>(bill.getBillDetails());

		if (stringsList.contains(billDetail)) {

			BillDetail detail = stringsList.get(stringsList.indexOf(billDetail));

			detail.setAmount(billDetail.getAmount() + detail.getAmount());
			detail.setTotal(billDetail.getTotal() + detail.getTotal());

			bill.getBillDetails().remove(detail);
			bill.getBillDetails().add(detail);

			billService.updateBillDetail(detail);

			totalMoney = totalMoney + billDetail.getTotal();
			moneyProduct = moneyProduct + billDetail.getProduct().getProductPrice();

		} else {
			billDetail.setBill(bill);
			bill.getBillDetails().add(billDetail);
			billService.addDetailToBill(bill);
			totalMoney = totalMoney + billDetail.getTotal();
			moneyProduct = moneyProduct + billDetail.getProduct().getProductPrice();

		}

		updateProductToTable();

	}

	// Xóa sản phẩm khỏi tbale
	private void deleteProductFromBill(BillDetail billDetail) {
		// TODO Auto-generated method stub

		billService = new BillServiceImpl();
		bill = billService.findBillByTable(coffeeTable);
		List<BillDetail> stringsList = new ArrayList<>(bill.getBillDetails());

		if (stringsList.contains(billDetail)) {
			BillDetail detail = stringsList.get(stringsList.indexOf(billDetail));

			if (detail.getAmount() > 1) {
				detail.setAmount(detail.getAmount() - billDetail.getAmount());
				detail.setTotal(detail.getTotal() - billDetail.getTotal());

				bill.getBillDetails().remove(detail);
				bill.getBillDetails().add(detail);

				billService.updateBillDetail(detail);

				totalMoney = totalMoney - billDetail.getTotal();
				moneyProduct = moneyProduct - billDetail.getProduct().getProductPrice();
			} else {

				bill.getBillDetails().remove(detail);

				billService.removeBillDetail(detail);

				totalMoney = totalMoney - billDetail.getTotal();
				moneyProduct = moneyProduct - billDetail.getProduct().getProductPrice();
			}

		}
		updateProductToTable();
	}

	private List<BillDetail> spiltBill() {
		List<BillDetail> billDetails = new ArrayList<BillDetail>();
		for (int i = 0; i < tableDetails.getRowCount(); i++) {
			Boolean value = (Boolean) tableDetails.getValueAt(i, 4);
			if (value != null && value) {

				billDetails.add(new ArrayList<>(bill.getBillDetails()).get(i));
			}

		}
		return billDetails;
	}

	// Cập nhật table khi add/delete
	public void updateProductToTable() {

		txtProductPrice.setText(moneyFormat.format(moneyProduct) + "đ");
		txtTotal.setText(moneyFormat.format(totalMoney) + "đ");

		modelDetails.setRowCount(0);

		billService = new BillServiceImpl();

		bill = billService.findBillByTable(coffeeTable);

		int i = 0;
		for (BillDetail b : bill.getBillDetails()) {
			i++;

			modelDetails.addRow(new Object[] { i, b.getProduct().getProductName(), b.getAmount(),
					moneyFormat.format(b.getTotal()) + "đ" });
		}

	}

	private void BillCancel() {
		int result = JOptionPane.showConfirmDialog(null, "Bạn muốn hủy hóa đơn này?", "Hủy hóa đơn",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		if (result == JOptionPane.YES_OPTION) {
			bill.setBillStatus("Đã hủy");
			bill.setTotalPrice(totalMoney);
			bill.setPayDate(new Date());
			bill.setBillNote("Hủy lúc"+new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));

			if (billService.deleteBill(bill)) {
				JOptionPane.showMessageDialog(null, "Hủy hóa đơn thành công");
				getAreaAndTable();
				pBill.removeAll();
				pBill.setBorder(null);
				pBill.updateUI();
			} else {
				JOptionPane.showMessageDialog(null, "Đã có lỗi xảy ra", "Hủy hóa đơn", JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	private void PayBill() {
		int result = JOptionPane.showConfirmDialog(null, "Bạn muốn thanh toán hóa đơn này?", "thanh toán hóa đơn",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		if (result == JOptionPane.YES_OPTION) {
			bill.setBillStatus("Đã hoàn thành");
			bill.setTotalPrice(totalMoney);
			bill.setPayDate(new Date());
			bill.setCustomer(customer != null ? customer : null);
			bill.setPayment(cbmPayment.getSelectedItem().toString());
			if (customer != null) {
				double pount = Integer.parseInt(txtPoint.getText()) + (totalMoney * 1 / 100);
				customer.setPoint((int) pount);
			}
			if (billService.payBill(bill, customer)) {
				JOptionPane.showMessageDialog(null, "Thanh toán thành công thành công");

				getAreaAndTable();
				pBill.removeAll();
				pBill.setBorder(null);
				pBill.updateUI();
			} else {
				JOptionPane.showMessageDialog(null, "Đã có lỗi xảy ra", "Hủy hóa đơn", JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	private Customer findCustomer() {

		customerService = new CustomerServiceImpl();

		Customer c = customerService.findByPhone(Long.parseLong(txtPhone.getText()));

		return c;
	}

	private double getMoneyProductinBill(Set<BillDetail> details) {

		double money = 0;

		for (BillDetail b : details) {
			Product p = b.getProduct();

			money = money + (p.getProductPrice() * b.getAmount());

		}

		return money;
	}

	private double getTotalBill(Set<BillDetail> details) {

		double money = 0;

		for (BillDetail b : details) {
			Product p = b.getProduct();

			money = money + (moneyProduct(p) * b.getAmount());

		}

		return money;
	}

	private double moneyProduct(Product product) {
		double TOTAL = 0;
		if (product.getDiscount() != null) {
			Date start = product.getDiscount().getDateStart();
			Date end = product.getDiscount().getDateEnd();
			if (start.compareTo(new Date()) <= 0 && end.compareTo(new Date()) >= 0) {

				Discount discount = product.getDiscount();
				if (discount.getDiscountType().equals("%")) {
					TOTAL = product.getProductPrice() - (product.getProductPrice() * discount.getDiscountValue() / 100);

				} else {
					TOTAL = product.getProductPrice() - discount.getDiscountValue();
				}

			} else {

				TOTAL = product.getProductPrice();
			}
		} else {
			TOTAL = product.getProductPrice();
		}

		return TOTAL;
	}

	public static boolean isNumeric(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
