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
import com.coffee.event.EventMenuSelected;
import com.coffee.model.Model_Menu;


public class Menu extends javax.swing.JPanel {

    private static final long serialVersionUID = 7109643283047387784L;
	@SuppressWarnings("unused")
	private EventMenuSelected event;

    public void addEventMenuSelected(EventMenuSelected event) {
        this.event = event;
        listMenu1.addEventMenuSelected(event);
    }

    public Menu() {
        initComponents();
        setOpaque(false);
        listMenu1.setOpaque(false);
        init();
    }

    private void init() {
        listMenu1.addItem(new Model_Menu("", "Menu", Model_Menu.MenuType.TITLE));
        listMenu1.addItem(new Model_Menu("dashboard", "Dashboard", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("analysis", "Thống kê", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("chart", "Biểu đồ", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("", "Quản lý", Model_Menu.MenuType.TITLE));
        listMenu1.addItem(new Model_Menu("product", "Sản phẩm", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("discount", "Khuyến mãi", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("customer", "Khách hàng", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("table_image", "Vị trí ngồi", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("", "Người dùng", Model_Menu.MenuType.TITLE));
      
        listMenu1.addItem(new Model_Menu("employee", "Nhân viên", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("account", "Tài khoản", Model_Menu.MenuType.MENU));
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
        			.addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
        			.addContainerGap())
        );
        panelMovingLayout.setVerticalGroup(
        	panelMovingLayout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(panelMovingLayout.createSequentialGroup()
        			.addContainerGap(143, Short.MAX_VALUE)
        			.addComponent(jLabel1)
        			.addContainerGap())
        );
        panelMoving.setLayout(panelMovingLayout);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addComponent(listMenu1, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
        		.addComponent(panelMoving, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(Alignment.TRAILING, layout.createSequentialGroup()
        			.addComponent(panelMoving, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
        			.addGap(6)
        			.addComponent(listMenu1, GroupLayout.DEFAULT_SIZE, 725, Short.MAX_VALUE))
        );
        this.setLayout(layout);
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintChildren(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint g = new GradientPaint(0, 0, Color.decode("#1CB5E0"), 0, getHeight(), Color.decode("#000046"));
  
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
