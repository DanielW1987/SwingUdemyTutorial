package com.wagner.ui;

import com.wagner.controller.Controller;
import com.wagner.event.FormEvent;
import com.wagner.model.PersonFileFilter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class MainFrame extends JFrame {

  private TextPanel         textPanel;
  private TablePanel        tablePanel;
  private JButton           button;
  private Toolbar           toolbar;
  private FormPanel         formPanel;
  private JFileChooser      fileChooser;
  private Controller        controller;
  private PreferencesDialog preferencesDialog;

  public MainFrame(){
    super("Main Frame");
    initFrame();
    initComponents();
    addComponents();
  }

  private void initFrame(){
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());
    this.setVisible(true);
    this.controller = new Controller();
  }

  private void initComponents(){
    this.textPanel         = new TextPanel();
    this.tablePanel        = new TablePanel();
    this.toolbar           = new Toolbar();
    this.formPanel         = new FormPanel();
    this.fileChooser       = new JFileChooser();
    this.preferencesDialog = new PreferencesDialog(this);
    this.button            = new JButton("Click me!");

    this.toolbar.registerStringListener(this.textPanel);
    this.tablePanel.setData(controller.getPersons());
    this.tablePanel.setPersonTableListener(controller::removePerson);
    this.formPanel.setFormListener(this::onFormEventOccured);
    this.button.addActionListener(this::onButtonClicked);
    this.fileChooser.addChoosableFileFilter(new PersonFileFilter());
    this.fileChooser.setAcceptAllFileFilterUsed(false);
  }

  private void addComponents(){
    this.add(toolbar, BorderLayout.NORTH);
    this.add(tablePanel, BorderLayout.CENTER);
    this.add(textPanel, BorderLayout.SOUTH);
    this.add(formPanel, BorderLayout.WEST);
    this.setJMenuBar(createMenuBar());

    this.pack();
    this.setMinimumSize(this.getPreferredSize());
  }

  private JMenuBar createMenuBar(){

    /*
     * MenuBar
     */
    JMenuBar menuBar  = new JMenuBar();

    /*
     * Menu 'File'
     */
    JMenu fileMenu                 = new JMenu("File");
    fileMenu.setMnemonic(KeyEvent.VK_F);

    ImageIcon exitIcon             = new ImageIcon(this.getClass().getClassLoader().getResource("exit.png"));
    ImageIcon importIcon           = new ImageIcon(this.getClass().getClassLoader().getResource("import.png"));
    ImageIcon exportIcon           = new ImageIcon(this.getClass().getClassLoader().getResource("export.png"));
    JMenuItem importDataMenuItem   = new JMenuItem("Import data...", importIcon);
    JMenuItem exportDataMenuItem   = new JMenuItem("Export data...", exportIcon);
    JMenuItem exitDataMenuItem     = new JMenuItem("Exit", exitIcon);

    exitDataMenuItem.setMnemonic('X');
    exitDataMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_MASK));
    exitDataMenuItem.addActionListener(this::onExit);

    importDataMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.ALT_MASK));
    importDataMenuItem.addActionListener(this::onImportDataClicked);

    exportDataMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.ALT_MASK));
    exportDataMenuItem.addActionListener(this::onExportDataClicked);

    // add all to fileMenu
    fileMenu.add(importDataMenuItem);
    fileMenu.add(exportDataMenuItem);
    fileMenu.addSeparator();
    fileMenu.add(exitDataMenuItem);

    /*
     * Menu 'Window'
     */
    JMenu windowMenu  = new JMenu("Window");
    windowMenu.setMnemonic('W');

    JMenu viewSubMenu = new JMenu("View");

    JCheckBoxMenuItem personFormViewItem = new JCheckBoxMenuItem("Person Form");
    personFormViewItem.setSelected(true);
    personFormViewItem.addActionListener(e -> {
      boolean state = personFormViewItem.isSelected();
      this.formPanel.setVisible(state);
    });

    JMenuItem preferenceMenuItem = new JMenuItem("Preferences...");
    preferenceMenuItem.addActionListener(e -> this.preferencesDialog.setVisible(true));

    viewSubMenu.add(personFormViewItem);
    windowMenu.add(viewSubMenu);
    windowMenu.add(preferenceMenuItem);

    // add all to menuBar
    menuBar.add(fileMenu);
    menuBar.add(windowMenu);

    return menuBar;
  }

  private void onButtonClicked(ActionEvent e) {
    this.textPanel.append(e.paramString() + System.lineSeparator());
  }

  private void onFormEventOccured(FormEvent event){
    this.controller.addPerson(event);
    this.tablePanel.refresh();
  }

  private void onExit(ActionEvent event){
    int answer = JOptionPane.showConfirmDialog(this, "Are you soure?", "Close Application", JOptionPane.YES_NO_OPTION);
    if(answer == JOptionPane.YES_OPTION){
      System.exit(0);
    }
  }

  private void onExportDataClicked(ActionEvent event){
    int returnValue = fileChooser.showSaveDialog(this);
    if(returnValue == JFileChooser.APPROVE_OPTION){
      try{
        this.controller.saveToFile(fileChooser.getSelectedFile());
        JOptionPane.showMessageDialog(this, "Export successfully completed!", "Export data", JOptionPane.INFORMATION_MESSAGE);
      }
      catch(IOException ioe){
        JOptionPane.showMessageDialog(this, ioe.getMessage(), "Error during saving", JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  private void onImportDataClicked(ActionEvent event){
    int returnValue = fileChooser.showOpenDialog(this);
    if(returnValue == JFileChooser.APPROVE_OPTION){
      try{
        this.controller.loadFromFile(fileChooser.getSelectedFile());
        this.tablePanel.refresh();
        JOptionPane.showMessageDialog(this, "Import successfully completed!", "Import data", JOptionPane.INFORMATION_MESSAGE);
      }
      catch(IOException ioe){
        JOptionPane.showMessageDialog(this, ioe.getMessage(), "Error during loading", JOptionPane.ERROR_MESSAGE);
      }
    }
  }
}