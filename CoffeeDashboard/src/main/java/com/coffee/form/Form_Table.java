package com.coffee.form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.coffee.custom.PanelBorder;
import com.coffee.custom.WrapLayout;
import com.coffee.entity.Area;
import com.coffee.entity.CoffeeTable;
import com.coffee.service.TableService;
import com.coffee.serviceimpl.TableServiceImpl;

/**
 * @author Nguyen Phuc Thinh
 */
public class Form_Table extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TableService tableService;
	private List<Area> arrAreas;
	private JPanel pForm;

	private JPanel pArea;
	private JLabel lblNewLabel;
	private JComboBox<Object> cbmArea;
	private JPanel[] pItemArea;
	private JPanel pAddArea;
	private JLabel lblAddArea;
	private PanelBorder[] pTableItem;
	private JPanel pTableAdd;
	private JLabel lblAddTable;
	private JScrollPane scrollPane;

	/**
	 * Create the panel.
	 */
	public Form_Table() {
		setLayout(new GridLayout(1, 0, 0, 0));

		pForm = new JPanel();
		pForm.setBackground(Color.WHITE);
		add(pForm);

		scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.white);
		scrollPane.setBorder(new EmptyBorder(10, 10, 10, 2));
		pArea = new JPanel();

		pArea.setBackground(Color.WHITE);
		//pArea.setLayout(new GridLayout(0, 2, 10, 10));
		pArea.setLayout(new WrapLayout(FlowLayout.LEADING, 10, 10));
		
		scrollPane.setViewportView(pArea);

		lblNewLabel = new JLabel("Khu vực");
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel.setForeground(new Color(192, 192, 192));

		cbmArea = new JComboBox<Object>();
		GroupLayout gl_pForm = new GroupLayout(pForm);
		gl_pForm.setHorizontalGroup(gl_pForm.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pForm.createSequentialGroup().addGap(10)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
						.addGap(10).addComponent(cbmArea, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(711, Short.MAX_VALUE))
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE));
		gl_pForm.setVerticalGroup(gl_pForm.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pForm.createSequentialGroup().addGap(15)
						.addGroup(gl_pForm.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(cbmArea, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addGap(25).addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)));
		pForm.setLayout(gl_pForm);

		listArea();

	}

	private void listArea() {
		pArea.removeAll();
		tableService = new TableServiceImpl();
		arrAreas = tableService.listArea();

		pItemArea = new JPanel[arrAreas.size()];

		for (int i = 0; i < arrAreas.size(); i++) {
			Area area = arrAreas.get(i);
			pItemArea[i] = new JPanel();
			pItemArea[i].setPreferredSize(new Dimension(600, 300));
			
			pItemArea[i].setLayout(new WrapLayout(FlowLayout.LEADING, 10, 10));
			pItemArea[i].setBorder(new TitledBorder(new LineBorder(Color.red), area.getName(), TitledBorder.LEADING,
					TitledBorder.TOP, null, null));
			pArea.add(pItemArea[i]);

			pItemArea[i].addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub

					JPanel click = (JPanel) e.getSource();
					if (SwingUtilities.isRightMouseButton(e)) {
						JPopupMenu menu = new JPopupMenu();
						JMenuItem updateMenu = new JMenuItem("Cập nhật khu vực");

						menu.add(updateMenu);

						updateMenu.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								String name = JOptionPane.showInputDialog(null, "Nhập vào tên khu vực mới:");
								if(name != null && !name.isEmpty()) {
									area.setName(name);
									if (tableService.updateArea(area)) {
										JOptionPane.showMessageDialog(null, "Cập nhật khu vực thành công");
										listArea();
									} else {
										JOptionPane.showMessageDialog(null, "Đã có lỗi xảy ra", "Cập nhật khu vực",
												JOptionPane.WARNING_MESSAGE);
									}
								}
								
							}
						});
						
						JMenuItem deleteMenu = new JMenuItem("Xóa khu vực");

						menu.add(deleteMenu);
						deleteMenu.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								
								  int result = JOptionPane.showConfirmDialog(null,"Bạn muốn xóa "+area.getName()+"\n(Nếu xóa bàn sẽ mất hết các bàn và hóa đơn liên quan đến bàn đó)", "Xóa bàn",
							               JOptionPane.YES_NO_OPTION,
							               JOptionPane.QUESTION_MESSAGE);
							            if(result == JOptionPane.YES_OPTION){
							              if(tableService.deleteArea(area)) {
							  				JOptionPane.showMessageDialog(null, "Xóa khu vực thành công");
							  				click.setVisible(false);;
							              }else {
							    				JOptionPane.showMessageDialog(null, "Đã có lỗi xảy ra");
							              }
							            }
							}
						});
						menu.show(click, e.getX(), e.getY());
					}
				}
			});

			/****************************/
			pTableItem = new PanelBorder[arrAreas.get(i).getTable().size()];
			JLabel[] lblTableName = new JLabel[arrAreas.get(i).getTable().size()];
			for (int j = 0; j < arrAreas.get(i).getTable().size(); j++) {
				CoffeeTable table = arrAreas.get(i).getTable().get(j);
				JLabel lblImage = new JLabel();
				lblTableName[j] = new JLabel();
				pTableItem[j] = new PanelBorder();

				pTableItem[j].setLayout(new BorderLayout(0, 0));
				pTableItem[j].setBackground(Color.white);
				pTableItem[j].setPreferredSize(new Dimension(80, 80));
				pTableItem[j].setBorder(new LineBorder(Color.BLUE));

				ImageIcon img = new ImageIcon("image/round-table.png");
				Image newImage = img.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
				lblImage.setIcon(new ImageIcon(newImage));
				lblImage.setHorizontalAlignment(SwingConstants.CENTER);

				lblTableName[j].setText(arrAreas.get(i).getTable().get(j).getName());
				lblTableName[j].setFont(new Font("SansSerif", Font.BOLD, 12));
				lblTableName[j].setHorizontalAlignment(SwingConstants.CENTER);
				lblTableName[j].setBorder(new EmptyBorder(0, 0, 5, 0));

				pTableItem[j].add(lblImage, BorderLayout.CENTER);
				pTableItem[j].add(lblTableName[j], BorderLayout.SOUTH);
				pItemArea[i].add(pTableItem[j]);
				int n =j;
				pTableItem[j].addMouseListener(new MouseAdapter() {

					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						PanelBorder click = (PanelBorder) e.getSource();
						if (SwingUtilities.isRightMouseButton(e)) {
							JPopupMenu menu = new JPopupMenu();
							JMenuItem updateMenu = new JMenuItem("Cập nhật bàn");
			
							menu.add(updateMenu);

							updateMenu.addActionListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent e) {
									// TODO Auto-generated method stub
									String name = JOptionPane.showInputDialog(null, "Nhập vào tên bàn mới:");
									
									if(name != null && !name.isEmpty()) {
										table.setName(name);
										if (tableService.updateTable(table)) {
											JOptionPane.showMessageDialog(null, "Cập nhật bàn thành công");
										//	listArea();
											lblTableName[n].setText(name);
										} else {
											JOptionPane.showMessageDialog(null, "Đã có lỗi xảy ra", "Cập nhật bàn",
													JOptionPane.WARNING_MESSAGE);
										}
									}
							
								}
							});
							
							
							JMenuItem deleteMenu = new JMenuItem("Xóa bàn");

							menu.add(deleteMenu);
							deleteMenu.addActionListener(new ActionListener() {
								
								@Override
								public void actionPerformed(ActionEvent e) {
									// TODO Auto-generated method stub
									
									  int result = JOptionPane.showConfirmDialog(null,"Bạn muốn xóa "+table.getName()+"\n(Nếu xóa bàn sẽ mất hết các hóa đơn liên quan đến bàn đó)", "Xóa bàn",
								               JOptionPane.YES_NO_OPTION,
								               JOptionPane.QUESTION_MESSAGE);
								            if(result == JOptionPane.YES_OPTION){
								              if(tableService.deleteTable(table)) {
								  				JOptionPane.showMessageDialog(null, "Xóa bàn thành công");
								  				click.setVisible(false);;
								              }else {
								    				JOptionPane.showMessageDialog(null, "Đã có lỗi xảy ra");
								              }
								            }
								}
							});
							
							
							menu.show(click, e.getX(), e.getY());
						}

					}

				});

			}
			pTableAdd = new JPanel();
			pTableAdd.setPreferredSize(new Dimension(80, 80));

			pItemArea[i].add(pTableAdd);
			pTableAdd.setLayout(new BorderLayout(0, 0));

			pTableAdd.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					if (SwingUtilities.isLeftMouseButton(e)) {
						String name = JOptionPane.showInputDialog(null, "Nhập vào tên bàn");

						if(name != null && !name.isEmpty()) {
							CoffeeTable c = new CoffeeTable();
							c.setArea(area);
							c.setName(name);

							area.getTable().add(c);

							if (tableService.addTable(area)) {
								JOptionPane.showMessageDialog(null, "Thêm bàn thành công");
								listArea();
							} else {
								JOptionPane.showMessageDialog(null, "Đã có lỗi xảy ra", "Thêm bàn",
										JOptionPane.WARNING_MESSAGE);
							}
						}
					}

				}
			});

			lblAddTable = new JLabel("");
			ImageIcon img = new ImageIcon("image/table-image.png");
			Image newImage = img.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
			lblAddTable.setIcon(new ImageIcon(newImage));
			lblAddTable.setHorizontalAlignment(SwingConstants.CENTER);
			pTableAdd.add(lblAddTable, BorderLayout.CENTER);
		}

		pAddArea = new JPanel();
		pArea.add(pAddArea);
		pAddArea.setLayout(new BorderLayout(0, 0));

		lblAddArea = new JLabel("");
		lblAddArea.setIcon(new ImageIcon("D:\\Dev\\LTPT\\CoffeeDashboard\\image\\table-image.png"));
		lblAddArea.setHorizontalAlignment(SwingConstants.CENTER);
		pAddArea.add(lblAddArea);

		pAddArea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if (SwingUtilities.isLeftMouseButton(e)) {
					String name = JOptionPane.showInputDialog(null, "Nhập vào tên khu vực");
					if(name != null && !name.isEmpty()) {
						Area a = new Area();
						a.setName(name);
						if (tableService.addArea(a)) {
							JOptionPane.showMessageDialog(null, "Thêm khu vực thành công");
							listArea();
						} else {
							JOptionPane.showMessageDialog(null, "Đã có lỗi xảy ra", "Thêm khu vực",
									JOptionPane.WARNING_MESSAGE);
						}
					}
				
				}
			}
		});
		// listTable();
		pArea.updateUI();
	}
}
