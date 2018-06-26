package examples;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * User: DanielW
 * Date: 22.06.2018
 * Time: 13:06
 */

public class MyFlowLayoutFrame extends JFrame{
  ////////////////////// region Variables  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


  // endregion
  ////////////////////// region Initialisation /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  MyFlowLayoutFrame(){

    init();

  }

  // endregion
  ////////////////////// region Methods ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  public static void main(String... args){
    EventQueue.invokeLater(() -> {
      MyFlowLayoutFrame frame = new MyFlowLayoutFrame();
      frame.setVisible(true);
    } );
  }

  private void init(){
    // configuration of frame
    this.setTitle("My Frame");
    this.setSize(800, 800);
    this.setLocationRelativeTo(null); // centered on monitor
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    // get container and layout
    Container contentPane = this.getContentPane();
    FlowLayout layout     = new FlowLayout(FlowLayout.LEFT, 10, 10);
    contentPane.setLayout(layout);

    // create button
    JButton button = new JButton("Quit");
    button.addActionListener(e -> System.exit(0));

    JButton button2 = new JButton("button2");
    JButton button3 = new JButton("button3");
    JButton button4 = new JButton("button4");
    JButton button5 = new JButton("button5");

    // create menu 'File'
    JMenu fileMenu      = new JMenu("File");
    fileMenu.setMnemonic(KeyEvent.VK_F);

    // create sub menu item 'New'
    JMenu newItemSubMenu = new JMenu("New");
    newItemSubMenu.setMnemonic(KeyEvent.VK_N);
    newItemSubMenu.add(new JMenuItem("Employee"));
    newItemSubMenu.add(new JMenuItem("Job"));
    newItemSubMenu.add(new JMenuItem("Career Level"));

    // create menu item 'Close'
    JMenuItem closeItem      = new JMenuItem("Exit", KeyEvent.VK_E);
    KeyStroke ctrlEKeyStroke = KeyStroke.getKeyStroke("control E");
    closeItem.setAccelerator(ctrlEKeyStroke);
    closeItem.addActionListener(e -> System.exit(0));
    //ImageIcon closeItemIcon = new ImageIcon(MyFrame.class.getResource("close2.png"));
    //JMenuItem closeItem     = new JMenuItem("Exit", closeItemIcon);

    // create menu bar
    JMenuBar menuBar = new JMenuBar();

    // add items and set menu bar
    fileMenu.add(newItemSubMenu);
    fileMenu.addSeparator();
    fileMenu.add(closeItem);
    menuBar.add(fileMenu);
    this.setJMenuBar(menuBar);

    // add components to content contentPane
    contentPane.add(button);
    contentPane.add(button2);
    contentPane.add(button3);
    contentPane.add(button4);
    contentPane.add(button5);

  }

  // endregion
  ////////////////////// region Inner Classes //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  ////////////////////// End of Class //////////////////////////////////////////////////////////////////////////////////////////////////////////////// endregion
}

