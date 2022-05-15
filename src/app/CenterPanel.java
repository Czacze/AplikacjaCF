package app;

import com.l2fprod.common.demo.OutlookBarMain;
import com.l2fprod.common.swing.JOutlookBar;
import com.l2fprod.common.swing.PercentLayout;
import jdk.nashorn.internal.scripts.JO;
import org.freixas.jcalendar.DateEvent;
import org.freixas.jcalendar.DateListener;
import org.freixas.jcalendar.JCalendarCombo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.ButtonUI;
import javax.swing.table.TableModel;

/**
 * Program <code>MyWindow</code>
 * Klasa <code>CenterPanel</code> definiujaca centralny panel
 * aplikacji zawierajacy glowna funkcjonalnosc aplikacji
 * @author
 * @version 1.0	15/12/2010
 */
public class CenterPanel extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel northPanel, centerPanel, southPanel;
    private JTextField numberTextField;
    private JSlider rowTextField;
    private JSlider columnTextField;
    private JTextArea resultTextArea;
    private JLabel numberLabel,rowLabel,columnLabel,operLabel;
    private static JButton submitButton;
    private JButton zeroButton;
    private JButton fillButton;
    private JButton susButton;
    private JButton saveButton;
    private JButton calcButton;
    private JButton wykres,oAutorze;
    private JTable table;
    private JScrollPane tablePane;
    private Tabela tabela;
    private JComboBox operacja;
    private String[] oper = {"Suma","Średnia","Min i Max"};
    private JCalendarCombo kalendarz = new JCalendarCombo();
    AboutWindow aboutWindow = null;



    private TitledBorder titledBorder;
    private Border blackLine;
    /**
     * Konstruktor bezparametrowy klasy <CODE>InfoBottomPanel<CODE>
     */
    public CenterPanel() {
        createGUI();
    }

    private JOutlookBar createJOutlookBar(){
        JOutlookBar jOutlookBar = new JOutlookBar();

        return jOutlookBar;
    }
    /**
     * Metoda tworzacaca graficzny interfejs uzytkownika
     */
    public void createGUI() {
        this.setLayout(new BorderLayout());

        // Utworzenie panelu z paramtrami i wynikiem
        northPanel = createNorthPanel();
        centerPanel = createCenterPanel();
        southPanel = createSouthPanel();
        JTree tree = new JTree();
        JOutlookBar outlook = createJOutlookBar();

        outlook.setTabPlacement(JTabbedPane.LEFT);
        addTab(outlook, "Funkcje");
        outlook.addTab("JTree", outlook.makeScrollPane(tree));

        // Utworzenie obiektow TextField
        this.add(northPanel,BorderLayout.NORTH);
        this.add(centerPanel,BorderLayout.CENTER);
        this.add(southPanel,BorderLayout.SOUTH);
        this.add(outlook,BorderLayout.WEST);
    }
    /**
     * Metoda tworzaca panel z parametrami
     */
    public JPanel createNorthPanel() {
        JPanel jp = new JPanel();
        jp.setLayout(new GridLayout());

        numberLabel = new JLabel("Wartość");
        numberLabel.setHorizontalAlignment(SwingConstants.CENTER);
        numberTextField = new JTextField();
        //wymuszenie wpisania liczb
        numberTextField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c =e.getKeyChar();
                if(!(Character.isDigit(c)|| (c==KeyEvent.VK_BACK_SPACE) || (c==KeyEvent.VK_DELETE) || (c==KeyEvent.VK_MINUS))){
                    e.consume();
                }
            }
        });


        rowTextField = new JSlider(JSlider.HORIZONTAL,1,5,1);
        rowTextField.setMajorTickSpacing(1);
        rowTextField.setPaintTicks(true);
        rowTextField.setPaintLabels(true);
        rowLabel = new JLabel("Nr wiersza");
        rowLabel.setHorizontalAlignment(SwingConstants.CENTER);


        columnTextField = new JSlider(JSlider.HORIZONTAL,1,5,1);
        columnTextField.setMajorTickSpacing(1);
        columnTextField.setPaintTicks(true);
        columnTextField.setPaintLabels(true);
        columnLabel = new JLabel("Nr kolumny");
        columnLabel.setHorizontalAlignment(SwingConstants.CENTER);


        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);

        jp.add(numberLabel);
        jp.add(numberTextField);
        jp.add(rowLabel);
        jp.add(rowTextField);
        jp.add(columnLabel);
        jp.add(columnTextField);
        jp.add(submitButton);
        return jp;
    }


    public JPanel createCenterPanel(){
        JPanel jp = new JPanel();
        jp.setLayout(new BorderLayout());
        Dimension d = new Dimension(100,105);


        tabela = new Tabela();
        table = new JTable(tabela);
        tablePane = new JScrollPane(table);
        tablePane.setPreferredSize(d);
        jp.add(tablePane, BorderLayout.NORTH);
        jp.add(createCenterPanel2(),BorderLayout.CENTER);
        jp.add(createCenterPanel3(),BorderLayout.SOUTH);

        return jp;
    }

    public JPanel createCenterPanel2(){
        JPanel jp = new JPanel();
        jp.setLayout(new GridBagLayout());

        zeroButton = new JButton("Zerowanie");
        zeroButton.addActionListener(this);
        jp.add(zeroButton);

        jp.add(Box.createHorizontalStrut(20));
        fillButton = new JButton("Zapełnianie");
        fillButton.addActionListener(this);
        jp.add(fillButton);

        jp.add(Box.createHorizontalStrut(20));
        saveButton = new JButton("Zapis");
        saveButton.addActionListener(this);
        jp.add(saveButton);

//        jp.add(Box.createHorizontalStrut(20));
//        susButton = new JButton("Amogus");
//        susButton.addActionListener(this);
//        jp.add(susButton);

        jp.setBackground(new Color(232,220,202));
        return jp;
    }

    public JPanel createCenterPanel3(){
        JPanel jp = new JPanel();
        jp.setLayout(new FlowLayout());

        operLabel = new JLabel("Wybór operacji");
        jp.add(operLabel);

        operacja = new JComboBox(oper);
        jp.add(operacja);

        calcButton = new JButton("Oblicz");
        calcButton.addActionListener(this);
        jp.add(calcButton);

        createCalendar();
        jp.add(kalendarz);

        return jp;
    }

    /**
     * Metoda tworzaca panel z wynikami
     */
    public JPanel createSouthPanel() {
        JPanel jp = new JPanel();

        blackLine = BorderFactory.createLineBorder(Color.gray);
        titledBorder = BorderFactory.createTitledBorder(blackLine,
                "Uzyskany rezultat");
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        jp.setBorder(titledBorder);
        jp.setLayout(new BorderLayout());

        resultTextArea = new JTextArea();
        // zawijanie wierszy
        resultTextArea.setLineWrap(true);
        // edycja pola TextArea
        resultTextArea.setEditable(false);
        resultTextArea.append("\n");
        jp.add(new JScrollPane(resultTextArea),BorderLayout.CENTER);
        return jp;
    }
    /**
     * Metoda obsługujaca zdarzenie akcji
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == submitButton) {
            try {
                int row = (int) rowTextField.getValue()-1;
                int column = (int) columnTextField.getValue()-1;
                int value = Integer.parseInt(numberTextField.getText());

                tabela.setValueAt(value, column, row);
            }catch(Exception e){
                JOptionPane.showMessageDialog(this,
                        "Błędna wartość",
                        "Błąd",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        else if(ae.getSource() == zeroButton) {
            tabela.setZeroTable();
        }
        else if(ae.getSource() == fillButton) {
            tabela.setRandomTable();
        }
        else if(ae.getSource() == saveButton){
            exportToCSV();
        }
        else if(ae.getSource() == calcButton){
            switch(operacja.getSelectedIndex()){
            case 0:
                resultTextArea.setText("Suma wynosi: " + tabela.calculateSum());
                break;
            case 1:
                resultTextArea.setText("Średnia wynosi: " + tabela.calculateAverage());
                break;
            case 2:
                resultTextArea.setText("Min wynosi: " + tabela.calculateMin() + " , Max wynosi: " + tabela.calculateMax());
                break;
            }
        }
        else if(ae.getSource() == wykres){

        }
        else if(ae.getSource() == oAutorze){
            if (aboutWindow != null) aboutWindow.setVisible(true);
            else {
                aboutWindow = new AboutWindow();
                aboutWindow.setVisible(true);
            }
        }
    }
    public void exportToCSV(){
        JFileChooser fileBrowser = new JFileChooser();
        int select = fileBrowser.showSaveDialog(this);
        if (select == JFileChooser.APPROVE_OPTION){
            try{
                TableModel model = table.getModel();
                FileWriter csv = new FileWriter(fileBrowser.getSelectedFile()+".csv");

                for(int i=0; i<model.getRowCount(); i++){
                    for (int j=0; j<model.getColumnCount(); j++){
                        csv.write(model.getValueAt(i,j) + ",");
                    }
                    csv.write("\n");
                }
                csv.close();
            }catch(IOException e){
                JOptionPane.showMessageDialog(this,
                        "Wystąpił błąd przy zapisywaniu pliku",
                        "Błąd",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    /**
     * Metoda okreslajaca wartosci odstepow od krawedzi panelu
     * (top,left,bottom,right)
     */
    public Insets getInsets() {
        return new Insets(5,10,10,10);
    }
    public JLabel getNumberLabel() {
        return numberLabel;
    }
    public static JButton getSubmitButton(){
        return submitButton;
    }

    private void createCalendar(){

        this.kalendarz = new JCalendarCombo();
        kalendarz.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        kalendarz.addActionListener(this);
        kalendarz.addDateListener(new DateListener() {
            @Override
            public void dateChanged(DateEvent dateEvent) {
                String formatDateOutput = new SimpleDateFormat("yyyy-MM-dd").format(kalendarz.getDate());
                resultTextArea.setText("Wybrano datę: "+formatDateOutput);
            }
        });
    }

    void addTab(JOutlookBar tabs, String title) {
        JPanel panel = new JPanel();
        panel.setLayout(new PercentLayout(PercentLayout.VERTICAL, 0));
        panel.setOpaque(false);

        wykres = new JButton("Wykres",new ImageIcon(OutlookBarMain.class.getResource("icons/folder32x32.png")));
        wykres.addActionListener(this);
        try {
                wykres.setUI((ButtonUI)Class.forName(
                        (String)UIManager.get("OutlookButtonUI")).newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        panel.add(wykres);

        oAutorze = new JButton("O autorze",new ImageIcon(OutlookBarMain.class.getResource("icons/propertysheet32x32.png")));
        oAutorze.addActionListener(this);
        try {
            oAutorze.setUI((ButtonUI)Class.forName(
                    (String)UIManager.get("OutlookButtonUI")).newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
        panel.add(oAutorze);

        JScrollPane scroll = tabs.makeScrollPane(panel);
        tabs.addTab("", scroll);

        // this to test the UI gets notified of changes
        int index = tabs.indexOfComponent(scroll);
        tabs.setTitleAt(index, title);
        tabs.setToolTipTextAt(index, title + " Tooltip");
    }
}