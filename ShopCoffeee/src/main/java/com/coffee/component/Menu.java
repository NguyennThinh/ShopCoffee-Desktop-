package com.coffee.component;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.coffee.custom.ListMenu;
import com.coffee.entity.Employee;
import com.coffee.event.EventMenuSelected;
import com.coffee.model.Model_Menu;
import javax.swing.LayoutStyle.ComponentPlacement;


public class Menu extends javax.swing.JPanel {

    private static final long serialVersionUID = 7109643283047387784L;
	@SuppressWarnings("unused")
	private EventMenuSelected event;

    public void addEventMenuSelected(EventMenuSelected event) {
        this.event = event;
        listMenu1.addEventMenuSelected(event);
    }

    public Menu(Employee employee) {
        initComponents();
        setOpaque(false);
        listMenu1.setOpaque(false);
        init(employee);
    }

    private void init(Employee employee) {
        
        listMenu1.addItem(new Model_Menu("dashboard", "Dashboard", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("sell", "Bán hàng", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("product", "Sản phẩm", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("discount", "Khuyến mãi", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("customer", "Khách hàng", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("bill", "Hóa đơn ", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("logout", "Đăng xuất", Model_Menu.MenuType.MENU));
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMoving = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        listMenu1 = new ListMenu<>();

        panelMoving.setOpaque(false);
        
        
        ImageIcon icon = new ImageIcon("image/logo-coffee.png");
        Image scaleImage = icon.getImage().getScaledInstance(80, 80,Image.SCALE_DEFAULT);
        jLabel1.setIcon(new ImageIcon(scaleImage)); // NOI18N
        
        jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        
        jLabel1.setText("Coffee Thùng");
        


        javax.swing.GroupLayout panelMovingLayout = new javax.swing.GroupLayout(panelMoving);
        panelMovingLayout.setHorizontalGroup(
        	panelMovingLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(panelMovingLayout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
        			.addContainerGap())
        );
        panelMovingLayout.setVerticalGroup(
        	panelMovingLayout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(Alignment.LEADING, panelMovingLayout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        			.addGap(29))
        );
        panelMoving.setLayout(panelMovingLayout);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addComponent(panelMoving, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
        		.addComponent(listMenu1, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(layout.createSequentialGroup()
        			.addComponent(panelMoving, GroupLayout.PREFERRED_SIZE, 51, Short.MAX_VALUE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(listMenu1, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE))
        );
        this.setLayout(layout);
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintChildren(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint g = new GradientPaint(0, 0, Color.decode("#e74c3c"), 0, getHeight(), Color.decode("#000000"));
  
        g2.setPaint(g);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        g2.fillRect(getWidth() - 20, 0, getWidth(), getHeight());
        super.paintChildren(grphcs);
    }

    private int x;
    private int y;

    public void initMoving(JFrame fram) {
        panelMoving.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                x = me.getX();
                y = me.getY();
            }

        });
        panelMoving.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent me) {
                fram.setLocation(me.getXOnScreen() - x, me.getYOnScreen() - y);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private ListMenu<String> listMenu1;
    private javax.swing.JPanel panelMoving;
}
