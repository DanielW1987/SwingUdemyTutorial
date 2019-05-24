package com.wagner.employee_app.model.server;

import javax.swing.*;

public class ServerTreeNode {

  private String name;
  private Long id;
  private ServerNodeType nodeType;
  private ImageIcon icon;
  private boolean isSelected;

  public ServerTreeNode(String name, Long id, ServerNodeType nodeType, ImageIcon icon) {
    this.name     = name;
    this.id       = id;
    this.nodeType = nodeType;
    this.icon     = icon;
  }

  public ServerTreeNode(String name, Long id, ServerNodeType nodeType){
    this(name, id, nodeType, null);
  }

  public String getName() {
    return name;
  }

   public ServerNodeType getNodeType(){
    return this.nodeType;
  }

  public ImageIcon getIcon() {
    return icon;
  }

  public boolean isSelected() {
    return isSelected;
  }

  public void setSelected(boolean selected) {
    isSelected = selected;
  }

  @Override
  public String toString() {
    return this.name;
  }
}
