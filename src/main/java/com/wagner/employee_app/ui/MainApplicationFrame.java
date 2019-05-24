package com.wagner.employee_app.ui;

import com.wagner.employee_app.controller.MainApplicationFrameController;
import com.wagner.employee_app.model.employee.Employee;
import com.wagner.employee_app.ui.components.ActionToolbar;
import com.wagner.employee_app.ui.components.PlaceholderTextField;
import com.wagner.employee_app.ui.dialogs.AboutDialog;
import com.wagner.employee_app.ui.dialogs.DatabaseConnectionDialog;
import com.wagner.employee_app.ui.dialogs.ProgressDialog;
import com.wagner.employee_app.ui.panels.EditEmployeePanel;
import com.wagner.employee_app.ui.panels.EmployeeTablePanel;
import com.wagner.employee_app.ui.util.IconFactory;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public final class MainApplicationFrame extends JFrame{
  ////////////////////// region Variables  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  private Container                      contentPane;
  private DatabaseConnectionDialog       databaseConnectionDialog;
  private EmployeeTablePanel             employeeTablePanel;
  private EditEmployeePanel              editEmployeePanel;
  private JPanel                         actionToolbarPanel;
  private ActionToolbar                  actionToolbar;
  private JPanel                         searchPanel;
  private PlaceholderTextField           searchText;
  private JSplitPane                     splitPane;
  private JButton                        searchEmployeeButton;
  private MainApplicationFrameController controller;
  // endregion
  ////////////////////// region Initialisation /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  public MainApplicationFrame(){
    init();
  }
  // endregion
  ////////////////////// region Methods ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  private void init(){
    this.controller = new MainApplicationFrameController();
    initFrame();
    initComponents();
    addComponents();
    this.pack();
  }

  private void initFrame(){
    this.setTitle("Demo Application");
    //this.setSize(800, 800);
    this.setLocationRelativeTo(null); // centered on monitor
    this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    this.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        MainApplicationFrame.this.onClickCloseApplication();
      }
    });

    contentPane = this.getContentPane();
    contentPane.setLayout(new BorderLayout());
  }

  private void initComponents(){
    this.actionToolbarPanel   = new JPanel(new FlowLayout(FlowLayout.LEFT));
    this.actionToolbar        = new ActionToolbar();
    this.searchPanel          = new JPanel(new FlowLayout(FlowLayout.LEFT));
    this.searchText           = new PlaceholderTextField(20);
    this.searchEmployeeButton = new JButton("Search", IconFactory.getSearchUserIcon());

    // init employee table
    try{
      List<Employee> employees = controller.getAllEmployees();
      this.employeeTablePanel  = new EmployeeTablePanel(employees);
    }
    catch(SQLException sqlException){
      // ToDo dwg: Handle error on startup, e. g. no connection to database possible
      System.err.println(sqlException.getMessage());
      System.exit(0);
    }

    this.editEmployeePanel = new EditEmployeePanel();
    this.splitPane         = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, this.employeeTablePanel, editEmployeePanel);

    // style sarch text
    this.searchText.setPlaceholder("First or last name of employee");
    Font font = this.searchText.getFont();
    this.searchText.setFont(new Font(font.getName(), font.getStyle(), 18));

    // add action listener for 'searchEmployeeButton'
    this.searchEmployeeButton.addActionListener(e -> onClickSearch());

    // add key listener for 'searchText'
    this.searchText.addKeyListener(new KeyAdapter(){
      @Override
      public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
          MainApplicationFrame.this.onClickSearch();
        }
      }
    });

    // add list selection listener
    this.employeeTablePanel.addListSelectionListener(this::onSelectTableEntry);
  }

  private void addComponents(){
    // make the toolbar to fill the available space
    this.actionToolbarPanel.setLayout(new GridBagLayout());
    GridBagConstraints gc = new GridBagConstraints();
    gc.gridx = 0;
    gc.gridy = 0;
    gc.weightx = gc.weighty = 1;
    gc.fill = GridBagConstraints.HORIZONTAL;
    this.actionToolbarPanel.add(actionToolbar, gc);
    this.searchPanel.add(this.searchText);
    this.searchPanel.add(this.searchEmployeeButton);

    // merge toolbar and search panel into the  'northPanel'
    JPanel northPanel = new JPanel();
    northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.PAGE_AXIS));
    northPanel.add(actionToolbarPanel);
    northPanel.add(searchPanel);

    this.contentPane.add(northPanel, BorderLayout.NORTH);
    this.contentPane.add(this.splitPane, BorderLayout.CENTER);
    this.setJMenuBar(createMenuBar());
  }

  private JMenuBar createMenuBar(){
    // create menu 'File'
    JMenu fileMenu = new JMenu("File");
    fileMenu.setMnemonic(KeyEvent.VK_F);

    // create menu item 'Connection'
    JMenuItem connectionItem = new JMenuItem("Database connection", IconFactory.getDatabaseIcon());
    connectionItem.setAccelerator(KeyStroke.getKeyStroke("alt C"));
    connectionItem.setMnemonic(KeyEvent.VK_C);
    connectionItem.addActionListener(e -> onClickDatabaseConnection());

    // create menu item 'Close'
    JMenuItem closeItem      = new JMenuItem("Exit", IconFactory.getExitIcon());
    closeItem.setAccelerator(KeyStroke.getKeyStroke("alt F4"));
    connectionItem.setMnemonic(KeyEvent.VK_E);
    closeItem.addActionListener(e -> onClickCloseApplication());

    // add items to menu
    fileMenu.add(connectionItem);
    fileMenu.addSeparator();
    fileMenu.add(closeItem);

    // create menu '?'
    JMenu infoMenu = new JMenu("?");

    // create menu item 'About'
    JMenuItem aboutItem = new JMenuItem("About", IconFactory.getAboutIcon());
    aboutItem.setMnemonic('A');
    aboutItem.addActionListener(e -> onClickAboutMenuItem());

    // add items to menu
    infoMenu.add(aboutItem);

    // create menu bar
    JMenuBar menuBar = new JMenuBar();
    menuBar.add(fileMenu);
    menuBar.add(infoMenu);

    return menuBar;
  }

  private void onSelectTableEntry(ListSelectionEvent e){
    DefaultListSelectionModel defaultListSelectionModel = (DefaultListSelectionModel) e.getSource();
    int leadSelectionIndex = defaultListSelectionModel.getLeadSelectionIndex();
    if(! e.getValueIsAdjusting() && ! defaultListSelectionModel.isSelectionEmpty()){
      Long id = Long.valueOf(this.employeeTablePanel.getValueAt(leadSelectionIndex, 0).toString());
      try{
        this.editEmployeePanel.initEmployeePanel(controller.getEmployee(id));
      }
      catch(SQLException sqle){
        // ToDo dwg: Handle exception properly
        sqle.printStackTrace();
      }
    }
    if(defaultListSelectionModel.isSelectionEmpty()){
      this.editEmployeePanel.initEmployeePanel(Optional.empty());
    }
  }

  private void onClickSearch(){

    ProgressDialog progressDialog     = new ProgressDialog(this);
    SwingWorker<Void, Integer> worker = new SwingWorker<>() {

      private List<Employee> employees;
      private static final int TASK_DURATION = 100;

      @Override
      protected Void doInBackground() {
        progressDialog.setMaximum(TASK_DURATION);
        try{
          // simulate waiting time
          for(int chunk = 0; chunk <= TASK_DURATION; chunk+=5){
            publish(chunk);
            Thread.sleep(75);
          }
        }catch(InterruptedException ie){
          throw new IllegalStateException(ie);
        }

        try{
          employees = controller.getEmployeesByName(searchText.getText());
        }
        catch(SQLException sqle){
          // ToDo dwg: Handle exception properly
          System.err.println(sqle.getMessage());
          sqle.printStackTrace();
        }
        return null;
      }

      @Override
      protected void process(List<Integer> chunks) {
        int lastIndex = chunks.size() - 1;
        progressDialog.setValue(chunks.get(lastIndex));
      }

      @Override
      protected void done() {
        if(employees != null){
          employeeTablePanel.updateAndRefresh(employees);
        }
        progressDialog.setVisible(false);
      }
    };

    progressDialog.setVisible(true);
    progressDialog.setCancelListener(() -> worker.cancel(false));
    worker.execute();
  }

  private void onClickDatabaseConnection(){
    if(this.databaseConnectionDialog == null){
      this.databaseConnectionDialog = new DatabaseConnectionDialog(this);
    }
    this.databaseConnectionDialog.show();
  }

  private void onClickCloseApplication(){
    int answer = JOptionPane.showConfirmDialog(this.getContentPane(), "Are you sure you want to quit the application?", "Close application", JOptionPane.YES_NO_OPTION);
    if(answer == JOptionPane.YES_OPTION){
      System.exit(0);
    }
  }

  private void onClickAboutMenuItem(){
    JDialog aboutDialog = new AboutDialog(this);
    aboutDialog.setVisible(true);
  }

  private void onClickOpenConfigurationFile(){
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Open configuration file");
    fileChooser.addChoosableFileFilter(new FileNameExtensionFilter(".ini", ".ini"));
    fileChooser.setAcceptAllFileFilterUsed(false);
    int response = fileChooser.showOpenDialog(this.getContentPane());

    if(response == JFileChooser.APPROVE_OPTION){
      File file = fileChooser.getSelectedFile();
    }
  }
  // endregion
  ////////////////////// region Inner Classes //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  ////////////////////// End of Class //////////////////////////////////////////////////////////////////////////////////////////////////////////////// endregion
}