package com.wagner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

class MainFrame extends JFrame {

    private TextPanel textPanel;
    private JButton button;
    private Toolbar toolbar;
    private FormPanel formPanel;

    MainFrame(){
        super("Main Frame");
        initFrame();
        initComponents();
        addComponents();
    }

    private void initFrame(){
        this.setSize(600,500);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setVisible(true);
    }

    private void initComponents(){
        this.textPanel   = new TextPanel();
        this.button      = new JButton("Click me!");
        this.toolbar     = new Toolbar();
        this.formPanel   = new FormPanel();

        this.toolbar.registerStringListener(this.textPanel);
        this.formPanel.setFormListener(event -> {
           String text = event.getName() + " - " + event.getOccupation() + System.lineSeparator();
           this.textPanel.append(text);
        });
        button.addActionListener(this::buttonAction);
    }

    private void addComponents(){
        this.add(toolbar, BorderLayout.NORTH);
        this.add(textPanel, BorderLayout.CENTER);
        this.add(button, BorderLayout.SOUTH);
        this.add(formPanel, BorderLayout.WEST);
    }

    private void buttonAction(ActionEvent e) {
        this.textPanel.append(e.paramString() + System.lineSeparator());
    }
}