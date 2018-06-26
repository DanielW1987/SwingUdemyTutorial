package com.wagner.model;

import com.wagner.util.FileUtil;

import javax.swing.filechooser.FileFilter;
import java.io.File;
import java.util.Arrays;
import java.util.List;

public class PersonFileFilter extends FileFilter {

  private List<String> validExtensions = Arrays.asList("persondb");

  @Override
  public boolean accept(File f) {
    String fileExtension = FileUtil.getFileExtension(f);
    return f.isDirectory() || validExtensions.contains(fileExtension);
  }

  @Override
  public String getDescription() {
    return "Person database files (*.persondb)";
  }
}

