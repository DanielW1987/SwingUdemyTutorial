package com.wagner.ui;

import com.wagner.listener.StringListener;

import javax.swing.*;
import java.awt.*;

class TextPanel extends JPanel implements StringListener {

    private JTextArea textArea;

    TextPanel(){
        super(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(13,0,5,0));
        this.textArea = new JTextArea();
        this.textArea.setRows(5);
        this.add(new JScrollPane(textArea), BorderLayout.CENTER);
    }

    void append(String text){
        textArea.append(text);
    }

    @Override
    public void textEmitted(String text) {
        this.textArea.append(text);
    }
}