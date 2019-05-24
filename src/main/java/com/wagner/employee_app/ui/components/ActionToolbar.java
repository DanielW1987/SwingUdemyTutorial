package com.wagner.employee_app.ui.components;

import com.wagner.employee_app.ui.util.IconFactory;

import javax.swing.*;
import java.awt.*;

public class ActionToolbar extends JToolBar{
  ////////////////////// region Variables  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  private JButton addUserButton;
  private JButton editUserButton;
  private JButton removeUserButton;
  // endregion
  ////////////////////// region Initialisation /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  public ActionToolbar(){
    this.addUserButton    = new JButton(IconFactory.getAddUserIcon());
    this.editUserButton   = new JButton(IconFactory.getEditUserIcon());
    this.removeUserButton = new JButton(IconFactory.getRemoveUserIcon());

    this.addUserButton.setBorder(BorderFactory.createEmptyBorder());
    this.editUserButton.setBorder(BorderFactory.createEmptyBorder());
    this.removeUserButton.setBorder(BorderFactory.createEmptyBorder());

    this.addUserButton.setToolTipText("Add user");
    this.editUserButton.setToolTipText("Edit user");
    this.removeUserButton.setToolTipText("Remove user");

    this.addUserButton.setOpaque(false);
    this.editUserButton.setOpaque(false);
    this.removeUserButton.setOpaque(false);

    this.add(Box.createRigidArea(new Dimension(5, 10)));
    this.add(this.addUserButton);
    this.add(Box.createRigidArea(new Dimension(15, 10)));
    this.add(this.editUserButton);
    this.add(Box.createRigidArea(new Dimension(15,10)));
    this.add(this.removeUserButton);
    this.setFloatable(false);
  }
  // endregion
  ////////////////////// region Methods ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // endregion
  ////////////////////// region Inner Classes //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  ////////////////////// End of Class //////////////////////////////////////////////////////////////////////////////////////////////////////////////// endregion
}