package com.wagner.util;

import java.io.File;

public class FileUtil {
  ////////////////////// region Variables  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // endregion
  ////////////////////// region Initialisation /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  public static String getFileExtension(File file){
    String fileName      = file.getName();
    int indexOfLastPoint = fileName.lastIndexOf('.');

    if(   indexOfLastPoint == -1
       || indexOfLastPoint == fileName.length() - 1){
      return null;
    }

    return fileName.substring(indexOfLastPoint + 1);
  }
  // endregion
  ////////////////////// region Methods ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // endregion
  ////////////////////// region Inner Classes //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  ////////////////////// End of Class //////////////////////////////////////////////////////////////////////////////////////////////////////////////// endregion
}
