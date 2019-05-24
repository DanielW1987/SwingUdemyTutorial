package com.wagner.employee_app.ui.panels;

import com.wagner.employee_app.model.server.ServerNodeType;
import com.wagner.employee_app.model.server.ServerTreeNode;
import com.wagner.employee_app.ui.util.IconFactory;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

class ServerTreePanel extends JPanel {

  private JTree tree;
  private JSplitPane splitPane;

  ServerTreePanel(){
    this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    this.initTree();

    this.splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, new JScrollPane(tree), new JPanel());
    this.splitPane.setDividerLocation(100);
    this.add(this.splitPane);
  }

  private void initTree(){
    this.tree = new JTree(createTree());
    this.tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
    this.tree.addTreeSelectionListener(e -> {
      DefaultMutableTreeNode node = (DefaultMutableTreeNode) this.tree.getLastSelectedPathComponent();
      System.out.println(e.getNewLeadSelectionPath());
      System.out.println(e.getOldLeadSelectionPath());
      System.out.println(e.getPath());
      if(node != null){
        Object userObject = node.getUserObject();
        System.out.println(userObject);
      }
      else{
        System.err.println("node is null");
      }
    });

    ServerTreeCellRenderer cellRenderer = new ServerTreeCellRenderer();
    ServerTreeCellEditor cellEditor     = new ServerTreeCellEditor();
    this.tree.setCellRenderer(cellRenderer);
    this.tree.setCellEditor(cellEditor);
    this.tree.setEditable(true);
  }

  private DefaultMutableTreeNode createTree(){
    DefaultMutableTreeNode root    = new DefaultMutableTreeNode(new ServerTreeNode("Servers", 1L, ServerNodeType.ROOT, IconFactory.getServerIcon()));
    DefaultMutableTreeNode branch1 = new DefaultMutableTreeNode(new ServerTreeNode("USA", 2L, ServerNodeType.COUNTRY, IconFactory.getUsaFlagIcon()));
    DefaultMutableTreeNode server1 = new DefaultMutableTreeNode(new ServerTreeNode("New York", 3L, ServerNodeType.SERVER));
    DefaultMutableTreeNode server2 = new DefaultMutableTreeNode(new ServerTreeNode("Boston", 4L, ServerNodeType.SERVER));
    DefaultMutableTreeNode server3 = new DefaultMutableTreeNode(new ServerTreeNode("Los Angeles", 5L, ServerNodeType.SERVER));

    branch1.add(server1);
    branch1.add(server2);
    branch1.add(server3);

    DefaultMutableTreeNode branch2 = new DefaultMutableTreeNode(new ServerTreeNode("UK", 6L, ServerNodeType.COUNTRY, IconFactory.getGreatBritainFlagIcon()));
    DefaultMutableTreeNode server4 = new DefaultMutableTreeNode(new ServerTreeNode("London", 7L, ServerNodeType.SERVER));
    DefaultMutableTreeNode server5 = new DefaultMutableTreeNode(new ServerTreeNode("Manchester", 8L, ServerNodeType.SERVER));

    branch2.add(server4);
    branch2.add(server5);

    root.add(branch1);
    root.add(branch2);

    return root;
  }
}

class ServerTreeCellRenderer implements TreeCellRenderer {

  @Override
  public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
    final ServerTreeNode serverTreeNode = ((ServerTreeNode) ((DefaultMutableTreeNode) value).getUserObject());
    switch (serverTreeNode.getNodeType()) {
      case ROOT:
      case COUNTRY:
        DefaultTreeCellRenderer treeCellRenderer = new DefaultTreeCellRenderer();
        treeCellRenderer.setOpenIcon(serverTreeNode.getIcon());
        treeCellRenderer.setClosedIcon(serverTreeNode.getIcon());
        treeCellRenderer.setLeafIcon(serverTreeNode.getIcon());
        return treeCellRenderer.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
      case SERVER:
        Color textForeground      = UIManager.getColor("Tree.textForeground");
        Color textBackground      = UIManager.getColor("Tree.textBackground");
        Color selectionForeground = UIManager.getColor("Tree.selectionForeground");
        Color selectionBackground = UIManager.getColor("Tree.selectionBackground");
        JCheckBox checkBox        = new JCheckBox(serverTreeNode.getName());
        if(selected){
          checkBox.setForeground(selectionForeground);
          checkBox.setBackground(selectionBackground);
        }
        else{
          checkBox.setForeground(textForeground);
          checkBox.setBackground(textBackground);
        }
        checkBox.setSelected(serverTreeNode.isSelected());
        return checkBox;
      default:
        throw new IllegalStateException("snh: Unknown ServerNodeType!");
    }
  }
}

class ServerTreeCellEditor extends AbstractCellEditor implements TreeCellEditor{

  private ServerTreeCellRenderer renderer;
  private ServerTreeNode serverTreeNode;
  private JCheckBox checkBox;

  ServerTreeCellEditor(){
    this.renderer = new ServerTreeCellRenderer();
  }

  @Override
  public Component getTreeCellEditorComponent(JTree tree, Object value, boolean isSelected, boolean expanded, boolean leaf, int row) {
    Component component = renderer.getTreeCellRendererComponent(tree, value, isSelected, expanded, leaf, row, true);

    if(leaf){

      // set serverTreeNode
      final DefaultMutableTreeNode mutableTreeNode = (DefaultMutableTreeNode) value;
      serverTreeNode = (ServerTreeNode) mutableTreeNode.getUserObject();

      // add listener to checkbox
      checkBox = (JCheckBox) component;
      ItemListener itemListener = new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent e) {
          fireEditingStopped();
          checkBox.removeItemListener(this);
        }
      };
    }

    return component;
  }

  @Override
  public Object getCellEditorValue() {
    // is invoked if fireEditingStopped() is fired.
    serverTreeNode.setSelected(checkBox.isSelected());
    return serverTreeNode;
  }

  @Override
  public boolean isCellEditable(EventObject e) {
    // method is invoked at first if a node is selected
    if(e instanceof MouseEvent){
      final MouseEvent mouseEvent = (MouseEvent) e;
      JTree tree = ((JTree) e.getSource());
      final TreePath pathForLocation = tree.getPathForLocation(mouseEvent.getX(), mouseEvent.getY());
      if(pathForLocation != null){
        final Object lastPathComponent = pathForLocation.getLastPathComponent();
        if(lastPathComponent != null){
          return ((DefaultMutableTreeNode) lastPathComponent).isLeaf();
        }
      }
    }

    return false;
  }
}