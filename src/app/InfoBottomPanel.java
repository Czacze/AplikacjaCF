package app;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * Program <code>MyWindow</code>
 * Klasa <code>InfoBottomPanel</code> definiująca dolny panel
 * aplikacji zawierajacy informacje o realizowanych zadaniach
 * @author
 * @version 1.0	15/12/2010
 */
public class InfoBottomPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private static JTextField infoTF, timeInfoTF,userInfoTF;
    private JLabel infoLabel, timeLabel,userLabel;


    /**
     * Konstruktor bezparametrowy klasy <CODE>InfoBottomPanel<CODE>
     */
    public InfoBottomPanel() {
        createGUI();
    }

    /**
     * Metoda tworząca graficzny interfejs użytkownika
     */
    public void createGUI() {
        this.setBackground(new Color(210,210,210));
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        // Utworzenie etykiet tekstowych
        infoLabel = new JLabel("Status:");
        timeLabel = new JLabel("Czas:");
        userLabel = new JLabel("User:");

        // Utworzenie obiektow TextField
        infoTF = new JTextField("Start aplikacji");
        infoTF.putClientProperty("JComponent.sizeVariant", "small");
        infoTF.setMinimumSize(new Dimension(200,20));


        timeInfoTF = new JTextField("Godzina");
        timeInfoTF.putClientProperty("JComponent.sizeVariant", "small");
        timeInfoTF.setMinimumSize(new Dimension(30,20));

        userInfoTF = new JTextField("");
        userInfoTF.putClientProperty("JComponent.sizeVariant", "small");
        userInfoTF.setMinimumSize(new Dimension(100,20));

        this.add(infoLabel);
        this.add(Box.createHorizontalStrut(10));
        this.add(infoTF);
        this.add(Box.createHorizontalStrut(20));
        this.add(timeLabel);
        this.add(Box.createHorizontalStrut(10));
        this.add(timeInfoTF);
        this.add(Box.createHorizontalStrut(20));
        this.add(userLabel);
        this.add(Box.createHorizontalStrut(10));
        this.add(userInfoTF);
    }

    

    /**
     * Publiczna metoda ustawiajaca zmienna infoString
     * @param infoString nowa wartosc zmiennej infoString
     */
    public static void setInfoString(String infoString) {
        infoTF.setText(infoString);
    }

    /**
     * Publiczna metoda ustawiajaca zmienna� timeStatus
     * @param timeStatus nowa wartosc zmiennej timeStatus
     */
    public void setDbStatus(boolean timeStatus) {

    }

    /**
     * Publiczna metoda ustawiajaca zmienna userInfoString
     * @param userInfoString nowa wartosc zmiennej userInfoString
     */
    public static void setUserInfoString(String userInfoString) {
        userInfoTF.setText(userInfoString);
    }

    /**
     * Metoda okreslajaca wartosci odstepow od krawedzi panelu
     * (top,left,bottom,right)
     */
    public Insets getInsets() {
        return new Insets(5,5,3,5);
    }
}