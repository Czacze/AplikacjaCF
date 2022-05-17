package app;

import com.l2fprod.common.swing.JTipOfTheDay;
import com.l2fprod.common.swing.tips.DefaultTip;
import com.l2fprod.common.swing.tips.DefaultTipModel;

import javax.swing.*;
import java.util.Random;

/**
 * Klasa TOTD obslugujaca wyswietlanie okna z porada dnia
 */
public class TOTD extends JDialog {

    JTipOfTheDay tip;
    DefaultTipModel defaultTips;

    public TOTD(){
        defaultTips();
        MyLogger.writeLog("INFO","Wyświetlenie okna z poradami dnia");
        tip.showDialog(this);
    }

    public void defaultTips(){
        //Dodawanie porad
        defaultTips = new DefaultTipModel();
        defaultTips.add(new DefaultTip("tip1","England has no kidney bank but it does have a Liverpool."));
        defaultTips.add(new DefaultTip("tip2","An origami shop closed unexpectedly. More to come as things unfold."));
        defaultTips.add(new DefaultTip("tip3","Big shout out to my fingers. I can always count on them."));
        defaultTips.add(new DefaultTip("tip4","Pigeons at the park are free. Take one home today!"));
        defaultTips.add(new DefaultTip("tip5","Tongue twister champion arrested. He was given a tough sentence."));

        //Umieszczenie porad w obiekcie klasy JTipOfTheDay
        tip = new JTipOfTheDay(defaultTips);

        //Losowanie porady wyswietlanej przy uruchamianiu
        Random random = new Random();
        tip.setCurrentTip(Math.abs(random.nextInt()) % defaultTips.getTipCount());
    }

}