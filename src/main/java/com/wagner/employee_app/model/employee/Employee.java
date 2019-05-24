package com.wagner.employee_app.model.employee;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class Employee {
  ////////////////////// region Variables  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  private Long id;
  private EmployeeDepartment department;
  private String email;
  private String firstName;
  private String lastName;
  private boolean teamLead;
  private double salary;

  static final String TABLE_NAME        = "employees";
  static final String ID_COLUMN         = "id";
  static final String DEPARTMENT_COLUMN = "department";
  static final String EMAIL_COLUMN      = "email";
  static final String FIRST_NAME_COLUMN = "first_name";
  static final String LAST_NAME_COLUMN  = "last_name";
  static final String TEAM_LEAD         = "team_lead";
  static final String SALARY_COLUMN     = "salary";
  // endregion
  ////////////////////// region Initialisation /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // endregion
  ////////////////////// region Methods ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  public Long getId() {
    return id;
  }

  public EmployeeDepartment getDepartment() {
    return department;
  }

  public String getEmail() {
    return email;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public boolean isTeamLead() {
    return teamLead;
  }

  public double getSalary() {
    return salary;
  }

  public String toString(){
    return this.id + " " + this.firstName + " " + this.lastName + " (" + this.department + ")";
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setDepartment(EmployeeDepartment department) {
    this.department = department;
  }

  public static Employee of(ResultSet resultSet) throws SQLException{
    Employee employee     = new Employee();
    employee.id           = resultSet.getLong(ID_COLUMN);
    employee.lastName     = resultSet.getString(LAST_NAME_COLUMN);
    employee.firstName    = resultSet.getString(FIRST_NAME_COLUMN);
    employee.email        = resultSet.getString(EMAIL_COLUMN);
    employee.department   = EmployeeDepartment.ofName(resultSet.getString(DEPARTMENT_COLUMN));
    employee.teamLead     = resultSet.getBoolean(TEAM_LEAD);
    employee.salary       = resultSet.getDouble(SALARY_COLUMN);

    return employee;
  }
  // endregion
  ////////////////////// region Inner Classes //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  ////////////////////// End of Class //////////////////////////////////////////////////////////////////////////////////////////////////////////////// endregion
}

