package app;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Klasa <code>StatusPanel</code> odpowiada za obsługę paska statusu w dolnym lewym rogu aplikacji.
 * Wyświetla ostatnio wykonane akcje.
 */

public class StatusPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel eventLabel;
	private JTextField numRow, numCol;
	
	public StatusPanel() {
		setLayout(new BorderLayout());
		setBackground(Color.GREEN);
		add(eventLabel, BorderLayout.WEST);
	}

	private void initGUI() {
		eventLabel = new JLabel("Start aplikacji");
		numRow = new JTextField("1");
		numRow.setHorizontalAlignment(JTextField.RIGHT);
		numCol = new JTextField("1");
		numCol.setHorizontalAlignment(JTextField.RIGHT);
	}

}
