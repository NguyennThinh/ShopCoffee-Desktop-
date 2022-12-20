package com.coffee.custom;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollBar;

public class ScrollBar extends JScrollBar {

    private static final long serialVersionUID = -5745426565231688258L;

	public ScrollBar() {
        setUI(new ModernScrollBarUI());
        setPreferredSize(new Dimension(5, 5));
        setBackground(new Color(242, 242, 242));
        setUnitIncrement(20);
    }
}
