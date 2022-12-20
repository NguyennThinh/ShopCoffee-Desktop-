package com.coffee.component;

import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import com.coffee.custom.Button;
import com.coffee.custom.ButtonBadges;
import com.coffee.custom.ImageAvatar;
import com.coffee.custom.PanelTransparent;
import com.coffee.entity.Employee;

public class Header extends PanelTransparent {

    private static final long serialVersionUID = 8076900329356859032L;
	public Header(Employee emp) {
        initComponents(emp);
        setTransparent(0.5f);
    }

    public void addMenuEvent(ActionListener event) {
        cmdMenu.addActionListener(event);
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents(Employee emp) {

        cmdMenu = new Button();
        pic = new ImageAvatar();
        lbUserName = new javax.swing.JLabel();
        lbRole = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        buttonBadges1 = new ButtonBadges();
        buttonBadges2 = new ButtonBadges();

        cmdMenu.setIcon(new javax.swing.ImageIcon("image/menu.png")); // NOI18N


		if (emp == null) {
	
			ImageIcon img = new ImageIcon("image/employees.png");
			Image newImage = img.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
			pic.setIcon(new ImageIcon(newImage));
		} else {

			if (emp.getImage() == null) {
				ImageIcon img = new ImageIcon("image/employees.png");
				Image newImage = img.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
				pic.setIcon(new ImageIcon(newImage));
			} else {
				pic.setIcon(new ImageIcon(emp.getImage()));
				lbUserName.setText(emp.getFullName());
				lbRole.setText(emp.getPosition().getPositionName());
			}
		}
	 // NOI18N

        lbUserName.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        lbUserName.setForeground(new java.awt.Color(127, 127, 127));


        lbRole.setForeground(new java.awt.Color(127, 127, 127));


        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        buttonBadges1.setForeground(new java.awt.Color(250, 49, 49));
        buttonBadges1.setIcon(new javax.swing.ImageIcon("image/notification.png")); // NOI18N
        buttonBadges1.setBadges(12);

        buttonBadges2.setForeground(new java.awt.Color(63, 178, 232));
        buttonBadges2.setIcon(new javax.swing.ImageIcon("image/message.png")); // NOI18N
        buttonBadges2.setBadges(5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmdMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 362, Short.MAX_VALUE)
                .addComponent(buttonBadges2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(buttonBadges1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbUserName, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbRole, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addComponent(pic, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbUserName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbRole))
                    .addComponent(cmdMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addComponent(buttonBadges1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonBadges2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private ButtonBadges buttonBadges1;
    private ButtonBadges buttonBadges2;
    private Button cmdMenu;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbRole;
    private javax.swing.JLabel lbUserName;
    private ImageAvatar pic;
    // End of variables declaration//GEN-END:variables
}
