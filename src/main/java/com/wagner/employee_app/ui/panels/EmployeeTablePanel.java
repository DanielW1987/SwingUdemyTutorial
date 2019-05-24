package com.wagner.employee_app.ui.panels;

import com.wagner.employee_app.model.employee.Employee;
import com.wagner.employee_app.model.employee.EmployeeDepartment;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.EventObject;
import java.util.List;

public final class EmployeeTablePanel extends JPanel{
  ////////////////////// region Variables  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  private JTable table;
  private EmployeeTableModel model;
  private static final int SCROLLABLE_VIEW_PORT_SIZE = 12;
  ////////////////////// region Initialisation /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // ToDo dwg: handle table widths
  public EmployeeTablePanel(List<Employee> employees){
    this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    this.setBorder(BorderFactory.createEmptyBorder(5 ,5 ,5, 5));

    this.model = new EmployeeTableModel(employees);
    this.table = new JTable(model);

    this.table.setDefaultRenderer(EmployeeDepartment.class, new EmployeeDepartmentCellRenderer());
    this.table.setDefaultEditor(EmployeeDepartment.class, new EmployeeDepartmentCellEditor());
    this.table.setRowHeight(25);
    this.table.setFillsViewportHeight(true);
    this.table.setPreferredScrollableViewportSize(new Dimension(table.getPreferredSize().width, table.getRowHeight() * SCROLLABLE_VIEW_PORT_SIZE));
    this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    this.add(new JScrollPane(table));
  }
  ////////////////////// region Methods ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  public void updateAndRefresh(List<Employee> employees){
    this.model.setEmployees(employees);
    this.model.fireTableDataChanged();
  }

  public void addListSelectionListener(ListSelectionListener listener){
    table.getSelectionModel().addListSelectionListener(listener);
  }

  public Object getValueAt(int rowIndex, int columnIndex){
    return this.model.getValueAt(rowIndex, columnIndex);
  }
  // endregion
  ////////////////////// region Inner Classes //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  ////////////////////// End of Class //////////////////////////////////////////////////////////////////////////////////////////////////////////////// endregion
}

final class EmployeeTableModel extends AbstractTableModel {

  private final String[] COLUMN_NAMES = {"ID", "First name", "Last name", "eMail", "Department", "Team Lead", "Salary"};
  private List<Employee> employees;

  EmployeeTableModel(List<Employee> employees){
    super();
    this.employees = employees;
  }

  void setEmployees(List<Employee> employees){
    this.employees = employees;
  }

  @Override
  public int getRowCount() {
    return this.employees.size();
  }

  @Override
  public boolean isCellEditable(int rowIndex, int columnIndex) {
    return columnIndex == 1 || columnIndex == 2 || columnIndex == 4;
  }

  @Override
  public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    Employee employee = employees.get(rowIndex);
    switch (columnIndex){
      case 1:
        employee.setFirstName((String)aValue);
        break;
      case 2:
        employee.setLastName((String)aValue);
        break;
      case 4:
        employee.setDepartment(((EmployeeDepartment) aValue));
    }
  }

  @Override
  public Class<?> getColumnClass(int columnIndex) {
    switch(columnIndex){
      case 4:
        return EmployeeDepartment.class;
      case 5:
        return Boolean.class;
      default:
        return String.class;
    }
  }

  @Override
  public int getColumnCount() {
    return COLUMN_NAMES.length;
  }

  @Override
  public String getColumnName(int column) {
    return COLUMN_NAMES[column];
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    Employee employee = this.employees.get(rowIndex);

    switch(columnIndex){
      case 0:
        return employee.getId();
      case 1:
        return employee.getFirstName();
      case 2:
        return employee.getLastName();
      case 3:
        return employee.getEmail();
      case 4:
        return employee.getDepartment().getName();
      case 5:
        return employee.isTeamLead();
      case 6:
        return employee.getSalary();
      default:
        throw new IllegalArgumentException("snh: column value for index " + columnIndex + " is not specified!");
    }
  }
}

final class EmployeeDepartmentCellRenderer implements TableCellRenderer{

  private JComboBox<EmployeeDepartment> comboBox;

  EmployeeDepartmentCellRenderer(){
    comboBox = new JComboBox<>(EmployeeDepartment.values());
  }

  @Override
  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    comboBox.setSelectedItem(value);
    return comboBox;
  }
}

final class EmployeeDepartmentCellEditor extends AbstractCellEditor implements TableCellEditor{

  private JComboBox<EmployeeDepartment> comboBox;

  EmployeeDepartmentCellEditor(){
    comboBox = new JComboBox<>(EmployeeDepartment.values());
  }

  @Override
  public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
    comboBox.setSelectedItem(value);
    comboBox.addActionListener(e -> fireEditingStopped());
    return comboBox;
  }

  @Override
  public Object getCellEditorValue() {
    return comboBox.getSelectedItem();
  }

  @Override
  public boolean isCellEditable(EventObject e) {
    return true;
  }
}