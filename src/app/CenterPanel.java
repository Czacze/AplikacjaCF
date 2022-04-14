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
    private JLabel numberLabel,rowLabel,columnLabel;
    private JButton submitButton;
    private JTable Table;

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
        this.setLayout(new GridLayout(3,1,5,5));

        // Utworzenie panelu z paramtrami i wynikiem
        northPanel = createNorthPanel();
        centerPanel = createCenterPanel();
        southPanel = createSouthPanel();

        // Utworzenie obiektow TextField
        this.add(northPanel);
        this.add(centerPanel);
        this.add(southPanel);
    }
    /**
     * Metoda tworzaca panel z parametrami
     */
    public JPanel createNorthPanel() {
        JPanel jp = new JPanel();
        jp.setLayout(new GridBagLayout());

        numberLabel = new JLabel("Wprowadź liczbę");
        numberTextField = new JTextField(10);

        Spin1 = new SpinnerNumberModel(1,1,5,1);
        rowLabel = new JLabel("Numer wiersza");
        rowTextField = new JSpinner(Spin1);

        Spin2 = new SpinnerNumberModel(1,1,5,1);
        columnLabel = new JLabel("Numer kolumny");
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

        DefaultTableModel tableModel = new DefaultTableModel();
        JTable table = new JTable(tableModel);
        tableModel.addColumn("Languages");
        tableModel.insertRow(0, new Object[] { "CSS" });
        tableModel.insertRow(0, new Object[] { "HTML5" });
        tableModel.insertRow(0, new Object[] { "JavaScript" });
        tableModel.insertRow(0, new Object[] { "jQuery" });
        tableModel.insertRow(0, new Object[] { "AngularJS" });
        tableModel.insertRow(tableModel.getRowCount(), new Object[] { "ExpressJS" });
        this.setSize(550, 350);
        this.add(new JScrollPane(table));
        this.setVisible(true);

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
            String param = numberTextField.getText();
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
