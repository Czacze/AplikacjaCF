package app;

import com.l2fprod.common.swing.JTipOfTheDay;
import com.l2fprod.common.swing.tips.DefaultTip;
import com.l2fprod.common.swing.tips.DefaultTipModel;

import javax.swing.*;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class TOTD extends JDialog {

    JTipOfTheDay tip;
    DefaultTipModel defaultTips;

    public TOTD(){
        defaultTips();
        tip.showDialog(this);
    }

    public void defaultTips(){
        defaultTips = new DefaultTipModel();
        defaultTips.add(new DefaultTip("tip1","England has no kidney bank but it does have a Liverpool."));
        defaultTips.add(new DefaultTip("tip2","An origami shop closed unexpectedly. More to come as things unfold."));
        defaultTips.add(new DefaultTip("tip3","Big shout out to my fingers. I can always count on them."));
        defaultTips.add(new DefaultTip("tip4","Pigeons at the park are free. Take one home today!"));
        defaultTips.add(new DefaultTip("tip5","Tongue twister champion arrested. He was given a tough sentence."));

        tip = new JTipOfTheDay(defaultTips);

        Random random = new Random();
        tip.setCurrentTip(Math.abs(random.nextInt()) % defaultTips.getTipCount());
    }

    public String randomString(){
        byte[] array = new byte[150];
        new Random().nextBytes(array);
        return new String(array, StandardCharsets.ISO_8859_1);
    }
}