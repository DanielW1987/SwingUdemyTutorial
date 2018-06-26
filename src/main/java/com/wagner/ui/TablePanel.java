package com.wagner.ui;

import com.wagner.listener.PersonTableListener;
import com.wagner.model.Person;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class TablePanel extends JPanel {
  ////////////////////// region Variables  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  private JTable              table;
  private PersonTableModel    personTableModel;
  private JPopupMenu          popupMenu;
  private PersonTableListener personTableListener;
  // endregion
  ////////////////////// region Initialisation /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  public TablePanel(){
    initComponents();
  }
  // endregion
  ////////////////////// region Methods ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  public void setData(List<Person> persons){
    this.personTableModel.setData(persons);
  }

  public void refresh(){
    this.personTableModel.fireTableDataChanged();
  }

  public void setPersonTableListener(PersonTableListener listener){
    this.personTableListener = listener;
  }

  private void initComponents(){
    this.personTableModel = new PersonTableModel();
    this.table            = new JTable(personTableModel);
    this.popupMenu        = createPopupMenu();

    // table
    this.table.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        int rowIndex = table.rowAtPoint(e.getPoint());
        table.getSelectionModel().setSelectionInterval(rowIndex, rowIndex);
        if(e.getButton() == MouseEvent.BUTTON3)
          popupMenu.show(table, e.getX(), e.getY());
      }
    });

    // panel
    this.setLayout(new BorderLayout());
    this.add(new JScrollPane(table), BorderLayout.CENTER);
  }

  private JPopupMenu createPopupMenu(){
    // create items
    JPopupMenu popupMenu = new JPopupMenu();
    JMenuItem removeItem = new JMenuItem("Delete row");

    // add items
    popupMenu.add(removeItem);

    // set action listener
    removeItem.addActionListener(e -> {
      int selectedRowIndex = table.getSelectedRow();
      personTableListener.rowDeleted(selectedRowIndex);
      personTableModel.fireTableRowsDeleted(selectedRowIndex, selectedRowIndex);
    });

    return popupMenu;
  }
  // endregion
  ////////////////////// region Inner Classes //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  ////////////////////// End of Class //////////////////////////////////////////////////////////////////////////////////////////////////////////////// endregion
}

class PersonTableModel extends AbstractTableModel{

  private List<Person> persons;
  private String[] COLUMN_NAMES = {"ID", "Name", "Occupation", "Age category", "Employment type", "Gender", "Tax ID"};

  public void setData(List<Person> persons){
    this.persons = persons;
  }

  @Override
  public String getColumnName(int columnIndex) {
    return COLUMN_NAMES[columnIndex];
  }

  @Override
  public int getRowCount() {
    return this.persons.size();
  }

  @Override
  public int getColumnCount() {
    return 7;
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    Person person = this.persons.get(rowIndex);

    switch(columnIndex){
      case 0:
        return person.getId();
      case 1:
        return person.getName();
      case 2:
        return person.getOccupation();
      case 3:
        return person.getAgeCategory();
      case 4:
        return person.getEmploymentType();
      case 5:
        return person.getGender();
      case 6:
        return person.getTaxID();
      default:
        throw new IllegalArgumentException("snh: column value for index " + columnIndex + " is not specified!");
    }
  }
}