package com.wagner.employee_app.model.employee;

import com.wagner.employee_app.database.JDBCConnectionPool;
import com.wagner.employee_app.model.core.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class EmployeeRepository implements Repository<Employee> {
  ////////////////////// region Variables  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // endregion
  ////////////////////// region Initialisation /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  public EmployeeRepository(){
  }
  // endregion
  ////////////////////// region Methods ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  @Override
  public List<Employee> getAll() throws SQLException{
    List<Employee> employees = new ArrayList<>();
    String queryAllStatement = "SELECT * FROM " + Employee.TABLE_NAME;

    try(Connection connection = JDBCConnectionPool.getDataSource().getConnection();
        Statement statement   = connection.createStatement();
        ResultSet resultSet   = statement.executeQuery(queryAllStatement)){

      while(resultSet.next()){
        employees.add(Employee.of(resultSet));
      }
    }

    return employees;
  }

  @Override
  public Optional<Employee> getById(Long id) throws SQLException{
    String queryByIdStatement = "SELECT * FROM " + Employee.TABLE_NAME + " WHERE " + Employee.ID_COLUMN + "=?";

    try(Connection connection       = JDBCConnectionPool.getDataSource().getConnection();
        PreparedStatement statement = connection.prepareStatement(queryByIdStatement)){

      statement.setLong(1, id);
      ResultSet resultSet = statement.executeQuery();

      if(resultSet.next()){
        return Optional.of(Employee.of(resultSet));
      }

      resultSet.close();
    }

    return Optional.empty();
  }

  @Override
  public Optional<Employee> getByName(String name) throws SQLException {
    throw new UnsupportedOperationException("not yet implemented!");
  }

  @Override
  public List<Employee> getAllWithNameLike(String name) throws SQLException {
    List<Employee> employees = new ArrayList<>();
    String queryStatement    = "SELECT * FROM " + Employee.TABLE_NAME + " " +
                               "WHERE " + Employee.FIRST_NAME_COLUMN + " LIKE ? " +
                               "OR " + Employee.LAST_NAME_COLUMN + " LIKE ?";

    try(Connection connection       = JDBCConnectionPool.getDataSource().getConnection();
        PreparedStatement statement = connection.prepareStatement(queryStatement)){

      statement.setString(1, name + "%");
      statement.setString(2, name + "%");

      ResultSet resultSet = statement.executeQuery();

      while(resultSet.next()){
        employees.add(Employee.of(resultSet));
      }

      resultSet.close();
    }

    return employees;
  }

  @Override
  public boolean remove(Long id) throws SQLException {
    return true;
  }

  @Override
  public boolean update(Employee element) throws SQLException {
    return true;
  }

  @Override
  public Long create(Employee element) throws SQLException {
    return 0L;
  }
  // endregion
  ////////////////////// region Inner Classes //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  ////////////////////// End of Class //////////////////////////////////////////////////////////////////////////////////////////////////////////////// endregion
}