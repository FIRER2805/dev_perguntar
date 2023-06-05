package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.JButton;

public class TelaCriarPergunta extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public TelaCriarPergunta() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Criar Pergunta");
		lblNewLabel.setBounds(267, 49, 70, 23);
		add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(202, 106, 86, 20);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Titulo");
		lblNewLabel_1.setBounds(133, 109, 59, 14);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Duvida");
		lblNewLabel_1_1.setBounds(133, 168, 59, 14);
		add(lblNewLabel_1_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(404, 105, 30, 22);
		add(comboBox);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(213, 168, 218, 186);
		add(textPane);
		
		JButton btnNewButton = new JButton("Postar");
		btnNewButton.setBounds(241, 376, 89, 23);
		add(btnNewButton);

	}
}
