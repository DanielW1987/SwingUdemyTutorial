package com.wagner.demo_app.ui;

import com.wagner.demo_app.event.FormEvent;
import com.wagner.demo_app.listener.FormListener;
import com.wagner.demo_app.model.AgeCategory;
import com.wagner.demo_app.model.EmploymentType;
import com.wagner.demo_app.model.Gender;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class FormPanel extends JPanel {

  private JLabel                    nameLabel;
  private JLabel                    occupationLabel;
  private JTextField                nameField;
  private JTextField                occupationField;
  private JButton                   okButton;
  private FormListener              formListener;
  private JList<AgeCategory>        ageCategories;
  private JComboBox<EmploymentType> employmentTypeComboBox;
  private JCheckBox                 usCitizenCheckBox;
  private JTextField                taxField;
  private JLabel                    taxFieldLabel;
  private JRadioButton              maleRadioButton;
  private JRadioButton              femaleRadioButton;
  private ButtonGroup               genderButtonGroup;

  FormPanel(){
    super(new GridBagLayout());

    // set size
    Dimension dimension = this.getPreferredSize();
    dimension.width = 275;
    this.setPreferredSize(dimension);

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
    this.nameLabel              = new JLabel("Name:");
    this.occupationLabel        = new JLabel("Occupation:");
    this.nameField              = new JTextField(10);
    this.occupationField        = new JTextField(10);
    this.okButton               = new JButton("OK");
    this.ageCategories          = new JList<>(new AgeCategoriesModel());
    this.employmentTypeComboBox = new JComboBox<>();
    this.usCitizenCheckBox      = new JCheckBox("US Citizen");
    this.taxField               = new JTextField(10);
    this.taxFieldLabel          = new JLabel("Tax ID:");
    this.femaleRadioButton      = new JRadioButton("female");
    this.maleRadioButton        = new JRadioButton("male");
    this.genderButtonGroup      = new ButtonGroup();

    // set labels for
    this.nameLabel.setLabelFor(nameField);
    this.nameLabel.setDisplayedMnemonic('N');
    this.occupationLabel.setLabelFor(occupationField);
    this.taxFieldLabel.setLabelFor(taxFieldLabel);

    // configure list 'ageCategories'
    this.ageCategories.setPreferredSize(new Dimension(110, 60));
    this.ageCategories.setBorder(BorderFactory.createEtchedBorder());
    this.ageCategories.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    this.ageCategories.setSelectedIndex(0);

    // configure combobox 'employmentTypeComboBox'
    this.employmentTypeComboBox.setModel(new EmploymentTypeComboBoxModel());

    // configure button 'okButton'
    this.okButton.addActionListener(this::onOKButtonClicked);
    this.okButton.setMnemonic(KeyEvent.VK_O);

    // configure checkbox 'usCitizenCheckBox'
    this.taxField.setEnabled(false);
    this.taxFieldLabel.setEnabled(false);
    this.usCitizenCheckBox.addActionListener(e -> {
      JCheckBox checkBox = (JCheckBox) e.getSource();
      this.taxField.setEnabled(checkBox.isSelected());
      this.taxFieldLabel.setEnabled(checkBox.isSelected());
    });

    // configure radion buttons for 'genderButtonGroup'
    this.maleRadioButton.setActionCommand(Gender.MALE.name());
    this.femaleRadioButton.setActionCommand(Gender.FEMALE.name());
    this.maleRadioButton.setSelected(true);
    this.genderButtonGroup.add(maleRadioButton);
    this.genderButtonGroup.add(femaleRadioButton);
  }

  private void onOKButtonClicked(ActionEvent e){
    String name           = this.nameField.getText();
    String occupation     = this.occupationField.getText();

    AgeCategory ageCategory = AgeCategory.NOT_DEFINED;
    if(this.ageCategories.getSelectedValue() != null){
      ageCategory = this.ageCategories.getSelectedValue();
    }

    EmploymentType employmentType = EmploymentType.NOT_DEFINED;
    if(this.employmentTypeComboBox.getSelectedItem() != null){
      employmentType = (EmploymentType) this.employmentTypeComboBox.getSelectedItem();
    }

    boolean usCitizen     = this.usCitizenCheckBox.isSelected();
    String taxID          = this.taxField.getText();
    Gender gender         = Gender.valueOf(this.genderButtonGroup.getSelection().getActionCommand());

    FormEvent event = new FormEvent(this, name, occupation, ageCategory, employmentType, usCitizen, taxID, gender);

    if(formListener != null){
      formListener.formEventOccured(event);
    }
  }

  private void addComponents(){
    GridBagConstraints gbConstraints = new GridBagConstraints();

    gbConstraints.anchor  = GridBagConstraints.FIRST_LINE_START;
    gbConstraints.insets  = new Insets(5, 5, 5, 5);

    // name
    gbConstraints.gridx   = 0;
    gbConstraints.gridy   = 0;
    this.add(this.nameLabel, gbConstraints);

    gbConstraints.gridx   = 1;
    gbConstraints.gridy   = 0;
    this.add(this.nameField, gbConstraints);

    // occupation
    gbConstraints.gridx   = 0;
    gbConstraints.gridy   = 1;
    this.add(this.occupationLabel, gbConstraints);

    gbConstraints.gridx   = 1;
    gbConstraints.gridy   = 1;
    this.add(this.occupationField, gbConstraints);

    // age category
    gbConstraints.gridx   = 0;
    gbConstraints.gridy   = 2;
    this.add(new JLabel("Age category:"), gbConstraints);

    gbConstraints.gridx   = 1;
    gbConstraints.gridy   = 2;
    this.add(this.ageCategories, gbConstraints);

    // employment type
    gbConstraints.gridx   = 0;
    gbConstraints.gridy   = 3;
    this.add(new JLabel("Employment type:"), gbConstraints);

    gbConstraints.gridx   = 1;
    gbConstraints.gridy   = 3;
    this.add(this.employmentTypeComboBox, gbConstraints);

    // us citizen
    gbConstraints.gridx   = 1;
    gbConstraints.gridy   = 4;
    this.add(this.usCitizenCheckBox, gbConstraints);

    // tax
    gbConstraints.gridx   = 0;
    gbConstraints.gridy   = 5;
    this.add(this.taxFieldLabel, gbConstraints);

    gbConstraints.gridx   = 1;
    gbConstraints.gridy   = 5;
    this.add(this.taxField, gbConstraints);

    // gender
    gbConstraints.gridx   = 0;
    gbConstraints.gridy   = 6;
    this.add(new JLabel("Gender:"), gbConstraints);

    GridBagConstraints rbConstraint = new GridBagConstraints();
    rbConstraint.gridx   = 1;
    rbConstraint.gridy   = 6;
    rbConstraint.anchor  = GridBagConstraints.FIRST_LINE_START;
    this.add(this.maleRadioButton, rbConstraint);

    // row 8
    rbConstraint.gridx   = 1;
    rbConstraint.gridy   = 7;
    this.add(this.femaleRadioButton, rbConstraint);

    // row 9
    gbConstraints.gridx   = 1;
    gbConstraints.gridy   = 8;
    gbConstraints.weighty = 1;
    gbConstraints.anchor  = GridBagConstraints.LAST_LINE_END;
    this.add(this.okButton, gbConstraints);
  }
}

class AgeCategoriesModel extends DefaultListModel<AgeCategory>{

  AgeCategoriesModel(){
    this.addElement(AgeCategory.CHILD);
    this.addElement(AgeCategory.ADULT);
    this.addElement(AgeCategory.SENIOR);
  }

}

class EmploymentTypeComboBoxModel extends DefaultComboBoxModel<EmploymentType>{

  EmploymentTypeComboBoxModel(){
    this.addElement(EmploymentType.EMPLOYED);
    this.addElement(EmploymentType.SELF_EMPLOYED);
    this.addElement(EmploymentType.UNEMPLOYED);
  }
}