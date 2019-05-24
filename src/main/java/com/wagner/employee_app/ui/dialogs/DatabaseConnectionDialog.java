package com.wagner.employee_app.ui.dialogs;

import com.wagner.employee_app.database.JDBCConnectionPool;
import com.wagner.employee_app.ui.util.FormatterFactory;
import com.wagner.employee_app.util.PreferencesConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.prefs.Preferences;

public final class DatabaseConnectionDialog extends AbstractDefaultModalDialog {
  ////////////////////// region Variables  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  private JPanel                         dialogFormPanel;
  private JLabel                         hostLabel;
  private JTextField                     host;
  private JLabel                         databaseNameLabel;
  private JTextField                     databaseName;
  private JLabel                         portLabel;
  private JFormattedTextField            port;
  private JLabel                         userNameLabel;
  private JTextField                     userName;
  private JLabel                         passwordLabel;
  private JPasswordField                 password;
  private JButton                        testConnectionButton;
  private JLabel                         testConnectionStateLabel;
  // endregion
  ////////////////////// region Initialisation /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  public DatabaseConnectionDialog(Frame owner){
    super(owner, "Database Connection");
  }
  // endregion
  ////////////////////// region Methods ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  @Override
  public JPanel createDialogFormPanel() {
    this.dialogFormPanel          = new JPanel(new GridBagLayout());
    this.hostLabel                = new JLabel("Database host:");
    this.host                     = new JTextField(15);
    this.databaseNameLabel        = new JLabel("Database name:");
    this.databaseName             = new JTextField(15);
    this.portLabel                = new JLabel("Port:");
    this.port                     = new JFormattedTextField(FormatterFactory.createPortNumberFormatter());
    this.userNameLabel            = new JLabel("Username:");
    this.userName                 = new JTextField(10);
    this.passwordLabel            = new JLabel("Password:");
    this.password                 = new JPasswordField(10);
    this.testConnectionButton     = new JButton("Test connection");
    this.testConnectionStateLabel = new JLabel();

    // adjustments to components
    this.port.setColumns(5);
    this.testConnectionStateLabel.setVisible(false);

    // style dialogFormPanel
    this.dialogFormPanel.setBackground(Color.WHITE);

    // set labels to text field
    this.hostLabel.setLabelFor(this.host);
    this.databaseNameLabel.setLabelFor(this.databaseName);
    this.portLabel.setLabelFor(this.port);
    this.userNameLabel.setLabelFor(this.userName);
    this.passwordLabel.setLabelFor(this.passwordLabel);

    // add listener
    this.testConnectionButton.addActionListener(this::onClickTestConnection);

    // add components to grid
    GridBagConstraints gc = new GridBagConstraints();

    // general constraints
    gc.insets = new Insets(5,5,5,5);
    gc.anchor = GridBagConstraints.FIRST_LINE_START;

    // Database host
    gc.gridx  = 0;
    gc.gridy  = 0;
    this.dialogFormPanel.add(hostLabel, gc);

    gc.gridx = 1;
    gc.gridy = 0;
    this.dialogFormPanel.add(host, gc);

    // Database name
    gc.gridx = 0;
    gc.gridy = 1;
    this.dialogFormPanel.add(databaseNameLabel, gc);

    gc.gridx = 1;
    gc.gridy = 1;
    this.dialogFormPanel.add(databaseName, gc);

    // Database port
    gc.gridx = 0;
    gc.gridy = 2;
    this.dialogFormPanel.add(this.portLabel, gc);

    gc.gridx = 1;
    gc.gridy = 2;
    this.dialogFormPanel.add(this.port, gc);

    // Database user
    gc.gridx = 0;
    gc.gridy = 3;
    this.dialogFormPanel.add(this.userNameLabel, gc);

    gc.gridx = 1;
    gc.gridy = 3;
    this.dialogFormPanel.add(this.userName, gc);

    // Database password
    gc.gridx = 0;
    gc.gridy = 4;
    this.dialogFormPanel.add(this.passwordLabel, gc);

    gc.gridx = 1;
    gc.gridy = 4;
    this.dialogFormPanel.add(this.password, gc);

    // Test connection
    gc.gridx  = 0;
    gc.gridy  = 5;
    gc.insets = new Insets(20, 0, 0 ,20);
    this.dialogFormPanel.add(this.testConnectionButton, gc);

    gc.gridx  = 1;
    gc.gridy  = 5;
    gc.anchor = GridBagConstraints.LINE_START;
    this.dialogFormPanel.add(this.testConnectionStateLabel, gc);

    return this.dialogFormPanel;
  }

  @Override
  protected void onInitializeDialog() {
    Preferences preferences = Preferences.userRoot().node(PreferencesConstants.DB_PREFERENCES);
    this.host.setText(preferences.get(PreferencesConstants.DATABASE_HOST, "localhost"));
    this.databaseName.setText(preferences.get(PreferencesConstants.DATABASE_NAME, ""));
    this.port.setValue(Integer.valueOf(preferences.get(PreferencesConstants.DATABASE_PORT, "3306")));
    this.userName.setText(preferences.get(PreferencesConstants.DATABASE_USERNAME, ""));
    this.password.setText(preferences.get(PreferencesConstants.DATABASE_PASSWORD, ""));
  }

  @Override
  protected void onClickOK() {
    Preferences preferences = Preferences.userRoot().node("db");
    preferences.put(PreferencesConstants.DATABASE_HOST, this.host.getText());
    preferences.put(PreferencesConstants.DATABASE_NAME, this.databaseName.getText());
    preferences.putInt(PreferencesConstants.DATABASE_PORT, Integer.parseInt(this.port.getValue().toString()));
    preferences.put(PreferencesConstants.DATABASE_USERNAME, this.userName.getText());
    preferences.put(PreferencesConstants.DATABASE_PASSWORD, String.valueOf(this.password.getPassword()));

    this.dispose();
  }

  @Override
  protected void onClickCancel() {
    this.dispose();
  }

  private void onClickTestConnection(ActionEvent event){
    String user     = this.userName.getText();
    String password = String.valueOf(this.password.getPassword());
    String jdbcUrl  = JDBCConnectionPool.buildJdbcUrl(this.host.getText(), this.port.getValue().toString(), this.databaseName.getText());

    try(Connection connection = DriverManager.getConnection(jdbcUrl, user, password)){
      this.testConnectionStateLabel.setText("Connection sucessful!");
      this.testConnectionStateLabel.setForeground(new Color(0,109,49));
      this.testConnectionStateLabel.setVisible(true);
    }
    catch(SQLException sqle){
      //TODO dwg: display detailed error message
      this.testConnectionStateLabel.setText("Connection failed!");
      this.testConnectionStateLabel.setForeground(Color.RED);
      this.testConnectionStateLabel.setVisible(true);
    }
  }
  // endregion
  ////////////////////// region Inner Classes //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  ////////////////////// End of Class //////////////////////////////////////////////////////////////////////////////////////////////////////////////// endregion
}