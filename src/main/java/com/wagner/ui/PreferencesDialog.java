package com.wagner.ui;

import javax.swing.*;
import java.awt.*;

public class PreferencesDialog extends JDialog {
  ////////////////////// region Variables  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  private JButton            okButton;
  private JButton            cancelButton;
  private JSpinner           portSpinner;
  private SpinnerNumberModel spinnerModel;
  // endregion
  ////////////////////// region Initialisation /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  public PreferencesDialog(JFrame owner){
    super(owner, "Preferences", true);
    initFrame(owner);
    initComponents();
    addComponents();
    this.pack();
  }
  // endregion
  ////////////////////// region Methods ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  private void initFrame(JFrame owner){
    this.setLocationRelativeTo(owner);
    this.setLayout(new GridBagLayout());
  }

  private void initComponents(){
    this.okButton     = new JButton("OK");
    this.cancelButton = new JButton("Cancel");
    this.spinnerModel = new SpinnerNumberModel(3306, 0, 9999, 1);
    this.portSpinner  = new JSpinner(spinnerModel);
  }

  private void addComponents(){
    GridBagConstraints gc = new GridBagConstraints();

    // Port
    gc.gridx = 0;
    gc.gridy = 0;
    this.add(new JLabel("Port:"), gc);

    gc.gridx = 1;
    gc.gridy = 0;
    this.add(this.portSpinner, gc);

  }
  // endregion
  ////////////////////// region Inner Classes //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  ////////////////////// End of Class //////////////////////////////////////////////////////////////////////////////////////////////////////////////// endregion
}

