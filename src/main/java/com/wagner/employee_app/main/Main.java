package com.wagner.employee_app.main;

import com.wagner.employee_app.ui.MainApplicationFrame;

import javax.swing.*;
import java.awt.*;

public final class Main {
  ////////////////////// region Variables  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // endregion
  ////////////////////// region Initialisation /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // endregion
  ////////////////////// region Methods ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  public static void main(String... args){
    setLookAndFeel();
    // start application
    EventQueue.invokeLater(() -> {
      MainApplicationFrame frame = new MainApplicationFrame();
      frame.setVisible(true);
    } );
  }

  @SuppressWarnings("unused")
  private void printAvailableLookAndFeels(){
    try{
      for(UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
        System.out.println(info.getName());
      }
    }
    catch(Exception e){
      e.printStackTrace();
    }
  }

  private static void setLookAndFeel(){
    try{
      //UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
      UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
    }
    catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e){
      e.printStackTrace();
    }
  }
  // endregion
  ////////////////////// region Inner Classes //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  ////////////////////// End of Class //////////////////////////////////////////////////////////////////////////////////////////////////////////////// endregion
}