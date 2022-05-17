package app;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.*;
import java.awt.event.*;
import com.l2fprod.common.swing.JTipOfTheDay;

import static java.lang.Thread.sleep;
import static javax.swing.JOptionPane.*;
import static app.MyLogger.log;
/**
 * Program AplikacjaCF wyświetla tabelę do której można wprowadzać wartości liczbowe.
 * W aplikacji można dokonać różnych działań np. obliczyć średnią, sumę, wartości minimalne i maksymalne,
 * wyświetlić tabelę i wyświetlić wybraną datę.
 *
 * @author Cezary Formalewicz
 * @version 1.01
 * @since 2022-04-09
 */

/**
 * Klasa MyWindow rozszerzajaca klase JFrame i implementujaca interfejs ActionListener
 * Obsluguje tworzenie okna, pasek narzedzi i pasek statusu
 */
public class MyWindow extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1;
    public static final int WIDTH = 600;
    public static final int HEIGHT = 450;

    private JPanel conPane;

    InfoBottomPanel infoPanel = null;
    CenterPanel centerPanel = null;
    MyLogger logger = null;
    AboutWindow aboutWindow = null;

    private JMenu fileMenu, helpMenu, viewMenu;
    private JMenuItem exitMenuItem, aboutMenuItem, helpMenuItem;
    private JMenuBar jMenuBar;
    private HelpWindow helpWindow;

    JToolBar jToolBar;
    JButton jtbExit, jtbHelp, jtbAbout;

    private Icon iconExit, mIconExit, iconHelp, mIconHelp, iconAbout, mIconAbout;
    private static final String ICON_PATH = ("/resources/");



    public MyWindow() {
        setTitle("Moje okno");
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setResizable(true);

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

        //Utworzenie GUI
        createMenus();
        createIcons();

        //Utworzenie elementów okna
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
            InfoBottomPanel.setInfoString("Próba zamknięcia aplikacji");
            log.info("Otwarto Potwierdzenie zamknięcia");
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
                log.info("Zamkniecie aplikacji");
                dispose();
                System.exit(0);
            }
            else if(value == NO_OPTION){
                InfoBottomPanel.setInfoString("Anulowanie zamknięcia");
                log.info("Anulowano Zamkniecie aplikacji");
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
            fileMenu.add(exitMenuItem);
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
                log.info("Blad tworzenia ikon");
                System.out.println("IconError");
            }
        }

    /**
     * Metoda obslugujaca zdarzenie akcji
     * @param ae obiekt klasy nasluchujacej <code>ActionListener</code>
     */
        public void actionPerformed(ActionEvent ae) {
            if ((ae.getSource() == aboutMenuItem) || (ae.getSource() == jtbAbout)) {

                if (aboutWindow != null) aboutWindow.setVisible(true);
                else {
                    log.info("Otwarto O autorze");
                    aboutWindow = new AboutWindow();
                    aboutWindow.setVisible(true);
                }
            }
            else if ((ae.getSource() == jtbExit) || (ae.getSource() == exitMenuItem)) {
                    closeWindow();
                }
            else if ((ae.getSource() == jtbHelp) || (ae.getSource() == helpMenuItem)) {
                if (helpWindow != null) helpWindow.setVisible(true);
                else {
                    log.info("Otwarto O programie");
                    helpWindow = new HelpWindow();
                    helpWindow.setVisible(true);
                }
            }

            }


    /**
     * Metoda sluzaca do uruchomienia aplikacji, tworzony jest w niej obiekt klasy <code>MyWindow</code>
     * @param args Tablica parametrów klasy <code>String</code>, niewykorzystywana
     */
    public static void main(String[] args) {
        System.out.println("Start aplikacji");
        MyWindow myWindow = new MyWindow();
        myWindow.setVisible(true);

        try{
            sleep(150);
        } catch(InterruptedException e){
            e.printStackTrace();
        }

        TOTD tip = new TOTD();
    }
}
