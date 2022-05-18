package apsoo.view.extensions;

import javax.swing.JPasswordField;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class JPasswordFieldPlaceholder extends JPasswordField {
    private String placeholder;

    public JPasswordFieldPlaceholder(String placeholder){
        this.placeholder = placeholder;

        FocusListener focusListener = new FocusListener(){
            @Override
            public void focusGained(FocusEvent event){
                JPasswordFieldPlaceholder passwordField = (JPasswordFieldPlaceholder)event.getComponent();
                if(String.valueOf(passwordField.getPassword()).equals(placeholder) && passwordField.getForeground() == Color.GRAY){
                    passwordField.setText("");
                    passwordField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent event){
                JPasswordFieldPlaceholder passwordField = (JPasswordFieldPlaceholder)event.getComponent();
                if(String.valueOf(passwordField.getPassword()).isBlank()){
                    passwordField.setText(placeholder);
                    passwordField.setForeground(Color.GRAY);
                }
            }
        };

        setText(placeholder);
        setForeground(Color.GRAY);
        addFocusListener(focusListener);
    }

    public String getPlaceholder(){
        return this.placeholder;
    }
}