package com.wagner.ui;

import com.wagner.listener.StringListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class Toolbar extends JPanel implements ActionListener {

    private JButton              exportButton;
    private JButton              importButton;
    private List<StringListener> stringListeners;

    Toolbar(){
        super(new FlowLayout(FlowLayout.LEFT));
        this.stringListeners = new ArrayList<>();
        this.exportButton    = new JButton("Export");
        this.importButton    = new JButton("Import");

        this.exportButton.addActionListener(this);
        this.importButton.addActionListener(this);

        this.add(exportButton);
        this.add(importButton);
    }

    void registerStringListener(StringListener listener){
       this.stringListeners.add(listener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();
        for(StringListener listener : stringListeners){
            if(buttonClicked == exportButton){
               listener.textEmitted("Save" + System.lineSeparator());
            }
            else if(buttonClicked == importButton){
                listener.textEmitted("Open" + System.lineSeparator());
            }
            else{
                throw new IllegalStateException("snh: unknown button has been clicked!");
            }
        }
    }
}