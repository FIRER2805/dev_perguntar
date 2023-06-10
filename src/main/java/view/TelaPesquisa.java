package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class TelaPesquisa extends JPanel {
	


	/**
	 * Create the panel.
	 */
	public TelaPesquisa() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(168, 140, 46, 14);
		add(lblNewLabel);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(224, 135, 163, 113);
		add(textArea);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(541, 11, 17, 577);
		add(scrollBar);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(224, 400, 163, 113);
		add(textArea_1);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(168, 405, 46, 14);
		add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 168, 295, 168);
		add(scrollPane);
		
		
		
		
	}
}
