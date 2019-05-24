package com.wagner.employee_app.ui.dialogs;

import com.wagner.employee_app.ui.util.UIFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public abstract class AbstractDefaultModalDialog{
  ////////////////////// region Variables  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  private JDialog dialog;
  // endregion
  ////////////////////// region Initialisation /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  protected AbstractDefaultModalDialog(Frame owner, String title){
    init(owner, title);
  }
  // endregion
  ////////////////////// region Methods ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  private void init(Frame owner, String title){
    // create default components
    this.dialog               = new JDialog(owner, title, true);
    JPanel dialogContentPanel = new JPanel();
    JButton okButton          = new JButton("OK");
    JButton cancelButton      = new JButton("Cancel");
    JPanel buttonPanel        = new JPanel();

    // init frame
    this.dialog.setLocationRelativeTo(owner);
    this.dialog.getRootPane().setDefaultButton(okButton);

    // init dialogContentPanel
    dialogContentPanel.setBackground(Color.WHITE);
    dialogContentPanel.setBorder(UIFactory.createDefaultLineBorder());

    // init buttonPanel
    buttonPanel.setBackground(new Color(245,248,250));
    buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
    buttonPanel.setBorder(BorderFactory.createEmptyBorder(0,0,5,5)); // for space

    // add action listener to dialog buttons
    okButton.addActionListener(e -> onClickOK());
    cancelButton.addActionListener(e -> onClickCancel());

    // call onClickCancel() when cross is clicked
    this.dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
    this.dialog.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        onClickCancel();
      }
    });

    // call onClickCancel() on ESCAPE
    dialogContentPanel.registerKeyboardAction(e -> onClickCancel()
                                              , KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    // add components
    buttonPanel.add(okButton);
    buttonPanel.add(cancelButton);
    dialogContentPanel.add(createDialogFormPanel(), BorderLayout.PAGE_START);
    this.dialog.getContentPane().add(dialogContentPanel, BorderLayout.CENTER);
    this.dialog.getContentPane().add(buttonPanel, BorderLayout.PAGE_END);

    // set size
    this.dialog.pack();
    this.dialog.setMinimumSize(this.dialog.getPreferredSize());
    this.dialog.setResizable(false);

    // do initialisation tasks
    onInitializeDialog();
  }

  protected abstract void onInitializeDialog();

  protected abstract void onClickOK();

  protected abstract void onClickCancel();

  protected abstract JPanel createDialogFormPanel();

  public void show(){
    this.dialog.setVisible(true);
  }

  public void dispose(){
    this.dialog.dispose();
  }
  // endregion
  ////////////////////// region Inner Classes //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  ////////////////////// End of Class //////////////////////////////////////////////////////////////////////////////////////////////////////////////// endregion
}