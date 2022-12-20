package com.coffee.form;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import com.coffee.component.Header;
import com.coffee.component.Menu;
import com.coffee.entity.Employee;
import com.coffee.event.EventMenuSelected;


/**
 * @author Nguyen Phuc Thinh
 */
public class Form_Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4068764243959538751L;
	private JPanel contentPane;
	private JPanel pHome;
	private Menu menu;
	private JPanel pForm;
	private Header panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Form_Main frame = new Form_Main(null);
					frame.setLocationRelativeTo(null);
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Form_Main(Employee employee) {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1401, 809);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		pHome = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(pHome,
				GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(pHome,
				GroupLayout.DEFAULT_SIZE, 772, Short.MAX_VALUE));
		contentPane.setLayout(gl_contentPane);

		initComponent(employee);

	}

	/**
	 * 
	 */
	
	private void initComponent(Employee employee) {
		// TODO Auto-generated method stub
		menu = new Menu();
		menu.initMoving(Form_Main.this);
		menu.addEventMenuSelected(new EventMenuSelected() {
			@Override
			public void selected(int index) {
				if (index == 1) {

					setForm(new Form_Home());
				} else if (index == 2) {

					setForm(new Form_Analysis());
				} else if (index == 3) {
					setForm(new Form_Chart());
		
				} else if (index == 5) {
					setForm(new Form_Product());
				} else if (index == 6) {
					setForm(new Form_Discount());
				} else if (index == 7) {
					setForm(new Form_Customer());
				}else if(index == 8) {
					setForm(new Form_Table());
				}else if (index == 10) {
					setForm(new Form_Employee());
				} else if (index == 11) {
					setForm(new Form_Account());
				} else if (index == 12) {
					  int result = JOptionPane.showConfirmDialog(null,"Bạn muốn đăng xuất", "Thoát",
				               JOptionPane.YES_NO_OPTION,
				               JOptionPane.QUESTION_MESSAGE);
				            if(result == JOptionPane.YES_OPTION){
				            	dispose();
				            	new Form_Login().setVisible(true);
				            	
				            }
				}
			}
		});

		pForm = new JPanel();
		pForm.setLayout(new BorderLayout(0, 0));
		
		if(employee != null) {
			
			if(employee.getImage() != null) {
				panel = new Header(employee);
			}else {
				panel = new Header(null);
			}
	
		}else {
			panel = new Header(null);
		}
		GroupLayout gl_pHome = new GroupLayout(pHome);
		gl_pHome.setHorizontalGroup(
			gl_pHome.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pHome.createSequentialGroup()
					.addComponent(menu, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pHome.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 1143, Short.MAX_VALUE)
						.addComponent(pForm, GroupLayout.DEFAULT_SIZE, 1149, Short.MAX_VALUE)))
		);
		gl_pHome.setVerticalGroup(
			gl_pHome.createParallelGroup(Alignment.LEADING)
				.addComponent(menu, GroupLayout.PREFERRED_SIZE, 772, Short.MAX_VALUE)
				.addGroup(gl_pHome.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(pForm, GroupLayout.DEFAULT_SIZE, 701, Short.MAX_VALUE)
					.addContainerGap())
		);
		pHome.setLayout(gl_pHome);

		setForm(new Form_Home());
	}

	private void setForm(JComponent com) {
		pForm.removeAll();
		pForm.add(com);
		pForm.repaint();
		pForm.revalidate();
	}

}
