package app;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

import static javax.swing.JOptionPane.*;

public class MyWindow extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1;
    private static final int WIDTH = 600;
    private static final int HEIGHT = 450;

    private JPanel conPane;

    AboutWindow aboutWindow = null;
    InfoBottomPanel infoPanel = null;
    CenterPanel centerPanel = null;
    MyLogger logger = null;

    private JMenu fileMenu, helpMenu, viewMenu;
    private JMenuItem exitMenuItem, aboutMenuItem, helpMenuItem;
    private JMenuBar jMenuBar;

    JToolBar jToolBar;
    JButton jtbExit, jtbHelp, jtbAbout;

    private Icon iconExit, mIconExit, iconHelp, mIconHelp, iconAbout, mIconAbout;
    private static final String ICON_PATH = ("/resources/");



    public MyWindow() {
        setTitle("Moje okno");
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        //Zamykanie okna
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                closeWindow();
            }
        });

        //Utworzenie głównego kontekstu
        conPane = (JPanel) this.getContentPane();
        conPane.setLayout(new BorderLayout());
        //Utworzenie dziennika zdarzeń
        logger = new MyLogger();

        //utworzenie GUI
        createMenus();
        createIcons();

        jToolBar = new JToolBar();
        createJToolBar(jToolBar);

        infoPanel = new InfoBottomPanel();
        createPanel();

        centerPanel= new CenterPanel();

        conPane.add(jToolBar, BorderLayout.NORTH);
        conPane.add(infoPanel, BorderLayout.SOUTH);
        conPane.add(centerPanel, BorderLayout.CENTER);

    }

        private void closeWindow() {
            //Okno dialogowe - zamykanie aplikacji
            Object[] options = {"Tak","Nie"};
            int value = showOptionDialog(
                    this,
                    "Zamknąć aplikację?",
                    "Uwaga",
                    YES_NO_CANCEL_OPTION,
                    WARNING_MESSAGE,
                    null,
                    options,
                    "Tak"
            );

            if(value == YES_OPTION) {
                MyLogger.writeLog("INFO","Zamkniecie aplikacji");
                dispose();
                System.exit(0);
            }
        }

        private void createMenus(){
        //Pasek menu - utworzenie
            JMenuBar menuBar = new JMenuBar();

        //Pola menu głównego
            fileMenu = createJMenu("Plik",KeyEvent.VK_P,true);
            helpMenu = createJMenu("Pomoc",KeyEvent.VK_O,true);

            exitMenuItem = createJMenuItem("Zamknij", mIconExit,
                    KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.ALT_MASK), true);
            aboutMenuItem = createJMenuItem("O autorze", mIconAbout,
                    KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.ALT_MASK), true);
            helpMenuItem = createJMenuItem("O programie", mIconHelp,
                    KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.ALT_MASK), true);


            menuBar.add(fileMenu);
            menuBar.add(helpMenu);
            helpMenu.add(aboutMenuItem);
            this.setJMenuBar(menuBar);
        }

        public void createPanel(){
            InfoBottomPanel.setInfoString("Start aplikacji");
        }

        public void createJToolBar(JToolBar cjtb) {

            cjtb.setFloatable(false);
            cjtb.add(Box.createHorizontalStrut(5));

            // Utworzenie przycisków paska narzedziowego
            jtbExit = createJButtonToolBar("Wyjście",iconExit,true);
            jtbHelp = createJButtonToolBar("O programie",iconHelp,true);
            jtbAbout = createJButtonToolBar("O autorze",iconAbout,true);

            // dodanie przycisków do paska narzędziowego
            cjtb.add(jtbExit);
            cjtb.addSeparator();
            cjtb.add(jtbHelp);
            cjtb.add(jtbAbout);
        }

        public JMenu createJMenu(String name, int keyEvent,boolean enable) {
            JMenu jMenu = new JMenu(name);
            jMenu.setMnemonic(keyEvent);
            jMenu.setEnabled(enable);
            return jMenu;
        }

        public JMenuItem createJMenuItem(String name, Icon icon, KeyStroke key, boolean enable) {
            JMenuItem jMI;
            if(icon != null)
                jMI = new JMenuItem(name,icon);
            else jMI = new JMenuItem(name);
            jMI.setAccelerator(key);
            jMI.addActionListener(this);
            jMI.setEnabled(enable);
            return jMI;
        }

        public JButton createJButtonToolBar(String tooltip,Icon icon,boolean enable) {
            JButton jb = new JButton("",icon);
            jb.setToolTipText(tooltip);
            jb.addActionListener(this);
            jb.setEnabled(enable);
            return jb;
        }


        public Icon createMyIcon(String nameFile)
//                throws IconException
        {
            String name = ICON_PATH + nameFile;
            Icon icon = new ImageIcon(getClass().getResource(name));
//            URL url = getClass().getResource(name);
//            if(url == null) throw new IconException();
//            else icon = new ImageIcon(url);

            return icon;
        }

        private void createIcons() {
            try{
                //Toolbar - ikony 24x24
                iconExit = createMyIcon("close.jpg");
                iconHelp = createMyIcon("help_context.jpg");
                iconAbout = createMyIcon("about.jpg");
                //Menu - ikony 16x16
                mIconExit = createMyIcon("min_close.jpg");
                mIconHelp = createMyIcon("min_help_context.jpg");
                mIconAbout = createMyIcon("min_about.jpg");

            }catch(Exception e){
                MyLogger.writeLog("ERROR","Blad tworzenia ikon");
                System.out.println("IconError");
            }
        }

        public void actionPerformed(ActionEvent ae){
            if((ae.getSource() == aboutMenuItem) || (ae.getSource() == jtbAbout)) {
                if (aboutWindow != null) aboutWindow.setVisible(true);
                else {
                    showMessageDialog(this,
                            "Autor: Cezary Formalewicz",
                            "O autorze",
                            INFORMATION_MESSAGE);
                }
            }
        }



    public static void main(String[] args) {
        System.out.println("Start aplikacji");
        MyWindow myWindow = new MyWindow();
        myWindow.setVisible(true);

    }
}
