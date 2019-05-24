package com.wagner.employee_app.ui.util;

import javax.swing.*;
import java.net.URL;

public final class IconFactory {
  ////////////////////// region Variables  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  private static ImageIcon EXIT_ICON;
  private static ImageIcon DATABASE_ICON;
  private static ImageIcon ABOUT_ICON;
  private static ImageIcon ADD_USER_ICON;
  private static ImageIcon EDIT_USER_ICON;
  private static ImageIcon REMOVE_USER_ICON;
  private static ImageIcon SEARCH_USER_ICON;
  private static ImageIcon SERVER_ICON;
  private static ImageIcon FOLDER_ICON;
  private static ImageIcon COUNTRY_ICON;
  private static ImageIcon USA_FLAG_ICON;
  private static ImageIcon GREAT_BRITAIN_FLAG_ICON;
  // endregion
  ////////////////////// region Initialisation /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  private IconFactory(){}
  // endregion
  ////////////////////// region Methods ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  public static ImageIcon getExitIcon(){
    if(EXIT_ICON == null){
      EXIT_ICON = getIconResource("images/exit.png");
    }
    return EXIT_ICON;
  }

  public static ImageIcon getDatabaseIcon(){
    if(DATABASE_ICON == null){
      DATABASE_ICON = getIconResource("images/database.png");
    }
    return DATABASE_ICON;
  }

  public static ImageIcon getAboutIcon(){
    if(ABOUT_ICON == null){
      ABOUT_ICON = getIconResource("images/about.png");
    }
    return ABOUT_ICON;
  }

  public static ImageIcon getAddUserIcon(){
    if(ADD_USER_ICON == null){
      ADD_USER_ICON = getIconResource("images/add_user.png");
    }
    return ADD_USER_ICON;
  }

  public static ImageIcon getSearchUserIcon(){
    if(SEARCH_USER_ICON == null){
      SEARCH_USER_ICON = getIconResource("images/search_user.png");
    }
    return SEARCH_USER_ICON;
  }

  public static ImageIcon getEditUserIcon(){
    if(EDIT_USER_ICON == null){
      EDIT_USER_ICON = getIconResource("images/edit_user.png");
    }
    return EDIT_USER_ICON;
  }

  public static ImageIcon getRemoveUserIcon(){
    if(REMOVE_USER_ICON == null){
      REMOVE_USER_ICON = getIconResource("images/remove_user.png");
    }
    return REMOVE_USER_ICON;
  }

  public static ImageIcon getServerIcon(){
    if(SERVER_ICON == null){
      SERVER_ICON = getIconResource("images/server.png");
    }
    return SERVER_ICON;
  }

  public static ImageIcon getFolderIcon(){
    if(FOLDER_ICON == null){
      FOLDER_ICON = getIconResource("images/folder.png");
    }
    return FOLDER_ICON;
  }

  public static ImageIcon getCountryIcon(){
    if(COUNTRY_ICON == null){
      COUNTRY_ICON = getIconResource("images/country.png");
    }
    return COUNTRY_ICON;
  }

  public static ImageIcon getGreatBritainFlagIcon(){
    if(GREAT_BRITAIN_FLAG_ICON == null){
      GREAT_BRITAIN_FLAG_ICON = getIconResource("images/great_britain.png");
    }
    return GREAT_BRITAIN_FLAG_ICON;
  }

  public static ImageIcon getUsaFlagIcon(){
    if(USA_FLAG_ICON == null){
      USA_FLAG_ICON = getIconResource("images/usa.png");
    }
    return USA_FLAG_ICON;
  }

  private static ImageIcon getIconResource(String resourceName){
    URL iconURL = IconFactory.class.getClassLoader().getResource(resourceName);
    if(iconURL != null){
      return new ImageIcon(iconURL);
    }
    else{
      return new ImageIcon();
    }
  }
  // endregion
  ////////////////////// region Inner Classes //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  ////////////////////// End of Class //////////////////////////////////////////////////////////////////////////////////////////////////////////////// endregion
}

