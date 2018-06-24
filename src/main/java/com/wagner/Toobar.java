package com.wagner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class Toolbar extends JPanel implements ActionListener {

    private JButton saveButton;
    private JButton openButton;
    private List<StringListener> stringListeners;

    Toolbar(){
        super(new FlowLayout(FlowLayout.LEFT));
        this.stringListeners = new ArrayList<>();
        this.saveButton      = new JButton("Save");
        this.openButton      = new JButton("Open");

        this.saveButton.addActionListener(this);
        this.openButton.addActionListener(this);

        this.add(saveButton);
        this.add(openButton);
    }

    void registerStringListener(StringListener listener){
       this.stringListeners.add(listener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();
        for(StringListener listener : stringListeners){
            if(buttonClicked == saveButton){
               listener.textEmitted("Save" + System.lineSeparator());
            }
            else if(buttonClicked == openButton){
                listener.textEmitted("Open" + System.lineSeparator());
            }
            else{
                throw new IllegalStateException("snh: unknown button has been clicked!");
            }
        }
    }
}