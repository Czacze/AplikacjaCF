package app;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.freixas.jcalendar.JCalendarCombo;
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
    private JSpinner rowTextField;
    private JSpinner columnTextField;
    private JTextArea resultTextArea;
    private JLabel numberLabel,rowLabel,columnLabel,operLabel;
    private JButton submitButton, sumButton, avgButton, maxButton, minButton, calcButton;
    private JTable table;
    private JScrollPane tablePane;
    private Tabela tabela;
    private JComboBox operacja;
    private String[] oper = {"Suma","Średnia","Min","Max"};

    public SpinnerNumberModel Spin1,Spin2;

    private TitledBorder titledBorder;
    private Border blackLine;
    /**
     * Konstruktor bezparametrowy klasy <CODE>InfoBottomPanel<CODE>
     */
    public CenterPanel() {
        createGUI();
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

        // Utworzenie obiektow TextField
        this.add(northPanel,BorderLayout.NORTH);
        this.add(centerPanel,BorderLayout.CENTER);
        this.add(southPanel,BorderLayout.SOUTH);
    }
    /**
     * Metoda tworzaca panel z parametrami
     */
    public JPanel createNorthPanel() {
        JPanel jp = new JPanel();
        jp.setLayout(new GridLayout(1,4,5,1));

        numberLabel = new JLabel("Wartość");
        numberTextField = new JTextField();

        Spin1 = new SpinnerNumberModel(1,1,5,1);
        rowLabel = new JLabel("Nr wiersza");
        rowTextField = new JSpinner(Spin1);

        Spin2 = new SpinnerNumberModel(1,1,5,1);
        columnLabel = new JLabel("Nr kolumny");
        columnTextField = new JSpinner(Spin2);


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
        jp.setLayout(new FlowLayout());

        sumButton = new JButton("Suma");
        jp.add(sumButton);
        avgButton = new JButton("Średnia");
        jp.add(avgButton);
        minButton = new JButton("Min");
        jp.add(minButton);
        maxButton = new JButton("Max");
        jp.add(maxButton);

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
        jp.add(calcButton);

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
        // resulTextAreat.setEditable(false);
        resultTextArea.append("Start aplikacji\n");
        jp.add(new JScrollPane(resultTextArea),BorderLayout.CENTER);
        return jp;
    }
    /**
     * Metoda obsługujaca zdarzenie akcji
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == submitButton) {
            int row = (int) rowTextField.getValue()-1;
            int column = (int) columnTextField.getValue()-1;
            int value = Integer.parseInt(numberTextField.getText());
            tabela.setValueAt(value,row,column);
        }
    }
    /**
     * Metoda okreslajaca wartosci odstepow od krawedzi panelu
     * (top,left,bottom,right)
     */
    public Insets getInsets() {
        return new Insets(5,10,10,10);
    }
}
