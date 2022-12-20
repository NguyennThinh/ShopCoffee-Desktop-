package com.coffee.custom;

import javax.swing.JComboBox;

public class ComboBoxSuggestion<E> extends JComboBox<E> {

    private static final long serialVersionUID = 1509974200702682169L;

	public ComboBoxSuggestion() {
        setUI(new ComboSuggestionUI());
    }
}
