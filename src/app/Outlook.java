package app;

import com.l2fprod.common.demo.OutlookBarMain;
import com.l2fprod.common.swing.JOutlookBar;
import com.l2fprod.common.swing.PercentLayout;

import javax.swing.*;
import javax.swing.plaf.ButtonUI;

public class Outlook {
    public JPanel Outlook(){
        JOutlookBar outlook = new JOutlookBar();
        outlook.setTabPlacement(JTabbedPane.LEFT);
        addTab(outlook, "Folders");
        addTab(outlook, "Backup");

        // show it is possible to add any component to the bar
        JTree tree = new JTree();
        outlook.addTab("A JTree", outlook.makeScrollPane(tree));

        outlook.addTab("Disabled", new JButton());
        outlook.setEnabledAt(3, false);

        JPanel panel = new JPanel(new PercentLayout(PercentLayout.HORIZONTAL, 3));
        panel.add(outlook, "100");
        return panel;
    }

    void addTab(JOutlookBar tabs, String title) {
        JPanel panel = new JPanel();
        panel.setLayout(new PercentLayout(PercentLayout.VERTICAL, 0));
        panel.setOpaque(false);

        String[] buttons = new String[] {"Inbox", "icons/outlook-inbox.gif",
                "Outbox", "icons/outlook-outbox.gif", "Drafts", "icons/outlook-inbox.gif",
                "Templates", "icons/outlook-inbox.gif", "Deleted Items",
                "icons/outlook-trash.gif",};

        for (int i = 0, c = buttons.length; i < c; i += 2) {
            JButton button = new JButton(buttons[i]);
            try {
                button.setUI((ButtonUI)Class.forName(
                        (String)UIManager.get("OutlookButtonUI")).newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
            button.setIcon(new ImageIcon(OutlookBarMain.class
                    .getResource(buttons[i + 1])));
            panel.add(button);
        }

        JScrollPane scroll = tabs.makeScrollPane(panel);
        tabs.addTab("", scroll);

        // this to test the UI gets notified of changes
        int index = tabs.indexOfComponent(scroll);
        tabs.setTitleAt(index, title);
        tabs.setToolTipTextAt(index, title + " Tooltip");
    }

}
