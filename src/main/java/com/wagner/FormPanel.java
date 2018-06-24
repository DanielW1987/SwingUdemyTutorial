package com.wagner;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class FormPanel extends JPanel {

    private JLabel nameLabel;
    private JLabel occupationLabel;
    private JTextField nameField;
    private JTextField occupationField;
    private JButton okButton;
    private FormListener formListener;

    FormPanel(){
        super(new GridBagLayout());

        // set size
        Dimension dimension = this.getPreferredSize();
        dimension.width = 250;
        this.setPreferredSize(dimension);

        // set border
        // this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        // this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        // this.setBorder(BorderFactory.createCompoundBorder());
        // this.setBorder(BorderFactory.createDashedBorder(Color.BLACK));
        // this.setBorder(BorderFactory.createEmptyBorder());
        //this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        //this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        //this.setBorder(BorderFactory.createLineBorder(Color.CYAN, 5));
        //this.setBorder(BorderFactory.createMatteBorder(5,5,5,5, Color.DARK_GRAY));
        //this.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(1), Color.BLACK));

        Border innerBorder = BorderFactory.createTitledBorder("Add Person");
        Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
        this.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        // init and add components
        initComponents();
        addComponents();

    }

    void setFormListener(FormListener formListener) {
        this.formListener = formListener;
    }

    private void initComponents(){
        this.nameLabel       = new JLabel("Name:");
        this.occupationLabel = new JLabel("Occupation:");
        this.nameField       = new JTextField(10);
        this.occupationField = new JTextField(10);
        this.okButton = new JButton("OK");

        this.okButton.addActionListener(e -> {
            String name       = this.nameField.getText();
            String occupation = this.occupationField.getText();
            FormEvent event   = new FormEvent(this, name, occupation);

            if(formListener != null){
                formListener.formEventOccured(event);
            }
        });
    }

    private void addComponents(){

        GridBagConstraints gbConstraints = new GridBagConstraints();
        gbConstraints.weightx = 1;
        gbConstraints.fill    = GridBagConstraints.NONE;
        gbConstraints.anchor  = GridBagConstraints.LINE_START;

        // First row
        gbConstraints.weighty = 0.1;
        gbConstraints.gridx   = 0;
        gbConstraints.gridy   = 0;
        gbConstraints.insets  = new Insets(0,10,0,0);
        this.add(this.nameLabel, gbConstraints);

        gbConstraints.weighty = 0.1;
        gbConstraints.gridx   = 1;
        gbConstraints.gridy   = 0;
        gbConstraints.insets  = new Insets(0,0,0,0);
        this.add(this.nameField, gbConstraints);

        // Second row
        gbConstraints.weighty = 0.1;
        gbConstraints.gridx   = 0;
        gbConstraints.gridy   = 1;
        gbConstraints.insets  = new Insets(0,10,0,0);
        this.add(this.occupationLabel, gbConstraints);

        gbConstraints.weighty = 0.1;
        gbConstraints.gridx   = 1;
        gbConstraints.gridy   = 1;
        gbConstraints.insets  = new Insets(0,0,0,0);
        this.add(this.occupationField, gbConstraints);

        // Third row
        gbConstraints.weighty = 2;
        gbConstraints.gridx   = 1;
        gbConstraints.gridy   = 2;
        gbConstraints.insets  = new Insets(0,0,0,0);
        gbConstraints.anchor  = GridBagConstraints.FIRST_LINE_START;
        this.add(this.okButton, gbConstraints);

    }
}