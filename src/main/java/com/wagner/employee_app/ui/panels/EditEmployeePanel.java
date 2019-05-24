package com.wagner.employee_app.ui.panels;

import com.wagner.employee_app.model.employee.Employee;
import com.wagner.employee_app.ui.util.FontFactory;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public final class EditEmployeePanel extends JPanel {

  private JTextField        id;
  private JTextField        firstName;
  private JTextField        lastName;
  private JTextField        eMail;
  private JComboBox<String> department;
  private JTextField        salary;
  private JTextArea         description;
  private JTabbedPane       tabbedPane;
  private JLabel            noDetailsLabel;
  private ServerTreePanel   serverTreePanel;

  public EditEmployeePanel(){
    super();
    initPanel();
    initComponents();
    addComponents();
  }

  private void initPanel(){
    this.setLayout(new GridBagLayout());
    this.setMinimumSize(new Dimension(270,250));
  }

  private void initComponents(){
    this.id               = new JTextField(5);
    this.firstName        = new JTextField(15);
    this.lastName         = new JTextField(15);
    this.eMail            = new JTextField(15);
    this.department       = new JComboBox<>(new String[]{"HR", "Engineering", "Legal", "Consulting"});
    this.salary           = new JTextField(15);
    this.description      = new JTextArea(10, 10);
    this.tabbedPane       = new JTabbedPane();
    this.noDetailsLabel   = new JLabel("No details available.");
    this.serverTreePanel = new ServerTreePanel();

    this.id.setEditable(false);
    this.firstName.setEditable(false);
    this.lastName.setEditable(false);
    this.eMail.setEditable(false);
    this.department.setEnabled(false);
    this.salary.setEditable(false);

    //this.description.setFont(new Font("Monospaced", Font.BOLD, 20));
    this.description.setFont(FontFactory.getVikingFont());
  }

  private void addComponents(){
    JPanel formPanel = createEditFormPanel();
    this.tabbedPane.addTab("Details", formPanel);
    this.tabbedPane.addTab("Description", new JScrollPane(description));
    this.tabbedPane.addTab("JTree", serverTreePanel);

    // add to panel
    GridBagConstraints tabbedPaneConstraint = new GridBagConstraints();
    tabbedPaneConstraint.fill               = GridBagConstraints.BOTH;
    tabbedPaneConstraint.weightx            = 1;
    tabbedPaneConstraint.weighty            = 1;
    this.add(tabbedPane, tabbedPaneConstraint);

    // by default, nothing is selected and the tabbedPane is not visible
    this.add(noDetailsLabel);
    this.tabbedPane.setVisible(false);
  }

  private JPanel createEditFormPanel(){
    JPanel formPanel       = new JPanel(new GridBagLayout());
    GridBagConstraints gc  = new GridBagConstraints();
    gc.anchor              = GridBagConstraints.FIRST_LINE_START;
    gc.insets              = new Insets(5,5, 5, 5);

    // id
    gc.gridx = 0;
    gc.gridy = 0;
    formPanel.add(new JLabel("ID:"), gc);

    gc.gridx = 1;
    gc.gridy = 0;
    formPanel.add(this.id, gc);

    // first name
    gc.gridx = 0;
    gc.gridy = 1;
    formPanel.add(new JLabel("First name:"), gc);

    gc.gridx = 1;
    gc.gridy = 1;
    formPanel.add(this.firstName, gc);

    // last name
    gc.gridx = 0;
    gc.gridy = 2;
    formPanel.add(new JLabel("Last name:"), gc);

    gc.gridx = 1;
    gc.gridy = 2;
    formPanel.add(this.lastName, gc);

    // eMail
    gc.gridx = 0;
    gc.gridy = 3;
    formPanel.add(new JLabel("eMail:"), gc);

    gc.gridx = 1;
    gc.gridy = 3;
    formPanel.add(this.eMail, gc);

    // department
    gc.gridx = 0;
    gc.gridy = 4;
    formPanel.add(new JLabel("Department:"), gc);

    gc.gridx = 1;
    gc.gridy = 4;
    formPanel.add(this.department, gc);

    // salary
    gc.gridx = 0;
    gc.gridy = 5;
    formPanel.add(new JLabel("Salary:"), gc);

    gc.gridx = 1;
    gc.gridy = 5;
    formPanel.add(this.salary, gc);

    // add to tabbed pane
    JPanel layoutedFormPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    layoutedFormPanel.add(formPanel);

    return layoutedFormPanel;
  }

  public void initEmployeePanel(Optional<Employee> employeeOptional){
    if(employeeOptional.isPresent()){
      Employee employee = employeeOptional.get();

      this.id.setText(String.valueOf(employee.getId()));
      this.firstName.setText(employee.getFirstName());
      this.lastName.setText(employee.getLastName());
      this.eMail.setText(employee.getEmail());
      this.department.setSelectedItem(employee.getDepartment());
      this.salary.setText(String.valueOf(employee.getSalary()));
      this.description.setText("");

      this.noDetailsLabel.setVisible(false);
      this.tabbedPane.setVisible(true);
    }
    else{
      this.noDetailsLabel.setVisible(true);
      this.tabbedPane.setVisible(false);
    }
  }
}