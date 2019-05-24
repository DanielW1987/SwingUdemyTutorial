package com.wagner.employee_app.database;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.wagner.employee_app.util.PreferencesConstants;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.prefs.Preferences;

public final class JDBCConnectionPool {
  ////////////////////// region Variables  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  private static ComboPooledDataSource dataSource;
  // endregion
  ////////////////////// region Initialisation /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  static{
    // ToDo dwg: user .properties?
    Preferences preferences = Preferences.userRoot().node(PreferencesConstants.DB_PREFERENCES);
    String jdbcUrl          = buildJdbcUrl(  preferences.get(PreferencesConstants.DATABASE_HOST, "")
                                           , preferences.get(PreferencesConstants.DATABASE_PORT, "")
                                           , preferences.get(PreferencesConstants.DATABASE_NAME, ""));

    dataSource = new ComboPooledDataSource();
    dataSource.setJdbcUrl(jdbcUrl);
    dataSource.setUser(preferences.get(PreferencesConstants.DATABASE_USERNAME, ""));
    dataSource.setPassword(preferences.get(PreferencesConstants.DATABASE_PASSWORD, ""));

    dataSource.setMinPoolSize(5);
    dataSource.setMaxPoolSize(100);
    dataSource.setAcquireIncrement(5); // steps to increase the pool size if necessary.
  }

  private JDBCConnectionPool(){}
  // endregion
  ////////////////////// region Methods ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  public static DataSource getDataSource(){
    return dataSource;
  }

  public static String buildJdbcUrl(String host, String port, String databaseName){
    return  "jdbc:mysql://" +
            host + ":" +
            port + "/" +
            databaseName + "?" +
            "useUnicode=true" +
            "&useJDBCCompliantTimezoneShift=true" +
            "&useLegacyDatetimeCode=false" +
            "&serverTimezone=UTC";
  }

  @SuppressWarnings("unused")
  public static void printStatus(){
    try{
      System.out.println("Amount of connections: " + dataSource.getNumConnections());
      System.out.println("Idle connections: " + dataSource.getNumIdleConnections());
      System.out.println("Busy connections: " + dataSource.getNumBusyConnections());
    }
    catch(SQLException sqlException){
      System.err.println("Unable to print data source status.");
    }
  }
  // endregion
  ////////////////////// region Inner Classes //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  ////////////////////// End of Class //////////////////////////////////////////////////////////////////////////////////////////////////////////////// endregion
}

