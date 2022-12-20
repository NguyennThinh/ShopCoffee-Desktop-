 package com.coffee.form;

import java.awt.BorderLayout;
import java.util.Date;

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
	 * Create the frame.
	 */
	public Form_Main(Date date, Employee employee) {

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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

		initComponent(date, employee);

	}

	/**
	 * 
	 */
	
	private void initComponent(Date date, Employee employee) {
		
		// TODO Auto-generated method stub
		
		menu = new Menu(employee);
		menu.initMoving(Form_Main.this);
		menu.addEventMenuSelected(new EventMenuSelected() {
			@Override
			public void selected(int index) {
			
					if (index == 0) {

						setForm(new Form_Home(date));
					} else if (index == 1) {

						setForm(new Form_Sell(employee));
					} else if (index == 2) {
						setForm(new Form_Product());
			
					} else if (index == 3) {
						setForm(new Form_Discount());
					} else if (index == 4) {
						setForm(new Form_Customer());
					} else if (index == 5) {
						setForm(new Form_Bill(employee));
					} else if (index == 6) {
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
		

			panel = new Header(employee);
		
		GroupLayout gl_pHome = new GroupLayout(pHome);
		gl_pHome.setHorizontalGroup(
			gl_pHome.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pHome.createSequentialGroup()
					.addComponent(menu, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pHome.createParallelGroup(Alignment.LEADING)
						.addComponent(pForm, GroupLayout.DEFAULT_SIZE, 1143, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 1143, Short.MAX_VALUE)))
		);
		gl_pHome.setVerticalGroup(
			gl_pHome.createParallelGroup(Alignment.LEADING)
				.addComponent(menu, GroupLayout.PREFERRED_SIZE, 772, Short.MAX_VALUE)
				.addGroup(gl_pHome.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(pForm, GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE))
		);
		pHome.setLayout(gl_pHome);

		setForm(new Form_Home(new Date()));
		
		  this.addWindowListener(new java.awt.event.WindowAdapter() {
			    @Override
			    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
			    Form_Main ma = new Form_Main(new Date(), employee);

			    Object[] options = {"Yes", "NO"};

			    int selectedOption = JOptionPane.showOptionDialog(ma, "Bạn muốn thoát khỏi chương trình?", "Warning",
			    JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
			    null, options, options[0]);

			           if (selectedOption == JOptionPane.YES_OPTION) {
			               System.exit(0);
			           }

			    }
			    }); 
	}

	private void setForm(JComponent com) {
		pForm.removeAll();
		pForm.add(com);
		pForm.repaint();
		pForm.revalidate();
	}

	
	
}
