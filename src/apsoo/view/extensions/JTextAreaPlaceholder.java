package apsoo.view.extensions;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextArea;

public class JTextAreaPlaceholder extends JTextArea {
    private String placeholder;

    public JTextAreaPlaceholder(String placeholder){
        this.placeholder = placeholder;

        FocusListener focusListener = new FocusListener(){
            @Override
            public void focusGained(FocusEvent event){
                JTextArea textField = (JTextArea)event.getComponent();
                if(isPlaceholder()){
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent event){
                JTextArea textField = (JTextArea)event.getComponent();
                if(textField.getText().equals("")){
                    textField.setText(placeholder);
                    textField.setForeground(Color.GRAY);
                }
            }
        };

        setText(placeholder);
        setForeground(Color.GRAY);
        addFocusListener(focusListener);
    }

    public boolean isPlaceholder(){
        String text = super.getText();
        Color foregroundColor = getForeground();
        return text.equals(placeholder) && foregroundColor == Color.GRAY;
    }

    public void resetToPlaceholder(){
        setText(placeholder);
        setForeground(Color.GRAY);
    }

    public String getPlaceholder(){
        return this.placeholder;
    }

    @Override
    public String getText() {
        if(isPlaceholder()) return "";
        return super.getText();
    }

    @Override
    public void setText(String text) {
        if(isPlaceholder()) setForeground(Color.BLACK);
        super.setText(text);
    }
}