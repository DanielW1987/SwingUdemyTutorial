package com.wagner.employee_app.ui.dialogs;

import com.wagner.employee_app.ui.listener.ProgressDialogListener;

import javax.swing.*;
import java.awt.*;

public final class ProgressDialog extends JDialog {

  private JButton                cancelButton;
  private JProgressBar           progressBar;
  private ProgressDialogListener cancelListener;

  public ProgressDialog(Window parent){
    super(parent, "Please wait", ModalityType.APPLICATION_MODAL);

    this.cancelButton = new JButton("Cancel");
    this.progressBar  = new JProgressBar();
    // this.progressBar.setIndeterminate(true);
    this.progressBar.setStringPainted(true);

    this.cancelButton.addActionListener(e -> {
      if(cancelListener != null){
        this.cancelListener.cancelled();
      }
    });

    this.setSize(400, 200);
    this.setLocationRelativeTo(parent);
    this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

    this.setLayout(new FlowLayout(FlowLayout.LEFT));
    this.add(this.progressBar);
    this.add(this.cancelButton);
    this.pack();
  }

  public void setMaximum(int value){
    this.progressBar.setMaximum(value);
  }

  public void setValue(int value){
    this.progressBar.setValue(value);
    int progress = 100 *  this.progressBar.getValue() / this.progressBar.getMaximum();
    this.progressBar.setString(progress + "%");
  }

  @Override
  public void setVisible(boolean visible) {
    SwingUtilities.invokeLater(() -> {
      super.setVisible(visible);
    });
  }

  public void setCancelListener(ProgressDialogListener cancelListener){
    this.cancelListener = cancelListener;
  }
}